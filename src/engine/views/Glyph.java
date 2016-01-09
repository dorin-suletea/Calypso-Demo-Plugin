package engine.views;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import engine.views.interfaces.ViewDragableInterface;
import engine.views.interfaces.ViewFocusableInterface;


public abstract class  Glyph extends JPanel implements ViewDragableInterface,ViewFocusableInterface{
	private static final long	serialVersionUID	= 1L;
	
	protected ArrayList<Glyph> nestedGlyphs;
	private boolean isResizable;
	private boolean isNested;
	private boolean hasFocus;
	
	
	public Glyph() {
		this.nestedGlyphs=new ArrayList<Glyph>();
		this.isResizable=true;
	}
	
	public abstract void nest(Glyph nestedGlyph);
	public abstract void deNest(Glyph neGlyph);
	public abstract String getName();
	
	
	
	public boolean isResizable() {
		return isResizable;
	}
	public void setResizable(boolean isResizable) {
		this.isResizable = isResizable;
	}

	public boolean isNested() {
		return isNested;
	}

	public void setNested(boolean isNested) {
		this.isNested = isNested;
	}
	
	public boolean hasNested(Glyph glyph){
		return nestedGlyphs.contains(glyph);
	}

	public boolean hasFocus() {
		return hasFocus;
	}

	public void setFocus(boolean hasFocus) {
		this.hasFocus = hasFocus;
	}
	
	public int nestedListSize(){
		return nestedGlyphs.size();
	}
	
	public ArrayList<Glyph> getGlyphs(){
		return nestedGlyphs;
	}
	public List<Glyph> getAllGlyphs(){
		List<Glyph> ret = new ArrayList<Glyph>();
		ret.addAll(nestedGlyphs);
		for (Glyph g :nestedGlyphs)
			ret.addAll(g.getAllGlyphs());
		return ret;
	}

}
