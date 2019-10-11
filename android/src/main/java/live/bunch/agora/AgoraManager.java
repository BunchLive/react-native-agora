package live.bunch.agora;

import android.content.Context;
import android.graphics.Matrix;
import android.util.Size;
import android.util.SparseArray;
import android.view.View;

import com.facebook.react.bridge.ReadableMap;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import io.agora.rtc.IRtcEngineEventHandler;
import io.agora.rtc.RtcEngine;
import io.agora.rtc.mediaio.AgoraTextureView;

import static io.agora.rtc.mediaio.MediaIO.BufferType.BYTE_ARRAY;
import static io.agora.rtc.mediaio.MediaIO.BufferType.TEXTURE;
import static io.agora.rtc.mediaio.MediaIO.PixelFormat.I420;
import static io.agora.rtc.mediaio.MediaIO.PixelFormat.TEXTURE_OES;

@SuppressWarnings("unused")
public class AgoraManager {
    private static AgoraManager sAgoraManager;

    public RtcEngine mRtcEngine;

    private final com.syan.agora.AgoraManager mMgr;
    private final CompositeRtcEngineEventHandler mRtcEventHandler;
    private WeakReference<Context> mContextRef;
    private CameraHelper mCameraHelper;
    private SparseArray<View> mSurfaceViews;
    private int mLocalUid = 0;

    public synchronized static AgoraManager getInstance() {
        if (sAgoraManager == null) {
            sAgoraManager = new AgoraManager(com.syan.agora.AgoraManager.getInstance());
        }

        return sAgoraManager;
    }

    private AgoraManager(com.syan.agora.AgoraManager agoraMgr) {
        mMgr = agoraMgr;
        mRtcEventHandler = new CompositeRtcEngineEventHandler();
        mSurfaceViews = new SparseArray<>();
        mContextRef = new WeakReference<>(null);
    }

    public void addRtcEngineEventHandler(IRtcEngineEventHandler handler) {
        mRtcEventHandler.add(handler);
    }

    public void removeRtcEngineEventHandler(IRtcEngineEventHandler handler) {
        mRtcEventHandler.remove(handler);
    }

    public boolean isInitialized() {
        return mMgr.mRtcEngine != null;
    }

    public boolean isLocalAudioStreamMuted() {
        return "true".equals(mMgr.mRtcEngine.getParameter("che.audio.mute_me", "false"));
    }

    public boolean isUsingFrontCamera() {
        return mCameraHelper != null && mCameraHelper.isFrontFacing();
    }

    public int init(Context context, ReadableMap options) {
        return init(context, null, options);
    }

    public int init(Context context, IRtcEngineEventHandler rtcEventHandler, ReadableMap options) {
        mContextRef = new WeakReference<>(context);

        if (rtcEventHandler != null) mRtcEventHandler.add(rtcEventHandler);

        int r = mMgr.init(context, mRtcEventHandler, options);
        mRtcEngine = mMgr.mRtcEngine;
        return r;
    }

    public int setEnableSpeakerphone(boolean enabled) {
        return mMgr.setEnableSpeakerphone(enabled);
    }

    public int setDefaultAudioRouteToSpeakerphone(boolean enabled) {
        return mMgr.setDefaultAudioRouteToSpeakerphone(enabled);
    }

    public int renewToken(String token) {
        return mMgr.renewToken(token);
    }

    public int setClientRole(int role) {
        return mMgr.setClientRole(role);
    }

    public int enableWebSdkInteroperability(boolean enabled) {
        return mMgr.enableWebSdkInteroperability(enabled);
    }

    public int getConnectionState() {
        return mMgr.getConnectionState();
    }

    public int joinChannel(ReadableMap options) {
        this.mLocalUid = options.hasKey("uid") ? options.getInt("uid") : 0;
        return mMgr.joinChannel(options);
    }

    public int enableLastmileTest() {
        return mMgr.enableLastmileTest();
    }

    public int disableLastmileTest() {
        return mMgr.disableLastmileTest();
    }

    public void startPreview() {
        mMgr.startPreview();
    }

    public void stopPreview() {
        mMgr.stopPreview();
    }

    public int leaveChannel() {
        return mMgr.leaveChannel();
    }

    public int switchCamera() {
        if (mCameraHelper == null) return -1;
        mCameraHelper.switchCamera();
        return 0;
    }

//    public void removeSurfaceView(int uid) {
//        mMgr.removeSurfaceView(uid);
//    }
//
//    public List<View> getSurfaceViews() {
//        return mMgr.getSurfaceViews();
//    }
//
//    public View getLocalSurfaceView() {
//        return mMgr.getLocalSurfaceView();
//    }
//
//    public View getSurfaceView(int uid) {
//        return mMgr.getSurfaceView(uid);
//    }
//
//    public int setupLocalVideo() {
//        return mMgr.setupLocalVideo();
//    }
//
//    public int setupRemoteVideo(int uid) {
//        return mMgr.setupRemoteVideo(uid);
//    }

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

    public int setupLocalVideo() {
        Context context = mContextRef.get();
        if (context == null) return -1;

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

        mMgr.mRtcEngine.setVideoSource(videoSource);
        mMgr.mRtcEngine.setLocalVideoRenderer(renderer);
        mSurfaceViews.put(mLocalUid, renderer);
        return 0;
    }

    public int setupRemoteVideo(final int uid) {
        Context context = mContextRef.get();
        if (context == null) return -1;

        AgoraTextureView renderer = new AgoraTextureView(context);
        renderer.init(null);
        renderer.setBufferType(BYTE_ARRAY);
        renderer.setPixelFormat(I420);

        mMgr.mRtcEngine.setRemoteVideoRenderer(uid, renderer);
        mSurfaceViews.put(uid, renderer);
        return 0;
    }
}
