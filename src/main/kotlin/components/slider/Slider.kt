package components.progressBar

import components.Renderable
import javax.swing.JSlider

/**
 * Created by vicboma on 05/12/16.
 */
interface Slider : Renderable {
    companion object{
        val POSITION = JSlider.HORIZONTAL
        val MIN = 0
        val MAX = 100
        val VALUE = 100
    }
}