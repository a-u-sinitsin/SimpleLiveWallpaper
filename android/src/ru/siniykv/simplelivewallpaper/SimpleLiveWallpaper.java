package ru.siniykv.simplelivewallpaper;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.backends.android.AndroidLiveWallpaperService;
import com.badlogic.gdx.backends.android.AndroidWallpaperListener;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SimpleLiveWallpaper extends AndroidLiveWallpaperService {

    public static float pixelOffset = 0;

    @Override
    public void onCreateApplication() {
        super.onCreateApplication();

        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

        config.useCompass = false;
        config.useWakelock = false;
        config.useAccelerometer = false;

        System.out.print("viewWidth - ");
        System.out.println(viewWidth);

        final ApplicationListener listener = new WallpaperListener();

        initialize(listener, config);
    }

    public static class WallpaperListener extends SimpleWallpaperListener implements AndroidWallpaperListener {

        @Override
        public void create() {
            super.resolver = new Resolver() {
                @Override
                public float getxPixelOffset() {
                    return pixelOffset;
                }
            };

            super.create();
        }

        @Override
        public void offsetChange(float xOffset, float yOffset, float xOffsetStep, float yOffsetStep, int xPixelOffset, int yPixelOffset) {
            pixelOffset = xPixelOffset;

        }

        @Override
        public void previewStateChange(boolean isPreview) {
        }
    }
}
