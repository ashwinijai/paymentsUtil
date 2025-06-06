package com.pacs.paymentsUtil.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pacs.paymentsUtil.Mapping;
import com.pacs.paymentsUtil.utility.CommonUtility;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class LookupService {

    public String getLookUpValue(String src, String target, JdbcTemplate jdbcTemplate) throws JsonProcessingException {
        String value=null;
        String mappingString = CommonUtility.getFileFromLocation("/Users/ashwinijayaraman/Downloads/paymentsUtil/src/main/resources/mapping.json");
        ObjectMapper objectMapper = new ObjectMapper();
        Mapping mapping = objectMapper.readValue(mappingString, Mapping.class);
        if (null != mapping && mapping.getMappingType().equals("LOOKUP")) {
            if(checkMandatoryFields(mapping)){
                value = getValue(mapping, jdbcTemplate, src, target);
            }
        }
        return value;
    }

    private String getValue(Mapping mapping, JdbcTemplate jdbcTemplate, String src, String target){
        String value=null;
        String query = "SELECT "+String.join(",",mapping.getLookupColumns())+" FROM "+mapping.getLookupTable() +" WHERE "+mapping.getLookupKey()+" = "+mapping.getLookupKeyValue();
        System.out.println("Query - "+ query);
        Map<String, Object> resultMap= jdbcTemplate.queryForMap(query);
       /* Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("DEST_CCY","CAD");
        resultMap.put("CR_DR_IND","CT");*/
        resultMap.forEach((key, val) -> System.out.println(key + " " + val));
        if(!resultMap.isEmpty()){
            value=concatenationLogic(resultMap, mapping, src, target );
            System.out.println("Final Value - "+ value);
        }
        return value;
    }

    private String concatenationLogic(Map<String, Object> resultMap, Mapping mapping, String source, String target){
        String finalValue = null;
        if(mapping.getFlowName().equals("RBCPACS")|| mapping.getFlowName().equals("PAINPACS")){
            finalValue = source+"_"+target+"_"+getValue(resultMap,mapping)+"_"+ currentTimestamp();
        }

        return finalValue;
    }

    private String currentTimestamp(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String timestampVal = now.format(formatter);
        System.out.println("Current timestamp - "+ timestampVal);
        return timestampVal;
    }
    private String getValue(Map<String, Object> resultMap, Mapping mapping){
        StringBuilder sb = new StringBuilder();
        for(String s:mapping.getLookupColumns()){
            sb.append(resultMap.get(s).toString());
        }
        return sb.toString();
    }


    private boolean checkMandatoryFields(Mapping mapping) {
        if (!StringUtils.isEmpty(mapping.getLookupTable()) &&
                !StringUtils.isEmpty(mapping.getLookupKey()) && (null != mapping.getLookupColumns() && mapping.getLookupColumns().length > 0) &&
                !StringUtils.isEmpty(mapping.getLookupKeyValue()) &&
                !StringUtils.isEmpty(mapping.getFlowName()) &&
                (!CollectionUtils.isEmpty(mapping.getTargetPath())
                        && !StringUtils.isEmpty(mapping.getTargetPath().get(0).getPath()))) {
            return true;
        }
        return false;
    }
}
