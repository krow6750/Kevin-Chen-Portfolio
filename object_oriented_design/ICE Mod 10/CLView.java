/*
 * Simple Command line view implementation
 * 3/30/23
 * -Dr. G
 * */
import java.util.Scanner;

public class CLView implements IView {
	  final Appendable out;
	  Scanner scan;
		  
	  public CLView() {
		  this.out=System.out;
	  }
	  
	public void update(int [][] screen) {
		
		String result = "";
    	
    	for (int[] row : screen)
    	{
    		for (int value : row)
    		{
    			result += value; 
    		}
    		result +="\n"; 
    	}
    	
    	System.out.println(result + "\n");
	}
	
}
