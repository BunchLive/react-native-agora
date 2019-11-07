## Patching react-native-agora android implementation.

1. Copy `live.bunch.agora` package to `android/src/main/java/`

2. Update `AgoraPackage.java` file imports

	```
	// android/src/main/java/com/syan/agora/AgoraPackage.java
	package com.syan.agora;
	
	import live.bunch.agora.AgoraViewManager;
	
	//...
	```

3. Update `AgoraModule.java` file imports

	```
	// android/src/main/java/com/syan/agora/AgoraModule.java
	package com.syan.agora;
	
	import live.bunch.agora.AgoraManager;
	
	//...
	```

4. Update `AgoraVideoView.java` file imports

	```
	// android/src/main/java/com/syan/agora/AgoraModule.java
	package com.syan.agora;
	
	import live.bunch.agora.AgoraManager;
	
	//...
	```
