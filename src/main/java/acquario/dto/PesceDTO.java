package acquario.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "tipoPesce",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = PescePalla.class, name = "pesce palla"),
        @JsonSubTypes.Type(value = PesceRosso.class, name = "pesce rosso")
})
public abstract class PesceDTO {
    private String tipoPesce;
    private String nome;
    private String colore;
    private String grandezza;



    public PesceDTO() {}

    public PesceDTO( String tipoPesce, String nome, String colore, String grandezza) {
        this.tipoPesce = tipoPesce;
        this.nome = nome;
        this.colore = colore;
        this.grandezza = grandezza;

    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getColore() {
        return colore;
    }

    public void setColore(String colore) {
        this.colore = colore;
    }

    public String getGrandezza() {
        return grandezza;
    }

    public void setGrandezza(String grandezza) {
        this.grandezza = grandezza;
    }

    public String getTipoPesce() {
        return tipoPesce;
    }

    public void setTipoPesce(String tipoPesce) {
        this.tipoPesce = tipoPesce;
    }

    @Override
    public String toString() {
        return "PesceDTO{" +
                "tipoPesce='" + tipoPesce + '\'' +
                ", nome='" + nome + '\'' +
                ", colore='" + colore + '\'' +
                ", grandezza='" + grandezza + '\'' +
                '}';
    }
}
