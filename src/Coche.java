import java.io.Serializable;

public class Coche implements Serializable{
    private static final long serialVersionUID = -

1688064832140731464L;

    private String matricula;

    private String marca;

    private String modelo;

    private String color;

    public Coche (String matricula, String marca, String modelo, String color) {

                  this.matricula = matricula;

                  this.marca = marca;

                  this.modelo = modelo;

                  this.color = color;

     }

     public String getMatricula() {

                   return matricula;

     }

     public String getMarca() {

                   return marca;

     }

     public String getModelo() {

                   return modelo;

     }

     public String getColor() {

                   return color;

     }

     @Override

     public String toString() {

                    return matricula + " " + marca + " " + modelo + " " + color;

 }
}
