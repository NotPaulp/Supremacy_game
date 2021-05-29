package com.mygdx.game.Scenes;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.graphics.Color;


public class Hud implements Disposable {
    public Stage stage;
    private Viewport viewport;
    private int worldTimer;
    private float timeCount;
    private int score;
    private int player;
    Label countdownLabel;
    Label scoreLabel;
    Label playersLabel;
    Label playerLabel;
    Label levelLabel;

    public Hud(SpriteBatch sb) {
        worldTimer=300;
        timeCount=0;
        score=0;
        player=1;
        viewport= new FitViewport(MyGdxGame.V_WIDTH,MyGdxGame.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport,sb);
        Table table=new Table();
        table.top();
        table.setFillParent(true);
        countdownLabel=new Label(String.format("%03d",worldTimer),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel=new Label(String.format("%06d",worldTimer),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        playersLabel = new Label("PLAYER", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        playerLabel = new Label(String.format("%01d",player),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        table.add(playersLabel).expandX().padTop(5);
        table.row();
        table.add(playerLabel).expandX().padTop(5);
        stage.addActor(table);
        stage.dispose();
    }


    @Override
    public void dispose() {
        stage.dispose();
    }
}
