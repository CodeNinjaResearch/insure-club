package za.co.iclub.pss.trans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubFullQuoteResponse")
public class IclubFullQuoteResponse {
	
	private String quoteNumber;
	private Double generatedPremium;
	
	public String getQuoteNumber() {
		return quoteNumber;
	}
	
	public void setQuoteNumber(String quoteNumber) {
		this.quoteNumber = quoteNumber;
	}
	
	public Double getGeneratedPremium() {
		return generatedPremium;
	}
	
	public void setGeneratedPremium(Double generatedPremium) {
		this.generatedPremium = generatedPremium;
	}
}
