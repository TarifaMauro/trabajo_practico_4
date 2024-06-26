package ar.edu.unju.fi.collections;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;
import ar.edu.unju.fi.model.Alumno;



@Component
public class CollectionsAlumno {
	private static List<Alumno> alumnos = new ArrayList<Alumno>();
	
	public static List<Alumno> getAlumnos(){
		if( alumnos.isEmpty() ) {
			alumnos.add(new Alumno(40440760, "Mauro", "Tarifa", "Dastan@gmail.com", "+543883310671",LocalDate.of(1997, 10, 19),
					"Virrey Cisneros","APU005005"));
			alumnos.add(new Alumno(43443760, "Bigote", "Tarifa", "Bigote@gmail.com", "+543883414661",LocalDate.of(2005, 7, 3),
					"Virrey Cisneros","APU004400"));
			alumnos.add(new Alumno(39339997, "Tino", "Tarifa", "Tino@gmail.com", "+543885336671",LocalDate.of(1998, 11, 20),
					"Virrey Cisneros", "APU003890"));

		}
		return alumnos;
	}
	public static boolean agregarAlumno(Alumno alumno) {
		return alumnos.add(alumno) ? true : false;
	}
	
	public static void eliminarAlumno(String luAlumno) {
		Iterator<Alumno> iterator = alumnos.iterator();
		while( iterator.hasNext() ) {
			if( iterator.next().getLu().equals(luAlumno) ) {
				iterator.remove();
			}
		}
	}
	
	public static void modificarAlumno(Alumno alumno) throws Exception {
		boolean encontrado = false;
		try {
			for( Alumno alu : alumnos) {
				if( alu.getLu() == alumno.getLu() ) {
					alu.setNombre(alumno.getNombre());
					alu.setApellido(alumno.getApellido());
					alu.setDni(alumno.getDni());
					alu.setEmail(alumno.getEmail());
					alu.setTelefono(alumno.getTelefono());
					alu.setFechaNacimiento(alumno.getFechaNacimiento());
					alu.setDomicilio(alumno.getDomicilio());
					encontrado = true;
				}
			}
			if(!encontrado) {
				throw new Exception ("El alumno con LU: " + alumno.getLu() + " no existe");
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
			
	}
	
	public static Alumno buscarAlumno(String lu) {
		Predicate<Alumno> filterCodigo = c -> c.getLu().equals(lu);
		Optional<Alumno> alumno = alumnos.stream().filter(filterCodigo).findFirst();
		if ( alumno.isPresent() ) {
			return alumno.get();
		}
		else {
			return null;
		}
	}
	
}
