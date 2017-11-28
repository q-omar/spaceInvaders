package model;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Color;

public class Barrier extends Shape{
	private int isHit = 0;
	private char barrierChar =' ';
	
	public Barrier(int xcoord, int ycoord){
		super(xcoord,ycoord,0,0);
	}
	
	public Barrier(int xcoord, int ycoord, int wide, int high){
		super(xcoord,ycoord, wide,high);
	}
	
	public void barrierIsHit(int count){
		if (isHit <3){
			isHit +=1;
		}
	}
	
	public int getBarrierHit(){
		return isHit;
	}
	
	
	public char barrierCharText(){
		
		if (isHit ==0){
			barrierChar ='A';
		} else if (isHit==1){
			barrierChar ='B';
		}else if (isHit ==2){
			barrierChar ='C';
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

