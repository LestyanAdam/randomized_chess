package randomized_chess;

import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import java.io.*;
import java.net.*;

public class XMLHandler {
	
	public static Document readXMLDocumentFromFile(InputStream stream) throws Exception {

	    //creating document builder
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder = factory.newDocumentBuilder();

	    //building the document
	    Document document = builder.parse(stream);

	    //normalizing the XML Structure
	    document.getDocumentElement().normalize();

	    return document;
	}
	
	public String[] loadLogsIntoStringArray() throws Exception{
		
		Document document = readXMLDocumentFromFile(XMLHandler.class.getResourceAsStream( "/resources/logs.xml" ));
	    
	    NodeList nList = document.getElementsByTagName("logs");
	    
	    String logList = nList.item(0).getTextContent();
	    
	    return logList.split("\\|");
	}
	
	//removes all logs from the xml
	public void resetXML() throws Exception {
		Document document = readXMLDocumentFromFile(XMLHandler.class.getResourceAsStream( "/resources/logs.xml" ));
		
		Element root = document.getDocumentElement();
		
		while(root.hasChildNodes()) {
			root.removeChild(root.getFirstChild());
		}
		
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();
        DOMSource domSource = new DOMSource(document);
        
        URL resourceUrl = XMLHandler.class.getResource( "/resources/logs.xml" );
        File file = new File(resourceUrl.toURI());
        
        StreamResult streamResult = new StreamResult(new FileOutputStream(file));
        transformer.transform(domSource, streamResult);
	}
	
	//adds a log to the xml
	public void addLogToXML(String log) throws Exception{
		
		Document document = readXMLDocumentFromFile(XMLHandler.class.getResourceAsStream( "/resources/logs.xml" ));
		
	    Element root = document.getDocumentElement();
	    
	    Element newLog = document.createElement("log");
	    newLog.appendChild(document.createTextNode(log + "|"));
	    root.appendChild(newLog);
	    
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();
        DOMSource domSource = new DOMSource(document);
        
        URL resourceUrl = XMLHandler.class.getResource( "/resources/logs.xml" );
        File file = new File(resourceUrl.toURI());
        
        StreamResult streamResult = new StreamResult(new FileOutputStream(file));
        transformer.transform(domSource, streamResult);

	}

}
