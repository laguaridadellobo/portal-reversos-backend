package com.empresa.reversos.dto;

import java.sql.Date;

public class DetalleReversoDto {
    private String fecha;
    private Integer institutionId;
    private Integer systemId;
    private Integer operatorDivId;
    private Integer operatorResponse;
    private Integer totalReversos;

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }
    public Integer getInstitutionId() { return institutionId; }
    public void setInstitutionId(Integer institutionId) { this.institutionId = institutionId; }
    public Integer getSystemId() { return systemId; }
    public void setSystemId(Integer systemId) { this.systemId = systemId; }
    public Integer getOperatorDivId() { return operatorDivId; }
    public void setOperatorDivId(Integer operatorDivId) { this.operatorDivId = operatorDivId; }
    public Integer getOperatorResponse() { return operatorResponse; }
    public void setOperatorResponse(Integer operatorResponse) { this.operatorResponse = operatorResponse; }
    public Integer getTotalReversos() { return totalReversos; }
    public void setTotalReversos(Integer totalReversos) { this.totalReversos = totalReversos; }
    public void setFecha(Date date) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setFecha'");
    }
}
