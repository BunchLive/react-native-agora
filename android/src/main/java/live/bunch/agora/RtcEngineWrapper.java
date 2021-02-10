package live.bunch.agora;

import javax.microedition.khronos.egl.EGLContext;

import io.agora.rtc.IAudioEffectManager;
import io.agora.rtc.IAudioFrameObserver;
import io.agora.rtc.IMetadataObserver;
import io.agora.rtc.IRtcEngineEventHandler;
import io.agora.rtc.RtcEngine;
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

public class RtcEngineWrapper extends RtcEngineEx {

    private final RtcEngine mRtcEngine;

    public RtcEngineWrapper(final RtcEngine rtcEngine) {
        mRtcEngine = rtcEngine;
    }

    public RtcEngineWrapper(final RtcEngineEx rtcEngine) {
        this((RtcEngine) rtcEngine);
    }

    //region RtcEngineEx

    public int setProfile(String profile, boolean merge) {
        if (mRtcEngine instanceof RtcEngineEx) return ((RtcEngineEx)mRtcEngine).setProfile(profile, merge);
        return -1;
    }


    public int setAppType(int appType) {
        if (mRtcEngine instanceof RtcEngineEx) return ((RtcEngineEx)mRtcEngine).setAppType(appType);
        return -1;
    }

    public int enableTransportQualityIndication(boolean enabled) {
        if (mRtcEngine instanceof RtcEngineEx) return ((RtcEngineEx)mRtcEngine).enableTransportQualityIndication(enabled);
        return -1;
    }

    public int playRecap() {
        if (mRtcEngine instanceof RtcEngineEx) return ((RtcEngineEx)mRtcEngine).playRecap();
        return -1;
    }

    public int enableRecap(int interval) {
        if (mRtcEngine instanceof RtcEngineEx) return ((RtcEngineEx)mRtcEngine).enableRecap(interval);
        return -1;
    }

    public String getParameters(String parameters) {
        if (mRtcEngine instanceof RtcEngineEx) return ((RtcEngineEx)mRtcEngine).getParameters(parameters);
        return null;
    }

    public String makeQualityReportUrl(String channel, int listenerUid, int speakerUid, int format) {
        if (mRtcEngine instanceof RtcEngineEx) return ((RtcEngineEx)mRtcEngine).makeQualityReportUrl(channel, listenerUid, speakerUid, format);
        return null;
    }

    public int updateSharedContext(EGLContext sharedContext) {
        if (mRtcEngine instanceof RtcEngineEx) return ((RtcEngineEx)mRtcEngine).updateSharedContext(sharedContext);
        return -1;
    }

    public int updateSharedContext(android.opengl.EGLContext sharedContext) {
        if (mRtcEngine instanceof RtcEngineEx) return ((RtcEngineEx)mRtcEngine).updateSharedContext(sharedContext);
        return -1;
    }

    public int setTextureId(int id, EGLContext eglContext, int width, int height, long ts) {
        if (mRtcEngine instanceof RtcEngineEx) return ((RtcEngineEx)mRtcEngine).setTextureId(id, eglContext, width, height, ts);
        return -1;
    }

    public int setTextureId(int id, android.opengl.EGLContext eglContext, int width, int height, long ts) {
        if (mRtcEngine instanceof RtcEngineEx) return ((RtcEngineEx)mRtcEngine).setTextureId(id, eglContext, width, height, ts);
        return -1;
    }

    public int monitorAudioRouteChange(boolean isMonitoring) {
        if (mRtcEngine instanceof RtcEngineEx) return ((RtcEngineEx)mRtcEngine).monitorAudioRouteChange(isMonitoring);
        return -1;
    }

    public int setApiCallMode(int syncCallTimeout) {
        if (mRtcEngine instanceof RtcEngineEx) return ((RtcEngineEx)mRtcEngine).setApiCallMode(syncCallTimeout);
        return -1;
    }

    //endregion

    //region RtcEngine

    public void addHandler(IRtcEngineEventHandler handler) {
        mRtcEngine.addHandler(handler);
    }

    public int setChannelProfile(int profile) {
        return mRtcEngine.setChannelProfile(profile);
    }

    public int setClientRole(int role) {
        return mRtcEngine.setClientRole(role);
    }

	@Override
	public int setClientRole(int role, ClientRoleOptions options) {
		return 0;
	}

	@Override
    public int sendCustomReportMessage(String id, String category, String event, String label, int value) {
        return 0;
    }

    public int joinChannel(String token, String channelName, String optionalInfo, int optionalUid) {
        return mRtcEngine.joinChannel(token, channelName, optionalInfo, optionalUid);
    }

	@Override
	public int joinChannel(String token, String channelName, String optionalInfo, int optionalUid, ChannelMediaOptions options) {
		return 0;
	}

	public int switchChannel(String token, String channelName) {
        return mRtcEngine.switchChannel(token, channelName);
    }

	@Override
	public int switchChannel(String token, String channelName, ChannelMediaOptions options) {
		return 0;
	}

	public int leaveChannel() {
        return mRtcEngine.leaveChannel();
    }

    public int renewToken(String token) {
        return mRtcEngine.renewToken(token);
    }

    public int registerLocalUserAccount(String appId, String userAccount) {
        return mRtcEngine.registerLocalUserAccount(appId, userAccount);
    }

    public int joinChannelWithUserAccount(String token, String channelName, String userAccount) {
        return mRtcEngine.joinChannelWithUserAccount(token, channelName, userAccount);
    }

	@Override
	public int joinChannelWithUserAccount(String token, String channelName, String userAccount, ChannelMediaOptions options) {
		return 0;
	}

	@Override
	public int setCloudProxy(int proxyType) {
		return 0;
	}

	public int getUserInfoByUserAccount(String userAccount, UserInfo userInfo) {
        return mRtcEngine.getUserInfoByUserAccount(userAccount, userInfo);
    }

    public int getUserInfoByUid(int uid, UserInfo userInfo) {
        return mRtcEngine.getUserInfoByUid(uid, userInfo);
    }

    public int enableWebSdkInteroperability(boolean enabled) {
        return mRtcEngine.enableWebSdkInteroperability(enabled);
    }

    public int getConnectionState() {
        return mRtcEngine.getConnectionState();
    }

	@Override
	public int enableRemoteSuperResolution(int uid, boolean enable) {
		return 0;
	}

	public int enableAudio() {
        return mRtcEngine.enableAudio();
    }

    public int disableAudio() {
        return mRtcEngine.disableAudio();
    }

    public int pauseAudio() {
        return mRtcEngine.pauseAudio();
    }

    public int resumeAudio() {
        return mRtcEngine.resumeAudio();
    }

    public int setAudioProfile(int profile, int scenario) {
        return mRtcEngine.setAudioProfile(profile, scenario);
    }

    public int setHighQualityAudioParameters(boolean fullband, boolean stereo, boolean fullBitrate) {
        return mRtcEngine.setHighQualityAudioParameters(fullband, stereo, fullBitrate);
    }

    public int adjustRecordingSignalVolume(int volume) {
        return mRtcEngine.adjustRecordingSignalVolume(volume);
    }

    public int adjustPlaybackSignalVolume(int volume) {
        return mRtcEngine.adjustPlaybackSignalVolume(volume);
    }

    public int enableAudioVolumeIndication(int interval, int smooth, boolean report_vad) {
        return mRtcEngine.enableAudioVolumeIndication(interval, smooth, report_vad);
    }

    public int enableAudioQualityIndication(boolean enabled) {
        return mRtcEngine.enableAudioQualityIndication(enabled);
    }

    public int enableLocalAudio(boolean enabled) {
        return mRtcEngine.enableLocalAudio(enabled);
    }

    public int muteLocalAudioStream(boolean muted) {
        return mRtcEngine.muteLocalAudioStream(muted);
    }

    public int muteRemoteAudioStream(int uid, boolean muted) {
        return mRtcEngine.muteRemoteAudioStream(uid, muted);
    }

    public int muteAllRemoteAudioStreams(boolean muted) {
        return mRtcEngine.muteAllRemoteAudioStreams(muted);
    }

    public int setDefaultMuteAllRemoteAudioStreams(boolean muted) {
        return mRtcEngine.setDefaultMuteAllRemoteAudioStreams(muted);
    }

    public int enableVideo() {
        return mRtcEngine.enableVideo();
    }

    public int disableVideo() {
        return mRtcEngine.disableVideo();
    }

    public int setVideoProfile(int profile, boolean swapWidthAndHeight) {
        return mRtcEngine.setVideoProfile(profile, swapWidthAndHeight);
    }

    public int setVideoProfile(int width, int height, int frameRate, int bitrate) {
        return mRtcEngine.setVideoProfile(width, height, frameRate, bitrate);
    }

    public int setVideoEncoderConfiguration(VideoEncoderConfiguration config) {
        return mRtcEngine.setVideoEncoderConfiguration(config);
    }

    public int setCameraCapturerConfiguration(CameraCapturerConfiguration config) {
        return mRtcEngine.setCameraCapturerConfiguration(config);
    }

    public int setupLocalVideo(VideoCanvas local) {
        return mRtcEngine.setupLocalVideo(local);
    }

    public int setupRemoteVideo(VideoCanvas remote) {
        return mRtcEngine.setupRemoteVideo(remote);
    }

    public int setLocalRenderMode(int mode) {
        return mRtcEngine.setLocalRenderMode(mode);
    }

    public int setRemoteRenderMode(int uid, int mode) {
        return mRtcEngine.setRemoteRenderMode(uid, mode);
    }

    public int startPreview() {
        return mRtcEngine.startPreview();
    }

    public int stopPreview() {
        return mRtcEngine.stopPreview();
    }

    public int enableLocalVideo(boolean enabled) {
        return mRtcEngine.enableLocalVideo(enabled);
    }

    public int muteLocalVideoStream(boolean muted) {
        return mRtcEngine.muteLocalVideoStream(muted);
    }

    public int muteRemoteVideoStream(int uid, boolean muted) {
        return mRtcEngine.muteRemoteVideoStream(uid, muted);
    }

    public int muteAllRemoteVideoStreams(boolean muted) {
        return mRtcEngine.muteAllRemoteVideoStreams(muted);
    }

    public int setDefaultMuteAllRemoteVideoStreams(boolean muted) {
        return mRtcEngine.setDefaultMuteAllRemoteVideoStreams(muted);
    }

    public int setBeautyEffectOptions(boolean enabled, BeautyOptions options) {
        return mRtcEngine.setBeautyEffectOptions(enabled, options);
    }

    public int setDefaultAudioRoutetoSpeakerphone(boolean defaultToSpeaker) {
        return mRtcEngine.setDefaultAudioRoutetoSpeakerphone(defaultToSpeaker);
    }

    public int setEnableSpeakerphone(boolean enabled) {
        return mRtcEngine.setEnableSpeakerphone(enabled);
    }

    public boolean isSpeakerphoneEnabled() {
        return mRtcEngine.isSpeakerphoneEnabled();
    }

    public int enableInEarMonitoring(boolean enabled) {
        return mRtcEngine.enableInEarMonitoring(enabled);
    }

    public int setInEarMonitoringVolume(int volume) {
        return mRtcEngine.setInEarMonitoringVolume(volume);
    }

    public int useExternalAudioDevice() {
        return mRtcEngine.useExternalAudioDevice();
    }

    public int setLocalVoicePitch(double pitch) {
        return mRtcEngine.setLocalVoicePitch(pitch);
    }

    public int setLocalVoiceEqualization(int bandFrequency, int bandGain) {
        return mRtcEngine.setLocalVoiceEqualization(bandFrequency, bandGain);
    }

    public int setLocalVoiceReverb(int reverbKey, int value) {
        return mRtcEngine.setLocalVoiceReverb(reverbKey, value);
    }

    public int setLocalVoiceChanger(int voiceChanger) {
        return mRtcEngine.setLocalVoiceChanger(voiceChanger);
    }

    public int setLocalVoiceReverbPreset(int preset) {
        return mRtcEngine.setLocalVoiceReverbPreset(preset);
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

	public int enableSoundPositionIndication(boolean enabled) {
        return mRtcEngine.enableSoundPositionIndication(enabled);
    }

    public int setRemoteVoicePosition(int uid, double pan, double gain) {
        return mRtcEngine.setRemoteVoicePosition(uid, pan, gain);
    }

    public int startAudioMixing(String filePath, boolean loopback, boolean replace, int cycle) {
        return mRtcEngine.startAudioMixing(filePath, loopback, replace, cycle);
    }

    public int stopAudioMixing() {
        return mRtcEngine.stopAudioMixing();
    }

    public int pauseAudioMixing() {
        return mRtcEngine.pauseAudioMixing();
    }

    public int resumeAudioMixing() {
        return mRtcEngine.resumeAudioMixing();
    }

    public int adjustAudioMixingVolume(int volume) {
        return mRtcEngine.adjustAudioMixingVolume(volume);
    }

    public int adjustAudioMixingPlayoutVolume(int volume) {
        return mRtcEngine.adjustAudioMixingPlayoutVolume(volume);
    }

    public int adjustAudioMixingPublishVolume(int volume) {
        return mRtcEngine.adjustAudioMixingPublishVolume(volume);
    }

    public int getAudioMixingPlayoutVolume() {
        return mRtcEngine.getAudioMixingPlayoutVolume();
    }

    public int getAudioMixingPublishVolume() {
        return mRtcEngine.getAudioMixingPublishVolume();
    }

    public int getAudioMixingDuration() {
        return mRtcEngine.getAudioMixingDuration();
    }

    public int getAudioMixingCurrentPosition() {
        return mRtcEngine.getAudioMixingCurrentPosition();
    }

    public int setAudioMixingPosition(int pos) {
        return mRtcEngine.setAudioMixingPosition(pos);
    }

    public int setAudioMixingPitch(final int pitch) {
        return mRtcEngine.setAudioMixingPitch(pitch);
    }

    public IAudioEffectManager getAudioEffectManager() {
        return mRtcEngine.getAudioEffectManager();
    }

    public int startAudioRecording(String filePath, int quality) {
        return mRtcEngine.startAudioRecording(filePath, quality);
    }

    public int stopAudioRecording() {
        return mRtcEngine.stopAudioRecording();
    }

    public int startEchoTest() {
        return mRtcEngine.startEchoTest();
    }

    public int startEchoTest(int intervalInSeconds) {
        return mRtcEngine.startEchoTest(intervalInSeconds);
    }

    public int stopEchoTest() {
        return mRtcEngine.stopEchoTest();
    }

    public int enableLastmileTest() {
        return mRtcEngine.enableLastmileTest();
    }

    public int disableLastmileTest() {
        return mRtcEngine.disableLastmileTest();
    }

    public int startLastmileProbeTest(LastmileProbeConfig config) {
        return mRtcEngine.startLastmileProbeTest(config);
    }

    public int stopLastmileProbeTest() {
        return mRtcEngine.stopLastmileProbeTest();
    }

    public int setVideoSource(IVideoSource source) {
        return mRtcEngine.setVideoSource(source);
    }

    public int setLocalVideoRenderer(IVideoSink render) {
        return mRtcEngine.setLocalVideoRenderer(render);
    }

    public int setRemoteVideoRenderer(int uid, IVideoSink render) {
        return mRtcEngine.setRemoteVideoRenderer(uid, render);
    }

    public int setExternalAudioSink(boolean enabled, int sampleRate, int channels) {
        return mRtcEngine.setExternalAudioSink(enabled, sampleRate, channels);
    }

    public int pullPlaybackAudioFrame(byte[] data, int lengthInByte) {
        return mRtcEngine.pullPlaybackAudioFrame(data, lengthInByte);
    }

    public int setExternalAudioSource(boolean enabled, int sampleRate, int channels) {
        return mRtcEngine.setExternalAudioSource(enabled, sampleRate, channels);
    }

    public int pushExternalAudioFrame(byte[] data, long timestamp) {
        return mRtcEngine.pushExternalAudioFrame(data, timestamp);
    }

    public void setExternalVideoSource(boolean enable, boolean useTexture, boolean pushMode) {
        mRtcEngine.setExternalVideoSource(enable, useTexture, pushMode);
    }

    public boolean pushExternalVideoFrame(AgoraVideoFrame frame) {
        return mRtcEngine.pushExternalVideoFrame(frame);
    }

    public boolean isTextureEncodeSupported() {
        return mRtcEngine.isTextureEncodeSupported();
    }

    public int registerAudioFrameObserver(IAudioFrameObserver observer) {
        return mRtcEngine.registerAudioFrameObserver(observer);
    }

    public int setRecordingAudioFrameParameters(int sampleRate, int channel, int mode, int samplesPerCall) {
        return mRtcEngine.setRecordingAudioFrameParameters(sampleRate, channel, mode, samplesPerCall);
    }

    public int setPlaybackAudioFrameParameters(int sampleRate, int channel, int mode, int samplesPerCall) {
        return mRtcEngine.setPlaybackAudioFrameParameters(sampleRate, channel, mode, samplesPerCall);
    }

    public int setMixedAudioFrameParameters(int sampleRate, int samplesPerCall) {
        return mRtcEngine.setMixedAudioFrameParameters(sampleRate, samplesPerCall);
    }

    public int addVideoWatermark(AgoraImage watermark) {
        return mRtcEngine.addVideoWatermark(watermark);
    }

    public int clearVideoWatermarks() {
        return mRtcEngine.clearVideoWatermarks();
    }

    public int setRemoteUserPriority(int uid, int userPriority) {
        return mRtcEngine.setRemoteUserPriority(uid, userPriority);
    }

    public int setLocalPublishFallbackOption(int option) {
        return mRtcEngine.setLocalPublishFallbackOption(option);
    }

    public int setRemoteSubscribeFallbackOption(int option) {
        return mRtcEngine.setRemoteSubscribeFallbackOption(option);
    }

    public int enableDualStreamMode(boolean enabled) {
        return mRtcEngine.enableDualStreamMode(enabled);
    }

    public int setRemoteVideoStreamType(int uid, int streamType) {
        return mRtcEngine.setRemoteVideoStreamType(uid, streamType);
    }

    public int setRemoteDefaultVideoStreamType(int streamType) {
        return mRtcEngine.setRemoteDefaultVideoStreamType(streamType);
    }

    public int setEncryptionSecret(String secret) {
        return mRtcEngine.setEncryptionSecret(secret);
    }

    public int setEncryptionMode(String encryptionMode) {
        return mRtcEngine.setEncryptionMode(encryptionMode);
    }

    @Override
    public int enableEncryption(boolean enabled, EncryptionConfig config) {
        return 0;
    }

    public int addInjectStreamUrl(String url, LiveInjectStreamConfig config) {
        return mRtcEngine.addInjectStreamUrl(url, config);
    }

    public int removeInjectStreamUrl(String url) {
        return mRtcEngine.removeInjectStreamUrl(url);
    }

    public int addPublishStreamUrl(String url, boolean transcodingEnabled) {
        return mRtcEngine.addPublishStreamUrl(url, transcodingEnabled);
    }

    public int removePublishStreamUrl(String url) {
        return mRtcEngine.removePublishStreamUrl(url);
    }

    public int setLiveTranscoding(LiveTranscoding transcoding) {
        return mRtcEngine.setLiveTranscoding(transcoding);
    }

    public int createDataStream(boolean reliable, boolean ordered) {
        return mRtcEngine.createDataStream(reliable, ordered);
    }

	@Override
	public int createDataStream(DataStreamConfig config) {
		return 0;
	}

	public int sendStreamMessage(int streamId, byte[] message) {
        return mRtcEngine.sendStreamMessage(streamId, message);
    }

    public int setVideoQualityParameters(boolean preferFrameRateOverImageQuality) {
        return mRtcEngine.setVideoQualityParameters(preferFrameRateOverImageQuality);
    }

    public int setLocalVideoMirrorMode(int mode) {
        return mRtcEngine.setLocalVideoMirrorMode(mode);
    }

    public int switchCamera() {
        return mRtcEngine.switchCamera();
    }

    public boolean isCameraZoomSupported() {
        return mRtcEngine.isCameraZoomSupported();
    }

    public boolean isCameraTorchSupported() {
        return mRtcEngine.isCameraTorchSupported();
    }

    public boolean isCameraFocusSupported() {
        return mRtcEngine.isCameraFocusSupported();
    }

    public boolean isCameraExposurePositionSupported() {
        return mRtcEngine.isCameraExposurePositionSupported();
    }

    public boolean isCameraAutoFocusFaceModeSupported() {
        return mRtcEngine.isCameraAutoFocusFaceModeSupported();
    }

    public int setCameraZoomFactor(float factor) {
        return mRtcEngine.setCameraZoomFactor(factor);
    }

    public float getCameraMaxZoomFactor() {
        return mRtcEngine.getCameraMaxZoomFactor();
    }

    public int setCameraFocusPositionInPreview(float positionX, float positionY) {
        return mRtcEngine.setCameraFocusPositionInPreview(positionX, positionY);
    }

    public int setCameraExposurePosition(float positionXinView, float positionYinView) {
        return mRtcEngine.setCameraExposurePosition(positionXinView, positionYinView);
    }

    public int enableFaceDetection(final boolean enable) {
        return mRtcEngine.enableFaceDetection(enable);
    }

    public int setCameraTorchOn(boolean isOn) {
        return mRtcEngine.setCameraTorchOn(isOn);
    }

    public int setCameraAutoFocusFaceModeEnabled(boolean enabled) {
        return mRtcEngine.setCameraAutoFocusFaceModeEnabled(enabled);
    }

    public String getCallId() {
        return mRtcEngine.getCallId();
    }

    public int rate(String callId, int rating, String description) {
        return mRtcEngine.rate(callId, rating, description);
    }

    public int complain(String callId, String description) {
        return mRtcEngine.complain(callId, description);
    }

    public int setLogFile(String filePath) {
        return mRtcEngine.setLogFile(filePath);
    }

    public int setLogFilter(int filter) {
        return mRtcEngine.setLogFilter(filter);
    }

    public int setLogFileSize(int fileSizeInKBytes) {
        return mRtcEngine.setLogFileSize(fileSizeInKBytes);
    }

	@Override
	public String uploadLogFile() {
		return null;
	}

	public long getNativeHandle() {
        return mRtcEngine.getNativeHandle();
    }

    public boolean enableHighPerfWifiMode(boolean enable) {
        return mRtcEngine.enableHighPerfWifiMode(enable);
    }

    public void monitorHeadsetEvent(boolean monitor) {
        mRtcEngine.monitorHeadsetEvent(monitor);
    }

    public void monitorBluetoothHeadsetEvent(boolean monitor) {
        mRtcEngine.monitorBluetoothHeadsetEvent(monitor);
    }

    public void setPreferHeadset(boolean enabled) {
        mRtcEngine.setPreferHeadset(enabled);
    }

    public int setParameters(String parameters) {
        return mRtcEngine.setParameters(parameters);
    }

    public String getParameter(String parameter, String args) {
        return mRtcEngine.getParameter(parameter, args);
    }

    public int registerMediaMetadataObserver(IMetadataObserver observer, int type) {
        return mRtcEngine.registerMediaMetadataObserver(observer, type);
    }

    public int startChannelMediaRelay(ChannelMediaRelayConfiguration channelMediaRelayConfiguration) {
        return mRtcEngine.startChannelMediaRelay(channelMediaRelayConfiguration);
    }

    public int stopChannelMediaRelay() {
        return mRtcEngine.stopChannelMediaRelay();
    }

    public int updateChannelMediaRelay(ChannelMediaRelayConfiguration channelMediaRelayConfiguration) {
        return mRtcEngine.updateChannelMediaRelay(channelMediaRelayConfiguration);
    }

    public RtcChannel createRtcChannel(String channel) {
        return mRtcEngine.createRtcChannel(channel);
    }

    public int startDumpVideoReceiveTrack(int uid, String dumpFile) {
        return mRtcEngine.startDumpVideoReceiveTrack(uid, dumpFile);
    }

    public int stopDumpVideoReceiveTrack() {
        return mRtcEngine.stopDumpVideoReceiveTrack();
    }

    public int addVideoWatermark (String watermarkUrl, WatermarkOptions options) {
        return mRtcEngine.addVideoWatermark(watermarkUrl, options);
    }

    public int startAudioRecording (String filePath, int sampleRate, int quality)  {
        return mRtcEngine.startAudioRecording(filePath, sampleRate, quality);
    }

    public int setRemoteRenderMode (int uid, int renderMode, int mirrorMode) {
        return mRtcEngine.setRemoteRenderMode(uid, renderMode, mirrorMode);
    }

    public int setLocalRenderMode (int renderMode, int mirrorMode) {
        return mRtcEngine.setLocalRenderMode(renderMode, mirrorMode);
    }

    public int adjustUserPlaybackSignalVolume (int uid, int volume) {
        return mRtcEngine.adjustUserPlaybackSignalVolume(uid, volume);
    }
    //endregion
}
