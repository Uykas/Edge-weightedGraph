package com.company;


import java.util.*;

public class WeightedGraph<V> {
    public class Vertex {
        public V data;
        private  Map<Vertex, Double> adjacentVertices;

        public Vertex(V data) {
            this.data = data;
            adjacentVertices = new HashMap<>();
        }

        public void addAdjacentVertices(Vertex destination, double weight) {
            adjacentVertices.put(destination, weight);
        }
    }

    private final boolean undirected;
    private final Map<V, Vertex> map = new HashMap<>();

    public WeightedGraph() {
        this.undirected = true;
    }

    public WeightedGraph(boolean undirected) {
        this.undirected = undirected;
    }

    public void addWeightedGraph(V v) {
      map.put(v, new Vertex(v));
    }

    public void addVertex(V v) {
        map.put(v, new Vertex(v));
    }

    public void addEdge(V source, V dest, double weight) {
        if (!hasVertex(source))
            addVertex(source);

        if (!hasVertex(dest))
            addVertex(dest);


        if (hasEdge(source, dest)
                || source.equals(dest))
            return; // reject parallels & self-loops


        map.get(source).addAdjacentVertices(map.get(dest), weight);

        if (undirected)
            map.get(dest).addAdjacentVertices(map.get(source), weight);
    }
    public int getVerticesCount() {
        return map.keySet().size();
    }


    public int getEdgesCount() {
        var temp = 0;
        for(V v: map.keySet()) {
           temp+= map.get(v).adjacentVertices.size();
        }
        return temp/2;
    }


    public boolean hasVertex(V v) {
        return map.containsKey(v);
    }


    public boolean hasEdge(V source, V dest) {
        if(!hasVertex(source)) {
            return false;
        }
        for(V v: map.keySet()) {
            return v.equals(source) && map.get(v).adjacentVertices.containsKey(map.get(dest));
        }
        return false;
    }

    public Iterable<V> adjacencyList(V v) {
        if (!hasVertex(v)) return null;
        List<V> vertices = new LinkedList<>();
        for (Vertex e: map.get(v).adjacentVertices.keySet()) {
            vertices.add(e.data);
        }
        return vertices;
    }
    public Iterable<Vertex> getEdges(V v) {
        if (!hasVertex(v)) return null;
        return map.get(v).adjacentVertices.keySet();
    }

    //
    public Collection<Double> getWeight(V v) {
        return map.get(v).adjacentVertices.values();
    }

    public void getD(V node, V target) { ///TEST CHECKING
        int i = 0;
       Collection temp =  getWeight(node);

        for (WeightedGraph<V>.Vertex edge : getEdges(node)) {
            if(edge.data.equals(target)) {
                System.out.println(temp.toArray()[i]);
                break;
            } else {
                i++;
            }

        }
    }
    public void show(V v) {//data is city //TEST CHECKING
        int i = 0;
        for (Vertex e: map.get(v).adjacentVertices.keySet()) {
            System.out.println(e.data + " ---==-=-=- ");
            System.out.println(map.get(v).adjacentVertices.values().toArray()[i]);
            i++;
        }
    }
}
