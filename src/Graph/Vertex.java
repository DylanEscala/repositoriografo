/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Melina Macias
 * @param <E>
 */
public class Vertex<E> {
    private E data;
    private List<Edge<E>> edges;
    private boolean visited;
    private int distancia;
    private Vertex<E> antecesor;
    
    public Vertex(E data){
        this.data=data;
        this.edges= new LinkedList<>();
        this.distancia = Integer.MAX_VALUE;
        this.antecesor = null;
        this.visited = false;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public List<Edge<E>> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge<E>> edges) {
        this.edges = edges;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vertex<E> other = (Vertex<E>) obj;
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        return true;
}

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.data);
        return hash;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public Vertex<E> getAntecesor() {
        return antecesor;
    }

    public void setAntecesor(Vertex<E> antecesor) {
        this.antecesor = antecesor;
    }
    
    
}