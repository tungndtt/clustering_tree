package clusteringtree;

import java.util.LinkedList;
import java.util.List;


public class ClusteringStructure {
	private int dx, dy;
	
	ClusteringStructure(int dx, int dy) {
		this.setDxy(dx, dy);
	}
	
	void setDxy(int dx, int dy) {
		this.dx = dx > 2? dx : 3;
		this.dy = dy > 0? dy : 1;
	}
	
	int getDx() {
		return this.dx;
	}
	
	Object[] generate(List<Object> clustering_groups) {
		List<String> samples = new LinkedList<>();
		Object clustering_structure = this.recursive_run(clustering_groups, 0, samples);
		return new Object[] {clustering_structure, samples};
	}
	
	@SuppressWarnings("unchecked")
	private Object recursive_run(List<Object> clustering_groups, int x, List<String> samples) {
		if(clustering_groups.size() == 0) {
			return null;
		} else {
			int start = -1, end = -1, height = 0;
			List<Object> clustering_structure = new LinkedList<>();
			for(Object node: clustering_groups) {
				if(node instanceof String) {
					String _node = (String) node;
					samples.add(_node);
					clustering_structure.add(new int[] {x, x + _node.length(), 0, x + _node.length() + this.dx});
					if(start == -1) {
						start = x + _node.length()/2;
					}
					end = x + _node.length()/2;
					x += _node.length() + this.dx;
				} else if(node instanceof List) {
					Object result = this.recursive_run((List<Object>)node, x, samples);
					if(result != null) {
						int[] range = null;
						if(result instanceof int[]) {
							range = (int[]) result;
						} else {
							range = (int[]) ((Object[]) result)[0];
						}
						if(start == -1) {
							start = (range[0] + range[1])/2;
						}
						end = (range[0] + range[1])/2;
						clustering_structure.add(result);
						if(height < range[2]) {
							height = range[2];
						}
						x = range[3];
					}
				}
			}
			if(clustering_structure.size() == 0) {
				return null;
			} else if(clustering_structure.size() == 1) {
				return clustering_structure.get(0);
			} else {
				return new Object[] {new int[] {start, end, height + this.dy, x}, clustering_structure};
			}
			
		}
	}
}
