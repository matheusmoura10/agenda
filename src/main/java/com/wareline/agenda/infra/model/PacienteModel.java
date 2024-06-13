package com.wareline.agenda.infra.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.wareline.agenda.shared.vo.EnderecoVO;

@Document(collection = "pacientes")
public class PacienteModel {

    @Id
    private String id;

    @Indexed(unique = true)
    private String nome;
    private String email;
    private String telefone;

    @Field("enderecos")
    private List<EnderecoVO> enderecos;


    public PacienteModel(final String id, final String nome, final String email, final String telefone,
            final List<EnderecoVO> enderecos) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.enderecos = enderecos;
    }

    public String getId() {
        return id;
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

    public List<EnderecoVO> getEnderecos() {
        return enderecos;
    }

}
