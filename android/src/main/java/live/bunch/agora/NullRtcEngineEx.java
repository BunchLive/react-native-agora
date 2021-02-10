package live.bunch.agora;

import javax.microedition.khronos.egl.EGLContext;

import io.agora.rtc.IAudioEffectManager;
import io.agora.rtc.IAudioFrameObserver;
import io.agora.rtc.IMetadataObserver;
import io.agora.rtc.RtcChannel;
import io.agora.rtc.RtcEngineEx;
import io.agora.rtc.internal.EncryptionConfig;
import io.agora.rtc.internal.LastmileProbeConfig;
import io.agora.rtc.live.LiveInjectStreamConfig;
import io.agora.rtc.live.LiveTranscoding;
import io.agora.rtc.mediaio.IVideoSink;
import io.agora.rtc.mediaio.IVideoSource;
import io.agora.rtc.models.ChannelMediaOptions;
import io.agora.rtc.models.ClientRoleOptions;
import io.agora.rtc.models.DataStreamConfig;
import io.agora.rtc.models.UserInfo;
import io.agora.rtc.video.AgoraImage;
import io.agora.rtc.video.AgoraVideoFrame;
import io.agora.rtc.video.BeautyOptions;
import io.agora.rtc.video.CameraCapturerConfiguration;
import io.agora.rtc.video.ChannelMediaRelayConfiguration;
import io.agora.rtc.video.VideoCanvas;
import io.agora.rtc.video.VideoEncoderConfiguration;
import io.agora.rtc.video.WatermarkOptions;

/**
 * Null Object for RtcEngineEx
 */
public final class NullRtcEngineEx extends RtcEngineEx {

    @Override
    public int setProfile(final String profile, final boolean merge) {
        return 0;
    }

    @Override
    public int setAppType(final int appType) {
        return 0;
    }

    @Override
    public int enableTransportQualityIndication(final boolean enabled) {
        return 0;
    }

    @Override
    public int playRecap() {
        return 0;
    }

    @Override
    public int enableRecap(final int interval) {
        return 0;
    }

    @Override
    public String getParameters(final String parameters) {
        return null;
    }

    @Override
    public String makeQualityReportUrl(final String channel, final int listenerUid, final int speakerUid, final int format) {
        return null;
    }

    @Override
    public int updateSharedContext(final EGLContext sharedContext) {
        return 0;
    }

    @Override
    public int updateSharedContext(final android.opengl.EGLContext sharedContext) {
        return 0;
    }

    @Override
    public int setTextureId(final int id, final EGLContext eglContext, final int width, final int height, final long ts) {
        return 0;
    }

    @Override
    public int setTextureId(final int id, final android.opengl.EGLContext eglContext, final int width, final int height, final long ts) {
        return 0;
    }

    @Override
    public int monitorAudioRouteChange(final boolean isMonitoring) {
        return 0;
    }

    @Override
    public int setApiCallMode(final int syncCallTimeout) {
        return 0;
    }

    @Override
    public int setChannelProfile(final int profile) {
        return 0;
    }

    @Override
    public int setClientRole(final int role) {
        return 0;
    }

	@Override
	public int setClientRole(final int role, ClientRoleOptions option) {
		return 0;
	}

    @Override
    public int sendCustomReportMessage(String id, String category, String event, String label, int value) { return 0; }

    @Override
    public int joinChannel(final String token, final String channelName, final String optionalInfo, final int optionalUid) {
        return 0;
    }

	@Override
	public int joinChannel(String token, String channelName, String optionalInfo, int optionalUid, ChannelMediaOptions options) {
		return 0;
	}

	@Override
    public int switchChannel(final String token, final String channelName) {
        return 0;
    }

	@Override
	public int switchChannel(String token, String channelName, ChannelMediaOptions options) {
		return 0;
	}

	@Override
    public int leaveChannel() {
        return 0;
    }

    @Override
    public int renewToken(final String token) {
        return 0;
    }

    @Override
    public int registerLocalUserAccount(final String appId, final String userAccount) {
        return 0;
    }

    @Override
    public int joinChannelWithUserAccount(final String token, final String channelName, final String userAccount) {
        return 0;
    }

	@Override
	public int joinChannelWithUserAccount(String token, String channelName, String userAccount, ChannelMediaOptions options) {
		return 0;
	}

	@Override
	public int setCloudProxy(int proxyType) {
		return 0;
	}

	@Override
    public int getUserInfoByUserAccount(final String userAccount, final UserInfo userInfo) {
        return 0;
    }

    @Override
    public int getUserInfoByUid(final int uid, final UserInfo userInfo) {
        return 0;
    }

    @Override
    public int enableWebSdkInteroperability(final boolean enabled) {
        return 0;
    }

    @Override
    public int getConnectionState() {
        return 0;
    }

	@Override
	public int enableRemoteSuperResolution(int uid, boolean enable) {
		return 0;
	}

	@Override
    public int enableAudio() {
        return 0;
    }

    @Override
    public int disableAudio() {
        return 0;
    }

    @Override
    public int pauseAudio() {
        return 0;
    }

    @Override
    public int resumeAudio() {
        return 0;
    }

    @Override
    public int setAudioProfile(final int profile, final int scenario) {
        return 0;
    }

    @Override
    public int setHighQualityAudioParameters(final boolean fullband, final boolean stereo, final boolean fullBitrate) {
        return 0;
    }

    @Override
    public int adjustRecordingSignalVolume(final int volume) {
        return 0;
    }

    @Override
    public int adjustPlaybackSignalVolume(final int volume) {
        return 0;
    }

    @Override
    public int enableAudioVolumeIndication(final int interval, final int smooth, final boolean report_vad) {
        return 0;
    }

    @Override
    public int enableAudioQualityIndication(final boolean enabled) {
        return 0;
    }

    @Override
    public int enableLocalAudio(final boolean enabled) {
        return 0;
    }

    @Override
    public int muteLocalAudioStream(final boolean muted) {
        return 0;
    }

    @Override
    public int muteRemoteAudioStream(final int uid, final boolean muted) {
        return 0;
    }

    @Override
    public int adjustUserPlaybackSignalVolume(final int uid, final int volume) {
        return 0;
    }

    @Override
    public int muteAllRemoteAudioStreams(final boolean muted) {
        return 0;
    }

    @Override
    public int setDefaultMuteAllRemoteAudioStreams(final boolean muted) {
        return 0;
    }

    @Override
    public int enableVideo() {
        return 0;
    }

    @Override
    public int disableVideo() {
        return 0;
    }

    @Override
    public int setVideoProfile(final int profile, final boolean swapWidthAndHeight) {
        return 0;
    }

    @Override
    public int setVideoProfile(final int width, final int height, final int frameRate, final int bitrate) {
        return 0;
    }

    @Override
    public int setVideoEncoderConfiguration(final VideoEncoderConfiguration config) {
        return 0;
    }

    @Override
    public int setCameraCapturerConfiguration(final CameraCapturerConfiguration config) {
        return 0;
    }

    @Override
    public int setupLocalVideo(final VideoCanvas local) {
        return 0;
    }

    @Override
    public int setupRemoteVideo(final VideoCanvas remote) {
        return 0;
    }

    @Override
    public int setLocalRenderMode(final int renderMode) {
        return 0;
    }

    @Override
    public int setLocalRenderMode(final int renderMode, final int mirrorMode) {
        return 0;
    }

    @Override
    public int setRemoteRenderMode(final int uid, final int renderMode) {
        return 0;
    }

    @Override
    public int setRemoteRenderMode(final int uid, final int renderMode, final int mirrorMode) {
        return 0;
    }

    @Override
    public int startPreview() {
        return 0;
    }

    @Override
    public int stopPreview() {
        return 0;
    }

    @Override
    public int enableLocalVideo(final boolean enabled) {
        return 0;
    }

    @Override
    public int muteLocalVideoStream(final boolean muted) {
        return 0;
    }

    @Override
    public int muteRemoteVideoStream(final int uid, final boolean muted) {
        return 0;
    }

    @Override
    public int muteAllRemoteVideoStreams(final boolean muted) {
        return 0;
    }

    @Override
    public int setDefaultMuteAllRemoteVideoStreams(final boolean muted) {
        return 0;
    }

    @Override
    public int setBeautyEffectOptions(final boolean enabled, final BeautyOptions options) {
        return 0;
    }

    @Override
    public int setDefaultAudioRoutetoSpeakerphone(final boolean defaultToSpeaker) {
        return 0;
    }

    @Override
    public int setEnableSpeakerphone(final boolean enabled) {
        return 0;
    }

    @Override
    public boolean isSpeakerphoneEnabled() {
        return false;
    }

    @Override
    public int enableInEarMonitoring(final boolean enabled) {
        return 0;
    }

    @Override
    public int setInEarMonitoringVolume(final int volume) {
        return 0;
    }

    @Override
    public int useExternalAudioDevice() {
        return 0;
    }

    @Override
    public int setLocalVoicePitch(final double pitch) {
        return 0;
    }

    @Override
    public int setLocalVoiceEqualization(final int bandFrequency, final int bandGain) {
        return 0;
    }

    @Override
    public int setLocalVoiceReverb(final int reverbKey, final int value) {
        return 0;
    }

    @Override
    public int setLocalVoiceChanger(final int voiceChanger) {
        return 0;
    }

    @Override
    public int setLocalVoiceReverbPreset(final int preset) {
        return 0;
    }

	@Override
	public int setAudioEffectPreset(int preset) {
		return 0;
	}

	@Override
	public int setVoiceBeautifierPreset(int preset) {
		return 0;
	}

	@Override
	public int setAudioEffectParameters(int preset, int param1, int param2) {
		return 0;
	}

	@Override
	public int setVoiceBeautifierParameters(int preset, int param1, int param2) {
		return 0;
	}

	@Override
	public int enableDeepLearningDenoise(boolean enabled) {
		return 0;
	}

	@Override
    public int enableSoundPositionIndication(final boolean enabled) {
        return 0;
    }

    @Override
    public int setRemoteVoicePosition(final int uid, final double pan, final double gain) {
        return 0;
    }

    @Override
    public int startAudioMixing(final String filePath, final boolean loopback, final boolean replace, final int cycle) {
        return 0;
    }

    @Override
    public int stopAudioMixing() {
        return 0;
    }

    @Override
    public int pauseAudioMixing() {
        return 0;
    }

    @Override
    public int resumeAudioMixing() {
        return 0;
    }

    @Override
    public int adjustAudioMixingVolume(final int volume) {
        return 0;
    }

    @Override
    public int adjustAudioMixingPlayoutVolume(final int volume) {
        return 0;
    }

    @Override
    public int adjustAudioMixingPublishVolume(final int volume) {
        return 0;
    }

    @Override
    public int getAudioMixingPlayoutVolume() {
        return 0;
    }

    @Override
    public int getAudioMixingPublishVolume() {
        return 0;
    }

    @Override
    public int getAudioMixingDuration() {
        return 0;
    }

    @Override
    public int getAudioMixingCurrentPosition() {
        return 0;
    }

    @Override
    public int setAudioMixingPosition(final int pos) {
        return 0;
    }

    @Override
    public int setAudioMixingPitch(final int pitch) {
        return 0;
    }

    @Override
    public IAudioEffectManager getAudioEffectManager() {
        return null;
    }

    @Override
    public int startAudioRecording(final String filePath, final int quality) {
        return 0;
    }

    @Override
    public int startAudioRecording(final String filePath, final int sampleRate, final int quality) {
        return 0;
    }

    @Override
    public int stopAudioRecording() {
        return 0;
    }

    @Override
    public int startEchoTest() {
        return 0;
    }

    @Override
    public int startEchoTest(final int intervalInSeconds) {
        return 0;
    }

    @Override
    public int stopEchoTest() {
        return 0;
    }

    @Override
    public int enableLastmileTest() {
        return 0;
    }

    @Override
    public int disableLastmileTest() {
        return 0;
    }

    @Override
    public int startLastmileProbeTest(final LastmileProbeConfig config) {
        return 0;
    }

    @Override
    public int stopLastmileProbeTest() {
        return 0;
    }

    @Override
    public int setVideoSource(final IVideoSource source) {
        return 0;
    }

    @Override
    public int setLocalVideoRenderer(final IVideoSink render) {
        return 0;
    }

    @Override
    public int setRemoteVideoRenderer(final int uid, final IVideoSink render) {
        return 0;
    }

    @Override
    public int setExternalAudioSink(final boolean enabled, final int sampleRate, final int channels) {
        return 0;
    }

    @Override
    public int pullPlaybackAudioFrame(final byte[] data, final int lengthInByte) {
        return 0;
    }

    @Override
    public int setExternalAudioSource(final boolean enabled, final int sampleRate, final int channels) {
        return 0;
    }

    @Override
    public int pushExternalAudioFrame(final byte[] data, final long timestamp) {
        return 0;
    }

    @Override
    public void setExternalVideoSource(final boolean enable, final boolean useTexture, final boolean pushMode) {

    }

    @Override
    public boolean pushExternalVideoFrame(final AgoraVideoFrame frame) {
        return false;
    }

    @Override
    public boolean isTextureEncodeSupported() {
        return false;
    }

    @Override
    public int registerAudioFrameObserver(final IAudioFrameObserver observer) {
        return 0;
    }

    @Override
    public int setRecordingAudioFrameParameters(final int sampleRate, final int channel, final int mode, final int samplesPerCall) {
        return 0;
    }

    @Override
    public int setPlaybackAudioFrameParameters(final int sampleRate, final int channel, final int mode, final int samplesPerCall) {
        return 0;
    }

    @Override
    public int setMixedAudioFrameParameters(final int sampleRate, final int samplesPerCall) {
        return 0;
    }

    @Override
    public int addVideoWatermark(final AgoraImage watermark) {
        return 0;
    }

    @Override
    public int addVideoWatermark(final String watermarkUrl, final WatermarkOptions options) {
        return 0;
    }

    @Override
    public int clearVideoWatermarks() {
        return 0;
    }

    @Override
    public int setRemoteUserPriority(final int uid, final int userPriority) {
        return 0;
    }

    @Override
    public int setLocalPublishFallbackOption(final int option) {
        return 0;
    }

    @Override
    public int setRemoteSubscribeFallbackOption(final int option) {
        return 0;
    }

    @Override
    public int enableDualStreamMode(final boolean enabled) {
        return 0;
    }

    @Override
    public int setRemoteVideoStreamType(final int uid, final int streamType) {
        return 0;
    }

    @Override
    public int setRemoteDefaultVideoStreamType(final int streamType) {
        return 0;
    }

    @Override
    public int setEncryptionSecret(final String secret) {
        return 0;
    }

    @Override
    public int setEncryptionMode(final String encryptionMode) {
        return 0;
    }

    @Override
    public int enableEncryption(final boolean enabled, final EncryptionConfig config) { return 0; }

    @Override
    public int addInjectStreamUrl(final String url, final LiveInjectStreamConfig config) {
        return 0;
    }

    @Override
    public int removeInjectStreamUrl(final String url) {
        return 0;
    }

    @Override
    public int addPublishStreamUrl(final String url, final boolean transcodingEnabled) {
        return 0;
    }

    @Override
    public int removePublishStreamUrl(final String url) {
        return 0;
    }

    @Override
    public int setLiveTranscoding(final LiveTranscoding transcoding) {
        return 0;
    }

    @Override
    public int createDataStream(final boolean reliable, final boolean ordered) {
        return 0;
    }

	@Override
	public int createDataStream(DataStreamConfig config) {
		return 0;
	}

	@Override
    public int sendStreamMessage(final int streamId, final byte[] message) {
        return 0;
    }

    @Override
    public int setVideoQualityParameters(final boolean preferFrameRateOverImageQuality) {
        return 0;
    }

    @Override
    public int setLocalVideoMirrorMode(final int mode) {
        return 0;
    }

    @Override
    public int switchCamera() {
        return 0;
    }

    @Override
    public boolean isCameraZoomSupported() {
        return false;
    }

    @Override
    public boolean isCameraTorchSupported() {
        return false;
    }

    @Override
    public boolean isCameraFocusSupported() {
        return false;
    }

    @Override
    public boolean isCameraExposurePositionSupported() {
        return false;
    }

    @Override
    public boolean isCameraAutoFocusFaceModeSupported() {
        return false;
    }

    @Override
    public int setCameraZoomFactor(final float factor) {
        return 0;
    }

    @Override
    public float getCameraMaxZoomFactor() {
        return 0;
    }

    @Override
    public int setCameraFocusPositionInPreview(final float positionX, final float positionY) {
        return 0;
    }

    @Override
    public int setCameraExposurePosition(final float positionXinView, final float positionYinView) {
        return 0;
    }

    @Override
    public int enableFaceDetection(final boolean enable) {
        return 0;
    }

    @Override
    public int setCameraTorchOn(final boolean isOn) {
        return 0;
    }

    @Override
    public int setCameraAutoFocusFaceModeEnabled(final boolean enabled) {
        return 0;
    }

    @Override
    public String getCallId() {
        return null;
    }

    @Override
    public int rate(final String callId, final int rating, final String description) {
        return 0;
    }

    @Override
    public int complain(final String callId, final String description) {
        return 0;
    }

    @Override
    public int setLogFile(final String filePath) {
        return 0;
    }

    @Override
    public int setLogFilter(final int filter) {
        return 0;
    }

    @Override
    public int setLogFileSize(final int fileSizeInKBytes) {
        return 0;
    }

	@Override
	public String uploadLogFile() {
		return null;
	}

	@Override
    public long getNativeHandle() {
        return 0;
    }

    @Override
    public boolean enableHighPerfWifiMode(final boolean enable) {
        return false;
    }

    @Override
    public void monitorHeadsetEvent(final boolean monitor) {

    }

    @Override
    public void monitorBluetoothHeadsetEvent(final boolean monitor) {

    }

    @Override
    public void setPreferHeadset(final boolean enabled) {

    }

    @Override
    public int setParameters(final String parameters) {
        return 0;
    }

    @Override
    public String getParameter(final String parameter, final String args) {
        return null;
    }

    @Override
    public int registerMediaMetadataObserver(final IMetadataObserver observer, final int type) {
        return 0;
    }

    @Override
    public int startChannelMediaRelay(final ChannelMediaRelayConfiguration channelMediaRelayConfiguration) {
        return 0;
    }

    @Override
    public int stopChannelMediaRelay() {
        return 0;
    }

    @Override
    public int updateChannelMediaRelay(final ChannelMediaRelayConfiguration channelMediaRelayConfiguration) {
        return 0;
    }

    @Override
    public int startDumpVideoReceiveTrack(final int uid, final String dumpFile) {
        return 0;
    }

    @Override
    public int stopDumpVideoReceiveTrack() {
        return 0;
    }

    @Override
    public RtcChannel createRtcChannel(final String channelId) {
        return null;
    }
}
