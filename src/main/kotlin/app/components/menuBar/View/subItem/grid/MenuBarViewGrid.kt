package assets.menuBar.child.dialog

import app.components.panel.ContentPaneParentImpl
import assets.frame.Frame
import assets.progressBar.*
import utils.ThreadMain
import java.awt.event.KeyEvent


/**
 * Created by vicboma on 12/12/16.
 */
fun MenuBarImpl.Companion.MenuBarViewGrid(frame: Frame,contentPaneParentImpl : ContentPaneParentImpl): Menu {

    fun visiblePanel(list: Boolean, grid: Boolean) {
        contentPaneParentImpl.visiblePanelListView(list)
        contentPaneParentImpl.visiblePanelGridView(grid)
        frame.jMenuBar.visibility(true)
    }

    fun actionVisibility(frame: Frame) {
        frame.jMenuBar.visibility(false)
        ThreadMain.asyncUI {
            visiblePanel(false, true)
        }
    }

    val radioButtonList = listOf(
            RadioItemImpl.create("1x cover", false, KeyEvent.VK_1, {
                actionVisibility(frame)
            }),
            RadioItemImpl.create("2x cover", false, KeyEvent.VK_2, {
                actionVisibility(frame)
            }),
            RadioItemImpl.create("3x cover", false, KeyEvent.VK_3, {
                actionVisibility(frame)
            }),
            RadioItemImpl.create("4x cover", false, KeyEvent.VK_4, {
                actionVisibility(frame)
            })
    )

    val radioGroup = GroupImpl.create(radioButtonList)

    return MenuImpl.create("Grid")
            .apply {
                addMenuItem(radioButtonList)
            }

}

