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

import com.github.piasy.java_universe.DataSource;
import com.github.piasy.java_universe.Window;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Piasy{github.com/Piasy} on 08/11/2017.
 */

public class JsDataSource implements DataSource {

    private final RequestBuilder mBuilder;

    public JsDataSource() {
        // callback in main thread by default
        String url = URL.encode("https://imgs.babits.top/windows_4.json");
        mBuilder = new RequestBuilder(RequestBuilder.GET, url);
    }

    @Override
    public void getWindows(final int page, final int num, final Callback<List<Window>> callback) {

        try {
            mBuilder.sendRequest(null,
                    new RequestCallback() {
                        @Override
                        public void onResponseReceived(final Request request,
                                final Response response) {
                            if (200 == response.getStatusCode()) {
                                List<Window> windows = new ArrayList<>();
                                JsArray<WindowJs> datas = JsonUtils.safeEval(
                                        response.getText());

                                for (int i = 0; i < datas.length(); i++) {
                                    final WindowJs windowJs = datas.get(i);
                                    Window window = new Window(windowJs.getWidth(),
                                            windowJs.getHeight(), windowJs.getTop(),
                                            windowJs.getLeft(), windowJs.getZ_index(),
                                            windowJs.getUid());
                                    windows.add(window);
                                }

                                callback.onSuccess(windows);
                            }
                        }

                        @Override
                        public void onError(final Request request, final Throwable throwable) {
                            callback.onFailure(DataSource.ERR_API_FAIL);
                        }
                    });
        } catch (RequestException e) {
            callback.onFailure(DataSource.ERR_API_FAIL);
        }
    }
}
