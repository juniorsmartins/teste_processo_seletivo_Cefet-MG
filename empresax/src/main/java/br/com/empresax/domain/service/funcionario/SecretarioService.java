package br.com.empresax.domain.service.funcionario;

import br.com.empresax.application.excecao.ResourceNotFoundCustomException;
import br.com.empresax.domain.dtos.funcionario.SecretarioDTORequest;
import br.com.empresax.domain.dtos.funcionario.SecretarioDTOResponse;
import br.com.empresax.domain.entities.funcionario.Secretario;
import br.com.empresax.domain.service.MensagemPadrao;
import br.com.empresax.domain.service.PolicyCrudService;
import br.com.empresax.resources.funcionario.SecretarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class SecretarioService implements PolicyCrudService<SecretarioDTORequest, SecretarioDTOResponse, Long> {

    @Autowired
    private SecretarioRepository repository;

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    @Override
    public SecretarioDTOResponse cadastrar(SecretarioDTORequest dto) {
        return Optional.of(dto)
                .map(Secretario::new)
                .map(secretario -> this.repository.saveAndFlush(secretario))
                .map(SecretarioDTOResponse::new)
                .orElseThrow();
    }

    @Override
    public SecretarioDTOResponse consultarPorId(Long id) {
        return this.repository.findById(id)
                .map(SecretarioDTOResponse::new)
                .orElseThrow(() -> new ResourceNotFoundCustomException(MensagemPadrao.RECURSO_NAO_ENCONTRADO));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    @Override
    public String apagarPorId(Long id) {
        return this.repository.findById(id)
                .map(secretario -> {
                    this.repository.delete(secretario);
                    return MensagemPadrao.RECURSO_APAGADO;
                })
                .orElseThrow(() -> new ResourceNotFoundCustomException(MensagemPadrao.RECURSO_NAO_ENCONTRADO));
    }
}

