package ar.edu.unju.fi.model;

public class Materia {
	private String codigo;
	private String nombre;
	private String curso;
	private byte cantHoras;
	private String modalidad;
	private Docente docente;
	private String carrera;
	
	 public Materia() {
		// TODO Auto-generated constructor stub
	}

	public Materia(String codigo, String nombre, String curso, byte cantHoras, String modalidad, Docente docente,
			String carrera) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.curso = curso;
		this.cantHoras = cantHoras;
		this.modalidad = modalidad;
		this.docente = docente;
		this.carrera = carrera;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public byte getCantHoras() {
		return cantHoras;
	}

	public void setCantHoras(byte cantHoras) {
		this.cantHoras = cantHoras;
	}

	public String getModalidad() {
		return modalidad;
	}

	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}

	public Docente getDocente() {
		return docente;
	}

	public void setDocente(Docente docente) {
		this.docente = docente;
	}

	public String getCarrera() {
		return carrera;
	}

	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}
	 
	 

}
