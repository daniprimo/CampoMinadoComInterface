package br.com.impacts.cm.visao;

import br.com.impacts.cm.Tabuleiro;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class PainelTabuleiro extends JPanel {

    public  PainelTabuleiro (Tabuleiro tabuleiro){
        setLayout(new GridLayout(tabuleiro.getLinhas(),tabuleiro.getColunas()));//como organizar os componentes

        tabuleiro.paraCadaCampo(c -> add(new BotaoCampo(c)));
        tabuleiro.registrarObservador(e -> {
            //TODO mostrar resultado para o usuario

            tabuleiro.reiniciar();
        });

    }

}
