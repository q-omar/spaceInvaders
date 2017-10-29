import java.awt.Component;
import java.awt.Graphics;
import java.awt.Color;


public class AlienArray{
    
    private int numAliens;  // Currently the GUI version can run fine
    private int rowsAliens;
    private boolean moveRight = true;
    Alien[][] aliens = new Alien[rowsAliens][numAliens];
    
    public AlienArray(String version){

        if (version.equals("GUI")) {
        	numAliens = 5;
        	rowsAliens = 3;
        	aliens = new Alien[rowsAliens][numAliens];
        	createAlienArrays();
        	setGUIaliens();

        } else {
        	numAliens = 5;
        	rowsAliens = 3;
        	aliens = new Alien[rowsAliens][numAliens];
        	createAlienArrays();
        	setAliens();  // One of the issues is the text version and GUI having a different array size, maybe an array list would work since its length is flexible?
        }
        
    }
    
    public int getNumAliens() {
    	return numAliens;
    }

    public int getRowsAliens() {
    	return rowsAliens;
    }
    
    public void createAlienArrays(){
        for (int r=0; r<rowsAliens ; r++) {
            for (int c=0; c<numAliens; c++){
                aliens[r][c] = new Alien(10, 5, 20);
            }
        }
    }
    
    public void setAliens(){
        for (int r = 0; r < rowsAliens ; r++) {
            for (int c=0; c< numAliens;c++){
            	aliens[r][c] = new Alien(3, 3);
            	System.out.println("test");
                aliens[r][c].setAlienX(4+2*c);
                if (r%2!=0){
                    aliens[r][c].setAlienY(r+1);     // There's an out of bounds error when running the text version, I have some print statements to troubleshoot
                }else{
                    aliens[r][c].setAlienY(r);
                }

                System.out.println("Alien X: " + aliens[r][c].getAlienX() + "  Alien Y: " + aliens[r][c].getAlienY());
            }
        }
    }

    
    public void aliensMovement(int width){
        boolean stop=false;
        if (moveRight){
            for (int r = 0; r < rowsAliens ; r++) {
                for (int c=0; c<numAliens;c++){
                    aliens[r][c].moveRight();

                    if (aliens[rowsAliens-1][numAliens-1].getAlienX() >= width - aliens[r][c].getRadius()*2){
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

            if(aliens[rowsAliens-1][0].getAlienX() <= 1) { // Moved this out of the loop so aliens don't go out of sync
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