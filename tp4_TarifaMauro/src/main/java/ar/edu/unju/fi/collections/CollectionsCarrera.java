package ar.edu.unju.fi.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.model.Carrera;

@Component
public class CollectionsCarrera {
	private static List<Carrera> carreras = new ArrayList<Carrera>();
	
	
	
	
	public static List<Carrera> getCarreras(){
		if( carreras.isEmpty() ) {
			carreras.add(new Carrera(1, "A.P.U", 3, true));
			carreras.add(new Carrera(2, "Ingeniería Informática", 5, true));
			carreras.add(new Carrera(3, "Ingeniería Química", 5, true));
		}
		return carreras;
	}
	//Agrega un objeto Carrera al arraylist de carrera
	
	public static boolean agregarCarrera(Carrera carrera) {
		return carreras.add(carrera) ? true : false;
	}
	
	//elimina un objeto carrera del arraylist de carrera
	
	public static void eliminarCarrera(int codigoCarrera) {
		Iterator<Carrera> iterator = carreras.iterator();
		while( iterator.hasNext() ) {
			if( iterator.next().getCodigo() == codigoCarrera ) {
				iterator.remove();
			}
		}
	}
	
	//modifica un objeto carrera con los nuevos valores enviados
	
	public static void modificarCarrera(Carrera carrera) throws Exception {
		boolean encontrado = false;
		try {
			for( Carrera carre : carreras) {
				if( carre.getCodigo() == carrera.getCodigo() ) {
					carre.setNombre(carrera.getNombre());
					carre.setCantidadAnios(carrera.getCantidadAnios());
					carre.setEstado(carrera.isEstado());
					encontrado = true;
				}
			}
			if(!encontrado) {
				throw new Exception ("La carrera con codigo " + carrera.getCodigo() + " no existe");
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
			
	}
	
	
	// busca un objeto carrera dentro del arraylist
	public static Carrera buscarCarrera(int codigo) {
		Predicate<Carrera> filterCodigo = c -> c.getCodigo() == codigo;
		Optional<Carrera> carrera = carreras.stream().filter(filterCodigo).findFirst();
		if ( carrera.isPresent() ) {
			return carrera.get();
		}
		else {
			return null;
		}
	}
}
