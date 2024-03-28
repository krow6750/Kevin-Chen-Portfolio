/*
 * model for MVC design
 * Contains 2d array screen representation and transformation
 * 3/30/23
 * -Dr. G
 * */

import java.util.Random;


public class FillModel {

	 private int[][] screen;
	 // Row of the display
	 private int m;
	 // Column of the display
	 private int n;
	 
	 private int [][] reset; 
		        
	 public FillModel(int [][] screen) {
		       	this.screen = screen;
		       	 
		       	 //we need a deep copy of a 2d array
		       	 reset= new int [screen.length][]; //this will create the correct number of arrays
		       	 for (int count = 0; count < screen.length; ++count)
		       		 reset[count] = screen[count].clone();
		       		 
		       		        // Row of the display
		       		       m = screen.length;
		       		       
		       		        // Column of the display
		       		       n = screen.length;
		        }
		        
	 //if no array is given create a randomized 10x10 grid
	 public FillModel() {	       	
		        randomScreen(8,8); 
		       	 
		       	 //we need a deep copy of a 2d array
		       	 reset= new int [screen.length][]; //this will create the correct number of arrays
		       	 for (int count = 0; count < screen.length; ++count)
		       		 reset[count] = screen[count].clone();
		       		 
		       		        // Row of the display
		       		       m = screen.length;
		       		       
		       		        // Column of the display
		       		       n = screen.length;
		        }
		        
	 //given the x and y of a position and a fill value mutate the grid
	 public void fill(int x, int y, int value, IFillAlgo algo) {
		        	algo.floodFill(screen, m, n, x, y, screen[x][y], value);
		        }
		        	
	 // basic getter for screen array
	 public int[][] getScreen(){return screen;}
		        
	 //to string override for testing
	 @Override
	 public String toString() {
		        	String result = "";
		        	
		        	for (int[] row : screen)
		        	{
		        		for (int value : row)
		        		{
		        			result += value; 
		        		}
		        		result +="\n"; 
		        	}
		        	
		        	return result;
		        }
		        
    //Resets to the saved screen
    public void reset() {
			       	 //we need a deep copy of a 2d array
			       	 for (int count = 0; count < screen.length; ++count)
			       		 screen[count] = reset[count].clone();
		        }
	
	//Creates a random screen	        
	public void randomScreen(int length, int width) {
		        	Random random = new Random(); 
		        	screen = new int [length][width];
		        	for (int x = 0; x<length; ++x)
		        		for(int y = 0; y<width; ++y)
		        			screen[x][y] = random.nextInt(3);
		        			
		        	
				}
}
