package implementation.commands;

import implementation.PluginImplementation;

import java.security.InvalidParameterException;

import bridge.transferable.interfaces.CommandInterface;
import bridge.transferable.interfaces.DataModelInterface;
import bridge.transferable.proxy.EntityViewProxy;


public class EntityModelMoveUpDataCommand implements CommandInterface {
	private DataModelInterface dataLine;
	private EntityViewProxy viewHost;

	private int index;

	public EntityModelMoveUpDataCommand(EntityViewProxy viewHost, DataModelInterface dataLine, int index) {
		super();
		this.viewHost = viewHost;
		this.index = index;
		this.dataLine = dataLine;
	}

	@Override
	public boolean execute() throws InvalidParameterException {
		if (isValid()) {
			String toMove = dataLine.getDataLine().get(index);
			dataLine.getDataLine().set(index, dataLine.getDataLine().get(index - 1));
			dataLine.getDataLine().set(index - 1, toMove);

			
			viewHost.update();
			viewHost.getParent().repaint();
			PluginImplementation.session.triggerSelectPanUpdate();

			return true;
		}
		return false;
	}

	@Override
	public void undo() {
		String toMove = dataLine.getDataLine().get(index - 1);
		dataLine.getDataLine().set(index - 1, dataLine.getDataLine().get(index));
		dataLine.getDataLine().set(index, toMove);

		viewHost.update();
		viewHost.getParent().repaint();
		PluginImplementation.session.triggerSelectPanUpdate();

	}

	@Override
	public boolean isValid() {
		if (index < 1)
			return false;
		return true;
	}

}
