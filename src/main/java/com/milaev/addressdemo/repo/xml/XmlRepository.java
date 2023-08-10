package com.milaev.addressdemo.repo.xml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;

/**
 * Репозиторий использующий xml-файл как источник данных
 *
 * @author Artemiy Milaev
 * @since 10.08.2023
 */
public abstract class XmlRepository<T> {

	private final String filePath;

	protected XmlRepository(String filePath) {
		this.filePath = filePath;
	}

	protected abstract Class<T> getRootClass();

	protected T getRootObject() {
		File file = new File(filePath);
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(getRootClass());
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			return (T) unmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			throw new RuntimeException("Error on parsing " + filePath, e);
		}
	}

}
