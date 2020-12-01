
package logica;

import java.util.ArrayList;

public class ControlStock {

    private int proxIdProd = 1;
    
    private ArrayList<Producto> productos = new ArrayList();
    private ArrayList<Proveedor> proveedores = new ArrayList();
    
    public ControlStock() {
    }
  
    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public ArrayList<Proveedor> getProveedores() {
        return proveedores;
    }

    
    public Producto getProductoMenorPrecio(){
        Producto menor = productos.get(0);
        Producto p;
        for(int x=1;x<productos.size();x++){
            p = productos.get(x);
            if (p.getPrecio()<menor.getPrecio()){
                menor = p;
            }
        }            
        
        return menor;
    }
      
    public void agregar(Proveedor unProveedor){
        proveedores.add(unProveedor);
    }
    
    public  boolean agregar (Producto unProducto){
        if(!unProducto.validar()) return false;
        unProducto.setCodigo(proxIdProd);
        proxIdProd++;
        productos.add(unProducto);
        unProducto.getProveedor().agregar(unProducto);
        return true;
    }
    
    public Producto buscarProductoPorCodigo(int codigoProducto) {
        for (Producto p: productos) {
            if (p.getCodigo() == codigoProducto) {
                return p;
            }
        }
        return null;
    }
    
        public Proveedor buscarProveedor(String nombreProv) {
        for (Proveedor prov: proveedores) {
            if (prov.getNombre().equals(nombreProv)) {
                return prov;
            }
        }
        return null;
    }
    
        public Producto buscarProductoDeProveedor(Proveedor p){
            
            //buscarProductoDeProveedor(p);
        
        boolean existe = false;
        int pos = 0;
        ArrayList<Producto> listaProductos = this.getProductos();
        while (pos < listaProductos.size() && !existe) {
            Producto pro = listaProductos.get(pos);
            if (pro.getProveedor().equals(p)) {
                return pro;
            }
            pos++;
        }
        return null;
      
    }
    
}
