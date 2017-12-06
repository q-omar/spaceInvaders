package model;

import java.awt.Graphics;
import java.awt.Color;

public class Barrier extends Shape{
	private int isHit = 0;
	private char barrierChar =' ';

	Barrier(Barrier toCopy){
		super(toCopy.xCoord, toCopy.yCoord, toCopy.width, toCopy.height);
	}
	
	Barrier(int xcoord, int ycoord, int wide, int high){
		super(xcoord,ycoord, wide,high);
	}
	
	void barrierIsHit(){
		if (isHit <3){
			isHit +=1;
		}
	}
	
	int getBarrierHit(){
		return isHit;
	}
	
	public char barrierCharText(){
		
		if (isHit ==0){
			barrierChar ='3';
		} else if (isHit==1){
			barrierChar ='2';
		}else if (isHit ==2){
			barrierChar ='1';
		}else if (isHit ==3){
			barrierChar =' ';
		}
		return barrierChar;
	}
	
	public void draw(Graphics g){
		if (isHit ==0){
			g.setColor(Color.GREEN);
		} else if (isHit==1){
			g.setColor(Color.YELLOW);
		}else if (isHit ==2){
			g.setColor(Color.RED);
		}else if (isHit ==3){
			g.setColor(Color.BLACK);
		}
		g.fillRect(getXCoord(),getYCoord(),getWidth(),getHeight());
	}
}