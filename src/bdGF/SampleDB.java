package bdGF;
import java.sql.*;

import com.adventnet.zoho.client.report.ReportClient;

import bdGF.utils.ResultSetConverter;
import net.sf.json.*;

public class SampleDB
{  
    
   
    private  Connection conn = null;
   
    public SampleDB(){
    	try {
    		conn=(new Conexion()).getConn();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
    }
    
   
    /**
     * Query generico
     */
    public JSONArray getqueryGen(String Query) throws SQLException
    {
    	Statement stmt = null;
        ResultSet rs = null;
        JSONArray resultado=null;
       
     try{   
         stmt = conn.createStatement();
         String sql = Query;
         rs = stmt.executeQuery(sql);
         resultado=ResultSetConverter.convert(rs);
     } 
     finally
     {
         if (rs!= null){try { rs.close(); } catch (Exception e){ e.printStackTrace();}}
         if (rs!= null){try { stmt.close(); } catch (Exception e){ e.printStackTrace();}}
         if (rs!= null){try { conn.close(); } catch (Exception e){ e.printStackTrace();}}
     }
    	return resultado;
    }
    /**
     * query para catalogo canchas
     */
    public JSONArray getCanchas() throws SQLException
    {
     String sql = "select * from Canchas order by descripcion";
     return getqueryGen(sql);
    }
    /**
     * query para catalogo categorias
     */
    public JSONArray getCategorias() throws SQLException
    {    
     String sql = "select * from categoria order by descripcion";
     return getqueryGen(sql);
    }
    /**
     * query para catalogo modalidad
     */
    public JSONArray getModalidades() throws SQLException
    {     
     String sql = "select * from Modalidad order by descripcion";
     return getqueryGen(sql);
    }
    
    public JSONArray getTorneos() throws SQLException{
      	
     String sql = "SELECT torneo.id_torneo as idTorneo, torneo.nombre_torneo as nombreTorneo, categoria.id as idCategoria, categoria.descripcion as nombreCategoria, " +
     		      "       torneo.fecha_ini as fechaInicio, torneo.usuario as idUsuario, usuario.nick as nombreUsuario, " +
     		      "       torneo.modalidad as idModalidad, Modalidad.descripcion as nombreModalidad, torneo.cancha as idCancha ," +
     		      "       Canchas.descripcion as nombreCancha " +
     			  " FROM torneo, categoria, usuario, Modalidad,Canchas" +
     			  " WHERE torneo.rama = categoria.id AND " +
     			  "      torneo.usuario = usuario.id_usuario AND " +
     			  "      torneo.modalidad = Modalidad.id  AND"+
     			  "		 torneo.cancha=Canchas.id";
     
     
    	
     //id_torneo nombre_torneo rama logo fecha_ini fecha_fin usuario modalidad cancha activo
     return getqueryGen(sql);
    }
    
    public JSONArray getUserTorneos(String idUsuario) throws SQLException{
    	
     String sql = " SELECT torneo.id_torneo as idTorneo, torneo.nombre_torneo as nombreTorneo, categoria.id as idCategoria, categoria.descripcion as nombreCategoria, " +
     		      "        torneo.fecha_ini as fechaInicio, torneo.usuario as idUsuario, usuario.nick as nombreUsuario, " +
     		      "        torneo.modalidad as idModalidad, Modalidad.descripcion as nombreModalidad, torneo.cancha as idCancha ," +
     		      "       Canchas.descripcion as nombreCancha " +
     			  "  FROM torneo, categoria, usuario, Modalidad,Canchas" +
     			  "  WHERE torneo.rama = categoria.id AND " +
     			  "       torneo.usuario = usuario.id_usuario AND " +
     			  "       torneo.modalidad = Modalidad.id  AND"+
     			  "		  torneo.cancha=Canchas.id AND " + 
	  			  "		  torneo.usuario = " + idUsuario;
     
     
    	
     //id_torneo nombre_torneo rama logo fecha_ini fecha_fin usuario modalidad cancha activo
     return getqueryGen(sql);
    }
    
    public JSONArray getTorneosFavoritos(String idUsuario ) throws SQLException{
        String sql = " SELECT torneos_favoritos.id as idFavorito, torneos_favoritos.id_usuario as idUsuario, " +
        			 "        torneos_favoritos.id_torneo as idTorneo, usuario.nick as nombreUsuario, " +
        			 "		  torneo.nombre_torneo as nombreTorneo, torneo.id_torneo as idTorneo" +
        			 " FROM torneos_favoritos, torneo, usuario" +
        			 " WHERE torneos_favoritos.id_torneo = torneo.id_torneo AND " +
        			 "       torneos_favoritos.id_usuario = usuario.id_usuario AND " +
        			 "       torneos_favoritos.id_usuario = " + idUsario;
       	
        return getqueryGen(sql);
    }
    
    public JSONArray getEquiposPorTorneo(String idTorneo) throws SQLException{
        String sql = " SELECT id_equipo as idEquipo, nombre_equipo as nombreEquipo, status, torneo " +
        			 " FROM torneo, equipo" +
        			 " WHERE equipo.torneo = " + idTorneo;
       	
        return getqueryGen(sql);
    }
    
    
    public JSONArray inscribirEquipo(String idTorneo) throws SQLException{
        String sql = " SELECT id_equipo as idEquipo, nombre_equipo as nombreEquipo, status, torneo " +
        			 " FROM torneo, equipo" +
        			 " WHERE equipo.torneo = " + idTorneo;
       	
        return getqueryGen(sql);
    }
}
