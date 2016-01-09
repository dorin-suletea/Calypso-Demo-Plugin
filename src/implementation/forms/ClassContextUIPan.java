package implementation.forms;

import implementation.PluginImplementation;
import implementation.commands.EntityModelMoveDownDataCommand;
import implementation.commands.EntityModelMoveUpDataCommand;
import implementation.resources.ResourceRetriever;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import commands.EntityModelChangeName;
import commands.EntityModelEditDataCommand;
import commands.EntityModelRemoveDataCommand;

import bridge.transferable.interfaces.AbstractControlJPanel;
import bridge.transferable.interfaces.CommandInterface;
import bridge.transferable.proxy.EntityViewProxy;



public class ClassContextUIPan extends AbstractControlJPanel {
	private static final long serialVersionUID = 1L;
	private final int PAD_H = 10;
	private final int PAD_V = 20;
	private final int TF_W = 120;
	private final int TF_H = 24;
	private final int PAD_V_LBL_COMP = 10;
	private final int PAD_H_LBL_COMP = 0;
	private final int DATA_TABLE_PAN_W = 120;
	private final int DATA_TABLE_PAN_H = 200;
	private final int DATA_TABLE_CTR_PAN_W = 30;
	private final int DATA_TABLE_CTR_PAN_H = 200;
	private final int BTN_W = 20;
	private final int BTN_H = 20;
	private final int PAD_BTN = 5;

	private JTextField typeTf;
	private JTextField nameTf;
	private JTable fieldJTable;
	private JScrollPane fieldJTableSp;
	private JPanel fieldControlJPan;
	private JTable methodJTable;
	private JScrollPane methodJTableSp;
	private JPanel methodControlJPan;
	private JButton upFieldBtn;
	private JButton downFieldBtn;
	private JButton addFieldBtn;
	private JButton remFieldBtn;
	private JButton upMethdBtn;
	private JButton downMethdBtn;
	private JButton addMethBtn;
	private JButton remMethBtn;
	
	private int fieldJTableSelection;
	private int methJTableSelection;
	
	private EntityViewProxy entity;

	public ClassContextUIPan(EntityViewProxy entity) {
		this.entity = entity;
		setPreferredSize(new Dimension((DATA_TABLE_PAN_W) + PAD_V * 3, 600));
		fieldJTableSelection = -1;
		methJTableSelection = -1;
		addComponents();
		initComponents();
		addListeners();
	}

	public int getFieldJTableSelection() {
		return fieldJTableSelection;
	}

	public void setFieldJTableSelection(int fieldJTableSelection) {
		this.fieldJTableSelection = fieldJTableSelection;
	}

	public int getMethJTableSelection() {
		return methJTableSelection;
	}

	public void setMethJTableSelection(int methJTableSelection) {
		this.methJTableSelection = methJTableSelection;
	}

	private void addComponents() {
		setLayout(null);

		JLabel typeLbl = new JLabel("Type");
		typeLbl.setBounds(PAD_V, PAD_H, TF_W, TF_H);
		add(typeLbl);

		typeTf = new JTextField();
		typeTf.setEditable(false);
		typeTf.setBounds(typeLbl.getX() + PAD_V_LBL_COMP, typeLbl.getY() + typeLbl.getHeight() + PAD_H_LBL_COMP, TF_W, TF_H);
		add(typeTf);

		JLabel nameLbl = new JLabel("Name");
		nameLbl.setBounds(typeLbl.getX(), typeTf.getY() + typeTf.getHeight() + PAD_V, TF_W, TF_H);
		add(nameLbl);

		nameTf = new JTextField();
		nameTf.setBounds(nameLbl.getX() + PAD_V_LBL_COMP, nameLbl.getY() + nameLbl.getHeight() + PAD_H_LBL_COMP, TF_W, TF_H);
		add(nameTf);

		fieldJTable = new JTable(new DefaultTableModel()) {
			private static final long serialVersionUID = 1L;

			@Override
			public void setValueAt(Object aValue, int row, int column) {
				if (aValue.equals(fieldJTable.getValueAt(fieldJTable.getEditingRow(), 0)))
					return;

				super.setValueAt(aValue, row, column);
				CommandInterface cmd = new EntityModelEditDataCommand(entity.getModel().getDataRows().get(0), entity, aValue.toString(), row);
				if (cmd.execute())
					PluginImplementation.sheetView.addCommand(cmd);
				else {
					PluginImplementation.session.postErrorMessage("Value is duplicated or is empty");
					initComponents();
				}
			}
		};
		fieldJTable.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
		fieldJTable.setRowSelectionAllowed(true);
		fieldJTableSp = new JScrollPane(fieldJTable);
		fieldJTableSp.setBounds(nameLbl.getX(), nameTf.getY() + nameTf.getHeight() + PAD_H, DATA_TABLE_PAN_W, DATA_TABLE_PAN_H);
		add(fieldJTableSp);

		fieldControlJPan = new JPanel();
		fieldControlJPan.setLayout(null);
		fieldControlJPan.setBounds(fieldJTableSp.getX() + fieldJTableSp.getWidth(), fieldJTableSp.getY(), DATA_TABLE_CTR_PAN_W, DATA_TABLE_CTR_PAN_H);
		add(fieldControlJPan);

		methodJTable = new JTable(new DefaultTableModel()) {
			private static final long serialVersionUID = 1L;

			@Override
			public void setValueAt(Object aValue, int row, int column) {
				if (aValue.equals(methodJTable.getValueAt(methodJTable.getEditingRow(), 0)))
					return;

				super.setValueAt(aValue, row, column);
				CommandInterface cmd = new EntityModelEditDataCommand(entity.getModel().getDataRows().get(1), entity, aValue.toString(), row);
				if (cmd.execute())
					PluginImplementation.sheetView.addCommand(cmd);
				else {
					PluginImplementation.session.postErrorMessage("Value is duplicated or is empty");
					initComponents();
				}
			}
		};
		methodJTable.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);

		methodJTableSp = new JScrollPane(methodJTable);
		methodJTableSp.setBounds(fieldJTableSp.getX(), fieldJTableSp.getY()+fieldJTableSp.getHeight()+PAD_H, DATA_TABLE_PAN_W, DATA_TABLE_PAN_H);
		add(methodJTableSp);

		methodControlJPan = new JPanel();
		methodControlJPan.setLayout(null);
		methodControlJPan.setBounds(methodJTableSp.getX() + methodJTableSp.getWidth(), methodJTableSp.getY(), DATA_TABLE_CTR_PAN_W, DATA_TABLE_CTR_PAN_H);
		add(methodControlJPan);

		upFieldBtn = new JButton(ResourceRetriever.loadIcon(ResourceRetriever.MOVE_UP_BTN_ICON));
		upFieldBtn.setBounds(PAD_BTN, PAD_BTN, BTN_W, BTN_H);
		fieldControlJPan.add(upFieldBtn);

		downFieldBtn = new JButton(ResourceRetriever.loadIcon(ResourceRetriever.MOVE_DOWN_BTN_ICON));
		downFieldBtn.setBounds(upFieldBtn.getX(), upFieldBtn.getY() + upFieldBtn.getHeight() + PAD_BTN, BTN_W, BTN_H);
		fieldControlJPan.add(downFieldBtn);

		addFieldBtn = new JButton(ResourceRetriever.loadIcon(ResourceRetriever.ADD_BTN_ICON));
		addFieldBtn.setBounds(downFieldBtn.getX(), downFieldBtn.getY() + downFieldBtn.getHeight() + PAD_BTN, BTN_W, BTN_H);
		fieldControlJPan.add(addFieldBtn);

		remFieldBtn = new JButton(ResourceRetriever.loadIcon(ResourceRetriever.REM_BTN_ICON));
		remFieldBtn.setBounds(addFieldBtn.getX(), addFieldBtn.getY() + addFieldBtn.getHeight() + PAD_BTN, BTN_W, BTN_H);
		fieldControlJPan.add(remFieldBtn);

		upMethdBtn = new JButton(ResourceRetriever.loadIcon(ResourceRetriever.MOVE_UP_BTN_ICON));
		upMethdBtn.setBounds(PAD_BTN, PAD_BTN, BTN_W, BTN_H);
		methodControlJPan.add(upMethdBtn);

		downMethdBtn = new JButton(ResourceRetriever.loadIcon(ResourceRetriever.MOVE_DOWN_BTN_ICON));
		downMethdBtn.setBounds(upMethdBtn.getX(), upMethdBtn.getY() + upMethdBtn.getHeight() + PAD_BTN, BTN_W, BTN_H);
		methodControlJPan.add(downMethdBtn);

		addMethBtn = new JButton(ResourceRetriever.loadIcon(ResourceRetriever.ADD_BTN_ICON));
		addMethBtn.setBounds(downMethdBtn.getX(), downMethdBtn.getY() + downMethdBtn.getHeight() + PAD_BTN, BTN_W, BTN_H);
		methodControlJPan.add(addMethBtn);

		remMethBtn = new JButton(ResourceRetriever.loadIcon(ResourceRetriever.REM_BTN_ICON));
		remMethBtn.setBounds(addMethBtn.getX(), addMethBtn.getY() + addMethBtn.getHeight() + PAD_BTN, BTN_W, BTN_H);
		methodControlJPan.add(remMethBtn);

	}

	public void initComponents() {
		typeTf.setText(entity.getModel().getType());
		nameTf.setText(entity.getModel().getName());
		
		
		((DefaultTableModel) fieldJTable.getModel()).setColumnCount(0);
		((DefaultTableModel) fieldJTable.getModel()).setRowCount(0);
		((DefaultTableModel) fieldJTable.getModel()).addColumn(entity.getModel().getDataRows().get(0).getIdentifier());
		DefaultTableModel fieldTabModel = ((DefaultTableModel) fieldJTable.getModel());
		for (String s : entity.getModel().getDataRows().get(0).getDataLine())
			fieldTabModel.addRow(new String[] { s });

		((DefaultTableModel) methodJTable.getModel()).setColumnCount(0);
		((DefaultTableModel) methodJTable.getModel()).setRowCount(0);
		((DefaultTableModel) methodJTable.getModel()).addColumn(entity.getModel().getDataRows().get(1).getIdentifier());
		DefaultTableModel methodTabModel = ((DefaultTableModel) methodJTable.getModel());
		for (String s : entity.getModel().getDataRows().get(1).getDataLine())
			methodTabModel.addRow(new String[] { s });

		if (fieldJTableSelection != -1) {
			fieldJTable.clearSelection();
			fieldJTable.setColumnSelectionInterval(0, 0);
			fieldJTable.setRowSelectionInterval(fieldJTableSelection, fieldJTableSelection);
		}

		if (methJTableSelection != -1) {
			methodJTable.clearSelection();
			methodJTable.setColumnSelectionInterval(0, 0);
			methodJTable.setRowSelectionInterval(methJTableSelection, methJTableSelection);
		}
	}

	public void addListeners() {
		nameTf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//this sequence will trigger loose focus command so no new command is needed 
				//because it's auto executed when the component looses focus
				nameTf.setFocusable(false);
				nameTf.setFocusable(true);
			}
		});

		nameTf.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				super.focusLost(e);
				if (!nameTf.getText().equals(entity.getModel().getName())) {
					
					if (nameTf.getText().isEmpty()){
						PluginImplementation.session.postErrorMessage("Value can not be empty");
						return;
					}
					
					CommandInterface cmd = new EntityModelChangeName(nameTf.getText(), entity);
					if (cmd.execute()){
						PluginImplementation.sheetView.addCommand(cmd);
					}
				}
			};
		});

		addFieldBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				QuickAddDialog qd = new QuickAddDialog("Add me");
				qd.new QuickAddDialogController(entity.getModel().getDataRows().get(0), entity);
				Point loc = addFieldBtn.getLocation();
				SwingUtilities.convertPointToScreen(loc, addFieldBtn.getParent());
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

				if (loc.x + QuickAddDialog.QUICK_DIAG_W > screenSize.width)
					loc.x = screenSize.width - QuickAddDialog.QUICK_DIAG_W - 5;
				if (loc.y + QuickAddDialog.QUICK_DIAG_H > screenSize.height)
					loc.y = screenSize.height - QuickAddDialog.QUICK_DIAG_H - 5;

				qd.setLocation(loc);
				qd.setVisible(true);
			}
		});

		upFieldBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fieldJTableSelection = fieldJTable.getSelectedRow();

				CommandInterface cmd = new EntityModelMoveUpDataCommand(entity, entity.getModel().getDataRows().get(0), fieldJTableSelection);
				fieldJTable.getSelectedRow();
				if (cmd.execute())
					PluginImplementation.sheetView.addCommand(cmd);

				if (fieldJTableSelection > 0)
					fieldJTableSelection--;

				fieldJTable.clearSelection();
				fieldJTable.addColumnSelectionInterval(0, 0);
				fieldJTable.addRowSelectionInterval(fieldJTableSelection, fieldJTableSelection);
			}
		});
		downFieldBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				fieldJTableSelection = fieldJTable.getSelectedRow();

				CommandInterface cmd = new EntityModelMoveDownDataCommand(entity, entity.getModel().getDataRows().get(0), fieldJTable.getSelectedRow());
				if (cmd.execute())
					PluginImplementation.sheetView.addCommand(cmd);

				if (fieldJTableSelection < fieldJTable.getModel().getRowCount()-1)
					fieldJTableSelection++;

				fieldJTable.clearSelection();
				fieldJTable.addColumnSelectionInterval(0, 0);
				fieldJTable.addRowSelectionInterval(fieldJTableSelection, fieldJTableSelection);

			}
		});
		remFieldBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CommandInterface cmd = new EntityModelRemoveDataCommand(entity, entity.getModel().getDataRows().get(0), fieldJTable.getSelectedRow());
				if (cmd.execute()) {
					PluginImplementation.sheetView.addCommand(cmd);
				}
			}
		});

		addMethBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				QuickAddDialog qd = new QuickAddDialog("Add me");
				qd.new QuickAddDialogController(entity.getModel().getDataRows().get(1), entity);
				Point loc = addFieldBtn.getLocation();
				SwingUtilities.convertPointToScreen(loc, addFieldBtn.getParent());
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

				if (loc.x + QuickAddDialog.QUICK_DIAG_W > screenSize.width)
					loc.x = screenSize.width - QuickAddDialog.QUICK_DIAG_W - 5;
				if (loc.y + QuickAddDialog.QUICK_DIAG_H > screenSize.height)
					loc.y = screenSize.height - QuickAddDialog.QUICK_DIAG_H - 5;

				qd.setLocation(loc);
				qd.setVisible(true);
			}
		});

		upMethdBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				methJTableSelection = methodJTable.getSelectedRow();

				CommandInterface cmd = new EntityModelMoveUpDataCommand(entity, entity.getModel().getDataRows().get(1), methodJTable.getSelectedRow());
				if (cmd.execute())
					PluginImplementation.sheetView.addCommand(cmd);

				if (methJTableSelection > 0)
					methJTableSelection--;

				methodJTable.clearSelection();
				methodJTable.addColumnSelectionInterval(0, 0);
				methodJTable.addRowSelectionInterval(methJTableSelection, methJTableSelection);

			}
		});

		downMethdBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				methJTableSelection = methodJTable.getSelectedRow();
				
				CommandInterface cmd = new EntityModelMoveDownDataCommand(entity, entity.getModel().getDataRows().get(1), methodJTable.getSelectedRow());
				if (cmd.execute())
					PluginImplementation.sheetView.addCommand(cmd);

				if (methJTableSelection < methodJTable.getModel().getRowCount())
					methJTableSelection++;

				methodJTable.clearSelection();
				methodJTable.addColumnSelectionInterval(0, 0);
				methodJTable.addRowSelectionInterval(methJTableSelection, methJTableSelection);

			}
		});

		remMethBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CommandInterface cmd = new EntityModelRemoveDataCommand(entity, entity.getModel().getDataRows().get(1), methodJTable.getSelectedRow());
				if (cmd.execute())
					PluginImplementation.sheetView.addCommand(cmd);
			}
		});
	}

	@Override
	public void cleanup() {
		if (fieldJTable.isEditing())
			fieldJTable.getDefaultEditor(Object.class).stopCellEditing();
		if (methodJTable.isEditing())
			methodJTable.getDefaultEditor(Object.class).stopCellEditing();
		
		if (nameTf.getText().isEmpty()){
			PluginImplementation.session.postErrorMessage("Value can not be empty");
		}else{
			entity.getModel().setName(nameTf.getText());
		}
	}

	@Override
	public Object getModelObject() {
		return entity.getModel();
	}

}
