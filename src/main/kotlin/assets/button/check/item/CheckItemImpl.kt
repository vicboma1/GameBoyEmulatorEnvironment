package assets.progressBar

/**
 * Created by vicboma on 15/12/16.
 */
class CheckItemImpl internal constructor(private val _name: String, private val _isSelected : Boolean) : javax.swing.JCheckBoxMenuItem(_name) , CheckItem {

    companion object {
        fun create(name: String, isSelected : Boolean) = CheckItemImpl(name, isSelected)
    }

    init{
        this.isSelected = _isSelected
    }

    override fun text(text: String){
        super.setText(text)
    }
}
