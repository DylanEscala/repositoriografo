/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author Melina Macias
 * @param <E>
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
    
    
    public boolean contains(E data) {
        for (Vertex<E>  v : vertexes){
            if(v.getData().equals(data)){
                return true;
            }
        }
        return false;
    }
    
    public void cleanVertex(){
        for (Vertex<E> v : this.vertexes) {
            v.setVisited(false);
        }
    }
    
    
    
    public List<E> bfs(E inicio){
       List<E> lista = new LinkedList<>();
       Vertex<E> v = searchVertex(inicio);
       if(v == null || this.isEmpty()){
           return lista;
       }
       Queue<Vertex<E>> cola = new LinkedList<>();
       v.setVisited(true);
       cola.offer(v);
       while (!cola.isEmpty()) {
           Vertex<E> vi = cola.poll();
           lista.add(vi.getData());
           for(Edge<E> e : vi.getEdges()){
               if(!e.getDestino().isVisited()){
                   e.getDestino().setVisited(true);
                   cola.offer(e.getDestino());  
                   } 
               }
           }
       cleanVertex();
       return lista;
   }
    
    
    public void Djikstra(Vertex<E> origen){
        
        //Setea las distancias/Antecesores/Visited
        cleanVertexDjkstra();
        origen.setDistancia(0);
       
        PriorityQueue<Vertex<E>> cola = new PriorityQueue<>((Vertex<E> v1, Vertex<E> v2)->v1.getDistancia()-v2.getDistancia());
        cola.offer(origen);
        while (!cola.isEmpty()){
            Vertex<E> u = cola.poll();
            u.setVisited(true);
            for (Edge<E> arco : u.getEdges()){
                Vertex<E> des = arco.getDestino();
                if (!des.isVisited()){
                    if (des.getDistancia()>u.getDistancia()+arco.getPeso()){
                        des.setDistancia(u.getDistancia()+arco.getPeso());
                        des.setAntecesor(u);
                        cola.offer(des);
                    }
                }
            }
        }
    }
    
    
    public List<Vertex<E>> getVertexes() {
        return vertexes;
    }

    public boolean isDirected() {
        return directed;
    }
    
    private void cleanVertexDjkstra(){
        for (Vertex<E> v: vertexes){
                v.setDistancia(Integer.MAX_VALUE);
                v.setAntecesor(null);
                v.setVisited(false);
            } 
    }
    
    public int menorDistancia(E origen, E destino){
        if(origen==null||destino==null)
            return -1;
        Vertex<E> vo= searchVertex(origen);
        Vertex<E> vd= searchVertex(destino);
        if(vd == null || vo == null){
            return -1;
        }
        Djikstra(vo);
        return vd.getDistancia();
        
        
    }
    
    public List<E> caminoMinimo(E origen, E destino){
        List<E> l = new LinkedList<>();
        Vertex<E> vo = this.searchVertex(origen);
        Vertex<E> vd = this.searchVertex(destino);
        if(vo == null || vd == null){
            return l;
        }        
        Djikstra(vo);
        Vertex<E> tmp = vd;
        if(!this.vertexes.contains(tmp)) {   
            System.out.println("No hay camino");
        }
        Deque<Vertex<E>> pila = new LinkedList<>();
        while((tmp != null)) {
            pila.push(tmp);
            tmp = tmp.getAntecesor();
        }
        while(!pila.isEmpty()){
            l.add(pila.pop().getData());
        }
        return l;
    }
    
    
}
