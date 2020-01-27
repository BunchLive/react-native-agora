import {Platform} from "react-native";
import {RtcEngine} from 'react-native-agora';

export const SoundEffects = {
    Blop: {id: 1, name: "blop.mp3", filepath: "/assets/blop.mp3" },
    Tick: {id: 2, name: "tick.mp3", filepath: "/assets/tick.mp3" },
    Woosh: {id: 3, name: "woosh.mp3", filepath: "/assets/woosh.mp3" }
};

export function preloadSoundEffects() {
    if (Platform.OS === "android") {
        Object.values(SoundEffects).forEach(effect => {
            console.log("preloading sound effect ", effect);
            RtcEngine.preloadEffect(effect.id, effect.filepath);
        });
    }
}

export function playEffect(effect, publish = false) {
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
