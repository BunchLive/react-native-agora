package live.bunch.agora;

import android.content.Context;
import android.graphics.Matrix;
import android.util.Size;
import android.util.SparseArray;
import android.view.View;

import com.facebook.react.bridge.ReadableMap;

import java.util.ArrayList;
import java.util.List;

import io.agora.rtc.IRtcEngineEventHandler;
import io.agora.rtc.RtcEngine;
import io.agora.rtc.mediaio.AgoraTextureView;

import static io.agora.rtc.mediaio.MediaIO.BufferType.BYTE_ARRAY;
import static io.agora.rtc.mediaio.MediaIO.BufferType.TEXTURE;
import static io.agora.rtc.mediaio.MediaIO.PixelFormat.I420;
import static io.agora.rtc.mediaio.MediaIO.PixelFormat.TEXTURE_OES;

public class AgoraManager {

    private static AgoraManager sAgoraManager;

    public static AgoraManager getInstance() {
        if (sAgoraManager == null) {
            synchronized (AgoraManager.class) {
                if (sAgoraManager == null) {
                    sAgoraManager = new AgoraManager(com.syan.agora.AgoraManager.getInstance());
                }
            }
        }
        return sAgoraManager;
    }

    // --

    public RtcEngine mRtcEngine;

    private com.syan.agora.AgoraManager mParent;
    private SparseArray<View> mSurfaceViews;

    private int mLocalUid = 0;
    private Context context;
    private final CompositeRtcEngineEventHandler mRtcEventHandler = new CompositeRtcEngineEventHandler();

    private AgoraManager(com.syan.agora.AgoraManager agoraManager) {
        mParent = agoraManager;
        mSurfaceViews = new SparseArray<>();
    }

    public void addRtcEngineEventHandler(IRtcEngineEventHandler handler) {
        mRtcEventHandler.add(handler);
    }

    public void removeRtcEngineEventHandler(IRtcEngineEventHandler handler) {
        mRtcEventHandler.remove(handler);
    }

    public boolean isInitialized() {
        return mRtcEngine != null;
    }

    public boolean isLocalAudioStreamMuted() {
        return "true".equals(mRtcEngine.getParameter("che.audio.mute_me", "false"));
    }

    public boolean isUsingFrontCamera() {
        return mCameraHelper.isFrontFacing();
    }

    // --

    public int init(Context context, ReadableMap options) {
        return init(context, null, options);
    }

    /**
     * initialize rtc engine
     */
    public int init(Context context, IRtcEngineEventHandler rtcEventHandler, ReadableMap options) {
        if (rtcEventHandler != null) mRtcEventHandler.add(rtcEventHandler);

        this.context = context;

        int r = mParent.init(context, mRtcEventHandler, options);
        mRtcEngine = new RtcEngineWrapper(mParent.mRtcEngine) {
            @Override
            public int switchCamera() {
                return AgoraManager.this.switchCamera();
            }
        };
        return r;
    }

    public int setLocalRenderMode(final Integer renderMode) {
        return mParent.setLocalRenderMode(renderMode);
    }

    public int setRemoteRenderMode(final Integer uid, final Integer renderMode) {
        return mParent.setRemoteRenderMode(uid, renderMode);
    }

    public int setEnableSpeakerphone(boolean enabled) {
        return mParent.setEnableSpeakerphone(enabled);
    }

    public int setDefaultAudioRouteToSpeakerphone(boolean enabled) {
        return mParent.setDefaultAudioRouteToSpeakerphone(enabled);
    }

    public int renewToken(String token) {
        return mParent.renewToken(token);
    }

    public int setClientRole(int role) {
        return mParent.setClientRole(role);
    }

    public int enableWebSdkInteroperability(boolean enabled) {
        return mParent.enableWebSdkInteroperability(enabled);
    }

    public int getConnectionState() {
        return mParent.getConnectionState();
    }

    public int joinChannel(ReadableMap options) {
        int uid = options.hasKey("uid") ? options.getInt("uid") : 0;
        this.mLocalUid = uid;
        return mParent.joinChannel(options);
    }

    public int enableLastmileTest() {
        return mParent.enableLastmileTest();
    }

    public int disableLastmileTest() {
        return mParent.disableLastmileTest();
    }

    public int startPreview() {
        return mParent.startPreview();
    }

    public int stopPreview() {
        return mParent.stopPreview();
    }

    public int leaveChannel() {
        return mParent.leaveChannel();
    }

    // -- TextureView support

    public void removeSurfaceView(int uid) {
        mSurfaceViews.remove(uid);
    }

    public List<View> getSurfaceViews() {
        List<View> list = new ArrayList<>();
        for (int i = 0; i < mSurfaceViews.size(); i++) {
            View surfaceView = mSurfaceViews.valueAt(i);
            list.add(surfaceView);
        }
        return list;
    }

    public View getLocalSurfaceView() {
        return mSurfaceViews.get(mLocalUid);
    }

    public View getSurfaceView(int uid) {
        return mSurfaceViews.get(uid);
    }

    public int switchCamera() {
        mCameraHelper.switchCamera();
        return 0;
    }

    private CameraHelper mCameraHelper;

    public int setupLocalVideo(Integer mode) {
        return setupLocalVideo();
    }

    public int setupLocalVideo() {
        if (mCameraHelper == null) {
            mCameraHelper = new CameraHelper(context, new CameraHelper.CameraHelperDelegate() {
                @Override
                public Size getTextureViewSize() {
                    return new Size(480, 640);
                }

                @Override
                public void setTextureViewTransform(final Matrix transform) {

                }

                @Override
                public void onPreviewSizeChange(final Size size) {

                }
            });
            mCameraHelper.setUseFrontCamera(true);
        }

        AgoraTextureCamera2 videoSource = new AgoraTextureCamera2(context, 480, 640, mCameraHelper);
        AgoraTextureView renderer = new AgoraTextureView(context);
        renderer.init(videoSource.getEglContext());
        renderer.setBufferType(TEXTURE);
        renderer.setPixelFormat(TEXTURE_OES);

        mRtcEngine.setVideoSource(videoSource);
        mRtcEngine.setLocalVideoRenderer(renderer);
        mSurfaceViews.put(mLocalUid, renderer);
        return 0;
    }

    public int setupRemoteVideo(final int uid, Integer mode) {
        return setupRemoteVideo(uid);
    }

    public int setupRemoteVideo(final int uid) {
        AgoraTextureView renderer = new AgoraTextureView(context);
        renderer.init(null);
        renderer.setBufferType(BYTE_ARRAY);
        renderer.setPixelFormat(I420);

        mRtcEngine.setRemoteVideoRenderer(uid, renderer);
        mSurfaceViews.put(uid, renderer);
        return 0;
    }
}
