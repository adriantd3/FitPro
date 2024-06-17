package uma.fitpro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uma.fitpro.dto.RutinaDTO;
import uma.fitpro.dao.RutinaRepository;
import uma.fitpro.entity.Rutina;

import java.util.List;

@Service
public class RutinaService extends DTOService{

    @Autowired
    private RutinaRepository rutinaRepository;

    @Autowired
    protected OrdenSesionRutinaRepository ordenSesionRutinaRepository;

    public Rutina getRutina(int id_rutina) {
        Rutina rutina = rutinaRepository.findById(id_rutina).orElse(null);
        return rutina;
    }

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
        List<Rutina> todasLasRutinas;
        if (rutinas.isEmpty()){
            todasLasRutinas = rutinaRepository.getRutinasByEntrenador(entrenador.getId());
        }else {
            todasLasRutinas = rutinaRepository.getRestantesRutinasByEntrenador(entrenador.getId(),rutinas);
        }
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

    public void asociarDiaSesion(Rutina rutina, Integer dia, Sesion nueva_sesion, Sesion antigua_sesion){
        if (antigua_sesion != null){ // Ya habia una sesion asociada a ese dia
            OrdenSesionRutinaId id = new OrdenSesionRutinaId();
            id.setOrden(dia);
            id.setSesionId(antigua_sesion.getId());
            id.setRutinaId(rutina.getId());

            OrdenSesionRutina antigua = ordenSesionRutinaRepository.findById(id).orElse(null);
            ordenSesionRutinaRepository.delete(antigua);
        }

        if (nueva_sesion != null){ // No queremos asociar ninguna sesion
            OrdenSesionRutinaId nueva_id = new OrdenSesionRutinaId();
            nueva_id.setOrden(dia);
            nueva_id.setSesionId(nueva_sesion.getId());
            nueva_id.setRutinaId(rutina.getId());

            OrdenSesionRutina nueva = new OrdenSesionRutina();
            nueva.setId(nueva_id);
            nueva.setSesion(nueva_sesion);
            nueva.setRutina(rutina);
            ordenSesionRutinaRepository.save(nueva);
        }
    }

    public Map<Integer, String> getDiasSemana(){
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

    public Map<Integer, Sesion> getDiasSesion(Rutina rutina){

        List<OrdenSesionRutina> ordenSesiones = new ArrayList<>(rutina.getOrdenSesionRutinas());
        Map<Integer, Sesion> mapa = new HashMap<>();
        for (OrdenSesionRutina o : ordenSesiones){
            OrdenSesionRutinaId id = o.getId();
            Integer orden = id.getOrden();
            mapa.put(orden, o.getSesion());
        }
        return mapa;
    }

    public List<RutinaDTO> buscarRutinas(List<Integer> rutinas) {
        List<Rutina> rutinasList = this.rutinaRepository.findAllById(rutinas);
        return this.entidadesADTO(rutinasList);
    }

    public RutinaDTO buscarRutina(Integer id) {
        Rutina rutina = this.rutinaRepository.findById(id).orElse(null);
        if(rutina != null){
            return rutina.toDTO();
        } else {
            return null;
        }
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
