package implementation.commands;

import java.security.InvalidParameterException;

import bridge.transferable.interfaces.CommandInterface;
import bridge.transferable.interfaces.EntityModelInterface;
import bridge.transferable.proxy.EntityViewProxy;


public class EntityModelSwapCommand implements CommandInterface{
	private EntityViewProxy view;
	private EntityModelInterface exModel;
	private EntityModelInterface newModel;
	
	
	public EntityModelSwapCommand(EntityViewProxy view,EntityModelInterface newModel) {
		this.view=view;
		this.exModel=this.view.getModel();
	
	}
	
	
	@Override
	public boolean execute() throws InvalidParameterException {
		view.setModel(newModel);
//		view.update();
		return true;
	}

	@Override
	public void undo() {
		view.setModel(exModel);
//		view.update();
	}

	@Override
	public boolean isValid() {
		return true;
	}
	

}
