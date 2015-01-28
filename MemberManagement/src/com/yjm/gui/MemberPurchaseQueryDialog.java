package com.yjm.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.yjm.model.Consume;
/**
 * 会员消费查询对话框
 * @author lxm
 * @version 2014-2-28 23:02:52
 */
public class MemberPurchaseQueryDialog extends AbstractDialog {

	private static final long serialVersionUID = -4662760232726180936L;

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
	int tempmid = 0;

	public MemberPurchaseQueryDialog(JFrame frame) {
		super(frame);
		initComponent();
		this.add(pane,BorderLayout.NORTH);
		this.add(spane,BorderLayout.CENTER);
		this.setTitle("会员消费记录查询");
		this.setBounds(290, 140, 700, 490);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	//组件初始化
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
		btnmsearch = new JButton("查询");
		btnmsearch.setFont(new Font("楷体", Font.BOLD, 14));
		btnmsearch.setBounds(385, 10, 80, 25);

		btnchange = new JButton("修改");
		btnchange.setFont(new Font("楷体", Font.BOLD, 14));
		btnchange.setBounds(480, 10, 80, 25);

		btndelete = new JButton("删除");
		btndelete.setFont(new Font("楷体", Font.BOLD, 14));
		btndelete.setBounds(575, 10, 80, 25);
	
		btnmsearch.setBounds(307, 10, 80, 25);
		rbtnmphone.setVisible(false);
		rbtnmname.setVisible(false);
		txtmtop.setVisible(false);
		btnchange.setVisible(false);
		btndelete.setVisible(false);
		
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

		spane = new JScrollPane(tbl);
		spane.setBounds(7, 50, 680, 400);

		pane.add(spane);

		// 查询消费记录监听器
		btnmsearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearTable();
				listcon = consumeService.selectNoMember();
				fillTable();				
			}
		});
	
		//公共部分
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
