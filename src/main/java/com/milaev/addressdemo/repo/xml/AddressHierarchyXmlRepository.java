package com.milaev.addressdemo.repo.xml;

import com.milaev.addressdemo.model.AddressHierarchy;
import com.milaev.addressdemo.model.AddressHierarchyItem;
import com.milaev.addressdemo.repo.AddressHierarchyRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Xml-репозиторий иерархии адресов
 *
 * @author Artemiy Milaev
 * @since 10.08.2023
 */
@Component
public class AddressHierarchyXmlRepository extends XmlRepository<AddressHierarchy> implements AddressHierarchyRepository {

	public AddressHierarchyXmlRepository(@Value("${address.address_hierarchy_repository_xml_path}") String filePath) {
		super(filePath);
	}

	@Override
	public List<AddressHierarchyItem> getAddressHierarchy() {
		return getRootObject().getAddressHierarchyItems();
	}

	@Override
	protected Class<AddressHierarchy> getRootClass() {
		return AddressHierarchy.class;
	}
}
