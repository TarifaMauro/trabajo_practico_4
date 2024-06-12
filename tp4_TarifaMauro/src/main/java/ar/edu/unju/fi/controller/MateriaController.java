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

import ar.edu.unju.fi.collections.CollectionsMateria;
import ar.edu.unju.fi.model.Materia;


@Controller
@RequestMapping("/materia")
public class MateriaController {
	
	@Autowired
	private Materia materia;
		
	@GetMapping("/listado")
	public String getMateriaPage(Model model) {
		model.addAttribute("materias", CollectionsMateria.getMaterias());
		model.addAttribute("titulo", "Materias");
		return "materias";
	}
	
	@GetMapping("/nueva")
	public String getMateriaNuevaPage(Model model) {
		model.addAttribute("materia", materia);
		model.addAttribute("titulo", "Nueva Materia");
		model.addAttribute("edicion", false);
		return "materia";
	}
	
	@PostMapping("/guardar")
	public ModelAndView guardarMateria(@ModelAttribute("materia") Materia materia, Model model) {
		ModelAndView modelView = new ModelAndView("materias");
		String mensaje;
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
		return "materia";
	}
	
	@PostMapping("/modificar")
	public String modificarMateria(@ModelAttribute("materia") Materia materia, Model model) {
		boolean exito = false;
		String mensaje = "";
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

