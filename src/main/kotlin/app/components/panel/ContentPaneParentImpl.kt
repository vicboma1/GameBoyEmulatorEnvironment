package app.components.panel

import TableColumnHeaderAdapter
import TableRowAdapter
import assets.frame.Frame
import assets.panel.grid.PanelGridLayoutImpl
import assets.panel.multipleImages.PanelCartridge
import assets.panel.multipleImages.PanelCover
import assets.panel.multipleImages.PanelSnapshot
import assets.panel.tab.TabPane
import assets.panel.tab.TabPaneListener
import assets.progressBar.Split
import assets.progressBar.SplitImpl
import assets.progressBar.StatusBarImpl
import assets.table.TableImpl
import assets.table.comparator.TableHeaderComparator
import assets.table.listener.rowKey.TableRowKeyListener
import assets.table.model.TableModelImpl
import main.kotlin.utils.listGames.ListGames
import src.configuration.Display
import java.awt.BorderLayout
import java.awt.Container
import java.awt.Dimension
import javax.swing.ImageIcon
import javax.swing.UIManager

/**
 * Created by vicboma on 12/02/17.
 */
class ContentPaneParentImpl internal constructor(private val classLoader : ClassLoader, private val  frame : Frame, private val  statusBar: StatusBarImpl) {

    private val dimension =  Dimension(Display.WIDHT, Display.HEIGTH)

    /*************** GRID VIEW ***************/
    private val panelGridView : PanelGridLayoutImpl by lazy {
        PanelGridLayoutImpl.create(50, 7,50,150, dimension)
    }

    /*************** LIST VIEW ***************/

    private val table: TableImpl by lazy {
        val listGames = ListGames.create(classLoader,"list/listGame.json")
        TableImpl.create(
                classLoader,
                //TableDaemon.create(),
                TableModelImpl.create(listGames.columnNames, listGames.rowNames),
                dimension
        )
    }

    private val tabbedPane: TabPane by lazy {
        TabPane(TabPaneListener.create(table))
                .apply{
                    add("Cover",    ImageIcon(), PanelCover(classLoader,"cover/_bg.png", "cover/addamsf.png"),            0)
                    add("Snapshot", ImageIcon(), PanelSnapshot(classLoader,"snapshot/_bg.png", "snapshot/addamsf.png"),    1)
                    add("Cartridge", ImageIcon(), PanelCartridge(classLoader,"cartridge/_bg.png", "cartridge/addamsf.png"), 2)
                }
    }

    private val panelListView: SplitImpl<Container> by lazy {
        SplitImpl.create(
                Split.HORIZONTAL,
                Frame.create(table.scrollPane(), BorderLayout.CENTER).contentPane,
                Frame.create(tabbedPane.scrollPane(), BorderLayout.CENTER).contentPane,
                UIManager.getBoolean("SplitPane.continuousLayout"),
                Display.WIDHT/2)
    }

    companion object {
    fun create ( classLoader : ClassLoader,frame : Frame, statusBar: StatusBarImpl) = ContentPaneParentImpl(classLoader, frame, statusBar)
    }

    init {

        visiblePanelListView(true)
        visiblePanelGridView(false)

        table.apply {
            addMouseListenerColumn(TableColumnHeaderAdapter.create(classLoader, table, TableHeaderComparator.create()))
            addMouseListenerRow(TableRowAdapter.create(table, statusBar, tabbedPane))
            addKeyListenerInput(TableRowKeyListener.create(frame, table, statusBar, tabbedPane))
        }
    }

    fun visiblePanelListView(state : Boolean){
        if(state) frame.contentPane.add(panelListView)
        else    frame.contentPane.remove(panelListView)

        panelListView.isVisible = state
    }

    fun visiblePanelGridView(state : Boolean){
        if(state) frame.contentPane.add(panelGridView.scrollPane())
        else    frame.contentPane.remove(panelGridView.scrollPane())

        panelGridView.isVisible = state
    }
}
