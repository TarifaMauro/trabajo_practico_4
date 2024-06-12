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

import ar.edu.unju.fi.collections.CollectionsAlumno;
import ar.edu.unju.fi.model.Alumno;

@Controller
@RequestMapping("/alumno")
public class AlumnoController {
	
	@Autowired
	private Alumno alumno;
		
	@GetMapping("/listado")
	public String getAlumnoPage(Model model) {
		model.addAttribute("alumnos", CollectionsAlumno.getAlumnos());
		model.addAttribute("titulo", "Alumnos");
		return "alumnos";
	}
	
	@GetMapping("/nuevo")
	public String getAlumnoNuevoPage(Model model) {
		model.addAttribute("alumno", alumno);
		model.addAttribute("titulo", "Nuevo Alumno");
		model.addAttribute("edicion", false);
		return "alumno";
	}
	
	@PostMapping("/guardar")
	public ModelAndView guardarAlumno(@ModelAttribute("alumno") Alumno alumno, Model model) {
		ModelAndView modelView = new ModelAndView("alumnos");
		String mensaje;
		boolean exito = CollectionsAlumno.agregarAlumno(alumno);
		if (exito) {
			mensaje = "Alumno guardado con exito";
		}
		else {
			mensaje = "Alumno no se pudo guardar";
		}
		model.addAttribute("exito", exito);
		model.addAttribute("mensaje", mensaje);
		modelView.addObject("alumnos", CollectionsAlumno.getAlumnos());
		return modelView;
	}
	
	@GetMapping("/modificar/{lu}")
	public String getModificarAlumnoPage(Model model, @PathVariable(value="lu") int lu) {
		Alumno alumnoEncontrado= new Alumno();
		boolean edicion = true;
		alumnoEncontrado = CollectionsAlumno.buscarAlumno(lu);
		if (alumnoEncontrado == null) {
	        model.addAttribute("error", "ALumno no encontrado");
	        return "redirect:/alumno/listado";
	    }
		model.addAttribute("edicion", edicion);
		model.addAttribute("alumno", alumnoEncontrado);
		model.addAttribute("titulo", "Modificar Alumno");
		return "alumno";
	}
	
	@PostMapping("/modificar")
	public String modificarAlumno(@ModelAttribute("alumno") Alumno alumno, Model model) {
		boolean exito = false;
		String mensaje = "";
		try {
			CollectionsAlumno.modificarAlumno(alumno);
			mensaje = "El alumno con Lu " + alumno.getLu() + " fue modificado con exito!";
			exito = true;
		} catch (Exception e) {
			mensaje = e.getMessage();
			e.printStackTrace();
		}
		model.addAttribute("exito", exito);
		model.addAttribute("mensaje", mensaje);
		model.addAttribute("alumnos", CollectionsAlumno.getAlumnos());
		model.addAttribute("titulo", "Alumnos");
		return "alumnos";
	}
	
	@GetMapping("/eliminar/{lu}")
	public String eliminarAlumno(@PathVariable(value="lu") int lu) {
		CollectionsAlumno.eliminarAlumno(lu);
		return "redirect:/alumno/listado";
	}

}


