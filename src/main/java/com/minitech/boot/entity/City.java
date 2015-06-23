package com.minitech.boot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
/*@Table(name = "T_CITY")
*/public class City extends AuditableEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;

	@Column(nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
