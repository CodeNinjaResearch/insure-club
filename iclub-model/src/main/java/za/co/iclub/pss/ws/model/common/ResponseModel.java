package za.co.iclub.pss.ws.model.common;

import java.io.File;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ResponseModel")
public class ResponseModel {
	private Integer statusCode;
	private String statusDesc;

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	
	public static void main(String[] args) {
		File folder = new File("/noformat/github/iclub/26-Feb-2015/insure-club/iclub-web/src/main/java/za/co/iclub/pss/web/bean");
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
		    if (file.isFile()) {
		       /* 
		    	System.out.println("<bean id=\""+file.getName().replace(".java", "")+"\" class=\"za.co.iclub.pss.ws.service."+file.getName().replace(".java", "")+"\"+>");
		    	System.out.println("<property name=\""+(Character.toLowerCase(file.getName().replace("Service.java", "DAO").charAt(0)) + file.getName().replace("Service.java", "DAO").substring(1))+"\" ref=\""+(Character.toUpperCase(file.getName().replace("Service.java", "DAO").charAt(0)) + file.getName().replace("Service.java", "DAO").substring(1))+"\" />");
		    	System.out.println("<property name=\"iclubCommonDAO\" ref=\"IclubCommonDAO\" />");
		    	System.out.println("</bean>");
		    	*/
		    	
		    		    System.out.println(file.getName().replace("Iclub","").replace("Bean.java", "").toLowerCase()+"="+file.getName().replace("Bean.java", "").replace("Iclub",""));
		    			System.out.println(file.getName().replace("Iclub","").replace("Bean.java", "").toLowerCase()+".create."+"shortdesc=Kurz schreibung");
		    			System.out.println(file.getName().replace("Iclub","").replace("Bean.java", "").toLowerCase()+".create."+"longdesc=Lange schreibung");
		    			System.out.println(file.getName().replace("Iclub","").replace("Bean.java", "").toLowerCase()+".create."+"status=Stellung");
		    			System.out.println(file.getName().replace("Iclub","").replace("Bean.java", "").toLowerCase()+".edit."+"id=Id");
		    			System.out.println(file.getName().replace("Iclub","").replace("Bean.java", "").toLowerCase()+".edit."+"shortdesc=Kurz schreibung");
		    			System.out.println(file.getName().replace("Iclub","").replace("Bean.java", "").toLowerCase()+".edit."+"longdesc=Lange schreibung");
		    			System.out.println(file.getName().replace("Iclub","").replace("Bean.java", "").toLowerCase()+".edit."+"status=Stellung");
		    			System.out.println(file.getName().replace("Iclub","").replace("Bean.java", "").toLowerCase()+".view."+".id=Id");
		    			System.out.println(file.getName().replace("Iclub","").replace("Bean.java", "").toLowerCase()+".view."+".shortdesc=Kurz schreibung");
		    			System.out.println(file.getName().replace("Iclub","").replace("Bean.java", "").toLowerCase()+".view."+".longdesc=Lange schreibung");
		    			System.out.println(file.getName().replace("Iclub","").replace("Bean.java", "").toLowerCase()+".view."+".status=Stellung");
		    			System.out.println();
		    			System.out.println();
		    	
		    
		    }
		}
	}

}
