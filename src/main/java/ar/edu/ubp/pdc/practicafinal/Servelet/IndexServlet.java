package ar.edu.ubp.pdc.practicafinal.Servelet;

import ar.edu.ubp.pdc.practicafinal.Beans.GeneroBean;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/index.jsp")
public class IndexServlet extends HttpServlet {
    @Override
    //El método doGet se ejecuta cuando el servlet recibe una solicitud HTTP GET
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<GeneroBean> generos = parseXML();  //Llama a parseXML para obtener una lista de objetos GeneroBean
        request.setAttribute("generos", generos); //Coloca la lista de GeneroBean como un atributo en el objeto request.
        request.getRequestDispatcher("/inicio.jsp").forward(request, response); //Redirige la solicitud a inicio.jsp, pasando el objeto request y response.
    }

    //Este método parseXML se encarga de leer y procesar un archivo XML para crear una lista de objetos GeneroBean.
    private List<GeneroBean> parseXML() throws IOException {
        List<GeneroBean> generos = new ArrayList<>(); //Inicializa una lista vacía de GeneroBean.

        //Intenta cargar los archivos XML y XSD desde los recursos del proyecto.
        try (InputStream xmlStream = getClass().getClassLoader().getResourceAsStream("xml" + File.separator + "generos.xml");
             InputStream xsdStream = getClass().getClassLoader().getResourceAsStream("xml" + File.separator + "generos.xsd")) {

            //Configura un DocumentBuilderFactory y un SchemaFactory para validar el XML contra el esquema XSD.
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new StreamSource(xsdStream));
            factory.setSchema(schema);

            //Crea un DocumentBuilder y parsea el XML en un Document.
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlStream);

            //Utiliza XPath para buscar todos los nodos <genero> en el documento XML.
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xPath = xPathFactory.newXPath();
            GeneroBean genero;
            NodeList generoNodes = (NodeList) xPath.evaluate("//genero", document, XPathConstants.NODESET);

            //Itera sobre los nodos <genero> y crea un GeneroBean para cada uno, añadiéndolos a la lista
            for (int i = 0; i < generoNodes.getLength(); i++) {
                Element generoElement = (Element) generoNodes.item(i);

                genero = new GeneroBean();
                genero.setNombre(generoElement.getTextContent());
                genero.setId(generoElement.getAttribute("id"));

                generos.add(genero);
            }
        }
        //Maneja cualquier excepción lanzada y la convierte en una RuntimeException.
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        //Devuelve la lista de GeneroBean.
        return generos;
    }
}
