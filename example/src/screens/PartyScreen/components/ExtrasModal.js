import React, {Component} from 'react';

import SectionListModal from "../../../components/SectionListModal";
import {mixInEffect, playEffect, SoundEffects} from "../soundEffects";
import {sendSignalSoundEffect, signalSoundEffect} from "../metadataSignals";
import {Music} from "../music";

export default class ExtrasModal extends Component {
    render() {
        const DATA = [
            {
                title: "Play Sound effect and publish",
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
                title: "Signal sound effect and play",
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
            },
            {
                title: "Mix music",
                data: [
                    {
                        id: 'music-play',
                        title: 'Play',
                    },
                    {
                        id: 'music-pause',
                        title: 'Pause',
                    },
                    {
                        id: 'music-resume',
                        title: 'Resume',
                    },
                    {
                        id: 'music-stop',
                        title: 'Stop',
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
                            sendSignalSoundEffect(SoundEffects.Blop);
                            playEffect(SoundEffects.Blop, false);
                            break;
                        case 'signal-sound-tick':
                            sendSignalSoundEffect(SoundEffects.Tick);
                            playEffect(SoundEffects.Tick, false);
                            break;
                        case 'signal-sound-woosh':
                            sendSignalSoundEffect(SoundEffects.Woosh);
                            playEffect(SoundEffects.Woosh, false);
                            break;

                        case 'music-play':
                            Music.play();
                            break;
                        case 'music-pause':
                            Music.pause();
                            break;
                        case 'music-resume':
                            Music.resume();
                            break;
                        case 'music-stop':
                            Music.stop();
                            break;
                    }
                }}
                onPressClose={this.props.onPressClose}
            />
        );
    }
}
