/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 */

import React, { Component, Fragment } from "react";
import {
  StyleSheet,
  Text,
  View,
  TextInput,
  PermissionsAndroid,
  AppState,
  Button
} from "react-native";
import AgoraRTCView from "./components/agora";

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
    backgroundColor: "#F5FCFF"
  },
  title: {
    fontSize: 20,
    textAlign: "center",
    margin: 10
  },
  instructions: {
    textAlign: "center",
    color: "#333333",
    marginBottom: 5
  },
  button: {
    height: 30,
    paddingHorizontal: 20,
    backgroundColor: "#6A71DD",
    justifyContent: "center",
    alignItems: "center",
    marginTop: 10
  }
});

export default class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      showLive: false,
      error: undefined,
      channelProfile: 1,
      videoProfile: 40,
      clientRole: 1,
      uid: 0,
      swapWidthAndHeight: true,
      channelName: null,
      overlayAllowed: undefined
    };
  }

  joinChannel = () => {
    const { channelName } = this.state;
    if (!channelName || channelName.trim().length == 0) return;

    this.setState({
      showLive: true
    });
  };

  onCancel = error => {
    this.setState({
      showLive: false,
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

  appState = AppState.currentState;

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

  renderHeader = () => {
    return (
      <View style={{ marginBottom: 10 }}>
        <Text style={styles.title}>react-native-agora Demo</Text>
      </View>
    );
  };

  renderContent = () => {
    return (
      <Fragment>
        {this.state.error ? (
          <Text>Error Message: {this.state.error}</Text>
        ) : null}
        <TextInput
          style={{ height: 40, margin: 20 }}
          placeholder="Enter channelName"
          onChangeText={text => {
            this.setState({ channelName: text });
          }}
        />
        <Button
          style={{ marginTop: 20 }}
          title="join room"
          onPress={this.joinChannel}
        />
      </Fragment>
    );
  };

  renderFooter = () => {
    return (
      <View style={{ position: "absolute", bottom: 10, left: 10 }}>
        <Text></Text>
      </View>
    );
  };

  render() {
    if (this.state.showLive) {
      console.log("channelName", this.state.channelName);
      return (
        <AgoraRTCView
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
      <View style={styles.container}>
        {this.renderHeader()}
        {this.renderContent()}
        {this.renderFooter()}
      </View>
    );
  }
}
