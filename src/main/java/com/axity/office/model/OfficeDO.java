package com.axity.office.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
/**
 * Entity class of the table TBL_Office
 * 
 * @author username@axity.com
 */
@Entity
@Table(name = "TBL_Office")
@Getter
@Setter
public class OfficeDO implements Serializable
{
  private static final long serialVersionUID = 1L;


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "cd_id")
  private Integer id;
  
  
  @Column(name = "nb_city")
  private String city;
  
  @Column(name = "nb_phone")
  private String phone;
  
  @Column(name = "nb_addressLine1")
  private String addressLine1;
  
  @Column(name = "nb_addressLine2")
  private String addressLine2;
  
  @Column(name = "nb_state")
  private String state;
  
  @ManyToOne
  @JoinColumn(name = "cd_country", referencedColumnName = "cd_id")
  private CountryDO country;
  
  
  @Column(name = "nb_postalCode")
  private String postalCode;
  

  

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals( Object object )
  {
    boolean isEquals = false;
    if( this == object )
    {
      isEquals = true;
    }
    else if( object != null && object.getClass().equals( this.getClass() ) )
    {
      OfficeDO that = (OfficeDO) object;

      isEquals = Objects.equals( this.id, that.id );
    }
    return isEquals;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode()
  {
    return Objects.hash( this.id );
  }

}
