package fp.inspecciones;

import java.time.LocalDate;
import java.time.Period;

public class Inspeccion {
	/*
	 * id: atributo String que almacena el identificador de la inspección
nombre: atributo String que almacena el nombre del restaurante
distrito: atributo del tipo enumerado Distrito.
tipoCocina: atributo String que almacena el tipo de cocina
fecha: atributo LocalDate que almacena la fecha de la inspección. La fecha de inspección
debe ser posterior al 1/1/1900.
descripcion: atributo String que almacena la descripción del resultado de la inspección
esCritica: atributo de tipo Boolean que almacena si la inspección ha sido, o no, crítica
score: atributo de tipo Double que almacena una puntuación numérica de la inspección. El
score debe ser mayor o igual que cero.
	 */
	private String id;
	private String nombre;
	private Distrito distrito;
	private String tipoCocina;
	private LocalDate fecha;
	private String descripcion;
	private Boolean esCritica;
	private Double score;
	
	// constructor 
	public Inspeccion(String id, String nombre, Distrito distrito, String tipoCocina, LocalDate fecha, String Descripcion, Boolean esCritica, Double score) {
		LocalDate fecha2 = LocalDate.of(1900,1,1);
		if(fecha.isBefore(fecha2)|| fecha.isEqual(fecha2))
			throw new IllegalArgumentException("La feha a de ser posterior a 1900");
		if (score <= 0) {
			throw new IllegalArgumentException("tiene que ser mayor que cero");
		}
		//getters 
		
	}

	public String getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public Distrito getDistrito() {
		return distrito;
	}

	public String getTipoCocina() {
		return tipoCocina;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Boolean getEsCritica() {
		return esCritica;
	}

	public Double getScore() {
		return score;
	}
	// derivadas
	public Period getTiempoTranscurrido() {
		return Period.between(getFecha(), LocalDate.now());
	}
	@Override
	public String  toString() {
		return getNombre() +", " + getFecha() +", "+getDescripcion()+", "+getEsCritica()+", "+getScore();
	}
	@Override
	public boolean equals(Object obj) {
		if (obj== this)
			return true;
		if(obj == null)
			return false;
		if(obj.getClass() !=this.getClass())
			return false;
		Inspeccion other = (Inspeccion)obj;
		if(getFecha() == null) {
			if(fecha != null) {
				return false;
			}
		}else if (!getFecha().equals(other.getFecha())) {
			return false;
		}
		if (this.getId() == null ) {
			if(other.getId() != null)
				return false;
		} else if(!other.getId().equals(this.getId())) {
			return false;
		}
		return true;
			
		
		
		
	}
	@Override
	public int hashCode() {
		return getFecha().hashCode()*getId().hashCode();
		
	}
	
	
}



