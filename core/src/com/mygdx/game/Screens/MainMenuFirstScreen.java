package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
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

public class MainMenuFirstScreen implements Screen,InputProcessor {
    public static int n;
    final MyGdxGame game;
    private Viewport gamePort;
    private Table menu;
    OrthographicCamera camera;
    MyGdxGame game1;
    Stage stage;
    private BitmapFont font;
    public static boolean newgame;
   private Skin skin;
   private TextButton btnexit;
    private TextButton btncontinue;
    private TextButton btnnewgame;
    //    private Stage stage; //** stage holds the Button **//
    private TextureAtlas buttonsAtlas; //** image of buttons **//
    private Skin buttonSkin; //** images are used as skins of the button **//
    private TextButton button;
    private Sprite Supremacy;
    public MainMenuFirstScreen(final MyGdxGame game) {

        this.game = game;
        stage=new Stage();
        camera= new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics. getWidth(), Gdx.graphics.getHeight());
        gamePort =new FitViewport(MyGdxGame.V_WIDTH,MyGdxGame.V_HEIGHT,camera);
        camera.position.set(gamePort.getScreenWidth()*1f/2,gamePort.getScreenHeight()*1f/2,0);
        font = new BitmapFont();
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.setColor(1, 1, 1, 1);
        font.getData().setScale(3f);
        menu=new Table();
        TextButton.TextButtonStyle buttonnextstyle = new TextButton.TextButtonStyle();
        buttonnextstyle.font = font;
        buttonnextstyle.fontColor = Color.WHITE;
        buttonnextstyle.downFontColor = Color.BLACK;
        TextButton empty=new TextButton("",buttonnextstyle);
        menu.setTransform(true);
        skin = new Skin(Gdx.files.internal("skin/flat-earth-ui.json"));
        btncontinue = new TextButton("CONTINUE", skin);
        btncontinue.setTransform(true);
        btncontinue.getLabel().setFontScale(Gdx.graphics.getWidth()*1f/2392*5f, Gdx.graphics.getWidth()*1f/2392*5f);
        btnnewgame = new TextButton("NEW GAME", skin);
        btnnewgame.getLabel().setFontScale(Gdx.graphics.getWidth()*1f/2392*5f, Gdx.graphics.getWidth()*1f/2392*5f);
        btnexit = new TextButton("EXIT", skin);
        btnexit.getLabel().setFontScale(Gdx.graphics.getWidth()*1f/2392*5f, Gdx.graphics.getWidth()*1f/2392*5f);
        Sprite background = new Sprite(new Texture(Gdx.files.internal("MainMenu1background.png")));
        TextureRegionDrawable textureRegionDrawableBg = new TextureRegionDrawable(new TextureRegion(background));
        menu.setBackground(textureRegionDrawableBg);

        menu.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getWidth());
        menu.add(btncontinue).width(Gdx.graphics.getWidth()*1f/2392*1200).height(Gdx.graphics.getWidth()*1f/2392*250);
        menu.row();
        menu.add(empty).width(Gdx.graphics.getWidth()*1f/2392*1000).height(Gdx.graphics.getWidth()*1f/2392*180);
        menu.row();
        menu.add(btnnewgame).width(Gdx.graphics.getWidth()*1f/2392*1200).height(Gdx.graphics.getWidth()*1f/2392*250);
        menu.row();
        menu.add(empty).width(Gdx.graphics.getWidth()*1f/2392*1000).height(Gdx.graphics.getWidth()*1f/2392*180);
        menu.row();
        menu.add(btnexit).width(Gdx.graphics.getWidth()*1f/2392*1200).height(Gdx.graphics.getWidth()*1f/2392*250);
        menu.setPosition(Gdx.graphics.getWidth()*1f/2,Gdx.graphics.getHeight()*1f/2,0);
        menu.defaults().expandX().fillX();
        btncontinue.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                newgame=false ;
                game.setScreen(new PlayScreen(game));
                dispose();
            }
        });
        btnnewgame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                newgame=true;
                game.setScreen(new MainMenuScreen(game));
                dispose();
            }
        });
        btnexit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });
        stage.addActor(menu);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        ScreenUtils.clear(0, 0, 0, 1);
        camera.update();
        stage.draw();
        stage.act();

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


