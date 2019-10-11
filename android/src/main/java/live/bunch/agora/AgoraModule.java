package live.bunch.agora;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableMap;
import com.syan.agora.ReactNativeAgoraException;

public class AgoraModule extends com.syan.agora.AgoraModule {

    public AgoraModule(ReactApplicationContext context) {
        super(context);
    }

    @Override
    public void switchCamera(Promise promise) {
        try {
            int res = AgoraManager.getInstance().switchCamera();
            if (res != 0) throw new ReactNativeAgoraException("switchCamera Failed", res);
            WritableMap map = Arguments.createMap();
            map.putBoolean("success", true);
            map.putInt("value", res);
            promise.resolve(map);
        } catch (Exception e) {
            promise.reject(e);
        }
    }
}
