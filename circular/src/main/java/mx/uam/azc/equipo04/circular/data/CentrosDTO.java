/**
 * Creado: 29 ago. 2024 19:40:49
 */
package mx.uam.azc.equipo04.circular.data;

import java.io.Serializable;

/**
 * @author Zeran
 */
public class CentrosDTO implements Serializable
{
  private String _id;

  private String _tel;

  private String _nombre;

  private String _dir;

  private String _email;

  /**
   * @return the id
   */
  public String getId()
  {
    return _id;
  }

  /**
   * @param id the id to set
   */
  public void setId( String id )
  {
    _id = id;
  }

  /**
   * @return the tel
   */
  public String getTel()
  {
    return _tel;
  }

  /**
   * @param tel the tel to set
   */
  public void setTel( String tel )
  {
    _tel = tel;
  }

  /**
   * @return the nombre
   */
  public String getNombre()
  {
    return _nombre;
  }

  /**
   * @param nombre the nombre to set
   */
  public void setNombre( String nombre )
  {
    _nombre = nombre;
  }

  /**
   * @return the dir
   */
  public String getDir()
  {
    return _dir;
  }

  /**
   * @param dir the dir to set
   */
  public void setDir( String dir )
  {
    _dir = dir;
  }

  /**
   * @return the email
   */
  public String getEmail()
  {
    return _email;
  }

  /**
   * @param email the email to set
   */
  public void setEmail( String email )
  {
    _email = email;
  }
}
