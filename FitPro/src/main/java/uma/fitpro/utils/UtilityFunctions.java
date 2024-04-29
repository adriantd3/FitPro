package uma.fitpro.utils;

import uma.fitpro.entity.Ejercicio;
import uma.fitpro.entity.Serie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UtilityFunctions {
    public static String getDayByNumber(Integer number){
        switch(number){
            case 1: return "Lunes";
            case 2: return "Martes";
            case 3: return "Miercoles";
            case 4: return "Jueves";
            case 5: return "Viernes";
            case 6: return "Sabado";
            case 7: return "Domingo";
            default: throw new NumberFormatException("Invalid number");
        }
    }

    public static Map<Ejercicio, List<Serie>> generateDictionary(List<Serie> seriesList){
        HashMap<Ejercicio,List<Serie>> sesion_dict = new HashMap<>();
        for(Serie serie: seriesList) {
            Ejercicio ejercicio = serie.getEjercicio();
            if(!sesion_dict.containsKey(ejercicio)) {
                sesion_dict.put(ejercicio, new ArrayList<>());
            }
            sesion_dict.get(ejercicio).add(serie);
        }

        return sesion_dict;
    }
}
