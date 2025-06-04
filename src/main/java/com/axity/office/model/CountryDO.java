package com.axity.office.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
/**
 * Entity class of the table TBL_Country
 * 
 * @author username@axity.com
 */
@Entity
@Table(name = "TBL_Country")
@Getter
@Setter
public class CountryDO implements Serializable
{
  private static final long serialVersionUID = 1L;


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "cd_id")
  private Integer id;
  
  
  @Column(name = "nb_name")
  private String name;
  
  @ManyToOne
  @JoinColumn(name = "cd_territory", referencedColumnName = "cd_id")
  private TerritoryDO territory;
  
  
  @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
  private List<OfficeDO> offices;
  
  

  

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
      CountryDO that = (CountryDO) object;

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
