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
import com.google.j2objc.annotations.Property;

/**
 * Created by Piasy{github.com/Piasy} on 08/11/2017.
 */

public class Window {

    public static final int MATCH_PARENT = 10000;

    /**
     * width : 90
     * height : 160
     * top : 0
     * left : 0
     * z_index : 1
     * uid : knuth
     */

    // all dimensions are percentage scaled by MATCH_PARENT(10000)
    @Property
    private int width;
    @Property
    private int height;
    @Property
    private int top;
    @Property
    private int left;
    @Property
    private int z_index;
    @Property
    private String uid;

    public Window() {
    }

    @ObjectiveCName("initWithWidth:height:top:left:zIndex:uid:")
    public Window(int width, int height, int top, int left, int z_index, String uid) {
        this.width = width;
        this.height = height;
        this.top = top;
        this.left = left;
        this.z_index = z_index;
        this.uid = uid;
    }

    public boolean isFullscreen() {
        return width == MATCH_PARENT && height == MATCH_PARENT;
    }

    public Window copy() {
        return new Window(width, height, top, left, z_index, uid);
    }

    @ObjectiveCName("swapWithWindow:")
    public void swap(Window that) {
        int tmp = top;
        top = that.top;
        that.top = tmp;

        tmp = left;
        left = that.left;
        that.left = tmp;

        tmp = width;
        width = that.width;
        that.width = tmp;

        tmp = height;
        height = that.height;
        that.height = tmp;

        tmp = z_index;
        z_index = that.z_index;
        that.z_index = tmp;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getZ_index() {
        return z_index;
    }

    public void setZ_index(int z_index) {
        this.z_index = z_index;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
