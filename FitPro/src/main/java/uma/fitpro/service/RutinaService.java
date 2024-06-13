package uma.fitpro.service;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uma.fitpro.dao.RutinaRepository;
import uma.fitpro.dao.UsuarioRepository;
import uma.fitpro.entity.Rutina;
import uma.fitpro.entity.Usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class RutinaService {

    @Autowired
    protected UsuarioRepository usuarioRepository;

    @Autowired
    protected RutinaRepository rutinaRepository;

    public List<Rutina> getRutinasEntrenador(Usuario entrenador) {

        List<Rutina> rutinas = rutinaRepository.getRutinasByEntrenador(entrenador.getId());
        Collections.sort(rutinas);

        return rutinas;
    }

    public List<Rutina> getRutinasCliente(Usuario cliente) {

        List<Rutina> rutinas = new ArrayList<>(cliente.getRutinasCliente());
        return rutinas;
    }

    public List<Rutina> getRestantesRutinasByEntrenador(Usuario entrenador, List<Rutina> rutinas){

        List<Rutina> todasLasRutinas = rutinaRepository.getRestantesRutinasByEntrenador(entrenador.getId(),rutinas);

        return todasLasRutinas;
    }

    public List<Rutina> filtrarRutinas(Integer id_entrenador, String nombre, String fecha) {

        LocalDate fechaFiltrada = getFecha(fecha);
        List<Rutina> rutinas = rutinaRepository.getFilteredRutinasByEntrenadorAndFecha(id_entrenador, nombre, fechaFiltrada);
        Collections.sort(rutinas);

        return rutinas;
    }

    public void borrarRutina(Integer id_rutina) {
        Rutina rutina = rutinaRepository.findById(id_rutina).orElse(null);
        rutinaRepository.delete(rutina);
    }

    public void nuevaRutina(Usuario entrenador, String nombre){
        Rutina rutina = new Rutina();
        rutina.setNombre(nombre);
        rutina.setEntrenador(entrenador);
        rutina.setFechaCreacion(LocalDate.now());
        this.rutinaRepository.save(rutina);
    }


    // --------------------------- METODOS AUXILIARES ---------------------------

    private LocalDate getFecha(String fecha){
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
