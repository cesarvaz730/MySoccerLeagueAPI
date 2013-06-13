package bdGF;

import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Conexion {
	
	private static final String APIKEY= "eba46241faba2d84df1501860e936474";//<your key>

    private static final String USER = "goalfactory";//<your user name>
    private static final String PASSWORD = "???...lala";//<your password 
    private static final String DB_NAME= "my_soccer_league_db";//<your db name>
    
    
	private  Connection conn = null;
    
    public Conexion(){
    	try {
			iniBD() ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public void iniBD()   throws Exception
    {
       
    	if (APIKEY.length() == 0)
        {
            throw new RuntimeException("Open this file and configure the variables");
        }       
        try
        {
            Class.forName("com.zoho.cloudsql.jdbc.ZohoReportsDriver");
            Properties conProps = new Properties();
            conProps.put("ZOHO_API_KEY",APIKEY);
            conProps.put("user",USER); 
            conProps.put("password",PASSWORD); 
            /*
            conProps.put("PROXYSERVER",PROXYSERVER); 
            conProps.put("PROXYPORT",PROXYPORT); 
            conProps.put("PROXYUSERNAME",PROXYUSERNAME); 
            conProps.put("PROXYPASSWORD",PROXYPASSWORD); 
            */
            conn = DriverManager.getConnection("https://reportsapi.zoho.com/api"                     // No I18N
                                               + "/" + URLEncoder.encode(USER,"UTF-8")
                                               + "/" + URLEncoder.encode(DB_NAME,"UTF-8"),
                                               conProps);
            
        }
        catch (Exception e){ e.printStackTrace();}
        
        
    }
    public Connection getConn() {
		return conn;
	}
	public void setConn(Connection conn) {
		this.conn = conn;
	}

}
