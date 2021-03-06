package Models;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Graph {

    private int count = 1;
    private List<Node> nodes = new ArrayList<>();
    private List<Edge> edges = new ArrayList<>();
    public static List<Node> redZoneNode = new ArrayList<>();
    public static List<Node> hospitalNode=new ArrayList<>();
    private Node source;
    public static Node destination;

    private boolean solved = false;

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    //check if node a node is reachable or not
    public boolean isNodeReachable(Node node) {
        for (Edge edge : edges) {
            if (node == edge.getNodeOne() || node == edge.getNodeTwo()) {
                return true;
            }
        }

        return false;
    }

    public void setSource(Node node) {
        if (nodes.contains(node)) {
            source = node;
        }
    }

    public void setDestination(Node node) {
        if (nodes.contains(node)) {
            destination = node;
        }
    }

    public Node getSource() {
        return source;
    }

    public Node getDestination() {
        return destination;
    }

    public boolean isSource(Node node) {
        return node == source;
    }

    public boolean isDestination(Node node) {
        return node == destination;
    }

    //make a node from point
    public void addNode(Point coord) {
        Node node = new Node(coord);
        addNode(node);
    }

    //add node to nodes (list)
    public void addNode(Node node) {
        node.setId(count++);
        nodes.add(node);
        if (node.getId() == 1) {
            source = node;
        }
    }

    //add edges to edge list
    public void addEdge(Edge new_edge) {
        boolean added = false;
        for (Edge edge : edges) {
            if (edge.equals(new_edge)) {
                added = true;
                break;
            }
        }
        if (!added) {
            edges.add(new_edge);
        }
    }

    //delete Nodes from the list
    public void deleteNode(Node node) {
        List<Edge> delete = new ArrayList<>();
        for (Edge edge : edges) {
            if (edge.hasNode(node)) {
                delete.add(edge);
            }
        }
        for (Edge edge : delete) {
            edges.remove(edge);
        }
        nodes.remove(node);
        if(isHospitalNode(node))
        	hospitalNode.remove(node);
        if(isRedZoneNode(node))
        	redZoneNode.remove(node);
    }

    //clear the graph
    public void clear() {
        count = 1;
        nodes.clear();
        edges.clear();
	hospitalNode.clear();
        redZoneNode.clear();
	solved = false;
        source = null;
        destination = null;
    }

    public boolean isRedZoneNode(Node node) {
        if (redZoneNode.contains(node)) {
            return true;
        }
        return false;
    }

    public void setRedZone(Node node) {
        if (!redZoneNode.contains(node)) {
            redZoneNode.add(node);
        }
    }

    public void removeRedZone(Node node) {
        redZoneNode.remove(node);
    }

	public boolean isHospitalNode(Node node) {
		if(hospitalNode.contains(node))
			return true;
		return false;
	}

	public void setHospitalNode(Node node) {
		if(!hospitalNode.contains(node))
			hospitalNode.add(node);
	}

	public void removeHospitalNode(Node node) {
		hospitalNode.remove(node);
	}
}
