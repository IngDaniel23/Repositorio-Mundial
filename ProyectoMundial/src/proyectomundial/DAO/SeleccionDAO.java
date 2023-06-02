/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectomundial.DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import proyectomundial.model.Seleccion;
import proyectomundial.util.BasedeDatos;
import static proyectomundial.util.BasedeDatos.ejecutarSQL;

public class SeleccionDAO {

    public SeleccionDAO() {
        BasedeDatos.conectar();
    }
    
    public boolean registrarSeleccion(Seleccion seleccion) {
        
        String sql = "INSERT INTO d_miranda2.seleccion (nombre, continente, director, nacionalidad) values("
                + "'" + seleccion.getNombre() + "', " 
                + "'" + seleccion.getContinente() + "', " 
                + "'" + seleccion.getDt() + "', " 
                + "'" + seleccion.getNacionalidad() + "')";
        
        //     BasedeDatos.conectar();
        boolean registro = BasedeDatos.ejecutarActualizacionSQL(sql);
        //BasedeDatos.desconectar();
        return registro;
    }
    
    
    
    public int getCantidadNacionalidadesDirectoresTecnicos() {
    List<Seleccion> selecciones = getSelecciones();

    Set<String> nacionalidadesDirectores = new HashSet<>();

   
    for (Seleccion seleccion : selecciones) {
        nacionalidadesDirectores.add(seleccion.getDt());
    }

    return nacionalidadesDirectores.size();
}

    

    public List<Seleccion> getSelecciones() {
        
        String sql = "SELECT nombre, continente, director, nacionalidad FROM d_miranda2.seleccion";
        List<Seleccion> selecciones = new ArrayList<Seleccion>();
        
        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);
            
            if(result != null) {
            
                while (result.next()) { 
                    Seleccion seleccion = new Seleccion(result.getString("nombre"), result.getString("continente"), result.getString("director"), result.getString("nacionalidad"));
                    selecciones.add(seleccion);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando selecciones");
        }
        
        return selecciones;
    }
    public String imprimirSeleccionesPorContinente() {
    List<Seleccion> selecciones = getSelecciones();

   
    Map<String, Integer> conteoPorContinente = new HashMap<>();

    for (Seleccion seleccion : selecciones) {
        String continente = seleccion.getContinente();
        conteoPorContinente.put(continente, conteoPorContinente.getOrDefault(continente, 0) + 1);
    }
    StringBuilder sb = new StringBuilder();
    sb.append("Número de selecciones por continente:\n");
    for (String continente : conteoPorContinente.keySet()) {
        int conteo = conteoPorContinente.get(continente);
        sb.append(continente).append(": ").append(conteo).append("\n");
    }

    return sb.toString();
}
    
public int getNumeroSelecciones() {
    List<Seleccion> selecciones = getSelecciones();
    return selecciones.size();
}
public Map<String, Integer> getRankingNacionalidadesDirectoresTecnicos() {
    List<Seleccion> selecciones = getSelecciones();

    
    Map<String, Integer> rankingNacionalidades = new HashMap<>();

    for (Seleccion seleccion : selecciones) {
        String nacionalidad = seleccion.getNacionalidad();
        rankingNacionalidades.put(nacionalidad, rankingNacionalidades.getOrDefault(nacionalidad, 0) + 1);
    }

    return rankingNacionalidades;
}

    

    public String[][] getSeleccionesMatriz() {
        
        String[][] matrizSelecciones = null;
        List<Seleccion> selecciones = getSelecciones();
        
        
        if(selecciones != null && selecciones.size() > 0) {
            
        
            matrizSelecciones = new String[selecciones.size()][4];

            int x = 0;
            for (Seleccion seleccion : selecciones) {

                matrizSelecciones[x][0] = seleccion.getNombre();
                matrizSelecciones[x][1] = seleccion.getContinente();
                matrizSelecciones[x][2] = seleccion.getDt();
                matrizSelecciones[x][3] = seleccion.getNacionalidad();
                x++;
            } 
       }
        
        return matrizSelecciones;
    }

    public Seleccion buscarSeleccionPorNombre(String textoBusqueda) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   
}
