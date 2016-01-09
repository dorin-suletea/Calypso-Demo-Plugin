package engine.views.interfaces;

import java.util.ArrayList;

import bridge.transferable.interfaces.AbstractControlJPanel;


public interface ViewFocusableInterface {
	public void getFocus();
	public void releaseFocus();
	//return the dragable components , for conector the snaps , for entity the entities
	public ArrayList<ViewDragableInterface> getDragableItems();
	//Callback method implemented in conector and entity contexts, recieves delegation
	//from ConnectorView and EntityView 
	public AbstractControlJPanel generateControlPan();
}
