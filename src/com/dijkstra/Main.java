package com.dijkstra;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Integer> izquierda=new ArrayList<>();
        List<Integer> derecha=new ArrayList<>();
        List<Integer> pesos=new ArrayList<>();
        int[][] matrizA;

        int contador=0;
        int[] nodes;
        try {
            File file = new File("C:\\Users\\Joseph\\Documents\\IntelliJ\\Dijkstra\\Grafo_ponderado.txt");
            Scanner myReader = new Scanner(file);
            System.out.println("Obteniendo datos del archivo:");
            while (myReader.hasNextLine()) {
                contador++;
                String data = myReader.nextLine();
                System.out.println(data);
                if (contador==1) returnNodes(data);
                if (contador==4){
                    izquierda=returnLeft(data);
                    derecha=returnRigth(data);
                    pesos=returnWeight(data);


                }

                int i=2;






            }
            myReader.close();
            int size=izquierda.size();
            int i=0;
            int iterador=0;
            while(i<size) {
                //System.out.println(izquierda.get(i) + " "+ derecha.get(i)+" "+pesos.get(i));
                i++;
            }
           matrizA=matrizDeAdyacencia(izquierda,derecha,pesos,20);
           imprimir(matrizA);
           Dijkstra t = new Dijkstra();
           int[] l=t.dijkstra(matrizA, 0,14);
            System.out.println("Resultado al nodo 14:");
           while (iterador<l.length) {
                             System.out.print(l[iterador] + "  ");
                             iterador++;
           }


        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
    static int[][] llenarMatriz(int nodos){
        int[][] matriz=new int[nodos][nodos];

        for (int i = 0; i < nodos; i++) {
            for (int j = 0; j <nodos; j++) {
                matriz[i][j]=0;
            }

        }

        return matriz;
    }
    static void imprimir(int[][] matriz){
        int fila=matriz.length;
        int columna=matriz[1].length;
        for (int i=0;i<20;i++) {
            System.out.print(i+"\t");
        }
        System.out.println(" ");
        for (int i = 0; i < fila; i++) {
            for (int j = 0; j < columna; j++) {
                System.out.print(matriz[i][j]+"\t");
            }
            System.out.print(i+"\t");
            System.out.println(" ");
        }
    }
    static int[][] matrizDeAdyacencia(List<Integer> listaleft,List<Integer> listaRight,List<Integer> listapeso,int numNodos){

        int[][] matrizAdj=llenarMatriz(numNodos);
        int size=listaleft.size();

        int left=0;
        int right=0;
        for (int i = 0; i < size; i++) {
            left=listaleft.get(i);
            right=listaRight.get(i);
            matrizAdj[left][right]=listapeso.get(i);
            matrizAdj[right][left]=listapeso.get(i);

        }


        return matrizAdj;
    }
    public static List<Integer> returnNodes(String data){
        int size=data.length();
        int indx=0;
        int principio=0;
        int finDelString=0;
        List<Integer> lista=new ArrayList<>();

        while (indx<size){
            if (data.charAt(indx)==' ') {
                principio = indx;
            }
            if (data.charAt(indx)==',') {
                finDelString = indx;
                lista.add(Integer.valueOf(data.substring(principio,finDelString).trim()));
                //System.out.println(data.substring(principio,finDelString));
            }
            indx++;
            }
          return lista;
        }
    public static List<Integer> returnLeft(String data){
        int size=data.length();
        int indx=0;
        int principio=0;
        int finDelString=0;
        int flag=0;
        List<Integer> lista=new ArrayList<>();

        while (indx<size){
            if (data.charAt(indx)=='(') {
                principio = indx+1;
                flag=1;
            }
            if (data.charAt(indx)==',' && flag==1) {
                finDelString = indx;
                lista.add(Integer.valueOf(data.substring(principio,finDelString).trim()));
               // System.out.println(data.substring(principio,finDelString));
                flag=0;
            }
            indx++;
        }

        return lista;
    }
    public static List<Integer> returnRigth(String data){
        int size=data.length();
        int indx=0;
        int principio=0;
        int finDelString=0;
        int flag=0;
        int contador=0;
        List<Integer> lista=new ArrayList<>();

        while (indx<size){
            if (data.charAt(indx)=='(') {

                flag=1;
            }
            if (data.charAt(indx)==',' && flag==1) {
                contador++;

                if (contador==1)principio = indx+2;
                if (contador==2){
                    finDelString = indx;
                    lista.add(Integer.valueOf(data.substring(principio,finDelString).trim()));
                    //System.out.println(Integer.valueOf(data.substring(principio,finDelString).trim()));
                    contador=0;
                    flag=0;
                }
            }
            indx++;
        }
        return lista;
    }
    public static List<Integer> returnWeight(String data){
        int size=data.length();
        int indx=0;
        int principio=0;
        int finDelString=0;
        int flag=0;
        int contador=0;
        List<Integer> lista=new ArrayList<>();

        while (indx<size){
            if (data.charAt(indx)==':') {
                principio = indx+1;
                flag=1;
            }
            if (data.charAt(indx)=='}' && flag==1) {
                finDelString = indx;
                lista.add(Integer.valueOf(data.substring(principio,finDelString).trim()));
                //System.out.println(data.substring(principio,finDelString));
                flag=0;
            }
            indx++;
        }
        return lista;
    }






    }
