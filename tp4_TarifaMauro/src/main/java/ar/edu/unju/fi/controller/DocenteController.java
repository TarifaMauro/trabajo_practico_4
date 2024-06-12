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

import ar.edu.unju.fi.collections.CollectionsDocente;
import ar.edu.unju.fi.model.Docente;


@Controller
@RequestMapping("/docente")
public class DocenteController {
	
	@Autowired
	private Docente docente;
		
	@GetMapping("/listado")
	public String getDocentePage(Model model) {
		model.addAttribute("docentes", CollectionsDocente.getDocentes());
		model.addAttribute("titulo", "Docentes");
		return "docentes";
	}
	
	@GetMapping("/nuevo")
	public String getDocenteNuevoPage(Model model) {
		model.addAttribute("docente", docente);
		model.addAttribute("titulo", "Nuevo Docente");
		model.addAttribute("edicion", false);
		return "docente";
	}
	
	@PostMapping("/guardar")
	public ModelAndView guardarDocente(@ModelAttribute("docente") Docente docente, Model model) {
		ModelAndView modelView = new ModelAndView("docentes");
		String mensaje;
		boolean exito = CollectionsDocente.agregarDocente(docente);
		if (exito) {
			mensaje = "Docente guardado con exito";
		}
		else {
			mensaje = "Docente no se pudo guardar";
		}
		model.addAttribute("exito", exito);
		model.addAttribute("mensaje", mensaje);
		modelView.addObject("docentes", CollectionsDocente.getDocentes());
		return modelView;
	}
	
	@GetMapping("/modificar/{legajo}")
	public String getModificarDocentePage(Model model, @PathVariable(value="legajo") int legajo) {
		Docente docenteEncontrado= new Docente();
		boolean edicion = true;
		docenteEncontrado = CollectionsDocente.buscarDocente(legajo);
		if (docenteEncontrado == null) {
	        model.addAttribute("error", "Docente no encontrado");
	        return "redirect:/docente/listado";
	    }
		model.addAttribute("edicion", edicion);
		model.addAttribute("docente", docenteEncontrado);
		model.addAttribute("titulo", "Modificar Docente");
		return "docente";
	}
	
	@PostMapping("/modificar")
	public String modificarDocente(@ModelAttribute("docente") Docente docente, Model model) {
		boolean exito = false;
		String mensaje = "";
		try {
			CollectionsDocente.modificarDocente(docente);
			mensaje = "El docente con legajo: " + docente.getLegajo() + " fue modificado con exito!";
			exito = true;
		} catch (Exception e) {
			mensaje = e.getMessage();
			e.printStackTrace();
		}
		model.addAttribute("exito", exito);
		model.addAttribute("mensaje", mensaje);
		model.addAttribute("docentes", CollectionsDocente.getDocentes());
		model.addAttribute("titulo", "Docentes");
		return "docentes";
	}
	
	@GetMapping("/eliminar/{legajo}")
	public String eliminarAlumno(@PathVariable(value="legajo") int legajo) {
		CollectionsDocente.eliminarDocente(legajo);
		return "redirect:/docente/listado";
	}

}


