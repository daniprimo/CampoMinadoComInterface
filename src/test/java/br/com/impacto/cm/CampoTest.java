package br.com.impacto.cm;

import br.com.impacto.cm.excessao.ExplosaoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import static org.junit.jupiter.api.Assertions.*;

class CampoTest {

        private Campo campo;

        @BeforeEach
        void iniciarCampo () {
            campo = new Campo(3,3);
        }

        @Test
        void testeVizinhoRealDistancia1Esquerda () {
            Campo vizinho = new Campo(3,2);
            boolean resultado = campo.adicionarVizinho(vizinho);
            assertTrue(resultado);
        }

         @Test
        void testeVizinhoRealDistancia1Direita () {
            Campo vizinho = new Campo(3,4);
            boolean resultado = campo.adicionarVizinho(vizinho);
            assertTrue(resultado);
        }


         @Test
        void testeVizinhoRealDistancia1EmCima () {
            Campo vizinho = new Campo(2,3);
            boolean resultado = campo.adicionarVizinho(vizinho);
            assertTrue(resultado);
        }


         @Test
        void testeVizinhoRealDistancia1EmBaixo () {
            Campo vizinho = new Campo(4,3);
            boolean resultado = campo.adicionarVizinho(vizinho);
            assertTrue(resultado);
        }

         @Test
        void testeVizinhoRealDistancia2 () {
            Campo vizinho = new Campo(2,2);
            boolean resultado = campo.adicionarVizinho(vizinho);
            assertTrue(resultado);
        }
         @Test
        void testeNaoVizinho () {
            Campo vizinho = new Campo(1,1);
            boolean resultado = campo.adicionarVizinho(vizinho);
            assertFalse(resultado);
        }
        @Test
        void testeValorPadraoAtribuoMarcado (){
            assertFalse(campo.isMarcado());
        }

        @Test
        void testeAlternarMarcacao () {
            campo.alternarMarcacao();
            assertTrue(campo.isMarcado());
        }
        @Test
        void testeAlternarMarcacaoDuasChamadas () {
            campo.alternarMarcacao();
            campo.alternarMarcacao();
            assertFalse(campo.isMarcado());
        }
        @Test
        void testeAbriNaoMinadoNaoMarcado () {
            assertTrue(campo.abrir());
        }
        @Test
        void testeAbriNaoMinadoNaoMarcado1 () {
            campo.alternarMarcacao();
            assertFalse(campo.abrir());
        }
        @Test
        void testeAbriMinadoMarcado () {
            campo.alternarMarcacao();
            campo.minar();
            assertFalse(campo.abrir());
        }

        @Test
        void testeAbriMinadoNaoMarcado () {
            campo.minar();
            assertThrows(ExplosaoException.class, () -> {
               campo.abrir();
            });
        }
    @Test
    void testeAbrirComVizinhos () {
        Campo vizinhoDoVizinho1 = new Campo(1,1);
        Campo vizinho1 = new Campo(2,2);
        vizinho1.adicionarVizinho(vizinhoDoVizinho1);
        campo.adicionarVizinho(vizinho1);
        campo.abrir();

        assertTrue(vizinho1.isAberto() && vizinhoDoVizinho1.isAberto());
    }
    @Test
    void testeAbrirComVizinhos2 () {
        Campo campo11 = new Campo(1,1);
        Campo campo12 = new Campo(1,1);
        campo12.minar();
        Campo campo22 = new Campo(2,2);
        campo22.adicionarVizinho(campo11);

        campo.adicionarVizinho(campo22);
        campo.abrir();

        assertTrue(campo22.isAberto() && campo11.isFechado());
    }



}