package com.petros.efthumiou.dailypulse

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform