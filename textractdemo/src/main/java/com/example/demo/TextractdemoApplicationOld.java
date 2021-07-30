package com.example.demo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class TextractdemoApplicationOld {

	public static void mainTest(String[] args) {
		SpringApplication.run(TextractdemoApplicationOld.class, args);
		ObjectMapper om = new ObjectMapper();
		try {
			Root root = om.readValue(new File("src/main/resources/apiResponse.json"), Root.class);
			System.out.println("START");
			List<TableModel> tablesFromTextract = getTablesFromTextract(root);
			List<NetworkBean5> networkBean5List = new ArrayList<NetworkBean5>();
			Map<Long, Map<Long, String>> rowMap = tablesFromTextract.get(0).getRowMap();
			String currentTopRowText = "";

			for (Map<Long, String> currentRow : rowMap.values()) {
				NetworkBean5 curObj = new NetworkBean5();
				for (Map.Entry<Long, String> entry : currentRow.entrySet()) {
					System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
					if (entry.getKey() == 1) {
						try {
							if (entry.getValue().substring(1, 2).equals(")")) {
								currentTopRowText = entry.getValue();
							}
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						curObj.setMainArea(currentTopRowText);
						curObj.setManagementType(entry.getValue());
						try {
							if (entry.getValue().substring(1, 2).equals(")")) {
								curObj.setMainArea(currentTopRowText);
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					if (entry.getKey() == 2) {
						curObj.setLicencePrice(entry.getValue());
					}

					if (entry.getKey() == 3) {
						curObj.setSummerHighTariff(entry.getValue());
					}

					if (entry.getKey() == 4) {
						curObj.setSummerLowTariff(entry.getValue());
					}

					if (entry.getKey() == 5) {
						curObj.setWinterHighTariff(entry.getValue());
					}

					if (entry.getKey() == 6) {
						curObj.setWinterLowTariff(entry.getValue());
					}
				}
				if (!(curObj.getLicencePrice().isEmpty() && curObj.getSummerHighTariff().isEmpty()
						&& curObj.getSummerLowTariff().isEmpty() && curObj.getWinterHighTariff().isEmpty()
						&& curObj.getWinterLowTariff().isEmpty())) {
					curObj.setNetworkType("5");
					networkBean5List.add(curObj);

				} else {

				}

			

				System.out.println();
			}

			System.out.println("END");
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static List<TableModel> getTablesFromTextract(Root textractModel) {
		List<TableModel> tables = null;

		try {

			if (textractModel != null) {
				tables = new ArrayList<>();
				List<Block> tableBlocks = new ArrayList<>();
				Map<String, Block> blockMap = new HashMap<>();

				for (Block block : textractModel.getBlocks()) {

					if (block.getBlockType().equals("TABLE")) {
						tableBlocks.add(block);

					}
					blockMap.put(block.getId(), block);
				}

				for (Block blockModel : tableBlocks) {

					Map<Long, Map<Long, String>> rowMap = new HashMap<>();

					for (Relationship relationship : blockModel.getRelationships()) {

						if (relationship.getType().equals("CHILD")) {

							for (String id : relationship.getIds()) {

								Block cell = blockMap.get(id);

								if (cell.getBlockType().equals("CELL")) {

									long rowIndex = cell.getRowIndex();
									long columnIndex = cell.getColumnIndex();

									if (!rowMap.containsKey(rowIndex)) {
										rowMap.put(rowIndex, new HashMap<>());
									}

									Map<Long, String> columnMap = rowMap.get(rowIndex);
									columnMap.put(columnIndex, getCellText(cell, blockMap));
								}
							}
						}
					}
					tables.add(new TableModel(blockModel, rowMap));
				}
				System.out.println("row Map " + tables.toString());
			}
		} catch (Exception e) {
			System.out.println("Could not get table from textract model" + e.getStackTrace());
		}
		return tables;
	}

	private static String getCellText(Block cell, Map<String, Block> blockMap) {
		String text = "";

		try {

			if (cell != null && cell.getRelationships() != null && cell.getRelationships().size() > 0) {

				for (Relationship relationship : cell.getRelationships()) {

					if (relationship.getType().equals("CHILD")) {

						for (String id : relationship.getIds()) {

							Block word = blockMap.get(id);

							if (word.getBlockType().equals("WORD")) {
								text += word.getText() + " ";
							} else if (word.getBlockType().equals("SELECTION_ELEMENT")) {

//	                            if (word.getSelectionStatus().equals("SELECTED")) {
//	                                text += "X ";
//	                            }
							}
						}
					}
				}
			}

		} catch (Exception e) {
			System.out.println("Could not get cell text of table" + e.getStackTrace());
		}
		return text;
	}

}
