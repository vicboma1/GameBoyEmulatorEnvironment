package app.components.menuBar.options.subItem.options

import assets.frame.Frame
import assets.progressBar.Menu
import assets.progressBar.MenuBarImpl
import assets.progressBar.MenuImpl
import assets.progressBar.MenuItemImpl
import javax.swing.JSlider

/**
 * Created by vicboma on 01/05/17.
 */
fun MenuBarImpl.Companion.MenuBarOptions(frame: Frame, map: MutableMap<String,Any?>) : Menu {

    var labelAsync = "Async load time :"
    var textAsync = "$labelAsync 500 ms"

    var labelAPermits = "Set of permits :"
    var textPermits = "$labelAPermits 5 units"

    val asyncLoadTimeLabel = MenuItemImpl.create(textAsync)
    val setOfPermits = MenuItemImpl.create(textPermits)

    val sliderAsync = JSlider(JSlider.HORIZONTAL,0,10000,5000).apply{
        setLabelTable(this@apply.createStandardLabels(5000))
        setMinorTickSpacing(1000)
        setMajorTickSpacing(2000)
        setPaintTicks(true)
        setPaintLabels(true)
        addChangeListener {
            val source = it.source as JSlider
            map["sliderAsync"]= source.value
            asyncLoadTimeLabel.text("$labelAsync ${source.value} ms ")
        }

    }

    val sliderPermits = JSlider(JSlider.HORIZONTAL,1,10,5).apply{
        setLabelTable(this@apply.createStandardLabels(5))
        setMinorTickSpacing(1)
        setMajorTickSpacing(2)
        setPaintTicks(true)
        setPaintLabels(true)
        addChangeListener {
            val source = it.source as JSlider
            map["sliderPermits"]= source.value
            setOfPermits.text("$labelAPermits ${source.value} units ")
        }
    }

    return MenuImpl.create("View")
            .apply {
                addMenuItem(asyncLoadTimeLabel)
                addMenuItem(sliderAsync)
                putSeparator()
                addMenuItem(setOfPermits)
                addMenuItem(sliderPermits)
            }

}