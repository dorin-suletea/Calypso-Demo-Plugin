package implementation.context.entity;

import implementation.forms.ClassContextUIPan;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import bridge.transferable.context.EntityContextInterface;
import bridge.transferable.interfaces.AbstractControlJPanel;
import bridge.transferable.interfaces.DataModelInterface;
import bridge.transferable.interfaces.EntityModelInterface;
import bridge.transferable.interfaces.GlyphLocationInterface;
import bridge.transferable.proxy.EntityViewProxy;


public class ClassContext implements EntityContextInterface {
	private final int TEXT_H_PAD = 5;
	private final int DATASHETT_H_PAD = 20;
	private final int LINE_W_PAD = 15;
	private final int TEXT_LEFT_PAD = 10;
	/**
	 * stores the temporary y at witch the draw occurs
	 */
	private int auxCurentHlocation = 0;

	@Override
	public String getDrawnType() {
		return "Class";
	}

	@Override
	public ArrayList<String> getDataRowIdentifiers() {
		ArrayList<String> ret = new ArrayList<String>();
		ret.add("Fields");
		ret.add("Methods");
		return ret;
	}

	@Override
	public Dimension getDefaultSize() {
		return new Dimension(100, 100);
	}

	@Override
	public void drawShape(Graphics g, int width, int height) {
		g.drawRoundRect(5, 5, width - 10, height - 10, 10, 10);
	}

	@Override
	public void drawData(Graphics g, int width, int height, List<DataModelInterface> data) {
		int textH = g.getFontMetrics(g.getFont()).getHeight();
		for (DataModelInterface dataRow : data) {
			auxCurentHlocation += DATASHETT_H_PAD;

			g.drawString(dataRow.getIdentifier(), LINE_W_PAD * 2, auxCurentHlocation);
			g.drawLine(LINE_W_PAD, auxCurentHlocation - textH / 2, LINE_W_PAD * 2 - 3, auxCurentHlocation - textH / 2);
			g.drawLine(LINE_W_PAD * 2 + g.getFontMetrics(g.getFont()).stringWidth(dataRow.getIdentifier()) + 3, auxCurentHlocation - textH / 2, width - LINE_W_PAD, auxCurentHlocation - textH / 2);
			auxCurentHlocation += DATASHETT_H_PAD;
			for (String s : dataRow.getDataLine()) {
				g.drawString(s, TEXT_LEFT_PAD, auxCurentHlocation);
				auxCurentHlocation += TEXT_H_PAD + textH;
			}
		}

	}

	@Override
	public void drawIdentifiers(Graphics g, int width, int height, String name, String type) {
		FontMetrics fm = g.getFontMetrics(g.getFont());
		auxCurentHlocation = 0;

		auxCurentHlocation += DATASHETT_H_PAD;
		g.drawString("<" + type + ">", width / 2 - fm.stringWidth("<" + type + ">") / 2, auxCurentHlocation);

		auxCurentHlocation += TEXT_H_PAD + fm.getHeight();
		g.drawString(name, width / 2 - fm.stringWidth(name) / 2, auxCurentHlocation);

		auxCurentHlocation += TEXT_H_PAD;
		g.drawLine(LINE_W_PAD, auxCurentHlocation, width - LINE_W_PAD, auxCurentHlocation);

	}

	@Override
	public void setNestedPositions(List<GlyphLocationInterface> nested, GlyphLocationInterface hostView) {
		int x = 0;
		int y = 0;
		int i = 0;
		for (GlyphLocationInterface n : nested) {
			n.setBounds(x, y, 100, 100);
			x += 100;
			i++;
			if (i == 4) {
				i = 0;
				x = 0;
				y += 100;
			}
		}
		if (y > 0)
			x = 400;
		hostView.setSize(hostView.getMinimumSize().width + x, hostView.getMinimumSize().height + y);
		for (Component n : ((JPanel) hostView).getComponents())
			n.setBounds(n.getBounds().x, hostView.getBounds().height - n.getBounds().y - n.getBounds().height, 100, 100);
	}

	@Override
	public AbstractControlJPanel generateControlPannel(EntityViewProxy entity) {
		return new ClassContextUIPan(entity);
	}
	
	@Override
	public Dimension getMinimumSize(EntityModelInterface entityModel, FontMetrics fontMetrics) {
		List<String> drawnStrings = new ArrayList<String>();

		int width = 0;
		int height = 0;
		int stringW = 0;

		height += DATASHETT_H_PAD * 2 + TEXT_H_PAD + fontMetrics.getHeight() * 2;

		width = fontMetrics.stringWidth(entityModel.getType());
		stringW = fontMetrics.stringWidth(entityModel.getName());
		if (stringW > width)
			width = stringW;

		drawnStrings.add(entityModel.getName());
		for (DataModelInterface dataRow : entityModel.getDataRows()) {
			height += DATASHETT_H_PAD;
			for (String s : dataRow.getDataLine()) {
				height += fontMetrics.getHeight() + TEXT_H_PAD;
				stringW = fontMetrics.stringWidth(s);
				if (stringW > width)
					width = stringW;
			}
		}
		width += 20;// border insets

		return new Dimension(width, height);
	}
}
