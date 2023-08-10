package com.milaev.addressdemo.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

/**
 * Набор адресов
 *
 * @author Artemiy Milaev
 * @since 09.08.2023
 */
@XmlRootElement(name = "ADDRESSOBJECTS")
@XmlAccessorType(XmlAccessType.FIELD)
public class AddressObjects {
	@XmlElement(name = "OBJECT")
	private List<AddressObject> addressObjects;

	public List<AddressObject> getAddressObjects() {
		return addressObjects;
	}

	public void setAddressObjects(List<AddressObject> addressObjects) {
		this.addressObjects = addressObjects;
	}
}
