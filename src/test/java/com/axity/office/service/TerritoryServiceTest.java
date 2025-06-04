package com.axity.office.service;

import com.axity.office.commons.dto.TerritoryDto;
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
 * Class TerritoryServiceTest
 * 
 * @author username@axity.com
 */
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Transactional
class TerritoryServiceTest
{
  private static final Logger LOG = LoggerFactory.getLogger( TerritoryServiceTest.class );

  @Autowired
  private TerritoryService territoryService;

  /**
   * Method to validate the paginated search
   */
  @Test
  void testFindTerritorys()
  {
    var request = new PaginatedRequestDto();
    request.setLimit( 5 );
    request.setOffset( 0 );
    var territorys = this.territoryService.findTerritorys( request );

    LOG.info( "Response: {}", territorys );

    assertNotNull( territorys );
    assertNotNull( territorys.getData() );
    assertFalse( territorys.getData().isEmpty() );
  }

  /**
   * Method to validate the search by id
   * 
   * @param territoryId
   */
  @ParameterizedTest
  @ValueSource(ints = { 1 })
  void testFind( Integer territoryId )
  {
    var territory = this.territoryService.find( territoryId );
    assertNotNull( territory );
    LOG.info( "Response: {}", territory );
  }

  /**
   * Method to validate the search by id inexistent
   */
  @Test
  void testFind_NotExists()
  {
    var territory = this.territoryService.find( 999999 );
    assertNull( territory );
  }

  /**
   * Test method for
   * {@link com.axity.office.service.impl.TerritoryServiceImpl#create(TerritoryDto)}.
   */
  @Test
  //TODO: Actualizar la prueba de acuerdo a la entidad
  void testCreate()
  {
    //var dto = new TerritoryDto();
    // Crear de acuerdo a la entidad

    //var response = this.territoryService.create( dto );
    //assertNotNull( response );
    //assertEquals( 0, response.getHeader().getCode() );
    //assertNotNull( response.getBody() );

    //this.territoryService.delete( dto.getId() );
    assertThrows( Exception.class, () -> this.territoryService.create( new TerritoryDto() ) );
  }

  /**
   * Method to validate update
   */
  @Test
  //TODO: Actualizar la prueba de acuerdo a la entidad"
  void testUpdate()
  {
    var territory = this.territoryService.find( 1 ).getBody();
    // TODO: actualizar de acuerdo a la entidad

    var response = this.territoryService.update( territory );

    assertNotNull( response );
    assertEquals( 0, response.getHeader().getCode() );
    assertTrue( response.getBody() );
    territory = this.territoryService.find( 1 ).getBody();

    // Verificar que se actualice el valor
  }

  /**
   * Method to validate an inexistent registry
   */
  @Test
  void testUpdate_NotFound()
  {
    var territory = new TerritoryDto();
    territory.setId(999999);
    var ex = assertThrows( BusinessException.class, () -> this.territoryService.update( territory ) );

    assertEquals( ErrorCode.OFFICE_NOT_FOUND.getCode(), ex.getCode() );
  }

  /**
   * Test method for {@link com.axity.office.service.impl.TerritoryServiceImpl#delete(String)}.
   */
  @Test
  void testDeleteNotFound()
  {
    var ex = assertThrows( BusinessException.class, () -> this.territoryService.delete( 999999 ) );
    assertEquals( ErrorCode.OFFICE_NOT_FOUND.getCode(), ex.getCode() );
  }
}
