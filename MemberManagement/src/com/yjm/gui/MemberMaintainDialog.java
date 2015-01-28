package com.yjm.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
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

import com.yjm.model.Member;
import com.yjm.util.DialogFactory;
/**
 * 艺剪美会员管理系统会员维护界面
 * @author lxm
 * @version 2014-2-24 23:42:13
 */
public class MemberMaintainDialog extends AbstractDialog {

	private static final long serialVersionUID = 5005144403691077848L;
	private JPanel pane;
	private DefaultTableModel dtm;
	private JTable tbl;
	private JScrollPane spane;
	private JTextField txtmtop;
	private JButton btnmsearch, btndelete, btnchange;
	private JRadioButton rbtnmphone, rbtnmname;

	private List<Member> list;
	private int tempmid;

	public MemberMaintainDialog(JFrame frame) {
		super(frame);
		initComponent();
		this.getContentPane().add(pane,BorderLayout.NORTH);
		this.getContentPane().add(spane,BorderLayout.CENTER);
		this.setTitle("会员信息维护");
		this.setBounds(290, 140, 700, 490);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	protected void initComponent() {
		pane = new JPanel();
		dtm = new DefaultTableModel();
		rbtnmphone = new JRadioButton("手机");
		rbtnmname = new JRadioButton("姓名");
		txtmtop = new JTextField();
		btnmsearch = new JButton("查询");
		btnchange = new JButton("修改");
		btndelete = new JButton("删除");
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
						list = memberService.selectByName(txtmtop.getText());
						fillTable();
					} else if (rbtnmphone.isSelected()) {
						list = memberService.selectByPhone(txtmtop.getText());
						fillTable();
					}
				}
				//搜索文本为空，就查询所有
				else  {
					list = memberService.selectAll();
					fillTable();
				}
			}
		});

		// 修改监听器
		btnchange.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tbl.getSelectedRow();
				if (tempmid != 0&&row != -1) {
					Member m = memberService.selectBymid(tempmid);
					createJDialog(1,m).setVisible(true);
				} else
					JOptionPane.showMessageDialog(null, "请选中要修改的行!", "提示",
							JOptionPane.WARNING_MESSAGE);
			}
		});

		// 删除会员
		btndelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tbl.getSelectedRow();
				if (tempmid != 0&&row != -1) {
					if (JOptionPane.showConfirmDialog(null, "您确定要删除该会员信息吗?") == JOptionPane.YES_OPTION) {
						memberService.deleteByID(tempmid);
						JOptionPane.showMessageDialog(null, "该会员删除成功！");
						dtm.removeRow(row);
					}
				} else
					JOptionPane.showMessageDialog(null, "请选中要删除的行!", "提示",
							JOptionPane.WARNING_MESSAGE);
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
	
	private JDialog createJDialog(int type,Member member){
		return DialogFactory.createDialog(type, this, member);
	}
}
