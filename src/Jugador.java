public class Jugador {

    private static int nextId = 1;

    private int id;
    private String nombre;
    private String apellido;
    private int anioNacimiento;
    private String club;

    public Jugador(String nombre, String apellido, int anioNacimiento, String club) {
        this.id = Jugador.nextId;
        Jugador.nextId++;
        this.nombre = nombre;
        this.apellido = apellido;
        this.anioNacimiento = anioNacimiento;
        this.club = club;
    }

    public int getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAnioNacimiento() {
        return anioNacimiento;
    }

    public void setAnioNacimiento(int anioNacimiento) {
        if (anioNacimiento <= 1980 || anioNacimiento >= 2010) {
            System.out.println("El año de nacimiento debe ser mayor a 1980 y menor a 2010, no se modifico nada");
            return;
        }
        this.anioNacimiento = anioNacimiento;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
       this.apellido = apellido;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public boolean coincideId(int id) {
        return this.id == id;
    }

}
