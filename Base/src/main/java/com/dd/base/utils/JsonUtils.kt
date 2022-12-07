package com.dd.base.utils

import kotlinx.serialization.json.Json

class JsonUtils {

    companion object{
       val jsonDecoder by lazy {
           Json {
               coerceInputValues = true
               ignoreUnknownKeys = true
               explicitNulls = false
           }
       }
    }

}