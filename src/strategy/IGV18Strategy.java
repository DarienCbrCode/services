package strategy;

public class IGV18Strategy implements ImpuestoStrategy {

    @Override
    public double calcular(double monto) {
        return monto * 0.18;
    }
}
