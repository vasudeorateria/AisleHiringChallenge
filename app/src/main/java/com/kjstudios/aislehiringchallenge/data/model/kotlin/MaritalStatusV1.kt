package com.kjstudios.aislehiringchallenge.data.model.kotlin

data class MaritalStatusV1(
    val id: Int,
    val is_open_to_all: Boolean,
    val is_selected: Boolean,
    val name: String,
    val preference_only: Boolean
)