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

    public boolean reservarEquipo(Long id) {
        if (!equipoRepository.existsById(id)) {
            throw new EquipoNoEncontrado(id);
        }

        Equipo equipo = equipoRepository.findById(id).get();

        if (equipo.getCantidad() <= 0) {
            throw new EquipoNoDisponible(id);
        }

        equipo.setCantidad(equipo.getCantidad() - 1);
        equipoRepository.save(equipo);
        return true;
    }

    public boolean devolverEquipo(Long id) {
        if (!equipoRepository.existsById(id)) {
            throw new EquipoNoEncontrado(id);
        }

        Equipo equipo = equipoRepository.findById(id).get();
        equipo.setCantidad(equipo.getCantidad() + 1);
        equipoRepository.save(equipo);
        return true;
    }
}