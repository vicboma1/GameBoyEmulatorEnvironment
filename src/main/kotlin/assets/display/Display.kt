package src.configuration

import src.GBEE
import java.awt.Component
import java.awt.GraphicsEnvironment
import java.awt.LayoutManager

/**
 * Created by vicboma on 02/12/16.
 */
interface Display  {

     companion object {
         private val localGraphics = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds()
         val KFRAME_JAVA = "GB Emulator Environment ${GBEE.version}"
         val WIDHT = localGraphics.width // 1280
         val HEIGTH = localGraphics.height // 773
         val VISIBLE = true
    }

    var title: String
    val widht: Int?
    val heigth: Int?
    val visible: Boolean?
    val layout: LayoutManager?
    val closeOp: Int?
    val location: Component?
}
