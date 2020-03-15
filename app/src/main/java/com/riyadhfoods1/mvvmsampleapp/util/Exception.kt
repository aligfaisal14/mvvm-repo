package com.riyadhfoods1.mvvmsampleapp.util

import java.io.IOException

class ApiException(message:String):IOException(message)
class NoInternetException(message: String):IOException(message)