package com.yjm.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.yjm.model.Card;
import com.yjm.model.CardType;
import com.yjm.model.Member;
import com.yjm.util.StringUtil;
/**
 * 修改会员信息界面
 * @author lxm
 * @version 2014-2-25 22:11:51
 */
public class MemberModifyDialog extends AbstractDialog {

	private static final long serialVersionUID = -925990222748171644L;
	private JPanel pane;
	private JLabel lblmid;
	private JButton btnreg;
	private JButton btnreturn;
	private JLabel lblmindate;
	private JLabel lbltopup;
	private JLabel lblmphone;
	private JLabel lblmname;
	private JLabel lblmsex;
	private JLabel lblmage;
	private JLabel lblmbirth;
	private JLabel lblmaddr;

	private JTextField txtmbirth;
	private JTextField txtmphone;
	private JTextField txtmname;
	private JTextField txtmage;
	private JTextField txtmaddr;
	private JTextField txtmindate;
	private JTextField txttopup;

	private JCheckBox jcbhasCard;
	private JLabel lblCardNumber;
	private JLabel lblCardType;
	private JLabel lblexpiryDate;
	private JTextField txtCardNumber;
	private JComboBox txtCardType;
	private JTextField txtExpiryDate;

	private JRadioButton jrb1, jrb2;
	private ButtonGroup bg;
	private SimpleDateFormat sdf2;
	private Member member;

	public MemberModifyDialog(JDialog frame,Member member) {
		super(frame);
		this.member = member;
		initComponent();
		add(pane);
		setTitle("会员信息修改");
		setBounds(390, 150, 475, 500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	protected void initComponent() {
		pane = new JPanel();
		pane.setLayout(null);

		// initialize JButton
		btnreg = new JButton("更新");
		btnreturn = new JButton("返回");

		// initialize JLabel
		lblmid = new JLabel();
		lblmphone = new JLabel("手机号");
		lblmname = new JLabel("姓名");
		lblmsex = new JLabel("性别");
		lblmage = new JLabel("年龄");
		lblmbirth = new JLabel("生日");
		lblmaddr = new JLabel("地址");
		lblmindate = new JLabel("注册日期");
		lbltopup = new JLabel("充值");
		lblCardNumber = new JLabel("会员卡号");
		lblCardType = new JLabel("卡类型");
		lblexpiryDate = new JLabel("过期日期");

		// initialize JRadioButton
		jrb1 = new JRadioButton("男", true);
		jrb2 = new JRadioButton("女");
		bg = new ButtonGroup();
		jcbhasCard = new JCheckBox("是否开通会员卡");

		// initialize JTextField
		txtmphone = new JTextField();
		txtmname = new JTextField();
		txtmage = new JTextField();
		txtmbirth = new JTextField();
		txtmaddr = new JTextField();
		txtmindate = new JTextField();
		txttopup = new JTextField();
		txtCardNumber = new JTextField();

		List<CardType> cardTypes = cardTypeService.queryAll();
		String[] types = new String[cardTypes.size()];
		for (int i = 0; i < cardTypes.size(); i++) {
			types[i] = cardTypes.get(i).getTypename();
		}
		txtCardType = new JComboBox(types);
		txtExpiryDate = new JTextField();

		ComponentListener listener = new ComponentListener();

		// initialize font
		Font font = new Font("楷体", Font.BOLD, 14);
		lblmphone.setFont(font);
		lblmname.setFont(font);
		lblmsex.setFont(font);
		lblmage.setFont(font);
		lblmbirth.setFont(font);
		lblmaddr.setFont(font);
		lblmindate.setFont(font);
		lblCardNumber.setFont(font);
		lblCardType.setFont(font);
		lbltopup.setFont(font);
		lblexpiryDate.setFont(font);

		// initialize size and location
		lblmphone.setBounds(40, 60, 50, 25);
		txtmphone.setBounds(100, 60, 120, 25);
		lblmname.setBounds(40, 95, 50, 25);
		txtmname.setBounds(100, 95, 120, 25);
		lblmsex.setBounds(260, 130, 50, 25);
		jrb1.setBounds(325, 130, 50, 25);
		jrb2.setBounds(375, 130, 50, 25);
		lblmage.setBounds(260, 60, 50, 25);
		txtmage.setBounds(310, 60, 120, 25);
		lblmbirth.setBounds(40, 130, 50, 25);
		txtmbirth.setBounds(100, 130, 120, 25);
		lblmaddr.setBounds(260, 95, 50, 25);
		txtmaddr.setBounds(310, 95, 120, 25);
		btnreg.setBounds(100, 305, 100, 30);
		btnreturn.setBounds(260, 305, 100, 30);
		lblmindate.setBounds(40, 165, 70, 25);
		txtmindate.setBounds(100, 165, 120, 25);
		lbltopup.setBounds(260, 200, 250, 25);
		txttopup.setBounds(310, 200, 120, 25);
		jcbhasCard.setBounds(260, 165, 250, 25);
		lblCardNumber.setBounds(40, 200, 70, 25);
		txtCardNumber.setBounds(100, 200, 120, 25);
		lblCardType.setBounds(260, 235, 70, 25);
		txtCardType.setBounds(310, 235, 120, 25);
		lblexpiryDate.setBounds(40, 235, 70, 25);
		txtExpiryDate.setBounds(100, 235, 120, 25);

		Chooser ser1 = Chooser.getInstance();
		Chooser ser2 = Chooser.getInstance();
		ser1.register(txtmbirth);
		ser2.register(txtExpiryDate);
		txtmindate.setEditable(false);
		sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = sdf2.format(new java.util.Date());
		txtmindate.setText(strDate);

		lbltopup.setVisible(false);
		txttopup.setVisible(false);
		lblCardNumber.setVisible(false);
		txtCardNumber.setVisible(false);
		lblCardType.setVisible(false);
		txtCardType.setVisible(false);
		lblexpiryDate.setVisible(false);
		txtExpiryDate.setVisible(false);

		txtmage.addKeyListener(listener);
		txttopup.addKeyListener(listener);
		txtCardNumber.addKeyListener(listener);
		btnreg.addActionListener(listener);
		btnreturn.addActionListener(listener);
		jcbhasCard.addActionListener(listener);
		txtCardType.addActionListener(listener);
		
		dataInit();

		// 公共部分
		pane.add(lblmid);
		pane.add(btnreg);
		pane.add(btnreturn);
		pane.add(lblmphone);
		pane.add(lblmname);
		pane.add(lblmsex);
		pane.add(lblmage);
		pane.add(lblmbirth);
		pane.add(lblmaddr);
		pane.add(lblmindate);
		pane.add(txtmphone);
		pane.add(txtmname);
		pane.add(txtmage);
		pane.add(txtmbirth);
		pane.add(txtmaddr);
		pane.add(txtmindate);
		pane.add(lbltopup);
		pane.add(txttopup);
		pane.add(jcbhasCard);
		pane.add(lblCardNumber);
		pane.add(txtCardNumber);
		pane.add(lblCardType);
		pane.add(txtCardType);
		pane.add(lblexpiryDate);
		pane.add(txtExpiryDate);
		pane.add(jrb1);
		pane.add(jrb2);
		bg.add(jrb1);
		bg.add(jrb2);
	}
	
	private void dataInit(){
		txtmphone.setText(member.getMphone());
		txtmage.setText(String.valueOf(member.getMage()));
		txtmname.setText(member.getMname());
		txtmaddr.setText(member.getMaddr());
		txtmbirth.setText(sdf2.format(member.getMbirth()));
		if("男".equals(member.getMsex())){
			jrb1.setSelected(true);
		}else{
			jrb2.setSelected(true);
		}
		txtmindate.setText(sdf2.format(member.getMindate()));
		Card card = cardService.queryByOwner(member.getMname());
		if(card!=null){
			jcbhasCard.setVisible(false);
		}
	}

	//事件监听类
	class ComponentListener extends KeyAdapter implements ActionListener {

		@Override
		public void keyTyped(KeyEvent e) {
			Object obj = e.getSource();
			if (obj.equals(txtmage) || obj.equals(txttopup)
					|| obj.equals(txtCardNumber)) {
				int keyChar = e.getKeyChar();
				if (keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9) {
				} else {
					e.consume(); // 关键，屏蔽掉非法输入
				}
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
			if(obj.equals(txtCardType)){
				CardType cardType = cardTypeService.queryByName(txtCardType.getSelectedItem().toString());
				txttopup.setText(String.valueOf(cardType.getTypestartamt()));
			}
			else if(obj.equals(jcbhasCard)){
				if (jcbhasCard.isSelected()) {
					lbltopup.setVisible(true);
					txttopup.setVisible(true);
					lblCardNumber.setVisible(true);
					txtCardNumber.setVisible(true);
					lblCardType.setVisible(true);
					txtCardType.setVisible(true);
					lblexpiryDate.setVisible(true);
					txtExpiryDate.setVisible(true);
				} else {
					lbltopup.setVisible(false);
					txttopup.setVisible(false);
					lblCardNumber.setVisible(false);
					txtCardNumber.setVisible(false);
					lblCardType.setVisible(false);
					txtCardType.setVisible(false);
					lblexpiryDate.setVisible(false);
					txtExpiryDate.setVisible(false);
				}
			}
			else if(obj.equals(btnreturn)){
				dispose();
			}
			else if(obj.equals(btnreg)){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				if (!StringUtil.isMobileNO(txtmphone.getText())) {
					JOptionPane.showMessageDialog(null, "电话号码格式不正确么！");
				} else if (!StringUtil.isAge(txtmage.getText())) {
					JOptionPane.showMessageDialog(null, "年龄格式不规范么!");
				} else if (!StringUtil.isDate(txtmbirth.getText())) {
					JOptionPane.showMessageDialog(null, "生日要用鼠标点文本框再选择!");
				} else {
					try {
						Member m = new Member();
						m.setMid(member.getMid());
						m.setMphone(txtmphone.getText());
						m.setMname(txtmname.getText());
						m.setMsex(jrb1.isSelected() ? "男" : "女");
						m.setMage(Integer.parseInt(txtmage.getText()));
						m.setMbirth(sdf.parse(txtmbirth.getText()));
						m.setMaddr(txtmaddr.getText());
						m.setMindate(new java.util.Date());
						memberService.updateMember(m);
						
						if (jcbhasCard.isSelected()) {
							Card card = new Card();
							card.setCardnumber(txtCardNumber.getText());
							card.setCardtype(txtCardType.getSelectedItem().toString());
							card.setCardowner(txtmname.getText());
							card.setExpirydate(sdf.parse(txtExpiryDate.getText()));
							card.setCardbalace(Integer.parseInt(txttopup.getText()));
							cardService.addCard(card);
						}
						JOptionPane.showMessageDialog(null, "会员信息更新成功！");
						dispose();
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
			}
		}

	}
}
