




package proyectomundial.DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import proyectomundial.model.Resultados;
import proyectomundial.util.BasedeDatos;
import static proyectomundial.util.BasedeDatos.ejecutarSQL;

/**
 *
 * 
 */
public class ResultadosDAO {

    public ResultadosDAO() {
        BasedeDatos.conectar();
    }

    public boolean registrarResultados(Resultados resultado) {
    
        
        String sql = "INSERT INTO d_miranda2.partidos (grupo, Local, visitante, continente_local,continente_visitante,goles_local,goles_visitante) values("
                + "'" + resultado.getGrupo() + "', "
                + "'" + resultado.getLocal() + "', "
                + "'" + resultado.getVisitante() + "', "
                + "'" + resultado.getContinente_local() + "', "
                + "'" + resultado.getContinente_visitante()+ "', "
                + "'" + resultado.getGoles_local()+ "', "
                + "'" + resultado.getGoles_visitante()+ "')";

        //BasedeDatos.conectar();
        boolean registro = BasedeDatos.ejecutarActualizacionSQL(sql);
        //BasedeDatos.desconectar();
        return registro;
    }

    public List<Resultados> getResultados() {
        
        String sql = "SELECT grupo, Local, visitante, continente_local,continente_visitante,goles_Local,goles_visitante FROM d_miranda2.partidos";
        List<Resultados> resultados = new ArrayList<Resultados>();

        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);

            if (result != null) {

                while (result.next()) {
                    Resultados resultado = new Resultados(result.getString("grupo"), result.getString("Local"), result.getString("visitante"), result.getString("continente_local"), result.getString("continente_visitante"), result.getString("goles_local"), result.getString("goles_visitante"));
                    resultados.add(resultado);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando resultados");
        }

        return resultados;
    }

public int getNumeroPartidosCargados() throws Exception {
    String sql = "SELECT COUNT(*) AS count FROM d_miranda2.partidos";
    int count = 0;

    try {
        ResultSet result = BasedeDatos.ejecutarSQL(sql);

        if (result != null && result.next()) {
            count = result.getInt("count");
        }
    } catch (SQLException e) {
        System.out.println(e.toString());
        System.out.println("Error obteniendo el número de partidos cargados");
    }

    return count;
}



public double getPromedioGolesPorPartido() throws Exception {
    String sql = "SELECT AVG(goles_Local + goles_visitante) AS promedio FROM d_miranda2.partidos";
    double promedio = 0.0;

    try {
        ResultSet result = BasedeDatos.ejecutarSQL(sql);

        if (result != null && result.next()) {
            promedio = result.getDouble("promedio");
        }
    } catch (SQLException e) {
        System.out.println(e.toString());
        System.out.println("Error obteniendo el promedio de goles por partido");
    }

    return promedio;
}



public List<String> getSeleccionesEMasGolesMenosG() throws Exception {
    List<String> resultados = new ArrayList<>();

    String sqlMaxGoles = "SELECT Local, SUM(goles_Local) AS total_goles FROM d_miranda2.partidos GROUP BY Local ORDER BY total_goles DESC LIMIT 1";
    String sqlMinGoles = "SELECT Local, SUM(goles_Local) AS total_goles FROM d_miranda2.partidos GROUP BY Local ORDER BY total_goles ASC LIMIT 1";

    try {
        ResultSet resultMaxGoles = BasedeDatos.ejecutarSQL(sqlMaxGoles);
        ResultSet resultMinGoles = BasedeDatos.ejecutarSQL(sqlMinGoles);

        if (resultMaxGoles != null && resultMaxGoles.next()) {
            String seleccionMaxGoles = resultMaxGoles.getString("Local");
            int golesMaxGoles = resultMaxGoles.getInt("total_goles");
            resultados.add("Selección con más goles: " + seleccionMaxGoles + " (Goles totales: " + golesMaxGoles + ")");
        }

        if (resultMinGoles != null && resultMinGoles.next()) {
            String seleccionMinGoles = resultMinGoles.getString("Local");
            int golesMinGoles = resultMinGoles.getInt("total_goles");
            resultados.add("Selección con menos goles: " + seleccionMinGoles + " (Goles totales: " + golesMinGoles + ")");
        }
    } catch (SQLException e) {
        System.out.println(e.toString());
        System.out.println("Error al obtener las selecciones con más y menos goles");
    }

    return resultados;
}



public List<String> getSeleccionConMasYPuntosMenosP() throws Exception {
    List<String> resultados = new ArrayList<>();

    String sqlMaxPuntos = "SELECT Local, SUM(CASE WHEN goles_Local > goles_visitante THEN 3 WHEN goles_Local = goles_visitante THEN 1 ELSE 0 END) AS total_puntos FROM d_miranda2.partidos GROUP BY Local ORDER BY total_puntos DESC LIMIT 1";
    String sqlMinPuntos = "SELECT Local, SUM(CASE WHEN goles_Local > goles_visitante THEN 3 WHEN goles_Local = goles_visitante THEN 1 ELSE 0 END) AS total_puntos FROM d_miranda2.partidos GROUP BY Local ORDER BY total_puntos ASC LIMIT 1";

    try {
        ResultSet resultMaxPuntos = BasedeDatos.ejecutarSQL(sqlMaxPuntos);
        ResultSet resultMinPuntos = BasedeDatos.ejecutarSQL(sqlMinPuntos);

        if (resultMaxPuntos != null && resultMaxPuntos.next()) {
            String seleccionMaxPuntos = resultMaxPuntos.getString("Local");
            int puntosMaxPuntos = resultMaxPuntos.getInt("total_puntos");
            resultados.add("Selección con más puntos: " + seleccionMaxPuntos + " (Puntos totales: " + puntosMaxPuntos + ")");
        }

        if (resultMinPuntos != null && resultMinPuntos.next()) {
            String seleccionMinPuntos = resultMinPuntos.getString("Local");
            int puntosMinPuntos = resultMinPuntos.getInt("total_puntos");
            resultados.add("Selección con menos puntos: " + seleccionMinPuntos + " (Puntos totales: " + puntosMinPuntos + ")");
        }
    } catch (SQLException e) {
        System.out.println(e.toString());
        System.out.println("Error al obtener las selecciones con más y menos puntos");
    }

    return resultados;
}




public List<String> obtenerGyE() throws Exception {
    List<String> resultados = new ArrayList<>();

    String sqlGanadores = "SELECT COUNT(*) AS count FROM d_miranda2.partidos WHERE goles_Local <> goles_visitante";
    String sqlEmpates = "SELECT COUNT(*) AS count FROM d_miranda2.partidos WHERE goles_Local = goles_visitante";
    int partidosGanados = 0;
    int partidosEmpatados = 0;

    try {
        ResultSet resultGanadores = BasedeDatos.ejecutarSQL(sqlGanadores);
        ResultSet resultEmpates = BasedeDatos.ejecutarSQL(sqlEmpates);

        if (resultGanadores != null && resultGanadores.next()) {
            partidosGanados = resultGanadores.getInt("count");
        }

        if (resultEmpates != null && resultEmpates.next()) {
            partidosEmpatados = resultEmpates.getInt("count");
        }
    } catch (SQLException e) {
        System.out.println(e.toString());
    }
    
    resultados.add("Número de partidos ganados: " + partidosGanados);
    resultados.add("Número de partidos empatados: " + partidosEmpatados);
    
    return resultados;
}

public List<String> getContinentesConMasYGolesMenosG() throws Exception {
    List<String> resultados = new ArrayList<>();

    String sqlMaxGoles = "SELECT continente_local, SUM(goles_local) AS total_goles FROM d_miranda2.partidos GROUP BY continente_local ORDER BY total_goles DESC LIMIT 1";
    String sqlMinGoles = "SELECT continente_local, SUM(goles_local) AS total_goles FROM d_miranda2.partidos GROUP BY continente_local ORDER BY total_goles ASC LIMIT 1";

    try {
        ResultSet resultMaxGoles = BasedeDatos.ejecutarSQL(sqlMaxGoles);
        ResultSet resultMinGoles = BasedeDatos.ejecutarSQL(sqlMinGoles);

        if (resultMaxGoles != null && resultMaxGoles.next()) {
            String continenteMaxGoles = resultMaxGoles.getString("continente_local");
            int golesMaxGoles = resultMaxGoles.getInt("total_goles");
            resultados.add("Continente(s) con más goles: " + continenteMaxGoles + " (Goles totales: " + golesMaxGoles + ")");
        }

        if (resultMinGoles != null && resultMinGoles.next()) {
            String continenteMinGoles = resultMinGoles.getString("continente_local");
            int golesMinGoles = resultMinGoles.getInt("total_goles");
            resultados.add("Continente(s) con menos goles: " + continenteMinGoles + " (Goles totales: " + golesMinGoles + ")");
        }
    } catch (SQLException e) {
        System.out.println(e.toString());
        System.out.println("Error al obtener los continentes con más y menos goles");
    }

    return resultados;
}

public Map<String, List<String>> getClasificadosPorGrupo() throws Exception {
    Map<String, List<String>> clasificados = new HashMap<>();

    String sql = "SELECT grupo, Local, " +
                 "SUM(CASE WHEN goles_Local > goles_visitante THEN 3 " +
                 "         WHEN goles_Local = goles_visitante THEN 1 " +
                 "         ELSE 0 " +
                 "    END) AS puntos " +
                 "FROM d_miranda2.partidos " +
                 "GROUP BY grupo, Local " +
                 "ORDER BY grupo, puntos DESC";

    try {
        ResultSet result = BasedeDatos.ejecutarSQL(sql);

        if (result != null) {
            while (result.next()) {
                String grupo = result.getString("grupo");
                String equipo = result.getString("Local");

                if (!clasificados.containsKey(grupo)) {
                    clasificados.put(grupo, new ArrayList<>());
                }

                List<String> equiposGrupo = clasificados.get(grupo);
                if (equiposGrupo.size() < 2) {
                    equiposGrupo.add(equipo);
                }
            }
        }
    } catch (SQLException e) {
        System.out.println(e.toString());
        System.out.println("Error al obtener los clasificados por grupo");
    }

    return clasificados;
}



public List<String> GetPardinosMasMenosGol() throws Exception {
    List<String> resultados = new ArrayList<>();

    String sqlMaxGoles = "SELECT * FROM d_miranda2.partidos ORDER BY goles_Local + goles_visitante DESC LIMIT 1";
    String sqlMinGoles = "SELECT * FROM d_miranda2.partidos ORDER BY goles_Local + goles_visitante ASC LIMIT 1";

    try {
        ResultSet resultMaxGoles = BasedeDatos.ejecutarSQL(sqlMaxGoles);
        ResultSet resultMinGoles = BasedeDatos.ejecutarSQL(sqlMinGoles);

        if (resultMaxGoles != null && resultMaxGoles.next()) {
            String partidoMaxGoles = resultMaxGoles.getString("Local") + " vs " + resultMaxGoles.getString("visitante");
            int golesMaxGoles = resultMaxGoles.getInt("goles_Local") + resultMaxGoles.getInt("goles_visitante");
            resultados.add("Partido con más goles: " + partidoMaxGoles + " (Goles: " + golesMaxGoles + ")");
        }

        if (resultMinGoles != null && resultMinGoles.next()) {
            String partidoMinGoles = resultMinGoles.getString("Local") + " vs " + resultMinGoles.getString("visitante");
            int golesMinGoles = resultMinGoles.getInt("goles_Local") + resultMinGoles.getInt("goles_visitante");
            resultados.add("Partido con menos goles: " + partidoMinGoles + " (Goles: " + golesMinGoles + ")");
        }
    } catch (SQLException e) {
        System.out.println(e.toString());
        System.out.println("Error al obtener los partidos con más y menos goles");
    }

    return resultados;
}
    public String[][] getResultadosMatriz() {
        String[][] matrizResultados = null;
        List<Resultados> resultados = getResultados();

        if (resultados != null && resultados.size() > 0) {

            matrizResultados = new String[resultados.size()][7];

            int x = 0;
            for (Resultados resultado : resultados) {

                matrizResultados[x][0] = resultado.getGrupo();
                matrizResultados[x][1] = resultado.getLocal();
                matrizResultados[x][2] = resultado.getVisitante();
                matrizResultados[x][3] = resultado.getGoles_local();
                matrizResultados[x][4] = resultado.getGoles_visitante();
                matrizResultados[x][5] = resultado.getGoles_local();
                matrizResultados[x][6] = resultado.getGoles_visitante();
                x++;
            }
        }

        return matrizResultados;
    }
}