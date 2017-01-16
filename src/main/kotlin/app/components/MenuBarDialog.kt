package app.components

import components.frame.Frame
import components.menuBar.child.dialog.MenuInput
import components.menuBar.child.dialog.MenuMessage
import components.progressBar.Menu
import components.progressBar.MenuBarImpl
import components.progressBar.MenuImpl
import menuBar.child.dialog.MenuConfirm

/**
 * Created by vicboma on 12/12/16.
 */
fun MenuBarImpl.Companion.MenuDialog(frame: Frame): Menu {
    return MenuImpl.create("Dialog")
            .apply {
                addMenuItem(MenuBarImpl.MenuMessage(frame))
                addMenuItem(MenuBarImpl.MenuConfirm(frame))
                addMenuItem(MenuBarImpl.MenuInput(frame))
            }

}