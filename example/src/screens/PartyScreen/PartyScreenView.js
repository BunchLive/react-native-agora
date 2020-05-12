import React, {Component, PureComponent} from 'react';
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
  View,
  Button,
} from 'react-native';

import {AgoraView, RtcEngine} from 'react-native-agora';
import {APPID, isIphoneX, isIphoneXR} from '../../utils';
import ExtrasModal from './components/ExtrasModal';
import {playEffect} from "./soundEffects";
import {Signals} from "./metadataSignals";

const {Agora} = NativeModules;
if (!Agora)
  throw new Error(
    'Agora load failed in react-native, please check ur compiler environments',
  );

const {
  FPS30,
  FixedPortrait,
  Host,
  AudioProfileDefault,
  AudioScenarioDefault,
} = Agora;

const BtnEndCall = () => require('../../assets/btn_endcall.png');
const BtnMute = () => require('../../assets/btn_mute.png');
const EnableCamera = () => require('../../assets/enable_camera.png');
const DisableCamera = () => require('../../assets/disable_camera.png');
const IconMuted = () => require('../../assets/icon_muted.png');

const Modals = {
  None: undefined,
  FullscreenPeer: 'fullscreenPeer',
  Extras: 'extras',
};

const {width, height} = Dimensions.get('window');

const safeTop = top =>
  isIphoneX(Platform, width, height)
    ? top + 88
    : isIphoneXR(Platform, width, height)
    ? top + 64
    : top;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#F4F4F4',
  },
  absView: {
    position: 'absolute',
    top: safeTop(0),
    left: 0,
    right: 0,
    bottom: 0,
    justifyContent: 'space-between',
  },
  videoView: {
    padding: 5,
    flexWrap: 'wrap',
    flexDirection: 'row',
    zIndex: 100,
  },
  localView: {
    flex: 1,
  },
  remoteView: {
    width: (width - 40) / 3,
    height: (width - 40) / 3,
    margin: 5,
  },
  bottomView: {
    padding: 20,
    flexDirection: 'row',
    justifyContent: 'space-around',
  },
});

class OperateButton extends PureComponent {
  render() {
    const {
      onPress,
      source,
      style,
      imgStyle = {width: 50, height: 50},
    } = this.props;
    return (
      <TouchableOpacity style={style} onPress={onPress} activeOpacity={0.7}>
        <Image style={imgStyle} source={source} />
      </TouchableOpacity>
    );
  }
}

export default class PartyScreenView extends Component {
  state = {
    peerIds: [],
    joinSucceed: false,
    isSpeak: true,
    isMute: true,
    isCameraTorch: false,
    showVideo: true,
    hideButton: false,
    modalVisible: Modals.None,
    fullScreenModalVisible: false,
    extrasModalVisible: false,
    selectedUid: undefined,
    appState: AppState.currentState,
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
        orientationMode: FixedPortrait,
      },
      audioProfile: AudioProfileDefault,
      audioScenario: AudioScenarioDefault,
    };
    RtcEngine.on('firstRemoteVideoDecoded', data => {
      console.log('[RtcEngine] onFirstRemoteVideoDecoded', data);
    });
    RtcEngine.on('userJoined', data => {
      console.log('[RtcEngine] onUserJoined', data);
      const {peerIds} = this.state;
      if (peerIds.indexOf(data.uid) === -1) {
        this.setState({
          peerIds: [...peerIds, data.uid],
        });
      }
    });
    RtcEngine.on('userOffline', data => {
      console.log('[RtcEngine] onUserOffline', data);
      this.setState({
        peerIds: this.state.peerIds.filter(uid => uid !== data.uid),
      });
    });
    RtcEngine.on('joinChannelSuccess', data => {
      console.log('[RtcEngine] onJoinChannelSuccess', data);
      RtcEngine.startPreview();
      this.setState({
        joinSucceed: true,
      });
    });
    RtcEngine.on('audioVolumeIndication', data => {
      // console.log("[RtcEngine] onAudioVolumeIndication", data);
    });
    RtcEngine.on('clientRoleChanged', data => {
      console.log('[RtcEngine] onClientRoleChanged', data);
    });
    RtcEngine.on('error', data => {
      if (data.error === 17) {
        RtcEngine.leaveChannel().then(_ => {
          RtcEngine.destroy();
          this.props.onCancel(data);
        });
      }
    });
    RtcEngine.on('localVideoChanged', data => {
      const {state} = data;
      let s;
      switch (state) {
        case 0:
          s = 'LOCAL_VIDEO_STREAM_STATE_STOPPED';
          break;
        case 1:
          s = 'LOCAL_VIDEO_STREAM_STATE_CAPTURING';
          break;
        case 2:
          s = 'LOCAL_VIDEO_STREAM_STATE_ENCODING';
          break;
        case 3:
          s = 'LOCAL_VIDEO_STREAM_STATE_FAILED';
          break;
        default:
          s = 'unknown';
      }
      console.log('localVideoChanged ', s, data);
    });
    RtcEngine.on("mediaMetaDataReceived", (metadata) => {
      console.log("mediaMetaDataReceived", metadata);
      try {
        const data = metadata.data;
        const signal = JSON.parse(data);
        if (signal && signal.signal === Signals.PlaySound) {
          playEffect(signal.payload, false);
        }
      } catch (e) {
        console.log("failed to parse MetaData", metadata);
      }
    });

    console.log('[CONFIG]', JSON.stringify(config));
    console.log('[CONFIG.encoderConfig', config.videoEncoderConfig);
    RtcEngine.init(config);
  }

  componentDidMount() {
    RtcEngine.getSdkVersion(version => {
      console.log('[RtcEngine] getSdkVersion', version);
    });

    console.log('[joinChannel] ' + this.props.channelName);
    RtcEngine.joinChannel(this.props.channelName, this.props.uid);
    RtcEngine.muteLocalAudioStream(this.state.isMute);
    RtcEngine.enableAudioVolumeIndication(500, 10, false);
    RtcEngine.registerMediaMetadataObserver();

    AppState.addEventListener('change', nextAppState => {
      this.setState({appState: nextAppState});
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

  muteLocalAudio = () => {
    this.setState(
      {
        isMute: !this.state.isMute,
      },
      () => {
        RtcEngine.muteLocalAudioStream(this.state.isMute);
      },
    );
  };

  muteLocalVideo = mute => {
    this.setState({showVideo: !mute}, () => {
      RtcEngine.muteLocalVideoStream(mute);
    });
  };

  toggleVideo = () => {
    const showVideo = !this.state.showVideo;
    this.muteLocalVideo(!showVideo);
  };

  toggleHideButtons = () => {
    this.setState({
      hideButton: !this.state.hideButton,
    });
  };

  onPressVideo = uid => {
    this.setState(
      {
        selectedUid: uid,
      },
      () => {
        this.setState({
          modalVisible: Modals.FullscreenPeer,
        });
      },
    );
  };

  renderHeader = () => {
    return (
      <View
        style={{
          position: 'absolute',
          top: 0,
          left: 0,
          right: 0,
          height: 40,
          flex: 1,
          flexDirection: 'row',
          justifyContent: 'space-between',
          zIndex: 200,
        }}>
        <Text>
          channelName: {this.props.channelName}, peers:{' '}
          {this.state.peerIds.length}
        </Text>
        <Button
          title="EXTRAS"
          onPress={() => this.setState({modalVisible: Modals.Extras})}
        />
      </View>
    );
  };

  renderFooter = () => {
    const {hideButton, showVideo, isMute} = this.state;

    if (hideButton) {
      return null;
    }

    return (
      <View>
        <View style={styles.bottomView}>
          <OperateButton
            onPress={this.muteLocalAudio}
            source={isMute ? IconMuted() : BtnMute()}
          />
          <OperateButton
            style={{alignSelf: 'center', marginBottom: -10}}
            onPress={this.handleCancel}
            imgStyle={{width: 60, height: 60}}
            source={BtnEndCall()}
          />
          <OperateButton
            onPress={this.toggleVideo}
            source={showVideo ? EnableCamera() : DisableCamera()}
          />
        </View>
      </View>
    );
  };

  agoraPeerViews = () => {
    const {modalVisible, peerIds} = this.state;

    if (modalVisible === Modals.FullscreenPeer) {
      return <View style={styles.videoView} />;
    }

    return (
      <View style={styles.videoView}>
        {peerIds.map((uid, key) => (
          <TouchableOpacity
            activeOpacity={1}
            onPress={() => this.onPressVideo(uid)}
            key={key}>
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

  fullScreenModalView = () => {
    const {modalVisible} = this.state;

    return (
      <Modal
        visible={modalVisible === Modals.FullscreenPeer}
        presentationStyle={'fullScreen'}
        animationType={'slide'}
        onRequestClose={() => {}}>
        <TouchableOpacity
          activeOpacity={1}
          style={{flex: 1}}
          onPress={() =>
            this.setState({
              modalVisible: Modals.None,
            })
          }>
          <AgoraView
            style={{flex: 1}}
            zOrderMediaOverlay={true}
            remoteUid={this.state.selectedUid}
          />
        </TouchableOpacity>
      </Modal>
    );
  };

  renderExtrasModal = () => {
    const {modalVisible} = this.state;

    return (
      <ExtrasModal
        visible={modalVisible === Modals.Extras}
        onPressClose={() => this.setState({modalVisible: Modals.None})}
      />
    );
  };

  renderAgoraLocalView = () => {
    const {showVideo} = this.state;

    if (!showVideo) {
      return (
        <View
          style={{
            flex: 1,
            backgroundColor: '#eee',
            justifyContent: 'center',
            alignItems: 'center',
          }}>
          <Image
            style={{width: 50, height: 50, opacity: 0.7, margin: 10}}
            source={DisableCamera()}
          />
          <Text>Video is disabled</Text>
        </View>
      );
    }

    return <AgoraView style={styles.localView} showLocalVideo={showVideo} />;
  };

  render() {
    if (!this.state.joinSucceed) {
      return (
        <View
          style={{
            flex: 1,
            backgroundColor: '#fff',
            justifyContent: 'center',
            alignItems: 'center',
          }}>
          <Text>Creating a video conference...</Text>
        </View>
      );
    }

    if (this.state.appState !== 'active') {
      return (
        <View
          style={{
            flex: 1,
            backgroundColor: '#fff',
            justifyContent: 'center',
            alignItems: 'center',
          }}>
          <Text>Preview not available while in background...</Text>
        </View>
      );
    }

    return (
      <TouchableOpacity
        activeOpacity={1}
        onPress={this.toggleHideButtons}
        style={styles.container}>
        {this.renderAgoraLocalView()}
        <View style={styles.absView}>
          {this.agoraPeerViews()}
          {this.renderHeader()}
          {this.renderFooter()}
        </View>
        {this.fullScreenModalView()}
        {this.renderExtrasModal()}
      </TouchableOpacity>
    );
  }
}
