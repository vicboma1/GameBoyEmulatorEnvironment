package app.components

import assets.frame.Frame
import assets.menuBar.child.dialog.MenuInput
import assets.menuBar.child.dialog.MenuMessage
import assets.progressBar.Menu
import assets.progressBar.MenuBarImpl
import assets.progressBar.MenuImpl
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