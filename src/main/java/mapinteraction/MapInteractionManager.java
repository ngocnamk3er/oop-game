package mapinteraction;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import entities.Button;
import entities.Coin;
import entities.Door;
import entities.Enimy;
import entities.Enity;
import entities.Player;
import entities.Stone;
import entities.StrangeDoor;
import help.Constant.MapInteraction;
import inputs.SetKeyBoardInputs;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

import java.awt.image.BufferedImage;
import java.io.IOException;

import main.MakeMainScene;
import static help.HelpMethods.*;
public class MapInteractionManager {
    private MakeMainScene makeMainScene;
    private ArrayList<Coin> coins;
    private ArrayList<Stone> stones;
    private ArrayList<Button> buttons;
    private ArrayList<Enimy> enimies;
    private Player player;
    private Door door;
    private StrangeDoor strangeDoor;
    private GraphicsContext gc;
    private int [][] mapData;
    private Image[][] animationImagesPlayer;
    private Image[][] animationImagesCoin;
    private Image animationImageStone;
    private Image[][] animationImagesDoor;
    private Image[] animationImagesButton;
    private BufferedImage bufferedImage;
    private Image animationImageStrangeDoor;
    private int levelValue;
    private Image[][] animationImagesEnimy;
    private void loadAnimations(){
        loadAnimationsPlayer();
        loadAnimationsCoins();
        loadAnimationsStone();
        loadAnimationsDoor();
        loadAnimationsButton();
        loadAnimationsStrangDoor();
        loadAnimationsEnimy();
    }
    public MapInteractionManager(GraphicsContext gc,int [][]mapData, MakeMainScene makeMainScene){
        loadAnimations();
        this.makeMainScene = makeMainScene;
        coins = new ArrayList<>();
        stones = new ArrayList<>();
        buttons = new ArrayList<>();
        enimies = new ArrayList<>();
        player = new Player();
        door = new Door();
        strangeDoor = new StrangeDoor();
        this.gc = gc;
        this.mapData = mapData;
    }
    public void setInitialState(int levelValue){
        this.levelValue = levelValue;
        loadDataMapInteraction(levelValue);
        new SetKeyBoardInputs(this);
    }
    private void loadDataMapInteraction(int levelValue){
        coins = new ArrayList<>();
        stones = new ArrayList<>();
        enimies = new ArrayList<>();
        buttons = new ArrayList<>();
        player = new Player();
        door = null;
        for(int i=0;i<12;i++){
            for(int j=0;j<21;j++){
                if(MapInteraction.MAP_INTERAC_DATA[levelValue][i][j] == 'c'){
                    Coin coin = new Coin(j*64, i*64,gc,animationImagesCoin);
                    coins.add(coin);
                }
                if(MapInteraction.MAP_INTERAC_DATA[levelValue][i][j] == 'p'){
                    player.setProperties(j*64, i*64,animationImagesPlayer,this);
                    
                }
                if(MapInteraction.MAP_INTERAC_DATA[levelValue][i][j] == 's'){
                    Stone stone = new Stone(j*64, i*64,animationImageStone ,this);
                    stones.add(stone);
                }
                if(MapInteraction.MAP_INTERAC_DATA[levelValue][i][j] == 'b'){
                    Button button = new Button(j*64, i*64,animationImagesButton,this);
                    buttons.add(button);
                }
                if(MapInteraction.MAP_INTERAC_DATA[levelValue][i][j] == 'd'){
                    door = new Door();
                    door.setProperties(j*64, i*64,animationImagesDoor,this);
                }
                if(MapInteraction.MAP_INTERAC_DATA[levelValue][i][j] == 'D'){
                    strangeDoor.setProperties(j*64, i*64, gc,animationImageStrangeDoor);
                }
                if(MapInteraction.MAP_INTERAC_DATA[levelValue][i][j] == 'e'){
                    Enimy enimy = new Enimy(j*64, i*64,animationImagesEnimy ,this);
                    enimies.add(enimy);
                }
            }
        }
    }
    public void update(){
        player.update();
        for(Stone stone:stones){
            stone.update();
        }
    
        for (Coin coin : coins) {
            coin.update();
        }

        for(Button button:buttons){
            button.update();
        }

        if(door!=null){
            door.update();
        }
        for(Enimy enimy:enimies){
            enimy.update();
        }
    }
    public void render(){
        try {
            gc.clearRect(0, 0, 21*64, 12*64);
            // player.render();    
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        strangeDoor.render();
        for(Stone stone:stones){
            stone.render();
        }

        for (Enity coin : coins) {
            coin.render();
        }
        
        for(Button button:buttons){
            button.render();
        }
        for(Enimy enimy:enimies){
            enimy.render();
        }
        if(door!=null){
            door.render();
        }
        
        player.render();  
    }
    
    private void loadAnimationsEnimy() {
        animationImagesEnimy = new Image[8][15];
        for(int i=0;i<8;i++){
            if(i==Enimy.IDLE_R){
                try {
                    bufferedImage = ImageIO.read(Enimy.class.getResourceAsStream("slimeIdleR.png"));
                    int AmountSprites = Enimy.getAmountSpritesOfEnimyAction(i);
                    for(int j=0;j<AmountSprites;j++) {
                        animationImagesEnimy[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage((AmountSprites-1-j)*16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            if(i==Enimy.RUN_R){
                try {
                    bufferedImage = ImageIO.read(Enimy.class.getResourceAsStream("slimeRunR.png"));
                    int AmountSprites = Enimy.getAmountSpritesOfEnimyAction(i);
                    for(int j=0;j<AmountSprites;j++) {
                        animationImagesEnimy[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage((AmountSprites-1-j)*16, 0, 16, 24), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }           
            }
            if(i==Enimy.DEATH_R){
                try {
                    bufferedImage = ImageIO.read(Enimy.class.getResourceAsStream("slimeDeathR.png"));
                    int AmountSprites = Enimy.getAmountSpritesOfEnimyAction(i);
                    for(int j=0;j<AmountSprites;j++) {
                        animationImagesEnimy[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage((AmountSprites-1-j)*16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }    
            }
            if(i==Enimy.HIT_R){
                try {
                    bufferedImage = ImageIO.read(Enimy.class.getResourceAsStream("slimeHitR.png"));
                    int AmountSprites = Enimy.getAmountSpritesOfEnimyAction(i);
                    for(int j=0;j<AmountSprites;j++) {
                        animationImagesEnimy[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage((AmountSprites-1-j)*16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }   
            }
            if(i==Enimy.IDLE_L){
                try {
                    bufferedImage = ImageIO.read(Enimy.class.getResourceAsStream("slimeIdleL.png"));
                    int AmountSprites = Enimy.getAmountSpritesOfEnimyAction(i);
                    for(int j=0;j<AmountSprites;j++) {
                        animationImagesEnimy[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage(j*16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            if(i==Enimy.RUN_L){
                try {
                    bufferedImage = ImageIO.read(Enimy.class.getResourceAsStream("slimeRunL.png"));
                    int AmountSprites = Enimy.getAmountSpritesOfEnimyAction(i);
                    for(int j=0;j<AmountSprites;j++) {
                        animationImagesEnimy[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage(j*16, 0, 16, 24), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }           
            }
            if(i==Enimy.DEATH_L){
                try {
                    bufferedImage = ImageIO.read(Enimy.class.getResourceAsStream("slimeDeathL.png"));
                    int AmountSprites = Enimy.getAmountSpritesOfEnimyAction(i);
                    for(int j=0;j<AmountSprites;j++) {
                        animationImagesEnimy[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage(j*16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }    
            }
            if(i==Enimy.HIT_L){
                try {
                    bufferedImage = ImageIO.read(Enimy.class.getResourceAsStream("slimeHitL.png"));
                    int AmountSprites = Enimy.getAmountSpritesOfEnimyAction(i);
                    for(int j=0;j<AmountSprites;j++) {
                        animationImagesEnimy[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage(j*16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }   
            }
        }
    }
    private void loadAnimationsStrangDoor() {
        try {
            animationImageStrangeDoor =new Image(Player.class.getResourceAsStream("StrangeDoor.png"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private void loadAnimationsStone(){
        try {
            animationImageStone =new Image(Stone.class.getResourceAsStream("stone.png"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private void loadAnimationsCoins() {
        animationImagesCoin =  new Image[2][6];
        try {
            bufferedImage = ImageIO.read(Coin.class.getResourceAsStream("coin.png"));
            for(int i = 0 ; i < 6 ; i++) {
                try {
                    animationImagesCoin[0][i]=SwingFXUtils.toFXImage(bufferedImage.getSubimage(i*8, 0, 8, 8), null);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            bufferedImage = ImageIO.read(Coin.class.getResourceAsStream("coin_pickup.png"));
            for(int i = 0 ; i < 6 ; i++) {
                try {
                    animationImagesCoin[1][i]=SwingFXUtils.toFXImage(bufferedImage.getSubimage(i*8, 0, 8, 16), null);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    private void loadAnimationsDoor() {
        animationImagesDoor = new Image[2][15];
        for(int i=0;i<2;i++){
            for(int j=0;j<Door.getAmountSpritesOfDoor(i);j++){
                if(i == Door.CLOSED){
                    try {
                        bufferedImage = ImageIO.read(Door.class.getResourceAsStream("door_closed.png"));
                        animationImagesDoor[i][j] =  SwingFXUtils.toFXImage(bufferedImage.getSubimage(j*16, 0, 16, 48), null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    try {
                        bufferedImage = ImageIO.read(Door.class.getResourceAsStream("door_openning.png"));
                        animationImagesDoor[i][j] =  SwingFXUtils.toFXImage(bufferedImage.getSubimage(j*16, 0, 16, 48), null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } 
    }
    private void loadAnimationsButton() {
        animationImagesButton =  new Image[2];
        for(int i = 0 ; i < 2 ; i++) {
            try {
                animationImagesButton[i]=new Image(Button.class.getResourceAsStream("button"+i+".png"));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    protected void loadAnimationsPlayer() {//load 1 bộ ảnh để tạo mảng 2 chiều để tạo animation
        animationImagesPlayer = new Image[16][8];
        for(int i = 0 ; i < 16 ; i++){
            if (i == Player.RUN_L) {
                try {
                    bufferedImage = ImageIO.read(Player.class.getResourceAsStream("HeroRunL.png"));
                    int AmountSprites = Player.getAmountSpritesOfPlayerAction(i);
                    for(int j=0;j<AmountSprites;j++) {
                        animationImagesPlayer[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage((AmountSprites-1-j)*16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            if (i == Player.RUN_R) {
                try {
                    bufferedImage = ImageIO.read(Player.class.getResourceAsStream("HeroRunR.png"));
                    for(int j=0;j<Player.getAmountSpritesOfPlayerAction(i);j++) {
                        animationImagesPlayer[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage(j*16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            if (i == Player.IDLE_L) {
                try {
                    bufferedImage = ImageIO.read(Player.class.getResourceAsStream("HeroIdleL.png"));
                    int AmountSprites = Player.getAmountSpritesOfPlayerAction(i);
                    for(int j=0;j<AmountSprites;j++) {
                        animationImagesPlayer[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage((AmountSprites-1-j)*16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            if (i == Player.IDLE_R) {
                try {
                    bufferedImage = ImageIO.read(Player.class.getResourceAsStream("HeroIdleR.png"));
                    for(int j=0;j<Player.getAmountSpritesOfPlayerAction(i);j++) {
                        animationImagesPlayer[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage(j*16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            if (i == Player.ATTACK1_L) {
                try {
                    bufferedImage = ImageIO.read(Player.class.getResourceAsStream("HeroAttack1L.png"));
                    int AmountSprites = Player.getAmountSpritesOfPlayerAction(i);
                    for(int j=0;j<AmountSprites;j++) {
                        animationImagesPlayer[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage((AmountSprites-1-j)*16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            if (i == Player.ATTACK1_R) {
                try {
                    bufferedImage = ImageIO.read(Player.class.getResourceAsStream("HeroAttack1R.png"));
                    for(int j=0;j<Player.getAmountSpritesOfPlayerAction(i);j++) {
                        animationImagesPlayer[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage(j*16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            if (i == Player.PUSH_L) {
                try {
                    bufferedImage = ImageIO.read(Player.class.getResourceAsStream("HeroPushingL.png"));
                    int AmountSprites = Player.getAmountSpritesOfPlayerAction(i);
                    for(int j=0;j<AmountSprites;j++) {
                        animationImagesPlayer[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage((AmountSprites-1-j)*16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            if (i == Player.PUSH_R) {
                try {
                    bufferedImage = ImageIO.read(Player.class.getResourceAsStream("HeroPushingR.png"));
                    for(int j=0;j<Player.getAmountSpritesOfPlayerAction(i);j++) {
                        animationImagesPlayer[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage(j*16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            if (i == Player.JUMPUP_L) {
                try {
                    bufferedImage = ImageIO.read(Player.class.getResourceAsStream("HeroJumpUpL.png"));
                    int AmountSprites = Player.getAmountSpritesOfPlayerAction(i);
                    for(int j=0;j<AmountSprites;j++) {
                        animationImagesPlayer[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage((AmountSprites-1-j)*16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            if (i == Player.JUMPUP_R) {
                try {
                    bufferedImage = ImageIO.read(Player.class.getResourceAsStream("HeroJumpUpR.png"));
                    for(int j=0;j<Player.getAmountSpritesOfPlayerAction(i);j++) {
                        animationImagesPlayer[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage(j*16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            if (i == Player.JUMPDOWN_L) {
                try {
                    bufferedImage = ImageIO.read(Player.class.getResourceAsStream("HeroJumpDownL.png"));
                    int AmountSprites = Player.getAmountSpritesOfPlayerAction(i);
                    for(int j=0;j<AmountSprites;j++) {
                        animationImagesPlayer[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage((AmountSprites-1-j)*16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            if (i == Player.JUMPDOWN_R) {
                try {
                    bufferedImage = ImageIO.read(Player.class.getResourceAsStream("HeroJumpDownR.png"));
                    for(int j=0;j<Player.getAmountSpritesOfPlayerAction(i);j++) {
                        animationImagesPlayer[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage(j*16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            if (i == Player.ATTACK2_L) {
                try {
                    bufferedImage = ImageIO.read(Player.class.getResourceAsStream("HeroAttack2L.png"));
                    int AmountSprites = Player.getAmountSpritesOfPlayerAction(i);
                    for(int j=0;j<AmountSprites;j++) {
                        animationImagesPlayer[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage((AmountSprites-1-j)*32, 0, 32, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            if (i == Player.ATTACK2_R) {
                try {
                    bufferedImage = ImageIO.read(Player.class.getResourceAsStream("HeroAttack2R.png"));
                    for(int j=0;j<Player.getAmountSpritesOfPlayerAction(i);j++) {
                        animationImagesPlayer[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage(j*32, 0, 32, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            if (i ==Player. DEATH_L) {
                try {
                    bufferedImage = ImageIO.read(Player.class.getResourceAsStream("HeroDeathL.png"));
                    int AmountSprites = Player.getAmountSpritesOfPlayerAction(i);
                    for(int j=0;j<AmountSprites;j++) {
                        animationImagesPlayer[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage((AmountSprites-1-j)*16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
            if (i == Player.DEATH_R) {
                try {
                    bufferedImage = ImageIO.read(Player.class.getResourceAsStream("HeroDeathR.png"));
                    for(int j=0;j<Player.getAmountSpritesOfPlayerAction(i);j++) {
                        animationImagesPlayer[i][j]= SwingFXUtils.toFXImage(bufferedImage.getSubimage(j*16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage()+"["+i+"]");
                }
            }
        }
    } 
    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
    public ArrayList<Coin> getCoins() {
        return coins;
    }
    public void setCoins(ArrayList<Coin> coins) {
        this.coins = coins;
    }
    public int[][] getMapData() {
        return mapData;
    }
    public void setMapData(int[][] mapData) {
        this.mapData = mapData;
    }
    public GraphicsContext getGc() {
        return gc;
    }
    public void setGc(GraphicsContext gc) {
        this.gc = gc;
    }
    public ArrayList<Stone> getStones() {
        return stones;
    }
    public void setStones(ArrayList<Stone> stones) {
        this.stones = stones;
    }
    public ArrayList<Button> getButtons() {
        return buttons;
    }
    public void setButtons(ArrayList<Button> buttons) {
        this.buttons = buttons;
    }
    public MakeMainScene getMakeMainScene() {
        return makeMainScene;
    }
    public void setMakeMainScene(MakeMainScene makeMainScene) {
        this.makeMainScene = makeMainScene;
    }
    public Door getDoor() {
        return door;
    }
    public void setDoor(Door door) {
        this.door = door;
    }
    public int getLevelValue() {
        return levelValue;
    }
    public void setLevelValue(int levelValue) {
        this.levelValue = levelValue;
    }
    public StrangeDoor getStrangeDoor() {
        return strangeDoor;
    }
    public void setStrangeDoor(StrangeDoor strangeDoor) {
        this.strangeDoor = strangeDoor;
    }
    public ArrayList<Enimy> getEnimies() {
        return enimies;
    }
    public void setEnimies(ArrayList<Enimy> enimies) {
        this.enimies = enimies;
    }
    
}

