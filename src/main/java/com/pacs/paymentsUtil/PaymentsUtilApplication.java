package com.pacs.paymentsUtil;

import com.pacs.paymentsUtil.service.PainToPaymentService;

public class PaymentsUtilApplication {

	public static void main(String[] args) {
        PainToPaymentService painToPaymentService = new PainToPaymentService();
        String pacsXml = painToPaymentService.convertToPain("PACS002","/Users/ashwinijayaraman/Downloads/sourceXml.xml" );
        System.out.println(pacsXml);
	}

}

