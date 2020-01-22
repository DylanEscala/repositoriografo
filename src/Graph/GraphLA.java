/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Familia
 */
public class GraphLA<E> {
    
    private List<Vertex<E>> vertexes;
    private boolean directed;
    
    public GraphLA(boolean directed) {
        this.directed=directed;
        this.vertexes= new LinkedList<>();
        
    }
    
    public boolean isEmpty(){
        return vertexes.isEmpty();
    }
    
    
    public boolean addVertex(E data){
        if(data==null)
            return false;
        Vertex<E> vertice= new Vertex<>(data);
        if(vertexes.contains(vertice)) return false;
        return vertexes.add(vertice);
        
        
    }
    
    public boolean addEdge(E origen,E destino,int peso){
        if(origen==null||destino==null)
            return false;
        Vertex<E> vo= searchVertex(origen);
        Vertex<E> vd= searchVertex(destino);
        //PROBAR QUE LOS DATOS SE ENCUENTRAN EN EL GRAFO
        if(vo==null||vd==null)  return false;
        Edge<E> e= new Edge<>(vo,vd,peso);
        if(vo.getEdges().contains(e)) return false;
        vo.getEdges().add(e);
        //si no son grafos dirigidos
        if(!directed){
            Edge<E> e1= new Edge<>(vd,vo,peso);
            //no se controla con un contains porque sino esta de un lado no deberia estar en otro. 
            vd.getEdges().add(e1);
        }
        return true;
    }
    private Vertex<E> searchVertex(E data){
        
        for(Vertex<E> v: vertexes){
            if(v.getData().equals(data))
                return v;
        }
        
        return null;
    }

    
    public boolean removeVertex(E data){
        if(data==null)
            return false;
        Vertex<E> vertice = searchVertex(data);
        if (vertice==null) 
            return false;
        //Recorrer para eliminar todas las relaciones que tiene ese vertice
        ListIterator<Vertex<E>> listaVertice= vertexes.listIterator();
        while(listaVertice.hasNext()){
            Vertex<E> vertex= listaVertice.next();
            //Recorre los edges que tiene cada vertice y los elimina
            ListIterator<Edge<E>> iterador= vertex.getEdges().listIterator();
            while(iterador.hasNext()){
                Edge<E> arco= iterador.next();
                if(arco.getDestino().equals(vertice)) 
                    iterador.remove();
            }
            
            vertice.getEdges().clear();
    }
        return this.vertexes.remove(vertice);
    }

    public boolean removeEdge(E origen,E destino){
        if(origen==null||destino==null)
            return false;
        //Vertice de origen
        Vertex<E> vertex1= new Vertex<>(origen);
        //vertice de destino
        Vertex<E> vertex2= new Vertex<>(destino);
        
        Edge<E> arco= new Edge<>(vertex1,vertex2,0);
        vertex1.getEdges().remove(arco);
        
        //eliminar el  otro arco si no es dirigido
        if (!directed){
            Edge<E> arcoOp= new Edge<>(vertex1,vertex2,0);
            vertex2.getEdges().remove(arcoOp);
        }
        return true;

    }
    //si el grafo no es dirigido inDegree y outDegree deben regresar lo mismo
    public int inDegree(E data){
        if(data == null) 
            return -1;
        //verificar si la data se encuentra en el grafo
        Vertex<E> v = searchVertex(data);
        if(v == null) 
            return -1;
        int degree = 0;
        
        ListIterator<Vertex<E>> iterador = vertexes.listIterator();
        while(iterador.hasNext()){
            Vertex<E> v1 = iterador.next();
            ListIterator<Edge<E>> ite = v1.getEdges().listIterator();
            while(ite.hasNext()){
                Edge<E> e1 = ite.next();
                if(e1.getDestino().equals(v)) degree++;
            }
        }
        return degree;
     }
    
    public int outDegree(E data){
        if(data == null )
            return -1;
        Vertex<E> vertice = searchVertex(data);
        if(vertice == null)
            return -1;
        return vertice.getEdges().size();
    }
    
    public void Djikstra(E origen){
        Vertex<E> start = searchVertex(origen);
        if (start != null){
            for (Vertex<E> v: vertexes){
                v.setDistancia(Integer.MAX_VALUE);
                v.setAntecesor(null);
                v.setVisited(false);
            }
            PriorityQueue<Vertex<E>> cola = new PriorityQueue<>((Vertex<E> v1, Vertex<E> v2)->v1.getDistancia()-v2.getDistancia());
            cola.offer(start);
            while (!cola.isEmpty()){
                Vertex<E> u = cola.poll();
                u.setVisited(true);
                for (Edge<E> ad : u.getEdges()){
                    Vertex<E> des = ad.getDestino();
                    if (!des.isVisited()){
                        if (des.getDistancia()>u.getDistancia()+ad.getPeso()){
                            des.setDistancia(u.getDistancia()+ad.getPeso());
                            des.setAntecesor(u);
                            cola.offer(des);
                        }
                    }
                }
            }
        }
    }
}
