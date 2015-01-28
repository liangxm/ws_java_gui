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
 * �ռ�����Ա����ϵͳ��Աά������
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
		this.setTitle("��Ա��Ϣά��");
		this.setBounds(290, 140, 700, 490);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	protected void initComponent() {
		pane = new JPanel();
		dtm = new DefaultTableModel();
		rbtnmphone = new JRadioButton("�ֻ�");
		rbtnmname = new JRadioButton("����");
		txtmtop = new JTextField();
		btnmsearch = new JButton("��ѯ");
		btnchange = new JButton("�޸�");
		btndelete = new JButton("ɾ��");
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
						list = memberService.selectByName(txtmtop.getText());
						fillTable();
					} else if (rbtnmphone.isSelected()) {
						list = memberService.selectByPhone(txtmtop.getText());
						fillTable();
					}
				}
				//�����ı�Ϊ�գ��Ͳ�ѯ����
				else  {
					list = memberService.selectAll();
					fillTable();
				}
			}
		});

		// �޸ļ�����
		btnchange.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tbl.getSelectedRow();
				if (tempmid != 0&&row != -1) {
					Member m = memberService.selectBymid(tempmid);
					createJDialog(1,m).setVisible(true);
				} else
					JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�޸ĵ���!", "��ʾ",
							JOptionPane.WARNING_MESSAGE);
			}
		});

		// ɾ����Ա
		btndelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tbl.getSelectedRow();
				if (tempmid != 0&&row != -1) {
					if (JOptionPane.showConfirmDialog(null, "��ȷ��Ҫɾ���û�Ա��Ϣ��?") == JOptionPane.YES_OPTION) {
						memberService.deleteByID(tempmid);
						JOptionPane.showMessageDialog(null, "�û�Աɾ���ɹ���");
						dtm.removeRow(row);
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
	
	private JDialog createJDialog(int type,Member member){
		return DialogFactory.createDialog(type, this, member);
	}
}
