package app.components.menuBar.view

import app.components.panel.ContentPaneParentImpl
import assets.frame.Frame
import assets.menuBar.child.dialog.*
import assets.progressBar.Menu
import assets.progressBar.JMenuBarImpl
import assets.progressBar.MenuImpl

/**
 * Created by vicboma on 02/01/17.
 */
fun JMenuBarImpl.Companion.MenuView(frame : Frame, contentPaneParentImpl : ContentPaneParentImpl): Menu {

    return MenuImpl.create("View")
            .apply{
                addMenuItem(JMenuBarImpl.MenuBarViewGrid(frame,contentPaneParentImpl))
                addMenuItem(JMenuBarImpl.MenuBarViewList(frame,contentPaneParentImpl))
            }
}
