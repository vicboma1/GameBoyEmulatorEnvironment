package assets.menuBarExt.child.dialog

import app.components.panel.ContentPaneParentImpl
import assets.button.addActionListenerAsync
import assets.frame.Frame
import assets.progressBar.StatusBar
import assets.progressBar.add
import assets.progressBar.addMenuItem
import assets.table.GRID_COVER
import javax.swing.ButtonGroup
import javax.swing.JMenu
import javax.swing.JRadioButtonMenuItem

/**
 * Created by vicboma on 12/12/16.
 */
fun JMenu.MenuBarViewGrid(frame: Frame, statusBar : StatusBar, contentPaneParentImpl : ContentPaneParentImpl) : JMenu {

    fun actionVisibility(frame: Frame, row: Int, cover: GRID_COVER) {
        frame.jMenuBar.isEnabled = false
        contentPaneParentImpl
                .coverScreen({
                    contentPaneParentImpl.visiblePanelListView(false)
                    contentPaneParentImpl.visiblePanelGridView(false)
                }, true, row, cover)
                .thenRunAsync {
                    println("****** FIN COMPLETABLE FUTURES *******")
                    frame.jMenuBar.isEnabled = true
                }

    }

    val radioButtonList = listOf(
            JRadioButtonMenuItem("1x cover", false).apply {
                addActionListenerAsync {
                    actionVisibility(frame, 1, GRID_COVER.ONE)
                }
            },
            JRadioButtonMenuItem("2x cover", false).apply {
                addActionListener {
                    actionVisibility(frame, 2, GRID_COVER.TWO)
                }
            },
            JRadioButtonMenuItem("3x cover", false).apply {
                addActionListener {
                    actionVisibility(frame, 3, GRID_COVER.THREE)
                }
            },
            JRadioButtonMenuItem("4x cover", false).apply {
                addActionListener {
                    actionVisibility(frame, 4, GRID_COVER.FOUR)
                }
            },
            JRadioButtonMenuItem("Rom Name", false).apply {
                addActionListener {
                    actionVisibility(frame, 13, GRID_COVER.FOUR)
                }
            }
    )

    val radioGroup = ButtonGroup().add(radioButtonList)

    return JMenu("Grid")
            .apply {
                addMenuItem(radioButtonList)
            }
}




