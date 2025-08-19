package co.analisys.gimnasio.controller;

import co.analisys.gimnasio.model.Equipo;
import co.analisys.gimnasio.service.EquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gimnasio")
public class EquipoController {
    @Autowired
    private EquipoService equipoService;

    @PostMapping("/equipos")
    public Equipo agregarEquipo(@RequestBody Equipo equipo) {
        return equipoService.agregarEquipo(equipo);
    }

    @GetMapping("/equipos")
    public List<Equipo> obtenerTodosEquipos() {
        return equipoService.obtenerTodosEquipos();
    }

    @PostMapping("/equipos/{id}/reservar/{cantidad}")
    public boolean reservarEquipo(@PathVariable Long id, @PathVariable Long cantidad) {
        return equipoService.reservarEquipos(id, cantidad);
    }

    @PostMapping("/equipos/{id}/devolver/{cantidad}")
    public boolean devolverEquipo(@PathVariable Long id, @PathVariable Long cantidad) {
        return equipoService.devolverEquipos(id, cantidad);
    }
}