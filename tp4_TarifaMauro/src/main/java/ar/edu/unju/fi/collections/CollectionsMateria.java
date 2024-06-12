package ar.edu.unju.fi.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;
import ar.edu.unju.fi.model.Materia;



@Component
public class CollectionsMateria {
	private static List<Materia> materias = new ArrayList<Materia>();
	
	public static List<Materia> getMaterias(){
		if( materias.isEmpty() ) {
			materias.add(new Materia(2025, "Programacion Visual", "Segundo", (byte)12, "Virtual",null , null ));
			materias.add(new Materia(2032, "Programacion Estructurada", "Primero", (byte)14, "Virtual",null , null ));
			materias.add(new Materia(2045, "Algebra I", "Segundo", (byte)15, "Virtual/Presencial",null , null ));
			materias.add(new Materia(2012, "LSO II", "Segundo", (byte)10, "Virtual",null , null ));
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
					mat.setDocente(null);
					mat.setCarrera(null);
					encontrado = true;
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
