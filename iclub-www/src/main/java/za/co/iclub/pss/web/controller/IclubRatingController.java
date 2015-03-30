package za.co.iclub.pss.web.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import za.co.iclub.pss.web.bean.IclubRateEngineBean;

@ManagedBean(name = "iclubRatingController")
@SessionScoped
public class IclubRatingController {
	private List<IclubRateEngineBean> beans;
	private String selRateType;

	public String refreshGrid() {
		return "";
	}

	public List<IclubRateEngineBean> getBeans() {
		return beans;
	}

	public void setBeans(List<IclubRateEngineBean> beans) {
		this.beans = beans;
	}

	public String getSelRateType() {
		return selRateType;
	}

	public void setSelRateType(String selRateType) {
		this.selRateType = selRateType;
	}

}
