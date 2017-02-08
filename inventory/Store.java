package inventory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public abstract class Store implements StoreCapable {

	private static final String PRODUCT = "Product";
	private static final String NAME = "name";
	private static final String PRICE = "price";
	private static final String SIZE = "size";
	private static final String TYPE = "type";
	private static final String CD = "CD";
	private static final String BOOK = "Book";
	private static final String FILEPATH = "src/inventory/products.xml";

	private void saveToXml(Product product) {

		try {

			File xml = new File(FILEPATH);

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(xml);

			Element root = doc.getDocumentElement();

			// creating new product
			Element newChild = doc.createElement(PRODUCT);

			// adding name attribute to the product, and setting its value
			Attr attrName = doc.createAttribute(NAME);
			newChild.setAttributeNode(attrName);
			attrName.setValue(product.getName());

			// adding price attribute to the product and setting its value
			Attr attrPrice = doc.createAttribute(PRICE);
			newChild.setAttributeNode(attrPrice);
			attrPrice.setValue(String.valueOf(product.getPrice()));

			// adding size attribute to the product
			Attr attrSize = doc.createAttribute(SIZE);
			newChild.setAttributeNode(attrSize);

			// adding type attribute to the product
			Attr attrType = doc.createAttribute(TYPE);
			newChild.setAttributeNode(attrType);

			if (product instanceof BookProduct) {
				attrSize.setValue(String.valueOf(((BookProduct) product).getPageSize()));
				attrType.setValue(BOOK);

			} else if (product instanceof CDProduct) {
				attrSize.setValue(String.valueOf(((CDProduct) product).getNumOfTracks()));
				attrType.setValue(CD);
			}

			root.appendChild(newChild);

			DOMSource source = new DOMSource(doc);

			TransformerFactory transFactory = TransformerFactory.newInstance();
			transFactory.setAttribute("indent-number", new Integer(2));
			Transformer transformer = transFactory.newTransformer();
			StreamResult result = new StreamResult();
//			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			transformer.transform(source, result);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected abstract void storeProduct(Product product);

	protected Product createProduct(String type, String name, int price, int size) {
		if (type.equals(CD)) {
			return new CDProduct(name, price, size);
		} else {
			return new BookProduct(name, price, size);
		}
	}

	public List<Product> loadProducts() {

		List<Product> productList = new ArrayList<Product>();
		try {

			File xml = new File(FILEPATH);

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(xml);

			Element root = doc.getDocumentElement();

			NodeList productNodesList = root.getElementsByTagName(PRODUCT);

			for (int i = 0; i < productNodesList.getLength(); i++) {
				Node productNode = productNodesList.item(i);

				if (productNode.getNodeType() == Node.ELEMENT_NODE) {
					Element productElement = (Element) productNode;
					String name = productElement.getAttribute(NAME);
					String price = productElement.getAttribute(PRICE);
					String type = productElement.getAttribute(TYPE);
					String size = productElement.getAttribute(SIZE);
					productList.add(createProduct(type, name, Integer.parseInt(price), Integer.parseInt(size)));
				}

			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		return productList;
	}

	public void store(Product product) {
		saveToXml(product);
		storeProduct(product);
	}
}
