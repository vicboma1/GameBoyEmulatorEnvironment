package app.components.panel.grid

import main.kotlin.utils.image.BufferedImageMemoryFromComponent
import main.kotlin.utils.image.scale
import main.kotlin.utils.listGames.ListGames
import java.awt.Color
import java.awt.Component
import java.awt.Container
import java.awt.Dimension
import java.awt.image.BufferedImage
import java.util.*
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ConcurrentHashMap
import javax.swing.BoxLayout
import javax.swing.ImageIcon
import javax.swing.JLabel
import javax.swing.JPanel

/**
 * Created by vicboma on 13/04/17.
 */
object CacheGrid {

    val map = object : ConcurrentHashMap<String, ImageIcon>(){}

    val convert = BufferedImageMemoryFromComponent()

    fun createRefImage(listGames: ListGames, classLoader: ClassLoader, w: Int, h: Int) : CompletableFuture<ConcurrentHashMap<String, ImageIcon>> {
        val imageDefault = ImageIcon().scale(w, h, classLoader.getResource("cover/_gbNotFound.png").file.toString())

        try {
            Arrays.stream(listGames.rowNames)
                   .filter {  it -> it != null }
                   .map { it -> it[1].toString().toLowerCase().split(".")[0].toString().plus(".png") }
                   .forEach { it ->
                      // map[it] = "cover/$it"
                       val image = classLoader.getResource("cover/$it")
                       val bufferedImage: BufferedImage?
                       bufferedImage = when (image) {
                           null -> imageDefault
                           else -> ImageIcon().scale(w, h, image.file.toString())
                       }

                       val panel = JPanel().apply {
                           layout = boxLayout(this).apply {
                               setBackground(Color(0, 0, 0))
                               isOpaque = false
                           }
                           add(jLabelFactory(" "))
                           add(jLabelFactory(ImageIcon(bufferedImage)))
                           add(jLabelFactory(" "))
                           add(jLabelFactory(it))
                           size = Dimension(w, h)
                           isOpaque = false
                           setBackground(Color(0, 0, 0))
                       }

                       val bufferedPanel = convert.invoke(panel)
                       map["$it"] = ImageIcon(bufferedPanel)
                       Thread.sleep(10)

                   }
        } catch(e: Exception) {
            println(e.message)
            e.stackTrace
        } finally {
            println("****** FIN LOAD MODEL *******")
            return CompletableFuture.completedFuture(map)
        }
    }

    /*   fun getImageIcon(listGames: Array<Array<Any>>, row: Int, cols: Int, col:Int,w: Int, h: Int, classLoader: ClassLoader, imageDefault: BufferedImage) : ImageIcon {

        val nameRom = listGames[(row * cols) + col][1].toString()
        val nameGame = nameRom.toLowerCase().split(".")[0].toString().plus(".png")

        println(nameGame+" $row$col")

        val image = classLoader.getResource("cover/$nameGame")
        val bufferedImage: BufferedImage?
        bufferedImage = when (image) {
            null -> imageDefault
            else -> ImageIcon().scale(w, h, image.file.toString())
        }

        val panel = JPanel().apply {
            layout = boxLayout(this).apply {
                setBackground(Color(0, 0, 0))
                isOpaque = false
            }
            add(jLabelFactory(" "))
            add(jLabelFactory(ImageIcon(bufferedImage)))
            add(jLabelFactory(" "))
            add(jLabelFactory(nameRom))
            size = Dimension(w, h)
            isOpaque = false
            setBackground(Color(0, 0, 0))
        }

        val bufferedPanel = panel.bufferedImageMemory()
        return ImageIcon(bufferedPanel)
        //val componentIcon = FadeComponentExt()
        //componentIcon.imageIcon = imageIcon
    }
    */
    private fun boxLayout(container : Container) = BoxLayout(container, BoxLayout.Y_AXIS)

    private fun jLabelFactory(name : String ) = JLabel(name).apply {
                    setBackground(Color(0, 0, 0))
                    isOpaque = false
                    setAlignmentX(Component.CENTER_ALIGNMENT)
                }

    private fun jLabelFactory(imageIcon : ImageIcon ) = JLabel(imageIcon).apply {
                    setBackground(Color(0, 0, 0))
                    isOpaque = false
                    setAlignmentX(Component.CENTER_ALIGNMENT)
                }
}