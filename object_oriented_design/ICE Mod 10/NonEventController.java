/*
 * NonEvent Based controller best used with command line or file IO
 * 3/30/23
 * -Dr. G
 * */

import java.util.Scanner;

public class NonEventController implements Controller {
	
	IView view;
	FillModel model;
	Scanner scan;
	
	public NonEventController(IView view, FillModel model)
	{
		this.view = view; 
		this.model = model;
		scan = new Scanner(System.in);
	}
	
	public void go() {
		
		int x = 0; 
		int y = 0; 
		int newC = 3;
		char cont;
		
		while (true)
		{
			view.update(model.getScreen());
			System.out.println("Please enter x cord :");
			x = scan.nextInt();
			System.out.println("Please enter y cord :");
			y = scan.nextInt();
			model.fill( x,  y,  newC, new FillAlgo()); 
			view.update(model.getScreen());
			
			System.out.println("Enter Q to quit anything else to continue");
			cont = scan.next().charAt(0);
			if (cont == 'Q' || cont == 'q') break;
		} 
	}

}
