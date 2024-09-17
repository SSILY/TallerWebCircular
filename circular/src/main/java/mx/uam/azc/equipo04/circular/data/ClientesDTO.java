/**
 * Creado: 17 sep. 2024 12:00:48
 */
package mx.uam.azc.equipo04.circular.data;

import java.io.Serializable;

/**
 * @author Zeran
 */
public class ClientesDTO implements Serializable
{
  private static final long serialVersionUID = 1L;

  private String _id;

  private String _nombre;

  private String _dir;

  private String _tel;

  private String _mail;

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
   * @return the mail
   */
  public String getMail()
  {
    return _mail;
  }

  /**
   * @param mail the mail to set
   */
  public void setMail( String mail )
  {
    _mail = mail;
  }
}
