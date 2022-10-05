# Clustering Groups Displaying
You have the clustering groups, and want to display them visually

## Config
The config has the following format:
- `dx`: The width-distance between samples
- `dy`: The height-distance between the clustering levels
- `print`: **true** if you just want to log in the console. **false** means that the result is saved into the specified `saving_file`
- `clustering_groups`: The clustering groups 

## Usage
The program can be compiled and run using the following commands:
- Working directory: `./ClusteringTree`
- Compile: `javac -d bin src/clusteringtree/*.java -cp libs/json-simple-1.1.jar`
- Run: `java -Dconfig="<your-config-file.json>" -cp libs/json-simple-1.1.jar;bin clusteringtree/Main clusteringtree/ClusteringTree clusteringtree/ClusteringStructure`

For example, if you want to run the `./demo/demo_1.json`, just compile the program and run `java -Dconfig="./demo/demo_1.json>" -cp libs/json-simple-1.1.jar;bin clusteringtree/Main clusteringtree/ClusteringTree clusteringtree/ClusteringStructure`

## Demo
[Here](https://github.com/tungndtt/clustering_tree/blob/main/demo/) you can find the demo configs and [here](https://github.com/tungndtt/clustering_tree/blob/main/demo/result/) contains the results of those demo configs