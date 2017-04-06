package app.components.panel

import TableColumnHeaderAdapter
import TableRowAdapter
import assets.frame.Frame
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
import utils.thread.CustomExecutor
import java.awt.BorderLayout
import java.awt.Container
import java.awt.Dimension
import javax.swing.*
import javax.swing.table.TableModel
/**
 * Created by vicboma on 12/02/17.
 */
class ContentPaneParentImpl internal constructor(private val classLoader : ClassLoader, private val  frame : Frame, private val  statusBar: StatusBarImpl) {

    private val listGames by lazy {
        ListGames.create(classLoader, "list/listGame.json")
    }

    private val dimension by lazy {
        Dimension(Display.WIDHT, Display.HEIGTH)
    }

    private val tableModelImpl by lazy {
        TableModelImpl.create(listGames.columnNames, listGames.rowNames)
    }


    private val table: TableImpl by lazy {
        TableImpl.create( classLoader, tableModelImpl, dimension)
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

    val panel = JPanel()
    var pane = JScrollPane()


    init {

        table.apply {
            addMouseListenerColumn(TableColumnHeaderAdapter.create(classLoader, this, TableHeaderComparator.create()))
            addMouseListenerRow(TableRowAdapter.create(this, statusBar, tabbedPane))
            addKeyListenerInput(TableRowKeyListener.create(frame, this, statusBar, tabbedPane))
        }

        visiblePanelListView(true)
        visiblePanelGridView(false)

        CustomExecutor.instance.add {

            val columnGrid = listGames.getColumnGrid(Array<Array<Any>>(listGames.rowNames!!.size) { arrayOf<Any>("","","","") } )
            TableModelImpl.create(arrayOf<Any>("","","",""), columnGrid)

        }.thenApplyAsync {


            pane = JScrollPane(
                    JTable(it as TableModel)
                            .apply {
                                showVerticalLines = false
                                showHorizontalLines = false
                                autoResizeMode = JTable.AUTO_RESIZE_ALL_COLUMNS
                                setRowHeight(250)

                            }
            ).apply {
                getVerticalScrollBar().setUnitIncrement(16);
            }

            visiblePanelGridView(true)
        }

    }

    fun visiblePanelListView(state : Boolean){

        if(state) frame.contentPane.add(panelListView)
        else    frame.contentPane.remove(panelListView)

        repaint()
    }

    fun visiblePanelGridView(state : Boolean){

        if(state) frame.contentPane.add(pane)
        else    frame.contentPane.remove(pane)

        repaint()
    }

    private fun repaint()  {
        (frame.contentPane as JPanel).revalidate()
        frame.repaint()
    }
}

