package com.wareline.agenda.domain.paciente;

import java.util.Set;

import com.wareline.agenda.shared.entities.AggregateRoot;
import com.wareline.agenda.shared.validation.ValidationHandlerInterface;
import com.wareline.agenda.shared.vo.EnderecoVO;

public class PacienteEntity extends AggregateRoot<PacienteID> {
    private String nome;
    private String email;
    private String telefone;
    private Set<EnderecoVO> enderecos;

    public PacienteEntity(final String id, final String nome, final String email, final String telefone,
            final Set<EnderecoVO> enderecos) {
        super(id != null ? PacienteID.from(id) : PacienteID.unique());
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.enderecos = enderecos;
    }

    public static PacienteEntity create(final String nome, final String email, final String telefone,
            final Set<EnderecoVO> enderecos) {
        return new PacienteEntity(null, nome, email, telefone, enderecos);
    }

    public static PacienteEntity create(final String id, final String nome, final String email,
            final String telefone,
            final Set<EnderecoVO> enderecos) {
        return new PacienteEntity(id, nome, email, telefone, enderecos);
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public Set<EnderecoVO> getEnderecos() {
        return enderecos;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void addEndereco(EnderecoVO endereco) {
        this.enderecos.add(endereco);
    }

    public void removeEndereco(EnderecoVO endereco) {
        this.enderecos.remove(endereco);
    }

    public void updateEndereco(EnderecoVO endereco) {
        this.enderecos.remove(endereco);
        this.enderecos.add(endereco);
    }

    public void addEnderecos(Set<EnderecoVO> enderecos) {
        this.enderecos.addAll(enderecos);
    }

    public void removeEnderecos(Set<EnderecoVO> enderecos) {
        this.enderecos.removeAll(enderecos);
    }

    public void updateEnderecos(Set<EnderecoVO> enderecos) {
        this.enderecos.clear();
        this.enderecos.addAll(enderecos);
    }

    @Override
    public void validate(ValidationHandlerInterface handler) {
        new PacienteValidator(this, handler).validate();
    }
}
