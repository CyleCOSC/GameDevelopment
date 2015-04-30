import java.awt.Rectangle;


public class pokerEnemy{

    private boolean isDead=false;
    private int maxHealth,currentHealth,power,speedX,speedY=0;
	private static int centerX;
	private static int centerY;
    private Background bg=StartingClass.getBg1();
    public Rectangle bottom=new Rectangle(0,0,0,0);

    public void update(){
        speedX=bg.getSpeedX()*5;
        centerX+=speedX;
        centerY+=speedY;
        bottom.setBounds(centerX-48,centerY-48,47,142);
        if(bottom.intersects(Player.zone)){checkCollision();}
        if(isDead)speedY=6;
    }
    private void checkCollision(){
    	if(bottom.intersects(Player.top)||bottom.intersects(Player.bottom)||bottom.intersects(Player.left)||bottom.intersects(Player.right)){
            System.out.println("player death");
            StartingClass.player1.die();}
    }
    public int getSpeedY() {
		return speedY;
	}
	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}
	public Rectangle getBottom() {
		return bottom;
	}
	public void setBottom(Rectangle bottom) {
		this.bottom = bottom;
	}
	public void die(){
        System.out.println("enemy death");
        isDead=true;
        Player p=StartingClass.player1;
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
    public static int getCenterX(){return centerX;}
    public static int getCenterY(){return centerY;}
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