package app.components

import app.components.panel.ContentPaneParentImpl
import assets.frame.Frame
import assets.progressBar.*
import utils.ThreadMain
import java.awt.event.KeyEvent

/**
 * Created by vicboma on 02/01/17.
 */
fun MenuBarImpl.Companion.MenuView(frame : Frame,contentPaneParentImpl : ContentPaneParentImpl): Menu {

    fun visiblePanel(list : Boolean , grid :Boolean) {
        contentPaneParentImpl.visiblePanelListView(list)
        contentPaneParentImpl.visiblePanelGridView(grid)
        frame.jMenuBar.visibility(true)
    }

    val radioButtonList = listOf(
                    RadioItemImpl.create("Grid", false, KeyEvent.VK_G) {
                        frame.jMenuBar.visibility(false)
                        ThreadMain.asyncUI {
                            visiblePanel(false,true)
                        }
                    },
                    RadioItemImpl.create("List", true,  KeyEvent.VK_L, {
                        frame.jMenuBar.visibility(false)
                        ThreadMain.asyncUI {
                            visiblePanel(true,false)
                        }
                    })
            )

    val radioGroup = GroupImpl.create(radioButtonList)

    return MenuImpl.create("View")
            .apply{
                addMenuItem(radioButtonList)
            }
}