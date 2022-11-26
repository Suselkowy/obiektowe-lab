package agh.ics.oop;

public interface IMapElement {

    Vector2d getPosition();

    boolean isAt(Vector2d position);

    String getResourcePath();
}

//Można by było rozważyc dodanie klasy AbstractWorldMapElement ponieważ dwie klasy elementów maja funkcje ktore definiowane sa w taki sam sposób.
// Użycie interfejsu również ma sens, ponieważ główny nacisk kładziemy na polimorfizm, a nie duplikacje kodu.