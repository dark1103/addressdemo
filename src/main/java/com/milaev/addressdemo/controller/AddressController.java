package com.milaev.addressdemo.controller;

import com.milaev.addressdemo.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Контроллер для адресов
 *
 * @author Artemiy Milaev
 * @since 10.08.2023
 */
@RestController
@RequestMapping("address")
public class AddressController {
	private static final String DRIVEWAY_TYPE = "проезд";

	private final AddressService addressService;

	@Autowired
	public AddressController(AddressService addressService) {
		this.addressService = addressService;
	}

	@GetMapping("/getAddressDescriptionForDate")
	public List<String> getAddressDescriptionForDate(
		@RequestParam Set<Long> objectIds,
		@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date
	) {
		return addressService.getAddressDescriptionForDate(objectIds, date);
	}

	@GetMapping("/getAddressChainsForDriveway")
	public List<String> getAddressChainsForDriveway() {
		return addressService.getAddressChainsForFiltered(addressObject -> DRIVEWAY_TYPE.equals(addressObject.getTypeName()));
	}
}
