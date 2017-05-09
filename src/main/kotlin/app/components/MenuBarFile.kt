package app.components

import assets.dialog.message.EnumDialog
import assets.dialog.message.MessageImpl
import assets.frame.Frame
import assets.progressBar.*
import java.awt.event.KeyEvent

/**
 * Created by vicboma on 12/12/16.
 */
fun JMenuBarImpl.Companion.MenuFile(frame: Frame,statusBar : StatusBar): Menu {

    val radioButtonList = listOf(
            RadioItemImpl.create("Open",statusBar, true),
            RadioItemImpl.create("Open Url",statusBar, false),
            RadioItemImpl.create("Open Recent",statusBar, false)
    )

    val checkButtonList = listOf(
            CheckItemImpl.create("Import Setting",statusBar, true),
            CheckItemImpl.create("Export Setting",statusBar, false),
            CheckItemImpl.create("Setting Repository",statusBar, true)
    )

    val radioGroup = GroupImpl.create(radioButtonList)

    return MenuImpl
            .create("File")
            .apply {
                addMenuItem(radioButtonList)
                putSeparator()
                addMenuItem(checkButtonList)
                putSeparator()
                addMenuItem(MenuItemImpl.create("Exit", KeyEvent.VK_E, statusBar, {
                    MessageImpl.create(frame, Pair("Exit Message", "GoodBye"), EnumDialog.PLAIN_MESSAGE).showDialog()
                    System.exit(0)
                }))
            }

}