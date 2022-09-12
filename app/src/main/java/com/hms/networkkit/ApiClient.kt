/*
 * Copyright 2022. Explore in HMS. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.hms.networkkit

import com.google.gson.Gson
import com.hms.converter.gson.GsonConverterFactory
import com.hms.logging.HttpLoggingInterceptor
import com.huawei.hms.network.httpclient.HttpClient
import com.huawei.hms.network.restclient.RestClient

const val BASE_URL = "https://mapapi.cloud.huawei.com/mapApi/v1/routeService/"

class ApiClient {

    companion object {
        private var restClient: RestClient? = null

        fun getApiClient(): RestClient {
            val httpClient = HttpClient.Builder()
                .callTimeout(10000)
                .connectTimeout(10000)
                .writeTimeout(10000)
                .readTimeout(30000)
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .build()

            if (restClient == null) {
                restClient = RestClient.Builder()
                    .baseUrl(BASE_URL)
                    .httpClient(httpClient)
                    .addConverterFactory(GsonConverterFactory.create(Gson()))
                    .build()
            }
            return restClient!!
        }
    }

}