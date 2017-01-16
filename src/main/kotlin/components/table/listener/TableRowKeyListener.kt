package components.table.listener

import components.progressBar.StatusBar
import utils.ThreadMain
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import javax.swing.JComponent
import javax.swing.JTabbedPane
import javax.swing.JTable

/**
 * Created by vicboma on 07/01/17.
 */
class TableRowKeyListener internal constructor(private val table: JTable, private val statusBar: StatusBar, private val tab:JTabbedPane) : KeyListener {

    companion object {
        fun create(table: JTable, statusBar : JComponent, tab :JTabbedPane) : KeyListener {
            return TableRowKeyListener(table, statusBar as StatusBar, tab)
        }
    }


    init {

    }

    override fun keyTyped(e: KeyEvent?) {
        //throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun keyPressed(e: KeyEvent?) {
        ThreadMain.asyncUI {
            when(e?.keyCode) {

                KeyEvent.VK_UP -> BaseInpuTableList.executeX(table, statusBar, tab, -1)
                KeyEvent.VK_DOWN -> BaseInpuTableList.executeX(table, statusBar, tab, 1)
                KeyEvent.VK_ENTER -> BaseInpuTableList.executeX(table, statusBar, tab, 1)
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


}