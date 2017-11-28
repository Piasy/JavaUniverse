package com.github.piasy;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Created by powerinfo on 2017/11/27.
 */

public class WindowJs extends JavaScriptObject {

    // Overlay types always have protected, zero argument constructors.
    protected WindowJs() {}

    // JSNI methods to get stock data.
    public final native int getWidth() /*-{ return this.width; }-*/;
    public final native int getHeight() /*-{ return this.height; }-*/;
    public final native int getTop() /*-{ return this.top; }-*/;
    public final native int getLeft() /*-{ return this.left; }-*/;
    public final native int getZ_index() /*-{ return this.z_index; }-*/;
    public final native String getUid() /*-{ return this.uid; }-*/;
}
