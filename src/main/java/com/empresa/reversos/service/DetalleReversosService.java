package com.empresa.reversos.service;

import com.empresa.reversos.dto.DetalleReversoDto;
import com.empresa.reversos.dto.DetalleReversoTotalDto;
import com.empresa.reversos.repository.ReversosRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleReversosService {

    private final ReversosRepository repository;

    public DetalleReversosService(ReversosRepository repository) {
        this.repository = repository;
    }

    public List<DetalleReversoDto> consultar(
            Integer institutionId,
            String systemId,
            Integer operationTypeId,
            String operatorDivId,
            String operatorResponse,
            String statusReverso,
            String fecha) {

        return repository.ejecutarTotalReversos(
                institutionId, systemId, operatorDivId, operatorResponse, statusReverso, fecha
        );
    }

    public List<DetalleReversoTotalDto> consultar_detalle(Integer institutionId, String systemId, Integer operationTypeId,
            String operatorDivId, String operatorResponse, String statusReverso, String fecha) {
        // TODO Auto-generated method stub
         return repository.ejecutarDetalleReversos(
                institutionId, systemId, operationTypeId, operatorDivId, operatorResponse, statusReverso, fecha
        );
    }
}
