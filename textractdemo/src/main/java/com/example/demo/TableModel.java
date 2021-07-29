package com.example.demo;

import java.util.Map;

public class TableModel {

private Block table;
private Map<Long, Map<Long, String>> rowMap;

public TableModel(Block table, Map<Long, Map<Long, String>> rowMap) {
    this.table = table;
    this.rowMap = rowMap;
}

public Block getTable() {
    return table;
}

public void setTable(Block table) {
    this.table = table;
}

public Map<Long, Map<Long, String>> getRowMap() {
    return rowMap;
}

public void setRowMap(Map<Long, Map<Long, String>> rowMap) {
    this.rowMap = rowMap;
}

@Override
public String toString() {
    return table.getId() + " - " + rowMap.toString();
}

}