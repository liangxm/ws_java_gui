package com.yjm.gui;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import com.yjm.model.Employee;
import com.yjm.service.EmployeeService;
import com.yjm.util.ProjectSettings;
import com.yjm.util.SwingResourceManager;

/**
 * �ռ�����Ա����ϵͳ��¼����
 * 
 * @author lxm
 * @version 2014-2-22 22:45:40
 */
public class LoginFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = -4065026953872818051L;
	private JLabel label_1;
	private JTextField textfield_1;
	private JPasswordField textfield_2;
	private ImageButton button_1;
	private ImageIcon img, img1;
	private EmployeeService employee = EmployeeService.getInstace();

	public LoginFrame() {
		getContentPane().setLayout(null);
		init();
		setTitle("�ռ�������Ա��¼");
		setIconImage(ProjectSettings.logo);
		((JPanel) getContentPane()).setOpaque(false);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		setSize(500, 400);
		setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
	}

	private void init() {
		img = new ImageIcon(SwingResourceManager.getImage("funcImg/loginc.jpg"));
		img1 = new ImageIcon(SwingResourceManager.getImage("funcImg/loginb.gif"));
		button_1 = new ImageButton(img1);

		label_1 = new JLabel(img);

		textfield_1 = new JTextField("");
		textfield_2 = new JPasswordField("");

		label_1.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());

		textfield_1.setBounds(70, 130, 150, 30);
		textfield_2.setBounds(70, 170, 150, 30);

		button_1.setBounds(90, 210, 140, 48);

		getLayeredPane().add(label_1, new Integer(Integer.MIN_VALUE));
		getContentPane().add(textfield_1);
		getContentPane().add(textfield_2);
		getContentPane().add(button_1);

		textfield_2.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) // ���س���ִ����Ӧ����;
				{
					loginCheck();
				}
			}
		});
		button_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loginCheck();
			}
		});
	}

	private void loginCheck() {
		String user = textfield_1.getText().trim();
		char[] values = textfield_2.getPassword();
		String pass = new String(values);
		ArrayList<Employee> list2 = (ArrayList<Employee>) employee.selectByManageReturnUser(user);
		if (pass.equals(employee.selectByManageReturnPass(user))) {
			JOptionPane.showMessageDialog(null, "��½�ɹ�");
			new MainFrame(user);
			dispose();
		} else if (list2.size() == 0) {
			JOptionPane.showMessageDialog(null, "�����ڸù����˻�", "��ʾ",
					JOptionPane.WARNING_MESSAGE);
		} else if (!pass.equals(employee.selectByManageReturnPass(user))) {
			JOptionPane.showMessageDialog(null, "�����������", "��ʾ",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		loginCheck();
	}

	public static void main(String args[]) {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "δ�ҵ���Ƥ����������JDK��6.0 update 10");
		}
		LoginFrame window = new LoginFrame();
		window.setVisible(true);
	}
}