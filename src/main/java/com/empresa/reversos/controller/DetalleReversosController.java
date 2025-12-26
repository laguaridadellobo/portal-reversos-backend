package com.empresa.reversos.controller;

import com.empresa.reversos.dto.DetalleReversoDto;
import com.empresa.reversos.dto.DetalleReversoTotalDto;
import com.empresa.reversos.service.DetalleReversosService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reversos")
public class DetalleReversosController {

    private final DetalleReversosService service;

    public DetalleReversosController(DetalleReversosService service) {
        this.service = service;
    }

    @GetMapping("/total")
    public List<DetalleReversoDto> detalle(
            @RequestParam Integer institutionId,
            @RequestParam String systemId,
            @RequestParam(required = false) Integer operationTypeId,
            @RequestParam String operatorDivId,
            @RequestParam String operatorResponse,
            @RequestParam String statusReverso,
            @RequestParam String fecha) {

        return service.consultar(
                institutionId, systemId, operationTypeId,
                operatorDivId, operatorResponse, statusReverso, fecha
        );
    }

    @GetMapping("/detalle")
    public List<DetalleReversoTotalDto> detalle_reversos(
            @RequestParam Integer institutionId,
            @RequestParam String systemId,
            @RequestParam(required = false) Integer operationTypeId,
            @RequestParam String operatorDivId,
            @RequestParam String operatorResponse,
            @RequestParam String statusReverso,
            @RequestParam String fecha) {

        return service.consultar_detalle(
                institutionId, systemId, operationTypeId,
                operatorDivId, operatorResponse, statusReverso, fecha
        );
    }
}
