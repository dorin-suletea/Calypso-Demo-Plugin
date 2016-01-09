package commands;

import implementation.PluginImplementation;

import java.security.InvalidParameterException;

import bridge.transferable.interfaces.CommandInterface;
import bridge.transferable.interfaces.DataModelInterface;
import bridge.transferable.proxy.EntityViewProxy;


public class EntityModelEditDataCommand implements CommandInterface {
	private DataModelInterface dataLine;
	private EntityViewProxy viewHost;

	private String newValue;
	private String exValue;
	private int index;

	public EntityModelEditDataCommand(DataModelInterface dataLine,EntityViewProxy viewHost, String newValue, int index) {
		this.dataLine = dataLine;
		this.newValue = newValue;
		this.index = index;
		this.viewHost = viewHost;
		this.exValue = dataLine.getDataLine().get(index);
	}

	@Override
	public boolean execute() throws InvalidParameterException {
		if (!isValid()) {
			return false;
		}
		dataLine.getDataLine().set(index, newValue);
		
		viewHost.update();
		viewHost.getParent().repaint();
		PluginImplementation.session.triggerSelectPanUpdate();
		
		return true;
	}

	@Override
	public void undo() {
		dataLine.getDataLine().set(index, exValue);
		
		viewHost.update();
		viewHost.getParent().repaint();
		PluginImplementation.session.triggerSelectPanUpdate();
	}

	@Override
	public boolean isValid() {
		if (index > dataLine.getDataLine().size() - 1)
			return false;
		if (dataLine.getDataLine().contains(newValue) && dataLine.getDataLine().indexOf(newValue) != index)
			return false;
		if (newValue.length() == 0)
			return false;
		return true;
	}

}
