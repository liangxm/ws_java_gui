package com.yjm.util;

import java.awt.Image;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;

public class ProjectSettings {
	public static Image logo;
	public static String Version = "1.0.0.0";
	public static SimpleDateFormat sdf;
	public static String user;
	static {
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		logo = new ImageIcon(SwingResourceManager.getImage("funcImg/logo.gif")).getImage();
	}
}
