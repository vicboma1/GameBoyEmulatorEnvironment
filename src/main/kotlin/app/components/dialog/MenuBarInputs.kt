package components.menuBar.child.dialog

import components.dialog.confirm.InputImpl
import components.dialog.message.EnumDialog
import components.dialog.message.MessageImpl
import components.frame.Frame
import components.progressBar.*
import utils.thread.CustomExecutor
import java.awt.event.KeyEvent
import java.util.*

/**
 * Created by vicboma on 12/12/16.
 */
fun MenuBarImpl.Companion.MenuInput(frame: Frame): Menu {
    val listButtons = listOf(

            MenuItemImpl.create("Basic", KeyEvent.VK_P, {
                frame.jMenuBar.visibility(false)
                CustomExecutor.instance.addPriority {
                    InputImpl.create(frame, Pair("Basic", "Basic Input"), EnumDialog.PLAIN_MESSAGE).showDialog({
                        MessageImpl.create(frame, Pair("Basic Message", "$it"), EnumDialog.PLAIN_MESSAGE).showDialog()
                    })
                    frame.jMenuBar.visibility(true)
                }
            }),

            MenuItemImpl.create("Information", KeyEvent.VK_O, {
                frame.jMenuBar.visibility(false)
                CustomExecutor.instance.addPriority {
                    InputImpl.create(frame, Pair("Information", "Information Input"), EnumDialog.INFORMATION_MESSAGE).showDialog({
                        MessageImpl.create(frame, Pair("Information Message", "$it"), EnumDialog.INFORMATION_MESSAGE).showDialog()
                    })
                    frame.jMenuBar.visibility(true)
                }
            }),

            MenuItemImpl.create("Warning", KeyEvent.VK_U, {
                frame.jMenuBar.visibility(false)
                CustomExecutor.instance.addPriority {
                    InputImpl.create(frame, Pair("Warning", "Warning Input"), EnumDialog.WARNING_MESSAGE).showDialog({
                        MessageImpl.create(frame, Pair("Warning Message", "$it"), EnumDialog.WARNING_MESSAGE).showDialog()
                    })
                    frame.jMenuBar.visibility(true)
                }
            }),

            MenuItemImpl.create("Error", KeyEvent.VK_I ,{
                frame.jMenuBar.visibility(false)
                CustomExecutor.instance.addPriority {
                    InputImpl.create(frame, Pair("Error", "Error Input"), EnumDialog.ERROR_MESSAGE).showDialog({
                        MessageImpl.create(frame, Pair("Error Message", "$it"), EnumDialog.ERROR_MESSAGE).showDialog()
                    })
                    frame.jMenuBar.visibility(true)
                }
            }),

            MenuItemImpl.create("Custom", KeyEvent.VK_Y , {
                frame.jMenuBar.visibility(false)
                CustomExecutor.instance.addPriority {
                    val theDays = Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday") as MutableList<Object>
                    InputImpl.create(frame, Pair("Today is: ", "Custom Input"), EnumDialog.INFORMATION_MESSAGE, null, ArrayList(theDays), theDays[0]).showDialog({
                        MessageImpl.create(frame, Pair("Custom Message", "$it"), EnumDialog.INFORMATION_MESSAGE).showDialog()
                    })
                    frame.jMenuBar.visibility(true)
                }
            })
    )

    return MenuImpl.create("Input")
            .apply {
                 addMenuItem(listButtons)
            }


}



