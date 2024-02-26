//-------@autors-----------//
//Guilherme Henrique Pereira//
//Léo Gustavo Del Ré//

import java.io.Serializable;

class Pet implements Serializable {
    private String codigo;
    private String nomeDono;
    private String nomeAnimal;
    private String tipoPet;

    public Pet(String codigo, String nomeDono, String nomeAnimal, String tipoPet) {
        this.codigo = codigo;
        this.nomeDono = nomeDono;
        this.nomeAnimal = nomeAnimal;
        this.tipoPet = tipoPet;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNomeDono() {
        return nomeDono;
    }

    public String getNomeAnimal() {
        return nomeAnimal;
    }

    public String getTipoPet() {
        return tipoPet;
    }

    @Override
    public String toString() {
        return "Cod.: " + codigo + " | Nome dono: " + nomeDono + " | Nome pet.: " + nomeAnimal + " | Tipo: " + tipoPet;
    }
}