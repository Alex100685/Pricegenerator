package ua.autoshop.utils.currencyrate;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Пользователь on 24.03.2016.
 */
public class CurrencyRateReader {

    private static final String currencyUrl = "http://bank.gov.ua/control/uk/curmetal/currency/search?formType=searchFormDate&time_step=daily&date=01.01.2105&execute&outer=xml";

    private static String getCurrencyXml() {
        String xml = null;
        URL url = null;
        URLConnection urlConn = null;
        InputStreamReader inStream = null;
        BufferedReader buff = null;
        try {
            url = new URL(currencyUrl);
            urlConn = url.openConnection();
            inStream = new InputStreamReader(
                    urlConn.getInputStream());
            buff = new BufferedReader(inStream);
            StringBuilder sb = new StringBuilder();

            while ((xml = buff.readLine())!=null){
                sb.append(xml);
            }
            xml = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xml;
    }

    public static CurrencyList getCurrencies() throws JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(CurrencyList.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        String data = getCurrencyXml();
        StringReader reader = new StringReader(data);
        CurrencyList currencyList = (CurrencyList) jaxbUnmarshaller.unmarshal(reader);
        return currencyList;
    }

}

