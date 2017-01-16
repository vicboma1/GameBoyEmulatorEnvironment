package components.progressBar

import components.Renderable

/**
 * Created by vicboma on 05/12/16.
 */
interface TextArea : Renderable {
    companion object {
        val MOD = 380
        val KAFKA = "Una mañana, tras un sueño intranquilo, Gregorio Samsa se despertó convertido en un monstruoso insecto. Estaba echado de espaldas sobre un duro caparazón y, al alzar la cabeza, vio su vientre convexo y oscuro, surcado por curvadas callosidades, sobre el que casi no se aguantaba la colcha, que estaba a punto de escurrirse hasta el suelo"
        val LOREMIPSUM = "Lorem ipsum is a pseudo-Latin text used in web design, typography, layout, and printing in place of English to emphasise design elements over content. It's also called placeholder (or filler) text. It's a convenient tool for mock-ups. It helps to outline the visual elements of a document or presentation, eg typography, font, or layout."
        val TEXT = "                                     Hello World!!!!\n                                    Asynchronous\n                                       Application"
    }
}