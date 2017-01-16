package components.menuBar.child.dialog

import components.dialog.message.EnumDialog
import components.dialog.message.MessageImpl
import components.frame.Frame
import components.progressBar.Menu
import components.progressBar.MenuBarImpl
import components.progressBar.MenuImpl
import components.progressBar.MenuItemImpl
import java.awt.event.KeyEvent
import java.net.URL
import java.util.*
import javax.swing.ImageIcon
import javax.swing.JOptionPane
import javax.swing.JSpinner
import javax.swing.SpinnerNumberModel


/**
 * Created by vicboma on 12/12/16.
 */
fun MenuBarImpl.Companion.MenuMessage(frame: Frame): Menu {
    return MenuImpl.create("Message")
            .apply {
                addMenuItem(Arrays.asList(

                    MenuItemImpl.create("Basic", KeyEvent.VK_L, {
                        MessageImpl.create(frame, Pair("Basic", "Basic Message"), EnumDialog.PLAIN_MESSAGE).showDialog()
                    }) ,

                    MenuItemImpl.create("Information", KeyEvent.VK_K, {
                        MessageImpl.create(frame, Pair("Information", "Information Message"), EnumDialog.INFORMATION_MESSAGE).showDialog()
                    }) ,

                    MenuItemImpl.create("Warning", KeyEvent.VK_J, {
                        MessageImpl.create(frame, Pair("Warning", "Warning Message"), EnumDialog.WARNING_MESSAGE).showDialog()
                    }) ,

                    MenuItemImpl.create("Error", KeyEvent.VK_H, {
                        MessageImpl.create(frame, Pair("Error", "Error Message"), EnumDialog.ERROR_MESSAGE).showDialog()
                    })
            ))
            putSeparator()
            addMenuItem(listOf(

                    MenuItemImpl.create("Custom", KeyEvent.VK_G, {
                        MessageImpl.create(frame, Pair("Custom", "Custom Message Download Async Image"), EnumDialog.INFORMATION_MESSAGE, ImageIcon(URL("https://kotlinlang.org/assets/images/open-graph/kotlin_250x250.png"))).showDialog()
                    }) ,
                    MenuItemImpl.create("Custom Spinner", KeyEvent.VK_F, {
                        val sModel = SpinnerNumberModel(0, 0, 30, 1)
                        val spinner = JSpinner(sModel)
                        val option = JOptionPane.showOptionDialog(null, spinner, "Enter valid number", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null)
                        MessageImpl.create(frame, Pair("Custom Message", "Option: $option - Value: ${spinner.value}"), EnumDialog.INFORMATION_MESSAGE).showDialog()
                    })
            ))
    }
}