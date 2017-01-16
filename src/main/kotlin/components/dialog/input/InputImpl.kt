package components.dialog.confirm

import components.dialog.message.EnumDialog
import java.awt.Frame
import java.util.*
import javax.swing.Icon
import javax.swing.JOptionPane

/**
 * Created by vicboma on 10/12/16.
 */
class InputImpl internal constructor(private val frame : Frame, private val pair : Pair<String,String>, private val option : EnumDialog, private val icon : Icon?, private val selectionValues: ArrayList<Object>?, private val initialSelectionValue : Object?) : Input {

    companion object {

        fun create(frame: Frame, pair: Pair<String, String>, option: EnumDialog) =
                InputImpl(frame, pair, option, null, null, null)

        fun create(frame: Frame, pair: Pair<String, String>, option: EnumDialog, icon: Icon?, selectionValues: ArrayList<Object>?, initialSelectionValue: Object?) =
                InputImpl(frame, pair, option, icon, selectionValues, initialSelectionValue)
    }

    init {

    }

    override fun showDialog(callback: (str: String) -> Unit) {
        val res = JOptionPane.showInputDialog(frame, pair.second, pair.first, option.value, icon, selectionValues?.toArray(), initialSelectionValue)
        if(null != res)
            callback.invoke(res.toString())
    }
}


