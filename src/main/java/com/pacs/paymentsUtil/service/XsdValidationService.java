package com.pacs.paymentsUtil.service;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;

public class XsdValidationService {
    public boolean validateXMLSchema(String xsdVal, String xmlPath, String xmlValue) throws IOException, SAXException {
        try {
            SchemaFactory factory =
                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new StreamSource(new StringReader(getFileFromLocation(xsdVal))));
            Validator validator = schema.newValidator();
            if(null!=xmlPath) {
                xmlValue=getFileFromLocation(xmlPath);
            }
            validator.validate(new StreamSource(new StringReader(xmlValue)));

        } catch (IOException e) {
            System.out.println("Exception: " + e.getMessage());
            throw e;
        } catch (SAXException e1) {
            System.out.println("SAX Exception: " + e1.getMessage());
            throw e1;
        }
        return true;

    }

    private String getFileFromLocation(String path){
        StringBuilder contentBuilder = new StringBuilder();
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while ((line = br.readLine()) != null) {
                contentBuilder.append(line).append(System.lineSeparator());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }
}
