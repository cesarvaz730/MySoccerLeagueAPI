package bdGF;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import com.adventnet.zoho.client.report.ReportClient;


public class InsertsDB {
	 private ReportClient rc = new ReportClient(Conexion.APIKEY); 
	 
	 ////////////////////////////////////////////////////////////////////////////////////
	    ////////////////////////PRUEBAS PARA INSERT/////////////////////////////////////////
	    public  boolean addRow(String nombreTorneo, String rama, /*String fechaInicio, String fechaFin,*/ 
				  String usuario, String modalidad, String cancha) throws Exception
	    {
	       
	    	rc.login(Conexion.USER, Conexion.PASSWORD, true);
	    	String uri = rc.getURI(Conexion.LOGINNAME,Conexion.DB_NAME,"torneo");
	        Map result = rc.addRow(uri,getRowValues(nombreTorneo,rama,usuario,modalidad,cancha),null);
	        System.out.println("Successfully added the row to " + uri);
	        System.out.println(" The response is " + result);
	        return true;
	    } 


	    private static HashMap getRowValues(String nombreTorneo, String rama, /*String fechaInicio, String fechaFin,*/ 
				  String usuario, String modalidad, String cancha)
	    {	    	
	    	
	        HashMap rowValsMap = new HashMap();
	        rowValsMap.put("nombre_torneo",nombreTorneo);
	        rowValsMap.put("rama",rama);
	        rowValsMap.put("usuario",usuario);
	        rowValsMap.put("modalidad",modalidad);
	        rowValsMap.put("cancha",cancha);
	        rowValsMap.put("activo","S");
	       
	        return rowValsMap;
	    }

}
