package com.yoite.chibissapp.model.api.common.parser

import com.google.gson.*


interface Parser {

    val defaultParser: Gson
    val jsonParser: JsonParser

    fun <T> parse(jsonString: String, clazz: Class<T>): T

    fun <T> parse(jsonObject: JsonObject, clazz: Class<T>): T

    fun <T> parse(jsonObject: JsonElement, clazz: Class<T>): T

    fun <T> parseList(jsonArray: JsonArray, clazz: Class<Array<T>>): List<T>
    fun <T> parseList(jsonArrayString: String, clazz: Class<Array<T>>): List<T>

    fun isJsonValid(json: String): Boolean

    fun parseObjectToString(obj: Any): String

}