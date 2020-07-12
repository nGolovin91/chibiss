package com.yoite.chibissapp.model.api.common.services

import java.util.HashMap
import javax.inject.Inject


class ServiceProvider @Inject internal constructor() {
    private var apiServiceMap: MutableMap<String, Any?>? = null
    private val apiServices: MutableMap<String, Any?>
        get() {
            if (apiServiceMap == null) {
                apiServiceMap = HashMap()
            }
            return apiServiceMap!!
        }

    /**
     * Метод для создания / получения объекта типа `Service`
     *
     * @param className    идентификатор класса
     * @param serviceClass объект типа `Service`
     * @param factory      абстрактная факбрика для создания объекта
     * @param <Service>    параметризованный тип `Service`
     * @return созданный / закешированный объект типа `Service`
    </Service> */
    fun <Service> getService(
        className: String,
        serviceClass: Class<Service>,
        factory: () -> Service
    ): Service {
        val service: Service
        if (!apiServices.containsKey(className)) {
            service = factory()
            apiServices[className] = service
        } else {
            service = serviceClass.cast(apiServices[className])!!
        }
        return service
    }

    fun destroyAllServices() {
        apiServices.clear()
    }
}