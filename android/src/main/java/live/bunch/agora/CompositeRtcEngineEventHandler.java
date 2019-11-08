package live.bunch.agora;

import android.graphics.Rect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.agora.rtc.IRtcEngineEventHandler;
import io.agora.rtc.models.UserInfo;

public class CompositeRtcEngineEventHandler extends IRtcEngineEventHandler {
    private final List<IRtcEngineEventHandler> handlers;

    public CompositeRtcEngineEventHandler() {
        super();
        this.handlers = new ArrayList<>();
    }

    public CompositeRtcEngineEventHandler(final IRtcEngineEventHandler... handlers) {
        super();
        this.handlers = Arrays.asList(handlers);
    }

    public void add(IRtcEngineEventHandler handler) {
        handlers.add(handler);
    }

    public void remove(IRtcEngineEventHandler handler) {
        handlers.remove(handler);
    }

    //region IRtcEngineEventHandler

    public void onWarning(int warn) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onWarning(warn);
        }
    }

    public void onError(int err) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onError(err);
        }
    }

    public void onJoinChannelSuccess(String channel, int uid, int elapsed) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onJoinChannelSuccess(channel, uid, elapsed);
        }
    }

    public void onRejoinChannelSuccess(String channel, int uid, int elapsed) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onRejoinChannelSuccess(channel, uid, elapsed);
        }
    }

    public void onLeaveChannel(RtcStats stats) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onLeaveChannel(stats);
        }
    }

    public void onClientRoleChanged(int oldRole, int newRole) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onClientRoleChanged(oldRole, newRole);
        }
    }

    public void onLocalUserRegistered(int uid, String userAccount) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onLocalUserRegistered(uid, userAccount);
        }
    }

    public void onUserInfoUpdated(int uid, UserInfo userInfo) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onUserInfoUpdated(uid, userInfo);
        }
    }

    public void onUserJoined(int uid, int elapsed) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onUserJoined(uid, elapsed);
        }
    }

    public void onUserOffline(int uid, int reason) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onUserOffline(uid, reason);
        }
    }

    public void onConnectionStateChanged(int state, int reason) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onConnectionStateChanged(state, reason);
        }
    }

    public void onConnectionInterrupted() {
        for (IRtcEngineEventHandler h : handlers) {
            h.onConnectionInterrupted();
        }
    }

    public void onConnectionLost() {
        for (IRtcEngineEventHandler h : handlers) {
            h.onConnectionLost();
        }
    }

    public void onConnectionBanned() {
        for (IRtcEngineEventHandler h : handlers) {
            h.onConnectionBanned();
        }
    }

    public void onApiCallExecuted(int error, String api, String result) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onApiCallExecuted(error, api, result);
        }
    }

    public void onTokenPrivilegeWillExpire(String token) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onTokenPrivilegeWillExpire(token);
        }
    }

    public void onRequestToken() {
        for (IRtcEngineEventHandler h : handlers) {
            h.onRequestToken();
        }
    }

    public void onMicrophoneEnabled(boolean enabled) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onMicrophoneEnabled(enabled);
        }
    }

    public void onAudioVolumeIndication(AudioVolumeInfo[] speakers, int totalVolume) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onAudioVolumeIndication(speakers, totalVolume);
        }
    }

    public void onActiveSpeaker(int uid) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onActiveSpeaker(uid);
        }
    }

    public void onFirstLocalAudioFrame(int elapsed) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onFirstLocalAudioFrame(elapsed);
        }
    }

    public void onFirstRemoteAudioFrame(int uid, int elapsed) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onFirstRemoteAudioFrame(uid, elapsed);
        }
    }

    public void onVideoStopped() {
        for (IRtcEngineEventHandler h : handlers) {
            h.onVideoStopped();
        }
    }

    public void onFirstLocalVideoFrame(int width, int height, int elapsed) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onFirstLocalVideoFrame(width, height, elapsed);
        }
    }

    public void onFirstRemoteVideoDecoded(int uid, int width, int height, int elapsed) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onFirstRemoteVideoDecoded(uid, width, height, elapsed);
        }
    }

    public void onFirstRemoteVideoFrame(int uid, int width, int height, int elapsed) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onFirstRemoteVideoFrame(uid, width, height, elapsed);
        }
    }

    public void onUserMuteAudio(int uid, boolean muted) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onUserMuteAudio(uid, muted);
        }
    }

    public void onUserMuteVideo(int uid, boolean muted) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onUserMuteVideo(uid, muted);
        }
    }

    public void onUserEnableVideo(int uid, boolean enabled) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onUserEnableVideo(uid, enabled);
        }
    }

    public void onUserEnableLocalVideo(int uid, boolean enabled) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onUserEnableLocalVideo(uid, enabled);
        }
    }

    public void onVideoSizeChanged(int uid, int width, int height, int rotation) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onVideoSizeChanged(uid, width, height, rotation);
        }
    }

    public void onRemoteAudioStateChanged(int uid, int state, int reason, int elapsed) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onRemoteAudioStateChanged(uid, state, reason, elapsed);
        }
    }

    public void onRemoteVideoStateChanged(int uid, int state, int reason, int elapsed) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onRemoteVideoStateChanged(uid, state, reason, elapsed);
        }
    }

    public void onChannelMediaRelayStateChanged(int state, int code) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onChannelMediaRelayStateChanged(state, code);
        }
    }

    public void onChannelMediaRelayEvent(int code) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onChannelMediaRelayEvent(code);
        }
    }

    public void onLocalPublishFallbackToAudioOnly(boolean isFallbackOrRecover) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onLocalPublishFallbackToAudioOnly(isFallbackOrRecover);
        }
    }

    public void onRemoteSubscribeFallbackToAudioOnly(int uid, boolean isFallbackOrRecover) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onRemoteSubscribeFallbackToAudioOnly(uid, isFallbackOrRecover);
        }
    }

    public void onAudioRouteChanged(int routing) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onAudioRouteChanged(routing);
        }
    }

    public void onCameraReady() {
        for (IRtcEngineEventHandler h : handlers) {
            h.onCameraReady();
        }
    }

    public void onCameraFocusAreaChanged(Rect rect) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onCameraFocusAreaChanged(rect);
        }
    }

    public void onCameraExposureAreaChanged(Rect rect) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onCameraExposureAreaChanged(rect);
        }
    }

    public void onAudioQuality(int uid, int quality, short delay, short lost) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onAudioQuality(uid, quality, delay, lost);
        }
    }

    public void onRtcStats(RtcStats stats) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onRtcStats(stats);
        }
    }

    public void onLastmileQuality(int quality) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onLastmileQuality(quality);
        }
    }

    public void onLastmileProbeResult(LastmileProbeResult result) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onLastmileProbeResult(result);
        }
    }

    public void onNetworkQuality(int uid, int txQuality, int rxQuality) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onNetworkQuality(uid, txQuality, rxQuality);
        }
    }

    public void onLocalVideoStats(LocalVideoStats stats) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onLocalVideoStats(stats);
        }
    }

    public void onRemoteVideoStats(RemoteVideoStats stats) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onRemoteVideoStats(stats);
        }
    }

    public void onLocalAudioStats(LocalAudioStats stats) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onLocalAudioStats(stats);
        }
    }

    public void onRemoteAudioStats(RemoteAudioStats stats) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onRemoteAudioStats(stats);
        }
    }

    public void onLocalVideoStat(int sentBitrate, int sentFrameRate) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onLocalVideoStat(sentBitrate, sentFrameRate);
        }
    }

    public void onRemoteVideoStat(int uid, int delay, int receivedBitrate, int receivedFrameRate) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onRemoteVideoStat(uid, delay, receivedBitrate, receivedFrameRate);
        }
    }

    public void onRemoteAudioTransportStats(int uid, int delay, int lost, int rxKBitRate) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onRemoteAudioTransportStats(uid, delay, lost, rxKBitRate);
        }
    }

    public void onRemoteVideoTransportStats(int uid, int delay, int lost, int rxKBitRate) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onRemoteVideoTransportStats(uid, delay, lost, rxKBitRate);
        }
    }

    public void onAudioMixingStateChanged(int state, int errorCode) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onAudioMixingStateChanged(state, errorCode);
        }
    }

    public void onAudioMixingFinished() {
        for (IRtcEngineEventHandler h : handlers) {
            h.onAudioMixingFinished();
        }
    }

    public void onAudioEffectFinished(int soundId) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onAudioEffectFinished(soundId);
        }
    }

    public void onFirstRemoteAudioDecoded(int uid, int elapsed) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onFirstRemoteAudioDecoded(uid, elapsed);
        }
    }

    public void onLocalAudioStateChanged(int state, int error) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onLocalAudioStateChanged(state, error);
        }
    }

    public void onLocalVideoStateChanged(int localVideoState, int error) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onLocalVideoStateChanged(localVideoState, error);
        }
    }

    public void onRtmpStreamingStateChanged(String url, int state, int errCode) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onRtmpStreamingStateChanged(url, state, errCode);
        }
    }

    public void onStreamPublished(String url, int error) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onStreamPublished(url, error);
        }
    }

    public void onStreamUnpublished(String url) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onStreamUnpublished(url);
        }
    }

    public void onTranscodingUpdated() {
        for (IRtcEngineEventHandler h : handlers) {
            h.onTranscodingUpdated();
        }
    }

    public void onStreamInjectedStatus(String url, int uid, int status) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onStreamInjectedStatus(url, uid, status);
        }
    }

    public void onStreamMessage(int uid, int streamId, byte[] data) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onStreamMessage(uid, streamId, data);
        }
    }

    public void onStreamMessageError(int uid, int streamId, int error, int missed, int cached) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onStreamMessageError(uid, streamId, error, missed, cached);
        }
    }

    public void onMediaEngineLoadSuccess() {
        for (IRtcEngineEventHandler h : handlers) {
            h.onMediaEngineLoadSuccess();
        }
    }

    public void onMediaEngineStartCallSuccess() {
        for (IRtcEngineEventHandler h : handlers) {
            h.onMediaEngineStartCallSuccess();
        }
    }

    public void onNetworkTypeChanged(int type) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onNetworkTypeChanged(type);
        }
    }

    //endregion
}