package com.yjm.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.yjm.model.Consume;
import com.yjm.model.Employee;
import com.yjm.model.Member;
import com.yjm.model.Services;
import com.yjm.util.ProjectSettings;
/**
 * ��Ա��������
 * @author lxm
 * @version 2014-3-1 11:07:40
 */
public class ConsumeMemberDialog extends AbstractDialog {

	private static final long serialVersionUID = -6792093065503716976L;

	private JTabbedPane tabs;
	private JPanel pane;
	private JPanel otherService;
	private DefaultTableModel dtm;
	private JTable tbl;
	
	private JRadioButton rbtnmphone;
	private JRadioButton rbtnmname;
	
	private JTextField txtmtop;
	private JLabel lblbeforeDiscount;
	private JLabel lblafterDiscount;
	private JLabel lblTopMoney;
	private JButton btnmsearch;
	private JScrollPane spane;
	
	private JLabel lblserItem;
	private JLabel lblAgio;
	private JLabel lblSerEmployee;
	private JLabel lblSerOprator;
	private JTextField txtOperater;
	private JLabel lblcode;
	private JTextField txtcode;
	
	//��Ա�����˵�
	private JComboBox cboEmployee;
	//�ۿ������˵�
	private JComboBox cboConAgio;
	//������Ŀ
	private JComboBox cservice;
	
	private JLabel lblser;
	
	private JTextField txtBeforeDiscount;
	private JTextField txtAfterDiscount;
	private JTextField txtTotolMoney;
	private JLabel lblremark;
	private JTextArea txtremark;
	private JButton btnInMoney;
	private JButton btnConReturn;
	
	private List<Member> list;
	List <JCheckBox> listcbo;
	
	protected int mid2;
	private ConsumeMemberWithCardDialog withCard;
	
	private String [] agio = {"100","95","90","85","80","75","70","65","60","55","50"};

	String conmphone = "";
	String conmname = "";
	
	public ConsumeMemberDialog(JFrame frame) {
		super(frame);
		initComponent();
		this.add(tabs);
		this.setTitle("��Ա����");
		this.setBounds(290, 60, 710, 620);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	
	//��ʼ�����
	protected void initComponent(){
		tabs = new JTabbedPane(JTabbedPane.TOP,
				JTabbedPane.SCROLL_TAB_LAYOUT);
		pane = new JPanel();
		withCard = new ConsumeMemberWithCardDialog();
		pane.setLayout(null);
		otherService = new JPanel();
		otherService.setLayout(new GridLayout(5, 10));
		otherService.setBorder(BorderFactory.createTitledBorder(""));
		
		//Initialize Component Object
		listcbo= new ArrayList<JCheckBox>();
		rbtnmphone = new JRadioButton("�ֻ�");
		rbtnmname = new JRadioButton("����");
		txtmtop = new JTextField();
		btnmsearch = new JButton("��ѯ");
		dtm = new DefaultTableModel();
		tbl = new JTable(dtm);
		spane = new JScrollPane(tbl);
		lblserItem = new JLabel("������Ŀ");
		lblser = new JLabel("����������Ŀ");
		
		lblAgio = new JLabel("�ۿ�");
		lblSerEmployee = new JLabel("��Ա");
		lblSerOprator = new JLabel("����Ա");
		lblcode = new JLabel("�ۼƻ���");
		lblbeforeDiscount = new JLabel("��ǰ�ܼ�");
		lblafterDiscount = new JLabel("�ۺ��ܼ�");
		lblTopMoney = new JLabel("ʵ�ս��");
		
		//��Ա
		List <Employee> listem = employeeService.selectAll();
		Vector <String> vec1 = new Vector<String>();
		for (int i = 0; i < listem.size(); i++) {
			Employee em = listem.get(i);	
			vec1.add(em.getEmname());
		}
		cboEmployee = new JComboBox(vec1);
		//�ۿ�
		cboConAgio = new JComboBox(agio);
		
		List <Services> listnormal = servicesService.selectByServicesType("������Ŀ");
		Vector <String> vec2 = new Vector<String>();
		for(Services ss:listnormal){
			vec2.add(ss.getSername());
		}
		//������Ŀ
		cservice = new JComboBox(vec2);
		
		txtOperater = new JTextField(ProjectSettings.user);
		txtcode = new JTextField();
		txtBeforeDiscount = new JTextField();
		txtAfterDiscount = new JTextField();
		txtTotolMoney = new JTextField();
		
		lblremark = new JLabel("��ע");
		txtremark = new JTextArea("�ֽ�֧��");
		btnInMoney = new JButton("����");
		btnConReturn = new JButton("����");
		ButtonGroup bg = new ButtonGroup();
		Font font = new Font("����", Font.PLAIN, 14);
		ComponentActionListener cal = new ComponentActionListener();
		
		//Initialize component location and size
		rbtnmphone.setBounds(150, 20, 70, 25);
		rbtnmname.setBounds(225, 20, 70, 25);
		txtmtop.setBounds(300, 20, 100, 25);
		btnmsearch.setBounds(415, 20, 70, 25);
		spane.setBounds(7, 70, 680, 200);
		lblser.setBounds(20, 330, 100, 20);
		otherService.setBounds(7, 350, 680, 120);
		
		lblserItem.setBounds(30, 280, 70, 25);
		lblAgio.setBounds(135, 280, 50, 20);
		lblSerEmployee.setBounds(208, 280, 70, 20);
		lblSerOprator.setBounds(288, 280, 70, 20);
		lblcode.setBounds(410, 280, 60, 20);
		lblbeforeDiscount.setBounds(485, 280, 80, 20);
		lblafterDiscount.setBounds(560, 280, 80, 20);
		lblTopMoney.setBounds(624, 280, 80, 20);
		
		cservice.setBounds(7, 300, 100, 25);
		cboConAgio.setBounds(115, 300, 70, 25);
		cboEmployee.setBounds(195, 300, 70, 25);
		txtOperater.setBounds(275, 300, 70, 25);
		txtcode.setBounds(410, 300, 50, 25);
		txtBeforeDiscount.setBounds(485, 300, 50, 25);
		txtAfterDiscount.setBounds(555, 300, 60, 25);
		txtTotolMoney.setBounds(634, 300, 40, 25);
		
		lblremark.setBounds(160, 470, 60, 20);
		txtremark.setBounds(147, 490, 180, 70);
		btnInMoney.setBounds(340, 520, 100, 28);
		btnConReturn.setBounds(440, 520, 100, 28);
		
		//set Labels font
		otherService.setFont(font);
		lblAgio.setFont(font);
		rbtnmphone.setFont(font);
		rbtnmname.setFont(font);
		btnmsearch.setFont(font);
		lblserItem.setFont(font);
		lblser.setFont(font);
		lblSerEmployee.setFont(font);
		lblSerOprator.setFont(font);
		lblcode.setFont(font);
		lblTopMoney.setFont(font);
		txtBeforeDiscount.setFont(font);
		txtAfterDiscount.setFont(font);
		txtTotolMoney.setFont(font);
		lblremark.setFont(font);
		btnInMoney.setFont(font);
		btnConReturn.setFont(font);
		
		tbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		lblserItem.setForeground(Color.blue);
		lblser.setForeground(Color.blue);
		lblAgio.setForeground(Color.blue);
		
		txtBeforeDiscount.setEditable(false);
		txtAfterDiscount.setEditable(false);
		txtcode.setEditable(false);
		
		lblSerEmployee.setForeground(Color.blue);
		lblSerOprator.setForeground(Color.blue);
		lblcode.setForeground(Color.blue);
		lblbeforeDiscount.setForeground(Color.red);
		lblafterDiscount.setForeground(Color.red);
		lblTopMoney.setForeground(Color.red);
		txtTotolMoney.setForeground(new Color(70, 200, 70));
		lblremark.setForeground(Color.blue);
		
		bg.add(rbtnmphone);
		bg.add(rbtnmname);
		
		dtm.addColumn("���");
		dtm.addColumn("�ֻ�");
		dtm.addColumn("����");
		dtm.addColumn("�Ա�");
		dtm.addColumn("����");
		dtm.addColumn("����");
		dtm.addColumn("סַ");
		dtm.addColumn("ע������");
		dtm.addColumn("�ۼ�����");
		dtm.addColumn("����");
		
		btnmsearch.addActionListener(cal);
		btnInMoney.addActionListener(cal);
		btnConReturn.addActionListener(cal);
		tbl.addMouseListener(cal);
		
		cservice.addActionListener(cal);
		cboConAgio.addActionListener(cal);
		
		pane.add(spane);
		
		pane.add(rbtnmname);
		pane.add(rbtnmphone);
		pane.add(txtmtop);
		pane.add(btnmsearch);
		//pane.add(txtmtopLayMoney);
		pane.add(lblbeforeDiscount);
		pane.add(lblafterDiscount);
		pane.add(lblTopMoney);
		
		pane.add(lblserItem);
		pane.add(lblAgio);
		pane.add(lblSerEmployee);
		pane.add(lblSerOprator);
		pane.add(lblcode);
		
		pane.add(cservice);
		List <Services> listser = servicesService.selectByServicesType("������Ŀ");
		for(Services s:listser){
			JCheckBox jrb = new JCheckBox(s.getSername());
			jrb.addActionListener(cal);
			listcbo.add(jrb);
			otherService.add(jrb);
		}
		
		pane.add(lblser);
		pane.add(otherService);
				
		pane.add(cboConAgio);
		pane.add(cboEmployee);
		pane.add(txtOperater);
		pane.add(txtcode);
		pane.add(lblTopMoney);
		pane.add(lblremark);
		pane.add(txtremark);
		pane.add(txtBeforeDiscount);
		pane.add(txtAfterDiscount);
		pane.add(txtTotolMoney);
		pane.add(btnInMoney);
		pane.add(btnConReturn);
		tabs.addTab("�޿�����", pane);
		tabs.addTab("�п�����", withCard.createTab2());
	}
	
	private class ComponentActionListener extends MouseAdapter implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			//������ť
			if(e.getSource().equals(btnmsearch)){
				clearTable();
				if (txtmtop.getText().length() != 0) {
					if (rbtnmname.isSelected()) {
						list = memberService.selectByName(txtmtop.getText());
					} else if (rbtnmphone.isSelected()) {
						list = memberService.selectByPhone(txtmtop.getText());
					}
				}
				else{
					list = memberService.selectAll();
				}
				fillTable();
			}
			else if(e.getSource().equals(cservice)){
				flushPrice();
			}
			else if(e.getSource().equals(cboConAgio)){
				flushPrice();
			}
			//������Ŀ
			else if(e.getSource() instanceof JCheckBox){
				flushPrice();
			}
			//����
			else if(e.getSource().equals(btnInMoney)){
				String paidAmount = txtTotolMoney.getText().trim();
				int row = tbl.getSelectedRow();
				if(row==-1){
					JOptionPane.showMessageDialog(null, "��ѡ��һ����Ա��", "��ʾ", JOptionPane.WARNING_MESSAGE);
				}
				else if(paidAmount.length()>0){
					if(JOptionPane.showConfirmDialog(null, "��ȷ��ҪΪ\n���Ϊ:" + conmphone + "\n����Ϊ:" + conmname + "�Ļ�Ա\n���:" + txtTotolMoney.getText() +"Ԫ��") == JOptionPane.YES_OPTION)
					{
						successInConsume();
						successUpdateMoney();
						SuccesInAndOutRefresh();
					}
					else{
						JOptionPane.showMessageDialog(null, "��ѡ�н��˵Ļ�Ա", "��ʾ", JOptionPane.WARNING_MESSAGE);
					} 
				}else{
					JOptionPane.showMessageDialog(null, "������ͻ�ʵ��֧����", "��ʾ", JOptionPane.WARNING_MESSAGE);
				}
			}
			//����
			else if(e.getSource().equals(btnConReturn)){
				dispose();
			}
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			if(e.getSource().equals(tbl)){
				int row = tbl.getSelectedRow();
				txtcode.setText(tbl.getValueAt(row, 9).toString());
				txtcode.setFont(new Font("����", Font.BOLD, 14));
				conmphone = tbl.getValueAt(row, 1).toString();
				conmname = tbl.getValueAt(row, 2).toString();
			}
		}
	}

	public void fillTable() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (int i = 0; i < list.size(); i++) {
			Member m = list.get(i);
			dtm.addRow(new Object[] { m.getMid(), m.getMphone(), m.getMname(),
					m.getMsex(), m.getMage(), sdf.format(m.getMbirth()),
					m.getMaddr(), sdf.format(m.getMindate()),m.getMtotalmoney(),
					m.getMintegral() });
		}
	}

	// ��ձ��ģ���е�����
	// ֪ͨ������ؿؼ����ģ�������ݵı䶯(ˢ��)
	public void clearTable() {
		dtm.getDataVector().clear();
		dtm.fireTableDataChanged();
	}
	
	//ˢ�¼۸�
	private void flushPrice(){
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
		for(JCheckBox jcb:listcbo){
			if(jcb.isSelected()){
				price2  = price2 + servicesService.selectByNameReturnPrice(jcb.getText());
			}
		}
		txtBeforeDiscount.setText(String.valueOf(price1+price2));
		txtAfterDiscount.setText(String.valueOf(df.format((price1+price2)*discount)));
	}
	
	private void successInConsume(){
		Employee em = employeeService.selectByNameReturnEmID(cboEmployee.getSelectedItem().toString());
		int serid = servicesService.selectByNameNo(cservice.getSelectedItem().toString());
		int conemid =0;
		if(em!=null){
			conemid = em.getEmid();
		}
		Consume con = new Consume(conmphone,conemid,serid,
									new Date(),Integer.parseInt(cboConAgio.getSelectedItem().toString()),
									Double.parseDouble(txtTotolMoney.getText()),txtremark.getText());
		consumeService.addConsume(con);
	}
	
	/**
	 * ���¿����������ۼƣ���Ա����
	 */
	private void successUpdateMoney(){
		memberService.updateMoney(Integer.parseInt(txtTotolMoney.getText()), conmphone);
	}
	
	/**
	 * ˢ�»�Ա�б�
	 */
	public void SuccesInAndOutRefresh(){
		txtTotolMoney.setText("");
		txtcode.setText("");
		for(JCheckBox jcb:listcbo){
			jcb.setSelected(false);
		}
		clearTable();
		JOptionPane.showMessageDialog(null, "���˳ɹ�");
	}
	
}
