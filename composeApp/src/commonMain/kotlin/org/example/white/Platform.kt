package org.example.white

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform