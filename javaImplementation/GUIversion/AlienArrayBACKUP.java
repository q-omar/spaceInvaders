import java.awt.Component;
import java.awt.Graphics;
import java.awt.Color;

public class AlienArray{
    
    private int numAlienCol = 8; //tweak number of aliens as desired
    private int numAlienRows = 4;
    Alien [][] alienArray = new Alien[numAlienRows][numAlienCol];

    private int alienXspacing = 50;
    private int alienYspacing = 40;
    private boolean moveRight = true;
    
    public int getRows(){
        return numAlienRows;
    }
    public int getCol(){
        return numAlienCol;
    }

    public void createAlienArrays(){
        for (int r = 0; r < numAlienRows ; r++) {
            for (int c = 0; c < numAlienCol; c++){
                alienArray[r][c] = new Alien();
            }
        }
    }
    
    public void setAliens(){ 
        for (int r = 0; r < numAlienRows ; r++) {
            for (int c = 0; c < numAlienCol; c++){
                alienArray[r][c].setAlienX(alienXspacing*c);
                alienArray[r][c].setAlienY(alienYspacing*r);
            }
        }
    }

    
    public void aliensMovement(int width){

        if (moveRight){
            if (alienArray[0][numAlienCol-1].getAlienX()>=width - alienArray[0][0].getRadius()*2){ 
                for (int r = 0; r < numAlienRows ; r++) {
                    for (int c = 0; c < numAlienCol; c++){
                        alienArray[r][c].moveDown();
                    } 
                }
                moveRight=false;
                return; 
            }

            for (int r = 0; r < numAlienRows ; r++) {
                for (int c = 0; c < numAlienCol; c++){
                    alienArray[r][c].moveRight();
                }
            }
        }
        
        else{
            
            if (alienArray[0][0].getAlienX()<=0){ 
                for (int r = 0; r < numAlienRows ; r++) {
                    for (int c = 0; c < numAlienCol; c++){
                        alienArray[r][c].moveDown();
                    }
                    
                }
                moveRight=true;
                return; 
            }

            for (int r = 0; r < numAlienRows ; r++) {
                for (int c = 0; c < numAlienCol; c++){
                    alienArray[r][c].moveLeft();
                }
            }
        }
    }

    
    public void drawAliens(Graphics g) { 
        for (int r = 0; r < numAlienRows ; r++) {
            for (int c = 0; c < numAlienCol; c++){
                if (alienArray[r][c].isAlive()){
                    alienArray[r][c].drawAlien(g);
                }
                
            }
           
        }
    }

    public AlienArray(){ 
        createAlienArrays();
        setAliens();
    }  
}