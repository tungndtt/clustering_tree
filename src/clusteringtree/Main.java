package clusteringtree;

import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class Main {
	@SuppressWarnings("unchecked")
	private static List<Object> formatClusteringGroups(JSONArray clustering_groups) {
		List<Object> formatted_clustering_groups = new LinkedList<>();
		clustering_groups.forEach(clustering_group -> {
			if(clustering_group instanceof JSONArray) {
				formatted_clustering_groups.add(formatClusteringGroups((JSONArray) clustering_group));
			} else {
				formatted_clustering_groups.add(clustering_group.toString());
			}
		});
		return formatted_clustering_groups;
	}

	public static void main(String[] args) {
		String filePath = System.getProperty("config");
		if(filePath == null) {
			System.out.println("No config is given!");
			return;
		}
		try (FileReader reader = new FileReader(filePath)) {
            //Read JSON file
			Object config = new JSONParser().parse(reader);
			JSONObject _config = (JSONObject) config;
			JSONArray clustering_groups = (JSONArray) _config.get("clustering_groups");
			List<Object> formatted_clustering_groups = formatClusteringGroups(clustering_groups);
			int dx = ((Long) _config.get("dx")).intValue();
			int dy = ((Long) _config.get("dy")).intValue();
			String clusteringTree = new ClusteringTree(dx, dy).print(formatted_clustering_groups);
			boolean print = (Boolean) _config.get("print");
			if(print) {
				System.out.println(clusteringTree);
			} else {
				String savingFile = (String) _config.get("saving_file");
				File directory = new File(savingFile).getParentFile();
			    if (!directory.exists()){
			    	directory.mkdir();
			    }
				try (PrintWriter out = new PrintWriter(savingFile)) {
				    out.println(clusteringTree);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
