package com.yjm.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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

import com.yjm.model.Card;
import com.yjm.util.StringUtil;
/**
 * 会员卡信息维护界面
 * @author lxm
 * @version 2014-3-1 11:07:40
 */
public class CardMaintainDialog extends AbstractDialog {

	private static final long serialVersionUID = -6792093065503716976L;

	private JPanel pane;
	private DefaultTableModel dtm;
	private JTable tbl;
	
	private JRadioButton rbtnmphone;
	private JRadioButton rbtnmname;
	
	private JTextField txtmtop;
	private JButton btnmsearch;
	private JButton btntopup;
	private JButton btndelete;
	private JScrollPane spane;
	
	private List<Card> list;
	
	public CardMaintainDialog(JFrame frame){
		super(frame);
		initComponent();
		this.getContentPane().add(pane,BorderLayout.NORTH);
		this.getContentPane().add(spane,BorderLayout.CENTER);
		this.setTitle("会员卡维护");
		this.setBounds(290, 140, 700, 490);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	

	private void clearTable() {
		dtm.getDataVector().clear();
		dtm.fireTableDataChanged();
	}
	
	private void fillTable() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (int i = 0; i < list.size(); i++) {
			Card m = list.get(i);
			dtm.addRow(new Object[] { m.getCardid(), m.getCardnumber(), m.getCardtype(),
					m.getCardowner(), sdf.format(m.getActivedate()), sdf.format(m.getExpirydate()),
					(int)m.getCardbalace()});
		}
	}
	
	private void refresh(){
		clearTable();
		if (txtmtop.getText().length() != 0) {
			list = new ArrayList<Card>(); 
			if (rbtnmname.isSelected()) {
				Card card = cardService.queryByOwner(txtmtop.getText());
				if(card!=null){
					list.add(card);
				}
			} else if (rbtnmphone.isSelected()) {
				Card card = cardService.queryByNum(txtmtop.getText());
				if(card!=null){
					list.add(card);
				}
			}
		}
		else{
			list = cardService.queryAll();
		}
		fillTable();
	}
	
	@Override
	protected void initComponent() {
		pane = new JPanel();
		
		//Initialize Component Object
		rbtnmphone = new JRadioButton("卡号");
		rbtnmname = new JRadioButton("姓名");
		txtmtop = new JTextField();
		btnmsearch = new JButton("查询");
		btntopup = new JButton("充值");
		btndelete = new JButton("删除");
		dtm = new DefaultTableModel();
		tbl = new JTable(dtm);
		spane = new JScrollPane(tbl);
		
		tbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		txtmtop.setColumns(10);
		
		dtm.addColumn("编号");
		dtm.addColumn("卡号");
		dtm.addColumn("卡类型");
		dtm.addColumn("会员");
		dtm.addColumn("激活日期");
		dtm.addColumn("过期日期");
		dtm.addColumn("卡上余额");
		
		btnmsearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				refresh();	
			}
		});
		
		btntopup.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tbl.getSelectedRow();
				if(row==-1){
					JOptionPane.showMessageDialog(null, "请选中需要充值的会员卡", "提示", JOptionPane.WARNING_MESSAGE);
				}else{
					String paidAmount=JOptionPane.showInputDialog(null,"请输入充值金额(为当前选中的会员卡充值)！");
					if(paidAmount!=null){
						int cardid = Integer.parseInt(tbl.getValueAt(row, 0).toString());
						if(StringUtil.isPositiveInteger(paidAmount)){
							cardService.earnCardBalance(cardid, Integer.parseInt(paidAmount));
							JOptionPane.showMessageDialog(null, "充值成功！");
							refresh();
						}
						else{
							JOptionPane.showMessageDialog(null, "请输入正确的金额!", "提示",
									JOptionPane.WARNING_MESSAGE);
						}
					}
				}
			}
		});
		
		btndelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tbl.getSelectedRow();
				if(row==-1){
					JOptionPane.showMessageDialog(null, "请选中要删除的会员卡", "提示", JOptionPane.WARNING_MESSAGE);
				}else{
					int cardid = Integer.parseInt(tbl.getValueAt(row, 0).toString());
					if (JOptionPane.showConfirmDialog(null, "您确定要删除该会员卡信息吗?") == JOptionPane.YES_OPTION) {
						cardService.deleteCard(cardid);
						JOptionPane.showMessageDialog(null, "该会员删除成功！");
						dtm.removeRow(row);
					}
				}
			}
		});
		
		pane.add(rbtnmname);
		pane.add(rbtnmphone);
		pane.add(txtmtop);
		pane.add(btnmsearch);
		pane.add(btntopup);
		pane.add(btndelete);
	}
}
