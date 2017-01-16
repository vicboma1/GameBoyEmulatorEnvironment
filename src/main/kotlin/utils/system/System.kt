package utils.system

/**
 * Created by vicboma on 14/01/17.
 */

public fun isWindows() = getProperty().indexOf("win") >= 0

public fun isMac() = getProperty().indexOf("mac") >= 0

public fun isUnix() = getProperty().indexOf("nix") >= 0 ||
                             getProperty().indexOf("nux") >= 0 ||
                             getProperty().indexOf("aix") > 0

public fun isSolaris() = getProperty().indexOf("sunos") >= 0

private fun getProperty() = System.getProperty("os.name").toLowerCase()