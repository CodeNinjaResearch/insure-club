package za.co.iclub.pss.trans;

import javax.xml.bind.annotation.XmlRootElement;

import za.co.iclub.pss.model.ui.IclubPolicyStatusBean;
import za.co.iclub.pss.model.ws.IclubPolicyStatusModel;
import za.co.iclub.pss.orm.bean.IclubPolicyStatus;

@XmlRootElement(name = "IclubPolicyStatusModel")
public class IclubPolicyStatusTrans {
	public static IclubPolicyStatusBean fromWStoUI(IclubPolicyStatusModel model) {
		IclubPolicyStatusBean bean = new IclubPolicyStatusBean();
		bean.setPsId(model.getPsId());
		bean.setPsLongDesc(model.getPsLongDesc());
		bean.setPsShortDesc(model.getPsShortDesc());
		bean.setPsStatus(model.getPsStatus());
		return bean;
	}
	
	public static IclubPolicyStatusModel fromUItoWS(IclubPolicyStatusBean bean) {
		IclubPolicyStatusModel model = new IclubPolicyStatusModel();
		model.setPsId(bean.getPsId());
		model.setPsLongDesc(bean.getPsLongDesc());
		model.setPsShortDesc(bean.getPsShortDesc());
		model.setPsStatus(bean.getPsStatus());
		return model;
	}
	
	public static IclubPolicyStatusModel fromORMtoWS(IclubPolicyStatus bean) {
		IclubPolicyStatusModel model = new IclubPolicyStatusModel();
		model.setPsId(bean.getPsId());
		model.setPsLongDesc(bean.getPsLongDesc());
		model.setPsShortDesc(bean.getPsShortDesc());
		model.setPsStatus(bean.getPsStatus());
		return model;
	}
	
	public static IclubPolicyStatus fromWStoORM(IclubPolicyStatusModel model) {
		IclubPolicyStatus acctype = new IclubPolicyStatus();
		
		acctype.setPsId(model.getPsId());
		acctype.setPsLongDesc(model.getPsLongDesc());
		acctype.setPsShortDesc(model.getPsShortDesc());
		acctype.setPsStatus(model.getPsStatus());
		
		return acctype;
	}
}
