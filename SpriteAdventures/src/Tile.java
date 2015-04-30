
import java.awt.Image;
import java.awt.Rectangle;

public class Tile{

    private int tileX,tileY,speedX,type;
    public Image tileImage;
    private Rectangle r;
    private Background bg;
    private Player player;

    public Tile(int x,int y,char typeInt){
        tileX=x*40;
        tileY=y*40;
        type=typeInt;
        r=new Rectangle();
        switch(type){
            case '1':tileImage=StartingClass.fence;break;
            case '2':tileImage=StartingClass.grassBridge;break;
            case '3':tileImage=StartingClass.fenceBroken;break;
            case '4':tileImage=StartingClass.grassLeft;break;
            case '5':tileImage=StartingClass.dirt;break;
            case '6':tileImage=StartingClass.grassRight;break;
            case '7':tileImage=StartingClass.water;break;
            case '8':tileImage=StartingClass.grassMid;break;
            case '9':tileImage=StartingClass.grassBot;break;
            case 'c':tileImage=StartingClass.coin;break;
            case 'x':tileImage=StartingClass.xbox;break;
            default:type='0';break;}
    }
    public void update(){
        player=StartingClass.getPlayer1();
        bg=StartingClass.getBg1();
        speedX=bg.getSpeedX()*5;
        tileX+=speedX;
        r.setBounds(tileX,tileY,40,40);
        if(r.intersects(Player.zone)&&type!='0'&&type!='1'&&type!='3'&&type!='7'&&!Player.isDead()){
            checkVerticalCollision(Player.top,Player.bottom);
            checkHorizontalCollision(Player.left,Player.right);}
    }
    public void checkVerticalCollision(Rectangle head,Rectangle feet){
        if(feet.intersects(r)&&type!='k'){
            player.setJumped(false);
            player.setSpeedY(0);
            player.setCenterY(tileY-12);}
        else if(head.intersects(r)&&type!='2'&&type!='k'){
            player.setJumped(false);
            player.setSpeedY(18);}
    }
    public void checkHorizontalCollision(Rectangle left_side,Rectangle right_side){
            if(left_side.intersects(r)&&type!='2'&&type!='k'){
                player.setCenterX(tileX+100);
                player.setSpeedX(0);}
            else if(right_side.intersects(r)&&type!='2'&&type!='k')
            {
                player.setCenterX(tileX-3);
                player.setSpeedX(0);}
    }
    public int getTileX(){return tileX;}
    public void setTileX(int tileX){this.tileX=tileX;}
    public int getTileY(){return tileY;}
    public void setTileY(int tileY){this.tileY=tileY;}
    public Image getTileImage(){return tileImage;}
    public void setTileImage(Image tileImage){this.tileImage=tileImage;}
}