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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.yjm.model.Consume;
/**
 * 消费记录查询对话框
 * @author lxm
 * @version 2014-3-1 13:08:27
 */
public class ConsumeRecordQueryDialog extends AbstractDialog {

	private static final long serialVersionUID = -6776136963161539184L;
	
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
	private List<Consume> listcon = null;
	protected int mid2;
	private int tempmid = 0;

	public ConsumeRecordQueryDialog(JFrame frame) {
		super(frame);
		initComponent();
		this.add(pane,BorderLayout.NORTH);
		this.add(spane,BorderLayout.CENTER);
		this.setTitle("消费记录信息查询");
		this.setBounds(290, 140, 700, 490);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	protected void initComponent(){
		pane = new JPanel();
		rbtnmphone = new JRadioButton("手机");
		rbtnmphone.setBounds(40, 10, 70, 25);
		rbtnmphone.setFont(new Font("楷体", Font.BOLD, 14));

		rbtnmname = new JRadioButton("姓名");
		rbtnmname.setBounds(115, 10, 70, 25);
		rbtnmname.setFont(new Font("楷体", Font.BOLD, 14));
		ButtonGroup bg = new ButtonGroup();
		bg.add(rbtnmphone);
		bg.add(rbtnmname);
		txtmtop = new JTextField();
		txtmtop.setBounds(200, 10, 160, 25);
		txtmtop.setColumns(10);
		btnmsearch = new JButton("查询");
		btnmsearch.setFont(new Font("楷体", Font.BOLD, 14));
		btnmsearch.setBounds(385, 10, 80, 25);

		btnchange = new JButton("修改");
		btnchange.setFont(new Font("楷体", Font.BOLD, 14));
		btnchange.setBounds(480, 10, 80, 25);

		btndelete = new JButton("删除");
		btndelete.setFont(new Font("楷体", Font.BOLD, 14));
		btndelete.setBounds(575, 10, 80, 25);
	
		//--查询消费记录开始
		rbtnmphone.setText("会员手机号");
		rbtnmname.setText("员工姓名");
		
		rbtnmphone.setBounds(20, 10, 110, 25);
		rbtnmname.setBounds(130, 10, 120, 25);
		txtmtop.setBounds(232, 10, 130, 25);
		
		dtm = new DefaultTableModel();
		
		dtm.addColumn("消费编号");		
		dtm.addColumn("会员编号");
		dtm.addColumn("员工编号");
		dtm.addColumn("服务编号");
		dtm.addColumn("消费日期");
		dtm.addColumn("折扣");
		dtm.addColumn("金额");
		dtm.addColumn("备注");

		tbl = new JTable(dtm);
		tbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int row = tbl.getSelectedRow();
				tempmid = Integer.parseInt(tbl.getValueAt(row, 0).toString());
			}
		});

		spane = new JScrollPane(tbl);
		pane.add(spane);

		// 查询消费记录监听器
		btnmsearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtmtop.getText().length() != 0) {
					clearTable();//按照员工ID查询
					if (rbtnmname.isSelected()) {
						listcon = consumeService.selectByConEmName(txtmtop.getText());
					} else if (rbtnmphone.isSelected()) {
						listcon = consumeService.selectByPhone(txtmtop.getText());
					}
					fillTable();
				}

				if (txtmtop.getText().length() == 0) {
					clearTable();
					listcon = consumeService.selectAll();
					fillTable();
				}
			}
		});
		
		//修改消费记录开始
		btnchange.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "目前系统不允许修改消费记录!", "提示",
						JOptionPane.WARNING_MESSAGE);
//				if (tempmid != 0) {
//					Consume s  = consumeService.selectByConIDReturnConsume(tempmid);
//					createJDialog(1,s).setVisible(true);
//				} else
//					JOptionPane.showMessageDialog(null, "请选中要修改的行!", "提示",
//							JOptionPane.WARNING_MESSAGE);
			}
		});
		
		// 删除消费记录
		btndelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "只有超级管理员才能删除消费记录\n！且删除后会影响收支平衡", "提示", JOptionPane.WARNING_MESSAGE);
				int row = tbl.getSelectedRow();
				if (tempmid != 0 && row != -1) {
					if (JOptionPane
							.showConfirmDialog(null, "您确定要删除该消费记录信息吗?") == JOptionPane.YES_OPTION) {
						consumeService.deleteByID(tempmid);
						JOptionPane.showMessageDialog(null, "该消费记录删除成功！");
						dtm.removeRow(row);
					}
				} else{
					JOptionPane.showMessageDialog(null, "请选中要删除的信息!", "提示",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
	
		pane.add(rbtnmname);
		pane.add(rbtnmphone);
		pane.add(txtmtop);
		pane.add(btnmsearch);
		pane.add(btnchange);
		pane.add(btndelete);
	}
	
	public  void fillTable() {
		for (int i = 0; i < listcon.size(); i++) {
			Consume con = listcon.get(i);
			dtm.addRow(new Object[] {con.getConid(),con.getConmphone(),con.getConemid(),
					con.getConserid(),con.getCondate(),con.getConagio(),con.getConmoney(),con.getConremark()});
		}
	}

	// 清空表格模型中的数据
	// 通知所有相关控件表格模型中数据的变动(刷新)
	public void clearTable() {
		dtm.getDataVector().clear();
		dtm.fireTableDataChanged();
	}

}
