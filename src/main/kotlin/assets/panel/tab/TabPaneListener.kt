package assets.panel.tab

import assets.panel.multipleImages.base.PanelMultipleImages
import assets.table.TableImpl
import utils.ThreadMain
import javax.swing.JTabbedPane
import javax.swing.event.ChangeEvent
import javax.swing.event.ChangeListener

/**
 * Created by vicboma on 15/01/17.
 */
class TabPaneListener internal constructor(private val table: TableImpl) : ChangeListener {

    companion object{
        fun create(table: TableImpl) = TabPaneListener(table)
    }

    override fun stateChanged(e: ChangeEvent?) {
        ThreadMain.asyncUI {
            val source = e?.getSource()
            if (source is JTabbedPane) {

                var selectedRow = table.selectedRow
                if(selectedRow != -1) {

                    val selectedIntdexTab = source.selectedIndex
                    val folder = source.getTitleAt(selectedIntdexTab).toLowerCase()
                    val model = table.getModel()
                    val available = model?.getValueAt(selectedRow!!, 0).toString().toUpperCase()
                    val nameRom = model?.getValueAt(selectedRow!!, 1).toString().toLowerCase().split(".")[0].toString().plus(".png")


                    val panel = source.getComponent(selectedIntdexTab) as PanelMultipleImages
                    val path = folder.plus("/$nameRom")
                    panel.setFront(path)
                    //panel.setBack("_bg.png")

                    val IsVisible = source.isVisible
                    if ( IsVisible )
                        source.repaint();
                }
            }
        }
    }

}