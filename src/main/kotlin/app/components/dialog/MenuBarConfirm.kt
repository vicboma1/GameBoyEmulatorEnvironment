package menuBar.child.dialog

import assets.dialog.confirm.ConfirmImpl
import assets.dialog.message.EnumDialog
import assets.dialog.message.MessageImpl
import assets.frame.Frame
import assets.progressBar.Menu
import assets.progressBar.MenuBarImpl
import assets.progressBar.MenuImpl
import assets.progressBar.MenuItemImpl
import java.awt.event.KeyEvent
import javax.swing.JOptionPane



/**
 * Created by vicboma on 12/12/16.
 */
fun MenuBarImpl.Companion.MenuConfirm(frame: Frame): Menu {

    var listButtons = listOf(

            MenuItemImpl.create("No - Yes", KeyEvent.VK_1, {
                    ConfirmImpl(frame, Pair("Basic Confirm Dialog", "Dialog with 2 buttons\nNo - Yes"), JOptionPane.YES_NO_OPTION)
                            .showDialog({
                                MessageImpl.create(frame, Pair("Basic Message", "Pressed Yes button!!!"), EnumDialog.PLAIN_MESSAGE).showDialog()
                            }, {
                                MessageImpl.create(frame, Pair("Basic Message", "Pressed No button!!!"), EnumDialog.PLAIN_MESSAGE).showDialog()
                            })
            }),

            MenuItemImpl.create("Cancel - No", KeyEvent.VK_2, {
                    ConfirmImpl(frame, Pair("Basic Confirm Dialog", "Dialog with 2 buttons\nCancel - Ok"), JOptionPane.OK_CANCEL_OPTION)
                            .showDialog({
                                MessageImpl.create(frame, Pair("Basic Message", "Pressed Ok button!!!"), EnumDialog.PLAIN_MESSAGE).showDialog()
                            }, {

                            }, {
                                MessageImpl.create(frame, Pair("Basic Message", "Pressed Cancel button!!!"), EnumDialog.PLAIN_MESSAGE).showDialog()
                            })
            }),

            MenuItemImpl.create("Cancel - No - Yes", KeyEvent.VK_3, {
                    ConfirmImpl(frame, Pair("Basic Confirm Dialog", "Dialog with 3 buttons\nCancel - No - Yes"), JOptionPane.YES_NO_CANCEL_OPTION)
                            .showDialog({
                                MessageImpl.create(frame, Pair("Basic Message", "Pressed Yes button!!!"), EnumDialog.PLAIN_MESSAGE).showDialog()
                            }, {
                                MessageImpl.create(frame, Pair("Basic Message", "Pressed No button!!!"), EnumDialog.PLAIN_MESSAGE).showDialog()
                            }, {
                                MessageImpl.create(frame, Pair("Basic Message", "Pressed Cancel button!!!"), EnumDialog.PLAIN_MESSAGE).showDialog()
                            })
            }))

    return MenuImpl.create("Confirm")
            .apply {
                addMenuItem(listButtons)
                putSeparator()
                addMenuItem(
                        MenuItemImpl.create("No - Yes Custom Message", KeyEvent.VK_4, {
                            ConfirmImpl(frame, Pair("Custom Confirm Dialog Message", "<html>The magic <span style='color:green'>color</span> </html>"), JOptionPane.YES_NO_OPTION)
                                    .showDialog({
                                        MessageImpl.create(frame, Pair("Basic Message", "Pressed Yes button!!!"), EnumDialog.PLAIN_MESSAGE).showDialog()
                                    }, {
                                        MessageImpl.create(frame, Pair("Basic Message", "Pressed No button!!!"), EnumDialog.PLAIN_MESSAGE).showDialog()
                                    })
                        })
                )
            }

}

