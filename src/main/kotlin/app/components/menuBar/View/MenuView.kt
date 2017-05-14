package app.components.menuBar.view

import app.components.panel.ContentPaneParentImpl
import assets.frame.Frame
import assets.menuBarExt.child.dialog.MenuBarViewGrid
import assets.menuBarExt.child.dialog.MenuBarViewList
import assets.progressBar.StatusBar
import assets.progressBar.addMenuItem
import javax.swing.JMenu

/**
 * Created by vicboma on 02/01/17.
 */
fun JMenu.MenuView(frame : Frame, statusBar : StatusBar, contentPaneParentImpl : ContentPaneParentImpl) =
     JMenu("View")
            .apply{
                addMenuItem(MenuBarViewGrid(frame,statusBar,contentPaneParentImpl))
                addMenuItem(MenuBarViewList(frame,statusBar,contentPaneParentImpl))
            }
