package assets.table.listener

import assets.dialog.confirm.ConfirmImpl
import assets.panel.multipleImages.base.PanelMultipleImages
import assets.progressBar.StatusBar
import kotlinx.coroutines.experimental.launch
import utils.swing.Swing
import java.awt.Frame
import java.io.File
import javax.swing.JFrame
import javax.swing.JOptionPane
import javax.swing.JTabbedPane
import javax.swing.JTable

/**
 * Created by vicboma on 07/01/17.
 */
class BaseInpuTableList internal constructor() {

    companion object {
        fun executeEnter(frame:Frame, table: JTable) {
            val selectedRowIndex = table.getSelectedRow()

            val model = table.getModel()
            val nameRom = model.getValueAt(selectedRowIndex, 1).toString()

            val pathRom = File("rom/$nameRom").absolutePath

            launch(Swing) {
                frame.setExtendedState(JFrame.ICONIFIED);
                ConfirmImpl.create(Frame(),Pair("Load Rom", pathRom), JOptionPane.YES_NO_CANCEL_OPTION)
                        .showDialog({
                            frame.setExtendedState(JFrame.NORMAL)
                        }, {
                            frame.setExtendedState(JFrame.NORMAL)
                        }, {
                            frame.setExtendedState(JFrame.NORMAL)
                        })
            }
        }

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

             val (panel, path) = getPathAbsolute(nameRom, source)

             panel.setFront(path)
             //panel.setBack("_bg.png")

             val IsVisible = source.selectedComponent.isVisible
             if (IsVisible)
                 source.selectedComponent.repaint();

         }



        fun executeY( table: JTable, source: JTabbedPane, key: Int) {
            val selectedRowIndex  = table.getSelectedRow()

            if (isValid(selectedRowIndex, table)) return

            var index = source.tabCount -1

            if(source.selectedIndex + key >= 0)
                index = (source.selectedIndex+key).mod(source.tabCount)

            val tab = source.getComponentAt(index)
            source.selectedComponent = tab

            val nameRom = table.model.getValueAt(selectedRowIndex, 1).toString()

            val (panel, path) = getPathAbsolute(nameRom, source)

            panel.setFront(path)

            val IsVisible = tab.isVisible
            if ( IsVisible )
                tab.repaint();
        }

        private fun getPathAbsolute(nameRom: String, source: JTabbedPane): Pair<PanelMultipleImages, String> {
            val selectedIntdexTab = source.selectedIndex
            val folder = source.getTitleAt(selectedIntdexTab).toLowerCase()

            val _nameRom = nameRom.toLowerCase().split(".")[0].toString().plus(".png")

            val panel = source.getComponent(selectedIntdexTab) as PanelMultipleImages
            val path = folder.plus("/$_nameRom")

            return Pair(panel, path)
        }

        private fun isValid(selectedRowIndex: Int, table: JTable): Boolean = (selectedRowIndex == -1 || selectedRowIndex >= table.rowCount)

    }

}
