package com.axity.office.facade;

import com.axity.office.commons.dto.UserDto;
import com.axity.office.commons.request.PaginatedRequestDto;
import com.axity.office.commons.response.GenericResponseDto;
import com.axity.office.commons.response.PaginatedResponseDto;
import com.axity.office.facade.impl.UserFacadeImpl;
import com.axity.office.service.UserService;
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
 * Class UserFacadeTest
 * 
 * @author username@axity.com
 */
class UserFacadeTest
{
  private UserFacade userFacade;
  private UserService userService;

  @BeforeEach
  public void setUp()
  {
    this.userFacade = new UserFacadeImpl();

    this.userService = mock( UserService.class );
    ReflectionTestUtils.setField( this.userFacade, "userService", this.userService );
  }

  /**
   * Test method for
   * {@link UserFacadeImpl#findUsers(PaginatedRequestDto)}.
   */
  @Test
  void testFindUsers()
  {
    int pageSize = 20;

    var data = new ArrayList<UserDto>();
    for( int i = 0; i < pageSize; i++ )
    {
      data.add( this.createUser( i + 1 ) );
    }
    var paginated = new PaginatedResponseDto<UserDto>( 0, pageSize, 50, data );
    when( this.userService.findUsers( any( PaginatedRequestDto.class ) ) ).thenReturn( paginated );

    var result = this.userFacade.findUsers( new PaginatedRequestDto( pageSize, 0 ) );
    assertNotNull( result );
  }

  /**
   * Test method for {@link UserFacadeImpl#find(String)}.
   */
  @Test
  void testFind()
  {
    var response = new GenericResponseDto<UserDto>( this.createUser( 1 ) );
    when( this.userService.find( anyInt() ) ).thenReturn( response );

    var result = this.userFacade.find( 1 );
    assertNotNull( result );
  }

  /**
   * Test method for
   * {@link UserFacadeImpl#create(UserDto)}.
   */
  @Test
  void testCreate()
  {
    var user = this.createUser( 8 );
    var response = new GenericResponseDto<>( user );
    when( this.userService.create( any( UserDto.class ) ) ).thenReturn( response );

    var result = this.userFacade.create( user );
    assertNotNull( result );
  }

  /**
   * Test method for
   * {@link UserFacadeImpl#update(UserDto)}.
   */
  @Test
  void testUpdate()
  {
    var user = this.createUser( 1 );

    var response = new GenericResponseDto<>( true );
    when( this.userService.update( any( UserDto.class ) ) ).thenReturn( response );
    var result = this.userFacade.update( user );
    assertNotNull( result );
  }

  /**
   * Test method for {@link UserFacadeImpl#delete(String)}.
   */
  @Test
  void testDelete()
  {
    var response = new GenericResponseDto<>( true );
    when( this.userService.delete( anyInt() ) ).thenReturn( response );

    var result = this.userFacade.delete( 9 );
    assertNotNull( result );
  }

  private UserDto createUser( int i )
  {
    var user = new UserDto();
    user.setId( i );
    return user;
  }
}
