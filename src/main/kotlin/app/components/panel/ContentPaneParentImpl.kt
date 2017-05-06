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
import javax.swing.ImageIcon
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.UIManager

/**
 * Created by vicboma on 12/02/17.
 */
class ContentPaneParentImpl internal constructor(val classLoader : ClassLoader, val frame : Frame, val statusBar: StatusBarImpl, val properties : Map<String,Any?>) {

    private val listGames: ListGames
    private val dimension : Dimension
    private val tableModelImpl : TableModelImpl
    private val table: TableImpl
    private val tabbedPane: TabPane
    private val panelListView: SplitImpl<Container>

    private var scrollGrid = JScrollPane()

    companion object {
        fun create (classLoader: ClassLoader, frame: Frame, statusBar: StatusBarImpl, properties : Map<String,Any?>) = ContentPaneParentImpl(classLoader, frame, statusBar,properties)
    }

    init {

        listGames = ListGames.create(classLoader, "list/listGame.json")
        dimension = Dimension(Display.WIDHT, Display.HEIGTH)
        tableModelImpl = TableModelImpl.create(listGames.columnNames, listGames.rowNames)
        table =  TableImpl.create( classLoader, tableModelImpl, dimension)
        tabbedPane =
            TabPane(TabPaneListener.create(table))
                    .apply{
                        add("Cover",    ImageIcon(), PanelCover(classLoader,"cover/_bg.png", "cover/addamsf.png"),            0)
                        add("Snapshot", ImageIcon(), PanelSnapshot(classLoader,"snapshot/_bg.png", "snapshot/addamsf.png"),    1)
                        add("Cartridge", ImageIcon(), PanelCartridge(classLoader,"cartridge/_bg.png", "cartridge/addamsf.png"), 2)
                    }

        panelListView =
            SplitImpl.create(
                    Split.HORIZONTAL,
                    Frame.create(table.scrollPane(), BorderLayout.CENTER).contentPane,
                    Frame.create(tabbedPane.scrollPane(), BorderLayout.CENTER).contentPane,
                    UIManager.getBoolean("SplitPane.continuousLayout"),
                    Display.WIDHT/2)


        table.apply {
            addMouseListenerColumn(TableColumnHeaderAdapter.create(classLoader, this, TableHeaderComparator.create()))
            addMouseListenerRow(TableRowAdapter.create(this, statusBar, tabbedPane))
            addKeyListenerInput(TableRowKeyListener.create(frame, this, statusBar, tabbedPane))
        }

        //visiblePanelListView(true)

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

        val tableModel = TableModelImpl.create(column, recalculeSize(column), row)
        val jtable = TableGridImpl(classLoader, tableModel, coverIndex)
        scrollGrid = TableGridScrollPaneImpl(jtable)

        visiblePanelGridView(listGridVisible)

        val sizeImage = CacheGrid.mapSizeImageCover.get(coverIndex)
        val bufferedImageDefault = ImageIcon().createBufferedImage(sizeImage!!.first,sizeImage!!.second, BufferedImage.TYPE_INT_ARGB)

        return CacheGrid.createRefImage(statusBar,listGames, classLoader, bufferedImageDefault, jtable,coverIndex,properties)
    }
}

