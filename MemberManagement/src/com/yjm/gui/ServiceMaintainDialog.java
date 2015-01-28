package com.yjm.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.yjm.model.Services;
import com.yjm.util.DialogFactory;
/**
 * 服务项目修改对话框
 * @author lxm
 * @version 2014-2-28 22:34:12
 */
public class ServiceMaintainDialog extends AbstractDialog {
	
	private static final long serialVersionUID = 9170614754418888924L;
	private JPanel pane;
	private DefaultTableModel dtm;
	private JTable tbl;
	private JRadioButton rbtnmphone;
	private JRadioButton rbtnmname;
	private JTextField txtmtop;
	private JButton btnmsearch;
	private JButton btndelete;
	private JButton btnchange;
	private JScrollPane spane;
	private List<Services> listser;
	protected int mid2;
	int tempmid = 0;

	public ServiceMaintainDialog(JFrame frame) {
		super(frame);
		initComponent();
		this.add(pane);
		this.setBounds(290, 140, 700, 490);
		this.setTitle("服务信息查询");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	//组件初始化
	protected void initComponent(){
		pane = new JPanel();
		pane.setLayout(null);
		rbtnmphone = new JRadioButton("手机");
		rbtnmphone.setBounds(40, 10, 70, 25);
		rbtnmphone.setFont(new Font("楷体", Font.BOLD, 14));

		rbtnmname = new JRadioButton("姓名");
		rbtnmname.setBounds(115, 10, 70, 25);
		rbtnmname.setFont(new Font("楷体", Font.BOLD, 14));
		ButtonGroup bg = new ButtonGroup();
		bg.add(rbtnmphone);
		bg.add(rbtnmname);
		txtmtop = new JTextField();
		txtmtop.setBounds(200, 10, 160, 25);
		btnmsearch = new JButton("查询");
		btnmsearch.setFont(new Font("楷体", Font.BOLD, 14));
		btnmsearch.setBounds(385, 10, 80, 25);

		btnchange = new JButton("修改");
		btnchange.setFont(new Font("楷体", Font.BOLD, 14));
		btnchange.setBounds(480, 10, 80, 25);

		btndelete = new JButton("删除");
		btndelete.setFont(new Font("楷体", Font.BOLD, 14));
		btndelete.setBounds(575, 10, 80, 25);
		
		dtm = new DefaultTableModel();
		
		dtm.addColumn("编号");		
		dtm.addColumn("项目名称");
		dtm.addColumn("项目价格");
		dtm.addColumn("服务类型");
		
		rbtnmphone.setVisible(false);

		tbl = new JTable(dtm);
		tbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int row = tbl.getSelectedRow();
				tempmid = Integer.parseInt(tbl.getValueAt(row, 0).toString());
			}
		});

		spane = new JScrollPane(tbl);
		spane.setBounds(7, 50, 680, 400);

		pane.add(spane);

		// 查询监听器
		btnmsearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearTable();
				if (txtmtop.getText().length() != 0) {
					if (rbtnmname.isSelected()) {
						listser = servicesService.selectByName(txtmtop.getText());
					} 
				} else if (txtmtop.getText().length() == 0) {
					listser = servicesService.selectAll();
				}
				fillTable();
			}
		});
		
		//修改服务开始
		btnchange.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tempmid != 0) {
					Services s  = servicesService.selectByIDReturnServices(tempmid);
					createJDialog(1,s).setVisible(true);
					clearTable();
					fillTable();
				} else{
					JOptionPane.showMessageDialog(null, "请选中要修改的行!", "提示",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		// 删除服务
		btndelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (tempmid != 0) {
					if (JOptionPane
							.showConfirmDialog(null, "您确定要删除该服务信息吗?") == JOptionPane.YES_OPTION) {
						int row = tbl.getSelectedRow();
						int count = consumeService.selectCountByServicesNo(tempmid);
						if(count == 0){
							servicesService.deleteByID(tempmid);
							dtm.removeRow(row);
							JOptionPane.showMessageDialog(null, "该服务删除成功！");
						}else{
							JOptionPane.showMessageDialog(null, "该服务已经绑定到了"+count+"条消费记录上\n请先删除相关消费记录再删除本服务！");
						}
					}
				} else
					JOptionPane.showMessageDialog(null, "请选中要删除的行!", "提示",
							JOptionPane.WARNING_MESSAGE);
			}
		});
		
		//公共部分
		pane.add(rbtnmname);
		pane.add(rbtnmphone);
		pane.add(txtmtop);
		pane.add(btnmsearch);
		pane.add(btnchange);
		pane.add(btndelete);
	}
	
	public  void fillTable() {
		for (int i = 0; i < listser.size(); i++) {
			Services ser = listser.get(i);
			dtm.addRow(new Object[] {ser.getSerid(),ser.getSername(),ser.getSermoney(),ser.getSertype()});
		}
	}
	
	// 通知所有相关控件表格模型中数据的变动(刷新)
	public void clearTable() {
		if(listser!=null)
			listser.clear();
		dtm.getDataVector().clear();
		dtm.fireTableDataChanged();
	}
	
	private JDialog createJDialog(int type,Services service){
		return DialogFactory.createDialog(type, this, service);
	}
}
