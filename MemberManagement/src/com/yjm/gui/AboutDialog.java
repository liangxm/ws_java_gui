package com.yjm.gui;

import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * 使用说明
 * @author lxm
 * @version 2014-3-4 21:46:04
 */
public class AboutDialog extends JDialog {

	private static final long serialVersionUID = 1335004889353274218L;
	private JPanel pane;
	private Multiline text;

	public AboutDialog(JFrame frame){
		super(frame,true);
		init();
		this.add(pane);
		this.setTitle("使用说明");
		this.setBounds(290, 30, 710, 620);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	private void init(){
		pane = new JPanel();
		pane.setLayout(null);
		StringBuffer sb = new StringBuffer();
		sb.append("作者说明").append("\n");
		sb.append("1.本系统由自由程序员梁小满耗时两周独立开发。").append("\n");
		sb.append("2.本系统主要目的是为中小型发廊、理发店提供会员管理和收银统计等基础功能。").append("\n");
		sb.append("3.本系统目前不做任何版权限制，任何人任何机构可自由任意分发篡改，甚至用于商业活动。").append("\n");
		sb.append("4.此版本属于测试版，使用过程中可能会发生一些bug,如有发生请及时联系本人（qq:1247983646）").append("\n").append("\n");
		
		sb.append("系统简介").append("\n");
		sb.append("1.本系统目前提供七项管理模块分别是（会员管理、收银管理、员工管理、库存管理、查询统计、个性化管理、系统管理）").append("\n");
		sb.append("2.系统主要功能为会员管理和收银统计，其它属于扩展功能").append("\n");
		sb.append("3.由于本版本是测试版本，一些功能模块尚未开发完毕，稍后将继续完善，如有给您带来不便尽请谅解").append("\n");
		sb.append("4.注册会员后，会员将享受不同于散客的一些优惠活动如：打折，生日特惠，积分兑换等等。").append("\n");
		sb.append("5.稍后改进版将会更加完善，包括增加营业额分析，会员偏好统计，会员级别管理，积分管理，节日特惠等项目。").append("\n");
		sb.append("6.库存管理主要用来统计保存化妆品和耗材，后续版本可能会加入成本估算。").append("\n");
		text = new Multiline(sb.toString());
		text.setBounds(110, 30, 500, 520);
		text.setBackground(new Color(70, 200, 200));
		text.setForeground(Color.red);
		pane.add(text);
	}
}
