import org.parosproxy.paros.network.HttpMessage
import org.zaproxy.zap.authentication.AuthenticationHelper
import org.zaproxy.zap.authentication.GenericAuthenticationCredentials

// The authenticate function will be called for authentications made via ZAP.

// The authenticate function is called whenever ZAP requires to authenticate, for a Context for which this script
// was selected as the Authentication Method. The function should send any messages that are required to do the authentication
// and should return a message with an authenticated response so the calling method.
//
// NOTE: Any message sent in the function should be obtained using the 'helper.prepareMessage()' method.
//
// Parameters:
//  helper          -   a helper class providing useful methods: prepareMessage(), sendAndReceive(msg), getHttpSender()
//  paramsValues    -   the values of the parameters configured in the Session Properties -> Authentication panel.
//                      The paramsValues is a map, having as keys the parameters names (as returned by the getRequiredParamsNames()
//                      and getOptionalParamsNames() functions below)
//  credentials     -   an object containing the credentials values, as configured in the Session Properties -> Users panel.
//                      The credential values can be obtained via calls to the getParam(paramName) method. The param names are the ones
//                      returned by the getCredentialsParamsNames() below
HttpMessage authenticate(AuthenticationHelper helper, Map<String, String> paramsValues, GenericAuthenticationCredentials credentials) {
    print("Authenticating via script...")
    def msg = helper.prepareMessage()

    // TODO: Process message to match the authentication needs

    // Configurations on how the messages are sent/handled:
    // Set to follow redirects when sending messages (default is false).
    helper.getHttpSender().setFollowRedirect(true)

    // Send message without following redirects (overriding the option previously set).
    helper.sendAndReceive(msg, false)

    // Set the number of maximum redirects followed to 5 (default is 100). Main purpose is to prevent infinite loops.
    helper.getHttpSender().setMaxRedirects(5)

    helper.sendAndReceive(msg)

    return msg
}

// This function is called during the script loading to obtain a list of the names of the required configuration parameters,
// that will be shown in the Session Properties -> Authentication panel for configuration. They can be used
// to input dynamic data into the script, from the user interface (e.g. a login URL, name of POST parameters etc.)
String[] getRequiredParamsNames(){
    return ["exampleTargetURL", "exampleField2"]
}

// This function is called during the script loading to obtain a list of the names of the optional configuration parameters,
// that will be shown in the Session Properties -> Authentication panel for configuration. They can be used
// to input dynamic data into the script, from the user interface (e.g. a login URL, name of POST parameters etc.)
String[] getOptionalParamsNames(){
    return ["exampleField3"]
}

// This function is called during the script loading to obtain a list of the names of the parameters that are required,
// as credentials, for each User configured corresponding to an Authentication using this script
String[] getCredentialsParamsNames(){
    return ["username", "password"]
}

// This optional function is called during the script loading to obtain the logged in indicator.
// NOTE: although optional this function must be implemented along with the function getLoggedOutIndicator().
/*String getLoggedInIndicator() {
	return "LoggedInIndicator"
}*/

// This optional function is called during the script loading to obtain the logged out indicator.
// NOTE: although optional this function must be implemented along with the function getLoggedInIndicator().
/*String getLoggedOutIndicator() {
	return "LoggedOutIndicator"
}*/
