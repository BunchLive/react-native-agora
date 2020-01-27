import {RtcEngine} from "../../../../lib";

export const Signals = {
    PlaySound: "play-sound"
};

export function sendSignal(signal, payload) {
    const data = JSON.stringify({ signal, payload });

    console.log("sendMediaData", data);
    RtcEngine.sendMediaData(data);
}

export function sendSignalSoundEffect(soundEffect) {
    sendSignal(Signals.PlaySound, soundEffect);
}
