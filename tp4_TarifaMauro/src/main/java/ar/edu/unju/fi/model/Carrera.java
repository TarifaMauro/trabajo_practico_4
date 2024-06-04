package ar.edu.unju.fi.model;

public class Carrera {
    private String codigo;
    private String nombre;
    private int cantdAnios;
    private String estado;

    public Carrera() {
		// TODO Auto-generated constructor stub
	}

	public Carrera(String codigo, String nombre, int cantdAnios, String estado) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.cantdAnios = cantdAnios;
		this.estado = estado;
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

	public int getCantdAnios() {
		return cantdAnios;
	}

	public void setCantdAnios(int cantdAnios) {
		this.cantdAnios = cantdAnios;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
    
}
