package com.axity.office.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
/**
 * Entity class of the table TBL_Territory
 * 
 * @author username@axity.com
 */
@Entity
@Table(name = "TBL_Territory")
@Getter
@Setter
public class TerritoryDO implements Serializable
{
  private static final long serialVersionUID = 1L;


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "cd_id")
  private Integer id;
  
  
  @Column(name = "nb_name")
  private String name;
  
  @OneToMany(mappedBy = "territory", fetch = FetchType.LAZY)
  private List<CountryDO> countries;
  
  

  

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
      TerritoryDO that = (TerritoryDO) object;

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
