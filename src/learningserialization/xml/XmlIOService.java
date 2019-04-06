package learningserialization.xml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import learningserialization.model.Employee;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author sscerbatiuc
 */
public class XmlIOService {

    private static final String EMPLOYEELIST_NODE = "employees";
    private static final String EMPLOYEE_NODE = "employee";
    private static final String NAME_NODE = "name";
    private static final String SURNAME_NODE = "surname";
    private static final String EMPLOYEDON_NODE = "employeed-on";

    public static void main(String[] args) throws ParserConfigurationException, TransformerException, SAXException, IOException {
        List<Employee> arrList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            arrList.add(Employee.getRandomEmployee());
        }
        writeToFile(arrList);
        readFromFile();
    }

    private static void writeToFile(List<Employee> empList) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory domBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder domBuilder = domBuilderFactory.newDocumentBuilder();
        Document doc = domBuilder.newDocument();
        Element root = doc.createElement(EMPLOYEELIST_NODE);
        doc.appendChild(root);
        for (Employee emp : empList) {
            Element employee = doc.createElement(EMPLOYEE_NODE);
            root.appendChild(employee);
            Element name = doc.createElement(NAME_NODE);
            name.appendChild(doc.createTextNode(emp.getName()));
            employee.appendChild(name);
            Element surname = doc.createElement(SURNAME_NODE);
            surname.setNodeValue(emp.getSurname());
            surname.appendChild(doc.createTextNode(emp.getSurname()));
            employee.appendChild(surname);
            Element employedDate = doc.createElement(EMPLOYEDON_NODE);
            employedDate.appendChild(doc.createTextNode(emp.getEmployeedOn().toString()));
            employee.appendChild(employedDate);
        }
        doc.getDocumentElement().normalize();

        // Writing to a file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult destination = new StreamResult(new File("D:\\employee.xml"));
        transformer.transform(source, destination);
    }

    private static void readFromFile() throws SAXException, IOException, ParserConfigurationException {
        File inputFile = new File("D:\\employee.xml");

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document xmlDoc = dBuilder.parse(inputFile);
        xmlDoc.getDocumentElement().normalize();

        System.out.println("Root element :" + xmlDoc.getDocumentElement().getNodeName());
        NodeList nList = xmlDoc.getElementsByTagName(EMPLOYEE_NODE);

        for (int index = 0; index < nList.getLength(); index++) {
            Node nNode = nList.item(index);
            System.out.println("\nCurrent Element :" + nNode.getNodeName());
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                System.out.println("Name : " + eElement.getElementsByTagName(NAME_NODE)
                        .item(0)
                        .getTextContent());
                System.out.println("Last Name : " + eElement.getElementsByTagName(SURNAME_NODE)
                        .item(0)
                        .getTextContent());
                System.out.println("Employed on : " + eElement.getElementsByTagName(EMPLOYEDON_NODE)
                        .item(0)
                        .getTextContent());
            }

        }
    }
}
