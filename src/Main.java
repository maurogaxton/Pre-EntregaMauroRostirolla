//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final ArrayList<Jugador>jugadoresDB = obtenerJugadores();
    public static Scanner entrada = new Scanner(System.in);

   public static void main(String[] args) {

        System.out.println("Te damos la bienvenida a la app de Clubes 🛒");
        label:
        while (true) {
            System.out.println("""
                    Ingrese el número equivalente a la opción:
                    0 - Finaliza el programa
                    1 - Agregar un Jugador
                    2 - Listar Jugadores
                    3 - Búsqueda por nombre
                    4 - Editar nombre Jugador
                    5 - Retirar Jugador
                    6 - Filtrar por Club
                    """);
            int opcionUsuario = entrada.nextInt();

            switch (opcionUsuario) {
                case 1 -> agregarJugador(jugadoresDB);
                case 2 -> listarJugadores(jugadoresDB);
                case 3 -> buscarJugadorPorNombre(jugadoresDB);
                case 4 -> editarJugador(jugadoresDB);
                case 5 -> retirarJugador(jugadoresDB);
                case 6 -> filtroPorClub(jugadoresDB);
                case 0 -> {
                    System.out.println("Gracias por usar la app!");
                    break label; // corta el bucle donde se ejecuta
                }
                default -> System.out.println("Opción incorrecta, intente de nuevo");
            }
        }
    }

    public static void agregarJugador(ArrayList<Jugador> jugadores) {

        System.out.println("Agregando Nuevo Jugador");

        System.out.println("Ingrese el nombre del nuevo jugador: ");
        String nombre = entrada.next();

        System.out.println("Ingrese el apellido del nuevo jugador: ");
        String apellido = entrada.next();

        System.out.println("Ingrese el año de nacimiento del nuevo jugador: ");
        int anioNacimiento = entrada.nextInt();

        System.out.println("Ingrese el nombre del club: ");
        String club = entrada.next();

        // TODO: cambiarlo cuando veamos static
        jugadores.add(new Jugador(nombre, apellido, anioNacimiento, club));
        System.out.println("Jugador agregado");
        // TODO: agregar un mensaje de confirmación cuando se agrega el jugador
        pausa();
    }

    public static void listarJugadores(ArrayList<Jugador> jugadores) {
        System.out.println("=========================================================================================");
        System.out.println("                                  LISTA DE JUGADORES                             ");
        System.out.println("=========================================================================================");

        if (jugadores == null || jugadores.isEmpty()) {
            System.out.println("⚠️  No hay jugadores para mostrar.");
        } else {
            System.out.printf("| %-3s | %-20s | %-20s | %-20s | %-15s |%n",
                    "ID", "Nombre", "Apellido", "Año nacimiento", "Club");
            System.out.println("-------------------------------------------------------------------------------------");

            for (Jugador jugador : jugadores) {
                System.out.printf("| %3d | %-20s | %-20" +
                                "s | %-20d | %-15s |%n",
                        jugador.getId(),
                        jugador.getNombre(),
                        jugador.getApellido(),
                        jugador.getAnioNacimiento(),
                        jugador.getClub());
            }
        }

        System.out.println("=========================================================================================");
        pausa();
    }

   public static void buscarJugadorPorNombre(ArrayList<Jugador> jugadores) {
        System.out.println("Ingrese un nombre de un jugador: ");
        String busqueda = entrada.next();
        ArrayList<Jugador> jugadorEncontrado = new ArrayList<>();

        for (Jugador jugador : jugadores) {
            if (estaIncluido(jugador.getNombre(), busqueda)) {
                jugadorEncontrado.add(jugador);
            }
        }

        listarJugadores(jugadorEncontrado);
    }

    public static void editarJugador(List<Jugador> jugadores) {
        // el listado de jugadores tiene las direcciones de memoria de los productos originales
        // aca obtenemos la dirección de memoria que nos permite modificar el objeto original
        // que es uno de los que está en el listado
        Jugador jugador = obtenerJugadorPorId(jugadores);
        // TODO: validar que encontramos el jugador
        if (jugador == null) {
            System.out.println("No se puede editar el jugador.");
            pausa();
            return; // cuando hacemos el return en una función void, estamos cortando la ejecución de la función
        }

        String nombreOriginal = jugador.getNombre();
        System.out.println("Jugador a editar:");
        System.out.println(nombreOriginal);
        // TODO: validar que el usuario quiere editar el jugador que se encontró
        System.out.print("Ingrese el nuevo nombre: ");
        String nuevoNombre = entrada.next();
        // actualizamos el nombre en el jugador
        jugador.setNombre(nuevoNombre);

        System.out.printf("El nombre del jugador cambio de %s a %s", nombreOriginal, nuevoNombre);
        System.out.println();
        pausa();
    }

    public static void retirarJugador(List<Jugador> jugadores) {
        Jugador jugador = obtenerJugadorPorId(jugadores);
        // TODO: validar que encontramos el producto
        if (jugador == null) {
            System.out.println("No pudimos retirar el jugador");
            pausa();
            return; //
        }
        String nombreOriginal = jugador.getNombre();
        System.out.println("Jugador a retirar:");
        System.out.println(nombreOriginal);
        // TODO: validar que el usuario quiere retirar el jugador que se encontró

        // aca retiramos al jugador
        jugadores.remove(jugador);
        System.out.println("Retirado exitosamente!");
        pausa();

   }

    public static void filtroPorClub(List<Jugador> jugadores) {
       System.out.println("Ingrese el nombre del club: ");
       String clubFiltro = entrada.next();

       ArrayList<Jugador> jugadoresFiltrados = new ArrayList<>();

        for (Jugador jugador : jugadores) {
            if (jugador.getClub().equals(clubFiltro)) {
                jugadoresFiltrados.add(jugador);
            }
        }

        listarJugadores(jugadoresFiltrados);
    }

    /* UTILIDADES */
    /* Busqueda por Id - ahora mismo solo funciona con el índice, en el futuro se va a cambiar */
    public static Jugador obtenerJugadorPorId(List<Jugador> jugadores) {
        // TODO: validacion de datos
        System.out.println("Ingrese el id del jugador: ");
        int idBusqueda = entrada.nextInt();

        for (Jugador jugador : jugadores) {
            if (jugador.coincideId(idBusqueda)) {
                return jugador;
            }
        }

        System.out.println("No pudimos encontrar el jugador con el id: " + idBusqueda);
        return null; // el null representa que no encontramos el producto
    }

    public static boolean estaIncluido(String nombreCompleto, String nombreParcial) {
        String nombreCompletoFormateado = formatoBusqueda(nombreCompleto);

        return nombreCompletoFormateado.contains(formatoBusqueda(nombreParcial));
    }

    public static String formatoBusqueda(String texto) {
        return texto.trim().toLowerCase();
    }

    public static void pausa() {
        System.out.println("Pulse ENTER para continuar...");
        entrada = new Scanner(System.in);
        entrada.nextLine();
        for (int i = 0; i < 20; ++i) {
            System.out.println();
        }
        // TODO: limpiar la pantalla de la consola
    }

    public static ArrayList<Jugador> obtenerJugadores() {
        ArrayList<Jugador> jugadores = new ArrayList<>();

        jugadores.add(new Jugador( "Oscar", "Córdoba", 1999, "Boca"));
        jugadores.add(new Jugador( "Jorge", "Bermúdez", 1998, "Boca"));
        jugadores.add(new Jugador( "Walter", "Samuel", 1997, "Boca"));
        jugadores.add(new Jugador( "Hugo", "Ibarra", 2000, "Boca"));
        jugadores.add(new Jugador( "Sebastián", "Battaglia", 2000, "Boca"));
        jugadores.add(new Jugador( "Juan Román", "Riquelme", 2001, "Boca"));
        jugadores.add(new Jugador( "Guillermo", "Barros Schelotto", 2001, "Boca"));
        jugadores.add(new Jugador( "Martín", "Palermo", 2002, "Boca"));

        return jugadores;
    }

}
