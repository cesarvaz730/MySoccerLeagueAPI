<%@ page contentType="application/json;charset=UTF-8"
	import="
		net.sf.json.*,
		bdGF.*,
		java.util.*"	
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
else if (informacion.equals("CatalogoEquipos")) {
	String idTorneo = (request.getParameter("idTorneo")!=null)?request.getParameter("idTorneo"):"";	
	JSONObject jsonObjRet= new JSONObject();
		jsonObjRet.put("success", new Boolean(true));
		jsonObjRet.put("nombre_peticion",informacion);
		jsonObjRet.put("registros", db1.getEquiposTorneo(idTorneo).toString());
		
		infoRegresar =	jsonObjRet.toString();
		
	}
	else if (informacion.equals("CatalogoTorneos")) {
		JSONObject jsonObjRet= new JSONObject();
		jsonObjRet.put("success", new Boolean(true));
		jsonObjRet.put("nombre_peticion",informacion);
		jsonObjRet.put("registros", db1.getTorneos().toString());
		
		infoRegresar =	jsonObjRet.toString();
		
	}else if (informacion.equals("UsuarioCatalogoTorneos")) {
		String idUsuario = (request.getParameter("idUsuario")!=null)?request.getParameter("idUsuario"):"";
		JSONObject jsonObjRet= new JSONObject();
		jsonObjRet.put("success", new Boolean(true));
		jsonObjRet.put("nombre_peticion",informacion);
		jsonObjRet.put("registros", db1.getUserTorneos(idUsuario).toString());
		
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
	}else if (informacion.equals("agregaEquipo")) {
		String idTorneo = (request.getParameter("idTorneo")!=null)?request.getParameter("idTorneo"):"";
		String nombreEquipo = (request.getParameter("nombreEquipo")!=null)?request.getParameter("nombreEquipo"):"";
		
		InsertsDB insertTorneo=new InsertsDB();
		
		JSONObject jsonObjRet= new JSONObject();
		jsonObjRet.put("success", new Boolean(true));
		jsonObjRet.put("nombre_peticion",informacion);
		
		HashMap camposTabla = new HashMap();
		camposTabla.put("nombre_equipo",nombreEquipo);
		camposTabla.put("status","S");
		camposTabla.put("torneo",idTorneo);		
        
		jsonObjRet.put("registros", new Boolean(insertTorneo.insertGenerico(camposTabla,"equipo")));
		
		infoRegresar =	jsonObjRet.toString();
	} else if (informacion.equals("registraUsuario")) {
		String mail = (request.getParameter("mail")!=null)?request.getParameter("mail"):"";
		String token = (request.getParameter("token")!=null)?request.getParameter("token"):"";
		JSONObject jsonObjRet= new JSONObject();
		jsonObjRet.put("success", new Boolean(true));
		jsonObjRet.put("nombre_peticion",informacion);
		if(db1.existeUser("'"+token+"'","'"+mail+"'"))
		{
			SampleDB db2=new SampleDB();
			jsonObjRet.put("resultadoID", db2.getIdUser("'"+token+"'","'"+mail+"'"));
			jsonObjRet.put("resultadoReg", true);
		}
		else
		{
			InsertsDB insertTorneo=new InsertsDB();
					
			HashMap camposTabla = new HashMap();
			camposTabla.put("nick",mail);
			camposTabla.put("token",token);
				if(new Boolean(insertTorneo.insertGenerico(camposTabla,"usuario")))
				{
					SampleDB db3=new SampleDB();
					jsonObjRet.put("resultadoID", db3.getIdUser("'"+token+"'","'"+mail+"'"));
					jsonObjRet.put("resultadoReg", true);
				}
				else
				{
					jsonObjRet.put("resultadoReg", false);
				}
		}
		infoRegresar =	jsonObjRet.toString();
	}else{
		JSONObject jsonObjRet= new JSONObject();
		jsonObjRet.put("success", new Boolean(true));
		jsonObjRet.put("parametro1", "mySoccerleague prueba");
		jsonObjRet.put("nombre_peticion",informacion);
		jsonObjRet.put("parametro2", "pruebas viejas");		
		infoRegresar =	jsonObjRet.toString();		
	}
	
	
		
%>
<%=infoRegresar%>