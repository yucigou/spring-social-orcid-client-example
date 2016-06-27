package org.jyougo.utils;

import java.io.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.social.orcid.jaxb.beans.OrcidMessage;
import org.springframework.social.orcid.jaxb.beans.impl.OrcidMessageImpl;
import org.w3c.dom.*;
import org.xml.sax.*;

/**
 * @author Yuci Gou
 *
 */
public class Utils {
    
    public final static String TAB_XML="  ";
    public final static String NL_XML="\n";
    public final static String SEP_VAL="%%%";
    public final static String NULL_INFO="NULL";
    public final static String PRESENCE_EUROPEPMC="EuropePMC"+SEP_VAL+"1";
    public final static String AUTHOR_SEPARATOR="    ";
    public final static String CITATION_AUTHOR_SEPARATOR=", ";

    private static String getOrcidMessageHeader(){
        String messageHeader="<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                              +NL_XML+"<orcid-message xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""
                              +" xsi:schemaLocation=\"http://www.orcid.org/ns/orcid https://github.com/ORCID/ORCID-Source/blob/master/orcid-model/src/main/resources/orcid-message-1.2.xsd\""
                              +" xmlns=\"http://www.orcid.org/ns/orcid\">"
                              + NL_XML+TAB_XML+"<message-version>1.2</message-version>";
        return messageHeader;
    }
    
    private static String SAMPLE_WORK = "<orcid-work><work-title><title>APITestTitle</title><subtitle>MySubtitle</subtitle></work-title><short-description>MyAbstract</short-description><work-citation><work-citation-type>formatted-apa</work-citation-type><citation>Mycorrectlyformattedcitation</citation></work-citation><work-type>journal-article</work-type><publication-date><year>2010</year><month>11</month></publication-date><work-external-identifiers><work-external-identifier><work-external-identifier-type>other-id</work-external-identifier-type><work-external-identifier-id>1234</work-external-identifier-id></work-external-identifier></work-external-identifiers><url>www.orcid.org</url><work-contributors><contributor><credit-name>Gou,Yuci</credit-name><contributor-attributes><contributor-sequence>first</contributor-sequence><contributor-role>author</contributor-role></contributor-attributes></contributor></work-contributors></orcid-work>";
    
    private static String getOrcidMessageFooter(){
        String messageFooter=NL_XML+"</orcid-message>";
        return messageFooter;
    }

    public static Document stringToDom(String xmlSource)
            throws SAXException, ParserConfigurationException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(new InputSource(new StringReader(xmlSource)));
    }
    
    public static String domToString(Document doc) throws TransformerException {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        StringWriter writer = new StringWriter();
        transformer.transform(new DOMSource(doc), new StreamResult(writer));
        String output = writer.getBuffer().toString().replaceAll("\n|\r", "");
        return output;
    }
    
    public static OrcidMessage domToOrcidMessage(Document doc) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(OrcidMessageImpl.class);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        OrcidMessage orcidMessage = (OrcidMessage) jaxbUnmarshaller.unmarshal(doc);
        return orcidMessage;
    }

    public static DOMSource getDomSource(Document document) {
        DOMSource domSource; 
        domSource = new DOMSource(document);
        return domSource;
    }
    
    public static String domSourceToString(DOMSource domSource) throws TransformerFactoryConfigurationError, TransformerException {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");        
        StringWriter sw = new StringWriter();
        StreamResult result = new StreamResult(sw);
        transformer.transform(domSource, result);
        String xmlString = sw.toString();
        return xmlString;
    }
    
    public static String _getWorksXml() {
        StringBuilder xml = new StringBuilder(getOrcidMessageHeader());
        xml.append(NL_XML+TAB_XML+"<orcid-profile>");
        xml.append(NL_XML+TAB_XML+TAB_XML+"<orcid-activities>");
        xml.append(NL_XML+TAB_XML+TAB_XML+TAB_XML+"<orcid-works>");
        xml.append(SAMPLE_WORK);
        xml.append(NL_XML+TAB_XML+TAB_XML+TAB_XML+"</orcid-works>");
        xml.append(NL_XML+TAB_XML+TAB_XML+"</orcid-activities>");
        xml.append(NL_XML+TAB_XML+"</orcid-profile>");
        xml.append(getOrcidMessageFooter());        
        return xml.toString();        
    }
    
    public static void main(String[] args)
            throws SAXException, ParserConfigurationException, IOException, TransformerException, JAXBException {
        String s = _getWorksXml();
        System.out.println(s);
        Document doc = stringToDom(s);
        String str = domToString(doc);
        System.out.println(str);
        
        System.out.println("Via DOMSource");
        System.out.println(domSourceToString(getDomSource(doc)));
        // OrcidMessage orcidMessage = domToOrcidMessage(doc);
        // System.out.println(orcidMessage);
        return;
    }
}
