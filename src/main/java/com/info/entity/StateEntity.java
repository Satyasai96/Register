package com.info.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="state_tab")
public class StateEntity {
	@Id
	private Integer stateId;
	private String stateName;
	private Integer countryId;

}
