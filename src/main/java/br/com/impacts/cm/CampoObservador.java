package br.com.impacts.cm;


@FunctionalInterface
public interface CampoObservador
{
    public void eventoOcorreu (Campo campo, CampoEvento evento);
}
