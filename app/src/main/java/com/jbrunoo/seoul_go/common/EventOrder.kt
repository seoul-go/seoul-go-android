package com.jbrunoo.seoul_go.common

sealed class EventOrder {
    object ByRegistration: EventOrder()
    object ByDeadline: EventOrder()
    object ByCost: EventOrder()
//    object ByDistance: EventOrder()
}