import React, { Component } from "react";
import {
  PermissionsAndroid,
  AppState,
} from "react-native";

import PartyScreen from './screens/PartyScreen';
import WelcomeScreen from "./screens/WelcomeScreen";

const Screens = {
  Welcome: 'WelcomeScreen',
  Party: 'PartyScreen'
};

export default class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      screen: Screens.Welcome,
      error: undefined,
      channelProfile: 1,
      videoProfile: 40,
      clientRole: 1,
      uid: 0,
      swapWidthAndHeight: true,
      channelName: null,
      overlayAllowed: undefined
    };
    this.appState = AppState.currentState;
  }

  joinChannel = () => {
    const { channelName } = this.state;
    if (!channelName || channelName.trim().length == 0) return;

    this.setState({
      screen: Screens.Party
    });
  };

  onCancel = error => {
    this.setState({
      screen: Screens.Welcome,
      error: JSON.stringify(error)
    });
  };

  async requestCameraAndAudioAndroidPermission() {
    try {
      const granted = await PermissionsAndroid.requestMultiple([
        PermissionsAndroid.PERMISSIONS.CAMERA,
        PermissionsAndroid.PERMISSIONS.RECORD_AUDIO
      ]);
      if (granted === PermissionsAndroid.RESULTS.GRANTED) {
        console.log("You can use the camera");
      } else {
        console.log("Camera permission denied");
      }
    } catch (err) {
      console.warn(err);
    }
  }

  UNSAFE_componentWillMount() {
    this.requestCameraAndAudioAndroidPermission();

    AppState.addEventListener("change", nextAppState => {
      if (
        this.appState.match(/inactive|background/) &&
        nextAppState === "active"
      ) {
        console.log("App has come to the foreground!");
      } else if (this.appState === "active" && nextAppState === "background") {
        console.log("App has entered background!");
      }
      this.appState = nextAppState;
    });
  }

  render() {
    if (this.state.screen === Screens.Party) {
      console.log("channelName", this.state.channelName);
      return (
          <PartyScreen
              channelProfile={this.state.channelProfile}
              channelName={this.state.channelName}
              videoProfile={this.state.videoProfile}
              clientRole={this.state.clientRole}
              uid={this.state.uid}
              onCancel={this.onCancel}
              overlayAllowed={this.state.overlayAllowed}
          />
      );
    }

    return (
      <WelcomeScreen
        error={this.state.error}
        onChangeChannelName={text => {
          this.setState({ channelName: text });
        }}
        onJoinChannel={this.joinChannel}
      />
    );
  }
}
