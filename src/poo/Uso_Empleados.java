package poo;
import java.util.*;

	public class Uso_Empleados {
	public static void main(String[] args) {
		
		Jefatura jefe_RRHH = new Jefatura("Juan", 55000,2015, 9, 25);
		
		
		jefe_RRHH.establecerIncentivo(2570);
		
		
		Empleado empleados[] = new Empleado[6]; // Estamos almacenando un objeto de tipo Jefatura en un array de tipo Empleado en [4], [5]
		
		empleados[0] = new Empleado("Jorge", 5500.0, 2020, 8, 27);
		empleados[1] = new Empleado("Samuel", 4500.0, 2000, 13, 3); 
		empleados[2] = new Empleado("Sara", 7500.0, 2019, 1, 15); 
		empleados[3] = new Empleado("Luis", 4300.0, 2000, 9, 6); 
		empleados[4] = jefe_RRHH; //Polimorfismo en acción. Principio de Sustitución.
		empleados[5] = new Jefatura("María", 45000, 2007, 12, 23); // Es otra forma perfectamente válida de representar el P.S.
		
		
		
		
		Jefatura Jefa_Finanzas = (Jefatura) empleados[5];// Casting
		
		Jefa_Finanzas.establecerIncentivo(3000);// Y ahora si!
		
		
		
		
		
		for(Empleado e : empleados) {
			e.cargarSueldo(5);
		}
		
		for(Empleado e : empleados) {
			System.out.println("Nombre: " + e.dameNombre() + "   Id: " + e.dameId() +
					"   Sueldo: " + e.dameSueldo() + "   Fecha: " + e.dameFechaContrato());
		}	// dameSueldo es un metodo perteneciente a dos clases diferentes, y ambas son llamadas según el objeto. [enlazado dinámico]
		
		System.out.println(Empleado.mostrarIdSiguiente());
		
		
	}
	
}
	
class Empleado{

	
	public Empleado(String nom, double sue, int agno, int mes, int dia){
		
		nombre = nom;
		sueldo = sue;
		
		GregorianCalendar calendario = new GregorianCalendar(agno, mes-1, dia);
		
		altaContrato = calendario.getTime();
		
//		Id = 1;
		
		Id = IdSiguiente;
		
		++IdSiguiente;
	}
	
	public Empleado(String nombre){
		
		this(nombre, 3500, 2015, 13, 23);
		
	}
	
	public void cambiarSeccion(String secc) {
		sección = secc;
	}
	
	public String mostraDatos() {
		return "\nSu nombre es: " + nombre + "\nY su área es: " + sección + "\nCon un ID: " + Id;
	}
	
	public static String mostrarIdSiguiente() {
		return "El Id Siguiente es: " + IdSiguiente;
	}
	
	
	public String dameNombre() {
		return nombre;
	}
	
	public int dameId() {
		return Id;
	}
	
	public double dameSueldo() {		
		return sueldo;
	}
	
	public Date dameFechaContrato() {
		return altaContrato;
	}
	
	public void cargarSueldo(double porcentaje) {	
		double aumento = sueldo * porcentaje/100;
		sueldo += aumento;
	}
	
	
	final private String nombre;

	private double sueldo;
	
	private Date altaContrato;
	
	private String sección;
	
//	public static int Id;
	
	private int Id;

	public static int IdSiguiente=1;
	
}

class Jefatura extends Empleado{
	
	public Jefatura(String nom, double sue, int agno, int mes, int dia) { // Constructor que une dos grupos de parámetros! 
		super(nom, sue, agno, mes, dia); // Constructor padre al que nos dirigimos
	}
	
	public void establecerIncentivo(double b) {
		incentivo = b;
	}
	
	public double dameSueldo() { 
		
		double sueldoJefe = super.dameSueldo();
		
		return sueldoJefe + incentivo;
	}
	
	private double incentivo; 
}