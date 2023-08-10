package com.milaev.addressdemo.model;

import jakarta.xml.bind.annotation.*;

import java.util.Date;

/**
 * Элемент иерархии
 *
 * @author Artemiy Milaev
 * @since 09.08.2023
 */
@XmlRootElement(name = "ITEM")
@XmlAccessorType(XmlAccessType.FIELD)
public class AddressHierarchyItem {
	@XmlAttribute(name = "OBJECTID")
	private long objectId;

	@XmlAttribute(name = "PARENTOBJID")
	private long parentObjectId;

	@XmlAttribute(name = "STARTDATE")
	@XmlSchemaType(name = "date")
	private Date startDate;

	@XmlAttribute(name = "ENDDATE")
	@XmlSchemaType(name = "date")
	private Date endDate;

	@XmlAttribute(name = "ISACTIVE")
	private boolean isActive;

	public long getObjectId() {
		return objectId;
	}

	public void setObjectId(long objectId) {
		this.objectId = objectId;
	}

	public long getParentObjectId() {
		return parentObjectId;
	}

	public void setParentObjectId(long parentObjectId) {
		this.parentObjectId = parentObjectId;
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

	@Override
	public String toString() {
		return "AddressHierarchyItem{" +
			"objectId='" + objectId + '\'' +
			", parentObjectId='" + parentObjectId + '\'' +
			", isActive='" + isActive + '\'' +
			'}';
	}
}