package entities;

import javafx.scene.image.Image;


public abstract class Trap extends Enity {
    protected Image []animationImages;
    protected float xDangerHitbox;
    protected float yDangerHitbox;
    protected float widthDangerHitbox;
    protected float heightDangerHitbox;
    private int aniTick = 0;
    private int aniSpeed = 6;
    private int aniIndex = 0;
    protected  int amountSprites;
    // public Trap( float x, float y,Image[] animationImages,MapInteractionManager mapInteractionManager) {
    //     this.x = x;
    //     this.y = y;
    //     width = 128;
    //     height = 128;
    //     xDangerHitbox = x;
    //     yDangerHitbox = y;
    //     widthDangerHitbox = width;
    //     heightDangerHitbox = height;
    //     this.gc = mapInteractionManager.getGc();
    //     this.animationImages = animationImages;
    //     amountSprites = 8;
    // }
    @Override
    public void render() {
        gc.drawImage(animationImages[aniIndex], x, y, width, height); 
    }

    @Override
    public void update() {
        updateAnimationTick();
        updateDangerHitbox();
    }

    @Override
    protected void updateAnimationTick() {
        aniTick++;
		if (aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if (aniIndex >= amountSprites) {
				aniIndex = 0;
			}
		}
    }
    protected abstract void updateDangerHitbox();
    public float getxDangerHitbox() {
        return xDangerHitbox;
    }
    public void setxDangerHitbox(float xDangerHitbox) {
        this.xDangerHitbox = xDangerHitbox;
    }
    public float getyDangerHitbox() {
        return yDangerHitbox;
    }
    public void setyDangerHitbox(float yDangerHitbox) {
        this.yDangerHitbox = yDangerHitbox;
    }
    public float getWidthDangerHitbox() {
        return widthDangerHitbox;
    }
    public void setWidthDangerHitbox(float widthDangerHitbox) {
        this.widthDangerHitbox = widthDangerHitbox;
    }
    public float getHeightDangerHitbox() {
        return heightDangerHitbox;
    }
    public void setHeightDangerHitbox(float heightDangerHitbox) {
        this.heightDangerHitbox = heightDangerHitbox;
    }
    
    public int getAmountSprites() {
        return amountSprites;
    }

    public void setAmountSprites(int amountSprites) {
        this.amountSprites = amountSprites;
    }

    @Override
    protected void handleCollision() {
        
    }

    @Override
    protected void setAnimation() {
        
    }

    @Override
    protected void updatePos() {
        
    }
    
}
