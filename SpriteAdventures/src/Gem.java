
import java.awt.Rectangle;

public class Gem{
	
    private Background bg=StartingClass.getBg1();
	public int centerX,centerY,speedX;
	public static Rectangle gemRect=new Rectangle();
	
	public Gem(int centerX,int centerY){
        setCenterX(centerX);
        setCenterY(centerY);
    }
    public void update(){
        speedX=bg.getSpeedX()*5;
        centerX+=speedX;
    	gemRect.setBounds(centerX+20,centerY+23,28,20);
    	if(Gem.gemRect.intersects(Player.zone))checkGem();
    }
	public void checkGem(){
		if(Player.top.intersects(gemRect)||Player.bottom.intersects(gemRect)||Player.left.intersects(gemRect)||Player.right.intersects(gemRect)){
			System.out.println("Winner");
			
			StartingClass.win();
		}
	}
    public void setCenterX(int centerX){this.centerX=centerX;}
    public void setCenterY(int centerY){this.centerY=centerY;}
    public int getCenterX(){return centerX;}
	public int getCenterY(){return centerY;}
}