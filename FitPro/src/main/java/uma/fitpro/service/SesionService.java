package uma.fitpro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uma.fitpro.dao.SesionRepository;
import uma.fitpro.entity.Sesion;

import java.util.List;

@Service
public class SesionService {
    @Autowired
    protected SesionRepository sesionRepository;

    public Sesion getSesion(Integer id_sesion){
        Sesion sesion = sesionRepository.findById(id_sesion).orElse(null);
        return sesion;
    }


    public List<Sesion> getSesiones(){
        List<Sesion> sesiones = sesionRepository.findAll();
        return sesiones;
    }

    public List<Sesion> filtrarSesiones(String nombre){
        List<Sesion> sesiones = sesionRepository.findByNombre(nombre);
        return sesiones;
    }

    public void borrarSesion(Integer id_sesion){
        Sesion sesion = sesionRepository.findById(id_sesion).orElse(null);
        sesionRepository.delete(sesion);
    }

    public void nuevaSesion(String nombre) {
        Sesion sesion = new Sesion();
        sesion.setNombre(nombre);
        sesionRepository.save(sesion);
    }
}
