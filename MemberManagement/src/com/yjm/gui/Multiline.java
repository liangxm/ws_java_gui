package com.yjm.gui;

import java.awt.Font;
import javax.swing.JTextArea;
/**
 * custome jtextArea
 * @author lxm
 * @version 2013-5-16 10:34:46
 */
public class Multiline extends JTextArea {
	
	private static final long serialVersionUID = 1L;

	public Multiline(String s) {
		super(s);
	}

	public void updateUI() {
		super.updateUI();

		/** set wrap*/
		setLineWrap(true);
		setWrapStyleWord(true);
		setHighlighter(null);
		setEditable(false);
		setOpaque(false);
		/** set border style*/
		setFont(new Font("¿¬Ìå", Font.PLAIN, 18));
	}
}