package commands;

import implementation.PluginImplementation;

import java.security.InvalidParameterException;

import bridge.transferable.interfaces.CommandInterface;
import bridge.transferable.interfaces.DataModelInterface;
import bridge.transferable.proxy.EntityViewProxy;


public class EntityModelAddDataCommand implements CommandInterface {
	private DataModelInterface dataLine;
	private EntityViewProxy viewHost;

	private String newValue;

	public EntityModelAddDataCommand(DataModelInterface dataLine,String newValue, EntityViewProxy viewHost) {
		this.dataLine = dataLine;
		this.newValue = newValue;
		this.viewHost = viewHost;
	}

	@Override
	public boolean execute() throws InvalidParameterException {
		boolean ret = false;
		if (isValid()) {
			ret = dataLine.getDataLine().add(newValue);

	

		}
		
		PluginImplementation.session.triggerSelectPanUpdate();
	
		viewHost.update();
		viewHost.getParent().repaint();
		return ret;

	}

	@Override
	public void undo() {
		if (!dataLine.getDataLine().contains(newValue))
			return;
		dataLine.getDataLine().remove(newValue);

		PluginImplementation.session.triggerSelectPanUpdate();
		viewHost.update();
		viewHost.getParent().repaint();
	}

	@Override
	public boolean isValid() {
		if (dataLine.getDataLine().contains(newValue))
			return false;
		if (newValue.length() == 0)
			return false;
		return true;
	}

}
