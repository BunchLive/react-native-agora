package live.bunch.agora;

import android.view.View;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.syan.agora.AgoraVideoView;

public class AgoraViewManager extends SimpleViewManager<AgoraVideoView> {

    private static final String REACT_CLASS = "RCTAgoraVideoView";

    private View videoView;

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
            AgoraManager.getInstance().setupLocalVideo(agoraVideoView.getRenderMode());
            videoView = AgoraManager.getInstance().getLocalSurfaceView();
            agoraVideoView.addView(videoView);
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
            AgoraManager.getInstance().setupRemoteVideo(remoteUid, agoraVideoView.getRenderMode());
            videoView = AgoraManager.getInstance().getSurfaceView(remoteUid);
            agoraVideoView.addView(videoView);
        }
    }

}