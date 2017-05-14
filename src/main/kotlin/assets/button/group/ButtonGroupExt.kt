package assets.progressBar

import javax.swing.AbstractButton
import javax.swing.ButtonGroup

/**
 * Created by vicboma on 05/12/16.
 */

fun <T>ButtonGroup.add(list: List<T>) {
    for( it in list){
        this.add(it as AbstractButton )
    }
}
