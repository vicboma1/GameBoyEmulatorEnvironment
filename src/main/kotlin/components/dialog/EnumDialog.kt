package components.dialog.message

/**
 * Created by vicboma on 10/12/16.
 */
enum class EnumDialog constructor(val value : Int){
    //
    // Message types. Used by the UI to determine what icon to display,
    // and possibly what behavior to give based on the type.
    //
    /** Used for error messages.  */
    ERROR_MESSAGE(0),
    /** Used for information messages.  */
    INFORMATION_MESSAGE(1),
    /** Used for warning messages.  */
    WARNING_MESSAGE(2),
    /** Used for questions.  */
    QUESTION_MESSAGE(3),
    /** No icon is used.  */
    PLAIN_MESSAGE(-1)
}