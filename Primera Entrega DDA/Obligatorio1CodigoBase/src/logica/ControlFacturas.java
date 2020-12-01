package logica;

import java.util.ArrayList;
import java.util.Date;

public class ControlFacturas {

    private ArrayList<Factura> facturas = new ArrayList();
    private ArrayList<NotaCredito> notasCredito = new ArrayList();
    private int proximoNumero;
    private int proximoNumeroNotas;

    public ControlFacturas() {

    }

    public ArrayList<Factura> getFacturas() {
        return facturas;
    }
    
    public ArrayList<NotaCredito> getNotasCredito() {
        return notasCredito;
    }


    public void agregar(Factura unaFactura) {
        unaFactura.setNumero(generarProximoNumero());
        unaFactura.asignarFecha();
        unaFactura.bajarStock();
        facturas.add(unaFactura);
    }

    public boolean clienteComproProducto(Cliente c, Producto p) {
        boolean ret = false;
        for (Factura f : facturas) {
            if (f.getCliente().equals(c) && f.tieneProducto(p)) {
                ret = true;
            }
        }

        return ret;
    }

    public Date clienteFechaUltimaFacturaPorProducto(Cliente c, Producto p) {
        Date ret = null;

        for (Factura f : facturas) {
            if (f.getCliente().equals(c) && f.tieneProducto(p)) {
                ret = f.getFecha();
            }
        }
        return ret;
    }

    private int generarProximoNumero() {
        proximoNumero++;
        return proximoNumero;
    }


    
    
    //Metodos agregados para el obligatorio
    
    public Factura buscarFactura(int numero) {
        boolean existe = false;
        int pos = 0;
        ArrayList<Factura> listaFacturas = this.getFacturas();
        while (pos < listaFacturas.size() && !existe) {
            Factura f = listaFacturas.get(pos);
            if (f.getNumero() == numero) {
                return f;
            }
            pos++;
        }
        return null;
    }
    
        public boolean existeFactura(int numero) {
        boolean existe = false;
        int pos = 0;
        ArrayList<Factura> listaFacturas = this.getFacturas();
        while (pos < listaFacturas.size() && !existe) {
            Factura f = listaFacturas.get(pos);
            if (f.getNumero() == numero) {
                existe = true;
            }
            pos++;
        }
        return existe;
    }

    
    
    public boolean existeNotaEnFactura(Factura factura){
        boolean existe = false;
        
            Factura f = buscarFactura(factura.getNumero());
            if (f.tieneNotaCredito(factura.getNotaCredito())) {
                existe = true;
            }
        return existe;
        
    }
    
    

    public boolean agregarNotaCredito(Factura f, NotaCredito nc) {
        boolean ok = false;

        if (this.existeFactura(f.getNumero())) {
            if(!this.existeNotaEnFactura(f)){
            nc.setNumero(generarProximoNumeroNotas());
            nc.asignarFecha();
            Factura actualizarFactura = this.buscarFactura(f.getNumero());
            actualizarFactura.setNotaCredito(nc);
            //f.agregarNotaAFactura(nc);
            f.devolverStock();

            notasCredito.add(nc);

            ok = true;
            }
        }

        return ok;
    }
    
    
    
   public ArrayList notasFacturasClientesXProveedor(Cliente cli, Proveedor pro) {
       
        Producto productoDelProveedor = Fachada.getInstancia().buscarProductoDeProveedor(pro);
        
        ArrayList<Factura> retorno = new ArrayList<Factura>();
        
        for(Factura f: facturas){
            if (this.existeNotaEnFactura(f) && f.getCliente().equals(cli) && f.tieneProducto(productoDelProveedor)){
                retorno.add(f);
            }
        }
        return retorno;
    }
            
    
    
    private int generarProximoNumeroNotas() {
        proximoNumeroNotas++;
        return proximoNumeroNotas;
    }

}
