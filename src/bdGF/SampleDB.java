package bdGF;
import java.sql.*;

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
    public JSONArray getCatalogoCanchas() throws SQLException
    {
     String sql = "select * from Canchas order by descripcion";
     return getqueryGen(sql);
    }
    /**
     * query para catalogo categorias
     */
    public JSONArray getCatalogoCategoria() throws SQLException
    {    
     String sql = "select * from categoria order by descripcion";
     return getqueryGen(sql);
    }
    /**
     * query para catalogo modalidad
     */
    public JSONArray getCatalogoModalidad() throws SQLException
    {     
     String sql = "select * from Modalidad order by descripcion";
     return getqueryGen(sql);
    }
    
    public JSONArray getCatalogoTorneo() throws SQLException{
    	String sql = "SELECT * FROM torneo";
    	
    	/*
     String sql = "SELECT id_torneo as idTorneo, nombre_torneo as nombreTorneo, categoria.descripcion as idCategoria, " +
     		      "       fecha_ini as fechaInicio, torneo.usuario as idUsuario, usuario.nick as nombreUsuario, " +
     		      "       torneo.modalidad as idModalidad, Modalidad.descripcion as nombreModalidad, torneo.cancha as idCancha " +
     		      "       Canchas.descripcion as nombreCancha " +
     			  "FROM torneo, categoria, usuario, Modalidad, Canchas " +
     			  "WHERE torneo.rama = categoria.descripcion AND " +
     			  "      torneo.usuario = usuario.nick AND " +
     			  "      torneo.modalidad = Modalidad.descripcion AND " +
     			  "      torneo.cancha = Canchas.descripcion ";
     */
     //id_torneo nombre_torneo rama logo fecha_ini fecha_fin usuario modalidad cancha activo
     return getqueryGen(sql);
    }
}
