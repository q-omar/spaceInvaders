import java.awt.Component;
import java.awt.Graphics;
import java.awt.Color;

public class AlienArray{
    
    private int numAliens = 6; //tweak number of aliens as desired
    Alien[] alienRowOne = new Alien[numAliens];
    Alien[] alienRowTwo = new Alien[numAliens]; 
    Alien[] alienRowThree = new Alien[numAliens]; 
    private boolean moveRight = true;

    public AlienArray(){ 
        createAlienArrays();
        setAliens();
    }
    
    public void createAlienArrays(){
        for (int i = 0; i < numAliens ; i++) {
            alienRowOne[i] = new Alien();
            alienRowTwo[i] = new Alien(); 
            alienRowThree[i]= new Alien();
        }
    }
    
    public void setAliens(){ 
        for (int i = 0; i < numAliens ; i++) {
            alienRowOne[i].setAlienX(20+40*i); //change the 60 to modify 'spacing' between aliens 
            alienRowOne[i].setAlienY(10); 
            alienRowTwo[i].setAlienX(20+40*i); //change the 60 to modify 'spacing' between aliens 
            alienRowTwo[i].setAlienY(40);
            alienRowThree[i].setAlienX(20+40*i); //change the 60 to modify 'spacing' between aliens 
            alienRowThree[i].setAlienY(70); //40 is vertical spacing 
        }
    }

    
    public void aliensMovement(){
        
        if (moveRight){
            if (alienRowOne[numAliens-1].getAlienX()>360){ //330 is the RIGHT boundary of the Jframe window 
                for (int i = 0; i < numAliens ; i++) {
                    alienRowOne[i].moveDown();
                    alienRowTwo[i].moveDown();
                    alienRowThree[i].moveDown();
                }
                moveRight=false;
                return; 
            }

            for (int i = 0; i < numAliens ; i++) {
                alienRowOne[i].moveRight();
                alienRowTwo[i].moveRight();
                alienRowThree[i].moveRight();
            }
        }
        
        else{
            if (alienRowOne[0].getAlienX()<22){ //22 is the LEFT boundary of jframe window
                for (int i = 0; i < numAliens ; i++) {
                    alienRowOne[i].moveDown();
                    alienRowTwo[i].moveDown();
                    alienRowThree[i].moveDown();
                }
                moveRight=true;
                return;
            }

            for (int i = 0; i < numAliens ; i++) {
                alienRowOne[i].moveLeft();
                alienRowTwo[i].moveLeft();
                alienRowThree[i].moveLeft();
            }
        }
    }

    public void drawAliens(Graphics g) { //drawing aliens
        for (int i = 0; i < numAliens; i++) {
            alienRowOne[i].drawAlien(g);
            alienRowTwo[i].drawAlien(g);
            alienRowThree[i].drawAlien(g);
        } 
    
    }  
}