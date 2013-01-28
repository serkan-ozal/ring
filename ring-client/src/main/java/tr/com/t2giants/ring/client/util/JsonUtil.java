package tr.com.t2giants.ring.client.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;


public class JsonUtil {

	private JsonUtil() {
		
	}
	
	public static void printJsonData(String jsonData) {
        try {
        	ObjectMapper mapper = new ObjectMapper();
            TypeReference<HashMap<String,Object>> typeRef = new TypeReference<HashMap<String,Object>>() {};
			Map<String, Object> dataMap = mapper.readValue(jsonData, typeRef);
			printJsonData(dataMap);
		} 
        catch (JsonParseException e) {
			e.printStackTrace();
		} 
        catch (JsonMappingException e) {
			e.printStackTrace();
		} 
        catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void printJsonData(Map<String, Object> dataMap) {
		printJsonData(dataMap, 0);
	}
	
	private static void printJsonData(Map<String, Object> dataMap, int depth) {
		int currentDepth = depth++;
		
		indent(currentDepth);
		System.out.println("{");
		
		for (String key : dataMap.keySet()) {
			Object data = dataMap.get(key);
			if (data instanceof List) {
				printData(key + ": ", depth);
				printJsonData((List<?>)data, depth);
			}
			else {
				printData(key, data, depth);
			}
		}
		
		indent(currentDepth);
		System.out.println("}");
	}

	@SuppressWarnings("unchecked")
	private static void printJsonData(List<?> dataList, int depth) {
		int currentDepth = depth++;
		
		indent(currentDepth);
		System.out.println("{");
		
		for (Object data : dataList) {
			if (data instanceof Map) {
				printJsonData((Map<String, Object>)data, depth);
			}
			else {
				printData(data, depth);
			}
		}
		
		indent(currentDepth);
		System.out.println("}");
	}
	
	private static void indent(int depth) {
		for (int i = 0; i < depth; i++) {
			System.out.print("\t");
		}
	}
	
	private static void printData(String key, Object data, int depth) {
		indent(depth);
		System.out.println(key + ": " + data);
	}
	
	private static void printData(Object data, int depth) {
		indent(depth);
		System.out.println(data);
	}
	
	public static <T> T toObject(String jsonText, Class<T> objectClass) {
        try {
        	ObjectMapper mapper = new ObjectMapper();
        	return mapper.readValue(jsonText, objectClass);
		} 
        catch (JsonParseException e) {
			e.printStackTrace();
		} 
        catch (JsonMappingException e) {
			e.printStackTrace();
		} 
        catch (IOException e) {
			e.printStackTrace();
		}
        return null;
	}
	
}
