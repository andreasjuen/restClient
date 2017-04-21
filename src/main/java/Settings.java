
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.util.Iterator;
import javax.swing.text.Element;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author juena
 */
public class Settings {

    private final String url_Wetter = "http://www.globalbee.at/Andreas/POS1_STOLZ/settingsWetterstation.xml";
    private final String methode_Wetter = "GET";
    private final String contentType_Wetter = "text.xml";

    private MyRESTClient myRESTClient;

    public Settings() {
        myRESTClient = new MyRESTClient();
    }

    public boolean setToOf_XML(City city) {

        String bufferXML = null;
        try {
            bufferXML = myRESTClient.getRequest(url_Wetter, methode_Wetter, contentType_Wetter);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (RuntimeException e) {
            e.printStackTrace();
            return false;
        }

        System.out.println(bufferXML);

        org.dom4j.Document document = null;
        try {
            SAXReader reader = new SAXReader();
            InputStream stream = new ByteArrayInputStream(bufferXML.getBytes(UTF_8));
            document = reader.read(stream);
        } catch (DocumentException e) {
            e.printStackTrace();
            return false;
        }

        org.dom4j.Element classElement = document.getRootElement();

        Iterator itr = classElement.elements().iterator();
        Element element = null;

        while (itr.hasNext()) {
            Object debel = itr.next();
            if (debel instanceof org.dom4j.Element) {
                org.dom4j.Element debelElement = (org.dom4j.Element) debel;
                System.out.println(debelElement.getName());

                switch (debelElement.attributeValue("type")) {
                    case "city":
                        Iterator itrNote = debelElement.elementIterator();
                        while (itrNote.hasNext()) {
                            Object objNode = itrNote.next();
                            if (objNode instanceof org.dom4j.Node) {
                                org.dom4j.Node nodeZW = (org.dom4j.Node) objNode;
                                switch (nodeZW.getName()) {
                                    case "id":
                                        int id;
                                        try {
                                            id = Integer.parseInt(nodeZW.getStringValue());
                                        } catch (NumberFormatException e) {
                                            e.printStackTrace();
                                            return false;
                                        }

                                        city.setId(id);
                                        break;
                                    case "name":
                                        city.setName(nodeZW.getStringValue());
                                        break;
                                    case "country":
                                        city.setCountry(nodeZW.getStringValue());
                                        break;
                                }
                            }
                        }
                        break;
                }
            }
        }
        return true;
    }

}
