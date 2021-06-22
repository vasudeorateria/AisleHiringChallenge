package com.kjstudios.aislehiringchallenge.data.model.kotlin

data class Likes(
    val can_see_profile: Boolean,
    val likes_received_count: Int,
    val profiles: List<ProfileX>
)