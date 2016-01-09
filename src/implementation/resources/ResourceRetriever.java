package implementation.resources;

import javax.swing.ImageIcon;

public class ResourceRetriever {
	public static final String CLOSE_BTN_ICON = "images/close_btn_img.png";
	public static final String ADD_BTN_ICON = "images/add_btn_img.png";
	public static final String REM_BTN_ICON = "images/remove_btn_img.png";
	public static final String MOVE_DOWN_BTN_ICON = "images/move_down_btn_img.png";
	public static final String MOVE_UP_BTN_ICON = "images/move_up_btn_img.png";
	
	public static final String GENSTUB_TB_BTN_ICON = "images/generate_stub_toolbar_btn.png";

	
	public static final String CONNECTOR_EXTENDS_BTN = "images/connector_extends_btn.png";
	public static final String CONNECTOR_AGGREGATES_BTN = "images/connector_aggregates_btn.png";
	
	public static final String CLASS_ICON = "images/class_icon.png";
	public static final String ABSTRACT_CLASS_ICON = "images/enum_icon.png";
	public static final String INTERFACE_ICON = "images/interface_icon.png";
	public static final String ENUM_ICON = "images/abstract_class_icon.png";
	
	
	

	
	public static ImageIcon loadIcon(String resLocation){
		return new ImageIcon(ResourceRetriever.class.getResource(resLocation));
	}
}
