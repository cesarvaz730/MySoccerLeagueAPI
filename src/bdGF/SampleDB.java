package bdGF;

import java.sql.*;

import com.adventnet.zoho.client.report.ReportClient;

import bdGF.utils.ResultSetConverter;
import net.sf.json.*;

public class SampleDB {

	private Connection conn = null;

	public SampleDB() {
		try {
			conn = (new Conexion()).getConn();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	/**
	 * Query generico
	 */
	public JSONArray getqueryGen(String Query) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		JSONArray resultado = null;

		try {
			stmt = conn.createStatement();
			String sql = Query;
			rs = stmt.executeQuery(sql);
			resultado = ResultSetConverter.convert(rs);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					stmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return resultado;
	}

	/**
	 * Query generico result
	 */
	public ResultSet getqueryGenRs(String Query, Statement stmt)
			throws SQLException {

		ResultSet rs = null;

		try {
			stmt = conn.createStatement();
			String sql = Query;
			rs = stmt.executeQuery(sql);

		} finally {
			;
		}
		return rs;
	}

	/**
	 * query para catalogo canchas
	 */
	public JSONArray getCanchas() throws SQLException {
		String sql = "select * from Canchas order by descripcion";
		return getqueryGen(sql);
	}

	/**
	 * query para catalogo categorias
	 */
	public JSONArray getCategorias() throws SQLException {
		String sql = "select * from categoria order by descripcion";
		return getqueryGen(sql);
	}

	/**
	 * query para catalogo modalidad
	 */
	public JSONArray getModalidades() throws SQLException {
		String sql = "select * from Modalidad order by descripcion";
		return getqueryGen(sql);
	}

	public JSONArray getTorneos() throws SQLException {

		String sql = "SELECT torneo.id_torneo as idTorneo, torneo.nombre_torneo as nombreTorneo, categoria.id as idCategoria, categoria.descripcion as nombreCategoria, "
				+ "       torneo.fecha_ini as fechaInicio, torneo.usuario as idUsuario, usuario.nick as nombreUsuario, "
				+ "       torneo.modalidad as idModalidad, Modalidad.descripcion as nombreModalidad, torneo.cancha as idCancha ,"
				+ "       Canchas.descripcion as nombreCancha "
				+ " FROM torneo, categoria, usuario, Modalidad,Canchas"
				+ " WHERE torneo.rama = categoria.id AND "
				+ "      torneo.usuario = usuario.id_usuario AND "
				+ "      torneo.modalidad = Modalidad.id  AND"
				+ "		 torneo.cancha=Canchas.id";

		// id_torneo nombre_torneo rama logo fecha_ini fecha_fin usuario
		// modalidad cancha activo
		return getqueryGen(sql);
	}

	public JSONArray getUserTorneos(String idUsuario) throws SQLException {

		String sql = " SELECT torneo.id_torneo as idTorneo, torneo.nombre_torneo as nombreTorneo, categoria.id as idCategoria, categoria.descripcion as nombreCategoria, "
				+ "        torneo.fecha_ini as fechaInicio, torneo.usuario as idUsuario, usuario.nick as nombreUsuario, "
				+ "        torneo.modalidad as idModalidad, Modalidad.descripcion as nombreModalidad, torneo.cancha as idCancha ,"
				+ "       Canchas.descripcion as nombreCancha "
				+ "  FROM torneo, categoria, usuario, Modalidad,Canchas"
				+ "  WHERE torneo.rama = categoria.id AND "
				+ "       torneo.usuario = usuario.id_usuario AND "
				+ "       torneo.modalidad = Modalidad.id  AND"
				+ "		  torneo.cancha=Canchas.id AND "
				+ "		  torneo.usuario = "
				+ idUsuario;

		// id_torneo nombre_torneo rama logo fecha_ini fecha_fin usuario
		// modalidad cancha activo
		return getqueryGen(sql);
	}

	public JSONArray getEquiposTorneo(String idTorneo) throws SQLException {

		String sql = "SELECT id_equipo as idEquipo,nombre_equipo as nombreEquipo "
				+ " FROM equipo "
				+ " WHERE status='S' AND torneo = "
				+ idTorneo;

		// id_torneo nombre_torneo rama logo fecha_ini fecha_fin usuario
		// modalidad cancha activo
		return getqueryGen(sql);
	}

	public boolean existeUser(String token, String mail) {

		String sql = "SELECT count(*) as conteo " + " FROM usuario "
				+ " WHERE nick=" + mail + " AND token = " + token;

		boolean bRes = false;
		ResultSet rs = null;
		Statement stmt = null;
		// id_torneo nombre_torneo rama logo fecha_ini fecha_fin usuario
		// modalidad cancha activo
		try {
			rs = getqueryGenRs(sql, stmt);
			rs.next();
			String s = rs.getString("conteo");
			if (s.equals("0"))
				bRes = false;
			else {
				bRes = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {try {rs.close();} catch (Exception e) {e.printStackTrace();}}
			if (rs != null && stmt != null) {try {stmt.close();} catch (Exception e) {e.printStackTrace();}}
			if (rs != null) {try {conn.close();} catch (Exception e) {e.printStackTrace();}}
		}
		return bRes;
	}
	public String getIdUser(String token, String mail) {

		String sql = "SELECT id_usuario as is_user " + " FROM usuario "
				+ " WHERE nick=" + mail + " AND token = " + token;

		String bRes = "";
		ResultSet rs = null;
		Statement stmt = null;
		// id_torneo nombre_torneo rama logo fecha_ini fecha_fin usuario
		// modalidad cancha activo
		try {
			rs = getqueryGenRs(sql, stmt);
			rs.next();
			bRes = rs.getString("is_user");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {try {rs.close();} catch (Exception e) {e.printStackTrace();}}
			if (rs != null && stmt != null) {try {stmt.close();} catch (Exception e) {e.printStackTrace();}}
			if (rs != null) {try {conn.close();} catch (Exception e) {e.printStackTrace();}}
		}
		return bRes;
	}

}
