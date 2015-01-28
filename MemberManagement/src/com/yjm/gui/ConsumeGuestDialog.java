package com.yjm.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.yjm.model.Consume;
import com.yjm.model.Employee;
import com.yjm.model.Services;
import com.yjm.util.ProjectSettings;

/**
 * ɢ�������Ի���
 * 
 * @author lxm
 * @version 2014-3-1 13:03:35
 */
public class ConsumeGuestDialog extends AbstractDialog {

	private static final long serialVersionUID = 7008177174404309607L;
	private JPanel pane;
	private JPanel pane2;

	private JLabel lblserItem;
	private JLabel lblAgio;
	private JLabel lblSerEmployee;
	private JLabel lblSerOprator;
	private JLabel lblbeforeDiscount;
	private JLabel lblafterDiscount;
	private JLabel lblTopMoney;
	private JLabel lblExtraItem;
	private JLabel lblwarning;

	private JComboBox cservice;
	private JComboBox cboConAgio;
	private JComboBox cboEmployee;
	private JTextField txtOperater;
	private JTextField txtBeforeDiscount;
	private JTextField txtAfterDiscount;
	private JTextField txtTotolMoney;

	private JLabel lblremark;
	private JTextArea txtremark;
	private JButton btnInMoney;
	private JButton btnConReturn;

	private List<JCheckBox> listcbo;

	private String[] agio = { "100", "95", "90", "85", "80", "75", "70", "65",
			"60", "55", "50" };

	private String conmphone = "";
	private String conmname = "";
	
	public ConsumeGuestDialog() {
		initComponent();
		this.add(pane);
		this.setTitle("ɢ������");
		this.setBounds(290, 60, 710, 620);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public ConsumeGuestDialog(JFrame frame) {
		super(frame);
		initComponent();
		this.add(pane);
		this.setTitle("ɢ������");
		this.setBounds(290, 140, 710, 520);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	protected void initComponent() {
		pane = new JPanel();
		pane.setLayout(null);

		pane2 = new JPanel();
		pane2.setLayout(new GridLayout(5, 6));
		pane2.setBorder(BorderFactory.createTitledBorder(""));

		lblserItem = new JLabel("������Ŀ");
		lblAgio = new JLabel("�ۿ�");
		lblSerEmployee = new JLabel("��Ա");
		lblSerOprator = new JLabel("����Ա");
		lblbeforeDiscount = new JLabel("��ǰ�ܼ�");
		lblafterDiscount = new JLabel("�ۺ��ܼ�");
		lblTopMoney = new JLabel("�ܼ�");
		lblExtraItem = new JLabel("������Ŀ");
		lblwarning = new JLabel("Ŀǰ����Ϊɢ�ͽ���������Ӧ�ý���ɢ��ע��Ϊ��Ա��");

		List<Services> listnormal = servicesService.selectByServicesType("������Ŀ");
		Vector<String> vec2 = new Vector<String>();
		for (Services ss : listnormal) {
			vec2.add(ss.getSername());
		}
		cservice = new JComboBox(vec2);
		cboConAgio = new JComboBox(agio);
		// ��Ա
		List<Employee> listem = employeeService.selectAll();
		Vector<String> vec1 = new Vector<String>();
		for (int i = 0; i < listem.size(); i++) {
			Employee em = listem.get(i);
			vec1.add(em.getEmname());
		}
		cboEmployee = new JComboBox(vec1);
		txtOperater = new JTextField(ProjectSettings.user);
		txtBeforeDiscount = new JTextField();
		txtAfterDiscount = new JTextField();
		txtTotolMoney = new JTextField();

		lblremark = new JLabel("��ע");
		txtremark = new JTextArea("ɢ������");
		btnInMoney = new JButton("����");
		btnConReturn = new JButton("����");
		
		Font font = new Font("����", Font.PLAIN, 14);

		listcbo = new ArrayList<JCheckBox>();

		ComponentActionListener cal = new ComponentActionListener();
		
		lblserItem.setFont(font);
		lblAgio.setFont(font);
		lblSerEmployee.setFont(font);
		lblSerOprator.setFont(font);
		lblbeforeDiscount.setFont(font);
		lblafterDiscount.setFont(font);
		lblTopMoney.setFont(font);
		lblExtraItem.setFont(font);
		lblwarning.setFont(font);

		lblserItem.setBounds(20, 140, 70, 25);
		lblAgio.setBounds(125, 140, 50, 20);
		lblSerEmployee.setBounds(208, 140, 70, 20);
		lblSerOprator.setBounds(288, 140, 70, 20);
		lblbeforeDiscount.setBounds(485, 140, 80, 20);
		lblafterDiscount.setBounds(560, 140, 80, 20);
		lblTopMoney.setBounds(644, 140, 80, 20);
		lblExtraItem.setBounds(20, 190, 70, 20);
		
		lblwarning.setBounds(150, 80, 340, 25);

		cservice.setBounds(7, 160, 70, 25);
		cboConAgio.setBounds(100, 160, 70, 25);
		cboEmployee.setBounds(190, 160, 70, 25);
		txtOperater.setBounds(270, 160, 70, 25);
		txtBeforeDiscount.setBounds(485, 160, 50, 25);
		txtAfterDiscount.setBounds(560, 160, 50, 25);
		txtTotolMoney.setBounds(634, 160, 40, 25);

		lblremark.setBounds(160, 370, 60, 20);
		txtremark.setBounds(147, 390, 180, 70);
		btnInMoney.setBounds(340, 420, 100, 28);
		btnConReturn.setBounds(440, 420, 100, 28);

		btnInMoney.addActionListener(cal);
		btnConReturn.addActionListener(cal);
		cservice.addActionListener(cal);
		cboConAgio.addActionListener(cal);

		pane2.setBounds(7, 210, 680, 160);

		pane.add(lblserItem);
		pane.add(lblAgio);
		pane.add(lblSerEmployee);
		pane.add(lblSerOprator);
		pane.add(lblbeforeDiscount);
		pane.add(lblafterDiscount);
		pane.add(lblTopMoney);
		pane.add(lblExtraItem);
		pane.add(lblwarning);
		
		lblwarning.setForeground(Color.red);

		pane.add(cservice);
		List<Services> listser = servicesService.selectByServicesType("������Ŀ");
		for (Services s : listser) {
			JCheckBox jrb = new JCheckBox(s.getSername());
			jrb.addActionListener(cal);
			listcbo.add(jrb);
			pane2.add(jrb);
		}

		pane.add(cboConAgio);
		pane.add(cboEmployee);
		pane.add(txtOperater);
		pane.add(txtBeforeDiscount);
		pane.add(txtAfterDiscount);
		pane.add(txtTotolMoney);

		pane.add(lblremark);
		pane.add(txtremark);
		pane.add(btnInMoney);
		pane.add(btnConReturn);

		pane.add(pane2);
	}

	private class ComponentActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(cservice)) {
				flushPrice();
			} else if (e.getSource().equals(cboConAgio)) {
				flushPrice();
			}
			// ������Ŀ
			else if (e.getSource() instanceof JCheckBox) {
				flushPrice();
			}
			// ����
			else if (e.getSource().equals(btnInMoney)) {
				conmname = "�ǻ�Ա";
				if (txtTotolMoney.getText().trim().length() > 0) {
					if (JOptionPane.showConfirmDialog(null, "��ȷ��ҪΪ\n���Ϊ:"
							+ conmphone + "\n����Ϊ:" + conmname + "�Ļ�Ա\n���:"
							+ txtTotolMoney.getText() + "Ԫ��") == JOptionPane.YES_OPTION) {
						successInMoney();
						SuccesInAndOutRefresh();
					}
				} else {
					JOptionPane.showMessageDialog(null, "������ͻ�ʵ��֧����", "��ʾ",
							JOptionPane.WARNING_MESSAGE);
				}
			}
			// ����
			else if (e.getSource().equals(btnConReturn)) {
				dispose();
			}
		}
	}

	// ˢ�¼۸�
	private void flushPrice() {
		DecimalFormat df = new DecimalFormat("###.00");
		double price1 = 0d;
		double price2 = 0d;
		double discount = 0d;
		String serviceStr = cservice.getSelectedItem().toString();
		String discussStr = cboConAgio.getSelectedItem().toString();
		if(serviceStr!=null){
			price1 = servicesService.selectByNamePrice(cservice
					.getSelectedItem().toString());
		}
		if(discussStr!=null){
			discount = (double) (Math.round(Integer.parseInt(discussStr)) / 100.0);
		}
		for (JCheckBox jcb : listcbo) {
			if (jcb.isSelected()) {
				price2 = price2
						+ servicesService.selectByNameReturnPrice(jcb.getText());
			}
		}
		txtBeforeDiscount.setText(String.valueOf(price1 + price2));
		txtAfterDiscount.setText(String.valueOf(df.format((price1 + price2)
				* discount)));
	}

	public void successInMoney() {
		conmphone = "000001";
		Employee em = employeeService.selectByNameReturnEmID(cboEmployee
				.getSelectedItem().toString());
		int serid = servicesService.selectByNameNo(cservice.getSelectedItem()
				.toString());
		int conemid = 0;
		if (em != null) {
			conemid = em.getEmid();
		}
		Consume con = new Consume(conmphone, conemid, serid, new Date(),
				Integer.parseInt(cboConAgio.getSelectedItem().toString()),
				Double.parseDouble(txtTotolMoney.getText()),
				txtremark.getText());
		consumeService.addConsume(con);
	}

	public void SuccesInAndOutRefresh() {
		txtTotolMoney.setText("");
		for (JCheckBox jcb : listcbo) {
			jcb.setSelected(false);
		}
		JOptionPane.showMessageDialog(null, "���˳ɹ�");
	}

}
