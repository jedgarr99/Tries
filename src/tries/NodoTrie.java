/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tries;

/**
 *
 * @author Jorge Edgar Rodriguez Ortiz Loyola 181334
 */
public class NodoTrie<T> {

    private NodoTrie[] hijos;
    private boolean fin;
    private NodoTrie papa;

    public NodoTrie() {
        hijos = new NodoTrie[26];
        papa = null;
        fin = false;
    }

    public NodoTrie(int num) {
        hijos = new NodoTrie[num];
        fin = false;
        papa = null;
    }

    public NodoTrie[] getHijos() {
        return hijos;
    }

    public void setHijos(NodoTrie[] hijos) {
        this.hijos = hijos;
    }

    public boolean isFin() {
        return fin;
    }

    public void setFin(boolean fin) {
        this.fin = fin;
    }

    public NodoTrie getSig(int pos) {
        return hijos[pos];
    }

    public NodoTrie getPapa() {
        return papa;
    }

    public void setPapa(NodoTrie papa) {
        this.papa = papa;
    }

    public boolean estaVacio() {
        boolean res = true;
        int i = 0;
        while (res && i < hijos.length) {
            if (hijos[i] != null) {
                res = false;
            }
            i++;
        }
        return res;
    }
    public int cantHijos(){
        int cont=0;
        for(int i=0; i<hijos.length;i++){
            if (hijos[i] != null) {
                cont++;
            }
            
        }
        return cont;
    }

}
