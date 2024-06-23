package uma.fitpro.utils;

import uma.fitpro.dto.DesempenyoSerieDTO;
import uma.fitpro.dto.EjercicioDTO;
import uma.fitpro.dto.SerieDTO;
import uma.fitpro.entity.DesempenyoSerie;
import uma.fitpro.entity.Ejercicio;
import uma.fitpro.entity.Serie;

import java.time.LocalDate;
import java.util.*;

public class UtilityFunctions {
    public static String getDayByNumber(Integer number){
        switch(number){
            case 1: return "Lunes";
            case 2: return "Martes";
            case 3: return "Miércoles";
            case 4: return "Jueves";
            case 5: return "Viernes";
            case 6: return "Sábado";
            case 7: return "Domingo";
            default: throw new NumberFormatException("Invalid number");
        }
    }

    public static Map<Integer,List<String>> getEjercicioParametros(){
        HashMap<Integer, List<String>> mapa = new HashMap<>();
        mapa.put(1, Arrays.asList("Peso (kg)", "Repeticiones"));
        mapa.put(2, Arrays.asList("Distancia (m)", "Duracion (seg)"));
        mapa.put(3, Arrays.asList("Duracion (seg)", "Descanso (seg)"));
        mapa.put(4, Arrays.asList("Repeticiones", "Descanso (seg)"));
        mapa.put(5, Arrays.asList("Repeticiones", "Descanso (seg)"));

        return mapa;
    }

    public static Map<Integer, String> getDiasSemana(){
        Map<Integer, String> mapa = new HashMap<>();
        mapa.put(1, "Lunes");
        mapa.put(2, "Martes");
        mapa.put(3, "Miércoles");
        mapa.put(4, "Jueves");
        mapa.put(5, "Viernes");
        mapa.put(6, "Sábado");
        mapa.put(7, "Domingo");
        return mapa;
    }

    public static LocalDate getFecha(String fecha){
        LocalDate res;
        if (fecha.isEmpty()){
            res = LocalDate.of(1,1,1);
        }else {
            String[] aux = fecha.split("-");
            if (aux.length == 3){
                res = LocalDate.of(Integer.parseInt(aux[0]), Integer.parseInt(aux[1]), Integer.parseInt(aux[2]));
            }else {
                res = LocalDate.of(1,1,1);
            }
        }

        return res;
    }
}
