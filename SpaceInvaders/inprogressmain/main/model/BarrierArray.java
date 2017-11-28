package model;

import java.awt.Graphics;


public class BarrierArray{ 
    private int amount; 
    private int rows;
	private int size;
	
	private String version;
    
    private Barrier[] barriersGUI;
	private Barrier[][][] barriersText;
    
    public BarrierArray(String version){

        if (version.equals("GUI")) {
			version="GUI";
        	amount = 3;
        	rows = 2;
			size= 10;
        	barriersGUI = new Barrier[amount];
			createBarrierArray();
        	setBarriersGUI();

        } else {
			version="Text";
        	amount = 2;
        	rows = 2;
			size =4;
        	barriersText = new Barrier[amount][rows][size];
        	setBarriersText();  
        }
 
    }
   
    public String getVersion(){
		return version;
	}
	
	public Barrier[] getBarriersGUI(){
        return barriersGUI;
    }
	
	public Barrier[][][] getBarriersText(){
		return barriersText;
	}
	
    public int getAmount() {
    	return amount;
    }

    public int getRows() {
    	return rows;
    }
    public int getSize(){
		return size;
	}
	
    public void setBarriersText(){
		int x=3;
		int y=20;
		for (int a =0; a<amount; a++){
			for (int r=0; r<rows ; r++) {
				for (int c=0; c<size; c++){
					barriersText[a][r][c] = new Barrier(x+c, y+r);
				}
			}
			x+=10;
		}
    }

   public void createBarrierArray(){
	   for(int a=0;a<amount;a++){
		   
			for (int r=0; r<rows ; r++) {
				for (int c=0; c<size; c++){
					barriersGUI[a] = new Barrier(0,0,20,20);
				}
			}
        }
    }
	

    public void setBarriersGUI(){
        int whereX=55;
        int whereY= 400;

		for (int a =0; a<amount; a++){
			barriersGUI[a] = new Barrier(whereX, whereY, 60,20);
			whereX += 115;	
		}
    }

    public void drawBarrierArray(Graphics g){
        for (int a=0; a<amount;a++){
            if (barriersGUI[a].getBarrierHit() <3){
                barriersGUI[a].draw(g);
            }
        }
    }
}