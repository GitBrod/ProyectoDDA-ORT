package logica;

public class DatosPrueba {

    public static void cargar() {

        Fachada logica = Fachada.getInstancia();

        Proveedor pA = new Proveedor("Proveedor A");
        Proveedor pB = new Proveedor("Proveedor B");
        Proveedor pC = new Proveedor("Proveedor C");

        logica.agregarProveedor(pA);
        logica.agregarProveedor(pB);
        logica.agregarProveedor(pC);

        Producto caramelo = new Producto("Caramelo", 2, 3000, pB);
        Producto camisa = new Producto("Camisa", 1300, 1000, pB);
        Producto computadora = new Producto("Computadora", 20000, 40, pC);

        logica.agregarProducto(caramelo);
        logica.agregarProducto(camisa);
        logica.agregarProducto(computadora);

        Cliente juan = new Cliente("12345678", "Juan");
        Cliente ana = new Cliente("13456789", "Ana");
        Cliente mario = new Cliente("21234567", "Mario");

        logica.agregarCliente(juan);
        logica.agregarCliente(ana);
        logica.agregarCliente(mario);

        //Agrego datos de prueba para mediospago y agrego metodo de pago a las facturas cargadas de prueba
        
        MedioPago efectivo = new MedioPago("Efectivo");
        MedioPago tarjetaCred = new MedioPago("Tarjeta de Credito");
        MedioPago tarjetaDebi = new MedioPago("Tarjeta de Debito");

        logica.agregarMedioPago(efectivo);
        logica.agregarMedioPago(tarjetaCred);
        logica.agregarMedioPago(tarjetaDebi);

        ////////////////////////////////////////
        
        
        Factura f1 = new Factura(juan, efectivo);
        f1.agregar(30, caramelo);
        f1.agregar(2, camisa);
        f1.agregar(1, computadora);

        logica.agregarFactura(f1);

        Factura f2 = new Factura(ana, tarjetaCred);
        f2.agregar(400, caramelo);
        f2.agregar(20, camisa);
        f2.agregar(10, computadora);

        logica.agregarFactura(f2);

        Factura f3 = new Factura(mario, tarjetaDebi);

        f3.agregar(1, camisa);
        f3.agregar(1, computadora);

        logica.agregarFactura(f3);
    }
}
