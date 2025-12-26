package com.empresa.reversos.repository;

import com.empresa.reversos.dto.DetalleReversoDto;
import com.empresa.reversos.dto.DetalleReversoTotalDto;

import oracle.jdbc.OracleTypes;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReversosRepository {

    private final DataSource dataSource;

    public ReversosRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<DetalleReversoDto> ejecutarTotalReversos(
            Integer institutionId,
            String systemId,
            String operatorDivId,
            String operatorResponse,
            String statusReverso,
            String fecha) {

        List<DetalleReversoDto> lista = new ArrayList<>();

        String sql = "{ call PA_WEBPORTAL_TA_01.SP_GET_TOTALREVERSOS(?,?,?,?,?,?,?,?,?) }";

        try (Connection conn = dataSource.getConnection();
                CallableStatement cs = conn.prepareCall(sql)) {

            // IN
            cs.setInt(1, institutionId);
            cs.setString(2, systemId);
            cs.setString(3, operatorDivId);
            cs.setString(4, operatorResponse);
            if (statusReverso == null
                    || statusReverso.trim().isEmpty()
                    || statusReverso.equalsIgnoreCase("null")) {

                cs.setNull(5, Types.INTEGER);

            } else {
                cs.setInt(5, Integer.parseInt(statusReverso));
            }

            cs.setString(6, fecha);

            // OUT
            cs.registerOutParameter(7, OracleTypes.CURSOR);
            cs.registerOutParameter(8, Types.INTEGER);
            cs.registerOutParameter(9, Types.VARCHAR);

            cs.execute();

            if (cs.getInt(8) != 0) {
                throw new RuntimeException(cs.getString(9));
            }

            ResultSet rs = (ResultSet) cs.getObject(7);
            while (rs.next()) {
                DetalleReversoDto dto = new DetalleReversoDto();
                dto.setFecha(rs.getString(1));
                dto.setInstitutionId(rs.getInt(2));
                dto.setSystemId(rs.getInt(3));
                dto.setOperatorDivId(rs.getInt(4));
                dto.setOperatorResponse(rs.getInt(5));
                dto.setTotalReversos(rs.getInt(6));
                lista.add(dto);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error ejecutando SP_GET_TOTALREVERSOS", e);
        }

        return lista;
    }

    public List<DetalleReversoTotalDto> ejecutarDetalleReversos(Integer institutionId, String systemId,
            Integer operationTypeId, String operatorDivId, String operatorResponse, String statusReverso,
            String fecha) {

        List<DetalleReversoTotalDto> lista = new ArrayList<>();

        String sql = "{ call PA_WEBPORTAL_TA_01.SP_GET_DETALLEREVERSOS(?,?,?,?,?,?,?,?,?,?) }";

        try (Connection conn = dataSource.getConnection();
                CallableStatement cs = conn.prepareCall(sql)) {

            // IN
            cs.setInt(1, institutionId);
            cs.setString(2, systemId);
                   if (operationTypeId == null
                   ) {

                cs.setNull(3, Types.INTEGER);

            } else {
                cs.setInt(3,operationTypeId);
            }
            cs.setString(4, operatorDivId);
            cs.setString(5, operatorResponse);
            if (statusReverso == null
                    || statusReverso.trim().isEmpty()
                    || statusReverso.equalsIgnoreCase("null")) {

                cs.setNull(6, Types.INTEGER);

            } else {
                cs.setInt(6, Integer.parseInt(statusReverso));
            }

            cs.setString(7, fecha);

            // OUT
            cs.registerOutParameter(8, OracleTypes.CURSOR);
            cs.registerOutParameter(9, Types.INTEGER);
            cs.registerOutParameter(10, Types.VARCHAR);

            cs.execute();

            if (cs.getInt(9) != 0) {
                throw new RuntimeException(cs.getString(10));
            }

            ResultSet rs = (ResultSet) cs.getObject(8);
            while (rs.next()) {
              DetalleReversoTotalDto dto = new DetalleReversoTotalDto();

                dto.setFecha(rs.getString("FCTRANSACTIONID"));
                dto.setSystemId(rs.getInt("FISYSTEMID"));
                dto.setInstitutionId(rs.getInt("FIINSTITUTIONID"));
                dto.setoperationTypeId(rs.getInt("FIOPERATIONTYPEID"));
                dto.setOperatorDivId(rs.getInt("FIOPERATORDIVID"));
                dto.setOperatorResponse(rs.getInt("FIOPERATORID"));
                dto.setTotalReversos(rs.getInt("TOTALREVERSOS")); 

                lista.add(dto);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error ejecutando SP_GET_TOTALREVERSOS", e);
        }

        return lista;
    }

}
