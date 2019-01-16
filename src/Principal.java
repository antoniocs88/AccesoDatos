import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;



public class Principal {
	
	 static Scanner lector;
	 
	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException {
        lector = new Scanner(System.in);

        ArrayList<Coche> coches = new ArrayList<Coche>();
        
        /*aqui le decimos que si existe el fichero coches.dat
         * nos haga el metodo leerfichero que basicamente
         * coge los objetos del arrayList que ahi en coches.dat y los mete en
         * el arrayList del programa, en este caso coches.
         */
        File file=new File("coches.dat");

        if(file.exists()) {
        	try {
				coches=leerFichero(coches);
			} catch (IOException e) {
				// TODO Bloque catch generado automáticamente
				e.printStackTrace();
			}
        
        	
        }

         int opc = 0;
         

         //menu con las opciones que podemos elegir y que cada una invoca a un metodo
           while (opc!=6) {

                         mostrarMenu();

                         opc = lector.nextInt();

                         lector.nextLine(); // Para recoger el retorno de carro.

             			switch (opc) {
            			case 1:
            				nuevoCoche(coches);
            				break;
            			case 2:
            				borrarCoche(coches);
            				break;
            			case 3:
            				
            				consultarCoche(coches);
            				break;
            			case 4:
            				listadoCoches(coches);

            				break;
            			case 5:
            				try {
								exportarCoches(coches);
							} catch (FileNotFoundException e) {
								// TODO Bloque catch generado automáticamente
								e.printStackTrace();
							}

            				break;
            			
            			}

        }
           System.out.println("programa terminado");

        /*

          * Abre el fichero coches.dat para escritura y guarda todos los

          * objetos Coche que estén en la colección ArrayList coches.

          * Si ya existe un fichero coches.dat deberá ser sobrescrito.

          */
           //lo hemos hecho a traves del metodo crearFichero
          try {
			crearFichero(coches);
		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
          lector.close();
	}
	
	public static void mostrarMenu() {

        System.out.println(" COCHES MATRICULADOS");

        System.out.println("---------------------------------------");

        System.out.println("1. Añadir nuevo coche");

        System.out.println("2. Borrar coche");

        System.out.println("3. Consultar coche");

        System.out.println("4. Listado de coches");

        System.out.println("5. Exportar coches a archivo de texto");

        System.out.println("6. Terminar programa");

        System.out.println("---------------------------------------");

        System.out.println("¿Qué opción eliges?");

}

@SuppressWarnings("null")
/*
 * metodo en el que introducimos por teclado
 * un nuevo coche y lo guardamos en el arrayList coches
 */
public static void nuevoCoche(ArrayList<Coche> coches) {

    
    String matricula;
    String marca;
    String modelo;
    String color;
    
    Scanner sc = new Scanner(System.in);
    
    Coche cochenuevo;
  
        System.out.print("Matrícula: ");
		matricula = sc.nextLine();          
        System.out.print("Marca: ");
        marca = sc.nextLine();
        System.out.print("Modelo: ");
        modelo = sc.nextLine();
        System.out.print("Color: ");
        color = sc.nextLine();

        sc.nextLine(); //limpiar el intro
        cochenuevo = new Coche(matricula, marca, modelo, color); 

                  
       //se añade el objeto al final del array
        coches.add(cochenuevo);
        System.out.println("el coche se ha añadido con exito");

}

/*
 * metodo en el que borramos un coche a partir de la matricula
 * que pongamos por teclado
 */
public static void borrarCoche(ArrayList<Coche> coches) {
       
		int i = 0;
		System.out.println("Matricula buscada: ");
		String matricula = lector.nextLine();
		while (i < coches.size() && !coches.get(i).getMatricula().equals(matricula)) {
			i++;
		}
		if (i == coches.size()) {
			System.out.println("No encontrado");
		} else {
			
			coches.remove(i);
		}
		System.out.println("el coche con matricula: " + matricula + " sera eliminado con exito");

}


/*
 * recorremos el arraylist y si coincide con la matricula
 * que le pasamos por teclado, nos imprima
 * por pantalla el objeto entero que tenga esa matricula
 *
 */
public static void consultarCoche(ArrayList<Coche> coches) {

    System.out.println("Matricula buscada: ");
    String matricula = lector.nextLine();
    String resultado = "";
    
		for (Coche c : coches) {
			if (c.getMatricula().contains(matricula)) {
				resultado = resultado + c + "\n";
				System.out.println(resultado);
			}
		}
		
		
	
}

/*
 * recorremos arraylist y nos muestra todos los objetos
 */
public static void listadoCoches(ArrayList<Coche> coches) {

	for (Coche c : coches) {
		System.out.println(c);
	}

}


/*
 * creamos fichero coches.txt y escribimos lineas a mano, 
 * y despues recorremos el arrayList y escribimos lineas en el fichero de los objetos
 * que vamos recorriendo en el arrayList
 */
public static void exportarCoches(ArrayList<Coche> coches) throws FileNotFoundException {

	
	// Abrir fichero para escritura
	FileWriter file;
		try {
				file = new FileWriter("coches.txt");
		} catch (IOException e) {
				System.out.println("No se puede abrir el fichero");
					System.out.println(e.getMessage());
					return;
		}
	BufferedWriter buffer = new BufferedWriter(file);
	try {
		
		buffer.write("COCHES MATRICULADOS");
		buffer.newLine();
		buffer.write("");
		buffer.newLine();
		
		for(int i = 0; i<coches.size(); i++) {
		buffer.write(String.valueOf(coches.get(i)));
		buffer.newLine();
		}
		
		buffer.close();
		
		System.out.println("coches exportados con exito");
		
	} catch (IOException e) {
	System.out.println("Error al escribir en el fichero");
	System.out.println(e.getMessage());
	}
	
   }

/*
 * creamos el fichero coches.dat y escribimos todos los objetos que 
 * sacamos del arrayList que recorremos
 */
public static void crearFichero(ArrayList<Coche>coches) throws IOException {
	FileOutputStream file = new FileOutputStream("coches.dat");
	ObjectOutputStream buffer = new ObjectOutputStream(file);
	for(Coche c: coches) {
		buffer.writeObject(c);
	}
	
	buffer.close();
	file.close();
}


/*
 * leemos el fichero coches.dat y sacamos los objetos guardados
 * y los añadimos al arrayList coches
 */
public static ArrayList<Coche> leerFichero (ArrayList<Coche>coches) throws IOException, ClassNotFoundException {
	FileInputStream file = new FileInputStream("coches.dat");
	ObjectInputStream buffer = new ObjectInputStream(file);
	boolean i= true;
	while(i) {
	try {
	coches.add((Coche)buffer.readObject());
	}catch (EOFException e){
		i=false;
	}
	}
	buffer.close();
	file.close();
	return coches;
	
}



}
