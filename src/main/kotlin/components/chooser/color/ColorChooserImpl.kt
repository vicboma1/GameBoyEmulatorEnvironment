package components.progressBar

import java.awt.Color
import javax.swing.JColorChooser



/**
 * Created by vicboma on 05/12/16.
 */
class ColorChooserImpl internal constructor(private val title: String, private val _color : Color) : JColorChooser(), ColorChooser {

    companion object {
        fun create(title : String, _color : Color) = ColorChooserImpl(title, _color)
    }

    init {

    }

    override fun showDialog(callback: (color: Color) -> Unit) {
        val color = JColorChooser.showDialog(null, title, color)
        if(null != color)
            callback.invoke(   color)
    }

}


