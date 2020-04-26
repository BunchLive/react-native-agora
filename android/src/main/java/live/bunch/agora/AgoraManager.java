package live.bunch.agora;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;

import com.facebook.react.bridge.ReadableMap;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import io.agora.rtc.IMetadataObserver;
import io.agora.rtc.IRtcEngineEventHandler;
import io.agora.rtc.RtcEngineEx;
import io.agora.rtc.mediaio.AgoraDefaultRender;
import io.agora.rtc.mediaio.AgoraDefaultSource;
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

    public RtcEngineEx mRtcEngine;

    private com.syan.agora.AgoraManager mParent;
    private SparseArray<View> mSurfaceViews;

    private int mLocalUid = 0;
    private Context context;
    private final CompositeRtcEngineEventHandler mRtcEventHandler = new CompositeRtcEngineEventHandler();
    private boolean mLocalVideoStreamMuted = false;

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

    public boolean isLocalVideoStreamMuted() {
        return mLocalVideoStreamMuted;
    }

    public boolean isUsingFrontCamera() {
        //if (mCameraHelper != null) return mCameraHelper.isFrontFacing();
        return true;
    }

    public int switchCamera() {
        return mParent.mRtcEngine.switchCamera();
    }

    // --

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

    // - AgoraManager changes

    public int init(Context context, ReadableMap options) {
        return init(context, null, options);
    }

    /**
     * initialize rtc engine
     */
    public int init(Context context, IRtcEngineEventHandler rtcEventHandler, ReadableMap options) {
        if (rtcEventHandler != null) {
            mRtcEventHandler.remove(rtcEventHandler);
            mRtcEventHandler.add(rtcEventHandler);
        }

        this.context = context;

        int r = mParent.init(context, mRtcEventHandler, options);
        mRtcEngine = new RtcEngineWrapper(mParent.mRtcEngine) {
            @Override
            public int switchCamera() {
                return AgoraManager.this.switchCamera();
            }

            @Override
            public int muteLocalVideoStream(boolean muted) {
                AgoraManager.this.mLocalVideoStreamMuted = muted;
                return super.muteLocalVideoStream(muted);
            }
        };

        mRtcEngine.registerMediaMetadataObserver(mRtcMetadataObserver, 0); // type VIDEO_METADATA(0)
        return r;
    }

    public int joinChannel(ReadableMap options) {
        mMetadataQueue.clear(); // Remove any pending metadata before joining.

        int uid = options.hasKey("uid") ? options.getInt("uid") : 0;
        this.mLocalUid = uid;
        return mParent.joinChannel(options);
    }

    public int joinChannelWithUserAccount(String token, String channelName, String userAccount) {
        mMetadataQueue.clear(); // Remove any pending metadata before joining.
        return mRtcEngine.joinChannelWithUserAccount(token, channelName, userAccount);
    }

    public void removeSurfaceView(int uid) {
        mParent.removeSurfaceView(uid);
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
        return mParent.getLocalSurfaceView();
    }

    public View getSurfaceView(int uid) {
        return mSurfaceViews.get(uid);
    }

    public int setupLocalVideo(Integer mode) {
        mRtcEngine.setVideoSource(new AgoraDefaultSource());
        mRtcEngine.setLocalVideoRenderer(new AgoraDefaultRender());
        return mParent.setupLocalVideo(mode);
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

    //region TextureView support

    private View mLocalTextureView = null;

    public View getLocalTextureView() {
        return mLocalTextureView;
    }

    public void removeLocalTextureView() {
        mLocalTextureView = null;
    }

    public int setupLocalTextureView() {
        AgoraTextureCamera1 videoSource = new AgoraTextureCamera1(context, 640, 480);
        AgoraTextureView renderer = new AgoraTextureView(context);
        renderer.setMirror(true);
        renderer.init(videoSource.getEglContext());
        renderer.setBufferType(TEXTURE);
        renderer.setPixelFormat(TEXTURE_OES);

        mRtcEngine.setVideoSource(videoSource);
        mRtcEngine.setLocalVideoRenderer(renderer);
        mLocalTextureView = renderer;
        return 0;
    }

    // endregion

    //region Metadata support

    public interface MetadataListener {
        /**
         * Called when the local user receives the metadata.
         *
         * @param buffer The received metadata. Use StringMetadataEncoder to decode it to a String.
         * @param uid The ID of the user who sent the metadata.
         * @param timeStampMs The timestamp (ms) of the received metadata.
         */
        void onMetadataReceived(byte[] buffer, int uid, long timeStampMs);
    }

    public static final int METADATA_MAX_LENGTH = 1024;

    private ConcurrentLinkedQueue<byte[]> mMetadataQueue = new ConcurrentLinkedQueue<>();
    private List<MetadataListener> mMetadataListeners = new ArrayList<>();
    private IMetadataObserver mRtcMetadataObserver = new IMetadataObserver() {

        @Override
        public int getMaxMetadataSize() {
            byte[] data = mMetadataQueue.peek();
            if (data == null) return 0;

            return data.length;
        }

        @Override
        public byte[] onReadyToSendMetadata(long timeStampMs) {
            byte[] data = mMetadataQueue.poll();
            if (data == null) return new byte[0];

            return data;
        }

        @Override
        public void onMetadataReceived(final byte[] buffer, final int uid, final long timeStampMs) {
            if (0 == buffer.length) return;

            for (MetadataListener l : mMetadataListeners) {
                l.onMetadataReceived(buffer, uid, timeStampMs);
            }
        }
    };

    /**
     * Send metadata via RtcEngine.
     * Use StringMetadataEncoder to encode String data.
     *
     * @param metadata byte[] data
     * @return true if data has been queued for sending,
     * false if data exceeds {@link #METADATA_MAX_LENGTH} and cannot be sent
     */
    public boolean sendMetadata(byte[] metadata) {
        if (METADATA_MAX_LENGTH < metadata.length) {
            return false;
        }
        mMetadataQueue.add(metadata);
        return true;
    }

    /**
     * Registers metadata listener
     *
     * @param listener {@link MetadataListener}
     */
    public void addMetadataListener(MetadataListener listener) {
        if (mMetadataListeners.indexOf(listener) == -1) {
            mMetadataListeners.add(listener);
        }
    }

    /**
     * Unregisters metadata listener.
     *
     * @param listener {@link MetadataListener}
     */

    public void removeMetadataListener(MetadataListener listener) {
        mMetadataListeners.remove(listener);
    }

    //endregion
}
