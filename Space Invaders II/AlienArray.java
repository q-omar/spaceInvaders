import java.awt.Component;
import java.awt.Graphics;
import java.awt.Color;

public class AlienArray{
    
    private int numAlienCol = 8; //tweak number of aliens as desired
    private int numAlienRows = 4;
    Alien [][] alienArray = new Alien[numAlienRows][numAlienCol];
    private boolean moveRight = true;
    
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
                alienArray[r][c].setAlienX(20+40*c);
                alienArray[r][c].setAlienY(10 + 30*r);
            }
        }
    }

    
    public void aliensMovement(){
        
        if (moveRight){
            if (alienArray[0][5].getAlienX()>850){ //850 is the RIGHT boundary of the Jframe window, youll have to tweak this if u change number of aliens per column
                System.out.println(alienArray[0][numAlienCol-1].getAlienX());
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
            
            if (alienArray[0][0].getAlienX()<22){ //22 is the LEFT boundary of jframe window
                for (int r = 0; r < numAlienRows ; r++) {
                    for (int c = 0; c < numAlienCol; c++){
                        alienArray[r][c].moveDown();
                        System.out.println("lower  loop is executing");
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
                alienArray[r][c].drawAlien(g);
            }
           
        }
    }
    public AlienArray(){ 
        createAlienArrays();
        setAliens();
    }  
}