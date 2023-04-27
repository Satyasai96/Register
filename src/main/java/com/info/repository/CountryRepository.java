package com.info.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.info.entity.CountryEntity;

public interface CountryRepository extends JpaRepository<CountryEntity, Serializable> {

}
