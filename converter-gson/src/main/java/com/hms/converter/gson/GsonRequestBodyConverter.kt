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
import com.google.gson.TypeAdapter
import com.huawei.hms.network.base.common.MediaType
import com.huawei.hms.network.base.common.RequestBodyProviders
import com.huawei.hms.network.base.common.trans.ByteString
import com.huawei.hms.network.httpclient.RequestBody
import com.huawei.hms.network.restclient.Converter
import okio.Buffer
import java.io.IOException
import java.io.OutputStreamWriter
import java.io.Writer
import java.nio.charset.Charset

internal class GsonRequestBodyConverter<T>(
    private val gson: Gson,
    private val adapter: TypeAdapter<T>
) : Converter<T, RequestBody>() {

    companion object {
        private val MEDIA_TYPE: MediaType = MediaType.parse("application/json; charset=utf-8")
        private val UTF_8 = Charset.forName("UTF-8")
    }

    @Throws(IOException::class)
    override fun convert(value: T): RequestBody {
        val buffer = Buffer()
        val writer: Writer = OutputStreamWriter(buffer.outputStream(), UTF_8)
        val jsonWriter = gson.newJsonWriter(writer)
        adapter.write(jsonWriter, value)
        jsonWriter.close()
        val transferredContent = ByteString(buffer.readByteArray())
        return RequestBodyProviders.create(MEDIA_TYPE, transferredContent)
    }

}