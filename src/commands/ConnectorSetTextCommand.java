package commands;

import implementation.PluginImplementation;

import java.security.InvalidParameterException;

import bridge.transferable.interfaces.CommandInterface;
import bridge.transferable.proxy.ConnectorViewProxy;



public class ConnectorSetTextCommand implements CommandInterface{
	private String exStartText;
	private String exMidText;
	private String exEndText;
	private String newStartText;
	private String newMidText;
	private String newEndText;
	private ConnectorViewProxy connView;
	
	
	
	public ConnectorSetTextCommand(String newStartText, String newMidText, String newEndText,ConnectorViewProxy conView) {
		super();
		this.connView=conView;
		
		this.newStartText = newStartText;
		this.newMidText = newMidText;
		this.newEndText = newEndText;
		
		this.exStartText=conView.getStartText();
		this.exMidText=conView.getMidText();
		this.exEndText=conView.getEndText();
	}

	@Override
	public boolean execute() throws InvalidParameterException {
		connView.setStartText(newStartText);
		connView.setMidText(newMidText);
		connView.setEndText(newEndText);
		PluginImplementation.session.triggerSelectPanUpdate();
		return true;
	}

	@Override
	public void undo() {
		connView.setStartText(exStartText);
		connView.setMidText(exMidText);
		connView.setEndText(exEndText);
		PluginImplementation.session.triggerSelectPanUpdate();
	}

	@Override
	public boolean isValid() {
		return true;
	}

}
