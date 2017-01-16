package components.table.listener

import components.panel.multipleImages.base.PanelMultipleImages
import components.progressBar.StatusBar
import javax.swing.JTabbedPane
import javax.swing.JTable

/**
 * Created by vicboma on 07/01/17.
 */
class BaseInpuTableList internal constructor() {

    companion object {
         fun executeX(table: JTable, statusBar: StatusBar, source: JTabbedPane, key: Int) {
             val selectedRowIndex = table.getSelectedRow() + key

             if (isValid(selectedRowIndex, table)) return

             val model = table.getModel()
             val available = model.getValueAt(selectedRowIndex, 0).toString().toUpperCase()
             val nameRom = model.getValueAt(selectedRowIndex, 1).toString()
             val nameGame = model.getValueAt(selectedRowIndex, 2).toString()
             val romType = model.getValueAt(selectedRowIndex, 3).toString()
             val bytes = model.getValueAt(selectedRowIndex, 4).toString()

             statusBar.text("$nameRom\t\t\t$nameGame\t\t\t$romType\t\t\t$bytes")

             val selectedIntdexTab = source.selectedIndex
             val folder = source.getTitleAt(selectedIntdexTab).toLowerCase()

             val _nameRom = nameRom.toLowerCase().split(".")[0].toString().plus(".png")

             val panel = source.getComponent(selectedIntdexTab) as PanelMultipleImages
             val path = folder.plus("/$_nameRom")
             panel.setFront(path)
             //panel.setBack("_bg.png")

             val IsVisible = source.selectedComponent.isVisible
             if (IsVisible)
                 source.selectedComponent.repaint();

         }

        fun executeY( table: JTable, source: JTabbedPane, key: Int) {
            val selectedRowIndex = table.getSelectedRow()

            if (isValid(selectedRowIndex, table)) return

            var index = source.tabCount -1

            if(source.selectedIndex + key >= 0)
                index = (source.selectedIndex+key).mod(source.tabCount)

            val tab = source.getComponentAt(index)
            source.selectedComponent = tab

            val IsVisible = tab.isVisible
            if ( IsVisible )
                tab.repaint();
        }

        private fun isValid(selectedRowIndex: Int, table: JTable): Boolean = (selectedRowIndex == -1 || selectedRowIndex >= table.rowCount)

    }

}