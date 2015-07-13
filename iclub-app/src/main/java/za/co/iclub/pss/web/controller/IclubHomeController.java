package za.co.iclub.pss.web.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import za.co.iclub.pss.util.IclubWebHelper;

@ManagedBean(name = "iclubHomeController")
@SessionScoped
public class IclubHomeController {
	public void initializePage() {
		IclubWebHelper.addObjectIntoSession("page_key", "/templates/home.xhtml");
	}
}
