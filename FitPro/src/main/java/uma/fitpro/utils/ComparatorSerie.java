package uma.fitpro.utils;

import uma.fitpro.dto.SerieDTO;

import java.util.Comparator;

public class ComparatorSerie implements Comparator<SerieDTO> {
    @Override
    public int compare(SerieDTO o1, SerieDTO o2) {
        return o1.getMetrica1().compareTo(o2.getMetrica1());
    }
}
