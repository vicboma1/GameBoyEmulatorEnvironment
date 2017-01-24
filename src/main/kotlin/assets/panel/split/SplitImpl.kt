package assets.progressBar

import java.awt.Component
import java.awt.Container
import javax.swing.JSplitPane
import javax.swing.UIManager

/**
 * Created by vicboma on 05/12/16.
 */
class SplitImpl<K: Container> internal constructor(
          newOrientation        : Int,
          _leftComponent        : K ,
          _rightComponent       : K ,
          newContinuousLayout   : Boolean = UIManager.getBoolean("SplitPane.continuousLayout"),
          _dividerLocation      : Int  = -1
    ) : JSplitPane(newOrientation,newContinuousLayout, _leftComponent, _rightComponent) , Split {

    companion object {

        fun <K:Container> create(  newOrientation : Int,   _leftComponent: K,   _rightComponent : K) =
             SplitImpl(newOrientation, _leftComponent, _rightComponent)

        fun <K:Container> create(  newOrientation : Int,   _leftComponent: K,   _rightComponent : K,   newContinuousLayout : Boolean ) =
             SplitImpl(newOrientation, _leftComponent, _rightComponent,newContinuousLayout)

        fun <K:Container> create(  newOrientation : Int,   _leftComponent: K,   _rightComponent : K,   newContinuousLayout : Boolean, _dividerLocation : Int ) =
             SplitImpl(newOrientation, _leftComponent, _rightComponent,newContinuousLayout, _dividerLocation)

    }

    init{
        oneTouchExpandable = true
        dividerLocation = _dividerLocation
        alignmentX = Component.CENTER_ALIGNMENT

    }

}
