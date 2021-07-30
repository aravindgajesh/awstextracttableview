package com.example.demo.resource;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.NetworkBean5;
import com.example.demo.Root;
import com.example.demo.TableModel;
import com.example.demo.TextractdemoApplication;
import com.example.demo.services.NetworkTariffService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@RequestMapping("/networks")
public class NetworkTariffResource {

	@Autowired
	private NetworkTariffService networkTariffService;

	@GetMapping("/test/{id}")
	public List<NetworkBean5> findByNetworksTest(@PathVariable("id") Long id) {
		List<NetworkBean5> networkBean5List = new ArrayList<NetworkBean5>();
		return networkBean5List;
	}

	@GetMapping("/{id}")
	public List<NetworkBean5> findByNetworks(@PathVariable("id") Long id) {

		List<NetworkBean5> networkBean5List = new ArrayList<NetworkBean5>();
		ObjectMapper om = new ObjectMapper();
		try {
			Root root = om.readValue(new File("src/main/resources/apiResponse.json"), Root.class);
			System.out.println("START");
			List<TableModel> tablesFromTextract = networkTariffService.getTablesFromTextract(root);
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

		return networkBean5List;

	}
}
