package app.components.panel

import TableColumnHeaderAdapter
import TableRowAdapter
import app.components.panel.grid.CacheGrid
import app.components.panel.grid.TableGridScrollPaneImpl
import assets.frame.Frame
import assets.panel.multipleImages.PanelCartridge
import assets.panel.multipleImages.PanelCover
import assets.panel.multipleImages.PanelSnapshot
import assets.panel.tab.TabPane
import assets.panel.tab.TabPaneListener
import assets.progressBar.Split
import assets.progressBar.SplitImpl
import assets.progressBar.StatusBarImpl
import assets.table.TableGridImpl
import assets.table.TableImpl
import assets.table.comparator.TableHeaderComparator
import assets.table.listener.rowKey.TableRowKeyListener
import assets.table.model.TableModelImpl
import main.kotlin.utils.listGames.ListGames
import src.configuration.Display
import utils.thread.CustomExecutor
import java.awt.BorderLayout
import java.awt.Component
import java.awt.Container
import java.awt.Dimension
import java.util.concurrent.CompletableFuture
import javax.swing.*

/**
 * Created by vicboma on 12/02/17.
 */
class ContentPaneParentImpl internal constructor(private val classLoader : ClassLoader, private val  frame : Frame, private val  statusBar: StatusBarImpl, completableFuture : CompletableFuture<Any?>) {

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
    fun create (classLoader: ClassLoader, frame: Frame, statusBar: StatusBarImpl, completableFuture: CompletableFuture<Any?>) = ContentPaneParentImpl(classLoader, frame, statusBar,completableFuture)
    }

    var scrollGrid = JScrollPane()
    val panel = JPanel()
    var jtable = JTable()

    init {

        table.apply {
            addMouseListenerColumn(TableColumnHeaderAdapter.create(classLoader, this, TableHeaderComparator.create()))
            addMouseListenerRow(TableRowAdapter.create(this, statusBar, tabbedPane))
            addKeyListenerInput(TableRowKeyListener.create(frame, this, statusBar, tabbedPane))
        }

        visiblePanelListView(false)

        val columnsHeader = arrayOf<Any>("","","","")
        var rows = listGames.rowNames!!.size / columnsHeader.size
        val resto = listGames.rowNames!!.size % columnsHeader.size == 0
        if(!resto)
            rows++

        val tableModel = TableModelImpl.create(arrayOf<Any>("", "", "", ""), Array<Array<Any>>(rows) { arrayOf<Any>("", "", "", "") })
        jtable = TableGridImpl(classLoader ,tableModel, listGames)
        scrollGrid = TableGridScrollPaneImpl(jtable)

        visiblePanelGridView(true)

        CustomExecutor.instance.add {
            showImageIconAsync()
        }

    }

    fun visiblePanelView(state : Boolean, component: Component){
        if(state) frame.contentPane.add(component)
        else    frame.contentPane.remove(component)

        (frame.contentPane as JPanel).revalidate()
        frame.repaint()
    }

    fun visiblePanelGridView(state : Boolean){
        visiblePanelView(state,scrollGrid)
    }

    fun visiblePanelListView(state : Boolean){
        visiblePanelView(state,panelListView)
    }

    fun showImageIconAsync() {

        var size = CacheGrid.limit
        while(size > 0){
            size --
            val completable = CacheGrid.queue.poll()
            completable.thenApplyAsync {
                val row = it["row"] as Int
                val col = it["column"] as Int
                val imageIcon = it["imageIcon"] as ImageIcon
                println(StringBuffer("$row + $col ").toString())
                jtable.setValueAt(imageIcon, row, col)
            }
        }
        println("Empty Queue! state: ${CacheGrid.state} ")
    }


}

