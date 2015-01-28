package com.yjm.gui;

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

import com.yjm.model.Services;
import com.yjm.util.DialogFactory;
/**
 * ������Ŀ�޸ĶԻ���
 * @author lxm
 * @version 2014-2-28 22:34:12
 */
public class ServiceMaintainDialog extends AbstractDialog {
	
	private static final long serialVersionUID = 9170614754418888924L;
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
	private List<Services> listser;
	protected int mid2;
	int tempmid = 0;

	public ServiceMaintainDialog(JFrame frame) {
		super(frame);
		initComponent();
		this.add(pane);
		this.setBounds(290, 140, 700, 490);
		this.setTitle("������Ϣ��ѯ");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	//�����ʼ��
	protected void initComponent(){
		pane = new JPanel();
		pane.setLayout(null);
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
		btnmsearch = new JButton("��ѯ");
		btnmsearch.setFont(new Font("����", Font.BOLD, 14));
		btnmsearch.setBounds(385, 10, 80, 25);

		btnchange = new JButton("�޸�");
		btnchange.setFont(new Font("����", Font.BOLD, 14));
		btnchange.setBounds(480, 10, 80, 25);

		btndelete = new JButton("ɾ��");
		btndelete.setFont(new Font("����", Font.BOLD, 14));
		btndelete.setBounds(575, 10, 80, 25);
		
		dtm = new DefaultTableModel();
		
		dtm.addColumn("���");		
		dtm.addColumn("��Ŀ����");
		dtm.addColumn("��Ŀ�۸�");
		dtm.addColumn("��������");
		
		rbtnmphone.setVisible(false);

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
		spane.setBounds(7, 50, 680, 400);

		pane.add(spane);

		// ��ѯ������
		btnmsearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearTable();
				if (txtmtop.getText().length() != 0) {
					if (rbtnmname.isSelected()) {
						listser = servicesService.selectByName(txtmtop.getText());
					} 
				} else if (txtmtop.getText().length() == 0) {
					listser = servicesService.selectAll();
				}
				fillTable();
			}
		});
		
		//�޸ķ���ʼ
		btnchange.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tempmid != 0) {
					Services s  = servicesService.selectByIDReturnServices(tempmid);
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
				// TODO Auto-generated method stub
				if (tempmid != 0) {
					if (JOptionPane
							.showConfirmDialog(null, "��ȷ��Ҫɾ���÷�����Ϣ��?") == JOptionPane.YES_OPTION) {
						int row = tbl.getSelectedRow();
						int count = consumeService.selectCountByServicesNo(tempmid);
						if(count == 0){
							servicesService.deleteByID(tempmid);
							dtm.removeRow(row);
							JOptionPane.showMessageDialog(null, "�÷���ɾ���ɹ���");
						}else{
							JOptionPane.showMessageDialog(null, "�÷����Ѿ��󶨵���"+count+"�����Ѽ�¼��\n����ɾ��������Ѽ�¼��ɾ��������");
						}
					}
				} else
					JOptionPane.showMessageDialog(null, "��ѡ��Ҫɾ������!", "��ʾ",
							JOptionPane.WARNING_MESSAGE);
			}
		});
		
		//��������
		pane.add(rbtnmname);
		pane.add(rbtnmphone);
		pane.add(txtmtop);
		pane.add(btnmsearch);
		pane.add(btnchange);
		pane.add(btndelete);
	}
	
	public  void fillTable() {
		for (int i = 0; i < listser.size(); i++) {
			Services ser = listser.get(i);
			dtm.addRow(new Object[] {ser.getSerid(),ser.getSername(),ser.getSermoney(),ser.getSertype()});
		}
	}
	
	// ֪ͨ������ؿؼ����ģ�������ݵı䶯(ˢ��)
	public void clearTable() {
		if(listser!=null)
			listser.clear();
		dtm.getDataVector().clear();
		dtm.fireTableDataChanged();
	}
	
	private JDialog createJDialog(int type,Services service){
		return DialogFactory.createDialog(type, this, service);
	}
}
