package com.syan.agora.source;

import android.content.Context;
import android.view.WindowManager;

import io.agora.rtc.mediaio.IVideoFrameConsumer;
import io.agora.rtc.mediaio.MediaIO.PixelFormat;
import io.agora.rtc.mediaio.TextureSource;

public class AgoraTextureCamera2 extends TextureSource {

    private final Context mContext;
    private final CameraHelper mCameraHelper;

    public AgoraTextureCamera2(Context context, final int width, final int height, CameraHelper cameraHelper) {
        super((io.agora.rtc.gl.EglBase.Context)null, width, height);
        this.mContext = context;
        this.mCameraHelper = cameraHelper;
    }

    public void onTextureFrameAvailable(int oesTextureId, float[] transformMatrix, long timestampNs) {
        super.onTextureFrameAvailable(oesTextureId, transformMatrix, timestampNs);
        int rotation = this.getFrameOrientation();
//        if (mCameraHelper.isFrontFacing()) {
//            transformMatrix = RendererCommon.multiplyMatrices(transformMatrix, RendererCommon.horizontalFlipMatrix());
//        }

        if (this.mConsumer != null && this.mConsumer.get() != null) {
            ((IVideoFrameConsumer)this.mConsumer.get()).consumeTextureFrame(oesTextureId, PixelFormat.TEXTURE_OES.intValue(), this.mWidth, this.mHeight, rotation, System.currentTimeMillis(), transformMatrix);
        }
    }

    @Override
    protected boolean onCapturerOpened() {
        return true;
    }

    @Override
    protected boolean onCapturerStarted() {
        mCameraHelper.start(getSurfaceTexture());
        return true;
    }

    @Override
    protected void onCapturerStopped() {
        mCameraHelper.stop();
    }

    @Override
    protected void onCapturerClosed() {

    }

    // --

    private int getDeviceOrientation() {
        WindowManager wm = (WindowManager) this.mContext.getSystemService(Context.WINDOW_SERVICE);
        short orientation;
        switch(wm.getDefaultDisplay().getRotation()) {
            case 0:
            default:
                orientation = 0;
                break;
            case 1:
                orientation = 90;
                break;
            case 2:
                orientation = 180;
                break;
            case 3:
                orientation = 270;
        }

        return orientation;
    }

    private int getFrameOrientation() {
        int rotation = this.getDeviceOrientation();
        if (!mCameraHelper.isFrontFacing()) { // back
            rotation = 360 - rotation;
        }

        int cameraOrientation = mCameraHelper.cameraOrientation() + 90;
        return 0; //(cameraOrientation + rotation) % 360;
    }
}
