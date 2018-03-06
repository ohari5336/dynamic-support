/*
 * Copyright 2018 Pranav Pandey
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pranavpandey.android.dynamic.support.sample.binder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.pranavpandey.android.dynamic.support.recyclerview.adapter.DynamicBinderAdapter;
import com.pranavpandey.android.dynamic.support.recyclerview.binder.DynamicInfoBigBinder;
import com.pranavpandey.android.dynamic.support.recyclerview.binder.DynamicRecyclerViewBinder;
import com.pranavpandey.android.dynamic.support.sample.R;

/**
 * A recycler view binder to display licenses by using
 * {@link DynamicRecyclerViewBinder}.
 */
public class LicenseBinder extends DynamicInfoBigBinder {

    public LicenseBinder(@NonNull DynamicBinderAdapter dynamicBinderAdapter) {
        super(dynamicBinderAdapter);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_license_card, parent, false));
    }
}
