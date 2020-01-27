import React, { Component, Fragment } from "react";
import {
    StyleSheet,
    Text,
    View,
    TextInput,
    Button
} from "react-native";

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

export default class WelcomeScreen extends Component {

    renderHeader = () => {
        return (
            <View style={{ marginBottom: 10 }}>
                <Text style={styles.title}>react-native-agora Demo</Text>
            </View>
        );
    };

    renderContent = () => {
        const { error, onChangeChannelName, onJoinChannel} = this.props;

        return (
            <Fragment>
                {error ? (
                    <Text>Error Message: {error}</Text>
                ) : null}
                <TextInput
                    style={{ height: 40, margin: 20 }}
                    placeholder="Enter channelName"
                    onChangeText={onChangeChannelName}
                />
                <Button
                    style={{ marginTop: 20 }}
                    title="join room"
                    onPress={onJoinChannel}
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
        return (
            <View style={styles.container}>
                {this.renderHeader()}
                {this.renderContent()}
                {this.renderFooter()}
            </View>
        );
    }
}
