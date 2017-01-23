
import components.progressBar.StatusBar
import components.table.listener.BaseInpuTableList
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.JComponent
import javax.swing.JTabbedPane
import javax.swing.JTable


 class TableRowAdapter internal constructor(val table: JTable, val statusBar: StatusBar, val tabbedPane: JTabbedPane) : MouseAdapter() {


     companion object {
         fun create(table: JTable, statusBar : JComponent,tabbedPane: JTabbedPane) = TableRowAdapter(table, statusBar as StatusBar,tabbedPane)
     }

     init {

     }

    override fun mouseClicked(e: MouseEvent) {

        BaseInpuTableList.executeX(table,statusBar,tabbedPane,0)
    }



}
