package com.pacs.paymentsUtil;

import java.util.List;

public class Mapping {
    private String mappingType;
    private String lookupTable;
    private String[] lookupColumns;
    private String lookupKey;
    private String lookupKeyValue;
    private String flowName;
    private List<TargetPath> targetPath;

    public String getMappingType() {
        return mappingType;
    }

    public void setMappingType(String mappingType) {
        this.mappingType = mappingType;
    }

    public String getLookupTable() {
        return lookupTable;
    }

    public void setLookupTable(String lookupTable) {
        this.lookupTable = lookupTable;
    }

    public String[] getLookupColumns() {
        return lookupColumns;
    }

    public void setLookupColumns(String[] lookupColumns) {
        this.lookupColumns = lookupColumns;
    }

    public String getLookupKey() {
        return lookupKey;
    }

    public void setLookupKey(String lookupKey) {
        this.lookupKey = lookupKey;
    }

    public String getLookupKeyValue() {
        return lookupKeyValue;
    }

    public void setLookupKeyValue(String lookupKeyValue) {
        this.lookupKeyValue = lookupKeyValue;
    }

    public String getFlowName() {
        return flowName;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    public List<TargetPath> getTargetPath() {
        return targetPath;
    }

    public void setTargetPath(List<TargetPath> targetPath) {
        this.targetPath = targetPath;
    }
}
