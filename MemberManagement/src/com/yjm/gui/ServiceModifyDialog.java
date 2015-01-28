package com.yjm.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.yjm.model.Services;
/**
 * �޸ķ���Ի���
 * @author lxm
 * @version 2014-2-28 22:20:10
 */
public class ServiceModifyDialog extends AbstractDialog {

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
	
	private Services service;

	public ServiceModifyDialog(JDialog frame,Services service){
		super(frame);
		this.service = service;
		initComponent();
		this.add(pane);
		this.setTitle("�޸ķ�����Ϣ");
		this.setBounds(390, 150, 475, 500);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	//�����ʼ��
	protected void initComponent(){
		pane = new JPanel();
		pane.setLayout(null);
	
		lblserid = new JLabel("���");
		lblsername = new JLabel("������Ŀ");
		lblsermoney = new JLabel("����۸�");
		lblsertype = new JLabel("��������");
		txtserid = new JTextField();
		txtsername = new JTextField();
		txtsermoney = new JTextField();
		btnsersbumit = new JButton("ȷ�ϸ���");
		btnserreturn = new JButton("����");
		Font font = new Font("����", Font.BOLD, 14); 
		
		jrb1 = new JRadioButton("����");
		jrb2 = new JRadioButton("����");
		bg = new ButtonGroup();
		
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
				int serid = Integer.parseInt(txtserid.getText());
				servicesService.updateServices(new Services(serid,txtsername.getText(),Integer.parseInt(txtsermoney.getText()),jrb1.isSelected()?"������Ŀ":"������Ŀ"));			
				JOptionPane.showMessageDialog(null, "������³ɹ�");
				dispose();
			}
		});
		dataInit();
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
	
	//full data for components
	private void dataInit(){
		txtserid.setText(String.valueOf(service.getSerid()));
		txtsername.setText(service.getSername());
		txtsermoney.setText(String.valueOf(service.getSermoney()));
		if("������Ŀ".equals(service.getSertype())){
			jrb1.setSelected(true);
			jrb2.setSelected(false);
		}else{
			jrb1.setSelected(false);
			jrb2.setSelected(true);
		}
	}
}
