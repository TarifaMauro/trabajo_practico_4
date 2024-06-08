package ar.edu.unju.fi.model;

import org.springframework.stereotype.Component;

@Component
public class Carrera {
    private int codigo;
    private String nombre;
    private int cantidadAnios;
    private boolean estado;

    public Carrera() {
		// TODO Auto-generated constructor stub
	}

	public Carrera(int codigo, String nombre, int cantidadAnios, boolean estado) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.cantidadAnios = cantidadAnios;
		this.estado = estado;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCantidadAnios() {
		return cantidadAnios;
	}

	public void setCantidadAnios(int cantidadAnios) {
		this.cantidadAnios = cantidadAnios;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}


}
