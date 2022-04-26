package com.example.composebegins.screen_cast

import androidx.compose.runtime.State

interface MutableState<T> : State<T> {
    override var value: T
}