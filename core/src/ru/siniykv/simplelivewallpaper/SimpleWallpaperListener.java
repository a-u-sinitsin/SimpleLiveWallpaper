package ru.siniykv.simplelivewallpaper;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SimpleWallpaperListener implements ApplicationListener {

    public Resolver resolver = null;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Texture texture;
    private float delta;


    @Override
    public void create() {
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(false);

        batch = new SpriteBatch();
        texture = new Texture("wallpaper.jpg");
    }

    @Override
    public void resize(int width, int height) {
        delta = texture.getWidth() / width;
    }

    @Override
    public void render() {

/*        if (isAndroid && resolver != null) // In daydream resolver is null
            camera.position.x = (sW / 2) - resolver.getxPixelOffset();
*/

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.position.set(camera.viewportWidth / 2 - resolver.getxPixelOffset() * delta, camera.viewportHeight / 2, 0);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(texture, 0, 0);
        batch.end();

/*        if (!isAndroid && Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
            Gdx.app.exit();
*/
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        texture.dispose();
    }

}
