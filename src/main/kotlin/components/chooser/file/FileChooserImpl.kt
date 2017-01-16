package components.progressBar

import javax.swing.JFileChooser
import javax.swing.JFrame


/**
 * Created by vicboma on 05/12/16.
 */
class FileChooserImpl internal constructor(private val frame : JFrame) : javax.swing.JFileChooser(), FileChooser {

    companion object {
        fun create(frame : JFrame) = FileChooserImpl(frame)
    }

    init {

    }

    override fun showDialog(callback: (str: String) -> Unit) {
        val file = javax.swing.JFileChooser();
        file.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        val res = file.showOpenDialog(frame)

        when (res) {
            JFileChooser.APPROVE_OPTION ->  callback.invoke(file.selectedFile.toString())
            JFileChooser.CANCEL_OPTION ->  callback.invoke("CANCEL_OPTION")
            else  ->  callback.invoke("ERROR_OPTION")
        }
    }

}


