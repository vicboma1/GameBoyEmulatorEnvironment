package assets.dialog.confirm

import java.awt.Frame
import javax.swing.JOptionPane

/**
 * Created by vicboma on 10/12/16.
 */
class ConfirmImpl internal constructor(private val frame : Frame, private val pair : Pair<String,String>, private val option : Int) : Confirm {

    companion object {
        fun create(frame: Frame, pair: Pair<String, String>, option: Int ) = ConfirmImpl(frame, pair, option)
    }

    init {

    }

    override fun showDialog(yes : () -> Unit)  = showDialog(yes,{},{});

    override fun showDialog(yes : () -> Unit,no : () -> Unit)  = showDialog(yes,no,{});

    override fun showDialog(yes : () -> Unit, no : () -> Unit, cancel : () -> Unit) {
        val res = JOptionPane.showConfirmDialog(frame, pair.second, pair.first, option)
        when (res){
            JOptionPane.YES_OPTION -> yes.invoke()
            JOptionPane.NO_OPTION -> no.invoke()
            JOptionPane.CANCEL_OPTION -> cancel.invoke()
            JOptionPane.CLOSED_OPTION -> System.out.println("Clode Dialog")
            else -> {
                throw RuntimeException("Error JOptionPane input buttons !!!!")
            }
        }
    }

}