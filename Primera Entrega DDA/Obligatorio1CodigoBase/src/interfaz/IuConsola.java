package interfaz;

import java.util.ArrayList;
import logica.Cliente;
import logica.Fachada;
import logica.Factura;
import logica.LineaFactura;
import logica.MedioPago;
import logica.Producto;
import logica.Proveedor;
import logica.NotaCredito;
import utilidades.Consola;

public class IuConsola {

    Fachada logica = Fachada.getInstancia();

    /**
     * Ejecuta la consola
     */
    public void mostrarConsola() {
        boolean salir = false;
        do {

            int opcion = imprimirMenu();
            salir = procesarOpcion(opcion);

        } while (!salir);
    }

    /**
     * Imprime el menú y sus opciones a pantalla
     */
    private int imprimirMenu() {
        System.out.println("MENU");
        System.out.println("====");

        ArrayList<String> opciones = new ArrayList();
        opciones.add("Alta de Cliente");
        opciones.add("Alta de Producto");
        opciones.add("Alta de Factura");
        opciones.add("Consulta clientes");
        opciones.add("Alta Nota de Credito");
        opciones.add("Mostrar notas de credito");
        opciones.add("Salir del menú");
        return Consola.menu(opciones);
    }

    /**
     * Captura la opción seleccionada por el usuario y ejecuta la acción
     * correspondiente
     */
    private boolean procesarOpcion(int opcion) {
        boolean salir = false;
        int numero;

        switch (opcion) {
            case 0:
                this.nuevoCliente();
                break;
            case 1:
                nuevoProducto();
                break;

            case 2:
                nuevoFactura();
                break;

            case 3:
                consultarClientes();
                break;

            case 4:
                altaNotaCredito();
                break;

            case 5:
                listarNotasCreditoClienteProveedor();
                break;

            case 6:
                salir = true;
                break;

        }
        return salir;
    }

    private void nuevoCliente() {

        System.out.println("ALTA DE CLIENTE");
        System.out.println("===============");

        Cliente unCliente = new Cliente();
        unCliente.setCedula(Consola.leer("Cedula:"));
        unCliente.setNombre(Consola.leer("Nombre:"));
        if (logica.agregarCliente(unCliente)) {
            mostrarClientes();
        } else {
            System.out.println("EL CLIENTE NO FUE INGRESADO");
        }

    }

    private void mostrarClientes() {
        System.out.println("=================");
        System.out.println("CLIENTES ACTUALES");
        System.out.println("=================");
        ArrayList<Cliente> clientes = logica.getClientes();
        for (Cliente c : clientes) {
            System.out.println(c.getCedula() + " - " + c.getNombre());
        }
    }

    private void nuevoProducto() {

        System.out.println("ALTA DE PRODUCTO");
        System.out.println("===============");

        Producto prod = new Producto();

        boolean ok = false;
        do {
            ok = prod.setNombre(Consola.leer("Nombre:"));
        } while (!ok);

        while (!prod.setUnidades(Consola.leerInt("Unidades:")));

        ArrayList<Proveedor> provs = logica.getProveedores();

        int p = Consola.menu(provs);

        Proveedor select = provs.get(p);

        prod.setProveedor(select);

        while (!prod.setPrecio(Consola.leerInt("Precio:")));

        if (logica.agregarProducto(prod)) {
            System.out.println("PRODUCTO AGREGADO");
        } else {
            System.out.println("ERROR AL AGREGAR EL PRODUCTO");
        }
    }
/////////////////////////////
    //Metodo modificado para el obligatorio
    /////////////////////////////////////////////////

    private void nuevoFactura() {
        System.out.println("ALTA DE FACTURA");
        System.out.println("===============");

        Cliente c = logica.buscarClientePorCedula(Consola.leer("Cedula:"));
        if (c == null) {
            System.out.println("No existe el cliente");
            return;
        }

        System.out.println("Seleccione un metodo de pago");
        ArrayList<String> mediosP = new ArrayList<>();
        mediosP.add("Efectivo");
        mediosP.add("Tarjeta de Debito");
        mediosP.add("Tarjeta de Credito");
        MedioPago mp = null;
        int opcionP = Consola.menu(mediosP);
        switch (opcionP) {
            case 0:
                mp = logica.buscarMedioPago("Efectivo");
                break;
            case 1:
                mp = logica.buscarMedioPago("Tarjeta de Debito");
                break;
            case 2:
                mp = logica.buscarMedioPago("Tarjeta de Credito");
                break;
        }

        Factura nuevaFactura = new Factura(c, mp);
        boolean ok, salir = false;
        while (!salir) {
            ok = nuevaFactura.agregarPorCodigoProducto(
                    Consola.leerInt("Cantidad:"), Consola.leerInt("Cod. Producto:"));

            if (ok) {
                mostrarFactura(nuevaFactura);
            } else {
                System.out.println("Error al agregar el producto.");
            }
            ArrayList<String> opciones = new ArrayList<>();
            opciones.add("Finalizar");
            opciones.add("Continuar");
            opciones.add("Descartar");
            int opcion = Consola.menu(opciones);
            switch (opcion) {
                case 0:
                    logica.agregarFactura(nuevaFactura);
                    mostrarFactura(nuevaFactura);
                    salir = true;
                    break;

                case 1:
                    break;

                case 2:
                    salir = true;
                    break;
            }
        }
    }

    private void mostrarFactura(Factura factura) {
        int x = 1;
        String mensaje = null;
        for (LineaFactura linea : factura.getLineas()) {
            mensaje = x + " - " + linea.getProducto().getCodigo() + " - "
                    + linea.getProducto().getNombre() + " - "
                    + linea.getCantidad() + " - "
                    + " $ " + linea.total();
            System.out.println(mensaje);
            x++;
        }
        System.out.println("***************");
        System.out.println("Total $ : " + factura.total());
        System.out.println("Medio de pago : " + factura.getMedioPago());
    }

    private void consultarClientes() {
        System.out.println("=================");
        System.out.println("CONSULTAR CLIENTES");
        System.out.println("=================");
        if (logica.getProductos().isEmpty()) {
            System.out.println("No existen productos ingresados.");
            return;
        }
        String mensaje;
        Producto menor = logica.getProductoMenorPrecio();
        mensaje = "(" + menor.getCodigo() + ") " + menor.getNombre() + " - "
                + " $ " + menor.getCodigo() + " - " + menor.getUnidades();
        System.out.println(mensaje);
        ArrayList<Cliente> clientes = logica.clientesCompraronProductoMenorPrecio();

        if (clientes.isEmpty()) {
            System.out.println("No existen clientes que compraron el producto de menor precio.");
            return;
        }

        for (Cliente c : clientes) {
            mensaje = c.getCedula() + " - "
                    + c.getNombre() + " - "
                    + logica.clienteFechaUltimaFacturaPorProducto(c, menor);
            System.out.println(mensaje);
        }
    }
//////////////////////////////////////
    //Metodos agregados para el obligatorio
    ////////////////////////////////////////////

    private void altaNotaCredito() {

        System.out.println("ALTA DE NOTA DE CREDITO");
        System.out.println("===============");

        Factura f = logica.buscarFactura(Integer.parseInt(Consola.leer("Ingrese el codigo de la factura: ")));

        if (f == null) {
            System.out.println("No existe una factura con ese numero");
            return;
        } else if (f.getMedioPago().getNombre() != "Tarjeta de Credito") {
            System.out.println("Esta factura no fue pagada con una Tarjeta de Credito");
            return;
        } else {
            mostrarFacturaObligatorio(f);
        }

        NotaCredito nuevaNota = new NotaCredito();

        boolean ok, salir = false;
        while (!salir) {
            ArrayList<String> opciones = new ArrayList<>();
            opciones.add("Generar nota de credito");
            opciones.add("Salir");
            int opcion = Consola.menu(opciones);
            switch (opcion) {
                case 0:
                    
//                    Esta validacion tambien la aplico a nivel experto en el controlador de factura
//                    que tiene todas las facturas y le pregunta a la factura si existe una nota en esa factura
//                    (validacion que tiene la clase factura por ser la experta en saber si tiene o no una factura).
//                    Queria dejarlo comentado porque por mas que cumplo con la facha al pasarle ese metodo
//                    si dejo solo la validacion a este nivel estaria rompiendo el experto y 
//                    creo estar aplicandolo a nivel de controlador y clase.
                    
                    if (!logica.existeNotaEnFactura(f)) {
                        logica.agregarNotaCredito(f, nuevaNota);
                        mostrarAltaCreditoDeFactura(f);
                    } else {
                        System.out.println("Esta factura ya tiene una nota de credito asociada");
                    }

                    salir = true;
                    break;

                case 1:
                    salir = true;
                    break;
            }
        }

    }

    private void mostrarFacturaObligatorio(Factura factura) {

        System.out.println("***************");

        System.out.println("Factura Codigo : " + factura.getNumero());
        System.out.println("Cliente : " + factura.getCliente().getCedula());
        System.out.println("Medio de pago : " + factura.getMedioPago());

        System.out.println("***************");

        String mensaje = null;
        for (LineaFactura linea : factura.getLineas()) {
            mensaje = linea.getProducto().getCodigo() + " - "
                    + linea.getProducto().getNombre() + " - "
                    + linea.getCantidad() + " - "
                    + " $ " + linea.getProducto().getPrecio() + " - "
                    + " $ " + linea.total();
            System.out.println(mensaje);
        }

        System.out.println("Total $ : " + factura.total());
    }

    private void mostrarAltaCreditoDeFactura(Factura factura) {

        System.out.println("Se a agregado una nueva nota de credito");

        String mensaje = null;
        for (LineaFactura linea : factura.getLineas()) {
            mensaje = linea.getProducto().getNombre() + " - "
                    + " Cantidad " + linea.getCantidad() + "/ Stock actualizado " + linea.getProducto().getUnidades();
            System.out.println(mensaje);;
        }
        System.out.println("***************");

    }

    private void listarNotasCreditoClienteProveedor() {

        System.out.println("Mostrar Notas de facturas clientes proveedor");
        System.out.println("===============");

        
        System.out.println("Seleccione un cliente");
        ArrayList<String> clientes = new ArrayList<>();
        clientes.add("12345678");
        clientes.add("13456789");
        clientes.add("21234567");
        Cliente cli = null;
        int opcionC = Consola.menu(clientes);
        switch (opcionC) {
            case 0:
                cli = logica.buscarClientePorCedula("12345678");
                break;
            case 1:
                cli = logica.buscarClientePorCedula("13456789");
                break;
            case 2:
                cli = logica.buscarClientePorCedula("21234567");
                break;
        }

        System.out.println("Seleccione un proveedor");
        ArrayList<String> proveedores = new ArrayList<>();
        proveedores.add("Proveedor A");
        proveedores.add("Proveedor B");
        proveedores.add("Proveedor C");
        Proveedor prov = null;
        int opcionP = Consola.menu(proveedores);
        switch (opcionP) {
            case 0:
                prov = logica.buscarProveedor("Proveedor A");
                break;
            case 1:
                prov = logica.buscarProveedor("Proveedor B");
                break;
            case 2:
                prov = logica.buscarProveedor("Proveedor C");
                break;
        }

        ArrayList<Factura> listaFacturasCliProv = logica.notasFacturasClientesXProveedor(cli, prov);
                
        String mensaje = null;
        for (Factura factura : listaFacturasCliProv) {
            mensaje = factura.getNotaCredito().getNumero() + " - "
                    + factura.getNumero() + " - "
                    + factura.cantidadProductosEnFactura() + " - "
                    + factura.getNotaCredito().getFecha() + " - "
                    + factura.getFecha() + " - "
                    + factura.total();
            System.out.println(mensaje);
        }

    }

}
