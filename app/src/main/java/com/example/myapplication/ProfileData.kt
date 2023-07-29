package com.example.myapplication

data class ProfileData(
    val invitation_type: String,
    val preferences: List<PreferenceX>,
    val question: String
)