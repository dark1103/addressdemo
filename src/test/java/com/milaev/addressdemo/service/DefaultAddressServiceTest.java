package com.milaev.addressdemo.service;

import com.milaev.addressdemo.model.AddressHierarchyItem;
import com.milaev.addressdemo.model.AddressObject;
import com.milaev.addressdemo.repo.AddressHierarchyRepository;
import com.milaev.addressdemo.repo.AddressRepository;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Sets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * Тесты для {@link DefaultAddressService}
 *
 * @author Artemiy Milaev
 * @since 10.08.2023
 */
@ExtendWith(MockitoExtension.class)
class DefaultAddressServiceTest {

	@Mock
	private AddressRepository addressRepository;
	@Mock
	private AddressHierarchyRepository addressHierarchyRepository;
	@InjectMocks
	private DefaultAddressService addressService;

	@BeforeEach
	void setUp() {

	}

	/**
	 * Метод должен возвращать описание адресов на заданный период времени
	 */
	@Test
	void getAddressDescriptionForDate() {
		Date date = createTestDate();
		List<AddressObject> addressObjects = new LinkedList<>();
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 3; j++) {
				AddressObject addressObject = new AddressObject();
				addressObject.setObjectId(i);
				addressObject.setName(j == 0 ? "good_date_" + i : "bad_date_" + i);
				addressObject.setTypeName("type");
				addressObject.setStartDate(addDays(date, -j - 1));
				addressObject.setEndDate(addDays(date, -j));
				addressObjects.add(addressObject);
			}
		}
		when(addressRepository.getAddressObjects()).thenReturn(addressObjects);

		List<String> addressDescriptionForDate = addressService.getAddressDescriptionForDate(Sets.set(1L, 2L), date);
		assertThat(addressDescriptionForDate).containsOnly("1: type good_date_1", "2: type good_date_2");
	}

	private Date createTestDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2022, 10, 10);
		return calendar.getTime();
	}

	private Date addDays(Date date, int days) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, days);
		return c.getTime();
	}

	/**
	 * Метод должен выполнять фильтрацию по условию и возвращать полное описание
	 */
	@Test
	void getAddressChainsForFiltered() {
		List<AddressObject> addressObjects = new LinkedList<>();
		for (int i = 0; i < 10; i++) {
			AddressObject addressObject = new AddressObject();
			addressObject.setObjectId(i);
			addressObject.setName(i + "");
			addressObject.setTypeName("type");
			addressObject.setActive(true);
			addressObjects.add(addressObject);
		}
		when(addressRepository.getAddressObjects()).thenReturn(addressObjects);

		List<AddressHierarchyItem> addressHierarchyItems = new ArrayList<>();
		for (int j = 0; j < 2; j++) {
			for (int i = 0; i < 4; i++) {
				AddressHierarchyItem addressHierarchyItem = new AddressHierarchyItem();
				addressHierarchyItem.setActive(true);
				addressHierarchyItem.setParentObjectId(i - 1L + j * 5L);
				addressHierarchyItem.setObjectId(i + j * 5L);
				addressHierarchyItems.add(addressHierarchyItem);
			}
		}
		when(addressHierarchyRepository.getAddressHierarchy()).thenReturn(addressHierarchyItems);

		assertThat(
			addressService.getAddressChainsForFiltered(addressObject ->
				addressObject.getObjectId() > 1 &&  addressObject.getObjectId() < 4
			)
		).containsOnly("type 0, type 1, type 2", "type 0, type 1, type 2, type 3");
	}
}