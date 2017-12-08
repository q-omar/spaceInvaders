package model;

import java.awt.Graphics;


public class BarrierArray{ 
    private final int AMOUNT;
    private final int ROWS;
	private final int SIZE;
    private final Barrier[] barriers;
    
    /*
    Method: BarrierArray
    @param toCopy type BarrierArray for copying members onto this class, avoiding privacy leaks
    Copies members of toCopy onto this
    */	 
    BarrierArray(BarrierArray toCopy) {
    	AMOUNT = toCopy.getAmount();
    	SIZE = toCopy.getSize();
    	ROWS = toCopy.getRows();
    	barriers = new Barrier[AMOUNT];
    	for (int i = 0; i < barriers.length; i++) {
    		barriers[i] = toCopy.getBarriers()[i];
    	}
    }
	
    //Constructor
    BarrierArray(String version){

        if (version.equals("GUI")) {
        	AMOUNT = 6;
        	ROWS = 2;
			SIZE= 10;
        	barriers = new Barrier[AMOUNT];
			createBarrierArray();
        	setBarriersGUI();

        } else {
        	AMOUNT = 3;
        	ROWS = 2;
			SIZE =4;
			barriers = new Barrier[AMOUNT];
        	setBarriersText();  
        }
 
    }
   
	//************getters***********
	
	public Barrier[] getBarriers(){
        return barriers;
    }
	
    public int getAmount() {
    	return AMOUNT;
    }
    
    public int getRows() {
    	return ROWS;
    }
    
    public int getSize() {
    	return SIZE;
    }
	//******************************
	
	
	
	private void setBarriersText(){
  		int x=5;
  		int y=24;
  		int height = 2;
  		int width = 10;
  		
  		for (int a = 0; a<AMOUNT; a++){
  			barriers[a] = new Barrier(x, y, width, height);
  			x+=20;
  		}
      } 

   private void createBarrierArray(){
	   for(int a=0;a<AMOUNT;a++){
		   
			for (int r=0; r<ROWS ; r++) {
				for (int c=0; c<SIZE; c++){
					barriers[a] = new Barrier(0,0,20,20);
				}
			}
        }
    }
	

    private void setBarriersGUI(){
        int whereX=55;
        int whereY= 400;

		for (int a =0; a<AMOUNT; a++){
			barriers[a] = new Barrier(whereX, whereY, 60,20);
			whereX += 115;	
		}
    }

    public void drawBarrierArray(Graphics g){
        for (int a=0; a<AMOUNT;a++){
            if (barriers[a].getBarrierHit() <3){
                barriers[a].draw(g);
            }
        }
    }
}
