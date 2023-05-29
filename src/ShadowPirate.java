import bagel.*;
import java.util.*;
import bagel.util.Rectangle;
import bagel.util.Point;
import bagel.util.Colour;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.awt.Graphics;

/**
 * Skeleton Code for SWEN20003 Project 1, Semester 1, 2022
 *
 * Please filling your name below
 * @author
 */
public class ShadowPirate extends AbstractGame {
    private final static int WINDOW_WIDTH = 1024;
    private final static int WINDOW_HEIGHT = 768;
    private final static String GAME_TITLE = "ShadowPirate";
    private Image BACKGROUND_IMAGE = new Image("res/background0.png");
    private Image sailorLeft;
    private Image block, bomb, elixir, potion, sword, treasure;
    private Image sailorRight;
    private Image[] pirate=new Image[100];
    private Image projectile;
    private double[] pirate_x=new double[100];
    private double[] pirate_y=new double[100];
    private static int picked = 0;
    private static int elixirCount = 0;
    private static boolean showElixir = false;
    private static int potionCount = 0;
    private static boolean showPotion = false;
    private static int swordCount = 0;
    private static boolean showSword = false;
    private double blackbeard_x;
    private double blackbeard_y;
    private int[] pirateHealth=new int[100];
    private String[] pirateFacing = new String[100];
    private boolean[] pCollision = new boolean[100];
    private boolean[] pirateOver = new boolean[100];
    private boolean[] invincible = new boolean[100];
    private boolean[] flag = new boolean[100];
    private boolean LevelZero = false;
    private boolean LevelOneStart = false;
    private boolean congrats = false;
    private int counter=0;
    private static int pirateNo=0;
    private static int pirates=0;
    private static boolean b = false;
    private static boolean levelOnePress = false;
    private static double x;
    private static double y;
    private static double sailor_x;
    private static double sailor_y;
    private static double[] pirates_x = new double[100];
    private static double[] pirates_y = new double[100];
    private static double[] angle=new double[100];
    private static double[] cooldown=new double[100];
    private String facing = "right";
    private static boolean sailorImage = false;
    private static boolean pirateImage = false;
    private static boolean blackbeardImage = false;
    private static boolean gameOver  = false;
    private static boolean levelZeroComplete = false;
    private int maxHealth = 100;
    private int health = maxHealth;
    private int damagePoints = 25;
    private static boolean collision=false;

    public static int getPirateNo() {
        return pirateNo;
    }
    public static void setPirateNo(int pirate) {
        pirateNo = pirate;
    }
    public static void setPirates(int pirate) {
        pirates=pirate;
    }
    public static int getPirates() {
        return pirates;
    }
    public static boolean getPirateImage() {
        return pirateImage;
    }
    public static void setPirateImage(boolean s) {
        pirateImage = s;
    }
    public static boolean getBlackbeardImage() {
        return blackbeardImage;
    }
    public static void setBlackbeardImage(boolean s) {
        blackbeardImage = s;
    }
    public static boolean getSailorImage() {
        return sailorImage;
    }
    public static void setSailorImage(boolean s) {
        sailorImage = s;
    }
    public String getFacing() {
        return facing;
    }
    public void setFacing(String facing) {
        this.facing = facing;
    }
    public static double getX() {
        return x;
    }
    public static void setX(int X) {
        x=X;
    }
    public static double getY() {
        return y;
    }
    public static void setY(int Y) {
        y=Y;
    }
    public static boolean getLevelOnePress() {
        return levelOnePress;
    }
    public static void setLevelOnePress(boolean press) {
        levelOnePress=press;
    }
    public static boolean getPress(){
        return b;
    }
    public static void setPress(boolean flag){
        b = flag;
    }
    public void gameOver() {
        BACKGROUND_IMAGE.draw(Window.getWidth()/2.0, Window.getHeight()/2.0);
        Font f = new Font("res/wheaton.otf",55);
        String s1 = "game over";
        double width = f.getWidth(s1);
        f.drawString(s1, Window.getWidth()/2 - width/2,402);

    }
    public static void setGameOver(boolean b) {
        gameOver = b;
    }
    public static boolean getGameOver() {
        return gameOver;
    }
    public static void setLevelZeroComplete(boolean b) {
        levelZeroComplete = b;
    }
    public static boolean getLevelZeroComplete() {
        return levelZeroComplete;
    }
    public void levelZeroComplete() {
        //BACKGROUND_IMAGE.draw(Window.getWidth()/2.0, Window.getHeight()/2.0);
        Font f = new Font("res/wheaton.otf",55);
        String s1 = "level complete!";
        double width = f.getWidth(s1);
        f.drawString(s1, Window.getWidth()/2 - width/2,402);
    }
    public void congrats() {
        //BACKGROUND_IMAGE.draw(Window.getWidth()/2.0, Window.getHeight()/2.0);
        Font f = new Font("res/wheaton.otf",55);
        String s1 = "congratulations!";
        double width = f.getWidth(s1);
        f.drawString(s1, Window.getWidth()/2 - width/2,402);
    }
    public void levelOneStart() {
        Font f = new Font("res/wheaton.otf", 55);
        String s1 = "press space to start";
        String s3 = "press s to attack";
        String s2 = "find the treasure";
        double width = f.getWidth(s1);
        f.drawString(s1, 200, 402);
        width = f.getWidth(s3);
        f.drawString(s3, 200, 472);
        width = f.getWidth(s2);
        f.drawString(s2, 200, 542);
    }
    public void renderHealth(int health, double x, double y, int size) {
        Font f = new Font("res/wheaton.otf",size);
        String s1 = health + "%";
        double width = f.getWidth(s1);
        DrawOptions d=new DrawOptions();
        Colour c;
        if(health>=35 && health<65 ) c = new Colour(0.9,0.6,0);
        else if(health<35) c = new Colour(1,0,0);
        else c = new Colour(0,0.8,0.2);
        d.setBlendColour(c);
        f.drawString(s1, x, y, d);
    }
    public void renderProjectile(double dist_x, double dist_y, double speed, double angle) {
        projectile = new Image("res/pirate/pirateProjectile.png");
        DrawOptions d=new DrawOptions();
        d.setRotation(angle);
        projectile.draw(dist_x,dist_y,d);
    }
    public static boolean getCollision() {
        return collision;
    }
    public static void setCollision(boolean c) {
        collision=c;
    }
    public int getDamagePoints() {
        return damagePoints;
    }
    public int getHealth() {
        return health;
    }
    public boolean checkCollisionSailor(double x, double y) {
        Point player = new Point(x, y);
        Rectangle r = new Rectangle(player,20,40);
        Point sailor = new Point(getX(),getY());
        Rectangle p = new Rectangle(sailor,20,40);
        if (p.intersects(r)) {
            return true;
        }
        return false;
    }
    public boolean checkCollision(String item,double x, double y, String obj, String filename) {
        Point player = new Point(x, y);
        Rectangle r = new Rectangle(player,40,40);
        Path pathToFile = Paths.get(filename);
        try (BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.US_ASCII)) {
            String line = br.readLine();
            while (line != null) {
                String[] a = line.split(",");
                if (a[0].equals(obj)) {
                    Point block = new Point(Integer.parseInt(a[1]), Integer.parseInt(a[2]));
                    Rectangle p = new Rectangle(block,40,40);
                    if (p.intersects(r)) {
                        return true;
                    }
                }
                line = br.readLine();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        if(item.equals("Sailor")) {
            for(int i=0;i<100;i++) {
                if(pirates_x[i]!=-1000.0 && pirates_y[i]!=-1000.0) {
                    if(Math.abs(x-pirates_x[i])<5 && Math.abs(y-pirates_y[i])<=5) return true;
                }
            }
        }
        return false;
    }
    public void setHealth(int health) {
        this.health = health;
    }

    public ShadowPirate() {
        super(WINDOW_WIDTH, WINDOW_HEIGHT, GAME_TITLE);
        sailorLeft = new Image("res/sailor/sailorLeft.png");
        block = new Image("res/block.png");
        bomb = new Image("res/bomb.png");
        elixir = new Image("res/items/elixir.png");
        potion = new Image("res/items/potion.png");
        sword = new Image("res/items/sword.png");
        treasure = new Image("res/treasure.png");
        sailorRight = new Image("res/sailor/sailorRight.png");
        for(int i=0;i<100;i++) {
            pirates_x[i]=-1000.0;
            pirates_y[i]=-1000.0;
            angle[i]=-100.0;
        }
    }


    /**
     * The entry point for the program.
     */
    public static void main(String[] args) {
        ShadowPirate game = new ShadowPirate();
        game.run();
    }

    /**
     * Method used to read file and create objects
     */

    private void readCSV(String fileName){
        Path pathToFile = Paths.get(fileName);
        try (BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.US_ASCII)) {
            String line = br.readLine();
            while (line != null) {
                String[] a = line.split(",");
                if(a[0].equals("Block")){
                    if(fileName.equals("res/level0.csv")) {
                        block.draw(Integer.parseInt(a[1]),Integer.parseInt(a[2]));
                    }
                    else {
                        bomb.draw(Integer.parseInt(a[1]),Integer.parseInt(a[2]));
                    }
                }
                else if(a[0].equals("Elixir")){
                    if(showElixir==false) elixir.draw(Integer.parseInt(a[1]),Integer.parseInt(a[2]));
                }
                else if(a[0].equals("Potion")){
                    if(showPotion==false) potion.draw(Integer.parseInt(a[1]),Integer.parseInt(a[2]));
                }
                else if(a[0].equals("Sword")){
                    if(showSword==false) sword.draw(Integer.parseInt(a[1]),Integer.parseInt(a[2]));
                }
                else if(a[0].equals("Treasure")){
                    treasure.draw(Integer.parseInt(a[1]),Integer.parseInt(a[2]));
                }
                else if(a[0].equals("Pirate")) {
                    if(getPirateImage()==false) {
                        int pirateNo=getPirateNo();
                        pirate[pirateNo] = new Image("res/pirate/pirateRight.png");
                        pirate[pirateNo].draw(Double.parseDouble(a[1]), Double.parseDouble(a[2]));
                        pirate_x[pirateNo] = Double.parseDouble(a[1]);
                        pirate_y[pirateNo] = Double.parseDouble(a[2]);
                        invincible[pirateNo] = false;
                        pirateFacing[pirateNo] = "right";
                        pirateHealth[pirateNo]=45;
                        pirateOver[pirateNo]=false;
                        pCollision[pirateNo]=false;
                        flag[pirateNo]=false;
                        setPirateNo(pirateNo + 1);
                        setPirates(pirateNo);
                    }
                }
                else if(a[0].equals("Blackbeard")) {
                    //if(getBlackbeardImage()==false) {
                        Image blackbeardRight = new Image("res/blackbeard/blackbeardRight.png");
                        blackbeardRight.draw(Double.parseDouble(a[1]), Double.parseDouble(a[2]));
                        blackbeard_x=Double.parseDouble(a[1]);
                        blackbeard_y=Double.parseDouble(a[2]);
                        ShadowPirate.setBlackbeardImage(true);
                    //}
                }
                else if(a[0].equals("Sailor")) {
                    if(getSailorImage()==false) {
                        sailorRight.draw(Integer.parseInt(a[1]),Integer.parseInt(a[2]));
                        ShadowPirate.setX(Integer.parseInt(a[1]));
                        ShadowPirate.setY(Integer.parseInt(a[2]));
                        ShadowPirate.setSailorImage(true);
                    }
                }

                line = br.readLine();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }


    }

    /**
     * Performs a state update.
     * allows the game to exit when the escape key is pressed.
     */
    @Override
    public void update(Input input) {

        //BACKGROUND_IMAGE.draw(Window.getWidth()/2.0, Window.getHeight()/2.0);
        if (getGameOver() == false && getLevelZeroComplete() == false) {
            if (input.wasPressed(Keys.SPACE)) {
                setPress(true);
            }
            if (input.wasPressed(Keys.ESCAPE)) {
                Window.close();
            }
            if (getPress() == false) {
                Font f = new Font("res/wheaton.otf", 55);
                String s1 = "press space to start";
                String s3 = "press s to attack";
                String s2 = "use arrow keys to find ladder";
                double width = f.getWidth(s1);
                f.drawString(s1, 0, 402);
                width = f.getWidth(s3);
                f.drawString(s3, 0, 472);
                width = f.getWidth(s2);
                f.drawString(s2, 0, 542);

            }
            else {
                BACKGROUND_IMAGE.draw(Window.getWidth()/2.0, Window.getHeight()/2.0);
                setPirateNo(0);
                readCSV("res/level0.csv");
                setPirateImage(true);
                int pirates=getPirates();
                for(int i=0;i<pirates;i++) {
                    if(angle[i]!=-100.0) {
                        double speed=0.2;
                        pirates_x[i]+=speed*Math.cos(angle[i]);
                        pirates_y[i]+=speed*Math.sin(angle[i]);
                        renderProjectile(pirates_x[i],pirates_y[i],speed,angle[i]);
                        cooldown[i]+=1;
                        if((cooldown[i]*1000)/60>6000) {
                            cooldown[i]=0;
                            angle[i]=-100.0;
                            pirates_x[i]=-1000.0;
                            pirates_y[i]=-1000.0;
                        }
                    }
                    if(Math.abs(x-pirate_x[i])<=100 && Math.abs(y-pirate_y[i])<=100) {
                        if(angle[i]==-100.0) {
                            pirates_x[i]=pirate_x[i];
                            pirates_y[i]=pirate_y[i];
                            sailor_x=x;
                            sailor_y=y;
                            double base = Math.abs(pirate_x[i] - x);
                            double per = Math.abs(pirate_y[i] - y);
                            angle[i] = Math.atan(per / base);
                            if(sailor_x<=pirate_x[i] && sailor_y>=pirate_y[i]) angle[i]=Math.PI-angle[i];
                            else if(sailor_x<=pirate_x[i] && sailor_y<=pirate_y[i]) angle[i]=Math.PI+angle[i];
                            else if(sailor_x>=pirate_x[i] && sailor_y<=pirate_y[i]) angle[i]=2*Math.PI-angle[i];
                        }
                    }
                    if(invincible[i]==true) counter+=1;
                    double pirate_speed=Math.random() * 0.5 + 0.2;
                    if(pirateFacing[i].equals("left")) {
                        if(flag[i]==false && checkCollision("Pirate", pirate_x[i], pirate_y[i],"Block","res/level0.csv")==true) {
                            pirate[i] = new Image("res/pirate/pirateRight.png");
                            pirateFacing[i]="right";
                            pirate_x[i]+=pirate_speed;
                            flag[i]=true;
                        }
                        else if(pirate_x[i]-pirate_speed>=0) {
                            pirate_x[i] -= pirate_speed;
                            flag[i] = false;
                        }
                        else {
                            pirate[i] = new Image("res/pirate/pirateRight.png");
                            pirateFacing[i]="right";
                            flag[i]=false;
                        }
                    }
                    else{
                        if(flag[i]==false && checkCollision("Pirate", pirate_x[i], pirate_y[i],"Block","res/level0.csv")==true) {
                            pirate[i] = new Image("res/pirate/pirateLeft.png");
                            pirateFacing[i]="left";
                            pirate_x[i]-=pirate_speed;
                            flag[i]=true;
                        }
                        else if(pirate_x[i]+pirate_speed<=1000) {
                            pirate_x[i]+=pirate_speed;
                            flag[i]=false;
                        }
                        else {
                            pirate[i] = new Image("res/pirate/pirateLeft.png");
                            pirateFacing[i]="left";
                            flag[i]=false;
                        }
                    }
                    if((counter*1000)/60>3000) {
                        counter=0;
                        invincible[i]=false;
                    }
                    if(invincible[i]==true) {
                        if(pirateFacing[i]=="left") pirate[i] = new Image("res/pirate/pirateHitLeft.png");
                        else pirate[i] = new Image("res/pirate/pirateHitRight.png");
                    }
                    else {
                        if(pirateFacing[i]=="left") pirate[i] = new Image("res/pirate/pirateLeft.png");
                        else pirate[i] = new Image("res/pirate/pirateRight.png");
                    }
                    if(checkCollisionSailor(pirate_x[i], pirate_y[i])==true) {
                        if(pCollision[i]==false) {
                            pCollision[i] = true;
                            pirateHealth[i] -= 15;
                        }
                        invincible[i]=true;
                    } else {
                        pCollision[i]=false;
                    }
                    int health = (pirateHealth[i]*100)/45;
                    if(health<=0) pirateOver[i]=true;
                    if(pirateOver[i]==false) {
                        renderHealth(health, pirate_x[i] - 18, pirate_y[i] - 36, 20);
                        pirate[i].draw(pirate_x[i], pirate_y[i]);
                    }
                }
                int speed = 2;
                if (input.isDown(Keys.LEFT)) {
                    if (checkCollision("Sailor", x - speed, y,"Block","res/level0.csv") == false) {
                        ShadowPirate.setCollision(false);
                        if(x-speed>10) x -= speed;
                    }
                    else {
                        if(ShadowPirate.getCollision()==false) {
                            ShadowPirate.setCollision(true);
                            setHealth(getHealth() - getDamagePoints());
                        }
                    }
                    setFacing("left");
                }
                if (input.isDown(Keys.RIGHT)) {
                    if (checkCollision("Sailor", x + speed, y,"Block","res/level0.csv") == false) {
                        ShadowPirate.setCollision(false);
                        if(x+speed<1000) x += speed;
                    }
                    else {
                        if(ShadowPirate.getCollision()==false) {
                            ShadowPirate.setCollision(true);
                            setHealth(getHealth() - getDamagePoints());
                        }
                    }
                    setFacing("right");
                }
                if (input.isDown(Keys.UP)) {
                    if (checkCollision("Sailor", x, y - speed,"Block","res/level0.csv") == false) {
                        ShadowPirate.setCollision(false);
                        if(y-speed>0) y -= speed;
                    }
                    else {
                        if(ShadowPirate.getCollision()==false) {
                            ShadowPirate.setCollision(true);
                            setHealth(getHealth() - getDamagePoints());
                        }
                    }
                }
                if (input.isDown(Keys.DOWN)) {
                    if (checkCollision("Sailor", x, y + speed,"Block","res/level0.csv") == false) {
                        ShadowPirate.setCollision(false);
                        if(y+speed<670) y += speed;
                    }
                    else {
                        if(ShadowPirate.getCollision()==false) {
                            ShadowPirate.setCollision(true);
                            setHealth(getHealth() - getDamagePoints());
                        }
                    }
                }
                if (input.isDown(Keys.ESCAPE)) {
                    Window.close();
                }
                if(getHealth()<=0) {
                    setGameOver(true);
                    gameOver();
                    return;
                }
                renderHealth((getHealth()*100)/maxHealth,10,25, 30);
                if (x >= 990 && y > 630) {
                    setLevelZeroComplete(true);
                    levelZeroComplete();
                }
                else {
                    if (getFacing().equals("right")) sailorRight.draw(x, y);
                    else sailorLeft.draw(x, y);
                }
            }
        }
        else if(LevelOneStart==true && congrats==false) {
            if (input.wasPressed(Keys.SPACE)) {
                setLevelOnePress(true);
            }
            if (input.wasPressed(Keys.ESCAPE)) {
                Window.close();
            }
            if (getLevelOnePress() == false) {
                levelOneStart();
            }
            else {
                BACKGROUND_IMAGE=new Image("res/background1.png");
                BACKGROUND_IMAGE.draw(Window.getWidth()/2.0, Window.getHeight()/2.0);
                setPirateNo(0);
                //setPirateImage(false);
                //setSailorImage(false);
                readCSV("res/level1.csv");
                setPirateImage(true);
                int pirates=getPirates();
                for(int i=0;i<pirates;i++) {
                    if(angle[i]!=-100.0) {
                        double speed=0.2;
                        pirates_x[i]+=speed*Math.cos(angle[i]);
                        pirates_y[i]+=speed*Math.sin(angle[i]);
                        renderProjectile(pirates_x[i],pirates_y[i],speed,angle[i]);
                        cooldown[i]+=1;
                        if((cooldown[i]*1000)/60>6000) {
                            cooldown[i]=0;
                            angle[i]=-100.0;
                            pirates_x[i]=-1000.0;
                            pirates_y[i]=-1000.0;
                        }
                    }
                    if(Math.abs(x-pirate_x[i])<=100 && Math.abs(y-pirate_y[i])<=100) {
                        if(angle[i]==-100.0) {
                            System.out.println("-");
                            pirates_x[i]=pirate_x[i];
                            pirates_y[i]=pirate_y[i];
                            sailor_x=x;
                            sailor_y=y;
                            double base = Math.abs(pirate_x[i] - x);
                            double per = Math.abs(pirate_y[i] - y);
                            angle[i] = Math.atan(per / base);
                            if(sailor_x<=pirate_x[i] && sailor_y>=pirate_y[i]) angle[i]=Math.PI-angle[i];
                            else if(sailor_x<=pirate_x[i] && sailor_y<=pirate_y[i]) angle[i]=Math.PI+angle[i];
                            else if(sailor_x>=pirate_x[i] && sailor_y<=pirate_y[i]) angle[i]=2*Math.PI-angle[i];
                        }
                    }
                    if(invincible[i]==true) counter+=1;
                    double pirate_speed=Math.random() * 0.5 + 0.2;
                    if(pirateFacing[i].equals("left")) {
                        if(flag[i]==false && checkCollision("Pirate", pirate_x[i], pirate_y[i],"Block","res/level1.csv")==true) {
                            pirate[i] = new Image("res/pirate/pirateRight.png");
                            pirateFacing[i]="right";
                            pirate_x[i]+=pirate_speed;
                            flag[i]=true;
                        }
                        else if(pirate_x[i]-pirate_speed>=0) {
                            pirate_x[i] -= pirate_speed;
                            flag[i] = false;
                        }
                        else {
                            pirate[i] = new Image("res/pirate/pirateRight.png");
                            pirateFacing[i]="right";
                            flag[i]=false;
                        }
                    }
                    else{
                        if(flag[i]==false && checkCollision("Pirate", pirate_x[i], pirate_y[i],"Block","res/level1.csv")==true) {
                            pirate[i] = new Image("res/pirate/pirateLeft.png");
                            pirateFacing[i]="left";
                            pirate_x[i]-=pirate_speed;
                            flag[i]=true;
                        }
                        else if(pirate_x[i]+pirate_speed<=1000) {
                            pirate_x[i]+=pirate_speed;
                            flag[i]=false;
                        }
                        else {
                            pirate[i] = new Image("res/pirate/pirateLeft.png");
                            pirateFacing[i]="left";
                            flag[i]=false;
                        }
                    }
                    if((counter*1000)/60>3000) {
                        counter=0;
                        invincible[i]=false;
                    }
                    if(invincible[i]==true) {
                        if(pirateFacing[i]=="left") pirate[i] = new Image("res/pirate/pirateHitLeft.png");
                        else pirate[i] = new Image("res/pirate/pirateHitRight.png");
                    }
                    else {
                        if(pirateFacing[i]=="left") pirate[i] = new Image("res/pirate/pirateLeft.png");
                        else pirate[i] = new Image("res/pirate/pirateRight.png");
                    }
                    if(checkCollisionSailor(pirate_x[i], pirate_y[i])==true) {
                        if(pCollision[i]==false) {
                            pCollision[i] = true;
                            pirateHealth[i] -= 15;
                        }
                        invincible[i]=true;
                    } else {
                        pCollision[i]=false;
                    }
                    int health = (pirateHealth[i]*100)/45;
                    if(health<=0) pirateOver[i]=true;
                    if(pirateOver[i]==false) {
                        renderHealth(health, pirate_x[i] - 18, pirate_y[i] - 36, 20);
                        pirate[i].draw(pirate_x[i], pirate_y[i]);
                    }
                }

                int speed = 2;
                if (input.isDown(Keys.LEFT)) {
                    if (checkCollision("Sailor", x - speed, y,"Block","res/level1.csv") == false) {
                        ShadowPirate.setCollision(false);
                        if(x-speed>10) x -= speed;
                    }
                    else {
                        if(ShadowPirate.getCollision()==false) {
                            ShadowPirate.setCollision(true);
                            setHealth(getHealth() - getDamagePoints());
                        }
                    }
                    setFacing("left");
                }
                if (input.isDown(Keys.RIGHT)) {
                    if (checkCollision("Sailor", x + speed, y,"Block","res/level1.csv") == false) {
                        ShadowPirate.setCollision(false);
                        if(x+speed<1000) x += speed;
                    }
                    else {
                        if(ShadowPirate.getCollision()==false) {
                            ShadowPirate.setCollision(true);
                            setHealth(getHealth() - getDamagePoints());
                        }
                    }
                    setFacing("right");
                }
                if (input.isDown(Keys.UP)) {
                    if (checkCollision("Sailor", x, y - speed,"Block","res/level1.csv") == false) {
                        ShadowPirate.setCollision(false);
                        if(y-speed>0) y -= speed;
                    }
                    else {
                        if(ShadowPirate.getCollision()==false) {
                            ShadowPirate.setCollision(true);
                            setHealth(getHealth() - getDamagePoints());
                        }
                    }
                }
                if (input.isDown(Keys.DOWN)) {
                    if (checkCollision("Sailor", x, y + speed,"Block","res/level1.csv") == false) {
                        ShadowPirate.setCollision(false);
                        if(y+speed<705) y += speed;
                    }
                    else {
                        if(ShadowPirate.getCollision()==false) {
                            ShadowPirate.setCollision(true);
                            setHealth(getHealth() - getDamagePoints());
                        }
                    }
                }
                if (input.isDown(Keys.ESCAPE)) {
                    Window.close();
                }
                if(getHealth()<=0) {
                    setGameOver(true);
                    gameOver();
                    return;
                }
                renderHealth((getHealth()*100)/maxHealth,10,25, 30);
                //System.out.println(x+" "+y);
                if(showElixir==false && Math.abs(x-50)<=20 && Math.abs(y-350)<=20) {
                    picked+=1;
                    maxHealth+=35;
                    health=maxHealth;
                    elixirCount = picked;
                    showElixir=true;
                }
                if(showPotion==false && Math.abs(x-450)<=20 && Math.abs(y-350)<=20) {
                    picked+=1;
                    health=Math.min(health+25,maxHealth);
                    potionCount = picked;
                    showPotion=true;
                }
                if(showSword==false && Math.abs(x-900)<=20 && Math.abs(y-700)<=20) {
                    picked+=1;
                    swordCount = picked;
                    showSword=true;
                }
                if(showElixir==true) {
                    Image elixirIcon = new Image("res/items/elixirIcon.png");
                    elixirIcon.draw(20,20+elixirCount*40);
                }
                if(showPotion==true) {
                    Image elixirIcon = new Image("res/items/potionIcon.png");
                    elixirIcon.draw(20,20+potionCount*40);
                }
                if(showSword==true) {
                    Image elixirIcon = new Image("res/items/swordIcon.png");
                    elixirIcon.draw(20,20+swordCount*40);
                }
                if (Math.abs(x-937)<=20 && Math.abs(y-70)<=20) {
                    congrats();
                    congrats=true;
                    return;
                }
                else {
                    if (getFacing().equals("right")) sailorRight.draw(x, y);
                    else sailorLeft.draw(x, y);
                }
            }
        }
        else if(getGameOver() == true){
            gameOver();
        }
        else if(congrats==true) {
            congrats();
        }
        else {
            if(LevelZero==false) {
                counter+=1;
                levelZeroComplete();
            }
            else {
                LevelOneStart=true;
                for(int i=0;i<100;i++) {
                    pirates_x[i]=-1000.0;
                    pirates_y[i]=-1000.0;
                    angle[i]=-100.0;
                }
                setPirateImage(false);
                setSailorImage(false);
                levelOneStart();
            }
            if((counter*1000)/60>3000) {
                counter=0;
                LevelZero=true;
            }
            //congrats();
        }
    }

}
