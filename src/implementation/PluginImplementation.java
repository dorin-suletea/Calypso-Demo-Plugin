package implementation;

import implementation.context.connector.CompositeContext;
import implementation.context.connector.InheritanceContext;
import implementation.context.entity.AbstractClassContext;
import implementation.context.entity.ClassContext;
import implementation.context.entity.EnumContext;
import implementation.context.entity.InterfaceContext;
import implementation.forms.CalypsoPluginToolbar;
import implementation.resources.ResourceRetriever;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JToolBar;

import bridge.transferable.ConnectorContextTransferWraper;
import bridge.transferable.EntityContextTransferWraper;
import bridge.transferable.PluginInterface;
import bridge.transferable.proxy.MainWindowProxy;
import bridge.transferable.proxy.SessionProxy;
import bridge.transferable.proxy.SheetViewProxy;




public class PluginImplementation implements PluginInterface{
	public static SheetViewProxy          sheetView;
	public static MainWindowProxy         mainWindow;
	public static SessionProxy            session;
	
	@Override
	public String getPluginName() {
		return "Test Plugin1";
	}

	@Override
	public String getAuthor() {
		return "Next";
	}

	@Override
	public String getReleaseDate() {
		return "30-Apr-2012";
	}

	@Override
	public long getSignature() {
		return 1;
	}

	@Override
	public List<EntityContextTransferWraper> getEntityContexts() {
		List<EntityContextTransferWraper> ret = new ArrayList<EntityContextTransferWraper>();
		ret.add(new EntityContextTransferWraper(new ClassContext(), ResourceRetriever.loadIcon(ResourceRetriever.CLASS_ICON)));
		ret.add(new EntityContextTransferWraper(new AbstractClassContext(), ResourceRetriever.loadIcon(ResourceRetriever.ABSTRACT_CLASS_ICON)));
		ret.add(new EntityContextTransferWraper(new InterfaceContext(), ResourceRetriever.loadIcon(ResourceRetriever.INTERFACE_ICON)));
		ret.add(new EntityContextTransferWraper(new EnumContext(), ResourceRetriever.loadIcon(ResourceRetriever.ENUM_ICON)));
		return ret;
	} 
	public List<ConnectorContextTransferWraper> getConnectorContexts(){
		List<ConnectorContextTransferWraper> ret = new ArrayList<ConnectorContextTransferWraper>();
		ret.add(new ConnectorContextTransferWraper(new InheritanceContext(), ResourceRetriever.loadIcon(ResourceRetriever.CONNECTOR_EXTENDS_BTN)));
		ret.add(new ConnectorContextTransferWraper(new CompositeContext(), ResourceRetriever.loadIcon(ResourceRetriever.CONNECTOR_AGGREGATES_BTN)));
		return ret;
	}

	@Override
	public void setSheetView(SheetViewProxy associatedSheet){
		sheetView=associatedSheet;
	}

	@Override
	public void setMainWindow(MainWindowProxy assoctiatedMainWindow) {
		mainWindow=assoctiatedMainWindow;
		
	}

	@Override
	public void setSession(SessionProxy associatedSession) {
		session=associatedSession;
	}
	
	public JToolBar getPluginToolbar(){
		return new CalypsoPluginToolbar();
	}
}
