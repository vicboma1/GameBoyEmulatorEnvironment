package components.dialog.confirm

/**
 * Created by vicboma on 10/12/16.
 */
interface  Confirm {
    fun showDialog(yes : () -> Unit)
    fun showDialog(yes : () -> Unit,no : () -> Unit)
    fun showDialog(yes : () -> Unit, no : () -> Unit, cancel : () -> Unit)
}