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
package com.hms.networkkit.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Route(
    @SerializedName("paths") @Expose val paths: ArrayList<Path>,
    @SerializedName("optimizedWaypoints") @Expose val optimizedWaypoints: ArrayList<Int>,
    @SerializedName("hasRestrictedRoad") @Expose val hasRestrictedRoad: Int,
    @SerializedName("dstInRestrictedArea") @Expose val dstInRestrictedArea: Int,
    @SerializedName("crossCountry") @Expose val crossCountry: Int,
    @SerializedName("crossMultiCountries") @Expose val crossMultiCountries: Int,
    @SerializedName("hasRoughRoad") @Expose val hasRoughRoad: Int,
    @SerializedName("dstInDiffTimeZone") @Expose val dstInDiffTimeZone: Int,
    @SerializedName("hasFerry") @Expose val hasFerry: Int,
    @SerializedName("hasTrafficLight") @Expose val hasTrafficLight: Int,
    @SerializedName("hasTolls") @Expose val hasTolls: Int,
    @SerializedName("trafficLightNum") @Expose val trafficLightNum: Int
)