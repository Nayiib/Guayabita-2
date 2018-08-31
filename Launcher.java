package Cliente;

public class Launcher {

    public static int lanzarDado() {
        return (int) (Math.random() * 6 + 1);
    }

    public static int getResultadoApuesta(int valorMaximo) {
        if (lanzarDado() < lanzarDado()) {
            return (int) (Math.random() * (valorMaximo - 1)) + 1;
        } else {
            return -((int) (Math.random() * (valorMaximo - 1)) + 1);
        }
    }

    public static void procesarApuestaJugador1(int valorDado, int turno, int dineroJugador1, int dineroJugador2, int apuestaMinima, int dineroMesa, int variacion) {
        jugar(valorDado, turno, dineroJugador1 + variacion, dineroJugador2, apuestaMinima, dineroMesa - variacion);
    }

    public static void procesarApuestaJugador2(int valorDado, int turno, int dineroJugador1, int dineroJugador2, int apuestaMinima, int dineroMesa, int variacion) {
        jugar(valorDado, turno, dineroJugador1, dineroJugador2 + variacion, apuestaMinima, dineroMesa - variacion);
    }

    public static void jugar(int valorDado, int turno, int dineroJugador1, int dineroJugador2, int apuestaMinima, int dineroMesa) {
        System.out.println("Dinero Mesa: " + dineroMesa + "\n");
        System.out.println("Dinero Jugador 1: " + dineroJugador1 + "\n");
        System.out.println("Dinero Jugador 2: " + dineroJugador2 + "\n");
        if (dineroJugador1 > 0 && dineroJugador2 > 0 && dineroMesa > 0) {
            if (turno % 2 == 0) {
                System.out.println("Turno jugador 1; Valor del Dado: " + valorDado + "\n");
                if (valorDado == 1 || valorDado == 6) {
                    if (dineroJugador1 > apuestaMinima) {
                        jugar(lanzarDado(), 3, dineroJugador1 - apuestaMinima, dineroJugador2, apuestaMinima, dineroMesa + apuestaMinima);
                    } else {
                        jugar(lanzarDado(), 3, 0, dineroJugador2, apuestaMinima, dineroMesa + dineroJugador1);
                    }
                } else {
                    if (dineroJugador1 > dineroMesa) {
                        procesarApuestaJugador1(lanzarDado(), 3, dineroJugador1, dineroJugador2, apuestaMinima, dineroMesa, getResultadoApuesta(dineroMesa));
                    } else {
                        procesarApuestaJugador1(lanzarDado(), 3, dineroJugador1, dineroJugador2, apuestaMinima, dineroMesa, getResultadoApuesta(dineroJugador1));
                    }
                }
            } else {
                System.out.println("Turno jugador 2; Valor del Dado: " + valorDado + "\n");
                if (valorDado == 1 || valorDado == 6) {
                    if (dineroJugador2 > apuestaMinima) {
                        jugar(lanzarDado(), 2, dineroJugador1, dineroJugador2 - apuestaMinima, apuestaMinima, dineroMesa + apuestaMinima);
                    } else {
                        jugar(lanzarDado(), 2, dineroJugador1, 0, apuestaMinima, dineroMesa + dineroJugador2);
                    }
                } else {
                    if (dineroJugador2 > dineroMesa) {
                        procesarApuestaJugador2(lanzarDado(), 2, dineroJugador1, dineroJugador2, apuestaMinima, dineroMesa, getResultadoApuesta(dineroMesa));
                    } else {
                        procesarApuestaJugador2(lanzarDado(), 2, dineroJugador1, dineroJugador2, apuestaMinima, dineroMesa, getResultadoApuesta(dineroJugador2));
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        jugar(lanzarDado(), 2, 500, 300, 20, 20 * 2);
    }
}
