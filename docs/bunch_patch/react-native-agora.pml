@startuml
title <b><i>react-native-agora</i></b>
header Nov 07 2019
center footer 2.8.0-alpha.2

package io.agora.rtc {
  interface RtcEngine
  class RtcEngineImpl

  .RtcEngine <|.. .RtcEngineImpl
}

package com.syan.agora {
  class AgoraPackage
  class AgoraViewManager
  class AgoraModule
  class AgoraVideoView
  class AgoraManager

  AgoraPackage -down-> AgoraModule
  AgoraPackage -down-> AgoraViewManager
  AgoraViewManager -left-> AgoraVideoView

  AgoraModule --> AgoraManager
  AgoraVideoView --> AgoraManager
  AgoraViewManager --> AgoraManager

  AgoraManager *-left-> .RtcEngineImpl

}

.AgoraManager *-- SurfaceView

@enduml
