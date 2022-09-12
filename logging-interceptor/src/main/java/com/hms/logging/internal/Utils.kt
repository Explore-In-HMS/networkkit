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
package com.hms.logging.internal

import com.huawei.hms.network.base.common.Headers

internal object Utils {

    private const val HTTP_CONTINUE = 100
    private const val HTTP_NO_CONTENT = 204
    private const val HTTP_NOT_MODIFIED = 304

    fun isPromisesBody(requestMethod: String, responseCode: Int, headers: Headers): Boolean {

        if (requestMethod == "HEAD") {
            return false
        }

        if ((responseCode < HTTP_CONTINUE || responseCode >= 200) &&
            responseCode != HTTP_NO_CONTENT &&
            responseCode != HTTP_NOT_MODIFIED
        ) {
            return true
        }

        val selectedHeader = headers.get("Transfer-Encoding")
        if (headers.size().toLong() != -1L ||
            "chunked".equals(selectedHeader, true)
        ) {
            return true
        }

        return false
    }

}