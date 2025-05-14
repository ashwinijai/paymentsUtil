package com.pacs.paymentsUtil.model;

import lombok.Data;

@Data
public class FetchFilePathRequest {
    private String sourceSystem;
    private String paymentType;
}
