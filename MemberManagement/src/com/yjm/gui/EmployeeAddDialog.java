package com.yjm.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.yjm.model.Employee;
import com.yjm.util.StringUtil;

/**
 * ���Ա���Ի���
 * 
 * @author lxm
 * @version 2014-2-27 20:52:37
 */
public class EmployeeAddDialog extends AbstractDialog {

	private static final long serialVersionUID = -6044856546958961706L;

	private JPanel pane;

	private JLabel lblmindate;
	private JLabel lblmid;
	private JLabel lblmname;
	private JLabel lblmphone;
	private JLabel lblemjob;
	private JLabel lblemcard;
	private JLabel lblemtel;
	private JLabel lblmanage;
	private JLabel lblmanagepass;
	private JLabel lblmsex;
	private JLabel lblmage;
	private JLabel lblmbirth;
	private JLabel lblmaddr;

	private JTextField txtmid;
	private JTextField txtmname;
	private JTextField txtmphone;
	private JTextField txtemjob;
	private JTextField txtemcard;
	private JTextField txtemtel;
	private JTextField txtmanage;
	private JPasswordField txtmanagepass;
	private JTextField txtmindate;
	private JTextField txtmage;
	private JTextField txtmbirth;
	private JTextField txtmaddr;

	private JButton btnreg;
	private JButton btnrturn;
	private JButton btnmanage;

	private JRadioButton jrb1, jrb2;
	private ButtonGroup bg;

	public EmployeeAddDialog(JFrame frame) {
		super(frame);
		initComponent();
		this.add(pane);
		this.setTitle("���Ա����Ϣ");
		this.setBounds(390, 150, 475, 500);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	// �����ʼ��
	protected void initComponent() {
		pane = new JPanel();
		pane.setLayout(null);

		// Initialize all of JLabel
		lblmname = new JLabel("����");
		lblmphone = new JLabel("�ֻ���");
		lblmsex = new JLabel("�Ա�");
		lblmage = new JLabel("����");
		lblmbirth = new JLabel("����");
		lblmaddr = new JLabel("��ַ");
		lblmindate = new JLabel("��ְ����");
		lblmid = new JLabel("���");
		lblemjob = new JLabel("ְλ");
		lblemcard = new JLabel("���֤��");
		lblemtel = new JLabel("�̶��绰");
		lblmanagepass = new JLabel("��������");
		lblmanage = new JLabel("�����˺�");

		// initialize all of JTextField
		txtmname = new JTextField();
		txtmphone = new JTextField();
		txtmage = new JTextField();
		txtmaddr = new JTextField();
		txtmbirth = new JTextField();
		txtmid = new JTextField();
		txtemjob = new JTextField();
		txtemcard = new JTextField();
		txtemtel = new JTextField();
		txtmanage = new JTextField();
		txtmindate = new JTextField();
		txtmanagepass = new JPasswordField();

		// initialize all of JButton
		btnreg = new JButton("ע��");
		btnrturn = new JButton("����");
		btnmanage = new JButton("��Ϊ����Ա");

		// initialize JRadioButton
		jrb1 = new JRadioButton("��", true);
		jrb2 = new JRadioButton("Ů");
		bg = new ButtonGroup();

		Font font = new Font("����", Font.BOLD, 14);
		lblmsex.setFont(font);
		lblmage.setFont(font);
		lblmbirth.setFont(font);
		lblmaddr.setFont(font);
		lblmindate.setFont(font);
		lblmid.setFont(font);
		lblemjob.setFont(font);
		lblemcard.setFont(font);
		lblemtel.setFont(font);
		lblmanage.setFont(font);
		lblmanagepass.setFont(font);

		lblmsex.setBounds(260, 215, 50, 25);
		jrb1.setBounds(325, 215, 50, 25);
		jrb2.setBounds(375, 215, 50, 25);
		lblmage.setBounds(260, 145, 50, 25);
		txtmage.setBounds(310, 145, 120, 25);
		lblmbirth.setBounds(40, 180, 50, 25);
		lblmaddr.setBounds(260, 180, 50, 25);
		txtmaddr.setBounds(310, 180, 120, 25);
		txtmbirth.setBounds(100, 180, 120, 25);
		lblmindate.setBounds(40, 215, 70, 25);
		lblmid.setBounds(40, 110, 50, 25);
		txtmid.setBounds(100, 110, 120, 25);
		lblmname.setBounds(260, 110, 50, 25);
		txtmname.setBounds(310, 110, 120, 25);
		lblmphone.setBounds(40, 145, 50, 25);
		txtmphone.setBounds(100, 145, 120, 25);
		lblemjob.setBounds(40, 250, 50, 25);
		txtemjob.setBounds(100, 250, 120, 25);
		lblemcard.setBounds(260, 250, 70, 25);
		txtemcard.setBounds(330, 250, 100, 25);
		lblemtel.setBounds(40, 285, 70, 25);
		txtemtel.setBounds(100, 285, 120, 25);
		lblmanage.setBounds(40, 320, 70, 25);
		txtmanage.setBounds(100, 320, 120, 25);
		lblmanagepass.setBounds(260, 320, 70, 25);
		txtmanagepass.setBounds(330, 320, 100, 25);
		btnreg.setBounds(70, 360, 100, 30);
		btnrturn.setBounds(180, 360, 100, 30);
		btnmanage.setBounds(290, 360, 100, 30);
		txtmindate.setBounds(100, 215, 120, 25);

		txtmindate.setEditable(false);
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = sdf2.format(new java.util.Date());
		txtmindate.setText(strDate);

		Chooser ser1 = Chooser.getInstance();
		Chooser ser2 = Chooser.getInstance();
		ser1.register(txtmbirth);
		ser2.register(txtmindate);

		// ����λ��
		txtmid.setEnabled(false);
		lblmanage.setVisible(false);
		txtmanage.setVisible(false);
		lblmanagepass.setVisible(false);
		txtmanagepass.setVisible(false);

		// ����TextFieldֻ������������
		txtmage.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();
				if (keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9) {
				} else {
					e.consume(); // �ؼ������ε��Ƿ�����
				}
			}
		});
		txtemcard.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();
				if (keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9) {
				} else {
					e.consume(); // �ؼ������ε��Ƿ�����
				}
			}
		});
		txtemtel.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();
				if (keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9) {
				} else {
					e.consume(); // �ؼ������ε��Ƿ�����
				}
			}
		});

		btnrturn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		// ��Ϊ���������
		btnmanage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!txtmanage.isVisible()) {
					if (JOptionPane.showConfirmDialog(null, "��ȷ��Ҫ���Ա��Ϊ����Ա��?") == JOptionPane.YES_OPTION) {
						txtmanage.setVisible(true);
						lblmanage.setVisible(true);
						lblmanagepass.setVisible(true);
						txtmanagepass.setVisible(true);
					}
				}
			}
		});

		// Ա��ע�������
		btnreg.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				if(txtmname.getText().length()==0){
					JOptionPane.showMessageDialog(null, "��������ܰ���������ô��");
					txtmname.grabFocus();
				}
				else if (!StringUtil.isMobileNO(txtmphone.getText())) {
					JOptionPane.showMessageDialog(null, "�绰�����ʽ����ȷô��");
					txtmphone.grabFocus();
				} else if (!StringUtil.isAge(txtmage.getText())) {
					JOptionPane.showMessageDialog(null, "�����ʽ���淶ô!");
					txtmage.grabFocus();
				} else if (!StringUtil.isDate(txtmbirth.getText())) {
					JOptionPane.showMessageDialog(null, "����Ҫ�������ı�����ѡ��!");
				} else if (!StringUtil.isDate(txtmindate.getText())) {
					JOptionPane.showMessageDialog(null, "��ְ���ڲ�Ҫ���");
				} else if ((txtemcard.getText().length() != 0)
						&& (!StringUtil.checkNID(txtemcard.getText()))) {
					JOptionPane.showMessageDialog(null, "���֤���벻Ҫ����(������15��8)��");
				} else if ((txtemtel.getText().length() != 0)
						&& (!StringUtil.checkNID(txtemcard.getText()))) {
					JOptionPane.showMessageDialog(null, "���֤���벻Ҫ����(������15��8)��");
				} else {
					try {
						java.util.Date utilDate = sdf.parse(txtmbirth.getText());
						java.util.Date mindate = sdf.parse(txtmindate.getText());
						Employee em = new Employee(txtmname.getText(), jrb1
								.isSelected() ? "��" : "Ů", Integer
								.parseInt(txtmage.getText()), txtmphone
								.getText(), txtemtel.getText(), txtmaddr
								.getText(), utilDate, txtemcard.getText(),
								mindate, txtemjob.getText(), txtmanage
										.getText(), new String(txtmanagepass
										.getPassword()));
						Employee em1 = employeeService
								.selectByNameReturnEmID(txtmname.getText());
						if (em1 != null) {
							JOptionPane.showMessageDialog(null,
									"�Ѿ���������Ϊ:" + em.getEmname()
											+ "��Ա��!\n�������޸ĸ�Ա����Ϣ,����������+��������");
						} else {
							employeeService.addEmployee(em);
							JOptionPane.showMessageDialog(null, "ע��ɹ�");
							dispose();
						}
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		pane.add(lblemjob);
		pane.add(lblemcard);
		pane.add(lblemtel);
		pane.add(lblmanage);
		pane.add(lblmanagepass);
		pane.add(lblmid);
		pane.add(lblmphone);
		pane.add(lblmname);
		pane.add(lblmsex);
		pane.add(lblmage);
		pane.add(lblmbirth);
		pane.add(lblmaddr);
		pane.add(lblmindate);

		pane.add(txtemjob);
		pane.add(txtemcard);
		pane.add(txtemtel);
		pane.add(txtmanage);
		pane.add(txtmanagepass);
		pane.add(txtmindate);
		pane.add(txtmid);
		pane.add(txtmphone);
		pane.add(txtmname);
		pane.add(txtmage);
		pane.add(txtmbirth);
		pane.add(txtmaddr);

		pane.add(btnmanage);
		pane.add(btnreg);
		pane.add(btnrturn);

		pane.add(jrb1);
		pane.add(jrb2);
		bg.add(jrb1);
		bg.add(jrb2);
	}
}
