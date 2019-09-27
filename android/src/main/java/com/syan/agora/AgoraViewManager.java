package com.syan.agora;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

import android.view.View;


/**
 * Created by DB on 2017/6/23.
 */

public class AgoraViewManager extends SimpleViewManager<AgoraVideoView> {

    public static final String REACT_CLASS = "RCTAgoraVideoView";

    public View surfaceView;

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    protected AgoraVideoView createViewInstance(ThemedReactContext reactContext) {
        return new AgoraVideoView(reactContext);
    }

    @ReactProp(name = "mode")
    public void setRenderMode(final AgoraVideoView agoraVideoView, Integer renderMode) {
        agoraVideoView.setRenderMode(renderMode);
    }

    @ReactProp(name = "showLocalVideo")
    public void setShowLocalVideo(final AgoraVideoView agoraVideoView, boolean showLocalVideo) {
        agoraVideoView.setShowLocalVideo(showLocalVideo);
        if (showLocalVideo) {
            AgoraManager.getInstance().setupLocalVideo();
            surfaceView = AgoraManager.getInstance().getLocalSurfaceView();
            // surfaceView.setZOrderMediaOverlay(agoraVideoView.getZOrderMediaOverlay());
            agoraVideoView.addView(surfaceView);
        }
    }

    @ReactProp(name = "zOrderMediaOverlay")
    public void setZOrderMediaOverlay(final AgoraVideoView agoraVideoView, boolean zOrderMediaOverlay) {
        // surfaceView.setZOrderMediaOverlay(zOrderMediaOverlay);
    }

    @ReactProp(name = "remoteUid")
    public void setRemoteUid(final AgoraVideoView agoraVideoView, final int remoteUid) {
        agoraVideoView.setRemoteUid(remoteUid);
        if (remoteUid != 0) {
            AgoraManager.getInstance().setupRemoteVideo(remoteUid);
            surfaceView = AgoraManager.getInstance().getSurfaceView(remoteUid);
            // surfaceView.setZOrderMediaOverlay(agoraVideoView.getZOrderMediaOverlay());
            agoraVideoView.addView(surfaceView);
        }
    }

}
