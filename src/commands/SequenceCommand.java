package commands;

import java.security.InvalidParameterException;
import java.util.ArrayList;

import bridge.transferable.interfaces.CommandInterface;


public class SequenceCommand implements CommandInterface{
	private ArrayList<CommandInterface> sequence;
	
	
	
	
	public SequenceCommand(CommandInterface ...commands) {
		sequence=new ArrayList<CommandInterface>();
		for (int i=0;i<commands.length;i++)
			sequence.add(commands[i]);
	}
	
	
	@SuppressWarnings("unchecked")
	public SequenceCommand(ArrayList<CommandInterface> commands) {
		sequence=(ArrayList<CommandInterface>) commands.clone();

	}

	@Override
	public boolean execute() throws InvalidParameterException {
		if (!isValid())
			return false;
		for (CommandInterface c : sequence)
			c.execute();
		return true;
	}

	@Override
	public void undo() {
		for (CommandInterface c : sequence)
			c.undo();
		
	}


	@Override
	public boolean isValid() {
		for (CommandInterface c : sequence)
			if (!c.isValid())
				return false;
		return true;
	}

}
