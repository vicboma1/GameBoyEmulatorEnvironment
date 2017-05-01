
import assets.table.comparator.TableHeaderComparator
import assets.table.model.TableModelImpl
import utils.ThreadMain
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.io.File
import javax.swing.JTable
import javax.swing.event.TableModelEvent
import javax.swing.table.TableColumnModel

class TableColumnHeaderAdapter internal constructor(private val classLoader: ClassLoader, val table: JTable, val cmp : TableHeaderComparator) : MouseAdapter() {

     var sortCol = 0

     companion object {
         fun create(classLoader: ClassLoader,table: JTable,  cmp : TableHeaderComparator)  = TableColumnHeaderAdapter( classLoader, table,  cmp )
     }

     init {
    //     selectedColumnHeaderSort(1)
     }

    private fun selectedColumnHeaderSort (selectedColumn:Int) {
        ThreadMain.asyncUI {
            val colModel = table.columnModel
            cmp.columnIndex = selectedColumn
            selectedColumnHeaderSort(colModel)
        }
    }

    override fun mouseClicked(e: MouseEvent) {
            val colModel = table.columnModel
            cmp.columnIndex = colModel.getColumnIndexAtX(e.getX())
            selectedColumnHeaderSort(colModel)
    }

    private fun selectedColumnHeaderSort(colModel: TableColumnModel) {
            val modelColumn = colModel.getColumn(cmp.columnIndex)
            val modelIndex = modelColumn.modelIndex

            if (modelIndex >= 0) {
                if (sortCol === modelIndex)
                    cmp.isSortAsc = !cmp.isSortAsc
                else
                    sortCol = modelIndex

                for (i in 0..colModel.columnCount - 1) {
                    val column = colModel.getColumn(i)
                    column.headerValue = table.model.getColumnName(column.modelIndex)
                }

                table.tableHeader.repaint()

                (table.model as TableModelImpl).sortWith(cmp)

                table.tableChanged(TableModelEvent(table.model))
                repaint()
            }
        }

    fun repaint() {
        for(it in 0..table.model.rowCount-1) {
            val nameGame = table.model.getValueAt(it, 1).toString().toLowerCase().trim()
            val file = classLoader.getResource("rom/$nameGame")
            when (file) {
                null -> {
                    table.model.setValueAt(false, it, 0)
                }
                else -> {
                    val f = File(file.toURI())
                    if (f.exists() && !f.isDirectory())
                        table.model.setValueAt(true, it, 0)
                }
            }
        }
        table.repaint()
    }
}