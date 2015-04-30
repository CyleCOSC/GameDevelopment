
import java.awt.Rectangle;
import java.util.ArrayList;

public class Player{

    final int MOVESPEED=5;
    private int centerX=100,centerY=377,speedX=0,speedY=0,jumpspeed=-18;
    static boolean isDead=false;
    private boolean jumped=false;
    private boolean movingLeft=false;
    private boolean movingRight=false;
    private boolean ducked=false;
    private boolean readyToFire=true;
    public static Rectangle top=new Rectangle(0,0,0,0);
    public static Rectangle bottom=new Rectangle(0,0,0,0);
    public static Rectangle zone=new Rectangle(0,0,0,0);
    public static Rectangle left=new Rectangle(0,0,0,0);
    public static Rectangle right=new Rectangle(0,0,0,0);
    private Background bg1=StartingClass.getBg1();
    private Background bg2=StartingClass.getBg2();
    private ArrayList<Projectile>projectiles=new ArrayList<Projectile>();

    public void update(){
        if(speedX<0)centerX+=speedX;
        if(speedX<=0){
            bg1.setSpeedX(0);
            bg2.setSpeedX(0);}
        if(centerX<=200&&speedX>0)centerX+=speedX;
        if(speedX>0&&centerX>200){
            bg1.setSpeedX(-MOVESPEED/5);
            bg2.setSpeedX(-MOVESPEED/5);}
        centerY+=speedY;
        speedY+=1;
        if(ducked && speedY>5)
        {
        	jumped=true;
        }
        else if(speedY>3)jumped=true;
        if(centerX+speedX<=60)centerX=61;
        if(isDead){
            speedX=0;
            speedY=7;}
        if(ducked)
        {
        	top.setRect(centerX-51,centerY-60,44,5);
        }
        else
        {
        	top.setRect(centerX-51,centerY-79,44,5);
        }
        bottom.setRect(centerX-51,centerY+10,44,5);
        zone.setRect(centerX-100,centerY-100,150,150);
        left.setRect(centerX-60,centerY-55,5,50);
        right.setRect(centerX-3,centerY-55,5,50);
	}
    public void moveRight(){if(!ducked)speedX=MOVESPEED;
    	
    }
    public void moveLeft(){if(!ducked)speedX=-MOVESPEED;}
    public void stopRight(){
        setMovingRight(false);
        stop();
    }
    public void stopLeft(){
        setMovingLeft(false);
        stop();
    }
    private void stop(){
        if(!isMovingRight()&&!isMovingLeft())speedX=0;
        if(!isMovingRight()&&isMovingLeft())moveLeft();
        if(isMovingRight()&&!isMovingLeft())moveRight();
    }
    public void jump(){
        if(!jumped){
        	if(ducked)
        	{
        		speedY=jumpspeed+6;
        	}
        	else
        	{
        		speedY=jumpspeed;
        	}
            jumped=true;}
    }
    public void shoot(){
        if(readyToFire){
            Projectile p=new Projectile(centerX+50,centerY-25);
            projectiles.add(p);}
    }
    public int getCenterX(){return centerX;}
    public void setCenterX(int centerX){this.centerX=centerX;}
    public int getCenterY(){return centerY;}
    public void setCenterY(int centerY){this.centerY=centerY;}
    public int getSpeedX(){return speedX;}
    public void setSpeedX(int speedX){this.speedX=speedX;}
    public int getSpeedY(){return speedY;}
    public void setSpeedY(int speedY){this.speedY=speedY;}
    public boolean isJumped(){return jumped;}
    public void setJumped(boolean jumped){this.jumped=jumped;}
    public boolean isDucked(){return ducked;}
    public void setDucked(boolean ducked){this.ducked=ducked;}
    public boolean isMovingRight(){return movingRight;}
    public void setMovingRight(boolean movingRight){this.movingRight=movingRight;}
    public boolean isMovingLeft(){return movingLeft;}
    public void setMovingLeft(boolean movingLeft){this.movingLeft=movingLeft;}
    public static boolean isDead(){return isDead;}
    public void setDead(boolean isDead){this.isDead=isDead;}
    public ArrayList getProjectiles(){return projectiles;}
    public boolean isReadyToFire(){return readyToFire;}
    public void setReadyToFire(boolean readyToFire){this.readyToFire=readyToFire;}
    public int getJumpspeed(){return jumpspeed;}
    public void setJumpspeed(int jumpspeed){this.jumpspeed=jumpspeed;}
    public void die(){isDead=true;}
}