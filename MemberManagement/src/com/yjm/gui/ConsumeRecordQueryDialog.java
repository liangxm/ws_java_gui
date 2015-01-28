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
 * ���Ѽ�¼��ѯ�Ի���
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
		this.setTitle("���Ѽ�¼��Ϣ��ѯ");
		this.setBounds(290, 140, 700, 490);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	protected void initComponent(){
		pane = new JPanel();
		rbtnmphone = new JRadioButton("�ֻ�");
		rbtnmphone.setBounds(40, 10, 70, 25);
		rbtnmphone.setFont(new Font("����", Font.BOLD, 14));

		rbtnmname = new JRadioButton("����");
		rbtnmname.setBounds(115, 10, 70, 25);
		rbtnmname.setFont(new Font("����", Font.BOLD, 14));
		ButtonGroup bg = new ButtonGroup();
		bg.add(rbtnmphone);
		bg.add(rbtnmname);
		txtmtop = new JTextField();
		txtmtop.setBounds(200, 10, 160, 25);
		txtmtop.setColumns(10);
		btnmsearch = new JButton("��ѯ");
		btnmsearch.setFont(new Font("����", Font.BOLD, 14));
		btnmsearch.setBounds(385, 10, 80, 25);

		btnchange = new JButton("�޸�");
		btnchange.setFont(new Font("����", Font.BOLD, 14));
		btnchange.setBounds(480, 10, 80, 25);

		btndelete = new JButton("ɾ��");
		btndelete.setFont(new Font("����", Font.BOLD, 14));
		btndelete.setBounds(575, 10, 80, 25);
	
		//--��ѯ���Ѽ�¼��ʼ
		rbtnmphone.setText("��Ա�ֻ���");
		rbtnmname.setText("Ա������");
		
		rbtnmphone.setBounds(20, 10, 110, 25);
		rbtnmname.setBounds(130, 10, 120, 25);
		txtmtop.setBounds(232, 10, 130, 25);
		
		dtm = new DefaultTableModel();
		
		dtm.addColumn("���ѱ��");		
		dtm.addColumn("��Ա���");
		dtm.addColumn("Ա�����");
		dtm.addColumn("������");
		dtm.addColumn("��������");
		dtm.addColumn("�ۿ�");
		dtm.addColumn("���");
		dtm.addColumn("��ע");

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

		// ��ѯ���Ѽ�¼������
		btnmsearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtmtop.getText().length() != 0) {
					clearTable();//����Ա��ID��ѯ
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
		
		//�޸����Ѽ�¼��ʼ
		btnchange.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Ŀǰϵͳ�������޸����Ѽ�¼!", "��ʾ",
						JOptionPane.WARNING_MESSAGE);
//				if (tempmid != 0) {
//					Consume s  = consumeService.selectByConIDReturnConsume(tempmid);
//					createJDialog(1,s).setVisible(true);
//				} else
//					JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�޸ĵ���!", "��ʾ",
//							JOptionPane.WARNING_MESSAGE);
			}
		});
		
		// ɾ�����Ѽ�¼
		btndelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "ֻ�г�������Ա����ɾ�����Ѽ�¼\n����ɾ�����Ӱ����֧ƽ��", "��ʾ", JOptionPane.WARNING_MESSAGE);
				int row = tbl.getSelectedRow();
				if (tempmid != 0 && row != -1) {
					if (JOptionPane
							.showConfirmDialog(null, "��ȷ��Ҫɾ�������Ѽ�¼��Ϣ��?") == JOptionPane.YES_OPTION) {
						consumeService.deleteByID(tempmid);
						JOptionPane.showMessageDialog(null, "�����Ѽ�¼ɾ���ɹ���");
						dtm.removeRow(row);
					}
				} else{
					JOptionPane.showMessageDialog(null, "��ѡ��Ҫɾ������Ϣ!", "��ʾ",
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

	// ��ձ��ģ���е�����
	// ֪ͨ������ؿؼ����ģ�������ݵı䶯(ˢ��)
	public void clearTable() {
		dtm.getDataVector().clear();
		dtm.fireTableDataChanged();
	}

}
