package ar.edu.unju.fi.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;
import ar.edu.unju.fi.model.Docente;



@Component
public class CollectionsDocente {
	private static List<Docente> docentes = new ArrayList<Docente>();
	
	public static List<Docente> getDocentes(){
		if( docentes.isEmpty() ) {
			docentes.add(new Docente(2020, "Lucas", "Majul", "Lucas@gmail.com", "+543885764837"));
			docentes.add(new Docente(2070, "Martin", "Lopez", "Martin@gmail.com", "+543884338869"));
			docentes.add(new Docente(1990, "Gustavo", "Sajama", "Gustavo@gmail.com", "+543885779845"));
			docentes.add(new Docente(2088, "Agustin", "Fernandez", "Agustin@gmail.com", "+543885989383"));
		}
		return docentes;
	}
	public static boolean agregarDocente(Docente docente) {
		return docentes.add(docente) ? true : false;
	}
	
	public static void eliminarDocente(int legajoDoc) {
		Iterator<Docente> iterator = docentes.iterator();
		while( iterator.hasNext() ) {
			if( iterator.next().getLegajo() == legajoDoc ) {
				iterator.remove();
			}
		}
	}
	
	public static void modificarDocente(Docente docente) throws Exception {
		boolean encontrado = false;
		try {
			for( Docente doc : docentes) {
				if( doc.getLegajo() == docente.getLegajo() ) {
					doc.setNombre(docente.getNombre());
					doc.setApellido(docente.getApellido());
					doc.setEmail(docente.getEmail());
					doc.setTelefono(docente.getTelefono());
					encontrado = true;
				}
			}
			if(!encontrado) {
				throw new Exception ("El docente con Legajo: " + docente.getLegajo() + " no existe");
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
			
	}
	
	public static Docente buscarDocente(int legajo) {
		Predicate<Docente> filterCodigo = c -> c.getLegajo() == legajo;
		Optional<Docente> docente = docentes.stream().filter(filterCodigo).findFirst();
		if ( docente.isPresent() ) {
			return docente.get();
		}
		else {
			return null;
		}
	}
	
	public static Docente findDocentebyId(int id) {
		Docente docente = new Docente();
		for (Docente d : docentes){
			if(id == d.getLegajo()) {
				docente = d;
				break;
			}
		}
		return docente;
	}
	
	public static void setDocente(Docente docente) {
		docente.setLegajo(docentes.get(docentes.size()-1).getLegajo());
		docentes.add(docente);
	}
}
