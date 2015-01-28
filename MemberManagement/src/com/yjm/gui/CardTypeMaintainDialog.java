package com.yjm.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.yjm.model.CardType;
import com.yjm.util.DialogFactory;
/**
 * 服务项目修改对话框
 * @author lxm
 * @version 2014-2-28 22:34:12
 */
public class CardTypeMaintainDialog extends AbstractDialog {
	
	private static final long serialVersionUID = 9170614754418888924L;
	private JPanel pane;
	private DefaultTableModel dtm;
	private JTable tbl;
	private JButton btnmsearch;
	private JButton btndelete;
	private JButton btnchange;
	private JScrollPane spane;
	private List<CardType> listser = null;
	protected int mid2;

	public CardTypeMaintainDialog(JFrame frame) {
		super(frame);
		initComponent();
		this.add(pane,BorderLayout.NORTH);
		this.add(spane,BorderLayout.CENTER);
		this.setBounds(290, 140, 700, 490);
		this.setTitle("会员卡类型维护");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	//组件初始化
	protected void initComponent(){
		pane = new JPanel();

		btnmsearch = new JButton("查询");
		btnchange = new JButton("修改");
		btndelete = new JButton("删除");
		
		dtm = new DefaultTableModel();
		
		btnmsearch.setFont(new Font("楷体", Font.BOLD, 14));
		btnchange.setFont(new Font("楷体", Font.BOLD, 14));
		btndelete.setFont(new Font("楷体", Font.BOLD, 14));
		
		
		dtm.addColumn("编号");		
		dtm.addColumn("类型名称");
		dtm.addColumn("类型折扣");
		dtm.addColumn("预付金额");
		
		tbl = new JTable(dtm);
		spane = new JScrollPane(tbl);
		tbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// 查询监听器
		btnmsearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearTable();
				fillTable();
			}
		});
		
		//修改服务开始
		btnchange.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tbl.getSelectedRow();
				if (row != -1) {
					int typeid = Integer.parseInt(tbl.getValueAt(row, 0).toString());
					CardType s  = cardTypeService.queryByID(typeid);
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
				int row = tbl.getSelectedRow();
				if (row != -1) {
					int typeid = Integer.parseInt(tbl.getValueAt(row, 0).toString());
					if (JOptionPane
							.showConfirmDialog(null, "您确定要删除该卡类型吗?") == JOptionPane.YES_OPTION) {
						if(cardTypeService.deleteCardType(typeid)){
							dtm.removeRow(row);
							JOptionPane.showMessageDialog(null, "卡类型删除成功！");
						}else{
							JOptionPane.showMessageDialog(null, "卡类型删除失败！");
						}
					}
				} else
					JOptionPane.showMessageDialog(null, "请选中要删除的行!", "提示",
							JOptionPane.WARNING_MESSAGE);
			}
		});
	
		//公共部分
		pane.add(btnmsearch);
		pane.add(btnchange);
		pane.add(btndelete);
	}
	
	private void fillTable() {
		listser = cardTypeService.queryAll();
		for (int i = 0; i < listser.size(); i++) {
			CardType cardType = listser.get(i);
			dtm.addRow(new Object[] {cardType.getTypeid(),cardType.getTypename(),cardType.getTypediscount(),cardType.getTypestartamt()});
		}
	}
	
	// 清空表格模型中的数据
	// 通知所有相关控件表格模型中数据的变动(刷新)
	private void clearTable() {
		dtm.getDataVector().clear();
		dtm.fireTableDataChanged();
	}
	
	private JDialog createJDialog(int type,CardType cardType){
		return DialogFactory.createDialog(type, this, cardType);
	}
}
