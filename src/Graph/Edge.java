/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph;

import java.util.Objects;

/**
 *
 * @author Melina Macias
 * @param <E>
 */
public class Edge<E> {
    private Vertex<E> origen;
    private Vertex<E> destino;
    private int peso;

    public Edge(Vertex<E> origen, Vertex<E> destino, int peso) {
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
    }

    public Vertex<E> getOrigen() {
        return origen;
    }

    public void setOrigen(Vertex<E> origen) {
        this.origen = origen;
    }

    public Vertex<E> getDestino() {
        return destino;
    }

    public void setDestino(Vertex<E> destino) {
        this.destino = destino;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
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
        final Edge<?> other = (Edge<?>) obj;
        if (this.peso != other.peso) {
            return false;
        }
        if (!Objects.equals(this.origen, other.origen)) {
            return false;
        }
        if (!Objects.equals(this.destino, other.destino)) {
            return false;
        }
        return true;
    }
    
    
    
}
