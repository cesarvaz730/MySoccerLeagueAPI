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
		jsonObjRet.put("registros", db1.getCanchas().toString());
		infoRegresar =	jsonObjRet.toString();
	}
	else if (informacion.equals("CatalogoCategorias")) {
		JSONObject jsonObjRet= new JSONObject();
		jsonObjRet.put("success", new Boolean(true));
		jsonObjRet.put("nombre_peticion",informacion);
		jsonObjRet.put("registros", db1.getCategorias().toString());
		
		infoRegresar =	jsonObjRet.toString();
	}
	else if (informacion.equals("CatalogoModalidades")) {
		JSONObject jsonObjRet= new JSONObject();
		jsonObjRet.put("success", new Boolean(true));
		jsonObjRet.put("nombre_peticion",informacion);
		jsonObjRet.put("registros", db1.getModalidades().toString());
		
		infoRegresar =	jsonObjRet.toString();
	}
	else if (informacion.equals("CatalogoTorneos")) {
		JSONObject jsonObjRet= new JSONObject();
		jsonObjRet.put("success", new Boolean(true));
		jsonObjRet.put("nombre_peticion",informacion);
		jsonObjRet.put("registros", db1.getTorneos().toString());
		
		infoRegresar =	jsonObjRet.toString();
		
	}else if (informacion.equals("UsuarioCatalogoTorneos")) {
		String idUsuario = (request.getParameter("idUsuario")!=null)?request.getParameter("id_Usuario"):"";
		JSONObject jsonObjRet= new JSONObject();
		jsonObjRet.put("success", new Boolean(true));
		jsonObjRet.put("nombre_peticion",informacion);
		jsonObjRet.put("registros", db1.getUserTorneos(idUsuario).toString());
		
		infoRegresar =	jsonObjRet.toString();
		
	} else if (informacion.equals("TorneosFavoritos")) {
		String idUsuario = (request.getParameter("idUsuario")!=null)?request.getParameter("id_Usuario"):"";
		JSONObject jsonObjRet= new JSONObject();
		jsonObjRet.put("success", new Boolean(true));
		jsonObjRet.put("nombre_peticion",informacion);
		jsonObjRet.put("registros", db1.getTorneosFavoritos(idUsuario).toString());
		
		infoRegresar =	jsonObjRet.toString();
		
	} else if (informacion.equals("creaTorneo")) {
		String nombreTorneo = (request.getParameter("nombreTorneo")!=null)?request.getParameter("nombreTorneo"):"";
		String rama = (request.getParameter("rama")!=null)?request.getParameter("rama"):"";
		String usuario = (request.getParameter("usuario")!=null)?request.getParameter("usuario"):"";
		String modalidad = (request.getParameter("modalidad")!=null)?request.getParameter("modalidad"):"";
		String cancha = (request.getParameter("cancha")!=null)?request.getParameter("cancha"):"";
		InsertsDB insertTorneo=new InsertsDB();
		
		JSONObject jsonObjRet= new JSONObject();
		jsonObjRet.put("success", new Boolean(true));
		jsonObjRet.put("nombre_peticion",informacion);
		
		HashMap camposTabla = new HashMap();
		camposTabla.put("nombre_torneo",nombreTorneo);
		camposTabla.put("rama",rama);
		camposTabla.put("usuario",usuario);
		camposTabla.put("modalidad",modalidad);
		camposTabla.put("cancha",cancha);
		camposTabla.put("activo","S");
        
		jsonObjRet.put("registros", new Boolean(insertTorneo.insertGenerico(camposTabla,"torneo")));
		
		infoRegresar =	jsonObjRet.toString();
	} else{
		JSONObject jsonObjRet= new JSONObject();
		jsonObjRet.put("success", new Boolean(true));
		jsonObjRet.put("parametro1", "mySoccerleague prueba");
		jsonObjRet.put("nombre_peticion",informacion);
		jsonObjRet.put("parametro2", "pruebas viejas");
		
		infoRegresar =	jsonObjRet.toString();
		
	}
	
	
		
%>
<%=infoRegresar%>