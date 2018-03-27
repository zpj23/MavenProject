package com.totoro.core.utils;

import java.util.ArrayList;
import java.util.List;

public class Ball {

     public static void main(String[] args) {		
    	 String ball = "";    	 
    	 List<Integer> list = new ArrayList<Integer>();    	 
    	 for(int v=1;v<34;v++){
    		 list.add(v);
    	 }    	
    	 for(int i=0;i<6;i++){    		 
    		 int x=0;
    		 for(int j=0;j<1000;j++){
    			 int a  = (int) (Math.random()*100);
    			 
    			 if(a>0&&a<34-x){    				 
    				 ball +=String.valueOf(list.get(a-1))+" ";
    				 list.remove(a-1);
    				 x++;
    				 break;
    			 }
    		 }    		 
    	 }    	 
    	 for(int j=0;j<1000;j++){
			 int a  = (int) (Math.random()*100);
			 if(a>0&&a<17){
				 ball += " | "+String.valueOf(a)+" ";
				 break;
			 }
    	 }    	 
    	 System.out.println(ball);
	}
	
}
