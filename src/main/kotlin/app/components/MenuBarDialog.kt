package app.components

import assets.frame.Frame
import assets.menuBarExt.child.dialog.MenuInput
import assets.menuBarExt.child.dialog.MenuMessage
import assets.progressBar.StatusBar
import assets.progressBar.addMenuItem
import menuBar.child.dialog.MenuConfirm
import javax.swing.JMenu

/**
 * Created by vicboma on 12/12/16.
 */
fun JMenu.MenuDialog(frame: Frame, status:StatusBar) : JMenu {
    return JMenu("Dialog")
            .apply {
                addMenuItem(MenuMessage(frame,status))
                addMenuItem(MenuConfirm(frame,status))
                addMenuItem(MenuInput(frame,status))
            }

}