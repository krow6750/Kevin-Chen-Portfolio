/*
 * The algorithm used to fill in the space
 * 3/30/23
 * -Dr. G
 * */

import java.awt.Point;
import java.util.Vector;

public class FillAlgo implements IFillAlgo {

	
	// Function that returns true if
	// the given pixel is "valid"
	private boolean isValid(int[][] screen, int m, int n, int x, int y, int prevC, int newC)
	    {
	        if(x < 0 || x >= m || y < 0 || y >= n || screen[x][y] != prevC || screen[x][y]== newC)
	            return false;
	        
		return true;
	    }

	public void floodFill(int[][] screen, int m, int n, int x, int y, int prevC, int newC)
	{
	    Vector<Point> queue = new Vector<Point>();

	    // Queue up a pixel
	    queue.add(new Point(x, y));

	    // Color the pixel with the new color
	    screen[x][y] = newC;

	    // While the queue is not empty i.e. the
	    // Review all pixels in the queue
	    while(queue.size() > 0)
	    {
	        // Dequeue the front pixel
	        Point currPixel = queue.get(queue.size() - 1);
	        queue.remove(queue.size() - 1);

	        int posX = currPixel.x;
	        int posY = currPixel.y;

	        // Check if the adjacent
	        
	        // pixel right
	        if(isValid(screen, m, n, posX + 1, posY, prevC, newC))
	        {
	            // Color with newC
	            // if valid and enqueue
	            screen[posX + 1][posY] = newC;
	            queue.add(new Point(posX + 1, posY));
	        }

	        // pixel left
	        if(isValid(screen, m, n, posX-1, posY, prevC, newC))
	        {
	            screen[posX-1][posY]= newC;
	            queue.add(new Point(posX-1, posY));
	        }

	        // pixel up
	        if(isValid(screen, m, n, posX, posY + 1, prevC, newC))
	        {
	            screen[posX][posY + 1]= newC;
	            queue.add(new Point(posX, posY + 1));
	        }

	        // pixel down
	        if(isValid(screen, m, n, posX, posY-1, prevC, newC))
	        {
	            screen[posX][posY-1]= newC;
	            queue.add(new Point(posX, posY-1));
	        }
	    }
	}
	
}
