package assets.progressBar

import assets.Renderable

/**
 * Created by vicboma on 05/12/16.
 */
interface ProgressBar : Renderable {
    companion object{
        val MIN = 0
        val MAX = 100
    }
}