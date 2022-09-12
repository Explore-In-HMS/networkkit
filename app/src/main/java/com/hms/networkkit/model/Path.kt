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

data class Path(
    @SerializedName("steps") @Expose val steps: ArrayList<Step>,
    @SerializedName("distance") @Expose val distance: Double,
    @SerializedName("distanceText") @Expose val distanceText: String,
    @SerializedName("duration") @Expose val duration: Double,
    @SerializedName("durationText") @Expose val durationText: String,
    @SerializedName("durationInTraffic") @Expose val durationInTraffic: Double,
    @SerializedName("durationInTrafficText") @Expose val durationInTrafficText: String,
    @SerializedName("startLocation") @Expose val startLocation: Coordinate,
    @SerializedName("startAddress") @Expose val startAddress: String,
    @SerializedName("endLocation") @Expose val endLocation: Coordinate,
    @SerializedName("endAddress") @Expose val endAddress: String,
    @SerializedName("viaWaypoints") @Expose val viaWaypoints: ArrayList<ViaWayPoints>
)