package live.bunch.agora;

import io.agora.rtc.IAudioEffectManager;
import io.agora.rtc.IAudioFrameObserver;
import io.agora.rtc.IMetadataObserver;
import io.agora.rtc.IRtcEngineEventHandler;
import io.agora.rtc.PublisherConfiguration;
import io.agora.rtc.RtcEngine;
import io.agora.rtc.internal.LastmileProbeConfig;
import io.agora.rtc.live.LiveInjectStreamConfig;
import io.agora.rtc.live.LiveTranscoding;
import io.agora.rtc.mediaio.IVideoSink;
import io.agora.rtc.mediaio.IVideoSource;
import io.agora.rtc.models.UserInfo;
import io.agora.rtc.video.AgoraImage;
import io.agora.rtc.video.AgoraVideoFrame;
import io.agora.rtc.video.BeautyOptions;
import io.agora.rtc.video.CameraCapturerConfiguration;
import io.agora.rtc.video.VideoCanvas;
import io.agora.rtc.video.VideoCompositingLayout;
import io.agora.rtc.video.VideoEncoderConfiguration;

public class RtcEngineWrapper extends io.agora.rtc.RtcEngine {

    private final io.agora.rtc.RtcEngine rtcEngine;

    public RtcEngineWrapper(RtcEngine rtcEngine) {
        this.rtcEngine = rtcEngine;
    }

    public int setChannelProfile(int profile) {
        return rtcEngine.setChannelProfile(profile);
    }

    public int setClientRole(int role) {
        return rtcEngine.setClientRole(role);
    }

    public int joinChannel(String token, String channelName, String optionalInfo, int optionalUid) {
        return rtcEngine.joinChannel(token, channelName, optionalInfo, optionalUid);
    }

    public int leaveChannel() {
        return rtcEngine.leaveChannel();
    }

    public int renewToken(String token) {
        return rtcEngine.renewToken(token);
    }

    public int registerLocalUserAccount(String appId, String userAccount) {
        return rtcEngine.registerLocalUserAccount(appId, userAccount);
    }

    public int joinChannelWithUserAccount(String token, String channelName, String userAccount) {
        return rtcEngine.joinChannelWithUserAccount(token, channelName, userAccount);
    }

    public int getUserInfoByUserAccount(String userAccount, UserInfo userInfo) {
        return rtcEngine.getUserInfoByUserAccount(userAccount, userInfo);
    }

    public int getUserInfoByUid(int uid, UserInfo userInfo) {
        return rtcEngine.getUserInfoByUid(uid, userInfo);
    }

    public int enableWebSdkInteroperability(boolean enabled) {
        return rtcEngine.enableWebSdkInteroperability(enabled);
    }

    public int getConnectionState() {
        return rtcEngine.getConnectionState();
    }

    public int enableAudio() {
        return rtcEngine.enableAudio();
    }

    public int disableAudio() {
        return rtcEngine.disableAudio();
    }

    public int pauseAudio() {
        return rtcEngine.pauseAudio();
    }

    public int resumeAudio() {
        return rtcEngine.resumeAudio();
    }

    public int setAudioProfile(int profile, int scenario) {
        return rtcEngine.setAudioProfile(profile, scenario);
    }

    public int setHighQualityAudioParameters(boolean fullband, boolean stereo, boolean fullBitrate) {
        return rtcEngine.setHighQualityAudioParameters(fullband, stereo, fullBitrate);
    }

    public int adjustRecordingSignalVolume(int volume) {
        return rtcEngine.adjustRecordingSignalVolume(volume);
    }

    public int adjustPlaybackSignalVolume(int volume) {
        return rtcEngine.adjustPlaybackSignalVolume(volume);
    }

    public int enableAudioVolumeIndication(int interval, int smooth) {
        return rtcEngine.enableAudioVolumeIndication(interval, smooth);
    }

    public int enableAudioQualityIndication(boolean enabled) {
        return rtcEngine.enableAudioQualityIndication(enabled);
    }

    public int enableLocalAudio(boolean enabled) {
        return rtcEngine.enableLocalAudio(enabled);
    }

    public int muteLocalAudioStream(boolean muted) {
        return rtcEngine.muteLocalAudioStream(muted);
    }

    public int muteRemoteAudioStream(int uid, boolean muted) {
        return rtcEngine.muteRemoteAudioStream(uid, muted);
    }

    public int muteAllRemoteAudioStreams(boolean muted) {
        return rtcEngine.muteAllRemoteAudioStreams(muted);
    }

    public int setDefaultMuteAllRemoteAudioStreams(boolean muted) {
        return rtcEngine.setDefaultMuteAllRemoteAudioStreams(muted);
    }

    public int enableVideo() {
        return rtcEngine.enableVideo();
    }

    public int disableVideo() {
        return rtcEngine.disableVideo();
    }

    public int setVideoProfile(int profile, boolean swapWidthAndHeight) {
        return rtcEngine.setVideoProfile(profile, swapWidthAndHeight);
    }

    public int setVideoProfile(int width, int height, int frameRate, int bitrate) {
        return rtcEngine.setVideoProfile(width, height, frameRate, bitrate);
    }

    public int setVideoEncoderConfiguration(VideoEncoderConfiguration config) {
        return rtcEngine.setVideoEncoderConfiguration(config);
    }

    public int setCameraCapturerConfiguration(CameraCapturerConfiguration config) {
        return rtcEngine.setCameraCapturerConfiguration(config);
    }

    public int setupLocalVideo(VideoCanvas local) {
        return rtcEngine.setupLocalVideo(local);
    }

    public int setupRemoteVideo(VideoCanvas remote) {
        return rtcEngine.setupRemoteVideo(remote);
    }

    public int setLocalRenderMode(int mode) {
        return rtcEngine.setLocalRenderMode(mode);
    }

    public int setRemoteRenderMode(int uid, int mode) {
        return rtcEngine.setRemoteRenderMode(uid, mode);
    }

    public int startPreview() {
        return rtcEngine.startPreview();
    }

    public int stopPreview() {
        return rtcEngine.stopPreview();
    }

    public int enableLocalVideo(boolean enabled) {
        return rtcEngine.enableLocalVideo(enabled);
    }

    public int muteLocalVideoStream(boolean muted) {
        return rtcEngine.muteLocalVideoStream(muted);
    }

    public int muteRemoteVideoStream(int uid, boolean muted) {
        return rtcEngine.muteRemoteVideoStream(uid, muted);
    }

    public int muteAllRemoteVideoStreams(boolean muted) {
        return rtcEngine.muteAllRemoteVideoStreams(muted);
    }

    public int setDefaultMuteAllRemoteVideoStreams(boolean muted) {
        return rtcEngine.setDefaultMuteAllRemoteVideoStreams(muted);
    }

    public int setBeautyEffectOptions(boolean enabled, BeautyOptions options) {
        return rtcEngine.setBeautyEffectOptions(enabled, options);
    }

    public int setDefaultAudioRoutetoSpeakerphone(boolean defaultToSpeaker) {
        return rtcEngine.setDefaultAudioRoutetoSpeakerphone(defaultToSpeaker);
    }

    public int setEnableSpeakerphone(boolean enabled) {
        return rtcEngine.setEnableSpeakerphone(enabled);
    }

    public boolean isSpeakerphoneEnabled() {
        return rtcEngine.isSpeakerphoneEnabled();
    }

    public int enableInEarMonitoring(boolean enabled) {
        return rtcEngine.enableInEarMonitoring(enabled);
    }

    public int setInEarMonitoringVolume(int volume) {
        return rtcEngine.setInEarMonitoringVolume(volume);
    }

    public int useExternalAudioDevice() {
        return rtcEngine.useExternalAudioDevice();
    }

    public int setLocalVoicePitch(double pitch) {
        return rtcEngine.setLocalVoicePitch(pitch);
    }

    public int setLocalVoiceEqualization(int bandFrequency, int bandGain) {
        return rtcEngine.setLocalVoiceEqualization(bandFrequency, bandGain);
    }

    public int setLocalVoiceReverb(int reverbKey, int value) {
        return rtcEngine.setLocalVoiceReverb(reverbKey, value);
    }

    public int setLocalVoiceChanger(int voiceChanger) {
        return rtcEngine.setLocalVoiceChanger(voiceChanger);
    }

    public int setLocalVoiceReverbPreset(int preset) {
        return rtcEngine.setLocalVoiceReverbPreset(preset);
    }

    public int enableSoundPositionIndication(boolean enabled) {
        return rtcEngine.enableSoundPositionIndication(enabled);
    }

    public int setRemoteVoicePosition(int uid, double pan, double gain) {
        return rtcEngine.setRemoteVoicePosition(uid, pan, gain);
    }

    public int startAudioMixing(String filePath, boolean loopback, boolean replace, int cycle) {
        return rtcEngine.startAudioMixing(filePath, loopback, replace, cycle);
    }

    public int stopAudioMixing() {
        return rtcEngine.stopAudioMixing();
    }

    public int pauseAudioMixing() {
        return rtcEngine.pauseAudioMixing();
    }

    public int resumeAudioMixing() {
        return rtcEngine.resumeAudioMixing();
    }

    public int adjustAudioMixingVolume(int volume) {
        return rtcEngine.adjustAudioMixingVolume(volume);
    }

    public int adjustAudioMixingPlayoutVolume(int volume) {
        return rtcEngine.adjustAudioMixingPlayoutVolume(volume);
    }

    public int adjustAudioMixingPublishVolume(int volume) {
        return rtcEngine.adjustAudioMixingPublishVolume(volume);
    }

    public int getAudioMixingPlayoutVolume() {
        return rtcEngine.getAudioMixingPlayoutVolume();
    }

    public int getAudioMixingPublishVolume() {
        return rtcEngine.getAudioMixingPublishVolume();
    }

    public int getAudioMixingDuration() {
        return rtcEngine.getAudioMixingDuration();
    }

    public int getAudioMixingCurrentPosition() {
        return rtcEngine.getAudioMixingCurrentPosition();
    }

    public int setAudioMixingPosition(int pos) {
        return rtcEngine.setAudioMixingPosition(pos);
    }

    public IAudioEffectManager getAudioEffectManager() {
        return rtcEngine.getAudioEffectManager();
    }

    public int startAudioRecording(String filePath, int quality) {
        return rtcEngine.startAudioRecording(filePath, quality);
    }

    public int stopAudioRecording() {
        return rtcEngine.stopAudioRecording();
    }

    public int startEchoTest() {
        return rtcEngine.startEchoTest();
    }

    public int startEchoTest(int intervalInSeconds) {
        return rtcEngine.startEchoTest(intervalInSeconds);
    }

    public int stopEchoTest() {
        return rtcEngine.stopEchoTest();
    }

    public int enableLastmileTest() {
        return rtcEngine.enableLastmileTest();
    }

    public int disableLastmileTest() {
        return rtcEngine.disableLastmileTest();
    }

    public int startLastmileProbeTest(LastmileProbeConfig config) {
        return rtcEngine.startLastmileProbeTest(config);
    }

    public int stopLastmileProbeTest() {
        return rtcEngine.stopLastmileProbeTest();
    }

    public int setVideoSource(IVideoSource source) {
        return rtcEngine.setVideoSource(source);
    }

    public int setLocalVideoRenderer(IVideoSink render) {
        return rtcEngine.setLocalVideoRenderer(render);
    }

    public int setRemoteVideoRenderer(int uid, IVideoSink render) {
        return rtcEngine.setRemoteVideoRenderer(uid, render);
    }

    public int setExternalAudioSource(boolean enabled, int sampleRate, int channels) {
        return rtcEngine.setExternalAudioSource(enabled, sampleRate, channels);
    }

    public int pushExternalAudioFrame(byte[] data, long timestamp) {
        return rtcEngine.pushExternalAudioFrame(data, timestamp);
    }

    public void setExternalVideoSource(boolean enable, boolean useTexture, boolean pushMode) {
        rtcEngine.setExternalVideoSource(enable, useTexture, pushMode);
    }

    public boolean pushExternalVideoFrame(AgoraVideoFrame frame) {
        return rtcEngine.pushExternalVideoFrame(frame);
    }

    public boolean isTextureEncodeSupported() {
        return rtcEngine.isTextureEncodeSupported();
    }

    public int registerAudioFrameObserver(IAudioFrameObserver observer) {
        return rtcEngine.registerAudioFrameObserver(observer);
    }

    public int setRecordingAudioFrameParameters(int sampleRate, int channel, int mode, int samplesPerCall) {
        return rtcEngine.setRecordingAudioFrameParameters(sampleRate, channel, mode, samplesPerCall);
    }

    public int setPlaybackAudioFrameParameters(int sampleRate, int channel, int mode, int samplesPerCall) {
        return rtcEngine.setPlaybackAudioFrameParameters(sampleRate, channel, mode, samplesPerCall);
    }

    public int setMixedAudioFrameParameters(int sampleRate, int samplesPerCall) {
        return rtcEngine.setMixedAudioFrameParameters(sampleRate, samplesPerCall);
    }

    public int addVideoWatermark(AgoraImage watermark) {
        return rtcEngine.addVideoWatermark(watermark);
    }

    public int clearVideoWatermarks() {
        return rtcEngine.clearVideoWatermarks();
    }

    public int setRemoteUserPriority(int uid, int userPriority) {
        return rtcEngine.setRemoteUserPriority(uid, userPriority);
    }

    public int setLocalPublishFallbackOption(int option) {
        return rtcEngine.setLocalPublishFallbackOption(option);
    }

    public int setRemoteSubscribeFallbackOption(int option) {
        return rtcEngine.setRemoteSubscribeFallbackOption(option);
    }

    public int enableDualStreamMode(boolean enabled) {
        return rtcEngine.enableDualStreamMode(enabled);
    }

    public int setRemoteVideoStreamType(int uid, int streamType) {
        return rtcEngine.setRemoteVideoStreamType(uid, streamType);
    }

    public int setRemoteDefaultVideoStreamType(int streamType) {
        return rtcEngine.setRemoteDefaultVideoStreamType(streamType);
    }

    public int setEncryptionSecret(String secret) {
        return rtcEngine.setEncryptionSecret(secret);
    }

    public int setEncryptionMode(String encryptionMode) {
        return rtcEngine.setEncryptionMode(encryptionMode);
    }

    public int addInjectStreamUrl(String url, LiveInjectStreamConfig config) {
        return rtcEngine.addInjectStreamUrl(url, config);
    }

    public int removeInjectStreamUrl(String url) {
        return rtcEngine.removeInjectStreamUrl(url);
    }

    public int addPublishStreamUrl(String url, boolean transcodingEnabled) {
        return rtcEngine.addPublishStreamUrl(url, transcodingEnabled);
    }

    public int removePublishStreamUrl(String url) {
        return rtcEngine.removePublishStreamUrl(url);
    }

    public int setLiveTranscoding(LiveTranscoding transcoding) {
        return rtcEngine.setLiveTranscoding(transcoding);
    }

    public int configPublisher(PublisherConfiguration config) {
        return rtcEngine.configPublisher(config);
    }

    public int setVideoCompositingLayout(VideoCompositingLayout layout) {
        return rtcEngine.setVideoCompositingLayout(layout);
    }

    public int clearVideoCompositingLayout() {
        return rtcEngine.clearVideoCompositingLayout();
    }

    public int createDataStream(boolean reliable, boolean ordered) {
        return rtcEngine.createDataStream(reliable, ordered);
    }

    public int sendStreamMessage(int streamId, byte[] message) {
        return rtcEngine.sendStreamMessage(streamId, message);
    }

    public int setVideoQualityParameters(boolean preferFrameRateOverImageQuality) {
        return rtcEngine.setVideoQualityParameters(preferFrameRateOverImageQuality);
    }

    public int setLocalVideoMirrorMode(int mode) {
        return rtcEngine.setLocalVideoMirrorMode(mode);
    }

    public int switchCamera() {
        return rtcEngine.switchCamera();
    }

    public boolean isCameraZoomSupported() {
        return rtcEngine.isCameraZoomSupported();
    }

    public boolean isCameraTorchSupported() {
        return rtcEngine.isCameraTorchSupported();
    }

    public boolean isCameraFocusSupported() {
        return rtcEngine.isCameraFocusSupported();
    }

    public boolean isCameraExposurePositionSupported() {
        return rtcEngine.isCameraExposurePositionSupported();
    }

    public boolean isCameraAutoFocusFaceModeSupported() {
        return rtcEngine.isCameraAutoFocusFaceModeSupported();
    }

    public int setCameraZoomFactor(float factor) {
        return rtcEngine.setCameraZoomFactor(factor);
    }

    public float getCameraMaxZoomFactor() {
        return rtcEngine.getCameraMaxZoomFactor();
    }

    public int setCameraFocusPositionInPreview(float positionX, float positionY) {
        return rtcEngine.setCameraFocusPositionInPreview(positionX, positionY);
    }

    public int setCameraExposurePosition(float positionXinView, float positionYinView) {
        return rtcEngine.setCameraExposurePosition(positionXinView, positionYinView);
    }

    public int setCameraTorchOn(boolean isOn) {
        return rtcEngine.setCameraTorchOn(isOn);
    }

    public int setCameraAutoFocusFaceModeEnabled(boolean enabled) {
        return rtcEngine.setCameraAutoFocusFaceModeEnabled(enabled);
    }

    public String getCallId() {
        return rtcEngine.getCallId();
    }

    public int rate(String callId, int rating, String description) {
        return rtcEngine.rate(callId, rating, description);
    }

    public int complain(String callId, String description) {
        return rtcEngine.complain(callId, description);
    }

    public int setLogFile(String filePath) {
        return rtcEngine.setLogFile(filePath);
    }

    public int setLogFilter(int filter) {
        return rtcEngine.setLogFilter(filter);
    }

    public int setLogFileSize(int fileSizeInKBytes) {
        return rtcEngine.setLogFileSize(fileSizeInKBytes);
    }

    public long getNativeHandle() {
        return rtcEngine.getNativeHandle();
    }

    public void addHandler(IRtcEngineEventHandler handler) {
        rtcEngine.addHandler(handler);
    }

    public boolean enableHighPerfWifiMode(boolean enable) {
        return rtcEngine.enableHighPerfWifiMode(enable);
    }

    public void monitorHeadsetEvent(boolean monitor) {
        rtcEngine.monitorHeadsetEvent(monitor);
    }

    public void monitorBluetoothHeadsetEvent(boolean monitor) {
        rtcEngine.monitorBluetoothHeadsetEvent(monitor);
    }

    public void setPreferHeadset(boolean enabled) {
        rtcEngine.setPreferHeadset(enabled);
    }

    public int setParameters(String parameters) {
        return rtcEngine.setParameters(parameters);
    }

    public String getParameter(String parameter, String args) {
        return rtcEngine.getParameter(parameter, args);
    }

    public int registerMediaMetadataObserver(IMetadataObserver observer, int type) {
        return rtcEngine.registerMediaMetadataObserver(observer, type);
    }
}
