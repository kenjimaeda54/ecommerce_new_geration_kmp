package com.ecommercenewgeration.util

import kotlinx.coroutines.CoroutineScope

expect open class CoroutineViewModel() {

    val scope: CoroutineScope

}