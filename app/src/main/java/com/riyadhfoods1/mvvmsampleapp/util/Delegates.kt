package com.riyadhfoods1.mvvmsampleapp.util

import kotlinx.coroutines.*

fun<T> lazyDeferred(blocl: suspend CoroutineScope.() -> T):Lazy<Deferred<T>>{
    return lazy {
   GlobalScope.async(start = CoroutineStart.LAZY) {
       blocl.invoke(this)
   }
    }
}