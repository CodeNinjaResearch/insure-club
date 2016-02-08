package za.co.iclub.pss.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.log4j.Logger;

import za.co.iclub.pss.model.ui.IclubInsurerMasterBean;
import za.co.iclub.pss.model.ui.IclubQuoteBean;
import za.co.iclub.pss.model.ui.IclubQuoteStatusBean;
import za.co.iclub.pss.model.ws.IclubInsurerMasterModel;
import za.co.iclub.pss.model.ws.IclubQuoteModel;
import za.co.iclub.pss.model.ws.IclubQuoteStatusModel;
import za.co.iclub.pss.trans.IclubInsurerMasterTrans;
import za.co.iclub.pss.trans.IclubQuoteStatusTrans;
import za.co.iclub.pss.trans.IclubQuoteTrans;
import za.co.iclub.pss.util.IclubWebHelper;

@ManagedBean(name = "iclubViewQuoteController")
@SessionScoped
public class IclubViewQuoteController implements Serializable {

	private static final long serialVersionUID = -6405843984156478759L;
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("iclub-web");
	protected static final Logger LOGGER = Logger.getLogger(IclubViewQuoteController.class);
	private static final String QUT_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubQuoteService/";
	private static final String QUTS_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubQuoteStatusService/";
	private static final String IM_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubInsurerMasterService/";
	private List<IclubQuoteBean> beans;
	private List<IclubQuoteStatusBean> quoteStatusBeans;
	private List<IclubInsurerMasterBean> insurerMasterBeans;
	private String sessionUserId;

	public List<IclubQuoteBean> getBeans() {

		WebClient client = IclubWebHelper.createCustomClient(QUT_BASE_URL + "get/userstatusId/" + getSessionUserId() + "/1");
		Collection<? extends IclubQuoteModel> models = new ArrayList<IclubQuoteModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubQuoteModel.class));
		client.close();

		beans = new ArrayList<IclubQuoteBean>();
		if (models != null && models.size() > 0) {
			for (IclubQuoteModel model : models) {
				IclubQuoteBean bean = IclubQuoteTrans.fromWStoUI(model);

				beans.add(bean);
			}
		}
		return beans;
	}

	public String quoteActionListener(IclubQuoteBean bean) {
		IclubWebHelper.addObjectIntoSession("fullquote", bean);

		return "fq";
	}

	public String policyActionListener(IclubQuoteBean bean) {
		IclubWebHelper.addObjectIntoSession("fullquote", bean);

		return "pdash";
	}

	public void setBeans(List<IclubQuoteBean> beans) {
		this.beans = beans;
	}

	public String getSessionUserId() {

		Object sessUsrId = IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.user.id"));

		if (sessUsrId != null)
			sessionUserId = sessUsrId.toString();

		return sessionUserId;
	}

	public void setSessionUserId(String sessionUserId) {
		this.sessionUserId = sessionUserId;
	}

	public List<IclubQuoteStatusBean> getQuoteStatusBeans() {

		WebClient client = IclubWebHelper.createCustomClient(QUTS_BASE_URL + "list");
		Collection<? extends IclubQuoteStatusModel> models = new ArrayList<IclubQuoteStatusModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubQuoteStatusModel.class));
		client.close();
		quoteStatusBeans = new ArrayList<IclubQuoteStatusBean>();
		if (models != null && models.size() > 0) {
			for (IclubQuoteStatusModel model : models) {
				IclubQuoteStatusBean bean = IclubQuoteStatusTrans.fromWStoUI(model);

				quoteStatusBeans.add(bean);
			}
		}

		return quoteStatusBeans;
	}

	public void setQuoteStatusBeans(List<IclubQuoteStatusBean> quoteStatusBeans) {
		this.quoteStatusBeans = quoteStatusBeans;
	}

	public List<IclubInsurerMasterBean> getInsurerMasterBeans() {

		WebClient client = IclubWebHelper.createCustomClient(IM_BASE_URL + "list");
		Collection<? extends IclubInsurerMasterModel> models = new ArrayList<IclubInsurerMasterModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubInsurerMasterModel.class));
		client.close();
		insurerMasterBeans = new ArrayList<IclubInsurerMasterBean>();
		if (models != null && models.size() > 0) {
			for (IclubInsurerMasterModel model : models) {
				IclubInsurerMasterBean bean = IclubInsurerMasterTrans.fromWStoUI(model);

				insurerMasterBeans.add(bean);
			}
		}

		return insurerMasterBeans;
	}

	public void setInsurerMasterBeans(List<IclubInsurerMasterBean> insurerMasterBean) {
		this.insurerMasterBeans = insurerMasterBean;
	}

}
