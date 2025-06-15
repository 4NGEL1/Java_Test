package com.axity.office.service.impl;

import com.axity.office.commons.dto.UserDto;
import com.axity.office.commons.enums.ErrorCode;
import com.axity.office.commons.exception.BusinessException;
import com.axity.office.commons.request.PaginatedRequestDto;
import com.axity.office.commons.response.GenericResponseDto;
import com.axity.office.commons.response.PaginatedResponseDto;
import com.axity.office.model.UserDO;
import com.axity.office.persistence.UserPersistence;
import com.axity.office.service.UserService;
import com.github.dozermapper.core.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional; // Explicitly import Optional for clarity

/**
 * Class UserServiceImpl
 * * @author username@axity.com
 */
@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

  @Autowired
  private UserPersistence userPersistence;

  @Autowired
  private Mapper mapper;
  
  /**
   * {@inheritDoc}
   */
  @Override
  public PaginatedResponseDto<UserDto> findUsers(PaginatedRequestDto request) {
    log.debug("Request received: {}", request); // Improved logging with placeholder

    // Using var for local variable type inference (Java 10+)
    var page = request.getOffset() / request.getLimit();
    var pageRequest = PageRequest.of(page, request.getLimit(), Sort.by("id"));

    var paged = this.userPersistence.findAll(pageRequest);

    var result = new PaginatedResponseDto<UserDto>(page, request.getLimit(), paged.getTotalElements());

    // Stream API enhancements in Java 8+, common in Java 11
    paged.stream().map(this::transform).forEach(result.getData()::add); 

    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<UserDto> find(Integer id) {
    // Using var for Optional and improved handling with orElse (Java 8+)
    var optionalUserDO = this.userPersistence.findById(id);
    
    // Instead of checking isPresent() and then get(), use orElse(null) or map directly
    return optionalUserDO.map(this::transform)
                         .map(GenericResponseDto::new) // If user is found, create new GenericResponseDto
                         .orElse(null); // If not found, return null as per original logic
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<UserDto> create(UserDto dto) {
    // Using var for entity
    var entity = new UserDO();
    this.mapper.map(dto, entity);
    entity.setId(null);

    this.userPersistence.save(entity);

    dto.setId(entity.getId());

    return new GenericResponseDto<>(dto);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<Boolean> update(UserDto dto) {
    // Using var for optional and improved handling with orElseThrow (Java 8+)
    var entity = this.userPersistence.findById(dto.getId())
                                     .orElseThrow(() -> new BusinessException(ErrorCode.OFFICE_NOT_FOUND));
    
    // Direct field updates
    entity.setUsername(dto.getUsername());
    entity.setEmail(dto.getEmail());
    entity.setName(dto.getName());
    entity.setLastName(dto.getLastName());
    // TODO: Actualizar entity.Roles (?) 

    this.userPersistence.save(entity);

    return new GenericResponseDto<>(true);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<Boolean> delete(Integer id) {
    // Using var for optional and improved handling with orElseThrow (Java 8+)
    var entity = this.userPersistence.findById(id)
                                     .orElseThrow(() -> new BusinessException(ErrorCode.OFFICE_NOT_FOUND));

    this.userPersistence.delete(entity);

    return new GenericResponseDto<>(true);
  }

  private UserDto transform(UserDO entity) {
    // Streamlined transformation using Optional.ofNullable and map (Java 8+)
    return Optional.ofNullable(entity)
                   .map(e -> this.mapper.map(e, UserDto.class))
                   .orElse(null);
  }
}
