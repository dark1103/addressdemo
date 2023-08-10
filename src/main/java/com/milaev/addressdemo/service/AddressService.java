package com.milaev.addressdemo.service;

import com.milaev.addressdemo.model.AddressObject;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

/**
 * Сервис для работы с адресами
 *
 * @author Artemiy Milaev
 * @since 10.08.2023
 */
public interface AddressService {

	/**
	 * Возвращает описания адресов на переданную дату
	 */
	List<String> getAddressDescriptionForDate(Set<Long> objectIds, Date date);

	/**
	 * Возвращает актуальные полные адреса, удволетворяющие условию
	 */
	List<String> getAddressChainsForFiltered(Predicate<AddressObject> predicate);
}
