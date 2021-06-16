package fp.utiles;

import java.util.Locale;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class GraphTools {
		
	private static final String BUBBLE_CHART_PATTERN_FILE ="res/BubbleChartPattern.html";
	public static String transform(String in, Map<String,String> reglas) {
		String out = in;
		Pattern pattern;
		for(String p:reglas.keySet()) {
			pattern = Pattern.compile("\\{"+p+"\\}");
			out = pattern.matcher(out).replaceAll(reglas.get(p));
		}
		return out;
	}
	
	public static <T1, T2, T3> void bubbleChart(String fileOut, String title, Map<String,T1> volumes,
			                                          String hName, Map<String,T2> hValues, 
			                                          String vName, Map<String,T3> vValues) {
		
		
		String msgErr = "Error leyendo fichero " + BUBBLE_CHART_PATTERN_FILE;
		String result = Ficheros.leeFicheroTexto(msgErr,BUBBLE_CHART_PATTERN_FILE,"\n");
		String dataText = rowsBubbleChart(volumes, hValues, vValues);
		Map<String,String> rules = new HashMap<>();
		rules.put("data",dataText);
		rules.put("title",title);
		rules.put("hAxis", hName);
		rules.put("vAxis", vName);
		result = transform(result,rules);
		String errMsg ="Error escribiendo fichero "+ fileOut;
		Ficheros.escribeFicheroTexto(errMsg, result, fileOut);
		
	}

	private static <T1, T2, T3> String rowsBubbleChart(Map<String,T1> volumes, Map<String,T2> hValues, Map<String,T3 > vValues) {
		Set<String> rowNames = volumes.keySet(); 
		return rowNames
			     .stream()
			     .map(row -> String.format(Locale.ROOT, "['%s',%s, %s, %s, %s]" , row, hValues.get(row).toString(), vValues.get(row).toString(), 
			    		                                                          volumes.get(row).toString(), volumes.get(row).toString()))
			     .collect(Collectors.joining(",\n","","\n"));
	}



}
