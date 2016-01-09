package commands;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import bridge.transferable.interfaces.CommandInterface;
import bridge.transferable.proxy.EntityViewProxy;
import engine.views.interfaces.ViewDragableInterface;

public class RelocateCommand implements CommandInterface {
	private List<ViewDragableInterface> viewReprezentations;

	private int dx;
	private int dy;

	public RelocateCommand(Set<ViewDragableInterface> viewReprezentations, int dx, int dy) {
		super();
		this.viewReprezentations = new ArrayList<ViewDragableInterface>(viewReprezentations);
		this.dx = dx;
		this.dy = dy;

	}

	public RelocateCommand(ViewDragableInterface viewReprezentation, int dx, int dy) {
		this.viewReprezentations = new ArrayList<ViewDragableInterface>();
		viewReprezentations.add(viewReprezentation);
		this.dx = dx;
		this.dy = dy;
	}
	
	public RelocateCommand(EntityViewProxy viewReprezentation, int dx, int dy) {
		this.viewReprezentations = new ArrayList<ViewDragableInterface>();
		viewReprezentations.add(viewReprezentation);
		this.dx = dx;
		this.dy = dy;
	}

	public boolean execute() {
		if (!isValid()) 
			return false;


		for (ViewDragableInterface v : viewReprezentations) {
			v.setLocation(new Point(v.getX() + dx, v.getY() + dy));
			v.update();
		}
		return true;
	}

	@Override
	public void undo() {
		for (ViewDragableInterface v : viewReprezentations) {
			v.setLocation(new Point(v.getX() - dx, v.getY() - dy));
			v.update();
		}
	}

	@Override
	public boolean isValid() {
		for (ViewDragableInterface v : viewReprezentations) {
			if (!v.validNewPosition(dx, dy))
				return false;
		}
		return true;
	}

}
