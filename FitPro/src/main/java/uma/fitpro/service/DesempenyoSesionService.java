package uma.fitpro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uma.fitpro.dao.*;
import uma.fitpro.dto.*;
import uma.fitpro.entity.*;

import java.time.LocalDate;
import java.util.*;

@Service
public class DesempenyoSesionService extends DTOService{

    @Autowired
    private DesempenyoSesionRepository desempenyoSesionRepository;

    @Autowired
    private DesempenyoSerieRepository desempenyoSerieRepository;

    @Autowired
    private SesionRepository sesionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private OrdenSesionRutinaRepository ordenSesionRutinaRepository;

    public List<DesempenyoSesionDTO> getDesempenyosSesionesByCliente(UsuarioDTO clienteDTO){
        Usuario cliente = usuarioRepository.findById(clienteDTO.getId()).orElse(null);
        List<DesempenyoSesion> desempenyoSesions = new ArrayList<>(cliente.getDesempenyoSesions());
        return this.entidadesADTO(desempenyoSesions);
    }

    public DesempenyoSesionDTO buscarDesempenyoSesion(Integer id){
        DesempenyoSesion desempenyoSesion = desempenyoSesionRepository.findById(id).orElse(null);
        if(desempenyoSesion != null){
            return desempenyoSesion.toDTO();
        } else {
            return null;
        }
    }

    public List<DesempenyoSesionDTO> buscarDesempenyosSesionPorClienteYSesion(Integer client_id, Integer sesion_id){
        List<DesempenyoSesion> desempenyoSesionList =
                desempenyoSesionRepository.findByClientIDAndSesionID(client_id, sesion_id);
        return this.entidadesADTO(desempenyoSesionList);
    }

    public DesempenyoSesionDTO nuevoDesempenyoSesion(Integer sesion_id, Integer cliente_id){
        Sesion sesion = sesionRepository.findById(sesion_id).orElse(null);
        Usuario cliente = usuarioRepository.findById(cliente_id).orElse(null);

        DesempenyoSesion desempenyoSesion = new DesempenyoSesion();
        desempenyoSesion.setSesion(sesion);
        desempenyoSesion.setUsuario(cliente);
        desempenyoSesion.setFecha(LocalDate.now());
        desempenyoSesion.setTerminado((byte) 0);

        desempenyoSesionRepository.saveAndFlush(desempenyoSesion);

        for(Serie serie : sesion.getSeries()){
            DesempenyoSerie des_serie = crearDesempenyoSerieFromSerie(serie, desempenyoSesion);
            desempenyoSerieRepository.saveAndFlush(des_serie);
        }

        return desempenyoSesion.toDTO();
    }

    public void terminarDesempenyoSesion(Integer id){
        DesempenyoSesion desempenyoSesion = desempenyoSesionRepository.findById(id).orElse(null);
        if(desempenyoSesion != null){
            desempenyoSesion.setTerminado((byte) 1);
            desempenyoSesionRepository.save(desempenyoSesion);
        }
    }

    public void borrarDesempenyoSesion(Integer id){
        desempenyoSesionRepository.deleteById(id);
    }

    public HashMap<OrdenSesionRutinaDTO,List<DesempenyoSesionDTO>> seguimientoRutina(UsuarioDTO clienteDTO, RutinaDTO rutinaDTO){
        HashMap<OrdenSesionRutinaDTO,List<DesempenyoSesionDTO>> seguimientoRutina = new HashMap<>();
        List<OrdenSesionRutina> sesionesRutina = ordenSesionRutinaRepository.findOrdenSesionRutinaByRutinaID(rutinaDTO.getId());
        List<OrdenSesionRutinaDTO> sesionesRutinaDTO = this.entidadesADTO(sesionesRutina);
        for (OrdenSesionRutinaDTO s: sesionesRutinaDTO){
            List<DesempenyoSesionDTO> desempenyoSesiones = buscarDesempenyosSesionPorClienteYSesion(clienteDTO.getId(), s.getIdSesion());
            seguimientoRutina.put(s,desempenyoSesiones);
        }
        return seguimientoRutina;
    }

    private DesempenyoSerie crearDesempenyoSerieFromSerie(Serie serie, DesempenyoSesion des) {
        DesempenyoSerie des_serie = new DesempenyoSerie();

        des_serie.setDesempenyoSesion(des);
        des_serie.setEjercicio(serie.getEjercicio());
        des_serie.setMetrica1(serie.getMetrica1());
        des_serie.setMetrica2(serie.getMetrica2());

        return des_serie;
    }
}
