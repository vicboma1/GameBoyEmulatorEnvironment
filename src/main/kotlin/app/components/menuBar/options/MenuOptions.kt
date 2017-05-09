package app.components.menuBar.view

import app.components.menuBar.options.subItem.options.MenuBarOptions
import app.configuration.properties.Properties
import assets.frame.Frame
import assets.progressBar.JMenuBarImpl
import assets.progressBar.Menu
import assets.progressBar.MenuImpl
import assets.progressBar.StatusBar

/**
 * Created by vicboma on 02/01/17.
 */
fun JMenuBarImpl.Companion.MenuOptions(frame : Frame, statusBar : StatusBar, properties: Properties): Menu {

    return MenuImpl.create("Options")
            .apply{
                addMenuItem(JMenuBarImpl.MenuBarOptions(frame,statusBar,properties))
            }
}
