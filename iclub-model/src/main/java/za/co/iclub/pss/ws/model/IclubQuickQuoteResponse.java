package za.co.iclub.pss.ws.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubQuickQuoteResponse")
public class IclubQuickQuoteResponse {

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
