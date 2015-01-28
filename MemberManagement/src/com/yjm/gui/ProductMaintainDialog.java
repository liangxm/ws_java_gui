package com.yjm.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
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

import com.yjm.model.Product;
import com.yjm.util.DialogFactory;
/**
 * 商品维护界面
 * @author lxm
 * @version 2014-2-26 23:05:42
 */
public class ProductMaintainDialog extends AbstractDialog {

	private static final long serialVersionUID = -704448981711693816L;
	private JPanel pane;
	private DefaultTableModel dtm;
	private JTable tbl;
	private JScrollPane spane;
	private JTextField txtmtop;
	private JButton btnmsearch, btndelete, btnchange;
	private JRadioButton rbtnmphone, rbtnmname;

	private List<Product> list;
	private String tempmid;

	public ProductMaintainDialog(JFrame frame) {
		super(frame);
		initComponent();
		this.add(pane,BorderLayout.NORTH);
		this.add(spane,BorderLayout.CENTER);
		this.setTitle("商品信息维护");
		this.setBounds(290, 140, 700, 490);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	protected void initComponent() {
		pane = new JPanel();
		dtm = new DefaultTableModel();
		rbtnmphone = new JRadioButton("商品编号");
		rbtnmname = new JRadioButton("商品名称");
		txtmtop = new JTextField();
		btnmsearch = new JButton("查询");
		btnchange = new JButton("修改");
		btndelete = new JButton("删除");
		tbl = new JTable(dtm);
		spane = new JScrollPane(tbl);
		ButtonGroup bg = new ButtonGroup();
		Font font = new Font("楷体", Font.BOLD, 14);
		
		rbtnmphone.setFont(font);
		rbtnmname.setFont(font);
		btnmsearch.setFont(font);
		btnchange.setFont(font);
		btndelete.setFont(font);
		txtmtop.setColumns(10);
		
		bg.add(rbtnmphone);
		bg.add(rbtnmname);
		
		tbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int row = tbl.getSelectedRow();
				tempmid = tbl.getValueAt(row, 0).toString();
			}
		});

		dtm.addColumn("商品编号");
		dtm.addColumn("商品名称");
		dtm.addColumn("商品数量");
		dtm.addColumn("商品单价");
		dtm.addColumn("采购渠道");
		dtm.addColumn("采购人员");
		dtm.addColumn("订货日期");
		dtm.addColumn("入库日期");
		dtm.addColumn("过期日期");

		// 查询监听器
		btnmsearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearTable();
				if (txtmtop.getText().length() != 0) {
					if (rbtnmname.isSelected()) {
						list = productService.selectByName(txtmtop.getText());
					} else if (rbtnmphone.isSelected()) {
						Product p = productService.selectBymid(txtmtop.getText());
						list.add(p);
					}
				}
				//搜索文本为空，就查询所有
				else  {
					list = productService.selectAll();
				}
				fillTable();
			}
		});

		// 修改监听器
		btnchange.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tbl.getSelectedRow();
				if (tempmid != null && row != -1) {
					Product m = productService.selectBymid(tempmid);
					createJDialog(1,m).setVisible(true);
				} else
					JOptionPane.showMessageDialog(null, "请选中要修改的行!", "提示",
							JOptionPane.WARNING_MESSAGE);
			}
		});

		// 删除会员
		btndelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tempmid != null) {
					if (JOptionPane.showConfirmDialog(null, "您确定要删除该会员信息吗?") == JOptionPane.YES_OPTION) {
						int row = tbl.getSelectedRow();
						productService.deleteByID(tempmid);
						JOptionPane.showMessageDialog(null, "该会员删除成功！");
						dtm.removeRow(row);
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

	// 清空表格模型中的数据
	// 通知所有相关控件表格模型中数据的变动(刷新)
	private void clearTable() {
		if(list!=null)
			list.clear();
		dtm.getDataVector().clear();
		dtm.fireTableDataChanged();
	}

	private void fillTable() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (int i = 0; i < list.size(); i++) {
			Product m = list.get(i);
			dtm.addRow(new Object[] {m.getPrid(), m.getPname(), m.getPcount(),
					m.getPperprice(), m.getPfrom(), m.getPby(),
					sdf.format(m.getPenterdate()), sdf.format(m.getPindate()), sdf.format(m.getPexpiry())});
		}
	}
	
	private JDialog createJDialog(int type,Product product){
		return DialogFactory.createDialog(type, this, product);
	}
}
