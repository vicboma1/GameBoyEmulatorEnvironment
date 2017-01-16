package src.app

import src.configuration.ConfigurationImpl

/**
 * Created by vicboma on 02/12/16.
 */
interface Application {
    fun startAsync(configuration : ConfigurationImpl)
}
