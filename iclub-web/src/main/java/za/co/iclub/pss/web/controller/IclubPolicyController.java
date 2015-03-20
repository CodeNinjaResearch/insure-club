package za.co.iclub.pss.web.controller;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;

import za.co.iclub.pss.web.bean.IclubInsuranceItemBean;

@ManagedBean(name = "iclubPolicyController")
@SessionScoped
public class IclubPolicyController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1299854691643272437L;
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("iclub-web");
	protected static final Logger LOGGER = Logger.getLogger(IclubPolicyController.class);
	
	private List< IclubInsuranceItemBean> beans;

	public List<IclubInsuranceItemBean> getBeans() {
		
		
		
		return beans;
	}

	public void setBeans(List<IclubInsuranceItemBean> beans) {
		this.beans = beans;
	}
	
}
