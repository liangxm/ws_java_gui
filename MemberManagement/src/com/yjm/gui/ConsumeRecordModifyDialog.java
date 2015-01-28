package com.yjm.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.yjm.model.Consume;

/**
 * 消费记录修改对话框
 * 
 * @author lxm
 * @version 2014-3-1 13:08:27
 */
public class ConsumeRecordModifyDialog extends JDialog {

	private static final long serialVersionUID = -6881584926285290167L;
	private JPanel pane;

	JButton btnsersbumit;
	JButton btnserreturn;

	//private Consume consume;

	public ConsumeRecordModifyDialog(JDialog frame,Consume consume) {
		super(frame,true);
		//this.consume = consume;
		init();
		this.add(pane);
		this.setBounds(390, 150, 475, 500);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	//初始化组件
	private void init(){
		pane = new JPanel();
		pane.setLayout(null);


		btnsersbumit = new JButton();
		btnsersbumit.setBounds(95, 260, 100, 25);
		btnserreturn = new JButton("返回");
		btnserreturn.setBounds(260, 260, 100, 25);
		btnserreturn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		// 修改消费记录
		btnsersbumit.setBounds(95, 280, 100, 25);
		btnserreturn.setBounds(260, 280, 100, 25);
		this.setTitle("更改消费记录信息");
		btnsersbumit.setText("更新");
		btnsersbumit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (JOptionPane.showConfirmDialog(null, "您确认要更改该消费记录的信息吗") == JOptionPane.YES_OPTION) {
//					ConsumeDAO.updateConsumeByConID(Integer
//							.parseInt(txtserid.getText()));
//					JOptionPane.showMessageDialog(null, "恭喜您更新成功");
//					dispose();
				}
			}
		});
		//pane.add(txtremark);
		pane.add(btnsersbumit);
		pane.add(btnserreturn);
	}
	
	//full data for components
//	private void dataInit(){
//		
//	}
	
}
