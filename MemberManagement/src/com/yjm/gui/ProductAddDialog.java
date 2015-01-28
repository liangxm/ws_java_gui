package com.yjm.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Timestamp;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.yjm.model.Product;
import com.yjm.util.ProjectSettings;
import com.yjm.util.StringUtil;

/**
 * 商品入库界面
 * 
 * @author lxm
 * @version 2014-2-26 21:05:10
 */
public class ProductAddDialog extends AbstractDialog {

	private static final long serialVersionUID = 3468221815747628466L;
	private JPanel pane;
	private JButton btnreg;
	private JButton btnreturn;

	private JLabel lprid;
	private JLabel lpname;
	private JLabel lpcount;
	private JLabel lpperprice;
	private JLabel lpenterdate;
	private JLabel lpfrom;
	private JLabel lpby;
	private JLabel lpexpiry;

	private JTextField tprid;
	private JTextField tpname;
	private JTextField tpcount;
	private JTextField tpperprice;
	private JTextField tpenterdate;
	private JTextField tpfrom;
	private JTextField tpby;
	private JTextField tpexpiry;

	public ProductAddDialog(JFrame frame) {
		super(frame);
		initComponent();
		add(pane);
		setTitle("商品入库");
		setBounds(390, 150, 475, 500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	protected void initComponent() {
		pane = new JPanel();
		pane.setLayout(null);

		// initialize JButton
		btnreg = new JButton("入库");
		btnreturn = new JButton("返回");

		// initialize JLabel
		lprid = new JLabel("商品编号:");
		lpname = new JLabel("商品名称:");
		lpcount = new JLabel("商品数量:");
		lpperprice = new JLabel("商品单价:");
		lpenterdate = new JLabel("订货日期:");
		lpfrom = new JLabel("采购渠道:");
		lpby = new JLabel("采购人员:");
		lpexpiry = new JLabel("过期日期:");

		// initialize JTextField
		tprid = new JTextField();
		tpname = new JTextField();
		tpcount = new JTextField();
		tpperprice = new JTextField();
		tpenterdate = new JTextField();
		tpfrom = new JTextField();
		tpby = new JTextField();
		tpexpiry = new JTextField();
		
		// initialize font
		Font font = new Font("楷体", Font.BOLD, 14);
		lprid.setFont(font);
		lpname.setFont(font);
		lpcount.setFont(font);
		lpperprice.setFont(font);
		lpenterdate.setFont(font);
		lpfrom.setFont(font);
		lpby.setFont(font);
		lpexpiry.setFont(font);

		// initialize size and location
		lprid.setBounds(30, 110, 70, 25);
		lpname.setBounds(30, 145, 70, 25);
		lpby.setBounds(30, 215, 70, 25);
		lpenterdate.setBounds(30, 180, 70, 25);
		lpcount.setBounds(240, 215, 70, 25);
		lpperprice.setBounds(240, 145, 70, 25);
		lpfrom.setBounds(240, 180, 70, 25);
		lpexpiry.setBounds(240, 110, 70, 25);
		
		tprid.setBounds(100, 110, 120, 25);
		tpname.setBounds(100, 145, 120, 25);
		tpenterdate.setBounds(100, 180, 120, 25);
		tpby.setBounds(100, 215, 120, 25);
		tpcount.setBounds(310, 215, 120, 25);
		tpperprice.setBounds(310, 145, 120, 25);
		tpfrom.setBounds(310, 180, 120, 25);
		tpexpiry.setBounds(310, 110, 120, 25);
		
		btnreg.setBounds(100, 305, 100, 30);
		btnreturn.setBounds(260, 305, 100, 30);

		Chooser ser1 = Chooser.getInstance();
		ser1.register(tpenterdate);
		Chooser ser2 = Chooser.getInstance();
		ser2.register(tpexpiry);
		
		//限制TextField只接受数字输入
		tpcount.addKeyListener(new KeyAdapter(){  
            public void keyTyped(KeyEvent e) {  
                int keyChar = e.getKeyChar();                 
                if(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9){  
                }else{  
                    e.consume(); //关键，屏蔽掉非法输入  
                }  
            }  
        });
		
		btnreg.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!StringUtil.isPositiveInteger(tpcount.getText())) {
					JOptionPane.showMessageDialog(null, "数量格式不正确么,咱们咋搞着尼？");
				} else if (!(StringUtil.isPositiveInteger(tpperprice.getText())||StringUtil.isDecimal(tpperprice.getText()))) {
					JOptionPane.showMessageDialog(null, "价格格式不正确么,把该输对撒!");
				} else if (!StringUtil.isDate(tpexpiry.getText())) {
					JOptionPane.showMessageDialog(null, "过期日期格式也不正确,咋回事(用鼠标点文本框)？");
				} else if (!StringUtil.isDate(tpenterdate.getText())) {
					JOptionPane.showMessageDialog(null, "进货日期格式也不正确,咋回事(用鼠标点文本框)？");
				} 
				else {
					if (productService.isProductExist(tprid.getText())) {
						JOptionPane.showMessageDialog(null,
								"搞啥子嘛！该商品已经入库了!");
					} else {
						Timestamp now = new Timestamp(System.currentTimeMillis());
						Product m = new Product();
						m.setPrid(tprid.getText());
						m.setPname(tpname.getText());
						m.setPcount(Integer.parseInt(tpcount.getText()));
						m.setPperprice(Double.parseDouble(tpperprice.getText()));
						m.setPfrom(tpfrom.getText());
						m.setPby(tpby.getText());
						m.setPindate(now);
						try {
							m.setPenterdate(new java.sql.Date(ProjectSettings.sdf.parse(tpenterdate.getText()).getTime()));
							m.setPexpiry(new java.sql.Date(ProjectSettings.sdf.parse(tpexpiry.getText()).getTime()));
						} catch (ParseException e1) {
							e1.printStackTrace();
						}

						productService.addProduct(m);
						JOptionPane.showMessageDialog(null, "商品入库成功！");
						tprid.setText("");		
						tpname.setText("");		
						tpcount.setText("");	
						tpperprice.setText("");	
						tpenterdate.setText("");	
						tpfrom.setText("");	
						tpby.setText("");	
						tpexpiry.setText("");

					}
				}
			}
		});
		btnreturn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		// 公共部分
		pane.add(btnreg);
		pane.add(btnreturn);
		
		pane.add(lprid);
		pane.add(lpname);
		pane.add(lpcount);
		pane.add(lpperprice);
		pane.add(lpenterdate);
		pane.add(lpfrom);
		pane.add(lpby);
		pane.add(lpexpiry);
		pane.add(tprid);
		pane.add(tpname);
		pane.add(tpcount);
		pane.add(tpperprice);
		pane.add(tpenterdate);
		pane.add(tpfrom);
		pane.add(tpby);
		pane.add(tpexpiry);
	}
}
