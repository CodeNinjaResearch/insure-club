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

import za.co.iclub.pss.web.bean.IclubQuoteBean;
import za.co.iclub.pss.web.util.IclubWebHelper;
import za.co.iclub.pss.ws.model.IclubQuoteModel;

@ManagedBean(name = "iclubViewQuoteController")
@SessionScoped
public class IclubViewQuoteController implements Serializable {

	private static final long serialVersionUID = -6405843984156478759L;
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("iclub-web");
	protected static final Logger LOGGER = Logger.getLogger(IclubViewQuoteController.class);
	private static final String QUT_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubQuoteService/";
	private List<IclubQuoteBean> beans;
	private String sessionUserId;

	public List<IclubQuoteBean> getBeans() {

		WebClient client = IclubWebHelper.createCustomClient(QUT_BASE_URL + "get/userstatusId/" + getSessionUserId() + "/1");
		Collection<? extends IclubQuoteModel> models = new ArrayList<IclubQuoteModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubQuoteModel.class));
		client.close();

		beans = new ArrayList<IclubQuoteBean>();
		if (models != null && models.size() > 0) {
			for (IclubQuoteModel model : models) {
				IclubQuoteBean bean = new IclubQuoteBean();

				bean.setQId(model.getQId());
				bean.setQCrtdDt(model.getQCrtdDt());
				bean.setQIsMatched(model.getQIsMatched());
				bean.setQPrevPremium(model.getQPrevPremium());
				bean.setQValidUntil(model.getQValidUntil());
				bean.setQMobile(model.getQMobile());
				bean.setQEmail(model.getQEmail());
				bean.setQGenPremium(model.getQGenPremium());
				bean.setQNumItems(model.getQNumItems());
				bean.setQGenDt(model.getQGenDt());
				bean.setQNumber(model.getQNumber());
				bean.setIclubPersonByQCrtdBy(model.getIclubPersonByQCrtdBy());
				bean.setIclubProductType(model.getIclubProductType());
				bean.setIclubProductType(model.getIclubProductType());
				bean.setIclubInsurerMaster(model.getIclubInsurerMaster());
				bean.setIclubCoverType(model.getIclubCoverType());
				bean.setIclubQuoteStatus(model.getIclubQuoteStatus());
				bean.setIclubPersonByQPersonId(model.getIclubPersonByQPersonId());

				if (model.getIclubPolicies() != null && model.getIclubPolicies().length > 0) {
					String[] policies = new String[model.getIclubPolicies().length];
					int i = 0;
					for (String policy : model.getIclubPolicies()) {
						policies[i] = policy;
						i++;
					}
				}
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

}
