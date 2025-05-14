package com.pacs.paymentsUtil.controller;


import com.pacs.paymentsUtil.model.ConvertorModel;
import com.pacs.paymentsUtil.model.FetchFilePathRequest;
import com.pacs.paymentsUtil.model.FetchFilePathResponse;
import com.pacs.paymentsUtil.model.XsdValidationModel;
import com.pacs.paymentsUtil.service.DBService;
import com.pacs.paymentsUtil.service.PainToPaymentService;
import com.pacs.paymentsUtil.service.XsdValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;

import java.io.IOException;

@RestController
@RequestMapping("/xmlUtil")
public class PaymentController {
    @Autowired
    XsdValidationService xsdValidationService;
    @Autowired
    PainToPaymentService painToPaymentService;
    @Autowired
    DBService dbService;

    @PostMapping("/validateXmlWithXsd")
    public ResponseEntity<Boolean> validateXsd(@RequestBody XsdValidationModel xsdValidationModel) throws IOException, SAXException {
        return new ResponseEntity<>(xsdValidationService.validateXMLSchema(xsdValidationModel.getXsdPath(), xsdValidationModel.getXmlPath()),HttpStatus.OK);
    }

    @PostMapping("/convertToPain")
    public ResponseEntity<String> convertToPain(@RequestBody ConvertorModel convertorModel){
        return new ResponseEntity<>( painToPaymentService.convertToPain(convertorModel.getPaymentType(), convertorModel.getXmlPath()),HttpStatus.OK);
    }

    @PostMapping("/getFilePaths")
    public ResponseEntity<FetchFilePathResponse> getFilePathFromDB(@RequestBody FetchFilePathRequest fetchFilePathRequest) {
        return new ResponseEntity<>(dbService.getFilePath(fetchFilePathRequest.getSourceSystem(), fetchFilePathRequest.getPaymentType()),HttpStatus.OK);
    }
}
