package commands;

import implementation.PluginImplementation;

import java.security.InvalidParameterException;

import bridge.transferable.interfaces.CommandInterface;
import bridge.transferable.interfaces.DataModelInterface;
import bridge.transferable.proxy.EntityViewProxy;


public class EntityModelRemoveDataCommand implements CommandInterface {
	private DataModelInterface dataLine;
	private EntityViewProxy viewHost;

	private int index;
	private String value;

	public EntityModelRemoveDataCommand(EntityViewProxy viewHost,DataModelInterface dataLine, int index) {
		super();
		this.dataLine = dataLine;
		this.viewHost = viewHost;
		this.index = index;
		//value will be set in exec (after the isValid() test is passed)
	}

	@Override
	public boolean execute() throws InvalidParameterException {
		if (!isValid())
			return false;
		this.value=dataLine.getDataLine().get(index);
		dataLine.getDataLine().remove(index);

		viewHost.update();
		viewHost.getParent().repaint();
		PluginImplementation.session.triggerSelectPanUpdate();
		
		return true;
	}

	@Override
	public void undo() {
		dataLine.getDataLine().add(index, value);

		viewHost.update();
		viewHost.getParent().repaint();
		PluginImplementation.session.triggerSelectPanUpdate();

	}

	@Override
	public boolean isValid() {
		if (index < 0 || index > dataLine.getDataLine().size() - 1)
			return false;
		return true;
	}

}
