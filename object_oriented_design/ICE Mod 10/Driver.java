/*
 * Simple Driver to test two different controllers and views
 * 3/30/23
 * -Dr. G
 * */

public class Driver {

	public static void main(String[] args) {

		//This would be better loading in from a file, but for now I'll use a constant
		  int[][] screen = Screens.screen1;
			
		  	//Model used for storing and mutating the representation matrix
		  	FillModel m1 = new FillModel(screen); 
		  	//Command line view
		  	IView v1 = new CLView();
		  	
		  	//Graphical View
		  	GUIView v2 = new GUIView();
		  	
		  	//A non event based controller
			Controller c1 = new NonEventController(v1,m1);
			//An event based controller
			Controller c2 = new EventController(v2, new FillModel(screen));
			
			//c1.go();
			c2.go();
	}
	
	

}
