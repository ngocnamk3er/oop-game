package main;

import buttons.CloseGameButton;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import map.MapManager;
import mapinteraction.MapInteractionManager;

public class GameScene extends Scene{
    private Canvas canvas;
    private Canvas canvasbg;
    private GameLoop gameLoop;
    private GraphicsContext gc;
    private GraphicsContext gcbg;
    private MapManager mapManager;
    private CloseGameButton closeGameButton;
    private MapInteractionManager mapInteractionManager;
    public GameScene(MainStage mainStage) {
        super(new Group(),1344,768);
        canvas=new Canvas(1344,768);
        canvasbg=new Canvas(1344,768);

        gc=canvas.getGraphicsContext2D();
        gcbg=canvasbg.getGraphicsContext2D();
        
        mapManager=new MapManager(gcbg);
        mapInteractionManager = new MapInteractionManager(gc,mapManager.getMapData(),this);

        Pane pane=new Pane();
        pane.getChildren().add(canvasbg);
        pane.getChildren().add(canvas);
        setRoot(pane);
        closeGameButton = new CloseGameButton(mainStage);
        pane.getChildren().add(closeGameButton);
        closeGameButton.setLayoutX(1344-58);
        closeGameButton.setLayoutY(10);
        setCursor(new ImageCursor(new Image(SelectLevelScene.class.getResourceAsStream("cursorImage.png"))));
    }
    public void MakeGameLevel(int levelValue){
        mapManager.loadDataMap(levelValue);//level
        mapManager.render();//level
        mapInteractionManager.setInitialState(levelValue);//level
        gameLoop = new GameLoop(mapInteractionManager);
        gameLoop.start();
    }
    public void MakeGameNextLevel(int levelValue){
        mapManager.loadDataMap(levelValue);//level
        mapManager.render();//level
        mapInteractionManager.setInitialState(levelValue);//level
    }
    public GameLoop getGameLoop() {
        return gameLoop;
    }
    public void setGameLoop(GameLoop gameLoop) {
        this.gameLoop = gameLoop;
    }
    
}
