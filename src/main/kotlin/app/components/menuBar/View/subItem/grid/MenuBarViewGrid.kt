package assets.menuBarExt.child.dialog

import app.components.panel.ContentPaneParentImpl
import assets.frame.Frame
import assets.progressBar.*
import assets.table.GRID_COVER
import java.awt.event.KeyEvent
import javax.swing.JMenu

/**
 * Created by vicboma on 12/12/16.
 */
fun JMenu.MenuBarViewGrid(frame: Frame, statusBar : StatusBar, contentPaneParentImpl : ContentPaneParentImpl) : JMenu {

    fun actionVisibility(frame: Frame, row : Int, cover: GRID_COVER) {
            frame.jMenuBar.visibility(false)
            contentPaneParentImpl
                    .coverScreen({
                        contentPaneParentImpl.visiblePanelListView(false)
                        contentPaneParentImpl.visiblePanelGridView(false)
                    },true,row,cover)
                    .thenRunAsync {
                        println("****** FIN COMPLETABLE FUTURES *******")
                        frame.jMenuBar.visibility(true)
                    }

    }

    val radioButtonList = listOf(
            RadioItemImpl.create("1x cover",statusBar, false, KeyEvent.VK_1,  {
                actionVisibility(frame, 1, GRID_COVER.ONE)
            }),
            RadioItemImpl.create("2x cover",statusBar, false, KeyEvent.VK_2, {
                actionVisibility(frame, 2, GRID_COVER.TWO)
            }),
            RadioItemImpl.create("3x cover",statusBar, false, KeyEvent.VK_3, {
                actionVisibility(frame, 3, GRID_COVER.THREE)
            }),
            RadioItemImpl.create("4x cover",statusBar, false, KeyEvent.VK_4, {
                actionVisibility(frame, 4, GRID_COVER.FOUR)
            }),
            RadioItemImpl.create("Rom Name", statusBar, false, KeyEvent.VK_5, {
                actionVisibility(frame, 13, GRID_COVER.FOUR)
            })

    )

    val radioGroup = GroupImpl.create(radioButtonList)

    return JMenu("Grid")
            .apply {
                addMenuItem(radioButtonList)
            }


}

