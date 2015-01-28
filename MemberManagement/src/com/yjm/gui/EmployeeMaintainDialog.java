package com.yjm.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.yjm.model.Employee;
import com.yjm.util.DialogFactory;

/**
 * 员工信息维护界面
 * 
 * @author lxm
 * @version 2014-2-27 22:12:09
 */
public class EmployeeMaintainDialog extends AbstractDialog {

	private static final long serialVersionUID = 1L;

	private JPanel pane;
	private DefaultTableModel dtm;
	private JTable tbl;
	private JRadioButton rbtnmphone;
	private JRadioButton rbtnmname;
	private JTextField txtmtop;
	private JButton btnmsearch;
	private JButton btndelete;
	private JButton btnchange;
	private JScrollPane spane;
	private List<Employee> listem = null;
	private Employee em = null;
	protected int mid2;
	private int tempmid = 0;

	public EmployeeMaintainDialog(JFrame frame) {
		super(frame);
		initComponent();
		this.add(pane,BorderLayout.NORTH);
		this.add(spane,BorderLayout.CENTER);
		this.setTitle("员工信息查询");
		this.setBounds(290, 140, 700, 490);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	protected void initComponent() {
		pane = new JPanel();
		
		rbtnmphone = new JRadioButton("手机");
		rbtnmname = new JRadioButton("姓名");
		txtmtop = new JTextField();
		btnmsearch = new JButton("查询");
		btnchange = new JButton("修改");
		btndelete = new JButton("删除");
		dtm = new DefaultTableModel();
		tbl = new JTable(dtm);
		spane = new JScrollPane(tbl);
		ButtonGroup bg = new ButtonGroup();
		Font font = new Font("楷体", Font.BOLD, 14);
		
		rbtnmphone.setFont(font);
		rbtnmname.setFont(font);
		btnmsearch.setFont(font);
		btnchange.setFont(font);
		btndelete.setFont(font);
		txtmtop.setColumns(10);
		
		bg.add(rbtnmphone);
		bg.add(rbtnmname);

		dtm.addColumn("编号");
		dtm.addColumn("姓名");
		dtm.addColumn("性别");
		dtm.addColumn("年龄");
		dtm.addColumn("手机");
		dtm.addColumn("电话");
		dtm.addColumn("住址");
		dtm.addColumn("生日");
		dtm.addColumn("身份证号");
		dtm.addColumn("入职时间");
		dtm.addColumn("职位");

		tbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		
		tbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int row = tbl.getSelectedRow();
				tempmid = Integer.parseInt(tbl.getValueAt(row, 0).toString());
			}
		});

		// 查询监听器
		btnmsearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearTable();
				if (txtmtop.getText().length() != 0) {
					if (rbtnmname.isSelected()) {
						listem = employeeService.selectByName(txtmtop.getText());
					} else if (rbtnmphone.isSelected()) {
						listem = employeeService.selectByEmPhone(txtmtop.getText());
					}
				}
				if (txtmtop.getText().length() == 0) {
					listem = employeeService.selectAll();
				}
				fillTable();
			}
		});
		// 修改员工开始
		btnchange.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tbl.getSelectedRow();
				if (tempmid != 0 && row != -1) {
					em = employeeService.selectByEmIDReturnEM(tempmid);
					createJDialog(1,em).setVisible(true);
				} else
					JOptionPane.showMessageDialog(null, "请选中要修改的行!", "提示",
							JOptionPane.WARNING_MESSAGE);
			}
		});

		// 删除员工
		btndelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Employee em = employeeService.selectByEmIDReturnEM(tempmid);
				if (em.getManage() != null) {
					if (tempmid != 0) {
						if (JOptionPane
								.showConfirmDialog(null, "您确定要删除该员工信息吗?") == JOptionPane.YES_OPTION) {
							int row = tbl.getSelectedRow();
							employeeService.deleteByEmID(tempmid);
							JOptionPane.showMessageDialog(null, "该员工删除成功！");
							dtm.removeRow(row);
						}
					} else
						JOptionPane.showMessageDialog(null, "请选中要删除的行!", "提示",
								JOptionPane.WARNING_MESSAGE);
				} else
					JOptionPane.showMessageDialog(null,
							"您要删除的员工是管理员,请先删除其管理身份(即将其管理用户名删掉)", "提示",
							JOptionPane.WARNING_MESSAGE);
			}
		});
		
		pane.add(spane);

		// 公共部分
		pane.add(rbtnmname);
		pane.add(rbtnmphone);
		pane.add(txtmtop);
		pane.add(btnmsearch);
		pane.add(btnchange);
		pane.add(btndelete);
	}

	public void fillTable() {
		for (int i = 0; i < listem.size(); i++) {
			Employee em = listem.get(i);
			dtm.addRow(new Object[] { em.getEmid(), em.getEmname(),
					em.getEmsex(), em.getEmage(), em.getEmphone(),
					em.getEmtel(), em.getEmaddr(), em.getEmbirth(),
					em.getEmcard(), em.getEmindate(), em.getEmjob() });
		}
	}

	// 清空表格模型中的数据
	// 通知所有相关控件表格模型中数据的变动(刷新)
	public void clearTable() {
		if(listem!=null)
			listem.clear();
		dtm.getDataVector().clear();
		dtm.fireTableDataChanged();
	}
	
	private JDialog createJDialog(int type,Employee employee){
		return DialogFactory.createDialog(type, this, employee);
	}
}
