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
import main.kotlin.utils.image.BufferedImageMemoryFromComponent
import main.kotlin.utils.image.scale
import main.kotlin.utils.listGames.ListGames
import src.configuration.Display
import utils.thread.CustomExecutor
import java.awt.*
import java.awt.image.BufferedImage
import java.lang.Thread.sleep
import javax.swing.*

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
    var jtable = JTable()

    init {

        table.apply {
            addMouseListenerColumn(TableColumnHeaderAdapter.create(classLoader, this, TableHeaderComparator.create()))
            addMouseListenerRow(TableRowAdapter.create(this, statusBar, tabbedPane))
            addKeyListenerInput(TableRowKeyListener.create(frame, this, statusBar, tabbedPane))
        }

        visiblePanelListView(false)

        CustomExecutor.instance.add {

            val columnsHeader = arrayOf<Any>("","","","")
            var rows = listGames.rowNames!!.size / columnsHeader.size
            val resto = listGames.rowNames!!.size % columnsHeader.size == 0
            if(!resto)
                rows++

            jtable = JTable(TableModelImpl.create(arrayOf<Any>("", "", "", ""), Array<Array<Any>>(listGames.rowNames!!.size / columnsHeader.size) { arrayOf<Any>("", "", "", "") }))
                    .apply {
                        showVerticalLines = false
                        showHorizontalLines = false
                        autoResizeMode = JTable.AUTO_RESIZE_ALL_COLUMNS
                        setRowHeight(250)

                    }
            pane = JScrollPane(jtable).apply {
                getVerticalScrollBar().setUnitIncrement(16)

            }

            visiblePanelGridView(true)


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


    private val w = 240//340 4
    private val h = 200//300 4

    val convertPanelToImage = BufferedImageMemoryFromComponent()
    var imageDefault = ImageIcon().scale(w, h,classLoader.getResource("cover/_gbNotFound.png").file.toString())


    fun getColumnGrid(capacity :Int) {

        val res = Array<Array<Any>>(capacity) { arrayOf<Any>(ImageIcon(),ImageIcon(),ImageIcon(),ImageIcon()) }

        try {
            val rows = res.size
            var cols = res[0].size
            for (row in 0..rows - 1) {
                for (col in 0..cols - 1) {
                    sleep(600)
                    val nameRom = listGames.rowNames!![(row * cols) + col][1].toString()
                    val nameGame = nameRom.toLowerCase().split(".")[0].toString().plus(".png")
                    println(nameGame)
                    val image = classLoader.getResource("cover/$nameGame")
                    val bufferedImage: BufferedImage?
                    bufferedImage = when (image) {
                        null -> imageDefault
                        else -> ImageIcon().scale(w, h, image.file.toString())
                    }
                    val panel = JPanel().apply {
                        layout = BoxLayout(this, BoxLayout.Y_AXIS)
                        add(JLabel(ImageIcon(bufferedImage)).apply { setAlignmentX(Component.CENTER_ALIGNMENT) })
                        add(JLabel(" ").apply { setAlignmentX(Component.CENTER_ALIGNMENT) })
                        add(JLabel(nameRom).apply { setAlignmentX(Component.CENTER_ALIGNMENT) })
                        add(JLabel(" ").apply {
                            setAlignmentX(Component.CENTER_ALIGNMENT)
                        })
                        size = Dimension(w, h)
                        isOpaque = false
                        setBackground(Color(0, 0, 0, 0))
                    }

                    val bufferedPanel = convertPanelToImage.invoke(panel)
                    jtable.setValueAt(ImageIcon(bufferedPanel),row,col)
                }
            }

        }
        catch(e: Exception){
            println(e.message)
        }
        finally {
        }
    }
}

