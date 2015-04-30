
import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import framework.Animation;

public class StartingClass extends Applet implements Runnable,KeyListener{

	enum GameState{Running,Dead}
	GameState state=GameState.Running;
    public static Player player1;
    private Gem gem;
    private Poker poker1;
    private flyFly fly1,fly2,fly3,fly4,fly5,fly6,fly7;
    private Image gemstone,image,gameover,winImage,
    currentSprite,walkingCurrentSprite,
    character,character2,characterDown,characterJumped,characterDead,background,
    flyFly,flyFly2,flyDead,
    p1_walk01,p1_walk02,p1_walk03,p1_walk04,p1_walk05,p1_walk06,p1_walk07,p1_walk08,p1_walk09,p1_walk10,p1_walk11,
    pokerMad;
    public boolean enemyUp=true,enemyDown=false;
    public int centerY=380,tempCenterY;
    public static Image grassBot,xbox,grassMid,coin,fence,dirt,grassRight,grassCliffLeft,grassCliffRight,grassBridge,grassLeft,fenceBroken,water,grass;
    private Graphics second;
    private URL base;
    private static Background bg1,bg2;
    private Animation anim,fanim,walkanim;
    private ArrayList<Tile>tilearray=new ArrayList<Tile>();
    private static Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
    private static final int SCREEN_X=(int)screen.getWidth()-10,SCREEN_Y=480;
    private Thread thread=new Thread(this);
    private static boolean win = false;
    
    public void init(){
        setSize(SCREEN_X,SCREEN_Y);
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);
        Frame frame=(Frame)this.getParent().getParent();
        frame.setTitle("Q-Bot Alpha");
        try{base=getDocumentBase();}catch(Exception e){e.printStackTrace();}
        character=getImage(base,"images/p1_front.png");
        character2=getImage(base,"images/p1_stand.png");
        characterDown=getImage(base,"images/p1_duck.png");
        characterJumped=getImage(base,"images/p1_jump.png");
        characterDead=getImage(base,"images/p1_dead.png");
        flyFly=getImage(base,"images/flyFly1.png");
        flyFly2=getImage(base,"images/flyFly2.png");
        flyDead=getImage(base,"images/flyDead.png");
        background=getImage(base,"images/background.png");
        xbox=getImage(base,"images/boxAlt.png");
        fence=getImage(base,"images/fence.png");
        grassMid=getImage(base,"images/grassMid.png");
        grass=getImage(base,"images/grass.png");
        dirt=getImage(base,"images/dirt.png");
        grassLeft=getImage(base,"images/grassLeft.png");
        grassRight=getImage(base,"images/grassRight.png");
        grassCliffLeft=getImage(base,"images/grassCliffLeft.png");
        grassCliffRight=getImage(base,"images/grassCliffRight.png");
        grassBridge=getImage(base,"images/grassBridge.png");
        fenceBroken=getImage(base,"images/fenceBroken.png");
        coin=getImage(base,"images/hud_coins.png");
        water=getImage(base,"images/water.png");
        grassBot=getImage(base,"images/grassBot.png");
        gameover=getImage(base,"images/dead.png");
        winImage=getImage(base,"images/win.png.");
        gemstone=getImage(base,"images/gemBlue.png");
        p1_walk01=getImage(base,"images/p1_walk01.png");
        p1_walk02=getImage(base,"images/p1_walk02.png");
        p1_walk03=getImage(base,"images/p1_walk03.png");
        p1_walk04=getImage(base,"images/p1_walk04.png");
        p1_walk05=getImage(base,"images/p1_walk05.png");
        p1_walk06=getImage(base,"images/p1_walk06.png");
        p1_walk07=getImage(base,"images/p1_walk07.png");
        p1_walk08=getImage(base,"images/p1_walk08.png");
        p1_walk09=getImage(base,"images/p1_walk09.png");
        p1_walk10=getImage(base,"images/p1_walk10.png");
        p1_walk11=getImage(base,"images/p1_walk11.png");
        pokerMad=getImage(base,"images/pokerMad.png");
        anim=new Animation();
        anim.addFrame(character,1250);
        anim.addFrame(character2,50);
        anim.addFrame(character2,50);
        anim.addFrame(character,50);
        fanim=new Animation();
        fanim.addFrame(flyFly,400);
        fanim.addFrame(flyFly2,400);
        currentSprite=anim.getImage();
        
        walkanim=new Animation();
        walkanim.addFrame(p1_walk01, 300);
        walkanim.addFrame(p1_walk02, 300);
        walkanim.addFrame(p1_walk03, 300);
        walkanim.addFrame(p1_walk04, 300);
        walkanim.addFrame(p1_walk05, 300);
        walkanim.addFrame(p1_walk06, 300);
        walkanim.addFrame(p1_walk07, 300);
        walkanim.addFrame(p1_walk08, 300);
        walkanim.addFrame(p1_walk09, 300);
        walkanim.addFrame(p1_walk10, 300);
        walkanim.addFrame(p1_walk11, 300);
        walkingCurrentSprite=walkanim.getImage();
    }
    public static void win(){
    	win = true;
    	System.out.println(win);
    }
    public void start(){
        bg1=new Background(0,0);
        bg2=new Background(2160,0);
        player1=new Player();
        try{loadMap("tilemaps/map1.txt");}catch(IOException e){e.printStackTrace();}
        
        fly1=new flyFly(340,375);
        fly2=new flyFly(700,375);
        fly3=new flyFly(1300,375);
        fly4=new flyFly(1780,180);
        fly5=new flyFly(2000,180);
        fly6=new flyFly(2400,380);
        fly7=new flyFly(3350,centerY);
        poker1=new Poker(500,343);
        gem=new Gem(4500,380);
        Thread thread=new Thread(this);
        thread.start();
        
    }
    private void loadMap(String filename)throws IOException{
        ArrayList<String>lines=new ArrayList<String>();
        int width=0;
        int height;
        BufferedReader reader=new BufferedReader(new FileReader(filename));
        while(true){
            String line=reader.readLine();
            if(line==null){
                reader.close();break;}
            if(!line.startsWith("!")){
                lines.add(line);
                width=Math.max(width,line.length());}}
        height=lines.size();
        for(int j=0;j<height;j++){
            String line=lines.get(j);
            for(int i=0;i<width;i++)
                if(i<line.length()){
                    char ch=line.charAt(i);
                    Tile t=new Tile(i,j,ch);
                    tilearray.add(t);}}
    }
    public void stop(){}
    public void destroy(){}
    public void run(){
    	if(state==GameState.Running){
	        while(true){
	            player1.update();
	            if(player1.isJumped() && !player1.isDucked())currentSprite=characterJumped;
	            else if(!player1.isJumped()&&!player1.isDucked()&&!player1.isMovingRight())
	                currentSprite=anim.getImage();
	            else if(!player1.isJumped()&&!player1.isDucked()&&player1.isMovingRight())
	            	currentSprite=walkanim.getImage();
	            ArrayList projectiles= player1.getProjectiles();
	            for(int i=0;i<projectiles.size();i++){
	                Projectile p=(Projectile)projectiles.get(i);
	                if(p.isVisible())p.update();
	                else projectiles.remove(i);}
	            updateTiles();
	            poker1.update();
	            fly1.update();   
	            fly2.update();
	            fly3.update();
	            fly4.update();
	            fly5.update();
	            fly6.update();
	            fly7.update();
	            bg1.update();
	            bg2.update();
	            if(!fly7.isDead())
	            {
	            if(enemyUp && centerY<380)
	            {
	            	centerY++;
	            }
	            else if(enemyDown && centerY>200)
	            {
	            	centerY--;
	            }
	            if(centerY==380)
	            {
	            	enemyDown=true;
	            	enemyUp=false;
	            }
	            else if(centerY==200)
	            {
	            	enemyDown=false;
	            	enemyUp=true;
	            }
	            fly7.setCenterY(centerY);
	            }
	            anim.update(10);
	            fanim.update(50);
	            walkanim.update(100);
	            gem.update();
	            repaint();
	            try{Thread.sleep(17);}catch(InterruptedException e){e.printStackTrace();}
	           	if(player1.getCenterY()>500)state=GameState.Dead;}}
    }
    public void update(Graphics g){
        if(image==null){
            image=createImage(this.getWidth(),this.getHeight());
            second=image.getGraphics();}
        second.setColor(getBackground());
        second.fillRect(0,0,getWidth(),getHeight());
        second.setColor(getForeground());
        paint(second);
        g.drawImage(image,0,0,this);
    }
    public void paint(Graphics g){
    	if(state==GameState.Running){
	        g.drawImage(background,bg1.getBgX(),bg1.getBgY(),this);
	        g.drawImage(background,bg2.getBgX(),bg2.getBgY(),this);
	        paintTiles(g);
	        ArrayList projectiles= player1.getProjectiles();
	        for(Object projectile:projectiles){
	            Projectile p=(Projectile)projectile;
	            g.setColor(Color.yellow);
	            g.fillRect(p.getX(),p.getY(),10,5);}
	        if (!win)
	        {
	        	g.drawImage(gemstone,gem.getCenterX(),gem.getCenterY(),this);
	        }
            g.drawRect((int)player1.top.getX(),(int)player1.top.getY(),(int)player1.top.getWidth(),(int)player1.top.getHeight());
            g.drawRect((int)player1.bottom.getX(),(int)player1.bottom.getY(),(int)player1.bottom.getWidth(),(int)player1.bottom.getHeight());
            g.drawRect((int)player1.left.getX(),(int)player1.left.getY(),(int)player1.left.getWidth(),(int)player1.left.getHeight());
            g.drawRect((int)player1.right.getX(),(int)player1.right.getY(),(int)player1.right.getWidth(),(int)player1.right.getHeight());
            g.drawRect((int)player1.zone.getX(),(int)player1.zone.getY(),(int)player1.zone.getWidth(),(int)player1.zone.getHeight());
            g.drawRect((int)fly1.bottom.getX(),(int)fly1.bottom.getY(),(int)fly1.bottom.getWidth(),(int)fly1.bottom.getHeight());
            g.drawRect((int)fly2.bottom.getX(),(int)fly2.bottom.getY(),(int)fly2.bottom.getWidth(),(int)fly2.bottom.getHeight());
            g.drawRect((int)fly3.bottom.getX(),(int)fly3.bottom.getY(),(int)fly3.bottom.getWidth(),(int)fly3.bottom.getHeight());
            g.drawRect((int)fly4.bottom.getX(),(int)fly4.bottom.getY(),(int)fly4.bottom.getWidth(),(int)fly4.bottom.getHeight());
            g.drawRect((int)fly5.bottom.getX(),(int)fly5.bottom.getY(),(int)fly5.bottom.getWidth(),(int)fly5.bottom.getHeight());
            g.drawRect((int)fly6.bottom.getX(),(int)fly6.bottom.getY(),(int)fly6.bottom.getWidth(),(int)fly6.bottom.getHeight());
            g.drawRect((int)fly7.bottom.getX(),(int)fly7.bottom.getY(),(int)fly7.bottom.getWidth(),(int)fly7.bottom.getHeight());
            g.drawRect((int)fly1.top.getX(),(int)fly1.top.getY(),(int)fly1.top.getWidth(),(int)fly1.top.getHeight());
            g.drawRect((int)fly2.top.getX(),(int)fly2.top.getY(),(int)fly2.top.getWidth(),(int)fly2.top.getHeight());
            g.drawRect((int)fly3.top.getX(),(int)fly3.top.getY(),(int)fly3.top.getWidth(),(int)fly3.top.getHeight());
            g.drawRect((int)fly4.top.getX(),(int)fly4.top.getY(),(int)fly4.top.getWidth(),(int)fly4.top.getHeight());
            g.drawRect((int)fly5.top.getX(),(int)fly5.top.getY(),(int)fly5.top.getWidth(),(int)fly5.top.getHeight());
            g.drawRect((int)fly6.top.getX(),(int)fly6.top.getY(),(int)fly6.top.getWidth(),(int)fly6.top.getHeight());
            g.drawRect((int)fly7.top.getX(),(int)fly7.top.getY(),(int)fly7.top.getWidth(),(int)fly7.top.getHeight());
            //g.drawRect((int)Gem.gemRect.getX(),(int)Gem.gemRect.getY(),(int)Gem.gemRect.getWidth(),(int)Gem.gemRect.getHeight());//*/            
            updatePlayer(player1,g);
            updateEnemy(fly1,g);
            updateEnemy(fly2,g);
            updateEnemy(fly3,g);
            updateEnemy(fly4,g);
            updateEnemy(fly5,g);
            updateEnemy(fly6,g);
            updateEnemy(fly7,g);
            updatePokerEnemy(poker1,g);
            
            if(win){
            	g.drawImage(winImage,0,-55, this);//-309
            	System.out.println(SCREEN_X);
            	System.out.println(SCREEN_Y);
	        	//System.out.println("inside win statement");
	        	//g.setColor(Color.BLACK);
	    		//g.fillRect(0,0,SCREEN_X,SCREEN_Y);
	    		//g.setColor(Color.WHITE);
	    		//g.drawString("Winner",SCREEN_X/2,SCREEN_Y/2);
            }}
            else if((state==GameState.Dead||player1.isDead())&&!win)
        {
        	g.drawImage(gameover,0,-55, this);
        	System.out.println(player1.isDead());
        	System.out.println("inside lose statement");
        	
        	//g.setColor(Color.BLACK);
    		//g.fillRect(0,0,SCREEN_X,SCREEN_Y);
    		//g.setColor(Color.WHITE);
    		//g.drawString("Dead",SCREEN_X/2,SCREEN_Y/2);
    		}
        
    }
    private void updatePlayer(Player player,Graphics g){
        if(player.isDead())g.drawImage(characterDead,player.getCenterX()-61,player.getCenterY()-80,this);
        else
        	g.drawImage(currentSprite,player.getCenterX()-61,player.getCenterY()-80,this);
    }
    private void updateEnemy(flyFly enemy,Graphics g){
        if(enemy.isDead())g.drawImage(flyDead,enemy.getCenterX()-48,enemy.getCenterY()-48,this);
        else g.drawImage(fanim.getImage(),enemy.getCenterX()-48,enemy.getCenterY()-48,this);
    }
    private void updatePokerEnemy(Poker enemy,Graphics g){
    	g.drawImage(pokerMad,enemy.getCenterX()-48,enemy.getCenterY()-48,this);
    }
    public Poker getPoker1() {
		return poker1;
	}
	public void setPoker1(Poker poker1) {
		this.poker1 = poker1;
	}
	private void updateTiles(){
        for(Tile t:tilearray)t.update();
    }
    private void paintTiles(Graphics g){
        for(Tile t:tilearray)g.drawImage(t.getTileImage(),t.getTileX(),t.getTileY(),this);
    }
    public void keyPressed(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                player1.moveLeft();
                player1.setMovingLeft(true);break;
            case KeyEvent.VK_RIGHT:
                player1.moveRight();
                player1.setMovingRight(true);break;
            case KeyEvent.VK_SPACE:
                player1.jump();break;
            case KeyEvent.VK_CONTROL:
                if(!player1.isDucked()&&!player1.isJumped()){
                    player1.shoot();
                    player1.setReadyToFire(false);}break;
            case KeyEvent.VK_DOWN:
                currentSprite=characterDown;
                if(!player1.isJumped()){
                    player1.setDucked(true);
                    player1.setSpeedX(0);}break;
            //case KeyEvent.VK_ENTER:
            	//state=GameState.Running;
            	//Player.isDead=false;
            	//tilearray=new ArrayList<Tile>();
            	//start();
            	
            	
        }
        										
    }
    public void keyReleased(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_DOWN:
                currentSprite=anim.getImage();
                player1.setDucked(false);break;
            case KeyEvent.VK_LEFT:
                player1.stopLeft();break;
            case KeyEvent.VK_RIGHT:
                player1.stopRight();break;
            case KeyEvent.VK_CONTROL:
                player1.setReadyToFire(true);break;}
    }
    public void keyTyped(KeyEvent e){}
    public static Background getBg1(){return bg1;}
    public static Background getBg2(){return bg2;}
    public static Player getPlayer1(){return player1;}
}