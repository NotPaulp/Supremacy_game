package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;

import static com.mygdx.game.Screens.PlayScreen.fields;

public class WinScreen implements Screen, InputProcessor {
    final MyGdxGame game;
    private Viewport gamePort;
    private BitmapFont font;
    OrthographicCamera camera;
    private int player=PlayScreen.player_win;
    private Stage stage;
    private Table menu;
    private Label.LabelStyle defaultlabelstyle;
    private Label fontlabel;
    private BitmapFont amountfont;
    public WinScreen(final MyGdxGame game){
        this.game=game;
        menu=new Table();
        defaultlabelstyle=new Label.LabelStyle();
        amountfont = new BitmapFont();
        amountfont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        amountfont.setColor(1, 1, 1, 1);
        amountfont.getData().setScale(6f);
        defaultlabelstyle.font=amountfont;
        defaultlabelstyle.fontColor=Color.WHITE;
        TextButton.TextButtonStyle buttonnextstyle = new TextButton.TextButtonStyle();
        buttonnextstyle.font = font;
        buttonnextstyle.fontColor = Color.WHITE;
        buttonnextstyle.downFontColor = Color.BLACK;
        TextButton empty=new TextButton("",buttonnextstyle);
        fontlabel = new Label("Player "+player+" won!",defaultlabelstyle);
        menu.setTransform(true);
        menu.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getWidth());
        menu.add(fontlabel).width(Gdx.graphics.getWidth()*1f/2392*1200).height(Gdx.graphics.getWidth()*1f/2392*250);
        menu.setPosition(Gdx.graphics.getWidth()*1f/2,Gdx.graphics.getHeight()*1f/2,0);
        menu.defaults().expandX().fillX();
        stage.addActor(menu);
        Gdx.input.setInputProcessor(stage);
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
        Gdx.gl.glClearColor(0.604f, 0.969f, 0.996f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        font = new BitmapFont();
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.setColor(fields[0].r,fields[0].g,fields[0].b,1f);
        font.getData().setScale(2.5f);
        camera.position.set(gamePort.getScreenWidth()*1f/2,gamePort.getScreenHeight()*1f/2,0);
        camera.update();
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
        game.setScreen(new MainMenuScreen(game));
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
