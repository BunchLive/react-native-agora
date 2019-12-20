package live.bunch.agora;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.util.Log;
import android.view.Surface;
import android.view.WindowManager;

import java.io.IOException;
import java.lang.ref.WeakReference;

import io.agora.rtc.gl.RendererCommon;
import io.agora.rtc.mediaio.IVideoFrameConsumer;
import io.agora.rtc.mediaio.MediaIO.PixelFormat;
import io.agora.rtc.mediaio.TextureSource;

//
// Altered version of io.agora.rtc.mediaio.AgoraTextureCamera
//  - fixed auto exposure
//  - extra check for capture started state
//  - isFrontFacing query method
//
public class AgoraTextureCamera1 extends TextureSource {
    private static final String TAG = io.agora.rtc.mediaio.AgoraTextureCamera.class.getSimpleName();
    private Context mContext;
    private Camera camera;
    private CameraInfo info;
    private boolean mStarted = false;

    public AgoraTextureCamera1(Context context, int width, int height) {
        super(null, width, height);
        this.mContext = context;
    }

    public void onTextureFrameAvailable(int oesTextureId, float[] transformMatrix, long timestampNs) {
        super.onTextureFrameAvailable(oesTextureId, transformMatrix, timestampNs);
        int rotation = this.getFrameOrientation();
        if (this.info.facing == CameraInfo.CAMERA_FACING_FRONT) {
            transformMatrix = RendererCommon.multiplyMatrices(transformMatrix, RendererCommon.horizontalFlipMatrix());
        }

        WeakReference<IVideoFrameConsumer> consumerWeakRef = this.mConsumer;
        if (mStarted && consumerWeakRef != null) {
            IVideoFrameConsumer consumer = consumerWeakRef.get();
            if (consumer != null) {
                consumer.consumeTextureFrame(oesTextureId, PixelFormat.TEXTURE_OES.intValue(), this.mWidth, this.mHeight, rotation, System.currentTimeMillis(), transformMatrix);
            }
        }
    }

    public boolean isFrontFacing() {
        return this.info != null && this.info.facing == CameraInfo.CAMERA_FACING_FRONT;
    }

    protected boolean onCapturerOpened() {
        try {
            this.openCamera();
            this.camera.setPreviewTexture(this.getSurfaceTexture());
            this.camera.startPreview();
            return true;
        } catch (IOException e) {
            Log.e(TAG, "initialize: failed to initalize camera device");
            return false;
        }
    }

    protected boolean onCapturerStarted() {
        mStarted = true;
        this.camera.startPreview();
        return true;
    }

    protected void onCapturerStopped() {
        mStarted = false;
        this.camera.stopPreview();
    }

    protected void onCapturerClosed() {
        this.releaseCamera();
    }

    private void openCamera() {
        if (this.camera != null) {
            throw new RuntimeException("camera already initialized");
        } else {
            this.info = new CameraInfo();
            int numCameras = Camera.getNumberOfCameras();

            for(int i = 0; i < numCameras; ++i) {
                Camera.getCameraInfo(i, this.info);
                if (this.info.facing == CameraInfo.CAMERA_FACING_FRONT) {
                    this.camera = Camera.open(i);
                    break;
                }
            }

            if (this.camera == null) {
                Log.d(TAG, "No front-facing camera found; opening default");
                this.camera = Camera.open();
            }

            if (this.camera == null) {
                throw new RuntimeException("Unable to open camera");
            } else {
                Parameters parms = this.camera.getParameters();

                // IMPORTANT!! Setting to high PreviewFpsRange will disable auto exposure and auto white balance.

                parms.setPreviewSize(this.mWidth, this.mHeight);
                parms.setRecordingHint(true);

                if (parms.isAutoExposureLockSupported()) parms.setAutoExposureLock(false);
                if (parms.isAutoWhiteBalanceLockSupported()) parms.setAutoWhiteBalanceLock(false);

                this.camera.setParameters(parms);
                parms.setFocusMode(Parameters.FOCUS_MODE_CONTINUOUS_VIDEO);

                Size cameraPreviewSize = parms.getPreviewSize();
                String previewFacts = cameraPreviewSize.width + "x" + cameraPreviewSize.height;
                Log.i(TAG, "Camera config: " + previewFacts);
            }
        }
    }

    private int getDeviceOrientation() {
        WindowManager wm = (WindowManager)this.mContext.getSystemService(Context.WINDOW_SERVICE);
        short orientation;
        switch(wm.getDefaultDisplay().getRotation()) {
            case Surface.ROTATION_0:
            default:
                orientation = 0;
                break;
            case Surface.ROTATION_90:
                orientation = 90;
                break;
            case Surface.ROTATION_180:
                orientation = 180;
                break;
            case Surface.ROTATION_270:
                orientation = 270;
        }

        return orientation;
    }

    private int getFrameOrientation() {
        int rotation = this.getDeviceOrientation();
        if (this.info.facing == CameraInfo.CAMERA_FACING_BACK) {
            rotation = 360 - rotation;
        }

        return (this.info.orientation + rotation) % 360;
    }

    private void releaseCamera() {
        if (this.camera != null) {
            this.camera.stopPreview();

            try {
                this.camera.setPreviewTexture(null);
            } catch (Exception e) {
                Log.e(TAG, "failed to set Preview Texture");
            }

            this.camera.release();
            this.camera = null;
            Log.d(TAG, "releaseCamera -- done");
        }

    }
}
