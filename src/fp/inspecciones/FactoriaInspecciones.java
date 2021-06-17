package fp.inspecciones;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FactoriaInspecciones {
	private static Inspeccion parsearInspecciones(String linea) {
		String lineaSinEspacio = linea.trim();
		String[] trozos=  lineaSinEspacio.split(",");
		if (trozos.length != 8) {
			throw new IllegalArgumentException("tiene que tener atributos");
		}
		 //Inspeccion(String id, String nombre, Distrito distrito, String tipoCocina, LocalDate fecha, String Descripcion, Boolean esCritica, Double score)
		return new Inspeccion(trozos[0], trozos[1], Distrito.valueOf(trozos[2]), trozos[3], LocalDate.parse(trozos[4], DateTimeFormatter.ofPattern("dd/mm/yyyy")), trozos[5], Boolean.valueOf(trozos[6]), Double.valueOf(trozos[7]));
	}
	public static  Inspecciones leerInspeccion(String ruta) {
		Path rutaConvertida = Paths.get(ruta);
		Inspecciones inspecciones =null;
		try {
			inspecciones = new Inspecciones(Files.lines(rutaConvertida).skip(1).map(FactoriaInspecciones::parsearInspecciones));
		} catch (IOException e) {
			System.out.println("NO se a podiao lee er archivo  xxxDDDD");
			 e.printStackTrace();
		}
		return inspecciones;
	}

}
