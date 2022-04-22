package br.com.impacts.cm;

import java.util.ArrayList;
import java.util.List;

public class Campo {

    private final int LINHA;
    private final int COLUNA;

    private boolean aberto = false;
    private boolean minado = false;
    private boolean marcado = false;

    private List<Campo> vizinhos = new ArrayList<>();

    //com interface
    private List<CampoObservador> observadores =
            new ArrayList<>();
    //sem interface
//    private List<BiConsumer<Campo, CampoEvento>> observadores2 =
//            new ArrayList<>();
    Campo(int linha, int coluna){
        this.LINHA = linha;
        this.COLUNA = coluna;
    }

    public void registrarObservador (CampoObservador observador){
        observadores.add(observador);
    }


    public void notificarObservadores (CampoEvento evento) {
        observadores.stream()
                .forEach(o -> o.eventoOcorreu(this, evento));
    }

    boolean adicionarVizinho(Campo vizinho){
        boolean linhaDiferente = LINHA != vizinho.LINHA;
        boolean colunaDiferente = COLUNA != vizinho.COLUNA;
        boolean diagonal = linhaDiferente && colunaDiferente;

        int deltaLinha = Math.abs(LINHA - vizinho.LINHA);
        int deltaColuna = Math.abs(COLUNA - vizinho.COLUNA);
        int deltaGeral = deltaColuna+deltaLinha;

        if (deltaGeral == 1 && !diagonal){
            vizinhos.add(vizinho);
            return true;
        }else if (deltaGeral == 2 && diagonal){
            vizinhos.add(vizinho);
            return true;
        }else {
            return false;
        }
    }

    public void alternarMarcacao () {
        if (!aberto){
            marcado = !marcado;
            if (marcado){
                notificarObservadores(CampoEvento.MARCAR);
            }else {
                notificarObservadores(CampoEvento.DESMARCAR);
            }
        }
    }

    public boolean abrir (){
        if (!aberto && !marcado){
            if (minado){
                notificarObservadores(CampoEvento.EXPLODIR);
                return true;
            }

            setAberto(true);

            if (vizinhancaSegura()){
                vizinhos.forEach(v -> v.abrir());
            }
            return true;
        }else {
            return false;
        }
    }

    public boolean vizinhancaSegura () {
        //Atraves do predicate verificando verificando se tem blocos minados
        return vizinhos.stream().noneMatch(v -> v.minado);
    }

    void minar(){
            minado = true;
    }

    public boolean isMinado (){
        return minado;
    }

    public boolean isMarcado () {
        return marcado;
    }

    void setAberto (Boolean aberto){
        this.aberto = aberto;
        if (aberto){
            notificarObservadores(CampoEvento.ABRIR);
        }

    }

    public boolean isAberto (){
       return aberto;
    }
    public boolean isFechado (){
       return !isAberto();
    }

    public int getColuna (){
        return COLUNA;
    }
    public int getLinha (){
        return LINHA;
    }

    boolean objetivoAlcancado (){
        boolean desvendado = !minado && aberto;
        boolean protegido = minado && marcado;
        return desvendado || protegido;
    }

   public int minasNaVizinhanca(){
        return (int) vizinhos.stream().filter(v -> v.minado).count();
    }

    void reiniciar () {
        aberto = false;
        minado = false;
        marcado = false;
        notificarObservadores(CampoEvento.REINICIAR);

    }

}
