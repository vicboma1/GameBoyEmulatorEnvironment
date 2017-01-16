package components.progressBar

/**
 * Created by vicboma on 15/12/16.
 */
interface  FileChooser {
    fun showDialog(callback: (str: String) -> Unit)
}