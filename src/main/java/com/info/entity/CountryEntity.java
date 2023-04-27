package com.info.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="country_tab")
public class CountryEntity {
	@Id
	private Integer countryId;
	private String countryName;
	

}
