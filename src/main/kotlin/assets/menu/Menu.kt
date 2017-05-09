package assets.progressBar

import java.awt.Component
import javax.swing.JComponent

/**
 * Created by vicboma on 15/12/16.
 */
interface Menu : MenuItem {
    fun addMenuItem(name: Component?)
    fun addMenuItem(name: JComponent?)
    fun addMenuItem(name: MenuItem?)
    fun addMenuItem(list: List<MenuItem?>)
    fun putSeparator()
}