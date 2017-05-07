package app.components.menuBar.options.subItem.options

import app.configuration.properties.Properties
import assets.frame.Frame
import assets.progressBar.Menu
import assets.progressBar.MenuBarImpl
import assets.progressBar.MenuImpl
import assets.progressBar.MenuItemImpl
import src.configuration.properties.PropertiesImpl
import javax.swing.JSlider

/**
 * Created by vicboma on 01/05/17.
 */
fun MenuBarImpl.Companion.MenuBarOptions(frame: Frame, properties : Properties) : Menu {

    var labelAsync = "Async load time :"
    var textAsync = "$labelAsync 5000 ms"

    var labelAPermits = "Set of permits :"
    var textPermits = "$labelAPermits 5 units"

    val asyncLoadTimeLabel = MenuItemImpl.create(textAsync)
    val setOfPermits = MenuItemImpl.create(textPermits)

    val sliderAsync = JSlider(JSlider.HORIZONTAL,0,10000, properties.get("sliderAsync")).apply{
        setLabelTable(this@apply.createStandardLabels(5000))
        setMinorTickSpacing(1000)
        setMajorTickSpacing(2000)
        setPaintTicks(true)
        setPaintLabels(true)
        addChangeListener {
            val source = it.source as JSlider
            properties.put("sliderAsync",source.value)
            asyncLoadTimeLabel.text("$labelAsync ${source.value} ms ")
        }

    }

    val sliderPermits = JSlider(JSlider.HORIZONTAL,1,11,properties.get("sliderPermits")).apply{
        setLabelTable(this@apply.createStandardLabels(5))
        setMinorTickSpacing(1)
        setMajorTickSpacing(2)
        setPaintTicks(true)
        setPaintLabels(true)
        addChangeListener {
            val source = it.source as JSlider
            properties.put("sliderPermits", source.value)
            setOfPermits.text("$labelAPermits ${source.value} units ")
        }
        isEnabled = false
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