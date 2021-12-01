package com;

import com.model.BeanDefinition;
import com.model.Property;
import com.model.RefProperty;
import com.model.ValueProperty;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class XmlBeanDefinitionReader implements BeanDefinitionReader {

    private String path;

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public List<BeanDefinition> readBeanDefinitions(String path) { //TODO file parsing
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException("Oooops! Achtung! Your application is under attack!", e);
        }

        Document document = null;
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse(inputStream);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException("Couldn`t parse file: " + path, e);
        }
        return parsingDocument(document);
    }

    private List<BeanDefinition> parsingDocument(Document document) {
        List<BeanDefinition> list = new ArrayList<>();
        document.getDocumentElement().normalize();
        NodeList nodes = document.getElementsByTagName("bean");

        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                list.add(createBeanDefinition(element));
            }
        }
        return list;
    }

    private BeanDefinition createBeanDefinition(Element element) {
        BeanDefinition beanDefinition = new BeanDefinition();
        String id = element.getAttribute("id");
        String className = element.getAttribute("class");
        beanDefinition.setId(id);
        beanDefinition.setClassName(className);

        NodeList propertyList = element.getElementsByTagName("property");
        /*beanDefinition.setValueDependencies(getDependencies(propertyList, "value"));
        beanDefinition.setRefDependencies(getDependencies(propertyList, "ref"));*/
        beanDefinition.setValueProperties(getDependencies(propertyList, "value"));
        beanDefinition.setRefProperties(getDependencies(propertyList, "ref"));
        System.out.println(beanDefinition);
        System.out.println("----------------------------------");
        return beanDefinition;
    }

    /*private List<Property> getDependencies(NodeList propertyList, String flag) {
        Map<String, String> properties = new HashMap<>();

        for (int i = 0; i < propertyList.getLength(); i++) {
            Node node = propertyList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String name = element.getAttribute("name");
                String attribute = element.getAttribute(flag);

                properties.put("name", name);
                properties.put(flag, attribute);
            }
        }
        return properties;
    }*/

    private List<ValueProperty> getValueProperties(NodeList propertyList) {
        List<ValueProperty> valueProperties = new ArrayList<>();

    }

    private List<Property> getDependencies(NodeList propertyList, String flag) {
        List<Property> properties = new ArrayList<>();

        for (int i = 0; i < propertyList.getLength(); i++) {
            Node node = propertyList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String name = element.getAttribute("name");
                String attribute = element.getAttribute(flag);
                if (flag.equals("value")) {
                    if (!attribute.equals(flag)) {
                        continue;
                    }
                    properties.add(new ValueProperty(name, attribute));
                } else if (flag.equals("ref")) {
                    if (!attribute.equals(flag)) {
                        continue;
                    }
                    properties.add(new RefProperty(name, attribute));
                }
            }
        }
        return properties;
    }

    public static void main(String[] args) {
        new XmlBeanDefinitionReader().readBeanDefinitions("context.xml");
    }
}
