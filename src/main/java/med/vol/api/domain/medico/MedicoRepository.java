package med.vol.api.domain.medico;

import med.vol.api.domain.consultas.DatosAgendarConsulta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



import java.time.LocalDateTime;
import java.util.List;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    Page<Medico> findByActivoTrue(Pageable paginacion);

    @Query("SELECT m FROM medicos m WHERE m.activo = true" +
            " AND m.especialidad = :especialidad" +
            " AND m.id_medico NOT IN (" +
            " select c.id_medico from consulta c where c.fecha = :fecha)" +
            " ORDER BY m.nombre")
    List<Medico> seleccionarMedicoConEspecialidadEnFecha(@Param("especialidad") Especialidad especialidad,
                                                         @Param("fecha") LocalDateTime fecha,
                                                         Pageable pageable);

}