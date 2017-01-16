package components.progressBar

/**
 * Created by vicboma on 05/12/16.
 */
class CheckImpl internal constructor() : javax.swing.JCheckBox() , Check{

    companion object {
        fun create() = CheckImpl()

    }

    init{

    }
}
