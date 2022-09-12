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
package com.hms.networkkit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hms.networkkit.databinding.ItemStepBinding
import com.hms.networkkit.model.RouteAction
import com.hms.networkkit.model.Step

class StepAdapter : ListAdapter<Step, StepAdapter.StepViewHolder>(COMPARATOR) {

    class StepViewHolder(private val binding: ItemStepBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindStep(step: Step) {

            binding.ivStepAction.setImageResource(RouteAction.getIconByAction(step.action))
            val distanceAndDurationText = "${step.distanceText} - ${step.durationText}"

            binding.tvDistanceAndTime.text = distanceAndDurationText
            binding.tvInstruction.text = step.instruction.ifEmpty { "-" }
            binding.tvRoadName.text = step.roadName.ifEmpty { "-" }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepViewHolder {
        val binding = ItemStepBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StepViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StepViewHolder, position: Int) {
        val step = getItem(position)
        holder.bindStep(step)
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Step>() {
            override fun areItemsTheSame(oldItem: Step, newItem: Step): Boolean {
                return oldItem.roadName == newItem.roadName
            }

            override fun areContentsTheSame(oldItem: Step, newItem: Step): Boolean {
                return oldItem == newItem
            }

        }
    }

}