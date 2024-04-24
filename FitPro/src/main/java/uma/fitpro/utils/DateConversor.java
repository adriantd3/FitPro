package uma.fitpro.utils;

public class DateConversor {
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
}
