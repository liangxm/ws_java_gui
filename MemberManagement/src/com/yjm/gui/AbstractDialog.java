package com.yjm.gui;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.yjm.service.CardService;
import com.yjm.service.CardTypeService;
import com.yjm.service.ConsumeService;
import com.yjm.service.EmployeeService;
import com.yjm.service.MemberService;
import com.yjm.service.ProductService;
import com.yjm.service.ServicesService;
/**
 * 所有对话框的顶层接口 
 * @author lxm
 * @date 2014-3-9 11:47:04
 */
public abstract class AbstractDialog extends JDialog {

	private static final long serialVersionUID = -7879443611137864054L;
	
	public AbstractDialog() {}
	
	public AbstractDialog(JFrame frame) {
		super(frame,true);
	}
	
	public AbstractDialog(JDialog frame){
		super(frame, true);
	}
	
	protected ConsumeService consumeService = ConsumeService.getInstace();
	protected EmployeeService employeeService = EmployeeService.getInstace();
	protected MemberService memberService = MemberService.getInstace();
	protected ProductService productService = ProductService.getInstace();
	protected ServicesService servicesService = ServicesService.getInstace();
	protected CardService cardService = CardService.getInstance();
	protected CardTypeService cardTypeService = CardTypeService.getInstance();
	
	protected abstract void initComponent();
}
