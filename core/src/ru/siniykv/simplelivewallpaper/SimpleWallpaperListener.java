package ru.siniykv.simplelivewallpaper;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import sun.rmi.runtime.Log;

public class SimpleWallpaperListener implements ApplicationListener {

    public Resolver resolver = null;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Texture texture;
    private float ratio;


    @Override
    public void create() {
        batch = new SpriteBatch();
        texture = new Texture("wallpaper.jpg");
        ratio = ((float) texture.getWidth()) / ((float) texture.getHeight());

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(false);
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void render() {

/*        if (isAndroid && resolver != null) // In daydream resolver is null
            camera.position.x = (sW / 2) - resolver.getxPixelOffset();
*/

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.position.set(camera.viewportWidth / 2 + (ratio * camera.viewportHeight - camera.viewportWidth) * resolver.getxOffset(), camera.viewportHeight / 2, 0);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(texture, 0, 0, ratio * camera.viewportHeight, camera.viewportHeight);
        batch.end();

/*        if (!isAndroid && Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
            Gdx.app.exit();
*/
    }

    @Override
    public void pause() {
        System.out.println("pause");
    }

    @Override
    public void resume() {
        System.out.println("resume");
    }

    @Override
    public void dispose() {
        System.out.println("dispose");
        batch.dispose();
        texture.dispose();
    }

}
