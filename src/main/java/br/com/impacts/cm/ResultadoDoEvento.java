package br.com.impacts.cm;

public class ResultadoDoEvento {
    private final boolean ganhou;

    public ResultadoDoEvento(boolean ganhou) {
        this.ganhou = ganhou;
    }

    public boolean isGanhou (){
        return  ganhou;
    }
}
