package implementation.forms;

import implementation.PluginImplementation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import commands.ConnectorSetTextCommand;

import bridge.transferable.interfaces.CommandInterface;
import bridge.transferable.proxy.ConnectorViewProxy;



public class PredefinedConnectorContextUIPan extends JPanel{
	private static final long serialVersionUID = 1L;
	private final int PAD_H = 10;
	private final int PAD_V = 10;
	
	private final int TF_W = 120;
	private final int TF_H = 24;
	
	private final int PAD_V_LBL_COMP = 10;
	private final int PAD_H_LBL_COMP = 0;
	
	private final int TA_W = 140;
	private final int TA_H = 60;
	
	private final int UPDATE_BTN_W=20;
	
	
	private JTextField typeTf;
	private JTextField fromEntityTf;
	private JTextField toEntityTf;
	
	private JTextArea startTextTa;
	private JTextArea midTextTa;
	private JTextArea endTextTa;
	
	private JButton   updateBtn;
	
	public PredefinedConnectorContextUIPan(ConnectorViewProxy connector) {
		addComponents();
		initComponents(connector);
		addListeners(connector);
	}

	private void addComponents(){
		setLayout(null);
		JLabel typeLbl = new JLabel("Type");
		typeLbl.setBounds(PAD_V, PAD_H, TF_W, TF_H);
		add(typeLbl);
		
		typeTf = new JTextField();
		typeTf.setBounds(typeLbl.getX() + PAD_V_LBL_COMP, typeLbl.getY() + typeLbl.getHeight() + PAD_H_LBL_COMP, TF_W, TF_H);
		typeTf.setEditable(false);
		add(typeTf);
		
		JLabel fromEntityLbl = new JLabel("From");
		fromEntityLbl.setBounds(typeLbl.getX(), typeTf.getY() + typeTf.getHeight() + PAD_V, TF_W, TF_H);
		add(fromEntityLbl);
		
		fromEntityTf = new JTextField();
		fromEntityTf.setBounds(fromEntityLbl.getX() + PAD_V_LBL_COMP, fromEntityLbl.getY() + fromEntityLbl.getHeight() + PAD_H_LBL_COMP, TF_W, TF_H);
		fromEntityTf.setEditable(false);
		add(fromEntityTf);
		
		JLabel toEntityLbl = new JLabel("To");
		toEntityLbl.setBounds(fromEntityTf.getX()+fromEntityTf.getWidth(), fromEntityLbl.getY(), TF_W, TF_H);
		add(toEntityLbl);
		
		toEntityTf = new JTextField();
		toEntityTf.setBounds(toEntityLbl.getX() + PAD_V_LBL_COMP, fromEntityTf.getY() , TF_W, TF_H);
		toEntityTf.setEditable(false);
		add(toEntityTf);
		
		JLabel startTextLbl = new JLabel("Start Text");
		startTextLbl.setBounds(PAD_V, fromEntityTf.getY()+fromEntityTf.getHeight()+PAD_H, TF_W, TF_H);
		add(startTextLbl);
		
		startTextTa = new JTextArea();
		startTextTa.setLineWrap(true);
		JScrollPane startTaSP = new JScrollPane(startTextTa);
		startTaSP.setBounds(startTextLbl.getX()+PAD_V_LBL_COMP, startTextLbl.getY()+startTextLbl.getHeight()+PAD_H_LBL_COMP, TA_W, TA_H);
		add(startTaSP);
		
		JLabel midTextLbl = new JLabel("Mid Text");
		midTextLbl.setBounds(PAD_V, startTaSP.getY()+startTaSP.getHeight()+PAD_H, TF_W, TF_H);
		add(midTextLbl);
		
		midTextTa = new JTextArea();
		midTextTa.setLineWrap(true);
		JScrollPane midTaSP = new JScrollPane(midTextTa);
		midTaSP.setBounds(midTextLbl.getX()+PAD_V_LBL_COMP, midTextLbl.getY()+midTextLbl.getHeight()+PAD_H_LBL_COMP, TA_W, TA_H);
		add(midTaSP);
		
		JLabel endTextLbl = new JLabel("End Text");
		endTextLbl.setBounds(PAD_V, midTaSP.getY()+midTaSP.getHeight()+PAD_H, TF_W, TF_H);
		add(endTextLbl);
		
		endTextTa = new JTextArea();
		startTextTa.setLineWrap(true);
		JScrollPane endTaSP = new JScrollPane(endTextTa);
		endTaSP.setBounds(endTextLbl.getX()+PAD_V_LBL_COMP, endTextLbl.getY()+endTextLbl.getHeight()+PAD_H_LBL_COMP, TA_W, TA_H);
		add(endTaSP);
		
		updateBtn = new JButton();
		updateBtn.setLocation(startTaSP.getX()+startTaSP.getWidth()+5,startTaSP.getY()+startTaSP.getHeight()/2);
		updateBtn.setSize(UPDATE_BTN_W, endTaSP.getY()+endTaSP.getHeight()/2-updateBtn.getY());
		add(updateBtn);
		
	}
	
	private void initComponents(ConnectorViewProxy connector){
		typeTf.setText(connector.getType());
		fromEntityTf.setText(connector.getSourceEntityName());
		toEntityTf.setText(connector.getEndEntityName());
		startTextTa.setText(connector.getStartText());
		midTextTa.setText(connector.getMidText());
		endTextTa.setText(connector.getEndText());
	}
	
	private void addListeners(final ConnectorViewProxy connector){
		updateBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CommandInterface cmd = new ConnectorSetTextCommand(startTextTa.getText(), midTextTa.getText(), endTextTa.getText(), connector);
				if (cmd.execute())
					PluginImplementation.sheetView.addCommand(cmd);
			}
		});
	}
}
