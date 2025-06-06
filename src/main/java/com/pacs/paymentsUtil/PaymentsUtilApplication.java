package com.pacs.paymentsUtil;

import com.pacs.paymentsUtil.service.FileProcessorService;
import com.pacs.paymentsUtil.service.LookupService;
import com.pacs.paymentsUtil.service.PainToPaymentService;
import com.pacs.paymentsUtil.service.XsdValidationService;
import org.xml.sax.SAXException;

import java.beans.XMLDecoder;
import java.io.IOException;

public class PaymentsUtilApplication {

	public static void main(String[] args) throws IOException, SAXException {
       /* PainToPaymentService painToPaymentService = new PainToPaymentService();
        String pacsXml = painToPaymentService.convertToPain("PACS002","/Users/ashwinijayaraman/Downloads/sourceXml.xml" );
        System.out.println(pacsXml);
        FileProcessorService fileProcessorService = new FileProcessorService();
        fileProcessorService.placeFileInLocation("/Users/ashwinijayaraman/Downloads",pacsXml,"pacs002.xml");
        XsdValidationService xsdValidationService = new XsdValidationService();
        xsdValidationService.validateXMLSchema("/Users/ashwinijayaraman/Downloads/archive_payments_clearing_and_settlement_9_9b41eab473/pacs.002.001.10.xsd",
                "/Users/ashwinijayaraman/Downloads/pacs002.xml", null);*/
        LookupService lookupService = new LookupService();
        lookupService.getLookUpValue("RBC", "PACS002", null);

	}

}

