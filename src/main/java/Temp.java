/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author juena
 */
public class Temp {
    private double day;
    private double min;
    private double max;
    private double night;
    private double eve;
    private double morn;

    public Temp(double day, double min, double max, double night, double eve, double morn) {
        this.day = day;
        this.min = min;
        this.max = max;
        this.night = night;
        this.eve = eve;
        this.morn = morn;
    }
    
    @Override
    public String toString() {
        return String.format("day: %f, min: %f, max: %f, night: %f, eve: %f, morn: %f", day, min, max, night, eve, morn);
    }
    
    
}
