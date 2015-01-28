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
 * ��Ա����Ϣά������
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
		this.setTitle("��Ա��ά��");
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
		rbtnmphone = new JRadioButton("����");
		rbtnmname = new JRadioButton("����");
		txtmtop = new JTextField();
		btnmsearch = new JButton("��ѯ");
		btntopup = new JButton("��ֵ");
		btndelete = new JButton("ɾ��");
		dtm = new DefaultTableModel();
		tbl = new JTable(dtm);
		spane = new JScrollPane(tbl);
		
		tbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		txtmtop.setColumns(10);
		
		dtm.addColumn("���");
		dtm.addColumn("����");
		dtm.addColumn("������");
		dtm.addColumn("��Ա");
		dtm.addColumn("��������");
		dtm.addColumn("��������");
		dtm.addColumn("�������");
		
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
					JOptionPane.showMessageDialog(null, "��ѡ����Ҫ��ֵ�Ļ�Ա��", "��ʾ", JOptionPane.WARNING_MESSAGE);
				}else{
					String paidAmount=JOptionPane.showInputDialog(null,"�������ֵ���(Ϊ��ǰѡ�еĻ�Ա����ֵ)��");
					if(paidAmount!=null){
						int cardid = Integer.parseInt(tbl.getValueAt(row, 0).toString());
						if(StringUtil.isPositiveInteger(paidAmount)){
							cardService.earnCardBalance(cardid, Integer.parseInt(paidAmount));
							JOptionPane.showMessageDialog(null, "��ֵ�ɹ���");
							refresh();
						}
						else{
							JOptionPane.showMessageDialog(null, "��������ȷ�Ľ��!", "��ʾ",
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
					JOptionPane.showMessageDialog(null, "��ѡ��Ҫɾ���Ļ�Ա��", "��ʾ", JOptionPane.WARNING_MESSAGE);
				}else{
					int cardid = Integer.parseInt(tbl.getValueAt(row, 0).toString());
					if (JOptionPane.showConfirmDialog(null, "��ȷ��Ҫɾ���û�Ա����Ϣ��?") == JOptionPane.YES_OPTION) {
						cardService.deleteCard(cardid);
						JOptionPane.showMessageDialog(null, "�û�Աɾ���ɹ���");
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
