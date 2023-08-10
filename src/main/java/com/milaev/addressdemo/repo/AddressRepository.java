package com.milaev.addressdemo.repo;

import com.milaev.addressdemo.model.AddressObject;

import java.util.List;

/**
 * Репозиторий адресов
 *
 * @author Artemiy Milaev
 * @since 09.08.2023
 */
public interface AddressRepository {

	List<AddressObject> getAddressObjects();
}
