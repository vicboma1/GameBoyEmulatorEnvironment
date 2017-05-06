package assets.table.listener.renderer

import java.awt.Component
import javax.swing.JTable
import javax.swing.table.DefaultTableCellRenderer
import javax.swing.table.TableCellRenderer

/**
 * Created by vicboma on 20/01/17.
 */
class TableCellRendererComponent internal constructor() : TableCellRenderer {


    companion object{
        fun create() = TableCellRendererComponent()
    }

    init {

    }

    override fun getTableCellRendererComponent(table: JTable?, value: Any?, isSelected: Boolean, hasFocus: Boolean, row: Int, column: Int): Component {
        val newCellRenderer  =  DefaultTableCellRenderer();

        println("table: $table, value: &value, isSelected: $isSelected, hasFocus: $hasFocus, row: $row, column: $column")

        return newCellRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column)
    }

}