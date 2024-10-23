package acquario.dto;

public class PescePalla extends PesceDTO {

    public PescePalla(String nome, String colore, String grandezza, String tipoPesce) {
        super(tipoPesce, nome, colore, grandezza);
    }

    public PescePalla() {
        super();
    }
}
