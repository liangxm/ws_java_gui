package com.yjm.gui;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import com.yjm.service.EmployeeService;
import com.yjm.util.DialogFactory;
import com.yjm.util.ProjectSettings;
import com.yjm.util.SwingResourceManager;
/**
 * �ռ�����Ա����ϵͳ���������
 * @author lxm
 * @version 2014-2-23 11:48:38
 */
public class MainFrame extends JFrame {
	
	/**
	 * �ռ�����Ա����ϵͳ��������
	 */
	private static final long serialVersionUID = 9191062468995901065L;
	private JImagePane pane;
	private JPanel pane1;
	private JMenuBar mnb;
	private JToolBar bar;
	private JMenu mnMemberManage, mnConsume, mnEmployee,mInventory, mnQuery, mnindiv,
			mnsys;
	private JMenuItem itemReg, itmeChange, itemChangePass, itemConfiscate, itemExit;
	private JMenuItem itemNotMember, itmeNote, itemintegral;
	private JMenuItem itemGetMoney, itemEmReg, itmeEmChange, itemEmChangePass;
	private JMenuItem itemInventoryAdd,itemInventoryUpdate;
	private JMenuItem CardStat, DateStat, Datecurve, Querrycashmember,
			QuerryEffort;
	private JMenuItem AwokeBirth, SetSound, KeySet;
	private JMenuItem ServicesAdd, ServicesManage, addCardType, maintainCardType,
			LogManage, BackRenew,help;
	private JButton jbt1,jbt2,jbt3;

	private ImageIcon img;
	private String cur_user;
	
	private double width,height;

	public MainFrame(String cur_user) {
		this.cur_user = cur_user;
		ProjectSettings.user = cur_user;
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setIconImage(ProjectSettings.logo);
		this.setLayout(new BorderLayout());
		init();
		//this.getContentPane().add(mnb,BorderLayout.NORTH);
		this.getContentPane().add(pane1,BorderLayout.NORTH);
		this.getContentPane().add(pane,BorderLayout.CENTER);
		this.setTitle("�ռ�����Ա����ϵͳv1.0.0.2"); //���ñ��⴦������
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//����ر�ʱ�Ĳ��� �˳�����
		width = Toolkit.getDefaultToolkit().getScreenSize().width; //�õ���ǰ��Ļ�ֱ��ʵĸ�
		height = Toolkit.getDefaultToolkit().getScreenSize().height;//�õ���ǰ��Ļ�ֱ��ʵĿ�
		this.setSize((int)width,(int)height);//���ô�С
		this.setLocation(0,0); //���ô��������ʾ
		this.setResizable(false);//������󻯰�ť
		this.setVisible(true);
	}
	
	private void init(){
		img = new ImageIcon(SwingResourceManager.getImage("funcImg/room.jpg"));
		pane = new JImagePane(img.getImage(),JImagePane.CENTRE);
		pane.setLayout(null);
		pane1 = new JPanel();
		pane1.setLayout(new BorderLayout());
		// �˵���
		mnb = new JMenuBar();
		//������
		bar = new JToolBar();
		
		jbt1 = new JButton("��Ա����", new ImageIcon(SwingResourceManager.getImage("funcImg/ccard1.jpg")));
		jbt2 = new JButton("����̨", new ImageIcon(SwingResourceManager.getImage("funcImg/cmoney1.jpg")));
		jbt3 = new JButton("��Ա����", new ImageIcon(SwingResourceManager.getImage("funcImg/member1.jpg")));
		
		jbt1.setHorizontalTextPosition(SwingConstants.CENTER);
		jbt1.setVerticalTextPosition(SwingConstants.BOTTOM);
		
		jbt2.setHorizontalTextPosition(SwingConstants.CENTER);
		jbt2.setVerticalTextPosition(SwingConstants.BOTTOM);
		
		jbt3.setHorizontalTextPosition(SwingConstants.CENTER);
		jbt3.setVerticalTextPosition(SwingConstants.BOTTOM);
		
		//��Ա����
		mnMemberManage = new JMenu("��Ա����");
		itemReg = new JMenuItem("��Աע�ᣨ�쿨��");
		itmeChange = new JMenuItem("��Աά��");
		itemChangePass = new JMenuItem("��Ա���Ѳ�ѯ");
		itemConfiscate = new JMenuItem("��Ա����ֵ");
		itemExit = new JMenuItem("�˳�");
		
		//��������
		mnConsume = new JMenu("��������");
		itemGetMoney = new JMenuItem("��Ա����");
		itemNotMember = new JMenuItem("ɢ������");
		itmeNote = new JMenuItem("���Ѽ�¼");
		itemintegral = new JMenuItem("���ֹ���");
		
		// Ա������
		mnEmployee = new JMenu("Ա������");
		itemEmReg = new JMenuItem("���Ա��");
		itmeEmChange = new JMenuItem("Ա��ά��");
		itemEmChangePass = new JMenuItem("�޸�����");
		
		//������
		mInventory = new JMenu("������");
		itemInventoryAdd = new JMenuItem("��Ʒ���");
		itemInventoryUpdate = new JMenuItem("��Ʒά��");
		
		//��ѯͳ��
		mnQuery = new JMenu("��ѯͳ��");
		CardStat = new JMenuItem("��Ա������ͳ��");
		DateStat = new JMenuItem("�ա��¡���Ӫҵͳ��");
		Datecurve = new JMenuItem("�ա���Ӫҵ����ͼ");
		Querrycashmember = new JMenuItem("����Ա��ѯ");
		QuerryEffort = new JMenuItem("Ա��ҵ����ѯ");
		
		//���Ի�����
		mnindiv = new JMenu("���Ի�����");
		AwokeBirth = new JMenuItem("��������");
		SetSound = new JMenuItem("������������");
		KeySet = new JMenuItem("��ݼ�����");

		//ϵͳ����
		mnsys = new JMenu("ϵͳ����");
		ServicesAdd = new JMenuItem("��ӷ���");
		ServicesManage = new JMenuItem("�޸ķ���");
		addCardType = new JMenuItem("��ӿ�����");
		maintainCardType = new JMenuItem("ά��������");
		LogManage = new JMenuItem("��־����");
		BackRenew = new JMenuItem("���ݱ���/�ָ�");
		help = new JMenuItem("��Ȩ˵��");
		MenuItemActionListener mta = new MenuItemActionListener();
		
		mnb.setBounds(0, 3, 800, 22);
		
		itemReg.addActionListener(mta);
		itmeChange.addActionListener(mta);
		itemChangePass.addActionListener(mta);
		itemConfiscate.addActionListener(mta);
		itemExit.addActionListener(mta);
		
		itemGetMoney.addActionListener(mta);
		itemNotMember.addActionListener(mta);
		itmeNote.addActionListener(mta);
		itemintegral.addActionListener(mta);
		
		itemEmReg.addActionListener(mta);
		itmeEmChange.addActionListener(mta);
		itemEmChangePass.addActionListener(mta);
		
		itemInventoryAdd.addActionListener(mta);
		itemInventoryUpdate.addActionListener(mta);
		
		CardStat.addActionListener(mta);
		DateStat.addActionListener(mta);
		Datecurve.addActionListener(mta);
		Querrycashmember.addActionListener(mta);
		QuerryEffort.addActionListener(mta);
		
		AwokeBirth.addActionListener(mta);
		SetSound.addActionListener(mta);
		KeySet.addActionListener(mta);
		
		ServicesAdd.addActionListener(mta);
		ServicesManage.addActionListener(mta);
		addCardType.addActionListener(mta);
		maintainCardType.addActionListener(mta);
		LogManage.addActionListener(mta);
		BackRenew.addActionListener(mta);
		help.addActionListener(mta);
		
		jbt1.addActionListener(mta);
		jbt2.addActionListener(mta);
		jbt3.addActionListener(mta);

		//������
		bar.setBorder(new EtchedBorder());
		bar.add(jbt1);
		bar.add(jbt2);
		bar.add(jbt3);
		
		pane1.add(mnb,BorderLayout.NORTH);
		pane1.add(bar,BorderLayout.CENTER);
		
		// ��ӵ��˵�
		mnMemberManage.add(itemReg);
		mnMemberManage.add(itmeChange);
		mnMemberManage.add(itemChangePass);
		mnMemberManage.add(itemConfiscate);
		mnMemberManage.addSeparator();
		mnMemberManage.add(itemExit);

		mnConsume.add(itemGetMoney);
		mnConsume.add(itemNotMember);
		mnConsume.add(itmeNote);
		mnConsume.add(itemintegral);

		mnEmployee.add(itemEmReg);
		mnEmployee.add(itmeEmChange);
		mnEmployee.add(itemEmChangePass);
		
		mInventory.add(itemInventoryAdd);
		mInventory.add(itemInventoryUpdate);

		mnQuery.add(CardStat);
		mnQuery.add(DateStat);
		mnQuery.add(Datecurve);
		mnQuery.add(Querrycashmember);
		mnQuery.add(QuerryEffort);

		mnindiv.add(AwokeBirth);
		mnindiv.add(SetSound);
		mnindiv.add(KeySet);

		mnsys.add(ServicesAdd);
		mnsys.add(ServicesManage);
		mnsys.add(addCardType);
		mnsys.add(maintainCardType);
		mnsys.add(LogManage);
		mnsys.add(BackRenew);
		mnsys.add(help);

		// ��ӵ��˵���
		mnb.add(mnMemberManage);
		mnb.add(mnConsume);
		mnb.add(mnEmployee);
		mnb.add(mInventory);
		mnb.add(mnQuery);
		mnb.add(mnindiv);
		mnb.add(mnsys);

		//pane.add(mnb);
		pane.setOpaque(false);
	}
	
	//���������
	private class MenuItemActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			//��Աע��
			if(e.getSource().equals(itemReg)||e.getSource().equals(jbt1)){
				createDialog(1).setVisible(true);
			}
			//��Աά��
			else if(e.getSource().equals(itmeChange)||e.getSource().equals(jbt3)){
				createDialog(2).setVisible(true);
			}
			//��Ա���Ѳ�ѯ
			else if(e.getSource().equals(itemChangePass)){
				createDialog(9).setVisible(true);
			}
			//�˳�
			else if(e.getSource().equals(itemExit)){
				System.exit(0);
			}
			//��Ա����
			else if(e.getSource().equals(itemGetMoney)||e.getSource().equals(jbt2)){
				createDialog(10).setVisible(true);
			}
			//ɢ������
			else if(e.getSource().equals(itemNotMember)){
				createDialog(12).setVisible(true);
			}
			//���Ѽ�¼
			else if(e.getSource().equals(itmeNote)){
				createDialog(11).setVisible(true);
			}
			//���Ա��
			else if(e.getSource().equals(itemEmReg)){
				createDialog(5).setVisible(true);
			}
			//��Ʒ���
			else if(e.getSource().equals(itemInventoryAdd)){
				createDialog(3).setVisible(true);
			}
			//��Ʒά��
			else if(e.getSource().equals(itemInventoryUpdate)){
				createDialog(4).setVisible(true);
			}
			//�޸���Ϣ
			else if(e.getSource().equals(itmeEmChange)){
				createDialog(6).setVisible(true);
			}
			//�޸�����
			else if(e.getSource().equals(itemEmChangePass)){
				String passwd=JOptionPane.showInputDialog(null,"������������(�޸ĵ�ǰ����Ա����)��"); 
				 if(passwd!=null){
					 EmployeeService.getInstace().updateManagePass(cur_user, passwd);
					 JOptionPane.showMessageDialog(null, "������³ɹ���");
				 }
			}
			//��ӷ���
			else if(e.getSource().equals(ServicesAdd)){
				createDialog(7).setVisible(true);
			}
			//�޸ķ���
			else if(e.getSource().equals(ServicesManage)){
				createDialog(8).setVisible(true);
			}
			//��ӿ�����
			else if(e.getSource().equals(addCardType)){
				createDialog(15).setVisible(true);
			}
			//ά������
			else if(e.getSource().equals(maintainCardType)){
				createDialog(16).setVisible(true);
			}
			//Ӫҵ��ͳ��
			else if(e.getSource().equals(DateStat)){
				createDialog(13).setVisible(true);
			}
			//ʹ��˵��
			else if(e.getSource().equals(help)){
				createDialog(14).setVisible(true);
			}
			//��Ա����ֵ
			else if(e.getSource().equals(itemConfiscate)){
				createDialog(17).setVisible(true);
			}
			//��������
			else if(e.getSource().equals(AwokeBirth)){
				createDialog(18).setVisible(true);
			}
			else{
				JOptionPane.showMessageDialog(null, "���ڿ����У������ڴ���");
			}
		}
	}
	
	public JDialog createDialog(int type){
		return DialogFactory.createDialog(type, this);
	}
}
