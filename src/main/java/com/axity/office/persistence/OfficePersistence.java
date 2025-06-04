package com.axity.office.persistence;

import com.axity.office.model.OfficeDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Persistence interface of  de {@link OfficeDO}
 * 
 * @author username@axity.com
 */
@Repository
public interface OfficePersistence extends JpaRepository<OfficeDO, Integer>
{
  // Agregar consultas personalizadas
}
