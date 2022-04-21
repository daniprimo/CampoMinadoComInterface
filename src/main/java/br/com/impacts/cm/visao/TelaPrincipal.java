package br.com.impacts.cm.visao;

import br.com.impacts.cm.Tabuleiro;

import javax.swing.*;
import javax.swing.plaf.TabbedPaneUI;

@SuppressWarnings("serial")
public class TelaPrincipal extends JFrame {
    public TelaPrincipal (){
        Tabuleiro tabuleiro = new Tabuleiro(16,30,50);
        add(new PainelTabuleiro(tabuleiro));

        setTitle("Campo Minado");
        setSize(690, 438);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);


    }
    public static void main(String[] args) {
        new TelaPrincipal();

    }

}
