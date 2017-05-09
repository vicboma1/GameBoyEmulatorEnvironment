package app.components

import assets.frame.Frame
import assets.menuBar.child.dialog.MenuInput
import assets.menuBar.child.dialog.MenuMessage
import assets.progressBar.Menu
import assets.progressBar.JMenuBarImpl
import assets.progressBar.MenuImpl
import assets.progressBar.StatusBar
import menuBar.child.dialog.MenuConfirm

/**
 * Created by vicboma on 12/12/16.
 */
fun JMenuBarImpl.Companion.MenuDialog(frame: Frame,status:StatusBar): Menu {
    return MenuImpl.create("Dialog")
            .apply {
                addMenuItem(JMenuBarImpl.MenuMessage(frame,status))
                addMenuItem(JMenuBarImpl.MenuConfirm(frame,status))
                addMenuItem(JMenuBarImpl.MenuInput(frame,status))
            }

}