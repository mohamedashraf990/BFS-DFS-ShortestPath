import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import com.sun.jdi.event.ThreadDeathEvent;

public class FinalGame {
	
	
	
	
	
	
	
	
	
	
	
	public static String bfs(String grid) {
		
		String values[]=grid.split(";");
		String gridSize=values[0];
		String gridSizeArray[] =gridSize.split(",");
		int m=Integer.parseInt(gridSizeArray[0]);
		int n=Integer.parseInt(gridSizeArray[1]);
		int checker[][]=new int[m][n];

		
		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++) {
				checker[i][j]=0;
			}
		}
		
	    String solutionArrayString[][]=new String [m][n];
		String posXYStart=values[1];
		String posXYStartArray[]=posXYStart.split(",");
		int posXStart=Integer.parseInt(posXYStartArray[0]);
		int posYStart=Integer.parseInt(posXYStartArray[1]);
		String obstacleString=values[2];
		String posXYEnd=values[3];
		String posXYEndArray[]=posXYEnd.split(",");
		int posXEnd=Integer.parseInt(posXYEndArray[0]);
		int posYEnd=Integer.parseInt(posXYEndArray[1]);
		boolean[][] isVisited=new boolean[m][n];
		
		Queue<Pair> valuesToTraverse=new LinkedList<>();
		 

		String obstaclesLocationsArray[]=obstacleString.split(",");
	
		
		 
		 
		 
		for(int i=0;i<obstaclesLocationsArray.length;i=i+2) {
			int x=Integer.parseInt(obstaclesLocationsArray[i]);
	
			int y=Integer.parseInt(obstaclesLocationsArray[i+1]);

			checker[x][y]=1;
		}
		checker[posXStart][posYStart]=2;
		checker[posXEnd][posYEnd]=3;
	
		
		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++) {
				 isVisited[i][j]=false;
			}
		}
		isVisited[posXStart][posYStart]=true;
		
	
		
		// first pair to add is the first element we will traverse which is the starting point
		Pair startPair=new Pair(posXStart, posYStart);
		valuesToTraverse.add(startPair);
	
		
		solutionArrayString[posXStart][posYStart]="";
		
		
		if(posXStart==posXEnd && posYStart==posYEnd) {
			return ";0";
		}
		while(!valuesToTraverse.isEmpty()) {
			
		
		for(int i=0;i<valuesToTraverse.size();i++) {
			
			Pair xPair=valuesToTraverse.poll();
			int x=xPair.getX();
			int y=xPair.getY();
			
		
			if(MoveUp(x)) {
				if(checker[x-1][y]!=1 &&isVisited[x-1][y]==false) {
					
					int newY=y;
					int oldX=x-1;
					if(checker[oldX][newY]==3){
						solutionArrayString[posXEnd][posYEnd]=solutionArrayString[x][y]+",up";
						String count[]=solutionArrayString[posXEnd][posYEnd].split(",");
						 String solString=(solutionArrayString[posXEnd][posYEnd]+";"+(count.length-1)).substring(1);
						 
						return solString;
					}
					Pair newValues=new Pair(oldX, newY);
					valuesToTraverse.add(newValues);
					isVisited[x-1][y]=true;
					solutionArrayString[oldX][newY]=solutionArrayString[x][y]+",up";
					// here we should add elements to the previous one
				}
				
			}
			if(MoveDown(x, m)) {
				if(checker[x+1][y]!=1 &&isVisited[x+1][y]==false) {
					int newY=y;
					int oldX=x+1;
					if(checker[oldX][newY]==3){
						solutionArrayString[posXEnd][posYEnd]=solutionArrayString[x][y]+",down";
					
						String count[]=solutionArrayString[posXEnd][posYEnd].split(",");
					    
						 String solString=(solutionArrayString[posXEnd][posYEnd]+";"+(count.length-1)).substring(1);
						
							return solString;
					}
					Pair newValues=new Pair(oldX, newY);
					valuesToTraverse.add(newValues);
					isVisited[x+1][y]=true;
					solutionArrayString[oldX][newY]=solutionArrayString[x][y]+",down";
				}
			}
			if(MoverRight(y, n)) {
				
				if(checker[x][y+1]!=1 &&isVisited[x][y+1]==false) {
					
					int oldY=y+1;
					int newX=x;
					if(checker[newX][oldY]==3){
						solutionArrayString[posXEnd][posYEnd]=solutionArrayString[x][y]+",right";
						String count[]=solutionArrayString[posXEnd][posYEnd].split(",");
					    
						 String solString=(solutionArrayString[posXEnd][posYEnd]+";"+(count.length-1)).substring(1);
						
							return solString;
					}
					Pair newValues=new Pair(newX, oldY);
					valuesToTraverse.add(newValues);
					isVisited[x][y+1]=true;
					solutionArrayString[newX][oldY]=solutionArrayString[x][y]+",right";

				}
			}
			if(MoveLeft(y)) {
				if(checker[x][y-1]!=1 &&isVisited[x][y-1]==false) {	
					int oldY=y-1;
					int newX=x;
					if(checker[newX][oldY]==3){
						solutionArrayString[posXEnd][posYEnd]=solutionArrayString[x][y]+",left";
					    String count[]=solutionArrayString[posXEnd][posYEnd].split(",");
					    
						 String solString=(solutionArrayString[posXEnd][posYEnd]+";"+(count.length-1)).substring(1);
				
						return solString;
					}
					Pair newValues=new Pair(newX, oldY);
					valuesToTraverse.add(newValues);
					isVisited[x][y-1]=true;
					solutionArrayString[newX][oldY]=solutionArrayString[x][y]+",left";
				}
	
			}
			
		}
		
		}
		return "No Solution";
		
	}
	
	
	public static String dfs(String grid) {
		
		
		
		String values[]=grid.split(";");
		String gridSize=values[0];
		String gridSizeArray[] =gridSize.split(",");
		int m=Integer.parseInt(gridSizeArray[0]);
		int n=Integer.parseInt(gridSizeArray[1]);
		int checker[][]=new int[m][n];

		

		
		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++) {
				checker[i][j]=0;
			}
		}
		
	    String solutionArrayString[][]=new String [m][n];
		String posXYStart=values[1];
		String posXYStartArray[]=posXYStart.split(",");
		int posXStart=Integer.parseInt(posXYStartArray[0]);
		int posYStart=Integer.parseInt(posXYStartArray[1]);
		String obstacleString=values[2];
		String posXYEnd=values[3];
		String posXYEndArray[]=posXYEnd.split(",");
		int posXEnd=Integer.parseInt(posXYEndArray[0]);
		int posYEnd=Integer.parseInt(posXYEndArray[1]);
		boolean[][] isVisited=new boolean[m][n];
		
		Stack<Pair> valuesToTraverse=new Stack<>();
		 

		String obstaclesLocationsArray[]=obstacleString.split(",");

		
		 
		 
		
		for(int i=0;i<obstaclesLocationsArray.length;i=i+2) {
			int x=Integer.parseInt(obstaclesLocationsArray[i]);
		
			int y=Integer.parseInt(obstaclesLocationsArray[i+1]);
		
			checker[x][y]=1;
		}
		checker[posXStart][posYStart]=2;
		checker[posXEnd][posYEnd]=3;
	
		
		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++) {
				 isVisited[i][j]=false;
			}
		}
		isVisited[posXStart][posYStart]=true;
		
	
		
		// first pair to add is the first element we will traverse which is the starting point
		Pair startPair=new Pair(posXStart, posYStart);
		valuesToTraverse.push(startPair);
	
		
		solutionArrayString[posXStart][posYStart]="";
		
		
		if(posXStart==posXEnd && posYStart==posYEnd) {
			return ";0";
		}
		
		while(!valuesToTraverse.isEmpty()) {
			
			
			for(int i=0;i<valuesToTraverse.size();i++) {
				
				Pair xPair=valuesToTraverse.pop();
				int x=xPair.getX();
				int y=xPair.getY();
				
			
				if(MoveUp(x)) {
					if(checker[x-1][y]!=1 &&isVisited[x-1][y]==false) {
						
						int newY=y;
						int oldX=x-1;
						if(checker[oldX][newY]==3){
							solutionArrayString[posXEnd][posYEnd]=solutionArrayString[x][y]+",up";
							String count[]=solutionArrayString[posXEnd][posYEnd].split(",");
							 String solString=(solutionArrayString[posXEnd][posYEnd]+";"+(count.length-1)).substring(1);
							 
							return solString;
						}
						Pair newValues=new Pair(oldX, newY);
						valuesToTraverse.add(newValues);
						isVisited[x-1][y]=true;
						solutionArrayString[oldX][newY]=solutionArrayString[x][y]+",up";
					}	
				}
				if(MoveDown(x, m)) {
					if(checker[x+1][y]!=1 &&isVisited[x+1][y]==false) {
						int newY=y;
						int oldX=x+1;
						if(checker[oldX][newY]==3){
							solutionArrayString[posXEnd][posYEnd]=solutionArrayString[x][y]+",down";
						
							String count[]=solutionArrayString[posXEnd][posYEnd].split(",");
						    
							 String solString=(solutionArrayString[posXEnd][posYEnd]+";"+(count.length-1)).substring(1);
						
								return solString;
						}
						Pair newValues=new Pair(oldX, newY);
						valuesToTraverse.add(newValues);
						isVisited[x+1][y]=true;
						solutionArrayString[oldX][newY]=solutionArrayString[x][y]+",down";
					}
				}
				if(MoverRight(y, n)) {
					
					if(checker[x][y+1]!=1 &&isVisited[x][y+1]==false) {
						
						int oldY=y+1;
						int newX=x;
						if(checker[newX][oldY]==3){
							solutionArrayString[posXEnd][posYEnd]=solutionArrayString[x][y]+",right";
							String count[]=solutionArrayString[posXEnd][posYEnd].split(",");
						    
							 String solString=(solutionArrayString[posXEnd][posYEnd]+";"+(count.length-1)).substring(1);
						
								return solString;
						}
						Pair newValues=new Pair(newX, oldY);
						valuesToTraverse.add(newValues);
						isVisited[x][y+1]=true;
						solutionArrayString[newX][oldY]=solutionArrayString[x][y]+",right";

					}
				}
				if(MoveLeft(y)) {
					if(checker[x][y-1]!=1 &&isVisited[x][y-1]==false) {	
						int oldY=y-1;
						int newX=x;
						if(checker[newX][oldY]==3){
							solutionArrayString[posXEnd][posYEnd]=solutionArrayString[x][y]+",left";
						    String count[]=solutionArrayString[posXEnd][posYEnd].split(",");
						    
							 String solString=(solutionArrayString[posXEnd][posYEnd]+";"+(count.length-1)).substring(1);
					
							return solString;
						}
						Pair newValues=new Pair(newX, oldY);
						valuesToTraverse.add(newValues);
						isVisited[x][y-1]=true;
						solutionArrayString[newX][oldY]=solutionArrayString[x][y]+",left";
					}
		
				}
				
			}
			
			}

		return "No Solution";
		
	}
	
	
	public static boolean MoveUp(int x){
		if(x-1>=0) {
			return true;
		}
		return false;
		
	}
	public static boolean MoveDown(int x,int height){
		if(x+1<height) {
			return true;
		}
		return false;
		
	}
	public static boolean MoverRight(int y,int width){
		if(y+1<width) {
			return true;
		}
		return false;
		
	}
	public static boolean MoveLeft(int y){
		if ((y-1)>=0) {
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {

	
	}

}
