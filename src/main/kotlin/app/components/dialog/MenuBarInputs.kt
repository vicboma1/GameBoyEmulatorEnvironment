package assets.menuBarExt.child.dialog

import assets.dialog.confirm.InputImpl
import assets.dialog.message.EnumDialog
import assets.dialog.message.MessageImpl
import assets.frame.Frame
import assets.progressBar.MenuItemImpl
import assets.progressBar.StatusBar
import assets.progressBar.addMenuItem
import assets.progressBar.visibility
import java.awt.event.KeyEvent
import java.util.*
import javax.swing.JMenu

/**
 * Created by vicboma on 12/12/16.
 */
fun JMenu.MenuInput(frame: Frame, statusBar: StatusBar): JMenu {
    val listButtons = listOf(

            MenuItemImpl.create("Basic", KeyEvent.VK_P,statusBar, {
                frame.jMenuBar.visibility(false)
                InputImpl.create(frame, Pair("Basic", "Basic Input"), EnumDialog.PLAIN_MESSAGE).showDialog({
                    MessageImpl.create(frame, Pair("Basic Message", "$it"), EnumDialog.PLAIN_MESSAGE).showDialog()
                })
                frame.jMenuBar.visibility(true)
            }),

            MenuItemImpl.create("Information", KeyEvent.VK_O,statusBar, {
                frame.jMenuBar.visibility(false)
                InputImpl.create(frame, Pair("Information", "Information Input"), EnumDialog.INFORMATION_MESSAGE).showDialog({
                    MessageImpl.create(frame, Pair("Information Message", "$it"), EnumDialog.INFORMATION_MESSAGE).showDialog()
                })
                frame.jMenuBar.visibility(true)


            }),

            MenuItemImpl.create("Warning", KeyEvent.VK_U,statusBar, {
                frame.jMenuBar.visibility(false)
                InputImpl.create(frame, Pair("Warning", "Warning Input"), EnumDialog.WARNING_MESSAGE).showDialog({
                    MessageImpl.create(frame, Pair("Warning Message", "$it"), EnumDialog.WARNING_MESSAGE).showDialog()
                })
                frame.jMenuBar.visibility(true)
            }),

            MenuItemImpl.create("Error", KeyEvent.VK_I,statusBar, {
                frame.jMenuBar.visibility(false)
                InputImpl.create(frame, Pair("Error", "Error Input"), EnumDialog.ERROR_MESSAGE).showDialog({
                    MessageImpl.create(frame, Pair("Error Message", "$it"), EnumDialog.ERROR_MESSAGE).showDialog()
                })
                frame.jMenuBar.visibility(true)
            }),

            MenuItemImpl.create("Custom", KeyEvent.VK_Y, statusBar, {
                frame.jMenuBar.visibility(false)
                val theDays = Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday") as MutableList<Object>
                InputImpl.create(frame, Pair("Today is: ", "Custom Input"), EnumDialog.INFORMATION_MESSAGE, null, ArrayList(theDays), theDays[0]).showDialog({
                    MessageImpl.create(frame, Pair("Custom Message", "$it"), EnumDialog.INFORMATION_MESSAGE).showDialog()
                    frame.jMenuBar.visibility(true)
                })
            }
            )
    )


    return JMenu("Input")
            .apply {
                addMenuItem(listButtons)
            }

}



