package com.yjm.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
/**
 * Ӫҵ��ͳ�ƶԻ���
 * @author lxm
 * @version 2014-3-4 20:29:21
 */
public class RunningTotalDialog extends AbstractDialog {

	private static final long serialVersionUID = -7682947998692346174L;
	private JPanel pane;
	
	private JTextArea lblresult;
	private JLabel lbldays;
	private JLabel lblmonths;
	private JLabel lblyears;
	private JLabel lblstart;
	private JLabel lblend;
	
	private JTextField jtfdays;
	private JComboBox cbomonths;
	private JComboBox cboyears;
	private JTextField jtfstart;
	private JTextField jtfend;
	
	private JButton jbdays;
	private JButton jbmonths;
	private JButton jbyears;
	private JButton jbtime;
	
	public RunningTotalDialog(JFrame frame){
		super(frame);
		initComponent();
		this.add(pane);
		this.setTitle("Ӫҵ��ͳ��");
		this.setBounds(390, 150, 475, 500);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	protected void initComponent(){
		pane = new JPanel();
		pane.setLayout(null);
		
		lblresult = new JTextArea();
		lbldays = new JLabel("��Ӫҵ��");
		lblmonths = new JLabel("��Ӫҵ��");
		lblyears = new JLabel("��Ӫҵ��");
		lblstart = new JLabel("��");
		lblend = new JLabel("��");
		
		jtfdays = new JTextField();
		jtfstart = new JTextField();
		jtfend = new JTextField();
		cbomonths = new JComboBox(new String[]{"һ��","����","����","����","����","����","����","����","����","ʮ��","ʮһ��","ʮ����"});
		cboyears = new JComboBox(new String[]{"2014","2015","2016","2017"});
		
		jbdays = new JButton("��ѯ");
		jbmonths = new JButton("��ѯ");
		jbyears = new JButton("��ѯ");
		jbtime = new JButton("��ѯ");
		
		JButtonActionListener jbal = new JButtonActionListener();
		Font font = new Font("����", Font.PLAIN, 14);
		
		lblresult.setBounds(110, 80, 240, 50);
		lbldays.setBounds(100, 140, 60, 25);
		lblmonths.setBounds(100, 180, 60, 25);
		lblyears.setBounds(100, 220, 60, 25);
		lblstart.setBounds(100, 260, 40, 25);
		lblend.setBounds(200, 260, 40, 25);

		jtfdays.setBounds(180, 140, 100, 25);
		jtfstart.setBounds(120, 260, 60, 25);
		jtfend.setBounds(220, 260, 60, 25);
		cbomonths.setBounds(180, 180, 100, 25);
		cboyears.setBounds(180, 220, 100, 25);
		
		jbdays.setBounds(300, 140, 60, 25);
		jbmonths.setBounds(300, 180, 60, 25);
		jbyears.setBounds(300, 220, 60, 25);
		jbtime.setBounds(300, 260, 60, 25);
		
		lblresult.setFont(font);
		lbldays.setFont(font);
		lblmonths.setFont(font);
		lblyears.setFont(font);
		
		Chooser ser = Chooser.getInstance();
		ser.register(jtfdays);
		Chooser ser1 = Chooser.getInstance();
		Chooser ser2 = Chooser.getInstance();
		ser1.register(jtfstart);
		ser2.register(jtfend);
		
		lblresult.setEditable(false);
		lblresult.setLineWrap(true);
		lblresult.setWrapStyleWord(true);
		
		jbdays.addActionListener(jbal);
		jbmonths.addActionListener(jbal);
		jbyears.addActionListener(jbal);
		jbtime.addActionListener(jbal);
		
		pane.add(lblresult);
		pane.add(lbldays);
		pane.add(lblmonths);
		pane.add(lblyears);
		pane.add(lblstart);
		pane.add(lblend);
		
		pane.add(jtfdays);
		pane.add(cbomonths);
		pane.add(cboyears);
		pane.add(jtfstart);
		pane.add(jtfend);
		
		pane.add(jbdays);
		pane.add(jbmonths);
		pane.add(jbyears);
		pane.add(jbtime);
	}
	
	private class JButtonActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			int result = 0;
			if(e.getSource().equals(jbdays)){
				String day = jtfdays.getText();
				result = consumeService.selectByInDate(day);
				lblresult.setText(day+" ���Ӫҵ���ǣ�"+result);
			}
			else if(e.getSource().equals(jbmonths)){
				int month = cbomonths.getSelectedIndex();
				String lmonth = cbomonths.getSelectedItem().toString();
				result = consumeService.selectByMonth(month+1); 
				lblresult.setText(lmonth+" ��Ӫҵ���ǣ�"+result);
			}
			else if(e.getSource().equals(jbyears)){
				String year = cboyears.getSelectedItem().toString();
				result = consumeService.selectByYear(year);
				lblresult.setText(year+" ���Ӫҵ���ǣ�"+result);
			}
			else if(e.getSource().equals(jbtime)){
				String time = "��"+jtfstart.getText()+"��"+jtfend.getText();
				result = consumeService.selectByTime(jtfstart.getText(), jtfend.getText());
				lblresult.setText(time+" ʱ����ڵ�Ӫҵ���ǣ�"+result);
			}
		}
	}
}
