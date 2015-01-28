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
 * �ռ�����Ա����ϵͳ��Աά������
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
		this.setTitle("��Ҫ�����յĻ�Ա");
		this.setBounds(290, 140, 700, 490);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	protected void initComponent() {
		pane = new JPanel();
		dtm = new DefaultTableModel();
		btnmsearch = new JButton("��ѯ");
		tbl = new JTable(dtm);
		spane = new JScrollPane(tbl);
		Font font = new Font("����", Font.BOLD, 14);
		
		btnmsearch.setFont(font);
		
		dtm.addColumn("���");
		dtm.addColumn("�ֻ�");
		dtm.addColumn("����");
		dtm.addColumn("�Ա�");
		dtm.addColumn("����");
		dtm.addColumn("����");
		dtm.addColumn("סַ");
		dtm.addColumn("�쿨����");
		dtm.addColumn("�ۼ�����");
		dtm.addColumn("����");
		
		tbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// ��ѯ������
		btnmsearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearTable();
				list = memberService.selectByBirthday();
				fillTable();
			}
		});

		//��������
		pane.add(btnmsearch);
	}

	// ��ձ��ģ���е�����
	// ֪ͨ������ؿؼ����ģ�������ݵı䶯(ˢ��)
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
