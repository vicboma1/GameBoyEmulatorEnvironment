package app.components.menuBar.view

import app.components.panel.ContentPaneParentImpl
import assets.frame.Frame
import assets.menuBar.child.dialog.MenuBarViewGrid
import assets.menuBar.child.dialog.MenuBarViewList
import assets.progressBar.JMenuBarImpl
import assets.progressBar.Menu
import assets.progressBar.MenuImpl
import assets.progressBar.StatusBar

/**
 * Created by vicboma on 02/01/17.
 */
fun JMenuBarImpl.Companion.MenuView(frame : Frame, statusBar : StatusBar, contentPaneParentImpl : ContentPaneParentImpl): Menu {

    return MenuImpl.create("View")
            .apply{
                addMenuItem(JMenuBarImpl.MenuBarViewGrid(frame,statusBar,contentPaneParentImpl))
                addMenuItem(JMenuBarImpl.MenuBarViewList(frame,statusBar,contentPaneParentImpl))
            }
}
