package assets.table.listener.rowKey

import assets.frame.Frame
import assets.progressBar.StatusBar
import assets.table.listener.BaseInpuTableList
import kotlinx.coroutines.experimental.launch
import utils.swing.Swing
import java.awt.event.ActionEvent
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import javax.swing.*

/**
 * Created by vicboma on 07/01/17.
 */
class TableRowKeyListener internal constructor(private val frame: Frame, private val table: JTable, private val statusBar: StatusBar, private val tab: JTabbedPane) : KeyListener {

    companion object {
        fun create(frame: Frame, table: JTable, statusBar : JComponent, tab : JTabbedPane) : KeyListener {
            return TableRowKeyListener(frame,table, statusBar as StatusBar, tab)
        }
    }


    init {
        keyEvent_VK_Enter()
    }

    override fun keyTyped(e: KeyEvent?) {
        //throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun keyPressed(e: KeyEvent?) {
        launch(Swing) {
            when(e?.keyCode) {

                KeyEvent.VK_UP -> BaseInpuTableList.executeX(table, statusBar, tab, 0)
                KeyEvent.VK_DOWN -> BaseInpuTableList.executeX(table, statusBar, tab, 0)
                KeyEvent.VK_ENTER -> BaseInpuTableList.executeEnter(frame,table)
                KeyEvent.VK_LEFT -> BaseInpuTableList.executeY(table, tab, -1)
                KeyEvent.VK_RIGHT -> BaseInpuTableList.executeY(table, tab, 1)
                else -> {

                }
            }
        }
    }

    override fun keyReleased(e: KeyEvent?) {
        //throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun keyEvent_VK_Enter() {
        table.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter")
        table.actionMap.put("Enter", object : AbstractAction() {
            override fun actionPerformed(ae: ActionEvent) {
            }
        })

    }

}