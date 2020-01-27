import React, {Component} from 'react';
import {RtcEngine} from 'react-native-agora';

import SectionListModal from "../../../components/SectionListModal";
import { playEffect, SoundEffects } from "../soundEffects";

function signalSoundEffect(soundEffect) {
    const data = JSON.stringify({
        command: "play-sound",
        payload: soundEffect
    });
    console.log("sendMediaData", data);
    RtcEngine.sendMediaData(data);
}

export default class ExtrasModal extends Component {
    render() {
        const DATA = [
            {
                title: "Play Sound effects + publish",
                data: [
                    {
                        id: 'play-sound-blop',
                        title: 'Play sound effect "Blop"',
                    },
                    {
                        id: 'play-sound-tick',
                        title: 'Play sound effect "Tick"',
                    },
                    {
                        id: 'play-sound-woosh',
                        title: 'Play sound effect "Woosh"',
                    },
                ]
            },
            {
                title: "Signal sound effect  local",
                data: [
                    {
                        id: 'signal-sound-blop',
                        title: 'Trigger "Blop"',
                    },
                    {
                        id: 'signal-sound-tick',
                        title: 'Trigger "Tick"',
                    },
                    {
                        id: 'signal-sound-woosh',
                        title: 'Trigger "Woosh"',
                    },
                ]
            }
        ];

        return (
            <SectionListModal
                title="Extras"
                visible={this.props.visible}
                data={DATA}
                onSelectItem={item => {
                    switch (item.id) {
                        case 'play-sound-blop':
                            playEffect(SoundEffects.Blop, true);
                            break;
                        case 'play-sound-tick':
                            playEffect(SoundEffects.Tick, true);
                            break;
                        case 'play-sound-woosh':
                            playEffect(SoundEffects.Woosh, true);
                            break;

                        case 'signal-sound-blop':
                            signalSoundEffect(SoundEffects.Blop);
                            playEffect(SoundEffects.Blop, false);
                            break;
                        case 'signal-sound-tick':
                            signalSoundEffect(SoundEffects.Tick);
                            playEffect(SoundEffects.Tick, false);
                            break;
                        case 'signal-sound-woosh':
                            signalSoundEffect(SoundEffects.Woosh);
                            playEffect(SoundEffects.Woosh, false);
                            break;
                    }
                }}
                onPressClose={this.props.onPressClose}
            />
        );
    }
}
