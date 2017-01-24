package assets.table.model

import assets.table.comparator.TableHeaderComparator
import javax.swing.table.AbstractTableModel

/**
 * Created by vbolinch on 02/01/2017.
 */

class TableModelImpl internal constructor(val columnNames : Array<Any>?, val data: Array<Array<Any>>?, var isEditable : Boolean = false ): AbstractTableModel() {

    companion object {
        fun create(columnNames: Array<Any>?, data: Array<Array<Any>>?) = TableModelImpl(columnNames, data)
    }

    init {
    }

    override fun getValueAt(rowIndex: Int, columnIndex: Int) : Any = data!![rowIndex][columnIndex]

    override fun getColumnCount(): Int = columnNames!!.size

    override fun getRowCount(): Int { if(data != null) return data.size; return 0 }

    override fun getColumnName(col: Int): String = columnNames!![col].toString()

    override fun getColumnClass(columnIndex: Int) : Class<*> = this.getValueAt(0, columnIndex).javaClass

    override fun isCellEditable(rowIndex: Int, columnIndex: Int) = isEditable

    override fun setValueAt(aValue: Any?, rowIndex: Int, columnIndex: Int) {
        data!![rowIndex][columnIndex] = aValue!!
        fireTableCellUpdated(rowIndex,columnIndex)
    }

    fun sortWith(cmp : TableHeaderComparator) = data?.sortWith(cmp)

    fun toString(rowIndex: Int): String {
         return "${getValueAt(rowIndex,0)} ${getValueAt(rowIndex,1)} ${getValueAt(rowIndex,2)} ${getValueAt(rowIndex,3)} ${getValueAt(rowIndex,4)}"
    }
}