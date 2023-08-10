package com.milaev.addressdemo.repo;

import com.milaev.addressdemo.model.AddressHierarchyItem;

import java.util.List;

/**
 * Репозиторий иерархии адресов
 *
 * @author Artemiy Milaev
 * @since 09.08.2023
 */
public interface AddressHierarchyRepository {

	List<AddressHierarchyItem> getAddressHierarchy();
}
