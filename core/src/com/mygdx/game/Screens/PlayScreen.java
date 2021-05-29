package com.mygdx.game.Screens;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Scenes.Hud;
import java.util.Random;
public class PlayScreen implements Screen, InputProcessor {
    private int n =MainMenuScreen.n;
    private MyGdxGame game;
    private Hud hud;
    private Skin skin;
    private boolean possible=false;
    private BitmapFont font;//** images are used as skins of the button **//
    private TextButton button;
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer render;
    private Texture[] textures = new Texture[21];
    private Sprite[] sprite = new Sprite[21];
    private boolean lsprite = false;
    private boolean lmap = true;
    private Field[] fields = new Field[21];
    private final float proportion = Gdx.graphics.getHeight() / 830.0f;
    private final float indent = (Gdx.graphics.getWidth() - proportion * 1200) / 2.0f;
    public int phase = 1 ;
    public int active_player = 1;
    GlyphLayout layout;
    Stage stage;

    private final TextButton buttonnext;
    private final TextButton.TextButtonStyle buttonnextstyle;
    public PlayScreen(MyGdxGame game) {
        for (int k = 0; k < 21; k++) {
            int i = k / 10;
            int j = k % 10;
            Field field = new Field();
            field.setNumber(k);
            fields[k] = field;
            float xstart = fields[k].position[0][0] * proportion + indent;
            float ystart = fields[k].position[0][1] * proportion;
            float xend = fields[k].position[1][0] * proportion + indent;
            float yend = fields[k].position[1][1] * proportion;
            fields[k].setCoordinates(xstart, ystart, xend, yend);

        }
        this.game = game;
        gamecam = new OrthographicCamera();
        gamecam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        gamePort = new FitViewport(MyGdxGame.V_WIDTH, MyGdxGame.V_HEIGHT, gamecam);
        hud = new Hud(game.batch);
        maploader = new TmxMapLoader();
        map = maploader.load("map.tmx");
        render = new OrthogonalTiledMapRenderer(map);


        font = new BitmapFont();
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.setColor(1, 1, 1, 1);
        font.getData().setScale(2.5f);
        gamecam.position.set(gamePort.getScreenWidth() / 2, gamePort.getScreenHeight() / 2, 0);

        stage = new Stage();
        skin = new Skin(Gdx.files.internal("skin/flat-earth-ui.json"));

        //dialog window
        Dialog dialog = new Dialog("Warning", skin, "default") {
            public void result(Object obj) {
                System.out.println("result "+obj);
            }
        };
        dialog.text("Are you sure you want to kill obema?");

        dialog.button("Yes", true); //sends "true" as the result
        dialog.button("No", false); //sends "false" as the result
        dialog.getBackground().setMinWidth(1000f);
        dialog.getBackground().setMinHeight(1000f);
        dialog.show(stage);


        buttonnextstyle = new TextButton.TextButtonStyle();
        buttonnextstyle.font = font;
        buttonnextstyle.fontColor = Color.RED;
        buttonnextstyle.downFontColor = Color.BLACK;

        buttonnext = new TextButton("REINFORCEMENT", buttonnextstyle);
        buttonnext.getLabel().setFontScale(5f, 5f);
//        buttonnext.setWidth(500f);
//        buttonnext.setHeight(0f);
        buttonnext.setPosition(proportion * 600 + indent, 10 * proportion);

        buttonnext.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                next();
                render.render();
            }
        });

        stage.addActor(buttonnext);
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(stage); // set stage as first input processor
        multiplexer.addProcessor(this);  // set your game input precessor as second
        Gdx.input.setInputProcessor(multiplexer);

        hostinit(n);

    }

    public enum State {
        PAUSE,
        RUN,
        RESUME,
        STOPPED
    }

    private State state = State.RUN;


    @Override
    public void show() {

//        exit.addListener(new ChangeListener() {
//            @Override
//            public void changed(ChangeEvent event, Actor actor) {
//                Gdx.app.exit();
//            }
//        });
    }


    public void update(float deltaTime) {

        gamecam.update();
        render.setView(gamecam);



    }

    @Override
    public void render(float delta) {


        if (this.lmap) {

            update(delta);
            Gdx.gl.glClearColor(0.604f, 0.969f, 0.996f, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            render.render();
            game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
            hud.stage.draw();

            for (int k = 0; k < 21; k++) {
                int i = k / 10;
                int j = k % 10;
                textures[k] = new Texture(Gdx.files.internal(i + "" + j + ".png"));
                sprite[k] = new Sprite(textures[k]);


                game.batch.begin();


                this.sprite[k].setColor(fields[k].r,fields[k].g,fields[k].b,1f);
                this.sprite[k].draw(game.batch);


                font.draw(game.batch, fields[k].units+ "", fields[k].textx, fields[k].texty, 0, Align.center, true);

                game.batch.end();

            }

        lmap=false;
        }
        if (this.lsprite) {
            for (int k = 0; k < 21; k++) {
                game.batch.begin();
                this.sprite[k].setColor(1,1,1,1);
                this.sprite[k].draw(game.batch);
                this.sprite[k].setColor(fields[k].r,fields[k].g,fields[k].b,fields[k].a);
                this.sprite[k].draw(game.batch);
                font.draw(game.batch, fields[k].units+ "", fields[k].textx, fields[k].texty, 0, Align.center, true);
                game.batch.end();

            }


        }
        stage.draw();
        stage.act();

    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
        gamecam.position.set(gamecam.viewportWidth / 2, gamecam.viewportHeight / 2, 0);

    }

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
        font.dispose();
        render.dispose();
        map.dispose();
        hud.dispose();
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
    public void next(){
        unselectall();
        if (phase==3){
            this.phase=1;
            if (active_player==n){
                this.active_player=1;
            } else{
                this.active_player+=1;
            }
        } else{
            this.phase+=1;
        }
        switch(phase){
            case (1):
                buttonnext.setText("REINFORCEMENT");
                break;
            case (2):
                buttonnext.setText("ATTACK");
                break;
            case (3):
                buttonnext.setText("STRENGTHENING");
                break;
        }
        switch(active_player){
            case (1):
                buttonnextstyle.fontColor = Color.RED;
                buttonnextstyle.downFontColor = Color.PINK;
                break;
            case (2):
                buttonnextstyle.fontColor = Color.BLUE;
                buttonnextstyle.downFontColor = Color.CYAN;
                break;
            case (3):
                buttonnextstyle.fontColor = Color.GREEN;
                buttonnextstyle.downFontColor = Color.LIME;
                break;
            case(4):
                buttonnextstyle.fontColor = Color.ORANGE;
                buttonnextstyle.downFontColor = Color.YELLOW;
                break;
        }
    }
    public void unselectall(){
        for (int k = 0; k < 21; k++) {fields[k].setSelection("-");}
    }
    public void add(int k){
        fields[k].setSelection("add");
    }
    public void attack(int k){
        fields[k].setSelection("attack");
        for (int n = 0; n < 6; n++) {
            if (fields[k].neighbours[n] != -1) {
                if ((fields[k].player != fields[fields[k].neighbours[n]].player)) {
                    fields[fields[k].neighbours[n]].setSelection("attack_threat");
                }
            }
        }
    }

    public void regroup(int k){
        fields[k].setSelection("army_regroup_to");
        for (int n = 0; n < 6; n++) {
            if (fields[k].neighbours[n] != -1) {
                if ((fields[k].player == fields[fields[k].neighbours[n]].player)&&(fields[fields[k].neighbours[n]].selected!="army_regroup_to")) {
                    possible=true;
                    fields[fields[k].neighbours[n]].setSelection("army_regroup_to");
                    regroup(fields[k].neighbours[n]);
                }
            }
        }
    }
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        for (int k = 0; k < 21; k++) {
            if ((screenX > fields[k].xstart && screenY > fields[k].ystart) && (screenX < fields[k].xend && screenY < fields[k].yend)) {
                if (fields[k].player==active_player||fields[k].selected=="attack_threat") {
                    if (fields[k].player==active_player){
                    switch (phase) {
                        case (1):
                            add(k);
                            break;
                        case (2):
                            attack(k);

                            break;
                        case (3):
                            possible = false;
                            regroup(k);
                            if (possible) {
                                fields[k].setSelection("army_regroup_from");
                            } else {
                                fields[k].setSelection("-");
                            }
                            break;
                    }}
                    if (fields[k].selected=="attack_threat"){
                        fields[k].setPlayer(active_player);
                    }
                    break;
                }
            }
            if (k == 20) {
//                next();
                unselectall();
            }
        }
        this.lsprite = true;
        this.lmap = false;
        render.render();
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
//        float x = Gdx.input.getDeltaX();
//        float y = Gdx.input.getDeltaY();
//
//        gamecam.translate(-x, y);
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {

        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
//        if (gamecam.zoom - 0 >= -0.25f && gamecam.zoom - 100 <= 2) {
//            gamecam.zoom -= (float) 0.2 / 10f;
//        }
        return true;
    }

    public void hostinit(int n) {
        int minu=n-1;
        int maxu=7;
        boolean lu;
        int player;
        int un=0;
        int [] units=new int[n];
        for (int u = 0; u < n; u++) {
            if ((u+1)==n){
                units[u] = 35 - (int) 21 / n+1;
            }
            else {
                units[u] = 35 - (int) 21 / n;
            }
        }

        boolean l;
        int[] taken = new int[21];
        for (int t = 0; t < 21; t++) {
            taken[t] = -1;
        }
        for (int k = 0; k < 21; k++) {
            l=true;
            while (l) {
                l = false;
                int number = (int) new Random().nextInt(21);
                for (int t = 0; t < 21; t++) {
                    if (taken[t] == number) {
                        l = true;
                        break;
                    }
                }
                if (!l) {
                        lu=true;

                        if ((k / (21 / n) + 1) > n) {
                            player=k / (21 / n);
                        } else {
                            player=k / (21 / n) + 1;
                        }
                        if ((((k+1 )/ (21 / n))>player)){
                        un = units[player-1];
                        } else {
                        while (lu) {
                            un = (int) (Math.random() * (maxu - minu)) + minu;
                            if (units[player-1]-un>=0||units[player-1]==0){
                                lu=false;
                            }
                            }
                        }
                    fields[number].setPlayer(player);
                    fields[number].addUnits(un);
                    taken[k] = number;
                }
            }
        }
    }

}
