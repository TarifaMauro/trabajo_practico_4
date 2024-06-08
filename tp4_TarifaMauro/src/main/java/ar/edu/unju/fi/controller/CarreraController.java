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
import ar.edu.unju.fi.model.Carrera;


@Controller
@RequestMapping("/carrera")
public class CarreraController {
	@Autowired
	private Carrera carrera;
	
	@GetMapping("/listado")
	public String getCarreraPage(Model model) {
		model.addAttribute("carreras", CollectionsCarrera.getCarreras());
		model.addAttribute("titulo", "Carreras");
		return "carreras";
	}
	
	@GetMapping("/nuevo")
	public String getNuevaCarreraPage(Model model) {
		boolean edicion = false;
		model.addAttribute("carrera", carrera);
		model.addAttribute("edicion", edicion);
		model.addAttribute("titulo", "Nueva Carrera");
		return "carrera";
	}
	
	@PostMapping("/guardar")
	public ModelAndView guardarCarrera(@ModelAttribute("carrera") Carrera carrera) {
		ModelAndView modelView = new ModelAndView("carreras"); 
		carrera.setEstado(true);
		CollectionsCarrera.agregarCarrera(carrera);
		modelView.addObject("carreras", CollectionsCarrera.getCarreras());
		return modelView;
	}
	
	@GetMapping("/modificar/{codigo}")
	public String getModificarCarreraPage(Model model, @PathVariable(value="codigo") int codigo) {
		Carrera carreraEncontrada= new Carrera();
		boolean edicion = true;
		carreraEncontrada = CollectionsCarrera.buscarCarrera(codigo);
		if (carreraEncontrada == null) {
	        // Si no se encuentra la carrera, redirige a la página de listado de carreras con un mensaje de error
	        model.addAttribute("error", "Carrera no encontrada");
	        return "redirect:/carrera/listado";
	    }
		model.addAttribute("edicion", edicion);
		model.addAttribute("carrera", carreraEncontrada);
		model.addAttribute("titulo", "Modificar Carrera");
		return "carrera";
	}
	
	@PostMapping("/modificar")
	public String modificarCarrera(@ModelAttribute("carrera") Carrera carrera) {
		CollectionsCarrera.modificarCarrera(carrera);
		return "redirect:/carrera/listado";
	}
	
	@GetMapping("/eliminar/{codigo}")
	public String eliminarCarrera(@PathVariable(value="codigo") int codigo) {
		CollectionsCarrera.eliminarCarrera(codigo);
		return "redirect:/carrera/listado";
	}
}
