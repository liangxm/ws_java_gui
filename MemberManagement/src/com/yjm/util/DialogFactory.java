package com.yjm.util;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.yjm.gui.AboutDialog;
import com.yjm.gui.CardMaintainDialog;
import com.yjm.gui.CardTypeAddDialog;
import com.yjm.gui.CardTypeMaintainDialog;
import com.yjm.gui.CardTypeModifyDialog;
import com.yjm.gui.ConsumeGuestDialog;
import com.yjm.gui.ConsumeMemberDialog;
import com.yjm.gui.ConsumeRecordModifyDialog;
import com.yjm.gui.ConsumeRecordQueryDialog;
import com.yjm.gui.EmployeeAddDialog;
import com.yjm.gui.EmployeeMaintainDialog;
import com.yjm.gui.EmployeeModifyDialog;
import com.yjm.gui.MemberMaintainDialog;
import com.yjm.gui.MemberModifyDialog;
import com.yjm.gui.MemberPurchaseQueryDialog;
import com.yjm.gui.MemberRegisterDialog;
import com.yjm.gui.NearBirthdayMemberDialog;
import com.yjm.gui.ProductAddDialog;
import com.yjm.gui.ProductMaintainDialog;
import com.yjm.gui.ProductModifyDialog;
import com.yjm.gui.RunningTotalDialog;
import com.yjm.gui.ServiceAddDialog;
import com.yjm.gui.ServiceMaintainDialog;
import com.yjm.gui.ServiceModifyDialog;
import com.yjm.model.CardType;
import com.yjm.model.Consume;
import com.yjm.model.Employee;
import com.yjm.model.Member;
import com.yjm.model.Product;
import com.yjm.model.Services;
/**
 * �Ի������ɹ���
 * @author lxm
 *
 */
public class DialogFactory {

	//������������JFrame�ĶԻ���
	public static JDialog createDialog(int type,JFrame frame){
		switch(type){
		case 1:
			return new MemberRegisterDialog(frame);
		case 2:
			return new MemberMaintainDialog(frame);
		case 3:
			return new ProductAddDialog(frame);
		case 4:
			return new ProductMaintainDialog(frame);
		case 5:
			return new EmployeeAddDialog(frame);
		case 6:
			return new EmployeeMaintainDialog(frame);
		case 7:
			return new ServiceAddDialog(frame);
		case 8:
			return new ServiceMaintainDialog(frame);
		case 9:
			return new MemberPurchaseQueryDialog(frame);
		case 10:
			return new ConsumeMemberDialog(frame);
		case 11:
			return new ConsumeRecordQueryDialog(frame);
		case 12:
			return new ConsumeGuestDialog(frame);
		case 13:
			return new RunningTotalDialog(frame);
		case 14:
			return new AboutDialog(frame);
		case 15:
			return new CardTypeAddDialog(frame);
		case 16:
			return new CardTypeMaintainDialog(frame);
		case 17:
			return new CardMaintainDialog(frame);
		case 18:
			return new NearBirthdayMemberDialog(frame);
		}
		return null;
	}
	
	//������������Member�ĶԻ���
	public static JDialog createDialog(int type,JDialog frame,Member member){
		switch(type){
		case 1:
			return new MemberModifyDialog(frame,member);
		}
		return null;
	}
	
	//������������Product�ĶԻ���
	public static JDialog createDialog(int type,JDialog frame,Product product){
		switch(type){
		case 1:
			return new ProductModifyDialog(frame,product);
		}
		return null;
	}
	
	//������������Employee�ĶԻ���
	public static JDialog createDialog(int type,JDialog frame,Employee employee){
		switch(type){
		case 1:
			return new EmployeeModifyDialog(frame,employee);
		}
		return null;
	}
	
	//������������JDialog�ĶԻ���
	public static JDialog createDialog(int type,JDialog frame,Services service){
		switch(type){
		case 1:
			return new ServiceModifyDialog(frame,service);
		}
		return null;
	}
	
	//������������JDialog�ĶԻ���
	public static JDialog createDialog(int type,JDialog frame,Consume consume){
		switch(type){
		case 1:
			return new ConsumeRecordModifyDialog(frame,consume);
		}
		return null;
	}
	
	//������������CardType�ĶԻ���
	public static JDialog createDialog(int type,JDialog frame,CardType cardType){
		switch(type){
		case 1:
			return new CardTypeModifyDialog(frame,cardType);
		}
		return null;
	}
}
