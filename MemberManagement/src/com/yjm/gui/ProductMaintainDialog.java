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

import com.yjm.model.Product;
import com.yjm.util.DialogFactory;
/**
 * ��Ʒά������
 * @author lxm
 * @version 2014-2-26 23:05:42
 */
public class ProductMaintainDialog extends AbstractDialog {

	private static final long serialVersionUID = -704448981711693816L;
	private JPanel pane;
	private DefaultTableModel dtm;
	private JTable tbl;
	private JScrollPane spane;
	private JTextField txtmtop;
	private JButton btnmsearch, btndelete, btnchange;
	private JRadioButton rbtnmphone, rbtnmname;

	private List<Product> list;
	private String tempmid;

	public ProductMaintainDialog(JFrame frame) {
		super(frame);
		initComponent();
		this.add(pane,BorderLayout.NORTH);
		this.add(spane,BorderLayout.CENTER);
		this.setTitle("��Ʒ��Ϣά��");
		this.setBounds(290, 140, 700, 490);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	protected void initComponent() {
		pane = new JPanel();
		dtm = new DefaultTableModel();
		rbtnmphone = new JRadioButton("��Ʒ���");
		rbtnmname = new JRadioButton("��Ʒ����");
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
		
		tbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int row = tbl.getSelectedRow();
				tempmid = tbl.getValueAt(row, 0).toString();
			}
		});

		dtm.addColumn("��Ʒ���");
		dtm.addColumn("��Ʒ����");
		dtm.addColumn("��Ʒ����");
		dtm.addColumn("��Ʒ����");
		dtm.addColumn("�ɹ�����");
		dtm.addColumn("�ɹ���Ա");
		dtm.addColumn("��������");
		dtm.addColumn("�������");
		dtm.addColumn("��������");

		// ��ѯ������
		btnmsearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearTable();
				if (txtmtop.getText().length() != 0) {
					if (rbtnmname.isSelected()) {
						list = productService.selectByName(txtmtop.getText());
					} else if (rbtnmphone.isSelected()) {
						Product p = productService.selectBymid(txtmtop.getText());
						list.add(p);
					}
				}
				//�����ı�Ϊ�գ��Ͳ�ѯ����
				else  {
					list = productService.selectAll();
				}
				fillTable();
			}
		});

		// �޸ļ�����
		btnchange.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tbl.getSelectedRow();
				if (tempmid != null && row != -1) {
					Product m = productService.selectBymid(tempmid);
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
				if (tempmid != null) {
					if (JOptionPane.showConfirmDialog(null, "��ȷ��Ҫɾ���û�Ա��Ϣ��?") == JOptionPane.YES_OPTION) {
						int row = tbl.getSelectedRow();
						productService.deleteByID(tempmid);
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
			Product m = list.get(i);
			dtm.addRow(new Object[] {m.getPrid(), m.getPname(), m.getPcount(),
					m.getPperprice(), m.getPfrom(), m.getPby(),
					sdf.format(m.getPenterdate()), sdf.format(m.getPindate()), sdf.format(m.getPexpiry())});
		}
	}
	
	private JDialog createJDialog(int type,Product product){
		return DialogFactory.createDialog(type, this, product);
	}
}
