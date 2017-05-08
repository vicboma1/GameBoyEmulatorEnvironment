package app.components

import assets.frame.Frame
import assets.menuBar.child.dialog.MenuInput
import assets.menuBar.child.dialog.MenuMessage
import assets.progressBar.Menu
import assets.progressBar.JMenuBarImpl
import assets.progressBar.MenuImpl
import menuBar.child.dialog.MenuConfirm

/**
 * Created by vicboma on 12/12/16.
 */
fun JMenuBarImpl.Companion.MenuDialog(frame: Frame): Menu {
    return MenuImpl.create("Dialog")
            .apply {
                addMenuItem(JMenuBarImpl.MenuMessage(frame))
                addMenuItem(JMenuBarImpl.MenuConfirm(frame))
                addMenuItem(JMenuBarImpl.MenuInput(frame))
            }

}