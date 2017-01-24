package app.components

import assets.dialog.message.EnumDialog
import assets.dialog.message.MessageImpl
import assets.frame.Frame
import assets.progressBar.*
import java.awt.event.KeyEvent

/**
 * Created by vicboma on 12/12/16.
 */
fun MenuBarImpl.Companion.MenuFile(frame: Frame): Menu {

    val radioButtonList = listOf(
            RadioItemImpl.create("Open", true),
            RadioItemImpl.create("Open Url", false),
            RadioItemImpl.create("Open Recent", false)
    )

    val checkButtonList = listOf(
            CheckItemImpl.create("Import Setting", true),
            CheckItemImpl.create("Export Setting", false),
            CheckItemImpl.create("Setting Repository", true)
    )

    val radioGroup = GroupImpl.create(radioButtonList)

    return MenuImpl
            .create("File")
            .apply {
                addMenuItem(radioButtonList)
                putSeparator()
                addMenuItem(checkButtonList)
                putSeparator()
                addMenuItem(MenuItemImpl.create("Exit", KeyEvent.VK_E, {
                    MessageImpl.create(frame, Pair("Exit Message", "GoodBye"), EnumDialog.PLAIN_MESSAGE).showDialog()
                    System.exit(0)
                }))
            }

}