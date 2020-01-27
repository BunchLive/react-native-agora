import {RtcEngine} from 'react-native-agora';

export const Music = {
    play: () => {
        RtcEngine.startAudioMixing({
            filepath: '/assets/music.mp3',
            loopback: false,
            replace: false,
            cycle: 1
        });
    },
    pause: () => {
        RtcEngine.pauseAudioMixing();
    },
    resume: () => {
        RtcEngine.resumeAudioMixing();
    },
    stop: () => {
        RtcEngine.stopAudioMixing();
    }
};

