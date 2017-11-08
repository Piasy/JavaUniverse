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

package com.github.piasy.java_universe.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.github.piasy.java_universe.Window;
import com.github.piasy.java_universe.WindowManager;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements WindowManager.Callback {

    @BindView(R.id.mContainer)
    FrameLayout mContainer;

    private final List<String> mUsers = new ArrayList<>();

    private WindowManager mWindowManager;
    private int mFcIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mWindowManager = new WindowManager(mContainer, this);

        mWindowManager.loadWindows();
    }

    @OnClick(R.id.mShuffle)
    void shuffle() {
        mFcIndex = (mFcIndex + 1) % mUsers.size();
        mWindowManager.toggleFullscreen(mUsers.get(mFcIndex));
    }

    @Override
    public void onWindowAdded(final Window window) {
        mUsers.add(window.getUid());
    }

    @Override
    public void onError(final int error) {
        Toast.makeText(this, "onError " + error, Toast.LENGTH_SHORT).show();
    }
}
