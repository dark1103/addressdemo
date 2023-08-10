package com.milaev.addressdemo.model;

import jakarta.xml.bind.annotation.*;

import java.util.Date;

/**
 * Адрес
 *
 * @author Artemiy Milaev
 * @since 09.08.2023
 */
@XmlRootElement(name = "OBJECT")
@XmlAccessorType(XmlAccessType.FIELD)
public class AddressObject {
	@XmlAttribute(name = "ID")
	private long id;

	@XmlAttribute(name = "OBJECTID")
	private long objectId;

	@XmlAttribute(name = "NAME")
	private String name;

	@XmlAttribute(name = "TYPENAME")
	private String typeName;

	@XmlAttribute(name = "STARTDATE")
	@XmlSchemaType(name = "date")
	private Date startDate;

	@XmlAttribute(name = "ENDDATE")
	@XmlSchemaType(name = "date")
	private Date endDate;

	@XmlAttribute(name = "ISACTIVE")
	private boolean isActive;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getObjectId() {
		return objectId;
	}

	public void setObjectId(long objectId) {
		this.objectId = objectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean active) {
		isActive = active;
	}

	public String getDescription() {
		return getTypeName() + " " + getName();
	}

	@Override
	public String toString() {
		return "AddressObject{" +
			"objectId='" + objectId + '\'' +
			", typeName='" + typeName + '\'' +
			", name='" + name + '\'' +
			", isActive='" + isActive + '\'' +
			'}';
	}
}
