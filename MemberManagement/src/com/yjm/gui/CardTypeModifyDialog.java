package com.yjm.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.yjm.model.CardType;
/**
 * 修改卡类型对话框
 * @author lxm
 * @version 2014-2-28 22:20:10
 */
public class CardTypeModifyDialog extends AbstractDialog {

	private static final long serialVersionUID = 6197266766136948952L;
	
	private JPanel pane;
	private JLabel lblserid;
	private JLabel lblsername;
	private JLabel lblsermoney;
	private JLabel lblsertype;
	
	private JTextField txttypeid;
	private JTextField txttypename;
	private JComboBox txttypediscount;
	private JTextField txtstartAmt;
	
	private JButton btnsersbumit;
	private JButton btnserreturn;
	
	private CardType cardType;
	private String[] discount = new String[]{"100","95","90","85","80","75","70","65","60","55","50"};
	
	public CardTypeModifyDialog(JDialog frame,CardType cardType){
		super(frame);
		this.cardType = cardType;
		initComponent();
		this.add(pane);
		this.setTitle("添加会员卡类型");
		this.setBounds(390, 150, 475, 500);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	//组件初始化
	protected void initComponent(){
		pane = new JPanel();
		pane.setLayout(null);
	
		lblserid = new JLabel("编号");
		lblsername = new JLabel("类型名称");
		lblsermoney = new JLabel("类型折扣");
		lblsertype = new JLabel("预付金额");
		txttypeid = new JTextField();
		txttypename = new JTextField();
		txttypediscount = new JComboBox(new String[]{"100","95","90","85","80","75","70","65","60","55","50"});
		txtstartAmt = new JTextField();
		btnsersbumit = new JButton("提交新类型");
		btnserreturn = new JButton("返回");
		Font font = new Font("楷体", Font.BOLD, 14); 
		
		lblserid.setFont(font);
		lblsername.setFont(font);
		lblsermoney.setFont(font);
		lblsertype.setFont(font);
		
		lblserid.setBounds(110, 100, 70, 25);
		txttypeid.setBounds(200, 100, 150, 25);
		lblsername.setBounds(110, 145, 70, 25);
		txttypename.setBounds(200, 145, 150, 25);
		lblsermoney.setBounds(110, 190, 70, 25);
		lblsertype.setBounds(110, 235, 70, 25);
		txttypediscount.setBounds(200, 190, 150, 25);
		btnsersbumit.setBounds(95, 290, 100, 25);
		btnserreturn.setBounds(260, 290, 100, 25);
		txtstartAmt.setBounds(200, 235, 150, 25);
		
		dataInit();
		
		txttypeid.setEditable(false);
		
		txtstartAmt.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();
				if (keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9) {
				} else {
					e.consume(); // 关键，屏蔽掉非法输入
				}
			}
		});
		
		btnserreturn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});	
		btnsersbumit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(txttypename.getText().trim().length()==0){
					JOptionPane.showMessageDialog(null, "请输入类型名称！");
				}
				else if(txtstartAmt.getText().trim().length()==0){
					JOptionPane.showMessageDialog(null, "请设置起始金额！");
				}else{
					CardType cardType = new CardType();
					cardType.setTypename(txttypename.getText());
					cardType.setTypediscount(Integer.parseInt(txttypediscount.getSelectedItem().toString()));
					cardType.setTypestartamt(Integer.parseInt(txtstartAmt.getText()));
					cardType.setTypeid(Integer.parseInt(txttypeid.getText()));
					if(cardTypeService.updateCardType(cardType)){
						JOptionPane.showMessageDialog(null, "新卡类型修改成功！");
						dispose();
					}else{
						JOptionPane.showMessageDialog(null, "新卡类型修改失败！");
					}
				}
			}
		});
		
		pane.add(txttypename);
		pane.add(lblserid);
		pane.add(lblsername);
		pane.add(lblsermoney);
		pane.add(lblsertype);
		pane.add(txttypeid);
		pane.add(txttypediscount);
		pane.add(btnsersbumit);
		pane.add(btnserreturn);
		pane.add(txtstartAmt);
	}
	
	//full data for components
	private void dataInit(){
		txttypeid.setText(String.valueOf(cardType.getTypeid()));
		txttypename.setText(cardType.getTypename());
		for(int i=0;i<discount.length;i++){
			int dis = Integer.parseInt(discount[i]);
			if(cardType.getTypediscount()==dis){
				txttypediscount.setSelectedIndex(i);
				break;
			}
		}
		txtstartAmt.setText(String.valueOf(cardType.getTypestartamt()));
	}
}
