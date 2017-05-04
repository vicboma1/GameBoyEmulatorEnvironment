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
import assets.table.GRID_COVER
import assets.table.TableGridImpl
import assets.table.TableImpl
import assets.table.comparator.TableHeaderComparator
import assets.table.listener.rowKey.TableRowKeyListener
import assets.table.model.TableModelImpl
import main.kotlin.utils.image.createBufferedImage
import main.kotlin.utils.listGames.ListGames
import src.configuration.Display
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
class ContentPaneParentImpl internal constructor(private val classLoader : ClassLoader, private val  frame : Frame, private val  statusBar: StatusBarImpl, val properties : Map<String,Any?>) {

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
        fun create (classLoader: ClassLoader, frame: Frame, statusBar: StatusBarImpl, properties : Map<String,Any?>) = ContentPaneParentImpl(classLoader, frame, statusBar,properties)
    }


    var scrollGrid = JScrollPane()
    val panel = JPanel()
    var tableModel = TableModelImpl(arrayOf<Any>(),Array<Array<Any>>(0) { arrayOf<Any>() })
    var jtable = JTable(tableModel)

    init {

        table.apply {
            addMouseListenerColumn(TableColumnHeaderAdapter.create(classLoader, this, TableHeaderComparator.create()))
            addMouseListenerRow(TableRowAdapter.create(this, statusBar, tabbedPane))
            addKeyListenerInput(TableRowKeyListener.create(frame, this, statusBar, tabbedPane))
        }

      /*  coverScreen({ visiblePanelListView(false) }, true, 13, GRID_COVER.FOUR)
                .thenRunAsync {
                    println("****** FIN COMPLETABLE FUTURES *******")
                }
                */
    }

    fun visiblePanelGridView(state : Boolean) = visiblePanelView(state,scrollGrid)

    fun visiblePanelListView(state : Boolean) = visiblePanelView(state,panelListView)

    fun visiblePanelView(state : Boolean, component: Component){
        if(state)
            frame.contentPane.add(component)
        else
            frame.contentPane.remove(component)

        (frame.contentPane as JPanel).revalidate()
        frame.repaint()
    }

    fun coverScreen(componentVisible:() -> Unit, listGridVisible:Boolean , row : Int, coverIndex: GRID_COVER) : CompletableFuture<Void>  {
        fun recalculeSize(column: Int): Int {
            var size = listGames.rowNames!!.size / column
            val resto = listGames.rowNames!!.size % column == 0
            if (!resto)
                size++

            return size
        }

        val column = row
        componentVisible.invoke()

        tableModel = TableModelImpl.create(column, recalculeSize(column), row)
        jtable = TableGridImpl(classLoader, tableModel, coverIndex)
        scrollGrid = TableGridScrollPaneImpl(jtable)

        visiblePanelGridView(listGridVisible)

        val sizeImage = CacheGrid.mapSizeImageCover.get(coverIndex)
        val bufferedImageDefault = ImageIcon().createBufferedImage(sizeImage!!.first,sizeImage!!.second, BufferedImage.TYPE_INT_ARGB)

        return CacheGrid.createRefImage(listGames, classLoader, bufferedImageDefault, jtable,coverIndex,properties)
    }
}

