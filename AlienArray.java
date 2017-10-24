public class AlienArray{
    
    public int numAliens = 18;
    Alien[] alienRowOne = new Alien[numAliens];
    Alien[] alienRowTwo = new Alien[numAliens]; //if you want to add another row, just repeat this line
    private boolean moveRight = true;


    public void createAlienArrays(){
        for (int i = 0; i < numAliens ; i++) {
            alienRowOne[i] = new Alien();
            alienRowTwo[i] = new Alien(); //again, make a row 'alienRowThree' if needed
        }
    }
    

    public void setAliens(){
        for (int i = 0; i < numAliens ; i++) {
            alienRowOne[i].setAlienX(4+2*i);
            alienRowOne[i].setAlienY(2);
            alienRowTwo[i].setAlienX(4+2*i);
            alienRowTwo[i].setAlienY(4); //again, make a row 'alienRowThree' if needed
        }
    }

    
    public void aliensMovement(){
        
        if (moveRight){
            if (alienRowOne[17].getAlienX()==59){ //17 is the last alien on the right side, 59 is a boundary condition
                //if you change the amount of aliens, you'll need to tweak the boudary condition or array will crash
                for (int i = 0; i < numAliens ; i++) {
                    alienRowOne[i].moveDown();
                    alienRowTwo[i].moveDown();
                }
                moveRight=false;
                return; 
            }

            for (int i = 0; i < numAliens ; i++) {
                alienRowOne[i].moveRight();
                alienRowTwo[i].moveRight();
            }
        }
        
        else{
            if (alienRowOne[0].getAlienX()==1){ //0 index is last alien on left side, 1 is the boundary condition on left side
                for (int i = 0; i < numAliens ; i++) {
                    alienRowOne[i].moveDown();
                    alienRowTwo[i].moveDown();
                }
                moveRight=true;
                return;
            }

            for (int i = 0; i < numAliens ; i++) {
                alienRowOne[i].moveLeft();
                alienRowTwo[i].moveLeft();
            }
        }
    }  
}