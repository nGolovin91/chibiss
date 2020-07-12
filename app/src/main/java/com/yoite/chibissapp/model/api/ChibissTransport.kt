package com.yoite.chibissapp.model.api

import com.yoite.chibissapp.model.api.hits.HitsApi
import com.yoite.chibissapp.model.api.restourant.RestaurantApi
import com.yoite.chibissapp.model.api.reviews.ReviewsApi


interface ChibissTransport : RestaurantApi, ReviewsApi, HitsApi