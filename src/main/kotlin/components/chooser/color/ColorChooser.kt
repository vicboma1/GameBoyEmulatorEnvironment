package components.progressBar

import java.awt.Color

/**
 * Created by vicboma on 15/12/16.
 */
interface ColorChooser{
    fun showDialog(callback: (color: Color) -> Unit)
}