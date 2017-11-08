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

import com.github.piasy.java_universe.DataSource;
import com.github.piasy.java_universe.Window;
import java.util.List;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by Piasy{github.com/Piasy} on 08/11/2017.
 */

public class RetrofitDataSource implements DataSource {
    private final Api mApi;

    public RetrofitDataSource() {
        // callback in main thread by default
        mApi = new Retrofit.Builder()
                .baseUrl("http://omduludtl.bkt.clouddn.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api.class);
    }

    @Override
    public void getWindows(final int page, final int num, final Callback<List<Window>> callback) {
        mApi.getWindows().enqueue(new retrofit2.Callback<List<Window>>() {
            @Override
            public void onResponse(final Call<List<Window>> call,
                    final Response<List<Window>> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(final Call<List<Window>> call, final Throwable t) {
                callback.onFailure(DataSource.ERR_API_FAIL);
            }
        });
    }

    interface Api {
        @GET("windows_3.json")
        Call<List<Window>> getWindows();
    }
}
