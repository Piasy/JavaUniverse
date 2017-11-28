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

package com.github.piasy;

import com.github.piasy.java_universe.GuiWrapper;
import com.github.piasy.java_universe.TextUtils;
import com.github.piasy.java_universe.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by Piasy{github.com/Piasy} on 08/11/2017.
 */

public class JsGuiWrapper implements GuiWrapper {
    private static final int[] COLORS = new int[] { 0, 1, 2 };

    private final AbsolutePanel mContainer;
    private int mContainerWidth;
    private int mContainerHeight;

    public JsGuiWrapper(final AbsolutePanel container) {
        mContainer = container;
    }

    @Override
    public void createView(final Window window) {
        if (mContainerWidth == 0) {
            mContainerWidth = mContainer.getOffsetWidth();
            mContainerHeight = mContainer.getOffsetHeight();
        }

        Label label = new Label();
        label.setText(window.getUid());
        label.setPixelSize(getSize(mContainerWidth, window.getWidth()),
                getSize(mContainerHeight, window.getHeight()));

        switch (COLORS[mContainer.getElement().getChildCount() % COLORS.length]) {
            case 0:
                label.addStyleName("red");
                break;
            case 1:
                label.addStyleName("blue");
                break;
            case 2:
                label.addStyleName("green");
                break;
        }
        mContainer.add(label);
        mContainer.setWidgetPosition(label, getSize(mContainerWidth, window.getLeft()),
                getSize(mContainerHeight, window.getTop()));

        label.setLayoutData(window);
    }

    @Override
    public void swapView(final Window alice, final Window bob) {
        Widget aliceView = null;
        Widget bobView = null;
        final int size = mContainer.getWidgetCount();
        for (int i = 0; i < size; i++) {
            if (mContainer.getWidget(i).getLayoutData() instanceof Window) {
                if (TextUtils.equals(alice.getUid(),
                        ((Window) mContainer.getWidget(i).getLayoutData()).getUid())) {
                    aliceView = mContainer.getWidget(i);
                } else if (TextUtils.equals(bob.getUid(),
                        ((Window) mContainer.getWidget(i).getLayoutData()).getUid())) {
                    bobView = mContainer.getWidget(i);
                }
            }
        }

        if (aliceView == null || bobView == null || aliceView == bobView) {
            return;
        }

        ((Window) aliceView.getLayoutData()).swap((Window) bobView.getLayoutData());

        applyWindowSize();
    }

    @Override
    public void clearView() {
        mContainer.clear();
    }

    private void applyWindowSize() {
        int n = mContainer.getWidgetCount();
        Widget[] children = new Widget[n];
        for (int i = 0; i < n; i++) {
            children[i] = mContainer.getWidget(i);
        }
        Arrays.sort(children, new Comparator<Widget>() {
            @Override
            public int compare(final Widget v1, final Widget v2) {
                Window w1 = (Window) v1.getLayoutData();
                Window w2 = (Window) v2.getLayoutData();
                return w1.getZ_index() - w2.getZ_index();
            }
        });

        mContainer.clear();
        for (Widget child : children) {
            Window window = (Window) child.getLayoutData();
            child.setPixelSize(getSize(mContainerWidth, window.getWidth()),
                    getSize(mContainerHeight, window.getHeight()));
            mContainer.add(child);
            mContainer.setWidgetPosition(child, getSize(mContainerWidth, window.getLeft()),
                    getSize(mContainerHeight, window.getTop()));
        }
    }

    private int getSize(int full, int size) {
        return full * size / Window.MATCH_PARENT;
    }
}
