package ru.siniykv.simplelivewallpaper;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.backends.android.AndroidLiveWallpaperService;
import com.badlogic.gdx.backends.android.AndroidWallpaperListener;

public class SimpleLiveWallpaper extends AndroidLiveWallpaperService {

    public static float Offset = 0;

    @Override
    public void onCreateApplication() {
        super.onCreateApplication();

        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

        config.useCompass = false;
        config.useWakelock = false;
        config.useAccelerometer = false;

        final ApplicationListener listener = new WallpaperListener();

        initialize(listener, config);
    }

    public static class WallpaperListener extends SimpleWallpaperListener implements AndroidWallpaperListener {

        @Override
        public void create() {
            super.resolver = new Resolver() {
                @Override
                public float getxOffset() {
                    return Offset;
                }
            };

            super.create();
        }

        @Override
        public void offsetChange(float xOffset, float yOffset, float xOffsetStep, float yOffsetStep, int xPixelOffset, int yPixelOffset) {
            Offset = xOffset;

        }

        @Override
        public void previewStateChange(boolean isPreview) {
        }
    }
}
