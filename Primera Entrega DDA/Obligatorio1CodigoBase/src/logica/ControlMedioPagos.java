package logica;

import java.util.ArrayList;


public class ControlMedioPagos {

    private ArrayList<MedioPago> mediosPago = new ArrayList();

    public ControlMedioPagos() {

    }

    public ArrayList<MedioPago> getMediosPago() {
        return mediosPago;
    }

    //busco en mi lista de medios si exite el medio que quiero agregar
    
    public boolean existeMedioPago(String medioPago) {
        boolean existe = false;
        int pos = 0;
        ArrayList<MedioPago> listaMedios = this.getMediosPago();
        while (pos < listaMedios.size() && !existe) {
            MedioPago mp = listaMedios.get(pos);
            if (mp.getNombre().equals(medioPago)) {
                existe = true;
            }
            pos++;
        }
        return existe;
    }

    //agrego el medio de pago 
    
    public boolean agregarMedioPago(MedioPago mp) {
        boolean ret = false;
        if (!existeMedioPago(mp.getNombre())) {
            mediosPago.add(mp);
            ret = true;
        }

        return ret;

    }

    
    //busco un metodo de pago por su nombre para tener sus datos
    
    public MedioPago buscarMedioPago(String medioPago) {
        boolean existe = false;
        int pos = 0;
        ArrayList<MedioPago> listaMedios = this.getMediosPago();

        while (pos < listaMedios.size() && !existe) {
            MedioPago mp = listaMedios.get(pos);
            if (mp.getNombre().equals(medioPago)) {
                return mp;
            }
            pos++;
        }
        return null;
    }

}
