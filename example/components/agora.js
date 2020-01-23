// noinspection JSIgnoredPromiseFromCall

import React, {Component, PureComponent} from "react";
import {
  AppState,
  Dimensions,
  Image,
  Modal,
  NativeModules,
  Platform,
  StyleSheet,
  Text,
  TouchableOpacity,
  View
} from "react-native";

import {AgoraView, RtcEngine} from "react-native-agora";
import {APPID, isIphoneX, isIphoneXR} from "../utils";

const { Agora } = NativeModules;
if (!Agora) throw new Error("Agora load failed in react-native, please check ur compiler environments");

const {
  FPS30,
  FixedLandscape,
  Host,
  AudioProfileDefault,
  AudioScenarioDefault
} = Agora;

const BtnEndCall = () => require("../assets/btn_endcall.png");
const BtnMute = () => require("../assets/btn_mute.png");
const BtnSpeaker = () => require("../assets/btn_speaker.png");
const BtnSwitchCamera = () => require("../assets/btn_switch_camera.png");
const BtnVideo = () => require("../assets/btn_video.png");
const EnableCamera = () => require("../assets/enable_camera.png");
const DisableCamera = () => require("../assets/disable_camera.png");
const EnablePhotoflash = () => require("../assets/enable_photoflash.png");
const DisablePhotoflash = () => require("../assets/disable_photoflash.png");
const IconMuted = () => require("../assets/icon_muted.png");
const IconSpeaker = () => require("../assets/icon_speaker.png");
const IconSoundEffect = () => require("../assets/icon_music_note_white.png");

const { width, height } = Dimensions.get("window");

const safeTop = top =>
  isIphoneX(Platform, width, height)
    ? top + 88
    : isIphoneXR(Platform, width, height)
    ? top + 64
    : top;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#F4F4F4"
  },
  absView: {
    position: "absolute",
    top: safeTop(0),
    left: 0,
    right: 0,
    bottom: 0,
    justifyContent: "space-between"
  },
  videoView: {
    padding: 5,
    flexWrap: "wrap",
    flexDirection: "row",
    zIndex: 100
  },
  localView: {
    flex: 1
  },
  remoteView: {
    width: (width - 40) / 3,
    height: (width - 40) / 3,
    margin: 5
  },
  topView: {
    flexDirection: "row",
    justifyContent: "space-around"
  },
  bottomView: {
    padding: 20,
    flexDirection: "row",
    justifyContent: "space-around"
  }
});

class OperateButton extends PureComponent {
  render() {
    const {
      onPress,
      source,
      style,
      imgStyle = { width: 50, height: 50 }
    } = this.props;
    return (
      <TouchableOpacity style={style} onPress={onPress} activeOpacity={0.7}>
        <Image style={imgStyle} source={source} />
      </TouchableOpacity>
    );
  }
}

const SoundEffects = {
  Blop: {id: 1, name: "blop.mp3", filepath: "/assets/blop.mp3" },
  Tick: {id: 2, name: "tick.mp3", filepath: "/assets/tick.mp3" },
  Woosh: {id: 3, name: "woosh.mp3", filepath: "/assets/woosh.mp3" }
};

function preloadSoundEffects() {
  if (Platform.OS === "android") {
    Object.values(SoundEffects).forEach(effect => {
      console.log("preloading sound effect ", effect);
      RtcEngine.preloadEffect(effect.id, effect.filepath);
    });
  }
}

function playEffect(effect, publish = false) {
  RtcEngine.playEffect({
    soundid: effect.id,
    filepath: effect.filepath,
    loopcount: 0,
    pitch: 1,
    pan: 0.0,
    gain: 100.0,
    publish: publish
  });
}

export default class AgoraComponent extends Component {
  state = {
    peerIds: [],
    joinSucceed: false,
    isSpeak: true,
    isMute: true,
    isCameraTorch: false,
    showVideo: true,
    hideButton: false,
    visible: false,
    selectedUid: undefined,
    appState: AppState.currentState
  };

  UNSAFE_componentWillMount() {
    const config = {
      appid: APPID,
      channelProfile: this.props.channelProfile,
      videoProfile: this.props.videoProfile,
      clientRole: this.props.clientRole || Host,
      videoEncoderConfig: {
        width: 360,
        height: 480,
        bitrate: 1,
        frameRate: FPS30,
        orientationMode: FixedLandscape
      },
      audioProfile: AudioProfileDefault,
      audioScenario: AudioScenarioDefault
    };
    RtcEngine.on("firstRemoteVideoDecoded", data => {
      console.log("[RtcEngine] onFirstRemoteVideoDecoded", data);
    });
    RtcEngine.on("userJoined", data => {
      console.log("[RtcEngine] onUserJoined", data);
      const { peerIds } = this.state;
      if (peerIds.indexOf(data.uid) === -1) {
        playEffect(SoundEffects.Blop);
        this.setState({
          peerIds: [...peerIds, data.uid]
        });
      }
    });
    RtcEngine.on("userOffline", data => {
      console.log("[RtcEngine] onUserOffline", data);
      playEffect(SoundEffects.Woosh);
      this.setState({
        peerIds: this.state.peerIds.filter(uid => uid !== data.uid)
      });
    });
    RtcEngine.on("joinChannelSuccess", data => {
      console.log("[RtcEngine] onJoinChannelSuccess", data);
      RtcEngine.startPreview();
      this.setState({
        joinSucceed: true
      });
    });
    RtcEngine.on("audioVolumeIndication", data => {
      // console.log("[RtcEngine] onAudioVolumeIndication", data);
    });
    RtcEngine.on("clientRoleChanged", data => {
      console.log("[RtcEngine] onClientRoleChanged", data);
    });
    RtcEngine.on("error", data => {
      if (data.error === 17) {
        RtcEngine.leaveChannel().then(_ => {
          RtcEngine.destroy();
          this.props.onCancel(data);
        });
      }
    });
    console.log("[CONFIG]", JSON.stringify(config));
    console.log("[CONFIG.encoderConfig", config.videoEncoderConfig);
    RtcEngine.init(config);

    preloadSoundEffects();
  }

  componentDidMount() {
    RtcEngine.getSdkVersion(version => {
      console.log("[RtcEngine] getSdkVersion", version);
    });

    console.log("[joinChannel] " + this.props.channelName);
    RtcEngine.joinChannel(this.props.channelName, this.props.uid);
    RtcEngine.muteLocalAudioStream(this.state.isMute);
    RtcEngine.enableAudioVolumeIndication(500, 3, true);

    AppState.addEventListener("change", nextAppState => {
      this.setState({ appState: nextAppState });
    });
  }

  componentWillUnmount() {
    if (this.state.joinSucceed) {
      RtcEngine.leaveChannel();
      RtcEngine.removeAllListeners();
      RtcEngine.destroy();
    }
  }

  handleCancel = () => {
    RtcEngine.leaveChannel();
    RtcEngine.removeAllListeners();
    RtcEngine.destroy();
    this.props.onCancel();
  };

  switchCamera = () => {
    //RtcEngine.switchCamera();
    RtcEngine.setEffectsVolume(100);
    playEffect(SoundEffects.Tick);
  };

  toggleAllRemoteAudioStreams = () => {
    this.setState(
      {
        isMute: !this.state.isMute
      },
      () => {
        RtcEngine.muteLocalAudioStream(this.state.isMute);
      }
    );
  };

  toggleSpeakerPhone = () => {
    this.setState(
      {
        isSpeak: !this.state.isSpeak
      },
      () => {
        RtcEngine.setDefaultAudioRouteToSpeakerphone(this.state.isSpeak);
      }
    );
  };

  toggleCameraTorch = () => {
    this.setState(
      {
        isCameraTorch: !this.state.isCameraTorch
      },
      () => {
        RtcEngine.setCameraTorchOn(this.state.isCameraTorch).then(val => {
          console.log("setCameraTorch", val);
        });
      }
    );
  };

  toggleVideo = () => {
    const showVideo = !this.state.showVideo;
    this.setState({ showVideo });
    RtcEngine.muteLocalVideoStream(!showVideo);
    // if (showVideo) {
    //   RtcEngine.enableVideo();
    //   RtcEngine.startPreview();
    // } else {
    //   RtcEngine.disableVideo();
    //   RtcEngine.stopPreview();
    // }
  };

  toggleHideButtons = () => {
    this.setState({
      hideButton: !this.state.hideButton
    });
  };

  onPressVideo = uid => {
    this.setState(
      {
        selectedUid: uid
      },
      () => {
        this.setState({
          visible: true
        });
      }
    );
  };

  buttonsView = ({
    hideButton,
    isCameraTorch,
    showVideo,
    isMute,
    isSpeaker
  }) => {
    if (!hideButton) {
      return (
        <View>
          <OperateButton
            style={{ alignSelf: "center", marginBottom: -10 }}
            onPress={this.handleCancel}
            imgStyle={{ width: 60, height: 60 }}
            source={BtnEndCall()}
          />
          <View style={styles.bottomView}>
            <OperateButton
              onPress={this.toggleCameraTorch}
              imgStyle={{ width: 40, height: 40 }}
              source={isCameraTorch ? EnablePhotoflash() : DisablePhotoflash()}
            />
            <OperateButton
              onPress={this.toggleVideo}
              source={showVideo ? EnableCamera() : DisableCamera()}
            />
          </View>
          <View style={styles.bottomView}>
            <OperateButton
              onPress={this.toggleAllRemoteAudioStreams}
              source={isMute ? IconMuted() : BtnMute()}
            />
            <OperateButton
              onPress={this.switchCamera}
              source={BtnSwitchCamera()}
            />
            <OperateButton
              onPress={this.toggleSpeakerPhone}
              source={!isSpeaker ? IconSpeaker() : BtnSpeaker()}
            />
          </View>
        </View>
      );
    }
  };

  soundEffectsView = () => {
    const { hideButton } = this.state;
    if (!hideButton) {
      return (
          <View style={{
            position: 'absolute',
            top: safeTop(0),
            right: 0,
            justifyContent: "space-between",
            flexDirection: 'column',
            backgroundColor: "rgba(0,0,0,0.32)",
          }}>
            <OperateButton
                onPress={() => playEffect(SoundEffects.Tick, true)}
                style={{margin: 10, padding: 5}}
                imgStyle={{ width: 30, height: 30 }}
                source={IconSoundEffect()}
            />
            <OperateButton
                onPress={() => playEffect(SoundEffects.Blop, true)}
                style={{margin: 10, padding: 5}}
                imgStyle={{ width: 30, height: 30 }}
                source={IconSoundEffect()}
            />
            <OperateButton
                onPress={() => playEffect(SoundEffects.Woosh, true)}
                style={{margin: 10, padding: 5}}
                imgStyle={{ width: 30, height: 30 }}
                source={IconSoundEffect()}
            />
          </View>
      )
    }
  };

  agoraPeerViews = ({ visible, peerIds }) => {
    return visible ? (
      <View style={styles.videoView} />
    ) : (
      <View style={styles.videoView}>
        {peerIds.map((uid, key) => (
          <TouchableOpacity
            activeOpacity={1}
            onPress={() => this.onPressVideo(uid)}
            key={key}
          >
            <AgoraView
              style={styles.remoteView}
              zOrderMediaOverlay={true}
              remoteUid={uid}
            />
          </TouchableOpacity>
        ))}
      </View>
    );
  };

  modalView = ({ visible }) => {
    return (
      <Modal
        visible={visible}
        presentationStyle={"fullScreen"}
        animationType={"slide"}
        onRequestClose={() => {}}
      >
        <TouchableOpacity
          activeOpacity={1}
          style={{ flex: 1 }}
          onPress={() =>
            this.setState({
              visible: false
            })
          }
        >
          <AgoraView
            style={{ flex: 1 }}
            zOrderMediaOverlay={true}
            remoteUid={this.state.selectedUid}
          />
        </TouchableOpacity>
      </Modal>
    );
  };

  render() {
    if (!this.state.joinSucceed) {
      return (
        <View
          style={{
            flex: 1,
            backgroundColor: "#fff",
            justifyContent: "center",
            alignItems: "center"
          }}
        >
          <Text>Creating a video conference...</Text>
        </View>
      );
    }

    if (this.state.appState !== "active") {
      return (
        <View
          style={{
            flex: 1,
            backgroundColor: "#fff",
            justifyContent: "center",
            alignItems: "center"
          }}
        >
          <Text>Preview not available while in background...</Text>
        </View>
      );
    }

    return (
      <TouchableOpacity
        activeOpacity={1}
        onPress={this.toggleHideButtons}
        style={styles.container}
      >
        {this.state.showVideo ? (
          <AgoraView
            style={styles.localView}
            showLocalVideo={this.state.showVideo}
          />
        ) : null}
        <View style={styles.absView}>
          <Text>
            channelName: {this.props.channelName}, peers:{" "}
            {this.state.peerIds.length}
          </Text>
          {this.agoraPeerViews(this.state)}
          {this.soundEffectsView()}
          {this.buttonsView(this.state)}
        </View>
        {this.modalView(this.state)}
      </TouchableOpacity>
    );
  }
}
