package app.components.menuBar.options.subItem.options

import app.configuration.properties.Properties
import app.configuration.properties.PropertiesEnum
import assets.frame.Frame
import assets.progressBar.*
import javax.swing.JSlider

/**
 * Created by vicboma on 01/05/17.
 */
fun JMenuBarImpl.Companion.MenuBarOptions(frame: Frame, statusBar : StatusBar, properties : Properties) : Menu {

    var labelAsync = "Async load time :"
    var textAsync = "$labelAsync 5000 ms"

    var labelAPermits = "Set of permits :"
    var textPermits = "$labelAPermits 5 units"

    var labelIncremeteGrid = "Increment scrollGrid :"
    var textIncrementGrid = "$labelIncremeteGrid 16 units"


    val asyncLoadTimeLabel = MenuItemImpl.create(textAsync,statusBar)
    val setOfPermits = MenuItemImpl.create(textPermits,statusBar)
    val incrementGridLabel = MenuItemImpl.create(textIncrementGrid,statusBar)


    val sliderAsync = JSlider(JSlider.HORIZONTAL,0,10000, properties.get<Int>(PropertiesEnum.SLIDER_ASYNC_TIME_LOAD)).apply{
        setLabelTable(this@apply.createStandardLabels(5000))
        setMinorTickSpacing(1000)
        setMajorTickSpacing(2000)
        setPaintTicks(true)
        setPaintLabels(true)
        addChangeListener {
            val source = it.source as JSlider
            properties.put( PropertiesEnum.SLIDER_ASYNC_TIME_LOAD ,source.value)
            asyncLoadTimeLabel.text("$labelAsync ${source.value} ms ")
        }
    }

    val sliderPermits = JSlider(JSlider.HORIZONTAL,1,11,properties.get<Int>(PropertiesEnum.SLIDER_PERMITS)).apply{
        setLabelTable(this@apply.createStandardLabels(5))
        setMinorTickSpacing(1)
        setMajorTickSpacing(2)
        setPaintTicks(true)
        setPaintLabels(true)
        addChangeListener {
            val source = it.source as JSlider
            properties.put(PropertiesEnum.SLIDER_PERMITS, source.value)
            setOfPermits.text("$labelAPermits ${source.value} units ")
        }
        isEnabled = false
    }

    val sliderIncrementScrollGrid = JSlider(JSlider.HORIZONTAL,0,100,properties.get<Int>(PropertiesEnum.SLIDER_INCREMENT_SCROLL_GRID)).apply{
        setLabelTable(this@apply.createStandardLabels(50))
        setMinorTickSpacing(10)
        setMajorTickSpacing(20)
        setPaintTicks(true)
        setPaintLabels(true)
        addChangeListener {
            val source = it.source as JSlider
            properties.put(PropertiesEnum.SLIDER_INCREMENT_SCROLL_GRID, source.value)
            incrementGridLabel.text("$labelIncremeteGrid ${source.value} units ")
        }
    }

    return MenuImpl.create("View")
            .apply {
                addMenuItem(asyncLoadTimeLabel)
                addMenuItem(sliderAsync)
                putSeparator()
                addMenuItem(setOfPermits)
                addMenuItem(sliderPermits)
                putSeparator()
                addMenuItem(incrementGridLabel)
                addMenuItem(sliderIncrementScrollGrid)
            }

}