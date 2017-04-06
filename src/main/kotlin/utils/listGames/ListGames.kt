package main.kotlin.utils.listGames

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import main.kotlin.utils.image.BufferedImageMemoryFromComponent
import main.kotlin.utils.image.scale
import java.awt.Color
import java.awt.Component
import java.awt.Dimension
import java.awt.image.BufferedImage
import java.io.File
import java.io.FileReader
import java.util.*
import javax.swing.BoxLayout
import javax.swing.ImageIcon
import javax.swing.JLabel
import javax.swing.JPanel

/**
 * Created by vbolinch on 03/01/2017.
 */
class ListGames internal constructor(private val classLoader: ClassLoader, private val path: String) {

    private val w = 240//340 2
    private val h = 200//300 2

    val convertPanelToImage = BufferedImageMemoryFromComponent()
    var imageDefault = ImageIcon().scale(w, h,classLoader.getResource("cover/_gbNotFound.png").file.toString())
    var columnNames : Array<Any>? = null
    var rowNames : Array<Array<Any>>? = null

    companion object {
        fun create(classLoader: ClassLoader,path: String) = ListGames(classLoader,path)
    }

    init {
        val gson = Gson()
        val type = object : TypeToken<Array<Array<Any>>>() {}.type
        val resource = classLoader.getResource(path)
        val file = File(resource.toURI())
        val list = gson.fromJson<Array<Array<Any>>>(FileReader(file), type)

        columnNames = list[0]
        rowNames = Arrays.copyOfRange(list ,1,list.size)
    }

    fun getColumnGrid(res : Array<Array<Any>>) : Array<Array<Any>>? {

        try {
            val rows = res.size
            var cols = res[0].size
            for (row in 0..rows - 1) {
                for (col in 0..cols - 1) {
                    val nameRom = rowNames!![(row * cols) + col][1].toString()
                    val nameGame = nameRom.toLowerCase().split(".")[0].toString().plus(".png")
                    println(nameGame)
                    val image = classLoader.getResource("cover/$nameGame")
                    val bufferedImage: BufferedImage?
                    bufferedImage = when (image) {
                        null -> imageDefault
                        else -> ImageIcon().scale(w, h, image.file.toString())
                    }
                    val panel = JPanel().apply {
                        layout = BoxLayout(this, BoxLayout.Y_AXIS)
                        add(JLabel(ImageIcon(bufferedImage)).apply { setAlignmentX(Component.CENTER_ALIGNMENT) })
                        add(JLabel(" ").apply { setAlignmentX(Component.CENTER_ALIGNMENT) })
                        add(JLabel(nameRom).apply { setAlignmentX(Component.CENTER_ALIGNMENT) })
                        add(JLabel(" ").apply {
                            setAlignmentX(Component.CENTER_ALIGNMENT)
                        })
                        size = Dimension(w, h)
                        isOpaque = false
                        setBackground(Color(0, 0, 0, 0))
                    }

                    val bufferedPanel = convertPanelToImage.invoke(panel)
                    res!![row][col] = ImageIcon(bufferedPanel)
                }
            }

        }
        catch(e: Exception){
            println(e.message)
        }
        finally {
            return res
        }
    }
}