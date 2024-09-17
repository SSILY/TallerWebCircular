package mx.uam.azc.equipo04.circular.controller;

import mx.uam.azc.equipo04.circular.data.ClientesDTO;

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
@WebServlet(name = "ClienteForm", urlPatterns = { "/ClienteForm" })
public class ClienteFormServlet extends HttpServlet
{
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doGet( HttpServletRequest request,
      HttpServletResponse response ) throws ServletException, IOException
  {

    int key = Integer.valueOf( request.getParameter( "llave" ) ).intValue();

    try
    {
      List<ClientesDTO> Cliente = getCliente( key );

      request.setAttribute( "clientes", Cliente );
    }
    catch ( Exception e )
    {
      throw new ServletException( e );
    }

    RequestDispatcher dispatcher = request
        .getRequestDispatcher( "/cliente_view.jsp" );

    dispatcher.forward( request, response );

  }

  // Obtenemos los datos registrados en la base de datos
  private List<ClientesDTO> getCliente( int key )
      throws NamingException, SQLException
  {
    Context context = new InitialContext();
    DataSource source = ( DataSource )context
        .lookup( "java:comp/env/jdbc/TestDS" );

    Connection connection = source.getConnection();

    try
    {
      return getCliente( connection, key );
    }
    finally
    {
      connection.close();
    }
  }

  // Realizar la consulta sobre la base de datos y regresar la lista de
  // registros
  private List<ClientesDTO> getCliente( Connection connection, int key )
      throws SQLException
  {
    Statement statement = connection.createStatement();

    String query = "SELECT cliente_id, nombre, direccion, telefono, email "
        + "FROM clientes "
        + "WHERE cliente_id='"
        + key + "';";
    ResultSet cursor = statement.executeQuery( query );

    try
    {
      List<ClientesDTO> clientes = new ArrayList<ClientesDTO>();

      while ( cursor.next() )
      {
        ClientesDTO cliente = new ClientesDTO();

        cliente.setId( cursor.getString( 1 ) );
        cliente.setNombre( cursor.getString( 2 ) );
        cliente.setDir( cursor.getString( 3 ) );
        cliente.setTel( cursor.getString( 4 ) );
        cliente.setMail( cursor.getString( 5 ) );

        clientes.add( cliente );
      }

      return clientes;
    }
    finally
    {
      cursor.close();
    }

  }

}
