package fp.inspecciones;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Inspecciones {
	private List<Inspeccion> inspecciones;

	public Inspecciones() {
		this.inspecciones =  new ArrayList<Inspeccion>();
	}

	public Inspecciones(Stream<Inspeccion> streamInspeccion) {
		this.inspecciones  = streamInspeccion.collect(Collectors.toList());
	}
	 public List<Inspeccion> getInspecciones(){
		 return new ArrayList<>(inspecciones);
	 }
	 public void añadirInspeccion(Inspeccion a ) {
		 inspecciones.add(a);
	 }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((inspecciones == null) ? 0 : inspecciones.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Inspecciones other = (Inspecciones) obj;
		if (inspecciones == null) {
			if (other.inspecciones != null)
				return false;
		} else if (!inspecciones.equals(other.inspecciones))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Inspecciones [inspecciones=" + inspecciones + "]";
	}
	public Map<String,String> obtenerDescripcionInspeccionMayorPuntuacion() {
		Function<String, String> descMAsValorada = nombre -> inspecciones.stream().filter(inspeccion -> inspeccion.getNombre().equals(nombre)).max(Comparator.comparing(Inspeccion::getScore)).get().getDescripcion();
		Map<String, String> diccionarioMaximos = inspecciones.stream().collect(Collectors.toMap(Inspeccion::getNombre,inspeccion-> descMAsValorada.apply(inspeccion.getNombre())));
		return diccionarioMaximos;
	}
}
