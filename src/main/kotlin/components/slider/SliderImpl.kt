package components.progressBar

import components.Renderable
import utils.ThreadMain
import java.awt.Component
import java.util.concurrent.CompletableFuture
import javax.swing.JSlider

/**
 * Created by vicboma on 05/12/16.
 */
class SliderImpl internal constructor(private val position : Int, private val min: Int,private  val max: Int, private val _value : Int) : JSlider(position,min,max,_value) , Slider {

    companion object {
        fun create(position: Int, min: Int, max: Int, _value: Int): Renderable {
            return SliderImpl(position, min,max, _value)
        }
    }

    init{
        this.apply {
            value = 0
            setMajorTickSpacing(10);
            setMinorTickSpacing(1);
            setPaintTicks(true);
            setPaintLabels(true);
            setAlignmentX(Component.CENTER_ALIGNMENT)
        }
    }

    override fun asyncUI()  {
        ThreadMain.asyncUI {
            CompletableFuture.runAsync {
                Thread.sleep(100)
                for (i in min..max) {
                    Thread.sleep(30)
                    value = i * 1
                }
            }.thenAcceptAsync {
                Thread.sleep(500)
                for (i in max downTo min) {
                    Thread.sleep(30)
                    value = i * 1
                }
                asyncUI()
            }
        }
    }
}
