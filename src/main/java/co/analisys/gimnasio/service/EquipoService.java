package co.analisys.gimnasio.service;

import co.analisys.gimnasio.exception.EquipoNoDisponible;
import co.analisys.gimnasio.exception.EquipoNoEncontrado;
import co.analisys.gimnasio.model.Equipo;
import co.analisys.gimnasio.repository.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipoService {
    @Autowired
    private EquipoRepository equipoRepository;

    public Equipo agregarEquipo(Equipo equipo) {
        return equipoRepository.save(equipo);
    }

    public List<Equipo> obtenerTodosEquipos() {
        return equipoRepository.findAll();
    }

    public boolean reservarEquipos(Long id, Long cantidad) {
        if (!equipoRepository.existsById(id)) {
            throw new EquipoNoEncontrado(id);
        }

        Equipo equipo = equipoRepository.findById(id).get();

        if (equipo.getCantidad() < cantidad) {
            throw new EquipoNoDisponible(id);
        }

        equipo.setCantidad(equipo.getCantidad() - cantidad.intValue());
        equipoRepository.save(equipo);
        return true;
    }

    public boolean devolverEquipos(Long id, Long cantidad) {
        if (!equipoRepository.existsById(id)) {
            throw new EquipoNoEncontrado(id);
        }

        Equipo equipo = equipoRepository.findById(id).get();
        equipo.setCantidad(equipo.getCantidad() + cantidad.intValue());
        equipoRepository.save(equipo);
        return true;
    }
}