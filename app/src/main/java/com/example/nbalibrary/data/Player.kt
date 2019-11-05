package com.example.nbalibrary.data

data class Player (
    var id: String? = null,
    var first_name: String?,
    var last_name: String?,
    var born: String?,
    var hometown: String?,
    var url: String?,
    var small_headshot_url: String?,
    var large_headshot_url: String?,
    var team_brand_url: String?,
    var background_color: String?,
    var secondary_color: String?,
    var number: String?,
    var apg: String?,
    var ppg: String?,
    var rpg: String?,
    var team: String?,
    var last_season: String?
) {}

