package com.milaev.addressdemo.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

/**
 * Набор элементов иерархии
 *
 * @author Artemiy Milaev
 * @since 10.08.2023
 */
@XmlRootElement(name = "ITEMS")
@XmlAccessorType(XmlAccessType.FIELD)
public class AddressHierarchy {
	@XmlElement(name = "ITEM")
	private List<AddressHierarchyItem> addressHierarchyItems;

	public List<AddressHierarchyItem> getAddressHierarchyItems() {
		return addressHierarchyItems;
	}

	public void setAddressHierarchyItems(List<AddressHierarchyItem> addressHierarchyItems) {
		this.addressHierarchyItems = addressHierarchyItems;
	}
}