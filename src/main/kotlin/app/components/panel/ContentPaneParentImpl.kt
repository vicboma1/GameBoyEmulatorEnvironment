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
import java.awt.image.BufferedImage
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

        //getColumnGrid(rows)

        completableFuture.thenApplyAsync {
        //    getColumnGrid(rows)
        }

        CustomExecutor.instance.add {
            getColumnGrid(rows)
        }

        /* CustomExecutor.instance.add {

           val columnsHeader = arrayOf<Any>("","","","")
           var rows = listGames.rowNames!!.size / columnsHeader.size
           val resto = listGames.rowNames!!.size % columnsHeader.size == 0
           if(!resto)
               rows++

           val columnGrid = getColumnGrid(rows)
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
       } */

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

    private val w = 240//340 4
    private val h = 200//300 4

    //val convertPanelToImage = BufferedImageMemoryFromComponent()
    //var imageDefault = ImageIcon().scale(w, h,classLoader.getResource("cover/_gbNotFound.png").file.toString())


    fun getColumnGrid(capacity :Int) {

        while(CacheGrid.queue.isNotEmpty()){
            val poll = CacheGrid.queue.poll()
            val bufferedImage = poll["bufferedPanel"] as BufferedImage
            val row = poll["row"] as Int
            val col = poll["column"] as Int
            jtable.setValueAt(ImageIcon(bufferedImage), row, col)
            Thread.sleep(100)
        }
       /* val res = Array<Array<Any>>(capacity) { arrayOf<Any>(ImageIcon(),ImageIcon(),ImageIcon(),ImageIcon()) }

        try {
            val rows = res.size
            var cols = res[0].size
            for (row in 0..rows) {
                for (col in 0..cols - 1) {
                    val key = listGames.rowNames!![(row * cols) + col][1].toString()
                    var image =
                    jtable.setValueAt(ImageIcon(image?.get()),row,col)
                    sleep(100)
                }
            }

        }
        catch(e: Exception){
            println(e.message)
        }
        finally {
        }*/
    }
}

