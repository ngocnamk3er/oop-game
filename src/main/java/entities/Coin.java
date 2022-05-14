package entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Coin extends Enity {
    private Image[][] animationImages;
    private GraphicsContext gc;
    private int aniTick=0;
    private int aniSpeed=5;
    private int aniIndex=0;
    private boolean pickedUp = false;
    private int coinAction;
    private final int IDLE = 0;
    private final int PICKEDUP = 1;
    private float width = 32;
    private float height = 32;
    private boolean disappear = false;
    public Coin(float x, float y, GraphicsContext gc, Image[][] animationImages) {
        super(x, y);
        this.gc = gc;
        this.animationImages = animationImages;
    }
    private void setAnimation(){
        int startAni = coinAction;
        if(pickedUp==false){
            coinAction = IDLE;
        }else{
            coinAction = PICKEDUP;
        }
        if(startAni != coinAction){
            aniIndex = 0;
        }
    }
    private void updateAnimationTick() {
		aniTick++;
		if (aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if (aniIndex >= 6) {
				aniIndex = 0;
                if(coinAction == PICKEDUP){
                    disappear = true;
                }
			}

		}
    }
    public void update(){
        if(!disappear){
            setAnimation();
            updateAnimationTick();
        }
    }
    public void render() {
        if(!disappear){
            if(pickedUp==false){
                gc.drawImage(animationImages[0][aniIndex], x+16, y+16, width,height);
            }else{
                gc.drawImage(animationImages[1][aniIndex], x+16, y, width,height*2);
            }
        }
    }
    public boolean isPickedUp() {
        return pickedUp;
    }
    public void setPickedUp(boolean pickedUp) {
        this.pickedUp = pickedUp;
    }
    
}
