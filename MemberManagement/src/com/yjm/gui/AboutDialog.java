package com.yjm.gui;

import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * ʹ��˵��
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
		this.setTitle("ʹ��˵��");
		this.setBounds(290, 30, 710, 620);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	private void init(){
		pane = new JPanel();
		pane.setLayout(null);
		StringBuffer sb = new StringBuffer();
		sb.append("����˵��").append("\n");
		sb.append("1.��ϵͳ�����ɳ���Ա��С����ʱ���ܶ���������").append("\n");
		sb.append("2.��ϵͳ��ҪĿ����Ϊ��С�ͷ��ȡ������ṩ��Ա���������ͳ�ƵȻ������ܡ�").append("\n");
		sb.append("3.��ϵͳĿǰ�����κΰ�Ȩ���ƣ��κ����κλ�������������ַ��۸ģ�����������ҵ���").append("\n");
		sb.append("4.�˰汾���ڲ��԰棬ʹ�ù����п��ܻᷢ��һЩbug,���з����뼰ʱ��ϵ���ˣ�qq:1247983646��").append("\n").append("\n");
		
		sb.append("ϵͳ���").append("\n");
		sb.append("1.��ϵͳĿǰ�ṩ�������ģ��ֱ��ǣ���Ա������������Ա��������������ѯͳ�ơ����Ի�����ϵͳ����").append("\n");
		sb.append("2.ϵͳ��Ҫ����Ϊ��Ա���������ͳ�ƣ�����������չ����").append("\n");
		sb.append("3.���ڱ��汾�ǲ��԰汾��һЩ����ģ����δ������ϣ��Ժ󽫼������ƣ����и����������㾡���½�").append("\n");
		sb.append("4.ע���Ա�󣬻�Ա�����ܲ�ͬ��ɢ�͵�һЩ�Żݻ�磺���ۣ������ػݣ����ֶһ��ȵȡ�").append("\n");
		sb.append("5.�Ժ�Ľ��潫��������ƣ���������Ӫҵ���������Աƫ��ͳ�ƣ���Ա����������ֹ��������ػݵ���Ŀ��").append("\n");
		sb.append("6.��������Ҫ����ͳ�Ʊ��滯ױƷ�ͺĲģ������汾���ܻ����ɱ����㡣").append("\n");
		text = new Multiline(sb.toString());
		text.setBounds(110, 30, 500, 520);
		text.setBackground(new Color(70, 200, 200));
		text.setForeground(Color.red);
		pane.add(text);
	}
}
