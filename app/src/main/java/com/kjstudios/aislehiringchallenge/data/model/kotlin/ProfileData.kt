package com.kjstudios.aislehiringchallenge.data.model.kotlin

data class ProfileData(
    val invitation_type: String,
    val preferences: List<PreferenceX>,
    val question: String
)