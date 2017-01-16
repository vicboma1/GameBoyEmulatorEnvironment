package components.frame.component

import components.frame.Frame
import java.awt.event.ComponentEvent
import java.awt.event.ComponentListener

/**
 * Created by vicboma on 06/01/17.
 */
class ComponentListenerScreen internal constructor(var component: Frame) : ComponentListener {

    override fun componentMoved(e: ComponentEvent?) {
        // throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun componentResized(e: ComponentEvent?) {
        val component = e?.component!!
        if(!component.isVisible)
            return

     // //  throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun componentShown(e: ComponentEvent?) {
       // throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun componentHidden(e: ComponentEvent?) {
      //  throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}