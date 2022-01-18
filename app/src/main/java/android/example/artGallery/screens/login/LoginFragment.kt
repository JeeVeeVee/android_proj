package android.example.artGallery.screens.login

import android.content.Intent
import android.example.artGallery.MainActivity
import android.example.artGallery.R
import android.example.artGallery.databinding.FragmentLoginBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.auth0.android.Auth0
import com.auth0.android.authentication.AuthenticationAPIClient
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.callback.Callback
import com.auth0.android.provider.WebAuthProvider
import com.auth0.android.result.Credentials
import com.auth0.android.result.UserProfile
import timber.log.Timber

class LoginFragment: Fragment() {

    private lateinit var account : Auth0
    private var loggedIn = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding: FragmentLoginBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        binding.loginButton.setOnClickListener{
            account = Auth0("norfRK0hpwDEeeoioi9l8xURgp33naXl", "https://dev-xzn5a0n9.eu.auth0.com")
            loginWithBrowser()
            checkIfToken()
        }

        return binding.root

    }

    private fun checkIfToken(){
        val token = CredentialsManager.getAccessToken(requireContext())
        if(token != null){
            //checking if the token works...
            showUserProfile(token)
        }
        else {
            Toast.makeText(context, "Token doesn't exist", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loginWithBrowser() {
        // Setup the WebAuthProvider, using the custom scheme and scope.
        WebAuthProvider.login(account)
            .withScheme("demo")
            .withScope("openid profile email")
            // Launch the authentication passing the callback where the results will be received
            .start(requireContext(), object : Callback<Credentials, AuthenticationException> {
                // Called when there is an authentication failure
                override fun onFailure(exception: AuthenticationException) {
                    loggedIn = false
                }

                // Called when authentication completed successfully
                override fun onSuccess(credentials: Credentials) {
                    // Get the access token from the credentials object.
                    // This can be used to call APIs
                    val accessToken = credentials.accessToken
                    Toast.makeText(context, accessToken, Toast.LENGTH_SHORT).show()

                    CredentialsManager.saveCredentials(requireContext(), credentials)
                    checkIfToken()
                    findNavController().navigate(R.id.action_loginFragment_to_homeKunstFragment)
                }
            })
    }

    private fun logout() {
        WebAuthProvider.logout(account)
            .withScheme("demo")
            .start(requireContext(), object: Callback<Void?, AuthenticationException> {
                override fun onSuccess(payload: Void?) {
                    Toast.makeText(context, "logout OK", Toast.LENGTH_SHORT).show()
                    loggedIn = false
                }

                override fun onFailure(error: AuthenticationException) {
                    Toast.makeText(context, "logout NOK", Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun showUserProfile(accessToken: String){
        var client = AuthenticationAPIClient(account)

        // With the access token, call `userInfo` and get the profile from Auth0.
        client.userInfo(accessToken)
            .start(object : Callback<UserProfile, AuthenticationException> {
                override fun onFailure(exception: AuthenticationException) {
                    Timber.i(exception.stackTraceToString())
                    loggedIn = false
                }

                override fun onSuccess(profile: UserProfile) {
                    // We have the user's profile!
                    Timber.i("SUCCESS! got the user profile")
                    val email = profile.email
                    val name = profile.name
                    loggedIn = true
                }
            })

    }
}