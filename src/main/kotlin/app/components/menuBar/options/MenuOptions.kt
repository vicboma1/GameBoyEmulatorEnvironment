package app.components.menuBar.view

import app.components.menuBar.options.subItem.options.MenuBarOptions
import assets.frame.Frame
import assets.progressBar.Menu
import assets.progressBar.MenuBarImpl
import assets.progressBar.MenuImpl

/**
 * Created by vicboma on 02/01/17.
 */
fun MenuBarImpl.Companion.MenuOptions(frame : Frame, map: MutableMap<String,Any?>): Menu {

    return MenuImpl.create("Options")
            .apply{
                addMenuItem(MenuBarImpl.MenuBarOptions(frame,map))
            }
}
