package com.example.sportsclubai

import com.google.firebase.appcheck.FirebaseAppCheck
import com.google.firebase.appcheck.debug.DebugAppCheckProviderFactory

fun installAppCheckProvider() {
    FirebaseAppCheck.getInstance()
        .installAppCheckProviderFactory(
            DebugAppCheckProviderFactory.getInstance()
        )
}