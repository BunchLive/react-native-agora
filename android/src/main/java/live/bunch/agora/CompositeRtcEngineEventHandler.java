package live.bunch.agora;

import android.graphics.Rect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.agora.rtc.IRtcEngineEventHandler;

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

    @Override
    public void onWarning(final int warn) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onWarning(warn);
        }
    }

    @Override
    public void onError(final int err) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onError(err);
        }
    }

    @Override
    public void onJoinChannelSuccess(final String channel, final int uid, final int elapsed) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onJoinChannelSuccess(channel, uid, elapsed);
        }
    }

    @Override
    public void onRejoinChannelSuccess(final String channel, final int uid, final int elapsed) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onRejoinChannelSuccess(channel, uid, elapsed);
        }
    }

    @Override
    public void onLeaveChannel(final RtcStats stats) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onLeaveChannel(stats);
        }
    }

    @Override
    public void onClientRoleChanged(final int oldRole, final int newRole) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onClientRoleChanged(oldRole, newRole);
        }
    }

    @Override
    public void onUserJoined(final int uid, final int elapsed) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onUserJoined(uid, elapsed);
        }
    }

    @Override
    public void onUserOffline(final int uid, final int reason) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onUserOffline(uid, reason);
        }
    }

    @Override
    public void onConnectionStateChanged(final int state, final int reason) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onConnectionStateChanged(state, reason);
        }
    }

    @Override
    public void onConnectionLost() {
        for (IRtcEngineEventHandler h : handlers) {
            h.onConnectionLost();
        }
    }

    @Override
    public void onApiCallExecuted(final int error, final String api, final String result) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onApiCallExecuted(error, api, result);
        }
    }

    @Override
    public void onTokenPrivilegeWillExpire(final String token) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onTokenPrivilegeWillExpire(token);
        }
    }

    @Override
    public void onRequestToken() {
        for (IRtcEngineEventHandler h : handlers) {
            h.onRequestToken();
        }
    }

    @Override
    public void onMicrophoneEnabled(final boolean enabled) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onMicrophoneEnabled(enabled);
        }
    }

    @Override
    public void onAudioVolumeIndication(final AudioVolumeInfo[] speakers, final int totalVolume) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onAudioVolumeIndication(speakers, totalVolume);
        }
    }

    @Override
    public void onActiveSpeaker(final int uid) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onActiveSpeaker(uid);
        }
    }

    @Override
    public void onFirstLocalAudioFrame(final int elapsed) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onFirstLocalAudioFrame(elapsed);
        }
    }

    @Override
    public void onFirstRemoteAudioFrame(final int uid, final int elapsed) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onFirstRemoteAudioFrame(uid, elapsed);
        }
    }

    @Override
    public void onVideoStopped() {
        for (IRtcEngineEventHandler h : handlers) {
            h.onVideoStopped();
        }
    }

    @Override
    public void onFirstLocalVideoFrame(final int width, final int height, final int elapsed) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onFirstLocalVideoFrame(width, height, elapsed);
        }
    }

    @Override
    public void onFirstRemoteVideoDecoded(final int uid, final int width, final int height, final int elapsed) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onFirstRemoteVideoDecoded(uid, width, height, elapsed);
        }
    }

    @Override
    public void onFirstRemoteVideoFrame(final int uid, final int width, final int height, final int elapsed) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onFirstRemoteVideoFrame(uid, width, height, elapsed);
        }
    }

    @Override
    public void onUserMuteAudio(final int uid, final boolean muted) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onUserMuteAudio(uid, muted);
        }
    }

    @Override
    public void onUserMuteVideo(final int uid, final boolean muted) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onUserMuteVideo(uid, muted);
        }
    }

    @Override
    public void onUserEnableVideo(final int uid, final boolean enabled) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onUserEnableVideo(uid, enabled);
        }
    }

    @Override
    public void onUserEnableLocalVideo(final int uid, final boolean enabled) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onUserEnableLocalVideo(uid, enabled);
        }
    }

    @Override
    public void onVideoSizeChanged(final int uid, final int width, final int height, final int rotation) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onVideoSizeChanged(uid, width, height, rotation);
        }
    }

    @Override
    public void onRemoteVideoStateChanged(final int uid, final int state) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onRemoteVideoStateChanged(uid, state);
        }
    }

    @Override
    public void onLocalPublishFallbackToAudioOnly(final boolean isFallbackOrRecover) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onLocalPublishFallbackToAudioOnly(isFallbackOrRecover);
        }
    }

    @Override
    public void onRemoteSubscribeFallbackToAudioOnly(final int uid, final boolean isFallbackOrRecover) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onRemoteSubscribeFallbackToAudioOnly(uid, isFallbackOrRecover);
        }
    }

    @Override
    public void onAudioRouteChanged(final int routing) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onAudioRouteChanged(routing);
        }
    }

    @Override
    public void onCameraReady() {
        for (IRtcEngineEventHandler h : handlers) {
            h.onCameraReady();
        }
    }

    @Override
    public void onCameraFocusAreaChanged(final Rect rect) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onCameraFocusAreaChanged(rect);
        }
    }

    @Override
    public void onCameraExposureAreaChanged(final Rect rect) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onCameraExposureAreaChanged(rect);
        }
    }

    @Override
    public void onRtcStats(final RtcStats stats) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onRtcStats(stats);
        }
    }

    @Override
    public void onLastmileQuality(final int quality) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onLastmileQuality(quality);
        }
    }

    @Override
    public void onLastmileProbeResult(final LastmileProbeResult result) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onLastmileProbeResult(result);
        }
    }

    @Override
    public void onNetworkQuality(final int uid, final int txQuality, final int rxQuality) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onNetworkQuality(uid, txQuality, rxQuality);
        }
    }

    @Override
    public void onLocalVideoStats(final LocalVideoStats stats) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onLocalVideoStats(stats);
        }
    }

    @Override
    public void onRemoteVideoStats(final RemoteVideoStats stats) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onRemoteVideoStats(stats);
        }
    }

    @Override
    public void onRemoteAudioStats(final RemoteAudioStats stats) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onRemoteAudioStats(stats);
        }
    }

    @Override
    public void onRemoteAudioTransportStats(final int uid, final int delay, final int lost, final int rxKBitRate) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onRemoteAudioTransportStats(uid, delay, lost, rxKBitRate);
        }
    }

    @Override
    public void onRemoteVideoTransportStats(final int uid, final int delay, final int lost, final int rxKBitRate) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onRemoteVideoTransportStats(uid, delay, lost, rxKBitRate);
        }
    }

    @Override
    public void onAudioMixingStateChanged(final int state, final int errorCode) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onAudioMixingStateChanged(state, errorCode);
        }
    }

    @Override
    public void onAudioMixingFinished() {
        for (IRtcEngineEventHandler h : handlers) {
            h.onAudioMixingFinished();
        }
    }

    @Override
    public void onAudioEffectFinished(final int soundId) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onAudioEffectFinished(soundId);
        }
    }

    @Override
    public void onStreamPublished(final String url, final int error) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onStreamPublished(url, error);
        }
    }

    @Override
    public void onStreamUnpublished(final String url) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onStreamUnpublished(url);
        }
    }

    @Override
    public void onTranscodingUpdated() {
        for (IRtcEngineEventHandler h : handlers) {
            h.onTranscodingUpdated();
        }
    }

    @Override
    public void onStreamInjectedStatus(final String url, final int uid, final int status) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onStreamInjectedStatus(url, uid, status);
        }
    }

    @Override
    public void onStreamMessage(final int uid, final int streamId, final byte[] data) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onStreamMessage(uid, streamId, data);
        }
    }

    @Override
    public void onStreamMessageError(final int uid, final int streamId, final int error, final int missed, final int cached) {
        for (IRtcEngineEventHandler h : handlers) {
            h.onStreamMessageError(uid, streamId, error, missed, cached);
        }
    }

    @Override
    public void onMediaEngineLoadSuccess() {
        for (IRtcEngineEventHandler h : handlers) {
            h.onMediaEngineLoadSuccess();
        }
    }

    @Override
    public void onMediaEngineStartCallSuccess() {
        for (IRtcEngineEventHandler h : handlers) {
            h.onMediaEngineStartCallSuccess();
        }
    }

    //endregion
}
