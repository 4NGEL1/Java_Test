package com.axity.office.facade;

import com.axity.office.commons.dto.OfficeDto;
import com.axity.office.commons.request.PaginatedRequestDto;
import com.axity.office.commons.response.GenericResponseDto;
import com.axity.office.commons.response.PaginatedResponseDto;
import com.axity.office.facade.impl.OfficeFacadeImpl;
import com.axity.office.service.OfficeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Class OfficeFacadeTest
 * 
 * @author username@axity.com
 */
class OfficeFacadeTest
{
  private OfficeFacade officeFacade;
  private OfficeService officeService;

  @BeforeEach
  public void setUp()
  {
    this.officeFacade = new OfficeFacadeImpl();

    this.officeService = mock( OfficeService.class );
    ReflectionTestUtils.setField( this.officeFacade, "officeService", this.officeService );
  }

  /**
   * Test method for
   * {@link OfficeFacadeImpl#findOffices(PaginatedRequestDto)}.
   */
  @Test
  void testFindOffices()
  {
    int pageSize = 20;

    var data = new ArrayList<OfficeDto>();
    for( int i = 0; i < pageSize; i++ )
    {
      data.add( this.createOffice( i + 1 ) );
    }
    var paginated = new PaginatedResponseDto<OfficeDto>( 0, pageSize, 50, data );
    when( this.officeService.findOffices( any( PaginatedRequestDto.class ) ) ).thenReturn( paginated );

    var result = this.officeFacade.findOffices( new PaginatedRequestDto( pageSize, 0 ) );
    assertNotNull( result );
  }

  /**
   * Test method for {@link OfficeFacadeImpl#find(String)}.
   */
  @Test
  void testFind()
  {
    var response = new GenericResponseDto<OfficeDto>( this.createOffice( 1 ) );
    when( this.officeService.find( anyInt() ) ).thenReturn( response );

    var result = this.officeFacade.find( 1 );
    assertNotNull( result );
  }

  /**
   * Test method for
   * {@link OfficeFacadeImpl#create(OfficeDto)}.
   */
  @Test
  void testCreate()
  {
    var office = this.createOffice( 8 );
    var response = new GenericResponseDto<>( office );
    when( this.officeService.create( any( OfficeDto.class ) ) ).thenReturn( response );

    var result = this.officeFacade.create( office );
    assertNotNull( result );
  }

  /**
   * Test method for
   * {@link OfficeFacadeImpl#update(OfficeDto)}.
   */
  @Test
  void testUpdate()
  {
    var office = this.createOffice( 1 );

    var response = new GenericResponseDto<>( true );
    when( this.officeService.update( any( OfficeDto.class ) ) ).thenReturn( response );
    var result = this.officeFacade.update( office );
    assertNotNull( result );
  }

  /**
   * Test method for {@link OfficeFacadeImpl#delete(String)}.
   */
  @Test
  void testDelete()
  {
    var response = new GenericResponseDto<>( true );
    when( this.officeService.delete( anyInt() ) ).thenReturn( response );

    var result = this.officeFacade.delete( 9 );
    assertNotNull( result );
  }

  private OfficeDto createOffice( int i )
  {
    var office = new OfficeDto();
    office.setId( i );
    return office;
  }
}
