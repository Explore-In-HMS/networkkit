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

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.hms.networkkit.adapter.StepAdapter
import com.hms.networkkit.databinding.ActivityMainBinding
import com.hms.networkkit.model.Coordinate
import com.hms.networkkit.model.DirectionRequest
import com.hms.networkkit.model.DirectionResponse
import com.huawei.hms.network.httpclient.Callback
import com.huawei.hms.network.httpclient.Response
import com.huawei.hms.network.httpclient.Submit
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val apiClient by lazy {
        ApiClient.getApiClient().create(SampleService::class.java)
    }

    private val stepAdapter by lazy { StepAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        binding.recyclerViewStep.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = stepAdapter
        }

        binding.btnGetRoute.setOnClickListener {
            val originLat = binding.etOriginLat.text.toString()
            val originLng = binding.etOriginLng.text.toString()

            val destinationLat = binding.etDestinationLat.text.toString()
            val destinationLng = binding.etDestinationLng.text.toString()

            val originCoordinate = Coordinate(originLat.toDouble(), originLng.toDouble())
            val destinationCoordinate =
                Coordinate(destinationLat.toDouble(), destinationLng.toDouble())

            getRouteAndShowOnScreen(originCoordinate, destinationCoordinate)
        }

        setContentView(view)
    }


    private fun getRouteAndShowOnScreen(
        originCoordinate: Coordinate,
        destinationCoordinate: Coordinate
    ) {

        val directionRequest = DirectionRequest(originCoordinate, destinationCoordinate)

        try {
            apiClient.getRoute(directionRequest).enqueue(object : Callback<DirectionResponse>() {
                override fun onResponse(
                    p0: Submit<DirectionResponse>,
                    response: Response<DirectionResponse>
                ) {
                    if (response.isSuccessful) {
                        runOnUiThread {
                            val steps = response.body.routes[0].paths[0].steps
                            stepAdapter.submitList(steps)
                        }
                    } else {
                        Log.d(
                            TAG,
                            "Response unsuccessful --- Response Code:${response.code} --- Response Body:${
                                response.errorBody.charStream().readText()
                            }"
                        )
                    }
                }

                override fun onFailure(p0: Submit<DirectionResponse>, throwable: Throwable?) {
                    Log.e(TAG, "onFailure message: ${throwable?.message.toString()}")
                }

            })
        } catch (exception: IOException) {
            Log.e(TAG, "Exception: ${exception.message}}")
        }

    }

    companion object {
        private const val TAG = "MainActivity"
    }

}