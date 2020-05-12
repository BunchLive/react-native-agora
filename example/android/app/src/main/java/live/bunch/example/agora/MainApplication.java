package live.bunch.example.agora;

import android.app.Application;
import android.util.Log;

import com.facebook.debug.debugoverlay.model.DebugOverlayTag;
import com.facebook.debug.holder.Printer;
import com.facebook.debug.holder.PrinterHolder;
import com.facebook.react.PackageList;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.soloader.SoLoader;

import java.util.List;

import live.bunch.agora.AgoraManager;

public class MainApplication extends Application implements ReactApplication {

    private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
        @Override
        public boolean getUseDeveloperSupport() {
            return BuildConfig.DEBUG;
        }

        @Override
        protected List<ReactPackage> getPackages() {
            @SuppressWarnings("UnnecessaryLocalVariable")
            List<ReactPackage> packages = new PackageList(this).getPackages();
            return packages;
        }

        @Override
        protected String getJSMainModuleName() {
            return "index";
        }
    };

    @Override
    public ReactNativeHost getReactNativeHost() {
        return mReactNativeHost;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG_JS) {
            PrinterHolder.setPrinter(new Printer() {
                @Override
                public void logMessage(final DebugOverlayTag tag, final String message, final Object... args) {
                    Log.d(tag.name, String.format(message, args));
                }

                @Override
                public void logMessage(final DebugOverlayTag tag, final String message) {
                    Log.d(tag.name, message);
                }

                @Override
                public boolean shouldDisplayLogMessage(final DebugOverlayTag tag) {
                    return true;
                }
            });
        }
        SoLoader.init(this, /* native exopackage */ false);
    }
}
