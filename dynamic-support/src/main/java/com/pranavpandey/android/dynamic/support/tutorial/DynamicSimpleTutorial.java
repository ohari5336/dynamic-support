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

package com.pranavpandey.android.dynamic.support.tutorial;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.pranavpandey.android.dynamic.support.tutorial.fragment.DynamicTutorialFragment;

/**
 * A simple tutorial item to display a title, subtitle and description along with an image
 * which can be tinted according to the background color.
 */
public class DynamicSimpleTutorial implements Parcelable,
        DynamicTutorial<DynamicSimpleTutorial, DynamicTutorialFragment> {

    /**
     * Id to uniquely identify this tutorial.
     */
    private @ColorInt int id;

    /**
     * Background color used by this tutorial.
     */
    private @ColorInt int backgroundColor;

    /**
     * Title used by this tutorial.
     */
    private String title;

    /**
     * Subtitle used by this tutorial.
     */
    private String subtitle;

    /**
     * Description used by this tutorial.
     */
    private String description;

    /**
     * Image resource used by this tutorial.
     */
    private @DrawableRes int imageRes;

    /**
     * {@code true} to tint the image according to the background color.
     */
    private boolean tintImage;

    private DynamicTutorialFragment mFragment;

    /**
     * Constructor to initialize an object of this class.
     *
     * @param id The id to uniquely identify this tutorial.
     * @param backgroundColor The background color for this tutorial.
     * @param title The title for this tutorial.
     * @param description The description for this tutorial.
     * @param imageRes The image resource for this tutorial.
     */
    public DynamicSimpleTutorial(int id, @ColorInt int backgroundColor,
            @Nullable String title, @Nullable String description, @DrawableRes int imageRes) {
        this(id, backgroundColor, title, null, description, imageRes);
    }

    /**
     * Constructor to initialize an object of this class.
     *
     * @param id The id to uniquely identify this tutorial.
     * @param backgroundColor The background color for this tutorial.
     * @param title The title for this tutorial.
     * @param subtitle The subtitle for this tutorial.
     * @param description The description for this tutorial.
     * @param imageRes The image resource for this tutorial.
     */
    public DynamicSimpleTutorial(int id, @ColorInt int backgroundColor,
            @Nullable String title, @Nullable String subtitle,
            @Nullable String description, @DrawableRes int imageRes) {
        this(id, backgroundColor, title, subtitle, description, imageRes, false);
    }

    /**
     * Constructor to initialize an object of this class.
     *
     * @param id The id to uniquely identify this tutorial.
     * @param backgroundColor The background color for this tutorial.
     * @param title The title for this tutorial.
     * @param subtitle The subtitle for this tutorial.
     * @param description The description for this tutorial.
     * @param imageRes The image resource for this tutorial.
     * @param tintImage {@code true} to tint the image according to the background color.
     */
    public DynamicSimpleTutorial(int id, @ColorInt int backgroundColor,
            @Nullable String title, @Nullable String subtitle, @Nullable String description,
            @DrawableRes int imageRes, boolean tintImage) {
        this.id = id;
        this.backgroundColor = backgroundColor;
        this.title = title;
        this.subtitle = subtitle;
        this.description = description;
        this.imageRes = imageRes;
        this.tintImage = tintImage;
    }

    /**
     * Parcelable creator to create from parcel.
     */
    public static final Parcelable.Creator<DynamicSimpleTutorial> CREATOR =
            new Parcelable.Creator<DynamicSimpleTutorial>() {
        @Override
        public DynamicSimpleTutorial createFromParcel(Parcel in) {
            return new DynamicSimpleTutorial(in);
        }

        @Override
        public DynamicSimpleTutorial[] newArray(int size) {
            return new DynamicSimpleTutorial[size];
        }
    };

    /**
     * Read {@link DynamicSimpleTutorial} object from the parcel.
     *
     * @param in The parcel to read the values.
     */
    public DynamicSimpleTutorial(Parcel in) {
        this.id = in.readInt();
        this.backgroundColor = in.readInt();
        this.title = in.readString();
        this.subtitle = in.readString();
        this.description = in.readString();
        this.imageRes = in.readInt();
        this.tintImage = in.readByte() != 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(backgroundColor);
        dest.writeString(title);
        dest.writeString(subtitle);
        dest.writeString(description);
        dest.writeInt(imageRes);
        dest.writeByte((byte) (tintImage ? 1 : 0));
    }

    /**
     * Returns the id to uniquely identify this tutorial.
     *
     * @return The id to uniquely identify this tutorial.
     */
    public int getId() {
        return id;
    }

    /**
     * Set the id to uniquely identify this tutorial.
     *
     * @param id The id to be set.
     *
     * @return The {@link DynamicSimpleTutorial} object to allow for chaining of calls to
     *         set methods.
     */
    public DynamicSimpleTutorial setId(int id) {
        this.id = id;

        return this;
    }

    /**
     * Get the background color used by this tutorial.
     *
     * @return The background color used by this tutorial.
     */
    @Override
    public @ColorInt int getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * Set the background color used by this tutorial.
     *
     * @param backgroundColor The background color to be set.
     *
     * @return The {@link DynamicSimpleTutorial} object to allow for chaining of calls to
     *         set methods.
     */
    public DynamicSimpleTutorial setBackgroundColor(@ColorInt int backgroundColor) {
        this.backgroundColor = backgroundColor;

        onBackgroundColorChanged(backgroundColor);
        return this;
    }

    /**
     * Get the title used by this tutorial.
     *
     * @return The title used by this tutorial.
     */
    public @Nullable String getTitle() {
        return title;
    }

    /**
     * Set the title used by this tutorial.
     *
     * @param title The subtitle to be set.
     *
     * @return The {@link DynamicSimpleTutorial} object to allow for chaining of calls to
     *         set methods.
     */
    public DynamicSimpleTutorial setTitle(@Nullable String title) {
        this.title = title;

        return this;
    }

    /**
     * Get the subtitle used by this tutorial.
     *
     * @return The subtitle used by this tutorial.
     */
    public @Nullable String getSubtitle() {
        return subtitle;
    }

    /**
     * Set the subtitle used by this tutorial.
     *
     * @param subtitle The subtitle to be set.
     *
     * @return The {@link DynamicSimpleTutorial} object to allow for chaining of calls to
     *         set methods.
     */
    public DynamicSimpleTutorial setSubtitle(@Nullable String subtitle) {
        this.subtitle = subtitle;

        return this;
    }

    /**
     * Get the description used by this tutorial.
     *
     * @return The description used by this tutorial.
     */
    public @Nullable String getDescription() {
        return description;
    }

    /**
     * Set the description used by this tutorial.
     *
     * @param description The description to be set.
     *
     * @return The {@link DynamicSimpleTutorial} object to allow for chaining of calls to
     *         set methods.
     */
    public DynamicSimpleTutorial setDescription(@Nullable String description) {
        this.description = description;

        return this;
    }

    /**
     * Get the image resource used by this tutorial.
     *
     * @return The image resource used by this tutorial.
     */
    public @DrawableRes int getImageRes() {
        return imageRes;
    }

    /**
     * Set the image resource used by this tutorial.
     *
     * @param imageRes The image resource to be set.
     *
     * @return The {@link DynamicSimpleTutorial} object to allow for chaining of calls to
     *         set methods.
     */
    public DynamicSimpleTutorial setImageRes(@DrawableRes int imageRes) {
        this.imageRes = imageRes;

        return this;
    }

    /**
     * Returns whether to tint the image according to the background color.
     *
     * @return {@code true} to tint the image according to the background color.
     */
    public boolean isTintImage() {
        return tintImage;
    }

    /**
     * Set the image to be tinted or not.
     *
     * @param tintImage {@code true} to tint the image.
     *
     * @return The {@link DynamicSimpleTutorial} object to allow for chaining of calls to
     *         set methods.
     */
    public DynamicSimpleTutorial setTintImage(boolean tintImage) {
        this.tintImage = tintImage;

        return this;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (mFragment != null) {
            mFragment.onPageScrolled(position, positionOffset, positionOffsetPixels);
        }
    }

    @Override
    public void onPageSelected(int position) {
        if (mFragment != null) {
            mFragment.onPageSelected(position);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (mFragment != null) {
            mFragment.onPageScrollStateChanged(state);
        }
    }

    @Override
    public @NonNull DynamicSimpleTutorial getTutorial() {
        return this;
    }

    @Override
    public @NonNull DynamicTutorialFragment createTutorial() {
        mFragment = DynamicTutorialFragment.newInstance(this);

        return mFragment;
    }

    @Override
    public int getTutorialId() {
        return getId();
    }

    @Override
    public void onBackgroundColorChanged(int color) {
        if (mFragment != null) {
            mFragment.onBackgroundColorChanged(color);
        }
    }

    @Override
    public void onSetPadding(int left, int top, int right, int bottom) {
        if (mFragment != null) {
            mFragment.onSetPadding(left, top, right, bottom);
        }
    }
}
