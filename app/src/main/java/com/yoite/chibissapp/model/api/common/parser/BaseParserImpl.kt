package com.yoite.chibissapp.model.api.common.parser

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.io.IOException


abstract class BaseParserImpl : Parser {

    override fun <T> parse(jsonString: String, clazz: Class<T>): T = defaultParser.fromJson(jsonString, clazz)
    override fun <T> parse(jsonObject: JsonObject, clazz: Class<T>): T = defaultParser.fromJson(jsonObject, clazz)
    override fun <T> parse(jsonObject: JsonElement, clazz: Class<T>): T = defaultParser.fromJson(jsonObject, clazz)

    override fun <T> parseList(jsonArray: JsonArray, clazz: Class<Array<T>>): List<T> =
        defaultParser.fromJson(jsonArray, clazz).toList()

    override fun <T> parseList(jsonArrayString: String, clazz: Class<Array<T>>): List<T> {
        val jsonArray = jsonParser.parse(jsonArrayString).asJsonArray

        return parseList(jsonArray, clazz)
    }

    override fun isJsonValid(json: String): Boolean = try {
        defaultParser.getAdapter(JsonElement::class.java).fromJson(json)
        true
    } catch (e: IOException) {
        false
    }

    override fun parseObjectToString(obj: Any): String = defaultParser.toJson(obj)

}