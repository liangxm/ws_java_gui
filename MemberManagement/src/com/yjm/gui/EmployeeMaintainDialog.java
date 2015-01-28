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
 * Ա����Ϣά������
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
		this.setTitle("Ա����Ϣ��ѯ");
		this.setBounds(290, 140, 700, 490);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	protected void initComponent() {
		pane = new JPanel();
		
		rbtnmphone = new JRadioButton("�ֻ�");
		rbtnmname = new JRadioButton("����");
		txtmtop = new JTextField();
		btnmsearch = new JButton("��ѯ");
		btnchange = new JButton("�޸�");
		btndelete = new JButton("ɾ��");
		dtm = new DefaultTableModel();
		tbl = new JTable(dtm);
		spane = new JScrollPane(tbl);
		ButtonGroup bg = new ButtonGroup();
		Font font = new Font("����", Font.BOLD, 14);
		
		rbtnmphone.setFont(font);
		rbtnmname.setFont(font);
		btnmsearch.setFont(font);
		btnchange.setFont(font);
		btndelete.setFont(font);
		txtmtop.setColumns(10);
		
		bg.add(rbtnmphone);
		bg.add(rbtnmname);

		dtm.addColumn("���");
		dtm.addColumn("����");
		dtm.addColumn("�Ա�");
		dtm.addColumn("����");
		dtm.addColumn("�ֻ�");
		dtm.addColumn("�绰");
		dtm.addColumn("סַ");
		dtm.addColumn("����");
		dtm.addColumn("���֤��");
		dtm.addColumn("��ְʱ��");
		dtm.addColumn("ְλ");

		tbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		
		tbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int row = tbl.getSelectedRow();
				tempmid = Integer.parseInt(tbl.getValueAt(row, 0).toString());
			}
		});

		// ��ѯ������
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
		// �޸�Ա����ʼ
		btnchange.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tbl.getSelectedRow();
				if (tempmid != 0 && row != -1) {
					em = employeeService.selectByEmIDReturnEM(tempmid);
					createJDialog(1,em).setVisible(true);
				} else
					JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�޸ĵ���!", "��ʾ",
							JOptionPane.WARNING_MESSAGE);
			}
		});

		// ɾ��Ա��
		btndelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Employee em = employeeService.selectByEmIDReturnEM(tempmid);
				if (em.getManage() != null) {
					if (tempmid != 0) {
						if (JOptionPane
								.showConfirmDialog(null, "��ȷ��Ҫɾ����Ա����Ϣ��?") == JOptionPane.YES_OPTION) {
							int row = tbl.getSelectedRow();
							employeeService.deleteByEmID(tempmid);
							JOptionPane.showMessageDialog(null, "��Ա��ɾ���ɹ���");
							dtm.removeRow(row);
						}
					} else
						JOptionPane.showMessageDialog(null, "��ѡ��Ҫɾ������!", "��ʾ",
								JOptionPane.WARNING_MESSAGE);
				} else
					JOptionPane.showMessageDialog(null,
							"��Ҫɾ����Ա���ǹ���Ա,����ɾ����������(����������û���ɾ��)", "��ʾ",
							JOptionPane.WARNING_MESSAGE);
			}
		});
		
		pane.add(spane);

		// ��������
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

	// ��ձ��ģ���е�����
	// ֪ͨ������ؿؼ����ģ�������ݵı䶯(ˢ��)
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
