package com.yjm.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Timestamp;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.yjm.model.Product;
import com.yjm.util.ProjectSettings;
/**
 * ��Ʒ��Ϣ�޸Ľ���
 * @author lxm
 * @version 2014-2-26 23:15:13
 */
public class ProductModifyDialog extends AbstractDialog {

	private static final long serialVersionUID = -925990222748171644L;
	private JPanel pane;

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

	private JButton btnreg;
	private JButton btnrturn;
	private Product product;

	public ProductModifyDialog(JDialog frame,Product product) {
		super(frame);
		this.product=product;
		initComponent();
		this.add(pane);
		this.setTitle("��Ա��Ϣά��");
		this.setBounds(390, 150, 475, 500);
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	protected void initComponent() {
		pane = new JPanel();
		pane.setLayout(null);
		// initialize JLabel
		lprid = new JLabel("��Ʒ���:");
		lpname = new JLabel("��Ʒ����:");
		lpcount = new JLabel("��Ʒ����:");
		lpperprice = new JLabel("��Ʒ����:");
		lpenterdate = new JLabel("��������:");
		lpfrom = new JLabel("�ɹ�����:");
		lpby = new JLabel("�ɹ���Ա:");
		lpexpiry = new JLabel("��������:");

		// initialize JTextField
		tprid = new JTextField();
		tpname = new JTextField();
		tpcount = new JTextField();
		tpperprice = new JTextField();
		tpenterdate = new JTextField();
		tpfrom = new JTextField();
		tpby = new JTextField();
		tpexpiry = new JTextField();
		
		btnreg = new JButton("����");
		btnrturn = new JButton("����");
		Font font = new Font("����", Font.BOLD, 14);

		// initialize font
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
		btnreg.setBounds(135, 305, 100, 30);
		btnrturn.setBounds(280, 305, 100, 30);
		
		//���ݳ�ʼ��
		dataInit();
		
		//�����ؼ���
		Chooser ser1 = Chooser.getInstance();
		ser1.register(tpenterdate);
		Chooser ser2 = Chooser.getInstance();
		ser2.register(tpexpiry);

		tprid.setEditable(false);
		
		//����TextFieldֻ������������
		tpcount.addKeyListener(new KeyAdapter(){  
            public void keyTyped(KeyEvent e) {  
                int keyChar = e.getKeyChar();                 
                if(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9){  
                }else{  
                    e.consume(); //�ؼ������ε��Ƿ�����  
                }  
            }  
        });
		
		btnrturn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		// ���¼�����
		btnreg.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "��ȷ��Ҫ���ĸû�Ա����Ϣ") == JOptionPane.YES_OPTION) {
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
					productService.updateProduct(m);
					JOptionPane.showMessageDialog(null, "��ϲ�����³ɹ�");
					dispose();
				}
			}
		});


		// ��������
		pane.add(btnreg);
		pane.add(btnrturn);

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
	
	//full data for components
	private void dataInit(){
		tprid.setText(product.getPrid());      
		tpname.setText(product.getPname());     
		tpcount.setText(String.valueOf(product.getPcount()));    
		tpperprice.setText(String.valueOf(product.getPperprice()));
		tpenterdate.setText(ProjectSettings.sdf.format(product.getPenterdate()));
		tpfrom.setText(product.getPfrom());    
		tpby.setText(product.getPby()); 
		tpexpiry.setText(ProjectSettings.sdf.format(product.getPexpiry()));
	}
}
