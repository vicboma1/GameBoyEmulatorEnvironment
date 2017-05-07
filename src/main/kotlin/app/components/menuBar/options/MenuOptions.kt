package app.components.menuBar.view

import app.components.menuBar.options.subItem.options.MenuBarOptions
import app.configuration.properties.Properties
import assets.frame.Frame
import assets.progressBar.Menu
import assets.progressBar.MenuBarImpl
import assets.progressBar.MenuImpl
import src.configuration.properties.PropertiesImpl

/**
 * Created by vicboma on 02/01/17.
 */
fun MenuBarImpl.Companion.MenuOptions(frame : Frame, properties: Properties): Menu {

    return MenuImpl.create("Options")
            .apply{
                addMenuItem(MenuBarImpl.MenuBarOptions(frame,properties))
            }
}
