package commands;

import java.security.InvalidParameterException;

import bridge.transferable.interfaces.CommandInterface;
import bridge.transferable.proxy.EntityViewProxy;


public class EntityModelChangeName implements CommandInterface{
	private String newName;
	private String exName;
	private EntityViewProxy viewHost;
	

	public EntityModelChangeName(String newName, EntityViewProxy viewHost) {
		super();
		this.newName = newName;
		this.exName = viewHost.getModel().getName();
		this.viewHost = viewHost;
	}

	@Override
	public boolean execute() throws InvalidParameterException {
		viewHost.getModel().setName(newName);
		viewHost.update();
		viewHost.getParent().repaint();
		return true;
	}

	@Override
	public void undo() {
		viewHost.getModel().setName(exName);
		viewHost.update();
		viewHost.getParent().repaint();
	}

	@Override
	public boolean isValid() {
		if (newName.length()>0)
			return true;
		return false;
	}

}
