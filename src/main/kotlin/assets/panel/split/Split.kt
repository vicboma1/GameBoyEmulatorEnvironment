package assets.progressBar

import assets.Component
import assets.Renderable
import javax.swing.JProgressBar
import javax.swing.JSlider
import javax.swing.JSplitPane

/**
 * Created by vicboma on 05/12/16.
 */
interface Split  {
    companion object{
        val VERTICAL = JSplitPane.VERTICAL_SPLIT
        val HORIZONTAL = JSplitPane.HORIZONTAL_SPLIT
    }
}