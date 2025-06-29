package com.axity.office.service;

import com.axity.office.commons.dto.RoleDto;
import com.axity.office.commons.enums.ErrorCode;
import com.axity.office.commons.exception.BusinessException;
import com.axity.office.commons.request.PaginatedRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class RoleServiceTest
 * 
 * @author username@axity.com
 */
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Transactional
class RoleServiceTest
{
  private static final Logger LOG = LoggerFactory.getLogger( RoleServiceTest.class );

  @Autowired
  private RoleService roleService;

  /**
   * Method to validate the paginated search
   */
  @Test
  void testFindRoles()
  {
    var request = new PaginatedRequestDto();
    request.setLimit( 5 );
    request.setOffset( 0 );
    var roles = this.roleService.findRoles( request );

    LOG.info( "Response: {}", roles );

    assertNotNull( roles );
    assertNotNull( roles.getData() );
    assertFalse( roles.getData().isEmpty() );
  }

  /**
   * Method to validate the search by id
   * 
   * @param roleId
   */
  @ParameterizedTest
  @ValueSource(ints = { 1 })
  void testFind( Integer roleId )
  {
    var role = this.roleService.find( roleId );
    assertNotNull( role );
    LOG.info( "Response: {}", role );
  }

  /**
   * Method to validate the search by id inexistent
   */
  @Test
  void testFind_NotExists()
  {
    var role = this.roleService.find( 999999 );
    assertNull( role );
  }

  /**
   * Test method for
   * {@link com.axity.office.service.impl.RoleServiceImpl#create(RoleDto)}.
   */
  @Test
  //TODO: Actualizar la prueba de acuerdo a la entidad
  void testCreate()
  {
    //var dto = new RoleDto();
    // Crear de acuerdo a la entidad

    //var response = this.roleService.create( dto );
    //assertNotNull( response );
    //assertEquals( 0, response.getHeader().getCode() );
    //assertNotNull( response.getBody() );

    //this.roleService.delete( dto.getId() );
    assertThrows( Exception.class, () -> this.roleService.create( new RoleDto() ) );
  }

  /**
   * Method to validate update
   */
  @Test
  //TODO: Actualizar la prueba de acuerdo a la entidad"
  void testUpdate()
  {
    var role = this.roleService.find( 1 ).getBody();
    // TODO: actualizar de acuerdo a la entidad

    var response = this.roleService.update( role );

    assertNotNull( response );
    assertEquals( 0, response.getHeader().getCode() );
    assertTrue( response.getBody() );
    role = this.roleService.find( 1 ).getBody();

    // Verificar que se actualice el valor
  }

  /**
   * Method to validate an inexistent registry
   */
  @Test
  void testUpdate_NotFound()
  {
    var role = new RoleDto();
    role.setId(999999);
    var ex = assertThrows( BusinessException.class, () -> this.roleService.update( role ) );

    assertEquals( ErrorCode.OFFICE_NOT_FOUND.getCode(), ex.getCode() );
  }

  /**
   * Test method for {@link com.axity.office.service.impl.RoleServiceImpl#delete(String)}.
   */
  @Test
  void testDeleteNotFound()
  {
    var ex = assertThrows( BusinessException.class, () -> this.roleService.delete( 999999 ) );
    assertEquals( ErrorCode.OFFICE_NOT_FOUND.getCode(), ex.getCode() );
  }
}
