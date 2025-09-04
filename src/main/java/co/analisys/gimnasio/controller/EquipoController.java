package co.analisys.gimnasio.controller;

import co.analisys.gimnasio.model.Equipo;
import co.analisys.gimnasio.service.EquipoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gimnasio")
public class EquipoController {
    @Autowired
    private EquipoService equipoService;

    // Endpoint: Agregar nuevo equipo
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Equipo agregado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Solicitud incorrecta"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor"),
        @ApiResponse(responseCode = "401", description = "No autorizado")
    })
    @Operation(summary = "Agregar nuevo equipo", description = "Permite agregar un nuevo equipo al gimnasio")
    @PostMapping("/equipos")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Equipo agregarEquipo(@RequestBody Equipo equipo) {
        return equipoService.agregarEquipo(equipo);
    }

    // Endpoint: Obtener todos los equipos
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Lista de equipos obtenida exitosamente"),
        @ApiResponse(responseCode = "404", description = "No se encontraron equipos"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor"),
        @ApiResponse(responseCode = "401", description = "No autorizado")
    })
    @Operation(summary = "Obtener todos los equipos", description = "Permite obtener la lista de todos los equipos del gimnasio")
    @GetMapping("/equipos")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TRAINER', 'ROLE_MEMBER')")
    public List<Equipo> obtenerTodosEquipos() {
        return equipoService.obtenerTodosEquipos();
    }

    // Endpoint: Reservar equipo
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Equipo reservado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Equipo no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor"),
        @ApiResponse(responseCode = "401", description = "No autorizado")
    })
    @Operation(summary = "Reservar equipo", description = "Permite reservar una cantidad específica de un equipo")
    @PostMapping("/equipos/{id}/reservar/{cantidad}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TRAINER')")
    public boolean reservarEquipo(@PathVariable Long id, @PathVariable Long cantidad) {
        return equipoService.reservarEquipos(id, cantidad);
    }

    // Endpoint: Devolver equipo
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Equipo devuelto exitosamente"),
        @ApiResponse(responseCode = "404", description = "Equipo no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor"),
        @ApiResponse(responseCode = "401", description = "No autorizado")
    })
    @Operation(summary = "Devolver equipo", description = "Permite devolver una cantidad específica de un equipo reservado")
    @PostMapping("/equipos/{id}/devolver/{cantidad}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TRAINER')")
    public boolean devolverEquipo(@PathVariable Long id, @PathVariable Long cantidad) {
        return equipoService.devolverEquipos(id, cantidad);
    }
}