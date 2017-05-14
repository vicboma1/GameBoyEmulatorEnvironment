package app.components.menuBar.view

import app.components.menuBar.options.subItem.options.MenuBarOptions
import app.configuration.properties.Properties
import assets.frame.Frame
import assets.progressBar.StatusBar
import assets.progressBar.addMenuItem
import javax.swing.JMenu

/**
 * Created by vicboma on 02/01/17.
 */
fun JMenu.MenuOptions(frame : Frame, statusBar : StatusBar, properties: Properties) =
        JMenu("Options").apply {
            addMenuItem(MenuBarOptions(frame,statusBar,properties))
        }
