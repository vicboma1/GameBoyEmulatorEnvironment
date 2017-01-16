package components.dialog.confirm

/**
 * Created by vicboma on 10/12/16.
 */
interface Input {
    fun showDialog(callback: (str : String) -> Unit)
}