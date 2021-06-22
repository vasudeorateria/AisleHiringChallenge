package com.kjstudios.aislehiringchallenge.data.model.kotlin

data class Preference(
    val answer_id: Int,
    val id: Int,
    val preference_question: PreferenceQuestion,
    val value: Int
)