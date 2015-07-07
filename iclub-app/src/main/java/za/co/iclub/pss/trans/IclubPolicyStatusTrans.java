package za.co.iclub.pss.trans;

import javax.xml.bind.annotation.XmlRootElement;

import za.co.iclub.pss.model.ui.IclubPolicyStatusBean;
import za.co.iclub.pss.model.ws.IclubPolicyStatusModel;
import za.co.iclub.pss.orm.bean.IclubPolicyStatus;

@XmlRootElement(name = "IclubPolicyStatusModel")
public class IclubPolicyStatusTrans {
	public IclubPolicyStatusBean fromWStoUI(IclubPolicyStatusModel model) {
		IclubPolicyStatusBean bean = new IclubPolicyStatusBean();
		bean.setPsId(model.getPsId().longValue());
		bean.setPsLongDesc(model.getPsLongDesc());
		bean.setPsShortDesc(model.getPsShortDesc());
		bean.setPsStatus(model.getPsStatus());
		return bean;
	}
	
	public IclubPolicyStatusModel fromUItoWS(IclubPolicyStatusBean bean) {
		IclubPolicyStatusModel model = new IclubPolicyStatusModel();
		model.setPsId(bean.getPsId().longValue());
		model.setPsLongDesc(bean.getPsLongDesc());
		model.setPsShortDesc(bean.getPsShortDesc());
		model.setPsStatus(bean.getPsStatus());
		return model;
	}
	
	public IclubPolicyStatusModel fromORMtoWS(IclubPolicyStatus bean) {
		IclubPolicyStatusModel model = new IclubPolicyStatusModel();
		model.setPsId(bean.getPsId().longValue());
		model.setPsLongDesc(bean.getPsLongDesc());
		model.setPsShortDesc(bean.getPsShortDesc());
		model.setPsStatus(bean.getPsStatus());
		return model;
	}
	
	public IclubPolicyStatus fromWStoORM(IclubPolicyStatusModel model) {
		IclubPolicyStatus acctype = new IclubPolicyStatus();
		
		acctype.setPsId(model.getPsId());
		acctype.setPsLongDesc(model.getPsLongDesc());
		acctype.setPsShortDesc(model.getPsShortDesc());
		acctype.setPsStatus(model.getPsStatus());
		
		return acctype;
	}
}
