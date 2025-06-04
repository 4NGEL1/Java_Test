package com.axity.office.persistence;

import com.axity.office.model.RoleDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Persistence interface of  de {@link RoleDO}
 * 
 * @author username@axity.com
 */
@Repository
public interface RolePersistence extends JpaRepository<RoleDO, Integer>
{
  // Agregar consultas personalizadas
}
