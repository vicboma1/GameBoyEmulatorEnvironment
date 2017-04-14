package assets.panel.multipleImages.base

import main.kotlin.utils.image.createBufferedImage
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
        val bufferedImageBack = ImageIcon().createBufferedImage(((Display.WIDHT / 3) * 1.25).toInt(), ((Display.HEIGTH / 2) * 1.45).toInt(), BufferedImage.TYPE_INT_ARGB)

        if(resourceBack != null)
            list.add(0,ImageIcon().scale(bufferedImageBack, resourceBack.file.toString()))

        val resourceFront = classLoader.getResource(front)
        val bufferedImageFront = ImageIcon().createBufferedImage(((Display.WIDHT / 3) * 1.25).toInt(), ((Display.HEIGTH / 2) * 1.45).toInt(), BufferedImage.TYPE_INT_ARGB)

        if(resourceFront != null)
            list.add(1,ImageIcon().scale(bufferedImageFront, resourceFront.file.toString()))
    }

    fun setBack(back:String) {
        val resourceSetBack = classLoader.getResource(back)
        if(resourceSetBack!=null) {
            val bufferedImageBack = ImageIcon().createBufferedImage(((Display.WIDHT / 3) * 1.25).toInt(), ((Display.HEIGTH / 2) * 1.45).toInt(), BufferedImage.TYPE_INT_ARGB)
            list.set(0, ImageIcon().scale(bufferedImageBack, resourceSetBack.file.toString()))
        }
    }

    fun setFront(front:String) {
        var file : String = ""
        val resourceSetFront = classLoader.getResource(front)

        if(null != resourceSetFront)
         file = resourceSetFront.file.toString()

        val bufferedImageFront = ImageIcon().createBufferedImage(((Display.WIDHT / 3) * 1.25).toInt(), ((Display.HEIGTH / 2) * 1.45).toInt(), BufferedImage.TYPE_INT_ARGB)
        val buffered = ImageIcon().scale(bufferedImageFront,file)
        list.set(1,buffered)
    }
}