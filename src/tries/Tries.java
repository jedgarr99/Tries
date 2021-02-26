/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tries;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Jorge Edgar Rodriguez Ortiz Loyola 181334
 */
public class Tries {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Character arr[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',};
        Trie<String> t = new Trie(arr);
        Scanner sc = null;
        String[] lista = new String[90000], aux;
        int i = 0;
        boolean ban;
        long TInicio, TFin, tiempo;
        Sort s = new Sort();
        /*t.insertar("a");
        t.insertar("and");
        t.insertar("as");
        t.insertar("be");
        t.insertar("for");
        t.insertar("had");
        System.out.println(t.toString());
         System.out.println(t.getRaiz().getHijos()[0].cantHijos());
        System.out.println(t.busca("a"));
        System.out.println(t.busca("and"));
        System.out.println(t.busca("as"));
        System.out.println(t.busca("ans"));
        System.out.println(t.busca("be"));
        System.out.println(t.busca("for"));
        System.out.println(t.toString());
        
         t = new Trie(arr);*/
        try {
            File ent = new File("C:\\Users\\Edgar\\Desktop\\Tries\\palabras.txt");
            sc = new Scanner(new FileReader(ent));

        } catch (FileNotFoundException e) {
            System.out.println("Input file not found");
            System.exit(1);
        }

        while (i < 90000) {
            String text = sc.nextLine().toLowerCase();
            ban = true;
            /*char[] chars = text.toCharArray();

            for (char c : chars) {
                if (!Character.isLetter(c)) {
                    ban = false;
                }
            }*/
            if ((text != null) && (!text.equals("")) && (text.matches("^[a-zA-Z]*$"))) {
                lista[i] = text;
                i++;
            }

            /*if (ban) {
                lista[i] = text;
                i++;
            }*/
        }

        //System.out.println(Arrays.toString(lista));
        TInicio = System.currentTimeMillis();
        for (int j = 0; j < 60000; j++) {
            t.insertar(lista[j]);
        }
        //System.out.println("j "+100);

        String[] res = t.ordenamientoLexicografico();
        TFin = System.currentTimeMillis();
        //System.out.println(Arrays.toString(res));
        tiempo = TFin - TInicio;
        System.out.println(tiempo);

        t = new Trie(arr);
        TInicio = System.currentTimeMillis();

        for (int j = 0; j < 1000; j++) {
            t.insertar(lista[j]);
        }
        //System.out.println("j "+1000);

        res = t.ordenamientoLexicografico();
        TFin = System.currentTimeMillis();
        //System.out.println(Arrays.toString(res));
        tiempo = TFin - TInicio;
        System.out.println(tiempo);
        t = new Trie(arr);

        for (int k = 1; k < 10; k++) {
            int limit = k * 10000;
            TInicio = System.currentTimeMillis();
            for (int j = 0; j < limit; j++) {
                t.insertar(lista[j]);
            }
            System.out.println("j " + limit);

            res = t.ordenamientoLexicografico();
            TFin = System.currentTimeMillis();
            // System.out.println(Arrays.toString(res));
            tiempo = TFin - TInicio;
            System.out.println(tiempo);
            t = new Trie(arr);

        }
        System.out.println("merge");
        System.out.println("");
        aux = new String[100];
        for (int j = 0; j < 100; j++) {
            aux[j] = lista[j];
        }

        TInicio = System.currentTimeMillis();

        res = (String[]) s.mergeSort(aux);
        TFin = System.currentTimeMillis();
        //System.out.println(Arrays.toString(res));
        tiempo = TFin - TInicio;
        System.out.println(TInicio);
        System.out.println(TFin);
        System.out.println(tiempo);
        t = new Trie(arr);
        aux = new String[1000];

        for (int j = 0; j < 1000; j++) {

            aux[j] = lista[j];
        }
        TInicio = System.currentTimeMillis();
        res = res = (String[]) s.mergeSort(aux);
        TFin = System.currentTimeMillis();
        //System.out.println(Arrays.toString(res));
        tiempo = TFin - TInicio;
        System.out.println(tiempo);
        t = new Trie(arr);

        for (int k = 1; k < 10; k++) {
            int limit = k * 10000;
            aux = new String[limit];
            for (int j = 0; j < limit; j++) {

                aux[j] = lista[j];
            }
            TInicio = System.currentTimeMillis();
            res = res = (String[]) s.mergeSort(aux);
            TFin = System.currentTimeMillis();
            // System.out.println(Arrays.toString(res));
            tiempo = TFin - TInicio;
            System.out.println(tiempo);
            t = new Trie(arr);

        }

        // s.mergeSort(bArray);
    }

}
