package com.milaev.addressdemo.service;

import com.milaev.addressdemo.model.AddressHierarchyItem;
import com.milaev.addressdemo.model.AddressObject;
import com.milaev.addressdemo.repo.AddressHierarchyRepository;
import com.milaev.addressdemo.repo.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Реализация сервиса для работы с адресами
 *
 * @author Artemiy Milaev
 * @since 10.08.2023
 */
@Service
public class DefaultAddressService implements AddressService {

	private final AddressRepository addressRepository;
	private final AddressHierarchyRepository addressHierarchyRepository;

	public DefaultAddressService(AddressRepository addressRepository, AddressHierarchyRepository addressHierarchyRepository) {
		this.addressRepository = addressRepository;
		this.addressHierarchyRepository = addressHierarchyRepository;
	}

	@Override
	public List<String> getAddressDescriptionForDate(Set<Long> objectIds, Date date) {
		Map<Long, AddressObject> addressObjectMap = addressRepository.getAddressObjects().stream()
			.filter(addressObject -> isActiveOnDate(addressObject, date))
			.collect(Collectors.toMap(AddressObject::getObjectId, Function.identity()));
		return objectIds.stream()
			.map(addressObjectMap::get)
			.map(addressObject -> addressObject.getObjectId() + ": " + addressObject.getDescription())
			.toList();
	}

	private boolean isActiveOnDate(AddressObject addressObject, Date date) {
		return date.compareTo(addressObject.getStartDate()) >= 0 && date.compareTo(addressObject.getEndDate()) <= 0;
	}

	@Override
	public List<String> getAddressChainsForFiltered(Predicate<AddressObject> predicate) {
		Map<Long, AddressHierarchyItem> hierarchyMap = getActualHierarchyMap();
		Map<Long, AddressObject> addressesMap = getActualAddressesMap();
		return addressesMap.values().stream()
			.filter(predicate)
			.map(addressObject -> getAddressChain(addressObject.getObjectId(), hierarchyMap))
			.map(addressChain ->
				addressChain.stream()
					.map(hierarchyItem -> addressesMap.get(hierarchyItem.getObjectId()).getDescription())
					.collect(Collectors.joining(", "))
			).toList();
	}

	private List<AddressHierarchyItem> getAddressChain(Long objectId, Map<Long, AddressHierarchyItem> hierarchyMap) {
		List<AddressHierarchyItem> addressChain = new ArrayList<>();
		while (objectId != null) {
			AddressHierarchyItem hierarchyItem = hierarchyMap.get(objectId);
			if (hierarchyItem == null) {
				break;
			}
			addressChain.add(hierarchyItem);
			objectId = hierarchyItem.getParentObjectId();
		}
		Collections.reverse(addressChain);
		return addressChain;
	}

	private Map<Long, AddressObject> getActualAddressesMap() {
		return addressRepository.getAddressObjects().stream()
			.filter(AddressObject::isActive)
			.collect(Collectors.toMap(AddressObject::getObjectId, Function.identity()));
	}

	private Map<Long, AddressHierarchyItem> getActualHierarchyMap() {
		return addressHierarchyRepository.getAddressHierarchy().stream()
			.filter(AddressHierarchyItem::isActive)
			.collect(Collectors.toMap(AddressHierarchyItem::getObjectId, Function.identity()));
	}
}
