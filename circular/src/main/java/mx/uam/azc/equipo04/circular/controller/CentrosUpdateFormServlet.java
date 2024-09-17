package mx.uam.azc.equipo04.circular.controller;

import mx.uam.azc.equipo04.circular.data.CentrosDTO;

import javax.naming.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class CentrosUpdateForm
 */
/**
 * @author Zeran
 */
@WebServlet(name = "CentrosUpdateForm", urlPatterns = { "/CentrosUpdateForm" })
public class CentrosUpdateFormServlet extends HttpServlet
{
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doGet( HttpServletRequest request,
      HttpServletResponse response ) throws ServletException, IOException
  {

    try
    {
      List<CentrosDTO> Centro = getCentro();

      request.setAttribute( "centros", Centro );
    }
    catch ( Exception e )
    {
      throw new ServletException( e );
    }

    RequestDispatcher dispatcher = request
        .getRequestDispatcher( "/centros_lavado_update_form.jsp" );

    dispatcher.forward( request, response );

  }

  // Obtenemos los datos registrados en la base de datos
  private List<CentrosDTO> getCentro() throws NamingException, SQLException
  {
    Context context = new InitialContext();
    DataSource source = ( DataSource )context
        .lookup( "java:comp/env/jdbc/TestDS" );

    Connection connection = source.getConnection();

    try
    {
      return getCentro( connection );
    }
    finally
    {
      connection.close();
    }
  }

  // Realizar la consulta sobre la base de datos y regresar la lista de
  // registros
  private List<CentrosDTO> getCentro( Connection connection )
      throws SQLException
  {
    Statement statement = connection.createStatement();

    ResultSet cursor = statement.executeQuery(
        "SELECT centro_id, nombre, direccion, telefono, email FROM centros_lavado;" );

    try
    {
      List<CentrosDTO> centros = new ArrayList<CentrosDTO>();

      while ( cursor.next() )
      {
        CentrosDTO centro = new CentrosDTO();

        centro.setId( cursor.getString( 1 ) );
        centro.setNombre( cursor.getString( 2 ) );
        centro.setDir( cursor.getString( 3 ) );
        centro.setTel( cursor.getString( 4 ) );
        centro.setEmail( cursor.getString( 5 ) );

        centros.add( centro );
      }

      return centros;
    }
    finally
    {
      cursor.close();
    }

  }

}
