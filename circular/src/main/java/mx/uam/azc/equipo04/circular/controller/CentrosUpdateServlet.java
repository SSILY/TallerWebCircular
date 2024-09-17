package mx.uam.azc.equipo04.circular.controller;

import mx.uam.azc.equipo04.circular.data.CentrosDTO;

import javax.naming.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;

import java.io.IOException;
import java.sql.*;

/*
 * Code Pioneers Innovar, crear, conquistar González Villa Sonia Maurilia -
 * 2193077059 Rios Valencia Gisela Rocio - 2173003053 Taller de Desarrollo de
 * Aplicaciones Web Hugo Pablo Leyva 26/08/2024
 */

/**
 * Servlet implementation class CentrosUpdateServlet
 */
@WebServlet(name = "CentrosUpdate", urlPatterns = { "/CentrosUpdate" })
public class CentrosUpdateServlet extends HttpServlet
{
  private static final long serialVersionUID = 1L;

  /**
   * Default constructor.
   */
  public CentrosUpdateServlet()
  {
    // TODO Auto-generated constructor stub
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doPost( HttpServletRequest request,
      HttpServletResponse response ) throws ServletException, IOException
  {
    //
    /*
     * Código correspondiente a Práctica 5 log(
     * "[!] Actualizando centro de lavado" ); response.setContentType(
     * "text/html" ); Writer writer = response.getWriter(); writer.write(
     * "<html><body><p style='font-family: Montserrat; font-size:2rem; font-weight:500; line-height:1.2;'>El lápiz que rompió tu regla</p></body></html>"
     * );
     */

    /**
     * Código correspondiente a Práctica 6
     */

    // Avance con froward() ========================================
    /**
     * log( "[!] Actualizando centro de lavado" ); RequestDispatcher dispatcher
     * = request .getRequestDispatcher( "/centros_lavado_update_form.jsp" );
     * dispatcher.forward( request, response );
     */
    // =============================================================

    // Avance con sendRedirect() ========================================

    // log( "[!] Actualizando centro de lavado" );

    // String base = request.getContextPath();
    // response.sendRedirect( base + "/centros_lavado_update_form.jsp");

    // =============================================================

    /**
     * Código correspondiente a Práctica 7
     */

    // Actividad de modificación 1 =====================================
    /**
     * try { updateCentros( request, response ); } catch ( Exception e ) { throw
     * new ServletException( e ); } String base = request.getContextPath();
     */

    /**
     * Codigo correspondiente a la Práctica 8
     */

    String accion = request.getParameter( "boton" );

    if ( accion.equals( "Modificar" ) )
    {
      try
      {
        updateCentros( request, response );
        log( "[!] Actualizando informacion de centro de lavado" );
      }
      catch ( Exception e )
      {
        throw new ServletException( e );
      }

    }
    else if ( accion.equals( "Borrar" ) )
    {
      try
      {
        deleteCentros( request, response );
        log( "[!] Borrando informacion de centro de lavado" );
      }
      catch ( Exception e )
      {
        log( "[ERROR] No se puede borrar el centro. Es usado por al menos un cliente!" );
      }

    }
    else if ( accion.equals( "Agregar" ) )
    {
      try
      {
        insertCentros( request, response );
        log( "[!] Insertando Centro" );
      }
      catch ( Exception e )
      {
        log( "[ERROR] No se puede insertar el centro!" );
      }
    }

    String base = request.getContextPath();
    // Actualización - P8 - 5/09/2024
    response.sendRedirect( base + "/CentrosUpdateForm" );

  }

  /**
   * REFACTOR
   */
  /**
   * @return
   * @throws NamingException
   * @throws SQLException
   */
  private Connection getConnection() throws NamingException, SQLException
  {
    Context context = new InitialContext();
    DataSource source = ( DataSource )context
        .lookup( "java:comp/env/jdbc/TestDS" );

    Connection connection = source.getConnection();
    return connection;
  }

  /**
   * ===================== BORRADO ============================================
   */

  /**
   * @param request
   * @param response
   */
  private void deleteCentros( HttpServletRequest request,
      HttpServletResponse response ) throws NamingException, SQLException
  {
    CentrosDTO centro = getCentro( request );

    Connection connection = getConnection();

    try
    {
      deleteCentros( connection, centro );
    }
    finally
    {
      connection.close();
    }

  }

  /**
   * @param connection
   * @param centro
   */
  private void deleteCentros( Connection connection, CentrosDTO centro )
      throws SQLException
  {
    PreparedStatement statement = connection
        .prepareStatement( "DELETE FROM centros_lavado WHERE centro_id = ?;" );

    try
    {
      statement.setString( 1, centro.getId() );
      statement.executeUpdate();
    }
    finally
    {
      statement.close();
    }

  }

  /**
   * ===================== INSERCION ===========================================
   */

  private void insertCentros( HttpServletRequest request,
      HttpServletResponse response ) throws NamingException, SQLException
  {
    CentrosDTO centro = getCentro( request );

    Connection connection = getConnection();

    try
    {
      insertCentros( connection, centro );
    }
    finally
    {
      connection.close();
    }
  }

  private void insertCentros( Connection connection, CentrosDTO centro )
      throws SQLException
  {
    PreparedStatement statement = connection.prepareStatement(
        "INSERT INTO centros_lavado(nombre, direccion, telefono, email) VALUES(?,?,?,?);" );

    try
    {
      //statement.setInt( 1, Integer.parseInt( centro.getId() ) );
      statement.setString( 1, centro.getNombre() );
      statement.setString( 2, centro.getDir() );
      statement.setString( 3, centro.getTel() );
      statement.setString( 4, centro.getEmail() );
      statement.executeUpdate();
    }
    finally
    {
      statement.close();
    }
  }

  /**
   * ===================== MODIFICACION========================================
   */

//Actividad de modificación 2 (DTO) ====================================

  /**
   * @param request
   * @param response
   * @throws NamingException
   * @throws SQLException
   */
  private void updateCentros( HttpServletRequest request,
      HttpServletResponse response ) throws NamingException, SQLException
  {
    CentrosDTO centro = getCentro( request );

    Connection connection = getConnection();

    try
    {
      updateCentros( connection, centro );
    }
    finally
    {
      connection.close();
    }

  }

  /**
   * @param connection
   * @param centro
   * @throws SQLException
   */
  private void updateCentros( Connection connection, CentrosDTO centro )
      throws SQLException
  {
    PreparedStatement statement = connection.prepareStatement(
        "UPDATE centros_lavado SET direccion = ?, telefono = ?, email = ? WHERE centro_id = ?;" );
    try
    {
      statement.setString( 1, centro.getDir() );
      statement.setString( 2, centro.getTel() );
      statement.setString( 3, centro.getEmail() );
      statement.setString( 4, centro.getId() );
      statement.executeUpdate();
    }
    finally
    {
      statement.close();
    }
  }

  /**
   * ================== OBTENER CENTRO =======================================
   */

  /**
   * @param request
   * @return
   */
  private CentrosDTO getCentro( HttpServletRequest request )
  {
    String idCentro = request.getParameter( "id" );
    String nombre = request.getParameter( "nombre" );
    String direccion = request.getParameter( "dir" );
    String telefonoCentro = request.getParameter( "tel" );
    String mail = request.getParameter( "mail" );

    // DTO
    CentrosDTO centro = new CentrosDTO();
    centro.setId( idCentro );
    centro.setNombre( nombre );
    centro.setDir( direccion );
    centro.setTel( telefonoCentro );
    centro.setEmail( mail );

    return centro;
  }

  /**
   * @param request
   * @param response
   * @throws NamingException
   * @throws SQLException
   */
  /*
   * private void updateCentros( HttpServletRequest request, HttpServletResponse
   * response ) throws NamingException, SQLException { String idCentro =
   * request.getParameter( "id" ); String telefonoCentro = request.getParameter(
   * "tel" ); Context context = new InitialContext(); DataSource source = (
   * DataSource )context .lookup( "java:comp/env/jdbc/TestDS" ); Connection
   * connection = source.getConnection(); try { updateCentros( connection,
   * idCentro, telefonoCentro ); } finally { connection.close(); } }
   */

  /**
   * @param connection
   * @param idCentro
   * @param telefonoCentro
   * @throws SQLException
   */
  /*
   * private void updateCentros( Connection connection, String idCentro, String
   * telefonoCentro ) throws SQLException { Statement statement =
   * connection.createStatement(); try { statement.executeUpdate(
   * "UPDATE centros_lavado SET " + " telefono = '" + telefonoCentro +
   * "' WHERE centro_id ='" + idCentro + "' ;" ); } finally { statement.close();
   * } }
   */

  // =======================================================================

  /**
   * @param connection
   * @param centro
   * @throws SQLException
   */
  /*
   * private void updateCentros( Connection connection, CentrosDTO centro )
   * throws SQLException { Statement statement = connection.createStatement();
   * try { statement.executeUpdate( "UPDATE centros_lavado SET " +
   * " telefono = '" + centro.getTel() + "' WHERE centro_id ='" + centro.getId()
   * + "' ;" ); } finally { statement.close(); } }
   */

}
