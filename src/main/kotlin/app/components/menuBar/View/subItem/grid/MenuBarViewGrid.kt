package assets.menuBar.child.dialog

import app.components.panel.ContentPaneParentImpl
import assets.frame.Frame
import assets.progressBar.*
import assets.table.GRID_COVER
import utils.ThreadMain
import java.awt.event.KeyEvent
import javax.swing.JSlider


/**
 * Created by vicboma on 12/12/16.
 */
fun MenuBarImpl.Companion.MenuBarViewGrid(frame: Frame, contentPaneParentImpl : ContentPaneParentImpl) : Menu {

    fun visiblePanel(list: Boolean, grid: Boolean,row : Int, cover: GRID_COVER) {
        contentPaneParentImpl
                .coverScreen(list,grid,row,cover)
                .thenRunAsync {
                    println("****** FIN COMPLETABLE FUTURES *******")
                    frame.jMenuBar.visibility(true)
                }

    }

    fun actionVisibility(frame: Frame, row : Int, cover: GRID_COVER) {
        frame.jMenuBar.visibility(false)
        ThreadMain.asyncUI {
            visiblePanel(false, true, row, cover)
        }
    }

    val radioButtonList = listOf(
            RadioItemImpl.create("1x cover", false, KeyEvent.VK_1, {
                actionVisibility(frame, 1, GRID_COVER.ONE)
            }),
            RadioItemImpl.create("2x cover", false, KeyEvent.VK_2, {
                actionVisibility(frame, 2, GRID_COVER.TWO)
            }),
            RadioItemImpl.create("3x cover", false, KeyEvent.VK_3, {
                actionVisibility(frame, 3, GRID_COVER.THREE)
            }),
            RadioItemImpl.create("4x cover", false, KeyEvent.VK_4, {
                actionVisibility(frame, 4, GRID_COVER.FOUR)
            })
    )

    val radioGroup = GroupImpl.create(radioButtonList)

    return MenuImpl.create("Grid")
            .apply {
                addMenuItem(radioButtonList)
                putSeparator()
                addMenuItem(JSlider(JSlider.HORIZONTAL,0,1000,500).apply{
                    setLabelTable(this@apply.createStandardLabels(500))
                    setMinorTickSpacing(100)
                    setMajorTickSpacing(200);
                    setPaintTicks(true);
                    setPaintLabels(true);
                })

            }


}

