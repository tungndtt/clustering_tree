package clusteringtree;

import java.util.LinkedList;
import java.util.List;


public class ClusteringTree {
	private ClusteringStructure cs;
	
	public ClusteringTree(int dx, int dy) {
		this.cs = new ClusteringStructure(dx, dy);
	}
	
	public void setDxy(int dx, int dy) {
		this.cs.setDxy(dx, dy);
	}
	
	@SuppressWarnings("unchecked")
	public String print(List<Object> clustering_groups) {
		Object[] pair = this.cs.generate(clustering_groups);
		if(pair == null) {
			return null;
		}
		String portrait = "";
		Object clustering_structure = pair[0];
		if(clustering_structure instanceof Object[]) {
			int height = ((int[]) ((Object[]) clustering_structure)[0])[2];
			LinkedList<Object> current_stage = new LinkedList<>();
			LinkedList<Object> temp_stage = new LinkedList<>();
			current_stage.add(clustering_structure);
			while(height >= 0) {
				int pos = 0;
				while(current_stage.size() > 0) {
					Object group = current_stage.removeFirst();
					int[] range = null;
					if(group instanceof int[]) {
						range = (int[]) group;
					} else {
						range = (int[]) ((Object[]) group)[0];
					}
					while(pos < range[0]) {
						portrait += " ";
						pos += 1;
					}
					if(range[1] - range[0] > 1) {
						portrait += " ";
						pos += 1;
					}
					String shape = null;
					if(height > 0 && height == range[2]) {
						shape = "_";
						List<Object> child_branches = (List<Object>) ((Object[]) group)[1];
						temp_stage.addAll(child_branches);
					} else {
						shape = " ";
						if(height > 0) {
							temp_stage.add(group);
						}
					}
					while(pos < (range[0] + range[1])/2) {
						portrait += shape;
						pos += 1;
					}
					portrait += "|";
					pos += 1;
					while(pos < range[1]) {
						portrait += shape;
						pos += 1;
					}
				}
				portrait += "\n";
				LinkedList<Object> temp = current_stage;
				current_stage = temp_stage;
				temp_stage = temp;
				height--;
			}
		}
		List<String> samples = (List<String>) pair[1];
		for(String sample: samples) {
			portrait += sample;
			for(int i=0; i<this.cs.getDx(); i++) {
				portrait += " ";
			}
		}
		return portrait;
	}
}
