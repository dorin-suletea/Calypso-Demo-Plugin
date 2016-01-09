package implementation.forms;

import implementation.resources.ResourceRetriever;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JToolBar;

public class CalypsoPluginToolbar extends JToolBar {
	private static final long serialVersionUID = 1L;
	public static final Dimension TOOLBAR_BTN_DIM = new Dimension(30, 30);

	private JButton generateCodeBtn;
	private JButton alignTopBtn;
	private JButton alignBotBtn;
	private JButton alignRightBtn;
	private JButton alignLeftBtn;
	private JButton alignHCenterBtn;
	private JButton alignVCenterBtn;

	public CalypsoPluginToolbar() {
		addComponents();
		addListeners();
		setAlignmentX(0);
	}

	private void addComponents() {
		generateCodeBtn = new JButton(ResourceRetriever.loadIcon(ResourceRetriever.GENSTUB_TB_BTN_ICON));
		generateCodeBtn.setSize(TOOLBAR_BTN_DIM);
		add(generateCodeBtn);

		// alignTopBtn = new JButton("top");
		// alignTopBtn.setSize(TOOLBAR_BTN_DIM);
		// add(alignTopBtn);
		//
		// alignBotBtn = new JButton("bot");
		// alignBotBtn.setSize(TOOLBAR_BTN_DIM);
		// add(alignBotBtn);
		//
		// alignRightBtn = new JButton("right");
		// alignRightBtn.setSize(TOOLBAR_BTN_DIM);
		// add(alignRightBtn);
		//
		// alignLeftBtn = new JButton("left");
		// alignLeftBtn.setSize(TOOLBAR_BTN_DIM);
		// add(alignLeftBtn);
		//
		// alignHCenterBtn = new JButton("HCenter");
		// alignHCenterBtn.setSize(TOOLBAR_BTN_DIM);
		// add(alignHCenterBtn);
		//
		// alignVCenterBtn = new JButton("Vcenter");
		// alignVCenterBtn.setSize(TOOLBAR_BTN_DIM);
		// add(alignVCenterBtn);
	}

	private void addListeners() {

		// generateCodeBtn.addActionListener(new ActionListener() {
		//
		// @Override
		// public void actionPerformed(ActionEvent arg0) {
		// try {
		// for (String s :
		// JavaParser.getInstance().parseRoutine(PluginImplementation.sheetView.getModel()))
		// {
		// System.out.println("-----");
		// System.out.println(s);
		// }
		// } catch (ParseException e) {
		// e.printStackTrace();
		// }
		// }
		// });
		// alignTopBtn.addActionListener(new ActionListener() {
		//
		// @Override
		// public void actionPerformed(ActionEvent arg0) {
		// System.out.println("Align top command");
		// CommandInterface top = new
		// AlignEntitiesCommand(AlignEntitiesCommand.ALIGN_TOP,
		// PluginImplementation.session);
		// if (top.execute())
		// PluginImplementation.session.getSelectedSheetProxy().addCommand(top);
		// }
		// });
		// alignBotBtn.addActionListener(new ActionListener() {
		//
		// @Override
		// public void actionPerformed(ActionEvent arg0) {
		// System.out.println("Align bot command");
		// CommandInterface bot = new
		// AlignEntitiesCommand(AlignEntitiesCommand.ALIGN_BOT,
		// PluginImplementation.session);
		// if (bot.execute())
		// PluginImplementation.session.getSelectedSheetProxy().addCommand(bot);
		// }
		// });
		//
		// alignRightBtn.addActionListener(new ActionListener() {
		//
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// System.out.println("Align right command");
		// CommandInterface right = new
		// AlignEntitiesCommand(AlignEntitiesCommand.ALIGN_RIGHT,
		// PluginImplementation.session);
		// if (right.execute())
		// PluginImplementation.session.getSelectedSheetProxy().addCommand(right);
		// }
		// });
		// alignLeftBtn.addActionListener(new ActionListener() {
		//
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// System.out.println("Align left command");
		// CommandInterface left = new
		// AlignEntitiesCommand(AlignEntitiesCommand.ALIGN_LEFT,
		// PluginImplementation.session);
		// if (left.execute())
		// PluginImplementation.session.getSelectedSheetProxy().addCommand(left);
		// }
		// });
		// alignHCenterBtn.addActionListener(new ActionListener() {
		//
		// @Override
		// public void actionPerformed(ActionEvent arg0) {
		// System.out.println("Align horizontal center  command");
		// CommandInterface hCenter = new
		// AlignEntitiesCommand(AlignEntitiesCommand.ALIGN_HORIZONTAL_CENTER,
		// PluginImplementation.session);
		// if (hCenter.execute())
		// PluginImplementation.session.getSelectedSheetProxy().addCommand(hCenter);
		// }
		// });
		// alignVCenterBtn.addActionListener(new ActionListener() {
		//
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// System.out.println("Align vertical center  command");
		// CommandInterface vCenter = new
		// AlignEntitiesCommand(AlignEntitiesCommand.ALIGN_VERTICAL_CENTER,
		// PluginImplementation.session);
		// if (vCenter.execute())
		// PluginImplementation.session.getSelectedSheetProxy().addCommand(vCenter);
		// }
		// });
	}
}
