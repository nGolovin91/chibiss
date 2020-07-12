package com.yoite.chibissapp.repository.hits

import com.yoite.chibissapp.model.api.common.map
import com.yoite.chibissapp.model.api.hits.HitsApi
import com.yoite.chibissapp.model.mapper.mapToHitModel
import com.yoite.chibissapp.ui.hits.di.HitsScope
import javax.inject.Inject


@HitsScope
class HitsRepositoryImpl @Inject constructor(
    private val hitsApi: HitsApi
) : HitsRepository {

    override suspend fun getHitsList() =
        hitsApi.getHitsList().map { list -> list.map { it.mapToHitModel() } }

}