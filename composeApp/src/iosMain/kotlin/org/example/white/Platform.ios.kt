package org.example.white

import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = "IOS"
}

actual fun getPlatform(): Platform = IOSPlatform()