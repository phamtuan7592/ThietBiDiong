package com.example.th5.googlesigin

import android.content.Context
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.tasks.await

class GoogleSigInClient(
    private val context: Context,
) {

    private val tag = "GoogleSigInClient: "

    private  val credentialManager = CredentialManager.create(context)
    private val firebaseAuth = FirebaseAuth.getInstance()

    fun isSignIn(): Boolean {
        if(firebaseAuth.currentUser !=null){
            println(tag +"already signed in")
            return true
        }

        return false
    }

   suspend fun signIn() : Boolean {
        if(isSignIn()){
            return true
        }
       try {

           val  result = buildCredentialRequest()
           return handleSignIn(result)

       } catch (e: Exception){
            e.printStackTrace()
           if (e is CancellationException) throw e

           println(tag + "sigIn error: ${e.message}")
           return false
       }
    }

    private suspend fun handleSignIn(result: GetCredentialResponse) : Boolean{
        val credential = result.credential

        if(
            credential is CustomCredential &&
            credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_SIWG_CREDENTIAL
        ){

            try {

                val tokenCredentail = GoogleIdTokenCredential.createFrom(credential.data)

                println(tag +"name: ${tokenCredentail.displayName}")
                println(tag +"name: ${tokenCredentail.id}")
                println(tag +"name: ${tokenCredentail.profilePictureUri}")

                val authCredentail = GoogleAuthProvider.getCredential(
                    tokenCredentail.idToken, null
                )
                val authResult = firebaseAuth.signInWithCredential(authCredentail).await()

                return authResult.user != null

            } catch (e : GoogleIdTokenParsingException){
                println(tag + "GoogleIdTokenParsingException: ${e.message}")
                return false
            }
        } else{
            println(tag + "credential is not GoogleIdTokenCredential")
            return false
        }
    }

    private suspend fun buildCredentialRequest() : GetCredentialResponse{
        val request = GetCredentialRequest.Builder()
            .addCredentialOption(
                GetGoogleIdOption.Builder()
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId(
                        "748592648772-qm4cm092cjgm987h6gqjmi791asemo8o.apps.googleusercontent.com"
                    )
                    .setAutoSelectEnabled(false)
                    .build()
            )
            .build()

        return credentialManager.getCredential(
            request = request, context = context
        )
    }

    suspend fun signOut(){
        credentialManager.clearCredentialState(
            ClearCredentialStateRequest()
        )
        firebaseAuth.signOut()
    }
}