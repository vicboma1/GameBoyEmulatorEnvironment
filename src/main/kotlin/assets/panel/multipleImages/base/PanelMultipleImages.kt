package assets.panel.multipleImages.base

import main.kotlin.utils.image.scale
import src.configuration.Display
import java.awt.BorderLayout
import java.awt.image.BufferedImage
import java.util.concurrent.CopyOnWriteArrayList
import javax.swing.ImageIcon
import javax.swing.JPanel

/**
 * Created by vicboma on 08/01/17.
 */
open class PanelMultipleImages internal constructor(private val classLoader: ClassLoader, private val back: String, private val front:String) : JPanel() {

    protected var list: CopyOnWriteArrayList<BufferedImage?> = CopyOnWriteArrayList()

    init {
        layout = BorderLayout()
        val resourceBack = classLoader.getResource(back)
        if(resourceBack != null)
            list.add(0,ImageIcon().scale(((Display.WIDHT / 3) * 1.25).toInt(), ((Display.HEIGTH / 2) * 1.45).toInt(), resourceBack.file.toString()))

        val resourceFront = classLoader.getResource(front)
        if(resourceFront != null)
            list.add(1,ImageIcon().scale(((Display.WIDHT / 3) * 1.25).toInt(), ((Display.HEIGTH / 2) * 1.45).toInt(), resourceFront.file.toString()))
    }

    fun setBack(back:String) {
        val resourceSetBack = classLoader.getResource(back)
        if(resourceSetBack!=null)
            list.set(0,ImageIcon().scale(((Display.WIDHT / 3) * 1.25).toInt(), ((Display.HEIGTH / 2) * 1.45).toInt(), resourceSetBack.file.toString()))

    }

    fun setFront(front:String) {
        var file : String = ""
        val resourceSetFront = classLoader.getResource(front)

        if(null != resourceSetFront)
         file = resourceSetFront.file.toString()

        val buffered = ImageIcon().scale(((Display.WIDHT / 3) * 1.25).toInt(), ((Display.HEIGTH / 2) * 1.45).toInt(),file)
        list.set(1,buffered)
    }
}