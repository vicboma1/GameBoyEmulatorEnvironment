package src.configuration

import TableColumnHeaderAdapter
import TableRowAdapter
import app.components.MenuChooser
import app.components.MenuDialog
import app.components.MenuFile
import assets.frame.Frame
import assets.panel.multipleImages.PanelCartridge
import assets.panel.multipleImages.PanelCover
import assets.panel.multipleImages.PanelSnapshot
import assets.panel.tab.TabPane
import assets.panel.tab.TabPaneListener
import assets.progressBar.MenuBarImpl
import assets.progressBar.Split
import assets.progressBar.SplitImpl
import assets.progressBar.StatusBarImpl
import assets.table.TableImpl
import assets.table.comparator.TableHeaderComparator
import assets.table.listener.rowKey.TableRowKeyListener
import assets.table.model.TableModelImpl
import main.kotlin.utils.listGames.ListGames
import java.awt.BorderLayout
import java.awt.Container
import java.awt.Dimension
import javax.swing.ImageIcon
import javax.swing.UIManager

/**
 * Created by vicboma on 02/12/16.
 */
class ConfigurationImpl internal constructor(private val frame : Frame) {

     val statusBar: StatusBarImpl by lazy {
         StatusBarImpl.create(Display.WIDHT)
     }

     val table: TableImpl by lazy {

         val listGames = ListGames.create("list/listGame.json")

         TableImpl.create(
            //TableDaemon.create(),
            TableModelImpl.create(listGames.columnNames, listGames.rowNames),
            Dimension(Display.WIDHT, Display.HEIGTH)
         )
     }

     val display: Display by lazy {
         DisplayImpl.create(Display.KFRAME_JAVA, Display.WIDHT, Display.HEIGTH, Display.VISIBLE, BorderLayout())
     }

     val tabbedPane: TabPane by lazy {
         TabPane(TabPaneListener.create(table))
                 .apply{
                     add("Cover",    ImageIcon(), PanelCover("cover/_bg.png", "cover/addamsf.png"),            0)
                     add("Snapshot", ImageIcon(), PanelSnapshot("snapshot/_bg.png", "snapshot/addamsf.png"),    1)
                     add("Cartridge", ImageIcon(),PanelCartridge("cartridge/_bg.png", "cartridge/addamsf.png"), 2)
                 }
     }

    val panel: SplitImpl<Container> by lazy {
        SplitImpl.create(
                Split.HORIZONTAL,
                Frame.create(table.scrollPane(), BorderLayout.CENTER).contentPane,
                Frame.create(tabbedPane.scrollPane(), BorderLayout.CENTER).contentPane,
                UIManager.getBoolean("SplitPane.continuousLayout"),
                Display.WIDHT/2)
    }

    val menuBar: MenuBarImpl by lazy {
        MenuBarImpl.create()
    }

    companion object {
        fun create(frame : Frame) = ConfigurationImpl(frame)
    }


    init {

        table.apply {
            addMouseListenerColumn(TableColumnHeaderAdapter.create(table, TableHeaderComparator.create()))
            addMouseListenerRow(TableRowAdapter.create(table,statusBar,tabbedPane ))
            addKeyListenerInput(TableRowKeyListener.create(frame,table,statusBar,tabbedPane))
        }

        menuBar.addMenu(
                listOf(
                    MenuBarImpl.MenuFile(frame),
                    MenuBarImpl.MenuDialog(frame),
                    MenuBarImpl.MenuChooser(frame)
                )
        )
    }

}

