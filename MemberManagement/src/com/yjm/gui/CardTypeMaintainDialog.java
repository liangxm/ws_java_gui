package com.yjm.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.yjm.model.CardType;
import com.yjm.util.DialogFactory;
/**
 * ������Ŀ�޸ĶԻ���
 * @author lxm
 * @version 2014-2-28 22:34:12
 */
public class CardTypeMaintainDialog extends AbstractDialog {
	
	private static final long serialVersionUID = 9170614754418888924L;
	private JPanel pane;
	private DefaultTableModel dtm;
	private JTable tbl;
	private JButton btnmsearch;
	private JButton btndelete;
	private JButton btnchange;
	private JScrollPane spane;
	private List<CardType> listser = null;
	protected int mid2;

	public CardTypeMaintainDialog(JFrame frame) {
		super(frame);
		initComponent();
		this.add(pane,BorderLayout.NORTH);
		this.add(spane,BorderLayout.CENTER);
		this.setBounds(290, 140, 700, 490);
		this.setTitle("��Ա������ά��");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	//�����ʼ��
	protected void initComponent(){
		pane = new JPanel();

		btnmsearch = new JButton("��ѯ");
		btnchange = new JButton("�޸�");
		btndelete = new JButton("ɾ��");
		
		dtm = new DefaultTableModel();
		
		btnmsearch.setFont(new Font("����", Font.BOLD, 14));
		btnchange.setFont(new Font("����", Font.BOLD, 14));
		btndelete.setFont(new Font("����", Font.BOLD, 14));
		
		
		dtm.addColumn("���");		
		dtm.addColumn("��������");
		dtm.addColumn("�����ۿ�");
		dtm.addColumn("Ԥ�����");
		
		tbl = new JTable(dtm);
		spane = new JScrollPane(tbl);
		tbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// ��ѯ������
		btnmsearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearTable();
				fillTable();
			}
		});
		
		//�޸ķ���ʼ
		btnchange.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tbl.getSelectedRow();
				if (row != -1) {
					int typeid = Integer.parseInt(tbl.getValueAt(row, 0).toString());
					CardType s  = cardTypeService.queryByID(typeid);
					createJDialog(1,s).setVisible(true);
					clearTable();
					fillTable();
				} else{
					JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�޸ĵ���!", "��ʾ",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		// ɾ������
		btndelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tbl.getSelectedRow();
				if (row != -1) {
					int typeid = Integer.parseInt(tbl.getValueAt(row, 0).toString());
					if (JOptionPane
							.showConfirmDialog(null, "��ȷ��Ҫɾ���ÿ�������?") == JOptionPane.YES_OPTION) {
						if(cardTypeService.deleteCardType(typeid)){
							dtm.removeRow(row);
							JOptionPane.showMessageDialog(null, "������ɾ���ɹ���");
						}else{
							JOptionPane.showMessageDialog(null, "������ɾ��ʧ�ܣ�");
						}
					}
				} else
					JOptionPane.showMessageDialog(null, "��ѡ��Ҫɾ������!", "��ʾ",
							JOptionPane.WARNING_MESSAGE);
			}
		});
	
		//��������
		pane.add(btnmsearch);
		pane.add(btnchange);
		pane.add(btndelete);
	}
	
	private void fillTable() {
		listser = cardTypeService.queryAll();
		for (int i = 0; i < listser.size(); i++) {
			CardType cardType = listser.get(i);
			dtm.addRow(new Object[] {cardType.getTypeid(),cardType.getTypename(),cardType.getTypediscount(),cardType.getTypestartamt()});
		}
	}
	
	// ��ձ��ģ���е�����
	// ֪ͨ������ؿؼ����ģ�������ݵı䶯(ˢ��)
	private void clearTable() {
		dtm.getDataVector().clear();
		dtm.fireTableDataChanged();
	}
	
	private JDialog createJDialog(int type,CardType cardType){
		return DialogFactory.createDialog(type, this, cardType);
	}
}
