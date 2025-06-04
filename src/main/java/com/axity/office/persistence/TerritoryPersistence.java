package com.axity.office.persistence;

import com.axity.office.model.TerritoryDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Persistence interface of  de {@link TerritoryDO}
 * 
 * @author username@axity.com
 */
@Repository
public interface TerritoryPersistence extends JpaRepository<TerritoryDO, Integer>
{
  // Agregar consultas personalizadas
}
