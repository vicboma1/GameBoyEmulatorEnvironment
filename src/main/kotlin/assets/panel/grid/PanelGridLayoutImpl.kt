package assets.panel.grid

import assets.frame.Frame
import assets.scroll.ScrollPane
import java.awt.BorderLayout
import java.awt.Container
import java.awt.Dimension
import java.awt.GridLayout
import javax.swing.JButton
import javax.swing.JPanel

/**
 * Created by vicboma on 12/02/17.
 */
class PanelGridLayoutImpl internal constructor( private val _rows: Int, private val _cols: Int, private val _hgap: Int, private val _vgap: Int, private val dimension : Dimension) : JPanel()  {

    private var container: Container
    private val scrollSize by lazy { ScrollPane.create(this, dimension) }

    companion object {
        fun create(_rows: Int, _cols: Int, _hgap: Int, _vgap: Int, dimension: Dimension) = PanelGridLayoutImpl(_rows, _cols, _hgap, _vgap, dimension)
        fun create(_rows: Int, _cols: Int,dimension: Dimension) = PanelGridLayoutImpl(_rows, _cols, 0, 0, dimension)
        fun create(dimension: Dimension) = PanelGridLayoutImpl(1, 0, 0, 0, dimension)
    }

    init {
        container = Frame.create(scrollSize, BorderLayout.CENTER).contentPane

        val grid = GridLayout(_rows, _cols, _hgap, _vgap)
        setLayout(grid)

        for (row in 0.._rows - 1) {
            for (col in 0.._cols - 1) {
                val b = JButton("($row,$col)")
                            .apply{
                                size = Dimension(30,50)
                            }
                add(b).setLocation(row, col)
                //b.addActionListener()
            }
        }
    }

    fun scrollPane() : Container = container


    override fun setVisible(aFlag: Boolean) {
        container.setVisible(aFlag)
    }

}