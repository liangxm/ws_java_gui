package com.yjm.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.yjm.model.Services;
/**
 * 添加服务对话框
 * @author lxm
 * @version 2014-2-28 22:20:10
 */
public class ServiceAddDialog extends AbstractDialog {

	private static final long serialVersionUID = 6197266766136948952L;
	
	private JPanel pane;
	private JLabel lblserid;
	private JLabel lblsername;
	private JLabel lblsermoney;
	private JLabel lblsertype;
	
	private JTextField txtserid;
	private JTextField txtsername;
	private JTextField txtsermoney;
	
	private JButton btnsersbumit;
	private JButton btnserreturn;
	
	private JRadioButton jrb1,jrb2;
	private ButtonGroup bg;

	public ServiceAddDialog(JFrame frame){
		super(frame);
		initComponent();
		this.add(pane);
		this.setTitle("添加服务信息");
		this.setBounds(390, 150, 475, 500);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	//组件初始化
	protected void initComponent(){
		pane = new JPanel();
		pane.setLayout(null);
	
		lblserid = new JLabel("编号");
		lblsername = new JLabel("服务项目");
		lblsermoney = new JLabel("服务价格");
		lblsertype = new JLabel("项目类型");
		txtserid = new JTextField();
		txtsername = new JTextField();
		txtsermoney = new JTextField();
		btnsersbumit = new JButton("提交新项目");
		btnserreturn = new JButton("返回");
		Font font = new Font("楷体", Font.BOLD, 14); 
		
		jrb1 = new JRadioButton("常规");
		jrb2 = new JRadioButton("附加");
		bg = new ButtonGroup();
		jrb1.setSelected(true);
		
		lblserid.setFont(font);
		lblsername.setFont(font);
		lblsermoney.setFont(font);
		lblsertype.setFont(font);
		
		lblserid.setBounds(110, 100, 70, 25);
		txtserid.setBounds(200, 100, 150, 25);
		lblsername.setBounds(110, 145, 70, 25);
		txtsername.setBounds(200, 145, 150, 25);
		lblsermoney.setBounds(110, 190, 70, 25);
		lblsertype.setBounds(110, 235, 70, 25);
		txtsermoney.setBounds(200, 190, 150, 25);
		btnsersbumit.setBounds(95, 290, 100, 25);
		btnserreturn.setBounds(260, 290, 100, 25);
		jrb1.setBounds(200, 235, 80, 25);
		jrb2.setBounds(300, 235, 80, 25);
		
		txtserid.setEditable(false);
		
		btnserreturn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});	
		btnsersbumit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				servicesService.addService(new Services(txtsername.getText(),Integer.parseInt(txtsermoney.getText()),jrb1.isSelected()?"常规项目":"附加项目"));			
				JOptionPane.showMessageDialog(null, "新服务添加成功");
				dispose();
			}
		});
		
		pane.add(txtsername);
		pane.add(lblserid);
		pane.add(lblsername);
		pane.add(lblsermoney);
		pane.add(lblsertype);
		pane.add(txtserid);
		pane.add(txtsermoney);
		pane.add(btnsersbumit);
		pane.add(btnserreturn);
		pane.add(jrb1);
		pane.add(jrb2);
		bg.add(jrb1);
		bg.add(jrb2);
	}
}
