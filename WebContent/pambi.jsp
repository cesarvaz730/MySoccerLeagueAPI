<%@ page contentType="application/json;charset=UTF-8"
	import="
		net.sf.json.*,
		bdGF.*,
		java.util.*
		"
	
%>
<%
String informacion = (request.getParameter("informacion")!=null)?request.getParameter("informacion"):"";
SampleDB db1=new SampleDB();

String infoRegresar="";
if (informacion.equals("ejemploMSL")) {
		///////////version ok///////////
		////////////////////////////////
		
		
		JSONObject jsonObjRet= new JSONObject();
		JSONObject jsonObjMesEl= new JSONObject();
		
		///Mes clavado
		jsonObjRet.put("success", new Boolean(true));
		jsonObjRet.put("mesC",1);
		jsonObjRet.put("anio","2013");
		infoRegresar =	jsonObjRet.toString();
	 
	}
	else if (informacion.equals("porAtenderDAC")) {
		JSONObject jsonObjRet= new JSONObject();
		jsonObjRet.put("success", new Boolean(true));
		
		infoRegresar =	jsonObjRet.toString();
	}
	else{
		JSONObject jsonObjRet= new JSONObject();
		jsonObjRet.put("success", new Boolean(true));
		jsonObjRet.put("parametro1", "mySoccerleague");
		jsonObjRet.put("parametro2", db1.getConsultaRes());
		
		infoRegresar =	jsonObjRet.toString();
		
	}
	
	
		
%>
<%=infoRegresar%>