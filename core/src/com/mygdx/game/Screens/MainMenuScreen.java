package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;

public class MainMenuScreen implements Screen, InputProcessor {
    public static int n;
    final MyGdxGame game;
    private Viewport gamePort;

    OrthographicCamera camera;
    MyGdxGame game1;
//    private Stage stage; //** stage holds the Button **//
    private TextureAtlas buttonsAtlas; //** image of buttons **//
    private Skin buttonSkin; //** images are used as skins of the button **//
    private TextButton button;
    private Sprite Supremacy;
    public MainMenuScreen(final MyGdxGame game) {
        this.game = game;
//        font = new BitmapFont();
//        font.getData().setScale(1f);
//        camera = new OrthographicCamera();
//        camera.setToOrtho(false, 800, 480);
//        buttonsAtlas = new TextureAtlas("atlas/buttonnext.pack"); //**button atlas image **//
//        buttonSkin = new Skin();
//        buttonSkin.addRegions(buttonsAtlas); //** skins for on and off **//
//        font = new BitmapFont(); //** font **//
//
//        stage = new Stage();        //** window is stage **//
//        stage.clear();
//        Gdx.input.setInputProcessor(stage); //** stage is responsive **//
//
//        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle(); //** Button properties **//
//        style.up = buttonSkin.getDrawable("nextunpressed");
//        style.down = buttonSkin.getDrawable("forwardpressed");
//
//        style.font = font;
//
//        button = new TextButton("START", style);
//        //** Button text and style **//
//        button.setHeight(1000); //** Button Height **//
//        button.setWidth(1000); //** Button Width **//
//
//        button.setPosition(0,0);
//
//        button.addListener(new InputListener() {
//            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
//                Gdx.app.log("my app", "Pressed"); //** Usually used to start Game, etc. **//
//
//
//                // TODO Auto-generated method stub
//
//
//
//                return true;
//
//            }
//
//            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
//
//
//            }
//        });
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.input.setInputProcessor(this);
        camera= new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics. getWidth(), Gdx.graphics.getHeight());
        gamePort =new FitViewport(MyGdxGame.V_WIDTH,MyGdxGame.V_HEIGHT,camera);
        ScreenUtils.clear(0, 0, 0, 1);
        camera.position.set(gamePort.getScreenWidth()/2,gamePort.getScreenHeight()/2,0);
        camera.update();


        Supremacy= new Sprite(new Texture(Gdx.files.internal("Supremacy.jpg")));
        Supremacy.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        game.batch.begin();
        Supremacy.draw(game.batch);
        game.batch.end();


    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            if (screenX<Gdx.graphics.getWidth()/2){
                this.n=2;

            }else{
                this.n=4;
            }
            game.setScreen(new PlayScreen(game));
            dispose();



        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}

