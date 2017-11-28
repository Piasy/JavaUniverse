package com.github.piasy;

import com.github.piasy.java_universe.Window;
import com.github.piasy.java_universe.WindowManagerCore;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import java.util.ArrayList;
import java.util.List;

public class WebApp implements EntryPoint, WindowManagerCore.Callback {

    private final List<String> mUsers = new ArrayList<>();
    private Button addStockButton = new Button("Add");
    private WindowManager mWindowManager;
    private AbsolutePanel mAbsolutePanel = new AbsolutePanel();
    private int mFcIndex = 0;

    /**
     * Entry point method.
     */
    public void onModuleLoad() {
        final RootPanel rootPanel = RootPanel.get("main");
        mAbsolutePanel.setPixelSize(800, 800);
        rootPanel.add(addStockButton);
        rootPanel.add(mAbsolutePanel);
        mWindowManager = new WindowManager(mAbsolutePanel, this);
        mWindowManager.loadWindows();

        addStockButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                mFcIndex = (mFcIndex + 1) % mUsers.size();
                mWindowManager.toggleFullscreen(mUsers.get(mFcIndex));
            }
        });
    }

    @Override
    public void onWindowAdded(final Window window) {
        mUsers.add(window.getUid());
    }

    @Override
    public void onError(final int error) {

    }
}