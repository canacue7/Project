package tech.getarrays.banco.Model;

public class Respuesta<T> {

    private T dato;
    private T datoDesti;
    private boolean done;
    private String messa;

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getMessa() {
        return messa;
    }

    public void setMessa(String messa) {
        this.messa = messa;
    }

    public T getDatoDesti() {
        return datoDesti;
    }

    public void setDatoDesti(T datoDesti) {
        this.datoDesti = datoDesti;
    }
}
