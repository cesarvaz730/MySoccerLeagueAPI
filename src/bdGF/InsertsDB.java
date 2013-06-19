package bdGF;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import com.adventnet.zoho.client.report.ParseException;
import com.adventnet.zoho.client.report.ReportClient;
import com.adventnet.zoho.client.report.ServerException;


public class InsertsDB {
	 private ReportClient rc;
	 
	 //contructor 
	 public InsertsDB() throws IOException, Exception
	 {
		 rc = new ReportClient(Conexion.APIKEY);
		 rc.login(Conexion.USER, Conexion.PASSWORD, true);	    	
	 }	 
	public boolean insertGenerico(HashMap elementos,String tabla){
		 String uri;
		 boolean resultado=false;
		try {
			uri = rc.getURI(Conexion.LOGINNAME,Conexion.DB_NAME,tabla);
			Map result = rc.addRow(uri,elementos,null);
			resultado=true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     return  resultado; 
	 }
}
