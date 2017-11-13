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

package com.github.piasy.java_universe;

import com.google.j2objc.annotations.ObjectiveCName;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Piasy{github.com/Piasy} on 08/11/2017.
 */

public class WindowManagerCore {
    private final DataSource mDataSource;
    private final GuiWrapper mGuiWrapper;
    private final Callback mCallback;

    private final List<Window> mWindows = new ArrayList<>();

    public WindowManagerCore(final DataSource dataSource, final GuiWrapper guiWrapper,
            final Callback callback) {
        mDataSource = dataSource;
        mGuiWrapper = guiWrapper;
        mCallback = callback;
    }

    public void loadWindows() {
        mDataSource.getWindows(1, 20, new DataSource.Callback<List<Window>>() {
            @Override
            public void onSuccess(final List<Window> data) {
                mGuiWrapper.clearView();
                mWindows.clear();
                mWindows.addAll(data);

                final int size = mWindows.size();
                for (int i = 0; i < size; i++) {
                    mGuiWrapper.createView(mWindows.get(i));
                }
                for (int i = 0; i < size; i++) {
                    mCallback.onWindowAdded(mWindows.get(i).copy());
                }
            }

            @Override
            public void onFailure(final int error) {
                mCallback.onError(error);
            }
        });
    }

    public List<Window> getWindows() {
        List<Window> windows = new ArrayList<>(mWindows.size());
        final int size = mWindows.size();
        for (int i = 0; i < size; i++) {
            windows.add(mWindows.get(i).copy());
        }
        return windows;
    }

    public void toggleFullscreen(String uid) {
        Window newFc = getWindowOf(uid);
        Window oldFc = getCurrentFullscreen();
        if (newFc != null && oldFc != null && newFc != oldFc) {
            mGuiWrapper.swapView(newFc, oldFc);
        }
    }

    private Window getWindowOf(final String uid) {
        final int size = mWindows.size();
        for (int i = 0; i < size; i++) {
            if (TextUtils.equals(mWindows.get(i).getUid(), uid)) {
                return mWindows.get(i);
            }
        }

        return null;
    }

    private Window getCurrentFullscreen() {
        final int size = mWindows.size();
        for (int i = 0; i < size; i++) {
            if (mWindows.get(i).isFullscreen()) {
                return mWindows.get(i);
            }
        }

        return null;
    }

    public interface Callback {
        @ObjectiveCName("onWindowAdded:")
        void onWindowAdded(Window window);

        @ObjectiveCName("onError:")
        void onError(int error);
    }
}
