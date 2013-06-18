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
		JSONObject jsonObjRet= new JSONObject();
		JSONObject jsonObjMesEl= new JSONObject();
		
		///Mes clavado
		jsonObjRet.put("success", new Boolean(true));
		jsonObjRet.put("mesC",1);
		jsonObjRet.put("anio","2013");
		infoRegresar =	jsonObjRet.toString();
	 
	}
	else if (informacion.equals("CatalogoCanchas")) {
		JSONObject jsonObjRet= new JSONObject();
		jsonObjRet.put("success", new Boolean(true));
		jsonObjRet.put("nombre_peticion",informacion);
		jsonObjRet.put("registros", db1.getCatalogoCanchas().toString());
		infoRegresar =	jsonObjRet.toString();
	}
	else if (informacion.equals("CatalogoCategorias")) {
		JSONObject jsonObjRet= new JSONObject();
		jsonObjRet.put("success", new Boolean(true));
		jsonObjRet.put("nombre_peticion",informacion);
		jsonObjRet.put("registros", db1.getCatalogoCategoria().toString());
		
		infoRegresar =	jsonObjRet.toString();
	}
	else if (informacion.equals("CatalogoModalidades")) {
		JSONObject jsonObjRet= new JSONObject();
		jsonObjRet.put("success", new Boolean(true));
		jsonObjRet.put("nombre_peticion",informacion);
		jsonObjRet.put("registros", db1.getCatalogoModalidad().toString());
		
		infoRegresar =	jsonObjRet.toString();
	}
	else{
		JSONObject jsonObjRet= new JSONObject();
		jsonObjRet.put("success", new Boolean(true));
		jsonObjRet.put("parametro1", "mySoccerleague prueba");
		jsonObjRet.put("nombre_peticion",informacion);
		jsonObjRet.put("parametro2", "pruebas viejas");
		
		infoRegresar =	jsonObjRet.toString();
		
	}
	
	
		
%>
<%=infoRegresar%>