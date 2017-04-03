package assets.panel.grid

import assets.frame.Frame
import assets.scroll.ScrollPane
import assets.table.model.TableModelImpl
import main.kotlin.utils.image.scale
import java.awt.*
import java.awt.image.BufferedImage
import javax.swing.BoxLayout
import javax.swing.ImageIcon
import javax.swing.JLabel
import javax.swing.JPanel

/**
 * Created by vicboma on 12/02/17.
 */
class PanelGridLayoutImpl internal constructor(
        private val _rows: Int,
        private val _cols: Int,
        private val _hgap: Int,
        private val _vgap: Int,
        private val dimension : Dimension,
        private val tableModelImpl : TableModelImpl) : JPanel()  {

    private var container: Container
    private val scrollSize by lazy { ScrollPane.create(this, dimension) }

    companion object {
        fun create(_rows: Int, _cols: Int, _hgap: Int, _vgap: Int, dimension: Dimension,tableModelImpl : TableModelImpl) = PanelGridLayoutImpl(_rows, _cols, _hgap, _vgap, dimension,tableModelImpl)
        fun create(_rows: Int, _cols: Int,dimension: Dimension, tableModelImpl : TableModelImpl) = PanelGridLayoutImpl(_rows, _cols, 0, 0, dimension,tableModelImpl)
        fun create(dimension: Dimension, tableModelImpl : TableModelImpl) = PanelGridLayoutImpl(1, 0, 0, 0, dimension,tableModelImpl)
    }

    init {
        container = Frame.create(scrollSize, BorderLayout.CENTER).contentPane

        val grid = GridLayout(_rows, _cols, _hgap, _vgap)
        setLayout(grid)

        for (row in 0.._rows - 1) {
            for (col in 0.._cols - 1) {
                val nameRom = this.tableModelImpl.getValueAt((row * _cols) + col, 1).toString()
                val nameGame = nameRom.toLowerCase().split(".")[0].toString().plus(".png")
                println(nameGame)
                val image = Thread.currentThread().contextClassLoader.getResource("cover/$nameGame")
                var bufferedImage : BufferedImage?
                bufferedImage = when(image){
                    null -> ImageIcon().scale(350, 300,Thread.currentThread().contextClassLoader.getResource("cover/_gbNotFound.png").file.toString())
                    else -> ImageIcon().scale(350, 300, image.file.toString())
                }
                val panel = JPanel(GridBagLayout()).apply{
                    layout = BoxLayout(this, BoxLayout.Y_AXIS)
                    add(JLabel(ImageIcon(bufferedImage)).apply { setAlignmentX(Component.CENTER_ALIGNMENT) })
                    add(JLabel(" ").apply { setAlignmentX(Component.CENTER_ALIGNMENT) })
                    add(JLabel(nameRom).apply { setAlignmentX(Component.CENTER_ALIGNMENT) })
                }

                add(panel).setLocation(row, col)
                }
                //b.addActionListener()
            }
        }


    fun scrollPane() : Container = container

    override fun setVisible(aFlag: Boolean) {
        container.setVisible(aFlag)
    }

}