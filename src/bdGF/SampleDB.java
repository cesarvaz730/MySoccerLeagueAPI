package bdGF;
import java.sql.*;

public class SampleDB
{

   
    
    private static final String TABLE_NAME="tabla_prueba";//<your table name>
    
    private  Connection conn = null;
    
    public SampleDB(){
    	try {
    		conn=(new Conexion()).getConn();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
    }
//
    public String getConsultaRes() throws SQLException
    {
    	String res=""; 
    	Statement stmt = null;
         ResultSet rs = null;
     try{   
         stmt = conn.createStatement();
         String sql = "select * from " + TABLE_NAME + " limit 5";
         rs = stmt.executeQuery(sql);
         
         System.out.println("pinta resultado");
         while (rs.next())
         {
         	res+=rs.getString("descripcion")+"--";
         	
         }
    } 
     finally
     {
         if (rs!= null){try { rs.close(); } catch (Exception e){ e.printStackTrace();}}
         if (rs!= null){try { stmt.close(); } catch (Exception e){ e.printStackTrace();}}
         if (rs!= null){try { conn.close(); } catch (Exception e){ e.printStackTrace();}}
     }
    	return res;
    }
    
}
