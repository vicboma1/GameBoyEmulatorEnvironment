package src.app

import src.configuration.ConfigurationImpl
import java.awt.BorderLayout
import java.util.concurrent.CompletableFuture
import javax.swing.JFrame

/**
 * Created by vicboma on 02/12/16.
 */
class ApplicationImpl internal constructor(private val frame: JFrame) : Application {

    private var configuration : ConfigurationImpl? = null

    companion object {
        fun create(frame: JFrame): Application {
            return ApplicationImpl(frame)
        }
    }

    override fun startAsync(configuration : ConfigurationImpl) {
        this.configuration = configuration
        var future = CompletableFuture<Application>()
        runAsync(future).get()
    }

    private fun runAsync(result: CompletableFuture<Application>) : CompletableFuture<Application>  {
        CompletableFuture.runAsync {
            this.let {
                setLayout()
                setTitle()
                setCloseOp()
                setMenuBar()
                setStatusBar()
                frame.pack()
                setSize()
                setVisible()
                result.complete(this)
            }
        }

        return result
    }

    private fun setCloseOp() {
        frame.defaultCloseOperation = configuration?.display?.closeOp!!
    }

    private fun setTitle()  {
        frame.setTitle(configuration?.display?.title!!)
    }

    private fun setLayout() {
        frame.setLayout(configuration?.display?.layout)
    }

    private fun setSize()  {
        frame.setSize(configuration?.display?.widht!!, configuration?.display?.heigth!!)
        frame.isResizable = false
    }

    private fun setLocation()  {
        frame.setLocationRelativeTo(configuration?.display?.location)
    }

    private fun setVisible() {
        frame.isVisible = configuration?.display?.visible!!
    }

    private fun setMenuBar() {
        frame.jMenuBar = configuration?.menuBar
    }

    private fun setStatusBar() {
        frame.contentPane.add(configuration?.statusBar, BorderLayout.SOUTH);
    }
}


