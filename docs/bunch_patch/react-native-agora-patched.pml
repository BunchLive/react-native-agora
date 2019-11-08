@startuml
title Patched <b><i>react-native-agora</i></b>
header Nov 07 2019
center footer 2.8.0-alpha.2-bunch.1

package io.agora.rtc {
  interface RtcEngine
  class RtcEngineImpl

  .RtcEngine <|.. .RtcEngineImpl
}

' com.syan.agora
class com.syan.agora.AgoraPackage
class com.syan.agora.AgoraModule
class com.syan.agora.AgoraVideoView

com.syan.agora.AgoraPackage --> com.syan.agora.AgoraModule

' live.bunch.agora
class live.bunch.agora.AgoraManager
class live.bunch.agora.RtcEngineWrapper
class live.bunch.agora.AgoraViewManager

.RtcEngine <|.. live.bunch.agora.RtcEngineWrapper
live.bunch.agora.RtcEngineWrapper *-left-> .RtcEngineImpl
live.bunch.agora.AgoraManager *-left-> live.bunch.agora.RtcEngineWrapper
live.bunch.agora.AgoraViewManager --> live.bunch.agora.AgoraManager
live.bunch.agora.AgoraViewManager --> com.syan.agora.AgoraVideoView
live.bunch.agora.AgoraManager *-- TextureView

' patch
com.syan.agora.AgoraPackage -left-> live.bunch.agora.AgoraViewManager
com.syan.agora.AgoraVideoView --> live.bunch.agora.AgoraManager
com.syan.agora.AgoraModule --> live.bunch.agora.AgoraManager


@enduml
