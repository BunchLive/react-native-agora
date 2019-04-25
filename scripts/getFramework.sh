agorafile="react-native-agora.podspec"
if [ ! -f "$agorafile" ]
then
  echo "Need to execute in root directory or else it won’t work"
  return
fi

rm -rf __MACOSX

dir0="ios/RCTAgora/libs/"
if [ ! -d "$dir0" ]
then
  mkdir ios/RCTAgora/libs
fi

cacheDir='/Library/Caches/Agora2_4_0_1/'

dir1tmp="${cacheDir}AgoraRtcEngineKit.framework"
if [ ! -d "$dir1tmp" ]
then
  echo "Downloading AgoraRtcEngineKit..."
  curl -O https://storage.googleapis.com/z1-rumble-dev.appspot.com/Agora2_4_0_1/AgoraRtcEngineKit.framework.zip
  rm -rf AgoraRtcEngineKit.framework/
  unzip AgoraRtcEngineKit.framework.zip
  mv AgoraRtcEngineKit.framework/ ${cacheDir}
  echo "$0: AgoraRtcEngineKit.framework downloaded to '${dir1tmp}'."
  # rm AgoraRtcEngineKit.framework.zip
fi

dir1="ios/RCTAgora/libs/AgoraRtcEngineKit.framework"
if [ ! -d "$dir1" ]
then
  mkdir ios/RCTAgora/libs/AgoraRtcEngineKit.framework/
  cp -rf /Library/Caches/Agora2_4_0_1/AgoraRtcEngineKit.framework/ ios/RCTAgora/libs/AgoraRtcEngineKit.framework/
  echo "$0: AgoraRtcEngineKit.framework copied to '${dir1}'."
fi

dir2tmp="${cacheDir}AgoraRtcCryptoLoader.framework"
if [ ! -d "$dir2tmp" ]
then
echo "Downloading AgoraRtcCryptoLoader..."
  curl -O https://storage.googleapis.com/z1-rumble-dev.appspot.com/Agora2_4_0_1/AgoraRtcCryptoLoader.framework.zip
  rm -rf AgoraRtcCryptoLoader.framework/
  unzip AgoraRtcCryptoLoader.framework.zip
  mv AgoraRtcCryptoLoader.framework/ ${cacheDir}
  echo "$0: AgoraRtcCryptoLoader.framework downloaded to '${dir2tmp}'."
  rm AgoraRtcCryptoLoader.framework.zip
fi

dir2="ios/RCTAgora/libs/AgoraRtcCryptoLoader.framework"
if [ ! -d "$dir2" ]
then
  mkdir ios/RCTAgora/libs/AgoraRtcCryptoLoader.framework/
  cp -rf ${cacheDir}AgoraRtcCryptoLoader.framework/ ios/RCTAgora/libs/AgoraRtcCryptoLoader.framework/
  echo "$0: AgoraRtcCryptoLoader.framework copied to '${dir2}'."
fi

file1tmp="${cacheDir}libcrypto.a"
if [ ! -f "$file1tmp" ]
then
  curl -O https://storage.googleapis.com/z1-rumble-dev.appspot.com/Agora2_4_0_1/libcrypto.a.zip
  rm libcrypto.a
  unzip libcrypto.a.zip
  rm libcrypto.a.zip
  cp libcrypto.a ${cacheDir}
  echo "$0: libcrypto.a downloaded to '${file1tmp}'."
fi

file1="ios/RCTAgora/libs/libcrypto.a"
if [ ! -f "$file1" ]
then
  cp -rf ${cacheDir}libcrypto.a ios/RCTAgora/libs
  echo "$0: libcrypto.a copied to '${file1}'."
fi

rm -rf __MACOSX