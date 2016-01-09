package implementation.context.connector;

import implementation.forms.CompositionConnectorContextUIPan;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import bridge.transferable.context.ConnectorContextInterface;
import bridge.transferable.interfaces.AbstractControlJPanel;
import bridge.transferable.proxy.ConnectorViewProxy;


public class CompositeContext implements ConnectorContextInterface{

	@Override
	public String getDrawnType() {
		return "Composition";
	}

	@Override
	public boolean isLabeled() {
		return true;
	}

	@Override
	public void drawEndArrow(Graphics g, ArrayList<Point> points) {
	}

	@Override
	public void drawStartArrow(Graphics g, ArrayList<Point> points) {
		Color gCol = g.getColor();
		g.setColor(Color.WHITE);
		g.fillPolygon(new int[]{points.get(1).x,points.get(3).x,points.get(7).x,points.get(5).x},
					  new int[]{points.get(1).y,points.get(3).y,points.get(7).y,points.get(5).y}, 
					  4);
		g.setColor(gCol);
		g.drawLine(points.get(1).x, points.get(1).y, points.get(3).x, points.get(3).y);
		g.drawLine(points.get(1).x, points.get(1).y, points.get(5).x, points.get(5).y);
		g.drawLine(points.get(5).x, points.get(5).y, points.get(7).x, points.get(7).y);
		g.drawLine(points.get(3).x, points.get(3).y, points.get(7).x, points.get(7).y);	
	}

	@Override
	public void drawLine(Graphics g, ArrayList<Point> snaps, Point start, Point end) {
		if (snaps.isEmpty())
			g.drawLine(start.x, start.y, end.x, end.y);
		else {
			// draw simple line
			g.drawLine(start.x, start.y, snaps.get(0).x, snaps.get(0).y);
			g.drawLine(end.x, end.y, snaps.get(snaps.size() - 1).x, snaps.get(snaps.size() - 1).y);
			for (int i = 0; i < snaps.size() - 1; i++)
				g.drawLine(snaps.get(i).x, snaps.get(i).y, snaps.get(i + 1).x, snaps.get(i + 1).y);
		}
	}

	@Override
	public AbstractControlJPanel generateUiPan(ConnectorViewProxy connector) {
		return new CompositionConnectorContextUIPan(connector);

	}

}
