package com.example.demo.services;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.Block;
import com.example.demo.Relationship;
import com.example.demo.Root;
import com.example.demo.TableModel;

@Service
public class NetworkTariffService {

	public List<TableModel> getTablesFromTextract(Root textractModel) {
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

	private String getCellText(Block cell, Map<String, Block> blockMap) {
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
