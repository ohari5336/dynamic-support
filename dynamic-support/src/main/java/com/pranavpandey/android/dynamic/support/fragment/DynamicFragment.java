/*
 * Copyright 2018-2020 Pranav Pandey
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

package com.pranavpandey.android.dynamic.support.fragment;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pranavpandey.android.dynamic.support.activity.DynamicActivity;
import com.pranavpandey.android.dynamic.support.activity.DynamicDrawerActivity;
import com.pranavpandey.android.dynamic.support.activity.DynamicSystemActivity;
import com.pranavpandey.android.dynamic.support.listener.DynamicTransitionListener;
import com.pranavpandey.android.dynamic.support.utils.DynamicResourceUtils;
import com.pranavpandey.android.dynamic.utils.DynamicSdkUtils;

/**
 * Base fragment class to provide basic functionality and to work with the {@link DynamicActivity}.
 * <p>Extend this fragment to add more functionality according to the need
 */
@SuppressWarnings("ConstantConditions")
public class DynamicFragment extends Fragment implements
        SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }

    /**
     * Returns the dynamic transition listener for the activity.
     *
     * @return The dynamic transition listener for the activity.
     */
    protected @Nullable DynamicTransitionListener getDynamicTransitionListener() {
        return null;
    }

    /**
     * Get boolean to control the shared preferences listener.
     *
     * @return {@code true} to set a shared preferences listener.
     *          <p>The default values is {@code false}.
     */
    protected boolean setSharedPreferenceChangeListener() {
        return false;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (setSharedPreferenceChangeListener() && getContext() != null) {
            PreferenceManager.getDefaultSharedPreferences(requireContext())
                    .registerOnSharedPreferenceChangeListener(this);
        }

        if (requireActivity() instanceof DynamicSystemActivity) {
            ((DynamicSystemActivity) requireActivity()).setDynamicTransitionListener(
                    getDynamicTransitionListener());
        }
    }

    @Override
    public void onPause() {
        setHasOptionsMenu(false);

        if (setSharedPreferenceChangeListener() && getContext() != null) {
            PreferenceManager.getDefaultSharedPreferences(requireContext())
                    .unregisterOnSharedPreferenceChangeListener(this);
        }
        super.onPause();
    }

    /**
     * Returns the title used by the parent activity.
     *
     * @return The title used by the parent activity.
     */
    protected @Nullable CharSequence getTitle() {
        if (isSupportActionBar()) {
            return ((AppCompatActivity) requireActivity()).getSupportActionBar().getTitle();
        }

        return null;
    }

    /**
     * Returns the subtitle used by the parent activity.
     *
     * @return The subtitle used by the parent activity.
     */
    protected @Nullable CharSequence getSubtitle() {
        if (isSupportActionBar()) {
            return ((AppCompatActivity) requireActivity()).getSupportActionBar().getSubtitle();
        }

        return null;
    }

    /**
     * Returns the bottom navigation view id to make its menu item checked.
     *
     * @return The bottom navigation view id to make its menu item checked.
     *
     * @see #getCheckedMenuItemId()
     */
    protected @IdRes int getBottomNavigationViewId() {
        return DynamicResourceUtils.ADS_DEFAULT_RESOURCE_ID;
    }

    /**
     * Returns the menu item id to make it checked.
     *
     * @return The menu item id to make it checked.
     */
    protected @IdRes int getCheckedMenuItemId() {
        return DynamicResourceUtils.ADS_DEFAULT_RESOURCE_ID;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity() == null) {
            return;
        }

        if (isSupportActionBar()) {
            ((AppCompatActivity) requireActivity()).getSupportActionBar().setTitle(getTitle());
            ((AppCompatActivity) requireActivity()).getSupportActionBar().setSubtitle(getSubtitle());
        }

        if (getCheckedMenuItemId() != DynamicResourceUtils.ADS_DEFAULT_RESOURCE_ID) {
            if (requireActivity().findViewById(getBottomNavigationViewId()) != null) {
                ((BottomNavigationView) requireActivity().findViewById(
                        getBottomNavigationViewId())).setSelectedItemId(getCheckedMenuItemId());
            }

            if (requireActivity() instanceof DynamicDrawerActivity) {
                ((DynamicDrawerActivity) requireActivity()).getNavigationView()
                        .setCheckedItem(getCheckedMenuItemId());
            }
        }
    }

    /**
     * Checks whether the parent activity is a app compat activity.
     *
     * @return {@code true} if the parent activity is an {@link AppCompatActivity}.
     */
    public boolean isAppCompatActivity() {
        return getActivity() != null && requireActivity() instanceof AppCompatActivity;
    }

    /**
     * Checks whether the support action bar is applied to the parent activity.
     *
     * @return {@code true} if the support action bar is not {@code null}.
     */
    public boolean isSupportActionBar() {
        return isAppCompatActivity() &&
                ((AppCompatActivity) requireActivity()).getSupportActionBar() != null;
    }

    /**
     * Retrieves a parcelable from the fragment arguments associated with the supplied key.
     *
     * @param key The key to be retrieved.
     *
     * @return The parcelable from the fragment arguments.
     *
     * @see Fragment#getArguments()
     */
    public @Nullable <T extends Parcelable> T getParcelableFromArguments(@Nullable String key) {
        if (getArguments() == null) {
            return null;
        }

        try {
            return requireArguments().getParcelable(key);
        } catch (Exception ignored) {
            return null;
        }
    }

    /**
     * Retrieves a string from the fragment arguments associated with the supplied key.
     *
     * @param key The key to be retrieved.
     *
     * @return The string from the fragment arguments.
     *
     * @see Fragment#getArguments()
     */
    public @Nullable String getStringFromArguments(@Nullable String key) {
        if (getArguments() == null) {
            return null;
        }

        try {
            return requireArguments().getString(key);
        } catch (Exception ignored) {
            return null;
        }
    }

    /**
     * Retrieves a boolean from the fragment arguments associated with the supplied key.
     *
     * @param key The key to be retrieved.
     * @param defaultValue The default value.
     *
     * @return The boolean from the fragment arguments.
     *
     * @see Fragment#getArguments()
     */
    public boolean getBooleanFromArguments(@Nullable String key, boolean defaultValue) {
        if (getArguments() == null) {
            return defaultValue;
        }

        return requireArguments().getBoolean(key, defaultValue);
    }

    /**
     * Set result for the parent activity to notify the requester about the action.
     *
     * @param resultCode The result code for the activity.
     * @param intent The result intent to provide any data as an extra.
     * @param finish {@code true} to finish the activity.
     */
    protected void setResult(int resultCode, @Nullable Intent intent, boolean finish) {
        if (getActivity() != null) {
            if (intent != null) {
                requireActivity().setResult(resultCode, intent);
            } else {
                requireActivity().setResult(resultCode);
            }

            if (finish) {
                finishActivity();
            }
        }
    }

    /**
     * Set result for the parent activity to notify the requester about the action.
     *
     * @param resultCode The result code for the activity.
     * @param intent The result intent to provide any data as an extra.
     */
    protected void setResult(int resultCode, @Nullable Intent intent) {
        setResult(resultCode, intent, true);
    }

    /**
     * Set result for the parent activity to notify the requester about the action.
     *
     * @param resultCode The result code for the activity.
     * @param finish {@code true} to finish the activity.
     */
    protected void setResult(int resultCode, boolean finish) {
        setResult(resultCode, null, finish);
    }

    /**
     * Set result for the parent activity to notify the requester about the action.
     *
     * @param resultCode The result code for the activity.
     */
    protected void setResult(int resultCode) {
        setResult(resultCode, null, true);
    }

    /**
     * Finish the parent activity by calling {@link Activity#finish()}.
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    protected void finishActivity() {
        if (getActivity() instanceof DynamicSystemActivity) {
            ((DynamicSystemActivity) requireActivity()).finishActivity();
        } else if (getActivity() != null && !requireActivity().isFinishing()) {
            if (DynamicSdkUtils.is21()
                    && (requireActivity().getWindow().getSharedElementEnterTransition() != null
                    || requireActivity().getWindow().getSharedElementReturnTransition() != null)) {
                requireActivity().supportFinishAfterTransition();
            } else {
                requireActivity().finish();
            }
        }
    }

    /**
     * Get the parent activity.
     *
     * @return The parent activity as the instance of {@link DynamicActivity}.
     */
    public @NonNull DynamicActivity getDynamicActivity() {
        return (DynamicActivity) requireActivity();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) { }
}
