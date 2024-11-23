class Entrada implements Runnable {
    private final Parking parking;

    public Entrada(Parking parking) {
        this.parking = parking;
    }

    @Override
    public void run() {
        parking.entradaCoche();
    }
}