package co.analisys.gimnasio.exception;

public class EquipoNoEncontrado extends RuntimeException {

        public EquipoNoEncontrado(Long equipoId) {
        super("No se encontró el equipo con ID " + equipoId);
    }
}
