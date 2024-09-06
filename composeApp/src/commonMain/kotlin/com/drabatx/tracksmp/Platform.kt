package com.drabatx.tracksmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform