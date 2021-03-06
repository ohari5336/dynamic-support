package com.pranavpandey.android.dynamic.support.widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * A {@link DynamicListView} that fully expands vertically according to its content height
 * which is suitable to show it as a nested view.
 */
public class DynamicNestedListView extends DynamicListView {

    public DynamicNestedListView(@NonNull Context context) {
        super(context);
    }

    public DynamicNestedListView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DynamicNestedListView(@NonNull Context context,
            @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST));
    }
}
