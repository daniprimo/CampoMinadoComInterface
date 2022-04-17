package br.com.impacto.cm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TesteCampo {

    private Campo campo = new Campo(3,3);

    @Test
    void testeVizinhoReal () {
        Campo vizinho = new Campo(3,2);

      boolean resultado = campo.adicionarVizinho(vizinho);

      Assertions.assertTrue(resultado);
    }
}
