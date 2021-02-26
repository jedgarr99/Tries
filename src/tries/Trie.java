/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tries;

import java.util.Arrays;

/**
 *
 * @author Jorge Edgar Rodriguez Ortiz Loyola 181334
 */
public class Trie<T> {

    private NodoTrie raiz;
    protected Character[] simbolos;
    private int cont, pos;

    public Trie(Character[] simbolos) {
        Arrays.sort(simbolos);
        this.simbolos = simbolos.clone();
        raiz = new NodoTrie(simbolos.length);
        cont = 0;
    }

    public Character[] getSimbolos() {
        return simbolos;
    }

    public void setSimbolos(Character[] simbolos) {
        this.simbolos = simbolos;
    }

    public boolean busca(String llave) {
        return busca(llave, raiz);
    }

    private boolean busca(String llave, NodoTrie actual) {
        if (actual == null) {
            return false;
        }
        if (llave.compareTo("") == 0) {
            return actual.isFin();
        }
        int pos = buscaCar(llave.charAt(0));

        return busca(llave.substring(1), actual.getSig(pos));
    }

    public NodoTrie buscaFin(String llave) {
        return buscaFin(llave, raiz);
    }

    private NodoTrie buscaFin(String llave, NodoTrie actual) {
        if (actual == null) {
            return null;
        }
        if (llave.compareTo("") == 0) {
            return actual;
        }
        int pos = buscaCar(llave.charAt(0));

        return buscaFin(llave.substring(1), actual.getSig(pos));
    }

    public void insertar(String pal) {
        if (!busca(pal)) {
            insertar(pal, raiz);
        }
    }
    /*NodoTrie currentNode = raiz;
        
        for (int i = 0; i < word.length(); i++) {
            NodoTrie node = currentNode.getHijo()[word.charAt(i) - 'a'];
            if (node == null) {
                node = new NodoTrie();
                currentNode.getHijo()[word.charAt(i) - 'a'] = node;
            }
            currentNode = node;
        }
        currentNode.setFin(true); */

    private void insertar(String llave, NodoTrie actual) {
        
        if (llave.compareTo("") == 0) {
            actual.setFin(true);
            cont++;
            return;
        }
        int pos = buscaCar(llave.charAt(0));
        NodoTrie temp = actual.getSig(pos);
        if (temp == null) {
            temp = new NodoTrie(simbolos.length);
            temp.setPapa(actual);
            actual.getHijos()[pos] = temp;
        }
        insertar(llave.substring(1), temp);
    }

    public boolean eliminar(String llave) {
        boolean res = false, ban;
        NodoTrie act = buscaFin(llave);
        ban = act != null;
        if (ban && llave != null && !llave.equals("")) {
            act.setFin(false);
            ban = act.estaVacio();
            System.out.println("ban" + ban);
            res = !ban;
            if (ban) {
                res = eliminar(llave, act.getPapa());
            }
        }
        if (res) {
            cont--;
        }
        return res;
    }

    private boolean eliminar(String llave, NodoTrie actual) {
        if (actual == null) {
            return false;
        }
        int pos = buscaCar(llave.charAt(llave.length() - 1));
        actual.getHijos()[pos] = null;
        if (actual.isFin()) {
            return true;
        }
        if (!actual.estaVacio()) {
            return true;
        }
        if (actual.getPapa() == null) {
            return true;
        }
        return eliminar(llave.substring(0, llave.length() - 1), actual.getPapa());
    }

    private int buscaCar(Character c) {
        int i = 0;
        while (i < simbolos.length && simbolos[i].compareTo(c) != 0) {
            i++;
        }
        if (i == simbolos.length) {
            throw new RuntimeException("No existe el simbolo");
        }
        return i;
    }
    /*public void inorderTraversal(NodoTrie node, String word) {
    // handle end of word
    
    if (node.value != 0) {
        System.out.println(word + node.character + ": " + node.value);
    }

    if(node.left != null) {
        inorderTraversal(node.left, word);
    }

    if (node.middle != null) {
        inorderTraversal(node.middle, word + node.character);
    }

    if(node.right != null) {
        inorderTraversal(node.right, word);
    }
}

public void traverse() {
    inorderTraversal(raiz, "");
}*/
    public void display() {
    char[] pal = new char[50];
            display(raiz,pal, 0 );
    }
  void display(NodoTrie root, char[] str, int level) 
{ 
    // If node is leaf node, it indicates end 
    // of string, so a null character is added 
    // and string is displayed 
    if (root.isFin())  
    { 
        str[level] = '\0'; 
        System.out.println(str);
    } 
  
    int i; 
    for (i = 0; i < root.getHijos().length; i++)  
    { 
        // if NON NULL child is found 
        // add parent key to str and 
        // call the display function recursively 
        // for child node 
        if (root.getHijos()[i]!=null)  
        { 
            Character car = simbolos[i];
            str[level] = car; 
            display(root.getHijos()[i], str, level + 1); 
        } 
    } 
} 

    public String toString() {
        StringBuilder cad = new StringBuilder();
        NodoTrie<T> act = raiz;
        String res="";
        //for(int i=0; i<act.getHijos().length;i++){
            res = toString(act, cad, "");
       // }
     
        return res;
    }

    private String toString(NodoTrie actual, StringBuilder cad, String pal) {
        if (actual == null) {
            return cad.toString();
        }
        if (actual.isFin()) {
            cad.append(pal);
            cad.append("\n");
        }
        NodoTrie[] arr = actual.getHijos();
        
        for (int i = 0; i < actual.getHijos().length; i++) {
            

            if (arr[i] != null) {
               String aux=pal;
                Character car = simbolos[i];
                aux += car;
                toString(arr[i], cad, aux);
            }
        }
        return cad.toString();
    }

    public NodoTrie getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoTrie raiz) {
        this.raiz = raiz;
    }

    public String[] ordenamientoLexicografico() {
        int pos = 0;
        String[] res = new String[cont];
        StringBuilder cad = new StringBuilder();
        NodoTrie<T> act = raiz;
        ordenamientoLexicografico(act, cad, "", res);
        return res;
    }

    private String[] ordenamientoLexicografico(NodoTrie actual, StringBuilder cad, String pal, String[] llaves) {
        if (actual == null) {
            return llaves;
        }
        if (actual.isFin()) {
            llaves[pos] = pal;
            pos++;
        }
        for (int i = 0; i < actual.getHijos().length; i++) {
            NodoTrie[] arr = actual.getHijos();

            if (arr[i] != null) {
                String aux=pal; 
                Character car = simbolos[i];
                aux += car;
                ordenamientoLexicografico(arr[i], cad, aux, llaves);
            }
        }
        return llaves;
    }

}
