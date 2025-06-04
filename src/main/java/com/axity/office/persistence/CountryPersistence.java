package com.axity.office.persistence;

import com.axity.office.model.CountryDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Persistence interface of  de {@link CountryDO}
 * 
 * @author username@axity.com
 */
@Repository
public interface CountryPersistence extends JpaRepository<CountryDO, Integer>
{
  // Agregar consultas personalizadas
}
