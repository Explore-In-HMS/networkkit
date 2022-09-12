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
package com.hms.converter.gson

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.huawei.hms.network.httpclient.RequestBody
import com.huawei.hms.network.httpclient.ResponseBody
import com.huawei.hms.network.restclient.Converter
import com.huawei.hms.network.restclient.RestClient
import java.lang.reflect.Type

class GsonConverterFactory(val gson: Gson) : Converter.Factory() {

    companion object {
        @JvmOverloads
        fun create(gson: Gson? = Gson()): GsonConverterFactory {
            if (gson == null) throw NullPointerException("gson == null")
            return GsonConverterFactory(gson)
        }
    }

    override fun responseBodyConverter(
        type: Type,
        parameterAnnotations: Array<out Annotation>,
        restClient: RestClient
    ): Converter<ResponseBody, *> {
        val adapter = gson.getAdapter(TypeToken.get(type))
        return GsonResponseBodyConverter(gson, adapter)
    }

    override fun requestBodyConverter(
        type: Type,
        parameterAnnotations: Array<out Annotation>,
        methodAnnotations: Array<out Annotation>,
        restClient: RestClient
    ): Converter<*, RequestBody> {
        val adapter = gson.getAdapter(TypeToken.get(type))
        return GsonRequestBodyConverter(gson, adapter)
    }

    override fun stringConverter(
        type: Type,
        parameterAnnotations: Array<out Annotation>?,
        restClient: RestClient
    ): Converter<*, String> {
        return super.stringConverter(type, parameterAnnotations, restClient)
    }

}
