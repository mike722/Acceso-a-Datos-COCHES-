package Persistencia;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import Dominio.Extra;
import Dominio.Turismo;
import Dominio.Vehiculo;

public class TurismoDao {

	//Creamos contructor vacio
	public TurismoDao() {

	}

	//Creamos metodo para leer el fichero
	public ArrayList<Vehiculo> leer() {

		ArrayList<Vehiculo> turismos = new ArrayList<Vehiculo>();
		Extra e = new Extra();
		ArrayList<Extra> extras = e.leer();

		try {
			Scanner in = new Scanner(new FileReader("turismos.txt")); //Leemos el fichero
			in.next();
			int contador = in.nextInt();
			
			// Leer turismo
			for (int i = 0; i < contador; i++) {
				in.next();
				String matricula = in.next();
				in.next();
				String marca = in.next();
				in.next();
				String modelo = in.next();
				in.next();
				String color = in.next();
				in.next();
				double precio = in.nextDouble();
				in.next();
				int num_puertas = in.nextInt();
				in.next();
				int extra = in.nextInt();
				boolean encontrado = false;
				for (int j = 0; j < extras.size(); j++) {
					if (extras.get(j).getIdentificador() == extra) {
						e = extras.get(j);
						encontrado = true;
						break;
					}
				}

				if (encontrado == false) {
					e = new Extra(0, "Sin extra");
				}

				Vehiculo turismo = new Turismo(matricula, marca, modelo, color, precio, num_puertas, extra);
				turismos.add(turismo);

			}

			in.close();

		} catch (FileNotFoundException b) { //Por si el fichero no existe
			System.out.println("El fichero no existe");
		} catch (IOException b) {
			System.out.println("Excepcion de entrada y salida" + b.toString());
			System.out.println(b.getMessage());
		}
		return turismos;

	}

	public void escribir(ArrayList<Vehiculo> vehiculos) {
		PrintWriter out;
		try {
			out = new PrintWriter(new FileWriter("turismos.txt")); //Decimos que fichero creemos escribir
			out.println("turismos:");
			out.println(vehiculos.size());
			for (int i = 0; i < vehiculos.size(); i++) {
				out.println("matricula: ");
				out.println(vehiculos.get(i).getMatricula());
				out.println("marca: ");
				out.println(vehiculos.get(i).getMarca());
				out.println("modelo: ");
				out.println(vehiculos.get(i).getModelo());
				out.println("color: ");
				out.println(vehiculos.get(i).getColor());
				out.println("precio: ");
				String precio=vehiculos.get(i).getPrecio()+"";precio=precio.replace(".", ",");
				out.println(precio);
				out.println("num_puertas: ");
				out.println(((Turismo) vehiculos.get(i)).getNum_puertas());
				out.println("extra: ");
				out.println(((Turismo) vehiculos.get(i)).getExtra());
			}
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}