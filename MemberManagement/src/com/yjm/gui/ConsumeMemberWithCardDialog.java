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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.yjm.model.Card;
import com.yjm.model.CardType;
import com.yjm.model.Consume;
import com.yjm.model.Employee;
import com.yjm.model.Services;
import com.yjm.util.ProjectSettings;
/**
 * 会员卡收银界面
 * @author lxm
 * @version 2014-3-1 11:07:40
 */
public class ConsumeMemberWithCardDialog extends AbstractDialog {

	private static final long serialVersionUID = -6792093065503716976L;

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
	
	//理发员下拉菜单
	private JComboBox cboEmployee;
	//折扣下拉菜单
	private JComboBox cboConAgio;
	//服务项目
	private JComboBox cservice;
	
	private JLabel lblser;
	
	private JTextField txtBeforeDiscount;
	private JTextField txtAfterDiscount;
	private JTextField txtTotolMoney;
	private JLabel lblremark;
	private JTextArea txtremark;
	private JButton btnInMoney;
	private JButton btnConReturn;
	
	private List<Card> list;
	List <JCheckBox> listcbo;
	
	protected int mid2;
	private int moneyBalace;
	
	private String [] agio = {"100","95","90","85","80","75","70","65","60","55","50"};

	private String concardid = "";
	private String conmphone = "";
	private String contype = "";
	private String conmname = "";
	
	public ConsumeMemberWithCardDialog(){}
	
	public JPanel createTab2(){
		pane = new JPanel();
		pane.setLayout(null);
		otherService = new JPanel();
		otherService.setLayout(new GridLayout(5, 10));
		otherService.setBorder(BorderFactory.createTitledBorder(""));
		
		//Initialize Component Object
		listcbo= new ArrayList<JCheckBox>();
		rbtnmphone = new JRadioButton("卡号");
		rbtnmname = new JRadioButton("姓名");
		txtmtop = new JTextField();
		btnmsearch = new JButton("查询");
		dtm = new DefaultTableModel();
		tbl = new JTable(dtm);
		spane = new JScrollPane(tbl);
		lblserItem = new JLabel("服务项目");
		lblser = new JLabel("其他服务项目");
		
		lblAgio = new JLabel("折扣");
		lblSerEmployee = new JLabel("理发员");
		lblSerOprator = new JLabel("操作员");
		lblbeforeDiscount = new JLabel("折前总计");
		lblafterDiscount = new JLabel("折后总计");
		lblTopMoney = new JLabel("实收金额");
		
		//理发员
		List <Employee> listem = employeeService.selectAll();
		Vector <String> vec1 = new Vector<String>();
		for (int i = 0; i < listem.size(); i++) {
			Employee em = listem.get(i);	
			vec1.add(em.getEmname());
		}
		cboEmployee = new JComboBox(vec1);
		//折扣
		cboConAgio = new JComboBox(agio);
		
		List <Services> listnormal = servicesService.selectByServicesType("常规项目");
		Vector <String> vec2 = new Vector<String>();
		for(Services ss:listnormal){
			vec2.add(ss.getSername());
		}
		//服务项目
		cservice = new JComboBox(vec2);
		
		txtOperater = new JTextField(ProjectSettings.user);
		txtBeforeDiscount = new JTextField();
		txtAfterDiscount = new JTextField();
		txtTotolMoney = new JTextField();
		
		lblremark = new JLabel("备注");
		txtremark = new JTextArea("会员卡消费");
		btnInMoney = new JButton("结账");
		btnConReturn = new JButton("返回");
		ButtonGroup bg = new ButtonGroup();
		Font font = new Font("楷体", Font.PLAIN, 14);
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
		lblbeforeDiscount.setBounds(485, 280, 80, 20);
		lblafterDiscount.setBounds(560, 280, 80, 20);
		lblTopMoney.setBounds(624, 280, 80, 20);
		
		cservice.setBounds(7, 300, 100, 25);
		cboConAgio.setBounds(115, 300, 70, 25);
		cboEmployee.setBounds(195, 300, 70, 25);
		txtOperater.setBounds(275, 300, 70, 25);
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
		
		lblSerEmployee.setForeground(Color.blue);
		lblSerOprator.setForeground(Color.blue);
		lblbeforeDiscount.setForeground(Color.red);
		lblafterDiscount.setForeground(Color.red);
		lblTopMoney.setForeground(Color.red);
		txtTotolMoney.setForeground(new Color(70, 200, 70));
		lblremark.setForeground(Color.blue);
		
		bg.add(rbtnmphone);
		bg.add(rbtnmname);
		
		dtm.addColumn("编号");
		dtm.addColumn("卡号");
		dtm.addColumn("卡类型");
		dtm.addColumn("会员");
		dtm.addColumn("激活日期");
		dtm.addColumn("过期日期");
		dtm.addColumn("卡上余额");
		
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
		
		pane.add(cservice);
		List <Services> listser = servicesService.selectByServicesType("附加项目");
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
		pane.add(lblTopMoney);
		pane.add(lblremark);
		pane.add(txtremark);
		pane.add(txtBeforeDiscount);
		pane.add(txtAfterDiscount);
		pane.add(txtTotolMoney);
		pane.add(btnInMoney);
		pane.add(btnConReturn);
		return pane;
	}
	
	private class ComponentActionListener extends MouseAdapter implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			//搜索按钮
			if(e.getSource().equals(btnmsearch)){
				list = new ArrayList<Card>(); 
				if (txtmtop.getText().length() != 0) {
					if (rbtnmname.isSelected()) {
						clearTable();
						Card card = cardService.queryByOwner(txtmtop.getText());
						if(card!=null){
							list.add(card);
							fillTable();
						}
					} else if (rbtnmphone.isSelected()) {
						clearTable();
						Card card = cardService.queryByNum(txtmtop.getText());
						if(card!=null){
							list.add(card);
							fillTable();
						}
					}
				}
				else {
					clearTable();
					list = cardService.queryAll();
					fillTable();
				}
			}
			else if(e.getSource().equals(cservice)){
				flushPrice();
			}
			else if(e.getSource().equals(cboConAgio)){
				flushPrice();
			}
			//附加项目
			else if(e.getSource() instanceof JCheckBox){
				flushPrice();
			}
			//结账
			else if(e.getSource().equals(btnInMoney)){
				String paidAmount = txtTotolMoney.getText().trim();
				if(paidAmount.length()>0){
					if(moneyBalace-Integer.parseInt(paidAmount)<0){
						JOptionPane.showMessageDialog(null, "卡上余额不足，请选择无卡消费！", "提示", JOptionPane.WARNING_MESSAGE);
					}
					else if(moneyBalace>0){
						if(JOptionPane.showConfirmDialog(null, "您确定要为\n卡号为:" + conmphone + "\n姓名为:" + conmname + "的会员\n结款:" + txtTotolMoney.getText() +"元吗") == JOptionPane.YES_OPTION)
						{
							successInConsume();
							successUpdateMoney();
							successUpdatePoint();
							SuccesInAndOutRefresh();
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "请选中结账的会员", "提示", JOptionPane.WARNING_MESSAGE);
					} 
				}else{
					JOptionPane.showMessageDialog(null, "请输入客户实际支付金额！", "提示", JOptionPane.WARNING_MESSAGE);
				}
			}
			//返回
			else if(e.getSource().equals(btnConReturn)){
				dispose();
			}
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			if(e.getSource().equals(tbl)){
				int row = tbl.getSelectedRow();
				concardid = tbl.getValueAt(row, 0).toString();
				conmphone = tbl.getValueAt(row, 1).toString();
				contype = tbl.getValueAt(row, 2).toString();
				conmname = tbl.getValueAt(row, 3).toString();
				moneyBalace = Integer.parseInt(tbl.getValueAt(row, 6).toString());
				CardType cardType = cardTypeService.queryByName(contype);
				for(int i=0;i<agio.length;i++){
					int dis = Integer.parseInt(agio[i]);
					if(cardType.getTypediscount()==dis){
						cboConAgio.setSelectedIndex(i);
						break;
					}
				}
			}
		}
	}

	public void fillTable() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (int i = 0; i < list.size(); i++) {
			Card m = list.get(i);
			dtm.addRow(new Object[] { m.getCardid(), m.getCardnumber(), m.getCardtype(),
					m.getCardowner(), sdf.format(m.getActivedate()), sdf.format(m.getExpirydate()),
					(int)m.getCardbalace()});
		}
	}

	// 清空表格模型中的数据
	// 通知所有相关控件表格模型中数据的变动(刷新)
	public void clearTable() {
		dtm.getDataVector().clear();
		dtm.fireTableDataChanged();
	}
	
	//刷新价格
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
	 * 更新卡上余额，消费累计，会员积分
	 */
	private void successUpdateMoney(){
		cardService.updateCardBalance(Integer.parseInt(concardid), Integer.parseInt(txtTotolMoney.getText()));
	}
	
	/**
	 * 更新消费累计和会员积分
	 */
	private void successUpdatePoint(){
		memberService.earnPoint(Integer.parseInt(txtTotolMoney.getText()), conmname);
	}
	
	public void SuccesInAndOutRefresh(){
		txtTotolMoney.setText("");
		for(JCheckBox jcb:listcbo){
			jcb.setSelected(false);
		}
		clearTable();
		JOptionPane.showMessageDialog(null, "结账成功");
	}

	@Override
	protected void initComponent() {
	}
}
