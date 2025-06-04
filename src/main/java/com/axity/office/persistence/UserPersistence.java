package com.axity.office.persistence;

import com.axity.office.model.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Persistence interface of  de {@link UserDO}
 * 
 * @author username@axity.com
 */
@Repository
public interface UserPersistence extends JpaRepository<UserDO, Integer>
{
  // Agregar consultas personalizadas
}
