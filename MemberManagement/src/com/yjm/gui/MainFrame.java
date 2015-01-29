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
 * 艺剪美会员管理系统主服务界面
 * @author lxm
 * @version 2014-2-23 11:48:38
 */
public class MainFrame extends JFrame {
	
	/**
	 * 艺剪美会员管理系统主服务区
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
		this.setTitle("艺剪美会员管理系统v1.0.0.2"); //设置标题处的文字
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//窗体关闭时的操作 退出程序
		width = Toolkit.getDefaultToolkit().getScreenSize().width; //得到当前屏幕分辨率的高
		height = Toolkit.getDefaultToolkit().getScreenSize().height;//得到当前屏幕分辨率的宽
		this.setSize((int)width,(int)height);//设置大小
		this.setLocation(0,0); //设置窗体居中显示
		this.setResizable(false);//禁用最大化按钮
		this.setVisible(true);
	}
	
	private void init(){
		img = new ImageIcon(SwingResourceManager.getImage("funcImg/room.jpg"));
		pane = new JImagePane(img.getImage(),JImagePane.CENTRE);
		pane.setLayout(null);
		pane1 = new JPanel();
		pane1.setLayout(new BorderLayout());
		// 菜单条
		mnb = new JMenuBar();
		//工具栏
		bar = new JToolBar();
		
		jbt1 = new JButton("会员开卡", new ImageIcon(SwingResourceManager.getImage("funcImg/ccard1.jpg")));
		jbt2 = new JButton("收银台", new ImageIcon(SwingResourceManager.getImage("funcImg/cmoney1.jpg")));
		jbt3 = new JButton("会员管理", new ImageIcon(SwingResourceManager.getImage("funcImg/member1.jpg")));
		
		jbt1.setHorizontalTextPosition(SwingConstants.CENTER);
		jbt1.setVerticalTextPosition(SwingConstants.BOTTOM);
		
		jbt2.setHorizontalTextPosition(SwingConstants.CENTER);
		jbt2.setVerticalTextPosition(SwingConstants.BOTTOM);
		
		jbt3.setHorizontalTextPosition(SwingConstants.CENTER);
		jbt3.setVerticalTextPosition(SwingConstants.BOTTOM);
		
		//会员管理
		mnMemberManage = new JMenu("会员管理");
		itemReg = new JMenuItem("会员注册（办卡）");
		itmeChange = new JMenuItem("会员维护");
		itemChangePass = new JMenuItem("会员消费查询");
		itemConfiscate = new JMenuItem("会员卡充值");
		itemExit = new JMenuItem("退出");
		
		//收银管理
		mnConsume = new JMenu("收银管理");
		itemGetMoney = new JMenuItem("会员收银");
		itemNotMember = new JMenuItem("散客收银");
		itmeNote = new JMenuItem("消费记录");
		itemintegral = new JMenuItem("积分管理");
		
		// 员工管理
		mnEmployee = new JMenu("员工管理");
		itemEmReg = new JMenuItem("添加员工");
		itmeEmChange = new JMenuItem("员工维护");
		itemEmChangePass = new JMenuItem("修改密码");
		
		//库存管理
		mInventory = new JMenu("库存管理");
		itemInventoryAdd = new JMenuItem("商品入库");
		itemInventoryUpdate = new JMenuItem("商品维护");
		
		//查询统计
		mnQuery = new JMenu("查询统计");
		CardStat = new JMenuItem("会员卡消费统计");
		DateStat = new JMenuItem("日、月、年营业统计");
		Datecurve = new JMenuItem("日、月营业曲线图");
		Querrycashmember = new JMenuItem("收银员查询");
		QuerryEffort = new JMenuItem("员工业绩查询");
		
		//个性化管理
		mnindiv = new JMenu("个性化管理");
		AwokeBirth = new JMenuItem("生日提醒");
		SetSound = new JMenuItem("声音提醒设置");
		KeySet = new JMenuItem("快捷键设置");

		//系统管理
		mnsys = new JMenu("系统管理");
		ServicesAdd = new JMenuItem("添加服务");
		ServicesManage = new JMenuItem("修改服务");
		addCardType = new JMenuItem("添加卡类型");
		maintainCardType = new JMenuItem("维护卡类型");
		LogManage = new JMenuItem("日志管理");
		BackRenew = new JMenuItem("数据备份/恢复");
		help = new JMenuItem("版权说明");
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

		//工具栏
		bar.setBorder(new EtchedBorder());
		bar.add(jbt1);
		bar.add(jbt2);
		bar.add(jbt3);
		
		pane1.add(mnb,BorderLayout.NORTH);
		pane1.add(bar,BorderLayout.CENTER);
		
		// 添加到菜单
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

		// 添加到菜单栏
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
	
	//组件监听器
	private class MenuItemActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			//会员注册
			if(e.getSource().equals(itemReg)||e.getSource().equals(jbt1)){
				createDialog(1).setVisible(true);
			}
			//会员维护
			else if(e.getSource().equals(itmeChange)||e.getSource().equals(jbt3)){
				createDialog(2).setVisible(true);
			}
			//会员消费查询
			else if(e.getSource().equals(itemChangePass)){
				createDialog(9).setVisible(true);
			}
			//退出
			else if(e.getSource().equals(itemExit)){
				System.exit(0);
			}
			//会员收银
			else if(e.getSource().equals(itemGetMoney)||e.getSource().equals(jbt2)){
				createDialog(10).setVisible(true);
			}
			//散客收银
			else if(e.getSource().equals(itemNotMember)){
				createDialog(12).setVisible(true);
			}
			//消费记录
			else if(e.getSource().equals(itmeNote)){
				createDialog(11).setVisible(true);
			}
			//添加员工
			else if(e.getSource().equals(itemEmReg)){
				createDialog(5).setVisible(true);
			}
			//商品入库
			else if(e.getSource().equals(itemInventoryAdd)){
				createDialog(3).setVisible(true);
			}
			//商品维护
			else if(e.getSource().equals(itemInventoryUpdate)){
				createDialog(4).setVisible(true);
			}
			//修改信息
			else if(e.getSource().equals(itmeEmChange)){
				createDialog(6).setVisible(true);
			}
			//修改密码
			else if(e.getSource().equals(itemEmChangePass)){
				String passwd=JOptionPane.showInputDialog(null,"请输入新密码(修改当前管理员密码)！"); 
				 if(passwd!=null){
					 EmployeeService.getInstace().updateManagePass(cur_user, passwd);
					 JOptionPane.showMessageDialog(null, "密码更新成功！");
				 }
			}
			//添加服务
			else if(e.getSource().equals(ServicesAdd)){
				createDialog(7).setVisible(true);
			}
			//修改服务
			else if(e.getSource().equals(ServicesManage)){
				createDialog(8).setVisible(true);
			}
			//添加卡类型
			else if(e.getSource().equals(addCardType)){
				createDialog(15).setVisible(true);
			}
			//维护类型
			else if(e.getSource().equals(maintainCardType)){
				createDialog(16).setVisible(true);
			}
			//营业额统计
			else if(e.getSource().equals(DateStat)){
				createDialog(13).setVisible(true);
			}
			//使用说明
			else if(e.getSource().equals(help)){
				createDialog(14).setVisible(true);
			}
			//会员卡充值
			else if(e.getSource().equals(itemConfiscate)){
				createDialog(17).setVisible(true);
			}
			//生日提醒
			else if(e.getSource().equals(AwokeBirth)){
				createDialog(18).setVisible(true);
			}
			else{
				JOptionPane.showMessageDialog(null, "正在开发中，尽请期待！");
			}
		}
	}
	
	public JDialog createDialog(int type){
		return DialogFactory.createDialog(type, this);
	}
}
