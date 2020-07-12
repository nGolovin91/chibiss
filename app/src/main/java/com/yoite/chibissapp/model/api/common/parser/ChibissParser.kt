package com.yoite.chibissapp.model.api.common.parser

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import javax.inject.Inject


class ChibissParser @Inject constructor() : BaseParserImpl() {

    override val defaultParser: Gson =
        GsonBuilder()
            .create()

    override val jsonParser = JsonParser()

}