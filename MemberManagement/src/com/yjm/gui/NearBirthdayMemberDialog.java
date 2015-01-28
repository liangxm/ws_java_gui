package com.yjm.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.yjm.model.Member;
/**
 * 艺剪美会员管理系统会员维护界面
 * @author lxm
 * @version 2014-2-24 23:42:13
 */
public class NearBirthdayMemberDialog extends AbstractDialog {

	private static final long serialVersionUID = 5005144403691077848L;
	private JPanel pane;
	private DefaultTableModel dtm;
	private JTable tbl;
	private JScrollPane spane;
	private JButton btnmsearch;

	private List<Member> list;

	public NearBirthdayMemberDialog(JFrame frame) {
		super(frame);
		initComponent();
		this.getContentPane().add(pane,BorderLayout.NORTH);
		this.getContentPane().add(spane,BorderLayout.CENTER);
		this.setTitle("快要过生日的会员");
		this.setBounds(290, 140, 700, 490);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	protected void initComponent() {
		pane = new JPanel();
		dtm = new DefaultTableModel();
		btnmsearch = new JButton("查询");
		tbl = new JTable(dtm);
		spane = new JScrollPane(tbl);
		Font font = new Font("楷体", Font.BOLD, 14);
		
		btnmsearch.setFont(font);
		
		dtm.addColumn("编号");
		dtm.addColumn("手机");
		dtm.addColumn("姓名");
		dtm.addColumn("性别");
		dtm.addColumn("年龄");
		dtm.addColumn("生日");
		dtm.addColumn("住址");
		dtm.addColumn("办卡日期");
		dtm.addColumn("累计消费");
		dtm.addColumn("积分");
		
		tbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// 查询监听器
		btnmsearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearTable();
				list = memberService.selectByBirthday();
				fillTable();
			}
		});

		//公共部分
		pane.add(btnmsearch);
	}

	// 清空表格模型中的数据
	// 通知所有相关控件表格模型中数据的变动(刷新)
	private void clearTable() {
		if(list!=null)
			list.clear();
		dtm.getDataVector().clear();
		dtm.fireTableDataChanged();
	}

	private void fillTable() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (int i = 0; i < list.size(); i++) {
			Member m = list.get(i);
			dtm.addRow(new Object[] { m.getMid(), m.getMphone(), m.getMname(),
					m.getMsex(), m.getMage(), sdf.format(m.getMbirth()),
					m.getMaddr(), sdf.format(m.getMindate()),m.getMtotalmoney(),
					m.getMintegral() });
		}
	}
}
