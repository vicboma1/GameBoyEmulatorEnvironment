package app.components.panel.grid

import main.kotlin.utils.image.BufferedImageMemoryFromComponent
import main.kotlin.utils.image.scale
import main.kotlin.utils.listGames.ListGames
import java.awt.Color
import java.awt.Component
import java.awt.Container
import java.awt.Dimension
import java.awt.image.BufferedImage
import java.io.Serializable
import java.util.*
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ConcurrentLinkedQueue
import javax.swing.BoxLayout
import javax.swing.ImageIcon
import javax.swing.JLabel
import javax.swing.JPanel

/**
 * Created by vicboma on 13/04/17.
 */
object CacheGrid {

    var state = CacheState.STOP
    var limit = 0
    val queue = ConcurrentLinkedQueue<CompletableFuture<Map<String, Serializable>>>()

    val convert = BufferedImageMemoryFromComponent()

    fun createRefImage(listGames: ListGames, classLoader: ClassLoader, bufferedDefault : BufferedImage, bufferedImage : BufferedImage) : CompletableFuture<Queue<CompletableFuture<Map<String, Serializable>>>> {
        limit = listGames.rowNames?.size!!
        state = CacheState.LOADING
        val imageDefault = ImageIcon().scale(bufferedDefault, classLoader.getResource("cover/_gbNotFound.png").file.toString())

        try {
            val rows = listGames.rowNames?.size
            var cols = listGames.rowNames!![0].size-1
            for (row in 0..rows!!) {
                for (col in 0..cols - 1) {

                    val index = (row * cols) + col
                    if( index > rows)
                        break

                    queue.add( CompletableFuture.supplyAsync {
                        print("($row * $cols) + $col = ${(row * cols) + col}")
                        val nameRom = listGames.rowNames!![(row * cols) + col][1].toString()
                        val nameImage = nameRom.toLowerCase().split(".")[0].toString().plus(".png")
                        val image = classLoader.getResource("cover/$nameImage")
                        println(" $nameRom - $nameImage - ${image.toURI()}")
                        val panel = JPanel().apply {
                            size = Dimension(bufferedImage.width, bufferedImage.height)
                            isOpaque = false
                            setBackground(Color(0, 0, 0))

                            layout = boxLayout(this).apply {
                                setBackground(Color(0, 0, 0))
                                isOpaque = false
                            }

                            add(jLabelFactory(" "))
                            add(jLabelFactory(ImageIcon(when (image) {
                                null -> imageDefault
                                else -> ImageIcon().scale(bufferedImage, image.file.toString())
                            }))
                            )
                            add(jLabelFactory(" "))
                            add(jLabelFactory(nameRom))
                        }

                        val bufferedPanel = convert.invoke(panel)
                        val imageIcon = ImageIcon(bufferedPanel)
                        // val weak = WeakReference(imageIcon)
                        mapOf(Pair("imageIcon", imageIcon), Pair("row", row), Pair("column", col))
                    })
                }
            }
        } catch(e: Exception) {
            println(e.message)
            e.stackTrace
        } finally {
 //           println("****** FIN LOAD MODEL *******")
            state = CacheState.FINISH
            return CompletableFuture.completedFuture(queue)
        }

   /*     try {
            Arrays.stream(listGames.rowNames)
                   .filter {  it -> it != null }
                   .map { it -> it[1].toString() }
                   .forEach { it ->
                       val nameRom = it.toLowerCase().split(".")[0].toString().plus(".png")
                       val image = classLoader.getResource("cover/$nameRom")
                       val panel = JPanel().apply {
                           size = Dimension(bufferedImage.width, bufferedImage.height)
                           isOpaque = false
                           setBackground(Color(0, 0, 0))

                           layout = boxLayout(this).apply {
                               setBackground(Color(0, 0, 0))
                               isOpaque = false
                           }

                           add(jLabelFactory(" "))
                           add(jLabelFactory(ImageIcon(when (image) {
                               null -> imageDefault
                               else -> ImageIcon().scale(bufferedImage, image.file.toString())
                                }))
                           )
                           add(jLabelFactory(" "))
                           add(jLabelFactory(it))
                       }

                       val bufferedPanel = convert.invoke(panel)
                       map["$it"] = mapOf(Pair("bufferedPanel",bufferedPanel),Pair("row",),Pair("column",))
                       Thread.sleep(10)
                   }
        } catch(e: Exception) {
            println(e.message)
            e.stackTrace
        } finally {
            println("****** FIN LOAD MODEL *******")
            return CompletableFuture.completedFuture(map)
        }*/
    }


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

enum class CacheState {
    STOP, LOADING, FINISH
}