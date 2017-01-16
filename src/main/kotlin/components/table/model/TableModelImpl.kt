package components.table.model

import utils.ThreadMain
import javax.swing.table.AbstractTableModel

/**
 * Created by vbolinch on 02/01/2017.
 */

class TableModelImpl internal constructor(val columnNames : Array<String>, val data: Array<Array<Any>>, val isEditable : Boolean = false ): AbstractTableModel() {

    companion object {

        fun create(columnNames: Array<String>, data: Array<Array<Any>>): TableModelImpl {
            return TableModelImpl(columnNames, data)
        }

    }

    override fun getValueAt(rowIndex: Int, columnIndex: Int): Any = data[rowIndex]!![columnIndex]!!

    override fun getColumnCount(): Int = columnNames.size

    override fun getRowCount(): Int = data.size

    override fun getColumnName(col: Int): String = columnNames[col]

    override fun getColumnClass(columnIndex: Int): Class<*> = this.getValueAt(0, columnIndex).javaClass

    override fun isCellEditable(rowIndex: Int, columnIndex: Int) = isEditable

    override fun setValueAt(aValue: Any?, rowIndex: Int, columnIndex: Int) {
        ThreadMain.asyncUI {
            data[rowIndex][columnIndex] = aValue!!
            fireTableCellUpdated(rowIndex, columnIndex)
        }
    }
}