package com.yjm.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.yjm.model.Consume;

/**
 * ���Ѽ�¼�޸ĶԻ���
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
	
	//��ʼ�����
	private void init(){
		pane = new JPanel();
		pane.setLayout(null);


		btnsersbumit = new JButton();
		btnsersbumit.setBounds(95, 260, 100, 25);
		btnserreturn = new JButton("����");
		btnserreturn.setBounds(260, 260, 100, 25);
		btnserreturn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		// �޸����Ѽ�¼
		btnsersbumit.setBounds(95, 280, 100, 25);
		btnserreturn.setBounds(260, 280, 100, 25);
		this.setTitle("�������Ѽ�¼��Ϣ");
		btnsersbumit.setText("����");
		btnsersbumit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (JOptionPane.showConfirmDialog(null, "��ȷ��Ҫ���ĸ����Ѽ�¼����Ϣ��") == JOptionPane.YES_OPTION) {
//					ConsumeDAO.updateConsumeByConID(Integer
//							.parseInt(txtserid.getText()));
//					JOptionPane.showMessageDialog(null, "��ϲ�����³ɹ�");
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
