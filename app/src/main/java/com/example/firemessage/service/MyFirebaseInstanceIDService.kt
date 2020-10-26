package com.example.firemessage.service

import com.example.firemessage.util.FIrestoreUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService
import java.lang.NullPointerException

class MyFirebaseInstanceIDService : FirebaseInstanceIdService() {

    override fun onTokenRefresh() {
        val newRegistrationToken = FirebaseInstanceId.getInstance().token

        if (FirebaseAuth.getInstance().currentUser != null)
            addTokenToFirestore(newRegistrationToken)
    }

    companion object {
        fun addTokenToFirestore(newRegistrationToken: String?){
            if (newRegistrationToken == null) throw NullPointerException("FCM token is null")

            FIrestoreUtil.getFCMRegistrationTokens { tokens ->
                if (tokens.contains(newRegistrationToken))
                    return@getFCMRegistrationTokens
                tokens.add(newRegistrationToken)
                FIrestoreUtil.setFCMRegistrationTokens(tokens)
            }
        }
    }
}