import java.awt.Rectangle;


public class Enemy{

	Player p=StartingClass.player1;
    private boolean isDead=false;
    private int maxHealth,currentHealth,power,speedX,speedY=0,centerX,centerY;
    private Background bg=StartingClass.getBg1();
    public Rectangle bottom=new Rectangle(0,0,0,0);
    public Rectangle top=new Rectangle(0,0,0,0);
    public void update(){
        speedX=bg.getSpeedX()*5;
        centerX+=speedX;
        centerY+=speedY;
        bottom.setBounds(centerX-48,centerY-42,72,30);
        top.setBounds(centerX-40,centerY-48,56,7);
        if(bottom.intersects(Player.zone)||top.intersects(Player.zone)){checkCollision();}
        if(isDead)speedY=6;
    }
    private void checkCollision(){
        if(top.intersects(Player.bottom)){die();}
    	else if(bottom.intersects(Player.top)||bottom.intersects(Player.bottom)||bottom.intersects(Player.left)||bottom.intersects(Player.right)){
            System.out.println("player death");
            StartingClass.player1.die();}
    }

    public void die(){
        System.out.println("enemy death");
        isDead=true;
        int jumpspeed=p.getJumpspeed();
        p.setJumped(false);
        p.setJumpspeed(-12);
        p.jump();
        p.setJumpspeed(jumpspeed);
    }
    public void attack(){}
    public int getMaxHealth(){return maxHealth;}
    public int getCurrentHealth(){return currentHealth;}
    public int getPower(){return power;}
    public int getSpeedX(){return speedX;}
    public int getCenterX(){return centerX;}
    public int getCenterY(){return centerY;}
    public Background getBg(){return bg;}
    public void setMaxHealth(int maxHealth){this.maxHealth=maxHealth;}
    public void setCurrentHealth(int currentHealth){this.currentHealth=currentHealth;}
    public void setPower(int power){this.power=power;}
    public void setSpeedX(int speedX){this.speedX=speedX;}
    public void setCenterX(int centerX){this.centerX=centerX;}
    public void setCenterY(int centerY){this.centerY=centerY;}
    public void setBg(Background bg){this.bg=bg;}
    public boolean isDead(){return isDead;}
    public void setDead(boolean isDead){this.isDead=isDead;}
}