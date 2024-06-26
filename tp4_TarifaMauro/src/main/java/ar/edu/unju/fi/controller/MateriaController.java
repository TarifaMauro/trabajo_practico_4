package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.collections.CollectionsCarrera;
import ar.edu.unju.fi.collections.CollectionsDocente;
import ar.edu.unju.fi.collections.CollectionsMateria;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.model.Materia;


@Controller
@RequestMapping("/materia")
public class MateriaController {
	
	@Autowired
	private Materia materia;
	
	@Autowired
	private Carrera carrera;
	
	@Autowired
	private Docente docente;
		
	@GetMapping("/listado")
	public String getMateriaPage(Model model) {
		model.addAttribute("carreras", CollectionsCarrera.getCarreras());
		model.addAttribute("docentes", CollectionsDocente.getDocentes());
		model.addAttribute("materias", CollectionsMateria.getMaterias());
		model.addAttribute("titulo", "Materias");
		model.addAttribute("exito", false);
		model.addAttribute("mensaje", "");
		return "materias";
	}
	
	@GetMapping("/nueva")
	public String getMateriaNuevaPage(Model model) {
		boolean edicion = false;
		model.addAttribute("carreras", CollectionsCarrera.getCarreras()); 
		model.addAttribute("docentes", CollectionsDocente.getDocentes());
		model.addAttribute("materia", materia);
		model.addAttribute("titulo", "Nueva Materia");
		model.addAttribute("edicion", edicion);
		return "materia";
	}
	
	@PostMapping("/guardar")
	public ModelAndView guardarMateria(@ModelAttribute("materia") Materia materia, Model model) {
		ModelAndView modelView = new ModelAndView("materias");
		String mensaje;
		carrera = CollectionsCarrera.buscarCarrera(materia.getCarrera().getCodigo());
        docente = CollectionsDocente.buscarDocente(materia.getDocente().getLegajo());
        materia.setCarrera(carrera); 
		materia.setDocente(docente);
		boolean exito = CollectionsMateria.agregarMateria(materia);
		if (exito) {
			mensaje = "Materia guardada con exito";
		}
		else {
			mensaje = "Materia no se pudo guardar";
		}
		model.addAttribute("exito", exito);
		model.addAttribute("mensaje", mensaje);
		modelView.addObject("materias", CollectionsMateria.getMaterias());
		return modelView;
	}
	
	@GetMapping("/modificar/{codigo}")
	public String getModificarMateriaPage(Model model, @PathVariable(value="codigo") int codigo) {
		Materia materiaEncontrada= new Materia();
		boolean edicion = true;
		materiaEncontrada = CollectionsMateria.buscarMateria(codigo);
		if (materiaEncontrada == null) {
	        model.addAttribute("error", "Materia no encontrada");
	        return "redirect:/materia/listado";
	    }
		model.addAttribute("edicion", edicion);
		model.addAttribute("materia", materiaEncontrada);
		model.addAttribute("titulo", "Modificar Materia");
		model.addAttribute("carreras",CollectionsCarrera.getCarreras()); 
		model.addAttribute("docentes", CollectionsDocente.getDocentes());
		return "materia";
	}
	
	@PostMapping("/modificar")
	public String modificarMateria(@ModelAttribute("materia") Materia materia, Model model) {
		boolean exito = false;
		String mensaje = "";
		carrera = CollectionsCarrera.buscarCarrera(materia.getCarrera().getCodigo());
        docente = CollectionsDocente.buscarDocente(materia.getDocente().getLegajo());
		materia.setCarrera(carrera); 
		materia.setDocente(docente);
		try {
			CollectionsMateria.modificarMateria(materia);
			mensaje = "La materia con codigo: " + materia.getCodigo() + " fue modificada con exito!";
			exito = true;
		} catch (Exception e) {
			mensaje = e.getMessage();
			e.printStackTrace();
		}
		model.addAttribute("exito", exito);
		model.addAttribute("mensaje", mensaje);
		model.addAttribute("materias", CollectionsMateria.getMaterias());
		model.addAttribute("titulo", "Materias");
		return "materias";
	}
	
	@GetMapping("/eliminar/{codigo}")
	public String eliminarAlumno(@PathVariable(value="codigo") int codigo) {
		CollectionsMateria.eliminarMateria(codigo);
		return "redirect:/materia/listado";
	}

}

