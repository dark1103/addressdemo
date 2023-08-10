package com.milaev.addressdemo.repo.xml;

import com.milaev.addressdemo.model.AddressObject;
import com.milaev.addressdemo.model.AddressObjects;
import com.milaev.addressdemo.repo.AddressRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Xml-репозиторий адресов
 *
 * @author Artemiy Milaev
 * @since 09.08.2023
 */
@Component
public class AddressXmlRepository extends XmlRepository<AddressObjects> implements AddressRepository {

	public AddressXmlRepository(@Value("${address.address_repository_xml_path}") String filePath) {
		super(filePath);
	}

	@Override
	public List<AddressObject> getAddressObjects() {
		return getRootObject().getAddressObjects();
	}

	@Override
	protected Class<AddressObjects> getRootClass() {
		return AddressObjects.class;
	}
}
