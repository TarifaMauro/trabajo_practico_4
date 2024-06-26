package ar.edu.unju.fi.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.model.Materia;



@Component
public class CollectionsMateria {
	private static List<Materia> materias = new ArrayList<Materia>();
	
	public static List<Materia> getMaterias(){
		if( materias.isEmpty() ) {
			Docente docente = CollectionsDocente.buscarDocente(2020);
			Carrera carrera = CollectionsCarrera.buscarCarrera(1);
			Docente docente2 = CollectionsDocente.buscarDocente(2070);
			Carrera carrera2 = CollectionsCarrera.buscarCarrera(1);
			Docente docente3 = CollectionsDocente.buscarDocente(1990);
			Carrera carrera3 = CollectionsCarrera.buscarCarrera(2);
			Docente docente4 = CollectionsDocente.buscarDocente(2088);
			Carrera carrera4 = CollectionsCarrera.buscarCarrera(3);
			
			materias.add(new Materia(2025, "Programacion Visual", "Segundo", (byte)12, "Virtual",docente , carrera ));
			materias.add(new Materia(2032, "Programacion Estructurada", "Primero", (byte)14, "Virtual",docente2 , carrera2 ));
			materias.add(new Materia(2045, "Algebra I", "Primero", (byte)15, "Virtual/Presencial",docente3 , carrera3 ));
			materias.add(new Materia(2012, "Analisis Matematico I", "Primero", (byte)10, "Virtual",docente4 , carrera4 ));
		}
		return materias;
	}
	public static boolean agregarMateria(Materia materia) {
		return materias.add(materia) ? true : false;
	}
	
	public static void eliminarMateria(int codigo) {
		Iterator<Materia> iterator = materias.iterator();
		while( iterator.hasNext() ) {
			if( iterator.next().getCodigo() == codigo ) {
				iterator.remove();
			}
		}
	}
	
	public static void modificarMateria(Materia materia) throws Exception {
		boolean encontrado = false;
		try {
			for( Materia mat : materias) {
				if( mat.getCodigo() == materia.getCodigo() ) {
					mat.setNombre(materia.getNombre());
					mat.setCurso(materia.getCurso());
					mat.setCantHoras(materia.getCantHoras());
					mat.setModalidad(materia.getModalidad());
					mat.setDocente(materia.getDocente());
					mat.setCarrera(materia.getCarrera());
					encontrado = true;
					break;
				}
			}
			if(!encontrado) {
				throw new Exception ("La materia con codigo: " + materia.getCodigo() + " no existe");
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
			
	}
	
	public static Materia buscarMateria(int codigo) {
		Predicate<Materia> filterCodigo = c -> c.getCodigo() == codigo;
		Optional<Materia> materia = materias.stream().filter(filterCodigo).findFirst();
		if ( materia.isPresent() ) {
			return materia.get();
		}
		else {
			return null;
		}
	}
}
