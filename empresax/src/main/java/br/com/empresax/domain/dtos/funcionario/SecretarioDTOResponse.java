package br.com.empresax.domain.dtos.funcionario;

import br.com.empresax.domain.dtos.PolicyDTO;
import br.com.empresax.domain.entities.funcionario.Secretario;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record SecretarioDTOResponse
    (
        Long id,
        String nome,
        LocalDate mesAnoAdmissao
    ) implements PolicyDTO<Long>
{
    public SecretarioDTOResponse(Secretario secretario) {
        this(secretario.getId(), secretario.getNome(), secretario.getMesAnoAdmissao());
    }
}
