package com.yjm.gui;

import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ImageButton extends JButton {

	private static final long serialVersionUID = 8847224741915827218L;

	public ImageButton(ImageIcon icon) {
		setSize(icon.getImage().getWidth(null), icon.getImage().getHeight(null));
		setIcon(icon);
		setMargin(new Insets(0, 0, 0, 0));// ���߿�����������ҿռ�����Ϊ0
		setIconTextGap(0);// ����ǩ����ʾ���ı���ͼ��֮��ļ��������Ϊ0
		setBorderPainted(false);// ����ӡ�߿�
		setBorder(null);// ��ȥ�߿�
		setText(null);// ��ȥ��ť��Ĭ������
		setFocusPainted(false);// ��ȥ����Ŀ�
		setContentAreaFilled(false);// ��ȥĬ�ϵı������
	}
}