package assets.progressBar

import assets.Renderable
import utils.ThreadMain
import java.awt.Dimension
import java.util.concurrent.CompletableFuture
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.JTextArea

/**
 * Created by vicboma on 05/12/16.
 */
class TextAreaImpl internal constructor(val _text: String) : JScrollPane() , TextArea {

    val textArea = JTextArea(_text)

    companion object {

        fun create(text: String) : Renderable = TextAreaImpl(text)

    }

    init{

        textArea.apply {
            lineWrap = true
            wrapStyleWord = true
            isEditable = false
            autoscrolls = true
        }

        this.apply {
            setViewportView(textArea)
            setPreferredSize(Dimension(380, 100))
            setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS)
            alignmentX = JPanel.CENTER_ALIGNMENT
        }

    }

    override fun asyncUI() {
        ThreadMain.asyncUI {
            CompletableFuture.runAsync {
                Thread.sleep(1500)
                textArea.text = ""
                for ( i in 0.._text.length-1) {
                    Thread.sleep(20)
                    i.text()
                }
            }.thenAcceptAsync {
                Thread.sleep(1000)
                asyncUI()
            }
        }
    }

    private fun isMod(a : Int, b :Int) = (a % b) == 0

    public fun Int.text() = when {
        //isMod(this,TextArea.MOD) -> caretPosition = getDocument().getLength()
        else -> {
            textArea.append(_text[this].toString())
            textArea.caretPosition = textArea.getDocument().getLength()
        }
    }

}
