package app.components.menuBar.view

import app.components.panel.ContentPaneParentImpl
import assets.frame.Frame
import assets.menuBar.child.dialog.*
import assets.progressBar.Menu
import assets.progressBar.MenuBarImpl
import assets.progressBar.MenuImpl

/**
 * Created by vicboma on 02/01/17.
 */
fun MenuBarImpl.Companion.MenuView(frame : Frame, contentPaneParentImpl : ContentPaneParentImpl): Menu {

    return MenuImpl.create("View")
            .apply{
                addMenuItem(MenuBarImpl.MenuBarViewGrid(frame,contentPaneParentImpl))
                addMenuItem(MenuBarImpl.MenuBarViewList(frame,contentPaneParentImpl))
            }
}
