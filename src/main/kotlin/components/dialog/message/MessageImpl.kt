package components.dialog.message

import java.awt.Frame
import javax.swing.ImageIcon
import javax.swing.JOptionPane

/**
 * Created by vicboma on 10/12/16.
 */
class MessageImpl internal constructor(private val frame : Frame, val pair : Pair<String,String>, val option : EnumDialog, val icon : ImageIcon?) : Message {

    companion object {
        fun create(frame: Frame, pair: Pair<String, String>, option: EnumDialog) =
                MessageImpl(frame, pair, option, null)

        fun create(frame: Frame, pair: Pair<String, String>, option: EnumDialog, icon: ImageIcon) =
                MessageImpl(frame, pair, option, icon)
    }

    init {
    }

    override fun showDialog() = JOptionPane.showMessageDialog(frame, pair.second, pair.first, option.value, icon)

}