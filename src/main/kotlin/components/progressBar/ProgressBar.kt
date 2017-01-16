package components.progressBar

import components.Renderable

/**
 * Created by vicboma on 05/12/16.
 */
interface ProgressBar : Renderable {
    companion object{
        val MIN = 0
        val MAX = 100
    }
}