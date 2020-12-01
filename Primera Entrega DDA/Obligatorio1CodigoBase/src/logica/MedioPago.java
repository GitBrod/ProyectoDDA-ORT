package logica;


public class MedioPago {
    
    private String nombre;

    public MedioPago(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public boolean validar(){
         return true;
    }
    
    @Override
    public String toString() {
        return nombre;
    }
   
}
