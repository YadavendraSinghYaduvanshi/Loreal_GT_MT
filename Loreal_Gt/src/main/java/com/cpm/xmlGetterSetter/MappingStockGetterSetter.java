package com.cpm.xmlGetterSetter;

import java.util.ArrayList;

/**
 * Created by yadavendras on 10-03-2017.
 */

public class MappingStockGetterSetter {

    String table_MAPPING_STOCK;

    ArrayList<String> SKU_CD = new ArrayList<>();

    public String getTable_MAPPING_STOCK() {
        return table_MAPPING_STOCK;
    }

    public void setTable_MAPPING_STOCK(String table_MAPPING_STOCK) {
        this.table_MAPPING_STOCK = table_MAPPING_STOCK;
    }

    public ArrayList<String> getSKU_CD() {
        return SKU_CD;
    }

    public void setSKU_CD(String SKU_CD) {
        this.SKU_CD.add(SKU_CD);
    }
}
