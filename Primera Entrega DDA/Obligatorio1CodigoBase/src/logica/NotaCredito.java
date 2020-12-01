package logica;

import java.util.Date;

public class NotaCredito {

    private Date fecha;
    private int numero;

    public NotaCredito() {
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void asignarFecha() {
        this.setFecha(new Date());
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

}
