package logica;

import java.util.ArrayList;
import java.util.Date;

public class Fachada {

    private static Fachada instancia;

    private ControlClientes cc = new ControlClientes();
    private ControlFacturas cf = new ControlFacturas();
    private ControlStock cs = new ControlStock();
    private ControlMedioPagos cmp = new ControlMedioPagos();

    public static Fachada getInstancia() {

        if (instancia == null) {
            instancia = new Fachada();
        }
        return instancia;
    }

    public ArrayList<Cliente> getClientes() {
        return cc.getClientes();
    }

    public ArrayList clientesNoCompraronProductoMenorPrecio() {
        return cc.clientesNoCompraronProductoMenorPrecio();
    }

    public ArrayList<Cliente> clientesCompraronProductoMenorPrecio() {
        return cc.clientesCompraronProductoMenorPrecio();
    }

    public boolean existeCliente(String unaCedula) {
        return cc.existeCliente(unaCedula);
    }

    public Cliente buscarClientePorCedula(String unaCedula) {
        return cc.buscarClientePorCedula(unaCedula);
    }

    public boolean agregarCliente(Cliente c) {
        return cc.agregar(c);
    }

    public ArrayList<Factura> getFacturas() {
        return cf.getFacturas();
    }

    public void agregarFactura(Factura unaFactura) {
        cf.agregar(unaFactura);
    }

    public boolean clienteComproProducto(Cliente c, Producto p) {
        return cf.clienteComproProducto(c, p);
    }

    public Date clienteFechaUltimaFacturaPorProducto(Cliente c, Producto p) {
        return cf.clienteFechaUltimaFacturaPorProducto(c, p);
    }

    public ArrayList<Producto> getProductos() {
        return cs.getProductos();
    }

    public ArrayList<Proveedor> getProveedores() {
        return cs.getProveedores();
    }

    public Producto getProductoMenorPrecio() {
        return cs.getProductoMenorPrecio();
    }

    public void agregarProveedor(Proveedor unProveedor) {
        cs.agregar(unProveedor);
    }

    public boolean agregarProducto(Producto unProducto) {
        return cs.agregar(unProducto);
    }

    public Producto buscarProductoPorCodigo(int codigoProducto) {
        return cs.buscarProductoPorCodigo(codigoProducto);
    }
    
    
    

    //Metodos agregados para el obligatorio
    
    public ArrayList<MedioPago> getMediosPagos() {
        return cmp.getMediosPago();
    }

    public MedioPago buscarMedioPago(String medioPago) {
        return cmp.buscarMedioPago(medioPago);
    }

    public boolean agregarMedioPago(MedioPago mp) {
        return cmp.agregarMedioPago(mp);
    }

    public Factura buscarFactura(int numero) {
        return cf.buscarFactura(numero);
    }

    public boolean agregarNotaCredito(Factura f, NotaCredito nc) {
        return cf.agregarNotaCredito(f, nc);
    }

    public boolean existeNotaEnFactura(Factura f) {
        return cf.existeNotaEnFactura(f);
    }
    
    public Producto buscarProductoDeProveedor(Proveedor p){
        return cs.buscarProductoDeProveedor(p);
    }
    
    public ArrayList<Factura> notasFacturasClientesXProveedor(Cliente cli, Proveedor pro){
        return cf.notasFacturasClientesXProveedor(cli, pro);
    }
    
    public Proveedor buscarProveedor(String nombre){
        return cs.buscarProveedor(nombre);
    }

    
    
}
