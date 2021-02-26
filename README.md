

# Tries
#### _Almacena claves ordenadamente_


## Table of contents
* [Información General](#Información-General)
* [Clases](#clases)
   - [Nodo Trie](#Nodo-Trie)
   - [Trie](#Trie)
   -  [Tries](#Tries)
* [Uso](#Uso)
* [Eficiencia](#eficiencia)
* [Estado](#Estado)
* [Contacto](#contacto)

## Información General


Este proyecto incluye las clases necesarias para implementar un Trie. Un Trie es un tipo de **árbol de búsqueda**. El Trie **almacena claves**, es decir, una secuencia de caracteres. Algunas ventajas de implementar un Trie en lugar de utilizar un algoritmo de búsqueda son:

- Buscar información en un Trie es **más rápido que utilizar una tabla imperfecta de Hash**
- Generar funciones de Hash se vuelve innecesario
- Realizar búsquedas  grandes presenta un **crecimiento lineal del tiempo**


[![EjemploTrie](https://upload.wikimedia.org/wikipedia/commons/thumb/b/be/Trie_example.svg/250px-Trie_example.svg.png)](https://upload.wikimedia.org/wikipedia/commons/thumb/b/be/Trie_example.svg/250px-Trie_example.svg.png)

Se incluye una comparación con el algoritmo de ordenamiento Merge Sort para comprender mejor las fortalezas y debilidades de los Tries en metodos de busqueda. 

## Clases 

##### Nodo Trie
Esta clase modela cada elemento o nodo que tendrá nuestro árbol. Como el Trie está diseñado para guardar Strings, es decir cadenas de caracteres, cada nodo tiene por default 26 "hijos". **Un hijo es el siguiente elemento en el árbol**.

Si se desea cambiar el número de hijos, correspondientes a la cantidad de símbolos que se usarán, se puede utilizar el método instanciador NodoTrie.

```java
 public NodoTrie(int num) {
        hijos = new NodoTrie[num];
        fin = false;
        papa = null;
    }
```
La clase Nodo Trie cuenta con varios métodos que brindan información del nodo en cuestión. Algunos de los más útiles son:

* `getHijos()`: Devuelve un array con los hijos actuales del nodo
* `isFin()`: Devuelve un valor Booleano. Si el nodo tiene hijos regresa False
*  `estaVacio()`: Devuelve un valor Booleano que indica si el nodo tiene hijos o no

El método `estaVacio()` se ejemplifica a continuación.

```java
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
```
#### Trie 
Es la **clase principal del proyecto**. Contiene los métodos necesarios para  cargar los símbolos que usaremos en los hijos, insertar elementos, convertir el Trie a una cadena String,  eliminar elementos y obtener el elemento buscado dentro del Trie.
El Trie se debe de instanciar con un arreglo con los símbolos que se usarán y con un nodo que será la raíz de nuestro árbol.  Automáticamente se genera un contador de la cantidad de hijos del nodo.

```java
public Trie(Character[] simbolos) {
        Arrays.sort(simbolos);
        this.simbolos = simbolos.clone();
        raiz = new NodoTrie(simbolos.length);
        cont = 0;
    }
```
#### Tries 
Es el **main del programa**. Aquí se prueban las distintas clases y métodos. Además, se hace una comparación con el método de búsqueda Merge Sort para analizar las ventajas de cada método.
En el siguiente bloque de código de muestra como se  instancia un nuevo Trie:

```java
Character arr[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',};
        Trie<String> t = new Trie(arr);
```
A continuación se lee un archivo para obtener las palabras a ser almacenadas: 
```java
try {
            File ent = new File("C:\\Users\\Edgar\\Desktop\\Tries\\palabras.txt");
            sc = new Scanner(new FileReader(ent));

        } catch (FileNotFoundException e) {
            System.out.println("Input file not found");
            System.exit(1);
        }
```
Después, se obtiene el tiempo que toma  buscar palabras, tanto usando un Trie como un Merge Sort. Para esto, se utiliza el método   `ordenamientoLexicografico()` del Trie y un Merge Sort. Finalmente, se comparan los tiempos.
```java
    res = t.ordenamientoLexicografico();
    TFin = System.currentTimeMillis();
    tiempo = TFin - TInicio;
    System.out.println(tiempo);
```
## Uso
Si deseas ordenar palabras o cadenas de caracteres utilizando un Trie primero debes de **seleccionar los caracteres que aparecerán en tus palabras**. Como cada caracter se almacena en un nodo distinto, es importante notar que si algún caracter está acentuado o alterado, este nuevo caracter deberá ser contado por separado. A continuación, **crea un arreglo con los caracteres elegidos**.

Ahora puedes instanciar un nuevo Trie, introduciendo como parámetro tu arreglo de caracteres. Cada caracter representa un posible hijo que tendrá el nodo. Ya que está el objeto Trie creado, **inserta las cadenas de caracteres o Strings que desees**. Es decir, puedes guardar las palabras que quieras  ordenar.

Finalmente, utiliza el método  `ordenamientoLexicografico()` para generar un **String con todas las palabras que contenga el Trie**, ordenadas alfabéticamente.


[![Diagrama](https://drive.google.com/uc?export=view&id=1XyBkAjd3xubovZppg0pfijX7V2YmvCaR)](https://drive.google.com/uc?export=view&id=1XyBkAjd3xubovZppg0pfijX7V2YmvCaR)
## Eficiencia  

Si se ordena una gran cantidad de claves,  el **tiempo se vuelve un problema a considerar**. Por esto es necesario elegir el algoritmo más eficiente. La siguiente tabla muestra los distintos tiempos al utilizar un Trie y un Merge Sort para ordenar distintas cantidades de palabras.  


Número de Elementos | Trie | Merge Sort
------------ | ------------------------- | -------------
100 | 0,25  |0
1000|15,5|15,75
10000|38,25|19,5
20000|85,75|27,75
30000|105,25|51
40000|129|67
50000|141|92
60000|169,75|105,25
70000|195,5|129
80000|234,5|159,5
90000|245,75|246

Esta tabla nos genera la siguiente gráfica:

[![GraficaDesempeño](https://drive.google.com/uc?export=view&id=1h04fwimTLPGGiymhhyaKL7VjJqPKbExT)](https://drive.google.com/uc?export=view&id=1h04fwimTLPGGiymhhyaKL7VjJqPKbExT)

El algoritmo de Merge Sort es más eficiente, aunque el **Trie presenta un crecimiento más lineal**, lo que podría ser útil en algunos casos. Una posible desventaja de utilizar un Trie, es que no es posible utilizar un Trie como sustituto a todos los algoritmos de ordenación, pues el **Trie solo funciona con elementos compuestos de partes más pequeñas**. En otras palabras, un Trie solo puede ordenar objetos divisibles.


## Estado
El proyecto esta: ***terminado***


## Contacto
Creado por    [@jedgarr99](https://github.com/jedgarr99) 
_¡Contáctame si tienes cualquier duda!_