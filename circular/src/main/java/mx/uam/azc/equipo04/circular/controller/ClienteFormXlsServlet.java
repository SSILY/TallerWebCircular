package mx.uam.azc.equipo04.circular.controller;

import mx.uam.azc.equipo04.circular.data.ClientesDTO;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import javax.naming.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;

import java.io.*;
import java.sql.*;
import java.util.*;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;


/**
 * @author Zeran
 */
@WebServlet(name = "ClienteFormXls", urlPatterns = { "/ClienteFormXls" })
public class ClienteFormXlsServlet extends HttpServlet
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
      List<ClientesDTO> Cliente = getCliente( key, response );

      request.setAttribute( "clientes", Cliente );
    }
    catch ( Exception e )
    {
      throw new ServletException( e );
    }

    RequestDispatcher dispatcher = request
        .getRequestDispatcher( "/cliente_search2.jsp" );

    dispatcher.forward( request, response );

  }

  // Obtenemos los datos registrados en la base de datos
  private List<ClientesDTO> getCliente( int key, HttpServletResponse response )
      throws NamingException, SQLException, IOException, ParsePropertyException,
      InvalidFormatException
  {
    Context context = new InitialContext();
    DataSource source = ( DataSource )context
        .lookup( "java:comp/env/jdbc/TestDS" );

    Connection connection = source.getConnection();

    try
    {
      return getCliente( connection, key, response );
    }
    finally
    {
      connection.close();
    }
  }

  // Realizar la consulta sobre la base de datos y regresar la lista de
  // registros
  /**
   * @param connection
   * @param key
   * @param response
   * @return
   * @throws SQLException
   * @throws ParsePropertyException
   * @throws InvalidFormatException
   * @throws IOException
   */
  private List<ClientesDTO> getCliente( Connection connection, int key,
      HttpServletResponse response ) throws SQLException,
      ParsePropertyException, InvalidFormatException, IOException
  {
    Statement statement = connection.createStatement();

    String query = "SELECT cliente_id, nombre, direccion, telefono, email "
        + "FROM clientes " + "WHERE cliente_id='" + key + "';";
    ResultSet cursor = statement.executeQuery( query );

    try
    {
      List<ClientesDTO> clientes = new ArrayList<ClientesDTO>();
      Map<String, ClientesDTO> beans = new HashMap<String, ClientesDTO>();

      while ( cursor.next() )
      {
        ClientesDTO cliente = new ClientesDTO();

        cliente.setId( cursor.getString( 1 ) );
        cliente.setNombre( cursor.getString( 2 ) );
        cliente.setDir( cursor.getString( 3 ) );
        cliente.setTel( cursor.getString( 4 ) );
        cliente.setMail( cursor.getString( 5 ) );

        clientes.add( cliente );
        beans.put( "cliente", cliente );
      }

      xlsShow( beans, response, key );
      System.out.println( "[INFO] Documento creado" );

      return clientes;
    }
    finally
    {
      cursor.close();
    }

  }

  public void xlsShow( Map<String, ClientesDTO> beans,
      HttpServletResponse response, int key ) throws IOException
  {
    ServletContext context = getServletContext();
    InputStream istream = context
        .getResourceAsStream( "/WEB-INF/templates/PlantillaCliente.xls" );
    XLSTransformer transformer = new XLSTransformer();
    HSSFWorkbook workbook = transformer.transformXLS( istream, beans );
    response.setContentType( "application/msexcel" );
    response.addHeader( "Content-Disposition",
        "attachment;filename=ReporteCliente" + key + ".xls" );
    OutputStream os = response.getOutputStream();
    workbook.write( os );
    os.flush();
  }

}
