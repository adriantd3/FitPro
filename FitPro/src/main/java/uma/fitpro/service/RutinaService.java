package uma.fitpro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uma.fitpro.dao.OrdenSesionRutinaRepository;
import uma.fitpro.dao.SesionRepository;
import uma.fitpro.dao.UsuarioRepository;
import uma.fitpro.dto.RutinaDTO;
import uma.fitpro.dao.RutinaRepository;
import uma.fitpro.dto.SesionDTO;
import uma.fitpro.dto.UsuarioDTO;
import uma.fitpro.entity.*;
import uma.fitpro.ui.FiltroRutina;
import uma.fitpro.utils.UtilityFunctions;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static uma.fitpro.utils.UtilityFunctions.getFecha;

@Service
public class RutinaService extends DTOService{

    @Autowired
    private RutinaRepository rutinaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    protected OrdenSesionRutinaRepository ordenSesionRutinaRepository;
    @Autowired
    private SesionRepository sesionRepository;

    public List<RutinaDTO> getRutinasEntrenador(UsuarioDTO entrenadorDTO) {
        Usuario entrenador = this.usuarioRepository.findById(entrenadorDTO.getId()).orElse(null);
        List<Rutina> rutinas = rutinaRepository.getRutinasByEntrenador(entrenador.getId());
        rutinas.sort((r1, r2) -> r1.getNombre().compareTo(r2.getNombre()));
        return this.entidadesADTO(rutinas);
    }

    public List<RutinaDTO> getRutinasEntrenador(UsuarioDTO entrenadorDTO, FiltroRutina filtro) {
        Usuario entrenador = this.usuarioRepository.findById(entrenadorDTO.getId()).orElse(null);
        List<Rutina> rutinas = rutinaRepository.getRutinasByEntrenadorAndFiltro(entrenador.getId(), filtro.getNombre(), filtro.getFechaCreacion(), filtro.getNumeroSesiones());
        rutinas.sort((r1, r2) -> r1.getNombre().compareTo(r2.getNombre()));
        return this.entidadesADTO(rutinas);
    }

    public List<RutinaDTO> getRutinasCliente(UsuarioDTO clienteDTO) {
        Usuario cliente = this.usuarioRepository.findById(clienteDTO.getId()).orElse(null);
        List<Rutina> rutinas = new ArrayList<>(cliente.getRutinasCliente());
        return this.entidadesADTO(rutinas);
    }

    public List<RutinaDTO> getRutinasCliente(UsuarioDTO clienteDTO, FiltroRutina filtro) {
        List<Rutina> rutinas = rutinaRepository.getRutinasByClienteAndFiltro(filtro.getNombre(), filtro.getFechaCreacion(), filtro.getNumeroSesiones(), clienteDTO.getRutinasCliente());
        return this.entidadesADTO(rutinas);
    }


    public List<RutinaDTO> getRutinasSinAsignarACliente(List<Integer> rutinasCliente){
        List<Rutina> rutinasSinAsignar = rutinaRepository.getRutinasByNotInRutinasCliente(rutinasCliente);
        return this.entidadesADTO(rutinasSinAsignar);
    }

    public List<RutinaDTO> getRutinasSinAsignarACliente(List<Integer> rutinasCliente, FiltroRutina filtro){
        List<Rutina> rutinas = rutinaRepository.getRutinasByNotInRutinasClienteAndFiltro(rutinasCliente, filtro.getNombre(), filtro.getFechaCreacion(), filtro.getNumeroSesiones());
        return this.entidadesADTO(rutinas);
    }

    public List<RutinaDTO> getRestantesRutinasByEntrenador(UsuarioDTO entrenadorDTO, List<RutinaDTO> rutinas){
        Usuario entrenador = this.usuarioRepository.findById(entrenadorDTO.getId()).orElse(null);
        List<Integer> rutinasIds = new ArrayList<>();
        for (RutinaDTO rutinaDTO : rutinas) {
            rutinasIds.add(rutinaDTO.getId());
        }
        List<Rutina> todasLasRutinas;
        if (rutinas.isEmpty()){
            todasLasRutinas = rutinaRepository.getRutinasByEntrenador(entrenador.getId());
        }else {
            todasLasRutinas = rutinaRepository.getRestantesRutinasByEntrenador(entrenador.getId(),rutinasIds);
        }
        return this.entidadesADTO(todasLasRutinas);
    }

    public List<RutinaDTO> filtrarRutinas(Integer id_entrenador, String nombre, String fecha) {

        LocalDate fechaFiltrada = getFecha(fecha);
        List<Rutina> rutinas = rutinaRepository.getFilteredRutinasByEntrenadorAndFecha(id_entrenador, nombre, fechaFiltrada);
        rutinas.sort((r1, r2) -> r1.getNombre().compareTo(r2.getNombre()));
        return this.entidadesADTO(rutinas);
    }

    public void borrarRutina(Integer id_rutina) {
        Rutina rutina = rutinaRepository.findById(id_rutina).orElse(null);
        rutinaRepository.delete(rutina);
    }

    public void nuevaRutina(UsuarioDTO entrenadorDTO, String nombre){
        Usuario entrenador = this.usuarioRepository.findById(entrenadorDTO.getId()).orElse(null);
        Rutina rutina = new Rutina();
        rutina.setNombre(nombre);
        rutina.setEntrenador(entrenador);
        rutina.setFechaCreacion(LocalDate.now());
        this.rutinaRepository.save(rutina);
    }

    public void asociarDiaSesion(RutinaDTO rutinaDTO, Integer dia, SesionDTO nueva_sesionDTO, SesionDTO antigua_sesionDTO){
        Sesion antigua_sesion;
        Sesion nueva_sesion;
        if (antigua_sesionDTO == null){
            antigua_sesion = null;
        }else {
            antigua_sesion = sesionRepository.findById(antigua_sesionDTO.getId()).orElse(null);
        }
        if (nueva_sesionDTO == null){
            nueva_sesion = null;
        }else {
            nueva_sesion = sesionRepository.findById(nueva_sesionDTO.getId()).orElse(null);
        }
        Rutina rutina = rutinaRepository.findById(rutinaDTO.getId()).orElse(null);
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

    public Map<Integer, SesionDTO> getDiasSesion(RutinaDTO rutinaDTO){
        Rutina rutina = rutinaRepository.findById(rutinaDTO.getId()).orElse(null);
        List<OrdenSesionRutina> ordenSesiones = new ArrayList<>(rutina.getOrdenSesionRutinas());
        Map<Integer, SesionDTO> mapa = new HashMap<>();
        for (OrdenSesionRutina o : ordenSesiones){
            OrdenSesionRutinaId id = o.getId();
            Integer orden = id.getOrden();
            mapa.put(orden, o.getSesion().toDTO());
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


}
