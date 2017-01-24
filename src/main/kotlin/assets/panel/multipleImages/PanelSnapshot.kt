package assets.panel.multipleImages

import assets.panel.multipleImages.base.PanelMultipleImages
import src.configuration.Display
import java.awt.Graphics
import java.awt.Graphics2D

/**
 * Created by vicboma on 08/01/17.
 */
class PanelSnapshot internal constructor(_back: String, val _front: String) : PanelMultipleImages(_back,_front) {

    companion object {
        fun create(_back: String,_front: String) =  PanelSnapshot(_back,_front)
    }

    override fun paintComponent(g: Graphics) {
        super.paintComponents(g)
        var g2 = g as Graphics2D

        if(list[0] != null)
            g2.drawImage(list[0], (this.width * 0.07).toInt() , (this.height * 0.07).toInt(), null)

        if(list[1] != null)
            g2.drawImage(list[1],(this.width * 0.132).toInt() , (this.height * 0.182).toInt(),((Display.WIDHT / 3) * 1.055).toInt(), ((Display.HEIGTH / 2) * 1.058).toInt(), null)
    }
}