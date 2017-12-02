package model;

import java.awt.Graphics;


public class BarrierArray{ 
    private int amount; 
    private int rows;
	private int size;

    
    private Barrier[] barriers;
    
    BarrierArray(String version){

        if (version.equals("GUI")) {
        	amount = 3;
        	rows = 2;
			size= 10;
        	barriers = new Barrier[amount];
			createBarrierArray();
        	setBarriersGUI();

        } else {
        	amount = 3;
        	rows = 2;
			size =4;
			
			barriers = new Barrier[amount];
        	setBarriersText();  
        }
 
    }
   

	public Barrier[] getBarriers(){
        return barriers;
    }
	
    public int getAmount() {
    	return amount;
    }

	private void setBarriersText(){
  		int x=5;
  		int y=24;
  		int height = 2;
  		int width = 10;
  		
  		for (int a = 0; a<amount; a++){
  			barriers[a] = new Barrier(x, y, width, height);
  			x+=20;
  		}
      } 

   private void createBarrierArray(){
	   for(int a=0;a<amount;a++){
		   
			for (int r=0; r<rows ; r++) {
				for (int c=0; c<size; c++){
					barriers[a] = new Barrier(0,0,20,20);
				}
			}
        }
    }
	

    private void setBarriersGUI(){
        int whereX=55;
        int whereY= 400;

		for (int a =0; a<amount; a++){
			barriers[a] = new Barrier(whereX, whereY, 60,20);
			whereX += 115;	
		}
    }

    public void drawBarrierArray(Graphics g){
        for (int a=0; a<amount;a++){
            if (barriers[a].getBarrierHit() <3){
                barriers[a].draw(g);
            }
        }
    }
}