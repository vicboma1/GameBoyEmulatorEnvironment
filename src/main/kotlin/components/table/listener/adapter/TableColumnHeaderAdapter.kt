
import components.table.comparator.TableHeaderComparator
import utils.ThreadMain
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.JTable
import javax.swing.event.TableModelEvent
import javax.swing.table.TableColumnModel

class TableColumnHeaderAdapter internal constructor(val table: JTable, var data: Array<Array<Any>>, val cmp : TableHeaderComparator) : MouseAdapter() {

     var sortCol = 0

     companion object {
         fun create( table: JTable,  data: Array<Array<Any>>,  cmp : TableHeaderComparator)  = TableColumnHeaderAdapter( table,  data,  cmp )
     }

     init {
         selectedColumnHeaderSort(1)
     }

    private fun selectedColumnHeaderSort (selectedColumn:Int) {
        ThreadMain.asyncUI {
            val colModel = table.columnModel
            cmp.columnIndex = selectedColumn
            selectedColumnHeaderSort(colModel)
        }
    }

    override fun mouseClicked(e: MouseEvent) {
        ThreadMain.asyncUI {
            val colModel = table.columnModel
            cmp.columnIndex = colModel.getColumnIndexAtX(e.getX())
            selectedColumnHeaderSort(colModel)
        }
    }

    private fun selectedColumnHeaderSort(colModel: TableColumnModel) {
        val modelColumn = colModel.getColumn(cmp.columnIndex)
        val modelIndex = modelColumn.modelIndex

        if (modelIndex < 0)
            return
        if (sortCol === modelIndex)
            cmp.isSortAsc = !cmp.isSortAsc
        else
            sortCol = modelIndex

        for (i in 0..colModel.columnCount - 1) {
            val column = colModel.getColumn(i)
            column.headerValue = table.model.getColumnName(column.modelIndex)
        }

        table.tableHeader.repaint()

        data.sortWith(cmp)

        table.tableChanged(TableModelEvent(table.model))
        table.repaint()
    }
}