package com.example.tp2;

public class Coordenada {
    private float x;
    private float y;

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }
    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }

    public void establecerY(float radio, float centroX, float centroY, boolean raizPositiva) {
        calcularY(radio, centroX, centroY, raizPositiva);
    }

    private void calcularY(float radio, float centroX, float centroY, boolean raizPositiva) {
        float radioCuadrado = radio * radio;
        float xMenosACuadrado = (float) Math.pow(this.x - centroX, 2);
        float resta = radioCuadrado - xMenosACuadrado;

        float raiz = (float) Math.sqrt(resta);
        if (!raizPositiva)
            raiz = -1 * raiz;
        this.y = raiz + centroY;


    }
    public void establecerX(float radio, float centroX, float centroY, boolean raizPositiva) {
        calcularX(radio, centroX, centroY, raizPositiva);
    }

    private void calcularX(float radio, float centroX, float centroY, boolean raizPositiva) {
        float radioCuadrado = radio * radio;
        float yMenosBCuadrado = (float) Math.pow(this.y - centroY, 2);
        float resta = radioCuadrado - yMenosBCuadrado;

        float raiz = (float) Math.sqrt(resta);
        if (!raizPositiva)
            raiz = -1 * raiz;
        this.x = raiz + centroX;


    }
}
