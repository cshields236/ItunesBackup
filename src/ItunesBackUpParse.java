import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.lang.model.util.Elements;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class ItunesBackUpParse{

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        File xmlFile = new File("MusicLibrary1.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);

        doc.getDocumentElement().normalize();

        System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

        NodeList tracks = doc.getElementsByTagName("dict");

        for (int i = 0; i < tracks.getLength(); i++) {
            Node track = tracks.item(i);
            //System.out.println(track.getNodeName());

            NodeList ts = (NodeList) tracks.item(i);
            for (int j = 0; j < ts.getLength(); j++) {

                Node t = ts.item(j);
                //System.out.println(t.getNodeName());

                NodeList tw = (NodeList) ts.item(j);

                for (int p = 0; p < tw.getLength(); p++){
                    if(tw.item(p) != null) {
                        Node z = tw.item(p);
                        //System.out.println(z.getTextContent());
                        if (tw.item(p).getTextContent().equalsIgnoreCase("Artist"))
                        {
                            Node artist = tw.item(p).getNextSibling();
                           // System.out.println(artist.getTextContent());
                        }
                        if(tw.item(p).getTextContent().equalsIgnoreCase("Name")){
                            Node songName = tw.item(p).getNextSibling();
                            System.out.println(songName.getTextContent());
                        }
                    }
                }

                //Node y = tw.item(0);

                // System.out.println(y);


            }

        }

    }
}
