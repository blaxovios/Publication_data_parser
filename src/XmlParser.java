import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class XmlParser {

    /*
     * Get the Document Builder
     * Get Document
     * Normalize the xml structure
     * Get all the element by the tag name
     * */

    public static void main(String[] args) {

        //Get the Document Builder

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Get Document
            Document document = builder.parse(new File("dblp-2021-02-01.xml"));

            // Normalize the xml structure
            document.getDocumentElement().normalize();

            // Get all the element by the tag name

            NodeList publicationsList = document.getElementsByTagName("animation");
            for(int i = 0; i <publicationsList.getLength(); i++) {
                Node publication = publicationsList.item(i);
                if(publication.getNodeType() == Node.ELEMENT_NODE) {

                    Element laptopElement = (Element) publication;
                    System.out.println("Publication Date: " + laptopElement.getAttribute("name"));

                    NodeList laptopDetails =  publication.getChildNodes();
                    for(int j = 0; j < laptopDetails.getLength(); j++){
                        Node detail = laptopDetails.item(j);
                        if(detail.getNodeType() == Node.ELEMENT_NODE) {
                            Element detailElement = (Element) detail;
                            System.out.println("     " + detailElement.getTagName() + ": " + detailElement.getAttribute("value"));
                        }

                    }

                }
            }


        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}