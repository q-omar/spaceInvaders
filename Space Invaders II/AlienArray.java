import java.awt.Component;
import java.awt.Graphics;
import java.awt.Color;


public class AlienArray{
    
    int numAliens=5;
    int rowsAliens=3;
    private boolean moveRight = true;
    Alien[][] aliens = new Alien[rowsAliens][numAliens];
    
    public AlienArray(){
        createAlienArrays();
        setGUIaliens();
    }
    
    
    public void createAlienArrays(){
        for (int r=0; r<rowsAliens ; r++) {
            for (int c=0; c<numAliens; c++){
                aliens[r][c] = new Alien();
            }
        }
    }
    
    public void setAliens(){
        for (int r = 0; r < rowsAliens ; r++) {
            for (int c=0; c<numAliens;c++){
                aliens[r][c].setAlienX(4+2*c);
                if (r%2!=0){
                    aliens[r][c].setAlienY(r+1);
                }else{
                    aliens[r][c].setAlienY(r);
                }
            }
        }
    }

    
    public void aliensMovement(int width){
        boolean stop=false;
        if (moveRight){
            for (int r = 0; r < rowsAliens ; r++) {
                for (int c=0; c<numAliens;c++){
                    aliens[r][c].moveRight();
                    if(aliens[rowsAliens-1][numAliens-1].getAlienX() >= width - aliens[r][c].getRadius()*2){
                        stop=true;
                        break;
                    }
                }
            }

            if(stop){
                for (int r = 0; r < rowsAliens ; r++) {
                    for (int c=0; c<numAliens;c++){
                        aliens[r][c].moveDown();
                    }
                }
                stop= false;
                moveRight=false;
                return; 
            }
    
        }else{

            for (int r = 0; r < rowsAliens ; r++) {
                for (int c=0; c<numAliens;c++){
                    aliens[r][c].moveLeft();
                }
            }

            if(aliens[rowsAliens-1][0].getAlienX() <= 0) { // Moved this out of the loop so aliens don't go out of sync
                stop=true;
            }

            if(stop){
                for (int r = 0; r < rowsAliens ; r++) {
                    for (int c=0; c<numAliens;c++){
                        aliens[r][c].moveDown();
                        stop=false;
                    }
                }
                moveRight=true;
                return; 
            }
        }
    } 


    public void drawAlienArray(Graphics g){
        //setGUIaliens();
        for (int r=0; r<rowsAliens;r++){
            for (int c=0; c<numAliens;c++){
                if (aliens[r][c].isAlive()){
                    aliens[r][c].draw(g);
                }
            }
        }
    }
    
    public void setGUIaliens(){
        int whereX=5;
        int whereY=0;
        createAlienArrays();

        for (int r=0; r<rowsAliens;r++){
            for (int c=0; c<numAliens;c++){
                whereX+=50;
                aliens[r][c].setAlienX(whereX);
                aliens[r][c].setAlienY(whereY);
            }
            whereX=5;
            whereY+=40;
        }
    }
        
        
        
        
        
        
        
        
        
        
}