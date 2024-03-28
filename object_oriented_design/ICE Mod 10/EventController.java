/*
 * Event based controller
 * 3/30/23
 * -Dr. G
 * */

public class EventController implements Controller{
	
	GUIView view;
	FillModel model;
	
	//Set the model and the view
	public EventController(GUIView view, FillModel model)
	{
		this.view = view;
		this.model = model;
		//You've got to make a connection to pass communication between event controllers
		this.view.setController(this);
	}
	
	//Start up method
	public void go() {
		view.showWindow();
	}
	
	//Load the initial data
	//Left for expansion
	public void callBackLoad() {
		view.update(model.getScreen());
	}
	
	//Take the existing data and transform it then update the view
	public void callBackMutate(int x, int y) {
		model.fill( x,  y,  3, new FillAlgo());		
		view.update(model.getScreen());
	}
	
	public void callBackReset() {
		model.reset();
		view.update(model.getScreen());
	}
	
	public void callBackRandomize() {
		model.randomScreen(8, 8);
		view.update(model.getScreen());
	}
}
