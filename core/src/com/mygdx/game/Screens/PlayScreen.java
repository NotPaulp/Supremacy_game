package com.mygdx.game.Screens;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Scenes.Hud;

import java.awt.Menu;
import java.util.Random;

import javax.xml.soap.Text;



public class PlayScreen implements Screen, InputProcessor, Input.TextInputListener {
    private static int n =MainMenuScreen.n;
    private TextButton menubtn;
    private MyGdxGame game;
    private Hud hud;
    private Table buttons;
    private TextButton info;
    private boolean infodraw;
    private Skin skin;
    private boolean possible=false;
    private boolean addcheck=true;
    private TextButton button;
    private Sprite[] border= new Sprite[6 ];
    public static int player_win;
    private TextButton btncontinue;
    private TextButton btnmainmenu;
    private boolean captured;
    private Table menu;
    private Sprite[] borderwhite=new Sprite[6];
    private Sprite[] borderblack=new Sprite[6];
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer render;
    private Texture[] textures = new Texture[21];
    private Sprite[] sprite = new Sprite[21];
    private boolean lsprite = false;
    private boolean lmap = true;
    private Texture NorthAmerica=new Texture(Gdx.files.internal("NorthAmerica.png"));
    private Texture SouthAmerica=new Texture(Gdx.files.internal("SouthAmerica.png"));
    private Texture Europe=new Texture(Gdx.files.internal("Europe.png"));
    private Texture Africa=new Texture(Gdx.files.internal("Africa.png"));
    private Texture Asia=new Texture(Gdx.files.internal("Asia.png"));
    private Texture Australia=new Texture(Gdx.files.internal("Australia.png"));
    public static Field[] fields = new Field[21];
    private final float proportion = Gdx.graphics.getHeight() / 830.0f;
    private final float indent = (Gdx.graphics.getWidth() - proportion * 1200) / 2.0f;
    public int phase = 1 ;
    public static int active_player = 1;
    public static String text;
    GlyphLayout layout;
    Stage stage;
    Slider slider;
    int from=0;
    private Sprite allfields;
    public static int max;
    public static BitmapFont font;
    private final TextButton buttonnext;
    private final TextButton.TextButtonStyle buttonnextstyle;
    private float[][] borderscolor=new float[n+1][3];
    public PlayScreen(MyGdxGame game) {
        initborderswhite();
        initborderscolor();
        initbordersblack();
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
        allfields=new Sprite(new Texture(Gdx.files.internal("fields.png")));
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
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("skin/flat-earth-ui.atlas"));
        stage = new Stage();

        skin = new Skin(Gdx.files.internal("skin/flat-earth-ui.json"));

//        Table table = new Table();
//        table.setFillParent(true);
//        table.setDebug(true);
//        stage.addActor(table);
//        table.setSize(1000,1000);
//        // temporary until we have asset manager in
//        Skin skin = new Skin(Gdx.files.internal("skin/flat-earth-ui.json"));
//        table.setTransform(true);
//        table.setScale(1f);
//        table.center();
//        //create buttons
//        TextButton newGame = new TextButton("New Game", skin);
//        TextButton preferences = new TextButton("Preferences", skin);
//        TextButton exit = new TextButton("Exit", skin);
//        newGame.setTransform(true);
//        newGame.setScale(2f);
//        table.setPosition(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2,0);
//        //add buttons to table
//        table.add(newGame).fillX().uniformX();
//        table.row().pad(10, 0, 10, 0);
//        table.add(preferences).fillX().uniformX();
//        table.row();
//        table.add(exit).fillX().uniformX();
//        exit.addListener(new ChangeListener() {
//            @Override
//            public void changed(ChangeEvent event, Actor actor) {
//                Gdx.app.exit();
//            }
//        });
//
//        newGame.addListener(new ChangeListener() {
//            @Override
//            public void changed(ChangeEvent event, Actor actor) {
//
//            }
//        });
        buttonnextstyle = new TextButton.TextButtonStyle();
        buttonnextstyle.font = font;
        buttonnextstyle.fontColor = Color.RED;
        buttonnextstyle.downFontColor = Color.PINK;




        buttonnext = new TextButton("REINFORCEMENT", buttonnextstyle);
        buttonnext.getLabel().setFontScale(Gdx.graphics.getWidth()*1f/2392*5f, Gdx.graphics.getWidth()*1f/2392*5f);

//        buttonnext.setWidth(500f);
//        buttonnext.setHeight(0f);
        buttonnext.setPosition(proportion * 600 + indent-buttonnext.getWidth()/2, 10 * proportion);

        buttonnext.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                next();
                createmenu();
                render.render();
            }
        });
        buttons=new Table();
        TextButton empty=new TextButton("",buttonnextstyle);
        buttons.setTransform(true);
        menubtn=new TextButton("=",skin);
        menubtn.setTransform(true);
        menubtn.getLabel().setFontScale(Gdx.graphics.getWidth()*1f/2392*5f, Gdx.graphics.getWidth()*1f/2392*5f);
        info=new TextButton("?",skin);
        menubtn.setTransform(true);
        menubtn.getLabel().setFontScale(Gdx.graphics.getWidth()*1f/2392*5f, Gdx.graphics.getWidth()*1f/2392*5f);
        buttons.setSize(Gdx.graphics.getWidth()*1f/1.2f,Gdx.graphics.getWidth()*1f/2);
        buttons.add(info).width(Gdx.graphics.getWidth()*1f/2392*1000).height(Gdx.graphics.getWidth()*1f/2392*200);
        buttons.row();
        buttons.add(empty).width(Gdx.graphics.getWidth()*1f/2392*1000).height(Gdx.graphics.getWidth()*1f/2392*200);
        buttons.row();
        buttons.add(menubtn).width(Gdx.graphics.getWidth()*1f/2392*1000).height(Gdx.graphics.getWidth()*1f/2392*200);
        buttons.setPosition(Gdx.graphics.getWidth()*1f/2,Gdx.graphics.getHeight()*1f/2,0);
        buttons.defaults().expandX().fillX();
        info.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("info");
            }
        });
        menubtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                createmenu();
                render.render();
            }
        });
        stage.addActor(buttons);
        stage.addActor(menubtn);
        stage.addActor(info);
        stage.addActor(buttonnext);

        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(this);
        Gdx.input.setInputProcessor(multiplexer);

        hostinit(n);

    }

    @Override
    public void input(String text) {

    }

    @Override
    public void canceled() {

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
        update(delta); 
        captured=false;

        if (this.lmap) {


            Gdx.gl.glClearColor(0.604f, 0.969f, 0.996f, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            render.render();
            game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
            hud.stage.draw();
            game.batch.begin();

            for (int k = 0; k < 21; k++) {
                int i = k / 10;
                int j = k % 10;
                textures[k] = new Texture(Gdx.files.internal(i + "" + j + ".png"));
                sprite[k] = new Sprite(textures[k]);

                this.sprite[k].setColor(fields[k].r,fields[k].g,fields[k].b,1f);
                this.sprite[k].draw(game.batch);
                font.draw(game.batch, fields[k].units+ "", fields[k].textx, fields[k].texty, 0, Align.center, true);


            }

            initborders();
            for (int b=0;b<6;b++) {
                border[b].draw(game.batch);
            }
            game.batch.end();

        lmap=false;
        }
        if (this.lsprite) {
        game.batch.begin();


            initborders();
            for (int bw=0;bw<6;bw++)
                borderwhite[bw].draw(game.batch);
            for (int b=0;b<6;b++)
                border[b].draw(game.batch);

            for (int k = 0; k < 21; k++) {

                this.sprite[k].setColor(1,1,1,1);
                this.sprite[k].draw(game.batch);
                this.sprite[k].setColor(fields[k].r,fields[k].g,fields[k].b,fields[k].a);
                this.sprite[k].draw(game.batch);
                font.draw(game.batch, fields[k].units+ "", fields[k].textx, fields[k].texty, 0, Align.center, true);


            }
            game.batch.end();
        }
        if (infodraw){
        game.batch.begin();
            for (int bb=0;bb<6;bb++)
                borderblack[bb].draw(game.batch);
            allfields.setColor(Color.BLACK);
            allfields.draw(game.batch);
        game.batch.end();
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
    public void createmenu(){
        menu=new Table();
        TextButton empty=new TextButton("",buttonnextstyle);
        menu.setTransform(true);
        btncontinue = new TextButton("CONTINUE", skin);
        btncontinue.setTransform(true);
        btncontinue.getLabel().setFontScale(Gdx.graphics.getWidth()*1f/2392*5f, Gdx.graphics.getWidth()*1f/2392*5f);

        Sprite background = new Sprite(new Texture(Gdx.files.internal("menubackground.png")));
        TextureRegionDrawable textureRegionDrawableBg = new TextureRegionDrawable(new TextureRegion(background));
        menu.setBackground(textureRegionDrawableBg);
        btnmainmenu = new TextButton("EXIT", skin);
        btnmainmenu.getLabel().setFontScale(Gdx.graphics.getWidth()*1f/2392*5f, Gdx.graphics.getWidth()*1f/2392*5f);
        menu.setSize(Gdx.graphics.getWidth()*1f/1.2f,Gdx.graphics.getWidth()*1f/2);
        menu.add(btncontinue).width(Gdx.graphics.getWidth()*1f/2392*1000).height(Gdx.graphics.getWidth()*1f/2392*200);
        menu.row();
        menu.add(empty).width(Gdx.graphics.getWidth()*1f/2392*1000).height(Gdx.graphics.getWidth()*1f/2392*200);
        menu.row();
        menu.add(btnmainmenu).width(Gdx.graphics.getWidth()*1f/2392*1000).height(Gdx.graphics.getWidth()*1f/2392*200);
        menu.setPosition(Gdx.graphics.getWidth()*1f/2,Gdx.graphics.getHeight()*1f/2,0);
        menu.defaults().expandX().fillX();
        btncontinue.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                menu.clear();
                menu.remove();
                render.render();
            }
        });
        btnmainmenu.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });
        stage.addActor(menu);

    }
    public void initborderswhite(){
        borderwhite[0]=new Sprite(new Texture(Gdx.files.internal("NorthAmerica.png")));
        borderwhite[1]=new Sprite(new Texture(Gdx.files.internal("SouthAmerica.png")));
        borderwhite[2]=new Sprite(new Texture(Gdx.files.internal("Africa.png")));
        borderwhite[3]=new Sprite(new Texture(Gdx.files.internal("Europe.png")));
        borderwhite[4]=new Sprite(new Texture(Gdx.files.internal("Asia.png")));
        borderwhite[5]=new Sprite(new Texture(Gdx.files.internal("Australia.png")));
    }
    public void initbordersblack() {
        borderblack[0] = new Sprite(new Texture(Gdx.files.internal("NorthAmerica.png")));
        borderblack[1] = new Sprite(new Texture(Gdx.files.internal("SouthAmerica.png")));
        borderblack[2] = new Sprite(new Texture(Gdx.files.internal("Africa.png")));
        borderblack[3] = new Sprite(new Texture(Gdx.files.internal("Europe.png")));
        borderblack[4] = new Sprite(new Texture(Gdx.files.internal("Asia.png")));
        borderblack[5] = new Sprite(new Texture(Gdx.files.internal("Australia.png")));
        for (int bb=0;bb<6;bb++){
            borderblack[bb].setColor(0,0,0,1f);
        }
    }
    public void initborderscolor(){
        borderscolor[1][0]=1;
        borderscolor[1][1]=0.5f;
        borderscolor[1][2]=0.44f;
        borderscolor[2][0]=0;
        borderscolor[2][1]=0.75f;
        borderscolor[2][2]=1;
        if (n>3) {
            borderscolor[3][0] = 0.5f;
            borderscolor[3][1] = 1;
            borderscolor[3][2] = 0;
            borderscolor[4][0] = 1;
            borderscolor[4][1] = 1;
            borderscolor[4][2] = 0;
        }
    }
    public void initborders(){
        border[0] = new Sprite(NorthAmerica);
        if (fields[0].player==fields[1].player&&fields[1].player==fields[2].player&&fields[2].player==fields[3].player)
            border[0].setColor(borderscolor[fields[0].player][0],borderscolor[fields[0].player][1],borderscolor[fields[0].player][2],1f);
        else
            border[0].setColor(0,0,0,1f);
        border[1] = new Sprite(SouthAmerica);
        if (fields[4].player==fields[5].player&&fields[5].player==fields[6].player)
            border[1].setColor(borderscolor[fields[4].player][0],borderscolor[fields[4].player][1],borderscolor[fields[4].player][2],1f);
        else
            border[1].setColor(0,0,0,1f);
        border[2] = new Sprite(Africa);
        if (fields[7].player==fields[8].player&&fields[8].player==fields[9].player)
            border[2].setColor(borderscolor[fields[7].player][0],borderscolor[fields[7].player][1],borderscolor[fields[7].player][2],1f);
        else
            border[2].setColor(0,0,0,1f);
        border[3] = new Sprite(Europe);
        if (fields[10].player==fields[11].player&&fields[11].player==fields[12].player)
            border[3].setColor(borderscolor[fields[10].player][0],borderscolor[fields[10].player][1],borderscolor[fields[10].player][2],1f);
        else
            border[3].setColor(0,0,0,1f);
        border[4] = new Sprite(Asia);
        if (fields[13].player==fields[14].player&&fields[14].player==fields[15].player&&fields[15].player==fields[16].player)
            border[4].setColor(borderscolor[fields[13].player][0],borderscolor[fields[13].player][1],borderscolor[fields[13].player][2],1f);
        else
            border[4].setColor(0,0,0,1f);
        border[5] = new Sprite(Australia);
        if (fields[17].player==fields[18].player&&fields[18].player==fields[19].player&&fields[19].player==fields[20].player)
            border[5].setColor(borderscolor[fields[20].player][0],borderscolor[fields[20].player][1],borderscolor[fields[20].player][2],1f);
        else
            border[5].setColor(0,0,0,1f);

    }
    public static void move(Field field,int phase,Field from,int min,int max){

        TextField.TextFieldStyle style = new TextField.TextFieldStyle();
        style.font = font;
        style.fontColor = Color.CHARTREUSE;

        TextField textfield = new TextField("", style);
        textfield.setText("Test");
        textfield.setWidth(150);
        textfield.setTextFieldFilter(new DigitFilter());

        MyTextInputListener listener = new MyTextInputListener(field,phase,from,min,max);
        Gdx.input.getTextInput(listener, "Выберите количество юнитов для захвата  Min: "+min+"  Max: "+max,""+max , ""+max);
        unselectall();

    }
    public void win(int player){
        this.player_win=player;
        game.setScreen(new WinScreen(game));
        dispose();
    }
    public void next(){
        unselectall();
        for (int k=0;k<20;k++){
            if (fields[k].player!=fields[k+1].player)
                break;


            if (k==19)
                win(fields[k].player);

        }
        addcheck=true;

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
    public void countadd(){
        int amount=0;
        for (int k = 0; k < 21; k++) {
            if (fields[k].player==active_player)
                amount++;
        }
        amount/=2;
        if (active_player==fields[0].player&&active_player==fields[1].player&&active_player==fields[2].player&&active_player==fields[3].player){
            amount+=4;
        }
        if (active_player==fields[4].player&&active_player==fields[5].player&&active_player==fields[6].player){
            amount+=3;
        }
        if (active_player==fields[10].player&&active_player==fields[11].player&&active_player==fields[12].player){
            amount+=2;
        }
        if (active_player==fields[13].player&&active_player==fields[14].player&&active_player==fields[15].player&&active_player==fields[16].player){
            amount+=5;
        }
        if (active_player==fields[7].player&&active_player==fields[8].player&&active_player==fields[9].player){
            amount+=3;
        }
        if (active_player==fields[17].player&&active_player==fields[18].player&&active_player==fields[19].player&&active_player==fields[20].player){
            amount+=4;
        }
        max=amount;
    }
    public void count(Field field){
        max=field.units-1;
    }
    public static void unselectall(){
        for (int k = 0; k < 21; k++) {fields[k].setSelection("-");}
    }
    public void createtextfield(Field field){
        TextField.TextFieldStyle style = new TextField.TextFieldStyle();
        style.font = font;
        style.fontColor = Color.CHARTREUSE;

        TextField textfield = new TextField("", style);

        
        textfield.setTextFieldFilter(new DigitFilter());
        MyTextInputListener listener = new MyTextInputListener(field,phase,fields[from],0,0);
        switch(phase) {
            case 1:
                Gdx.input.getTextInput(listener, "Выберите количество юнитов для укрепления    Max: " + max, ""+max, "" + max);
                break;
            case 2:
                Gdx.input.getTextInput(listener, "Выберите количество юнитов для атаки    Max: " + max, ""+max, "" + max);
                break;
            case 3:
                Gdx.input.getTextInput(listener, "Выберите количество юнитов для перемещения    Max: " + max, ""+max, "" + max);
                break;
        }
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
                            unselectall();
                            add(k);
                            if (addcheck)
                                countadd();
                            addcheck=false;
                            if (max!=0)
                            createtextfield(fields[k]);
                            else
                            next();
                            break;
                        case (2):
                            if (fields[k].units>1) {
                                attack(k);
                                from = k;
                                count(fields[k]);
                            }
                            break;
                        case (3):

                            if (fields[k].selected!="army_regroup_to"&&fields[k].units>1) {
                                from=k;
                                count(fields[k]);
                                possible = false;
                                regroup(k);
                                if (possible) {
                                    fields[k].setSelection("army_regroup_from");
                                } else {
                                    fields[k].setSelection("-");
                                }
                                count(fields[k]);
                            }else if (fields[k].selected=="army_regroup_to"){
                                if (max==1) {
                                    fields[k].addUnits(1);
                                    fields[from].addUnits(-1);
                                }else
                                    createtextfield(fields[k]);
                                next();
                            }
                            break;
                    }}
                    if (fields[k].selected=="attack_threat") {
                        createtextfield(fields[k]);

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
