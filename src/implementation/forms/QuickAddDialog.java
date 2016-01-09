package implementation.forms;

import implementation.PluginImplementation;
import implementation.resources.ResourceRetriever;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import commands.EntityModelAddDataCommand;

import bridge.transferable.interfaces.CommandInterface;
import bridge.transferable.interfaces.DataModelInterface;
import bridge.transferable.proxy.EntityViewProxy;




public class QuickAddDialog extends JDialog{
	private static final long serialVersionUID = 1L;
	public 	static final int QUICK_DIAG_W=200;
	public 	static final int QUICK_DIAG_H=60;
	
	private final int CLOSE_BTN_W=20;
	private final int CLOSE_BTN_H=20;
	
	private final int PAD_V=50;
	private final int PAD_H=10;
	
	private final int DATA_TF_W=120;
	private final int DATA_TF_H=24;
	
	private final int CONTROL_BTN_W=80;
	private final int CONTROL_BTN_H=16;
	
	private JButton     closeBtn;
	private JButton     okBtn;
	private JButton     cancelBtn;
	
	private JTextField  dataRowTf;
	
	public QuickAddDialog(String caption) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(QUICK_DIAG_W, QUICK_DIAG_H);
		setModal(true);
		setTitle(caption);
		setUndecorated(true);
		addComponents();
	}
	
	private void addComponents(){
		JPanel contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBorder(BorderFactory.createLineBorder(Color.black));
		getContentPane().add(contentPane);
		
		closeBtn = new JButton(ResourceRetriever.loadIcon(ResourceRetriever.CLOSE_BTN_ICON));
		closeBtn.setBounds(QUICK_DIAG_W-CLOSE_BTN_W, 0 ,CLOSE_BTN_W, CLOSE_BTN_H);
		contentPane.add(closeBtn);
		
		dataRowTf = new JTextField();
		dataRowTf.setText("New value");
		dataRowTf.setBounds(PAD_V, PAD_H, DATA_TF_W, DATA_TF_H);
		contentPane.add(dataRowTf);
		
		okBtn = new JButton("Ok");
		okBtn.setBounds(dataRowTf.getX()-35, dataRowTf.getY()+dataRowTf.getHeight()+5, CONTROL_BTN_W, CONTROL_BTN_H);
		contentPane.add(okBtn);
		
		cancelBtn = new JButton("Cancel");
		cancelBtn.setBounds(dataRowTf.getX()+dataRowTf.getWidth()+35-CONTROL_BTN_W, okBtn.getY(), CONTROL_BTN_W, CONTROL_BTN_H);
		contentPane.add(cancelBtn);
	}

	public class QuickAddDialogController{
		private DataModelInterface       dataSheet;
		private EntityViewProxy entity;
		
		public QuickAddDialogController(DataModelInterface dataSheet, EntityViewProxy entity) {
			this.entity=entity;
			this.dataSheet=dataSheet;
			addListeners();
		}
		
		private void addListeners(){
			closeBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					onCancel();
				}
			});
			cancelBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					onCancel();
				}
			});
			
			okBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					onOkAction();
				}
			});
			dataRowTf.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					onOkAction();
					
				}
			});
		}
		
		protected void onOkAction(){
			String toAdd = dataRowTf.getText();
			CommandInterface cmd = new EntityModelAddDataCommand(dataSheet,toAdd,entity);
			if (cmd.execute()){
				PluginImplementation.sheetView.addCommand(cmd);
				QuickAddDialog.this.dispose();
			}
			else
				JOptionPane.showMessageDialog(null, "Value is duplicated or is empty", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		protected void onCancel(){
			QuickAddDialog.this.dispose();
		}
	}
}
