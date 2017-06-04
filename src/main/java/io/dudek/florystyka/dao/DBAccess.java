//package io.dudek.florystyka.dao;
//
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;
//import org.xml.sax.SAXException;
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;
//import java.io.File;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//public class DBAccess {
//
//
//    private String path = "src/main/resources/";
//
//
//    public Map<String, String> getAccessData() throws ParserConfigurationException, SAXException, IOException {
//        ClassLoader classLoader = getClass().getClassLoader();
//        File f = new File(this.path + "db.xml");
//        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//        DocumentBuilder db = dbf.newDocumentBuilder();
//        Document doc = db.parse(f);
//
//        doc.getDocumentElement().normalize();
//
//        NodeList nodeList = doc.getElementsByTagName("database");
//
//        Map<String, String> access = new HashMap<>();
//
//        for(int i =0; i < nodeList.getLength(); i++) {
//            Node node = nodeList.item(i);
//            if(node.getNodeType() == Node.ELEMENT_NODE) {
//                Element element = (Element) node;
//                access.put("address", element.getElementsByTagName("address").item(0).getTextContent());
//                access.put("username", element.getElementsByTagName("username").item(0).getTextContent());
//                access.put("password", element.getElementsByTagName("password").item(0).getTextContent());
//                access.put("options", element.getElementsByTagName("options").item(0).getTextContent());
//            }
//        }
//
//        return access;
//
//    }
//
//}