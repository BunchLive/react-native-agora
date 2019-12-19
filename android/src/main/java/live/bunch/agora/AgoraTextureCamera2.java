package live.bunch.agora;

import android.content.Context;
import android.view.Surface;
import android.view.WindowManager;

import java.lang.ref.WeakReference;

import io.agora.rtc.mediaio.IVideoFrameConsumer;
import io.agora.rtc.mediaio.MediaIO.PixelFormat;
import io.agora.rtc.mediaio.TextureSource;

public class AgoraTextureCamera2 extends TextureSource {
    private final Context mContext;
    private final CameraHelper mCameraHelper;
    private boolean mStarted = false;

    public AgoraTextureCamera2(Context context, final int width, final int height, CameraHelper cameraHelper) {
        super(null, width, height);
        this.mContext = context;
        this.mCameraHelper = cameraHelper;
    }

    public void onTextureFrameAvailable(int oesTextureId, float[] transformMatrix, long timestampNs) {
        super.onTextureFrameAvailable(oesTextureId, transformMatrix, timestampNs);

        WeakReference<IVideoFrameConsumer> consumerWeakRef = mConsumer;
        if (mStarted && consumerWeakRef != null) {
            IVideoFrameConsumer consumer = consumerWeakRef.get();
            if (consumer != null) {
                consumer.consumeTextureFrame(oesTextureId, PixelFormat.TEXTURE_OES.intValue(), mWidth, mHeight, getFrameOrientation(), System.currentTimeMillis(), transformMatrix);
            }
        }
    }

    @Override
    protected boolean onCapturerOpened() {
        mCameraHelper.start(getSurfaceTexture());
        return true;
    }

    @Override
    protected boolean onCapturerStarted() {
        mStarted = true;
        return true;
    }

    @Override
    protected void onCapturerStopped() {
        mStarted = false;

    }

    @Override
    protected void onCapturerClosed() {
        mCameraHelper.stop();
    }

    // --

    private int getDeviceOrientation() {
        WindowManager wm = (WindowManager) this.mContext.getSystemService(Context.WINDOW_SERVICE);
        switch(wm.getDefaultDisplay().getRotation()) {
            case Surface.ROTATION_0:
            default: return 0;
            case Surface.ROTATION_90: return 90;
            case Surface.ROTATION_180: return 180;
            case Surface.ROTATION_270: return 270;
        }
    }

    private int getFrameOrientation() {
        int cameraOrientation = mCameraHelper.cameraOrientation();
        int deviceOrientation = this.getDeviceOrientation();
        int rotation = deviceOrientation;
        if (deviceOrientation == 90 || deviceOrientation == 270) {
            rotation = (cameraOrientation == 90 || cameraOrientation == 270) ? (deviceOrientation + 180) % 360 : deviceOrientation;
        }

        return rotation;
    }
}