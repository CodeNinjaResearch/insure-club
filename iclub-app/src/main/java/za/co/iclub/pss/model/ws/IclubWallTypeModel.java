package za.co.iclub.pss.model.ws;

import java.io.File;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubWallTypeModel")
public class IclubWallTypeModel {
	
	private Long wtId;
	private String wtShortDesc;
	private String wtLongDesc;
	private String wtStatus;
	
	public Long getWtId() {
		return wtId;
	}
	
	public void setWtId(Long wtId) {
		this.wtId = wtId;
	}
	
	public String getWtShortDesc() {
		return wtShortDesc;
	}
	
	public void setWtShortDesc(String wtShortDesc) {
		this.wtShortDesc = wtShortDesc;
	}
	
	public String getWtLongDesc() {
		return wtLongDesc;
	}
	
	public void setWtLongDesc(String wtLongDesc) {
		this.wtLongDesc = wtLongDesc;
	}
	
	public String getWtStatus() {
		return wtStatus;
	}
	
	public void setWtStatus(String wtStatus) {
		this.wtStatus = wtStatus;
	}
	
	public static void main(String[] args) {
		File file = new File("/noformat/github/iclub-app/src/main/java/za/co/iclub/pss/trans/wstoorm");
		for (File f : file.listFiles()) {
			File f2 = new File("/noformat/github/iclub-app/src/main/java/za/co/iclub/pss/trans/" + f.getName().toString().replace("Model", "").replace(".java", "Trans.java"));
			try {
				f.renameTo(f2);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(f);
		}
	}
}
