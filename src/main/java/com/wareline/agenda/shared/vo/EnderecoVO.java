package com.wareline.agenda.shared.vo;

public class EnderecoVO extends ValueObject {

    private final String logradouro;
    private final String numero;
    private final String complemento;
    private final String bairro;
    private final String cidade;
    private final String estado;
    private final String cep;

    public EnderecoVO(final String logradouro, final String numero, final String complemento, final String bairro,
            final String cidade, final String estado, final String cep) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento == null ? "" : complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public String getCep() {
        return cep;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        EnderecoVO other = (EnderecoVO) obj;
        return logradouro.equals(other.logradouro) && numero.equals(other.numero)
                && complemento.equals(other.complemento)
                && bairro.equals(other.bairro) && cidade.equals(other.cidade) && estado.equals(other.estado)
                && cep.equals(other.cep);
    }

    @Override
    public int hashCode() {
        return logradouro.hashCode() + numero.hashCode() + complemento.hashCode() + bairro.hashCode()
                + cidade.hashCode() + estado.hashCode() + cep.hashCode();
    }

    @Override
    public String toString() {
        return "EnderecoVO [logradouro=" + logradouro + ", numero=" + numero + ", complemento=" + complemento
                + ", bairro=" + bairro + ", cidade=" + cidade + ", estado=" + estado + ", cep=" + cep + "]";
    }
}
