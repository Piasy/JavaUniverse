/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Piasy
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.github.piasy.java_universe.android;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.github.piasy.java_universe.GuiWrapper;
import com.github.piasy.java_universe.TextUtils;
import com.github.piasy.java_universe.Window;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by Piasy{github.com/Piasy} on 08/11/2017.
 */

public class AndroidGuiWrapper implements GuiWrapper {
    private static final int[] COLORS = new int[] {
            Color.RED, Color.GREEN, Color.BLUE
    };

    private final ViewGroup mContainer;

    private int mContainerWidth;
    private int mContainerHeight;

    public AndroidGuiWrapper(final ViewGroup container) {
        mContainer = container;
    }

    @Override
    public void createView(final Window window) {
        if (mContainerWidth == 0) {
            mContainerWidth = mContainer.getWidth();
            mContainerHeight = mContainer.getHeight();
        }

        TextView textView = new TextView(mContainer.getContext());

        textView.setText(window.getUid());
        textView.setGravity(Gravity.CENTER);
        textView.setBackgroundColor(COLORS[mContainer.getChildCount() % COLORS.length]);

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                getSize(mContainerWidth, window.getWidth()),
                getSize(mContainerHeight, window.getHeight()));
        params.leftMargin = getSize(mContainerWidth, window.getLeft());
        params.topMargin = getSize(mContainerHeight, window.getTop());
        mContainer.addView(textView, params);

        textView.setTag(window);
    }

    @Override
    public void swapView(final Window alice, final Window bob) {
        View aliceView = null;
        View bobView = null;
        final int size = mContainer.getChildCount();
        for (int i = 0; i < size; i++) {
            if (mContainer.getChildAt(i).getTag() instanceof Window) {
                if (TextUtils.equals(alice.getUid(),
                        ((Window) mContainer.getChildAt(i).getTag()).getUid())) {
                    aliceView = mContainer.getChildAt(i);
                } else if (TextUtils.equals(bob.getUid(),
                        ((Window) mContainer.getChildAt(i).getTag()).getUid())) {
                    bobView = mContainer.getChildAt(i);
                }
            }
        }

        if (aliceView == null || bobView == null || aliceView == bobView) {
            return;
        }

        ((Window) aliceView.getTag()).swap((Window) bobView.getTag());

        applyWindowSize();
    }

    @Override
    public void clearView() {
        mContainer.removeAllViews();
    }

    private void applyWindowSize() {
        int n = mContainer.getChildCount();
        View[] children = new View[n];
        for (int i = 0; i < n; i++) {
            children[i] = mContainer.getChildAt(i);
        }
        Arrays.sort(children, new Comparator<View>() {
            @Override
            public int compare(final View v1, final View v2) {
                Window w1 = (Window) v1.getTag();
                Window w2 = (Window) v2.getTag();
                return w1.getZ_index() - w2.getZ_index();
            }
        });

        for (View child : children) {
            Window window = (Window) child.getTag();
            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) child.getLayoutParams();
            params.width = getSize(mContainerWidth, window.getWidth());
            params.height = getSize(mContainerHeight, window.getHeight());
            params.leftMargin = getSize(mContainerWidth, window.getLeft());
            params.topMargin = getSize(mContainerHeight, window.getTop());
            child.setLayoutParams(params);
            child.bringToFront();
        }
    }

    private int getSize(int full, int size) {
        return full * size / Window.MATCH_PARENT;
    }
}
