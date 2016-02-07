package rajminster.comparcity;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Currency;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Zillow
{
    private static final DocumentBuilderFactory dbFac;
    private static final DocumentBuilder docBuilder;
    static
    {
        try
        {
            dbFac = DocumentBuilderFactory.newInstance();
            docBuilder = dbFac.newDocumentBuilder();
        }
        catch(ParserConfigurationException e)
        {
            throw new RuntimeException(e);
        }
    }
    private static final String DEEP_URL = "http://www.zillow.com/webservice/GetDeepSearchResults.htm";
    private static final String ZESTIMATE_URL = "http://www.zillow.com/webservice/GetZestimate.htm";

    private static final String ZWSID = "X1-ZWz1f5ef67eiob_26nu3";

    private static final NumberFormat nf = NumberFormat.getCurrencyInstance();

    // Returns Zestimate value for address.
    public static String getValuation(String address, String cityStateZip) throws SAXException, IOException
    {
        Document deepDoc = docBuilder.parse(DEEP_URL +
                "?zws-id=" + ZWSID +
                "&address=" + address +
                "&citystatezip=" + cityStateZip);
        Element firstResult = (Element)deepDoc.getElementsByTagName("result").item(0);
        String zpid = firstResult.getElementsByTagName("zpid").item(0).getTextContent();
        Document valueDoc = docBuilder.parse(ZESTIMATE_URL +
                "?zws-id=" + ZWSID +
                "&zpid=" + zpid);
        Element zestimate = (Element)valueDoc.getElementsByTagName("zestimate").item(0);
        Element amount = (Element)zestimate.getElementsByTagName("amount").item(0);
        String currency = amount.getAttribute("currency");
        nf.setCurrency(Currency.getInstance(currency));
        return nf.format(Double.parseDouble(amount.getTextContent()));
    }

    public static void main(String[] args) throws Throwable
    {
        String address = "2752 Bridgegate Trce";
        String cityStateZip = "Marietta GA 30068";
        System.out.println(getValuation(address, cityStateZip));
    }
}