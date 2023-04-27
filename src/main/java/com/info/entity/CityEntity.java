package com.info.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="city_tab")
public class CityEntity {
	@Id
	private Integer cityId;
	private String cityName;
	private Integer stateId;

}
