package com.example.jesuspc.ejercicio_trees_rayas;

import java.util.Random;

public class partida {


    public partida(int dificultad){


        this.dificultad=dificultad;

        jugador=1;

        casillla=new int[9];        //con este codigo al iniciar la partida las casillas adoptan un valor=0

        for (int i=0;i<9;i++){


            casillla[i]=0;
        }




    }



    public boolean comprueba_casilla(int casilla){     //publico para acceder desde otor, buleano verdadero o falso , y toma un valor de tipo entero tipo casilla

     if (casillla[casilla]!=0){   //si casillas de casilla es diferetne de 0

         return false;

        } else{casillla[casilla]=jugador; //rellenamos la casilla de casilla con jugador o o jug 2


        }

        return true;

    }

    public int turno(){

        boolean ulti_movimiento=true;
        boolean empate=true;



        for (int i=0;i<COMBINACIONES.length;i++){  //este ciclo me permite recorrer por la longitud del array y ver los 24 numeros de cada grupo por separado

            for (int pos:COMBINACIONES[i]){ //pos-nombre  variable de tipo entero, con este puedo ver la dimencion de cada posicion del array combinacion

                System.out.print("valor de la posicion" + pos+""+casillla[pos]);

                if (casillla[pos]!=jugador)ulti_movimiento=false; //el contenido de la posicion es diferente al jugador

                if (casillla[pos]==0){  //

                    empate=false;
                }

            } //cierre for anidado  quien evalua en grupos de 3

            System.out.print("........................................................................................");

            if (ulti_movimiento)return jugador; //esto me dice que me retorne quien ha hecho triky

            ulti_movimiento=true;

        }//cierre for principal


        if (empate){   //si empate es verdadero regresa un 3
            return 3;
        }

        jugador++;
        if (jugador>2){

            jugador=1;
        }

        return 0;
    }


    public int dosenraya(int jugador_en_turno){  //methodo para buscar la casilla faltante



        int casilla_faltante=-1;   //valor que tomara la casilla, se iguala a cualquier numero que no estemos usando

        int cuantas_llevo=0;    //este contador lo igualamos a 0 para cuando valga 2 se active

        for (int i=0;i<COMBINACIONES.length;i++){   //codigo para recorrer el array

            for (int pos:COMBINACIONES[i]){      //recorre el sub array grupos de 3

                if (casillla[pos]==jugador_en_turno) cuantas_llevo++; //casilla de posicion es igual al jugador en turno, osea se pulso O, de ser asi aumenta las cuentas
                if (casillla[pos]==0)casilla_faltante=pos; //casillas de por es igual a 0, osea donde no se ha pulsado.


            }

            if (cuantas_llevo==2 && casilla_faltante!=-1)return casilla_faltante; //condiciones contador=2 y casilla faltante es diferente del valor inicial, en caso d eser verdad retornar casilla calve

            casilla_faltante=-1; //reiniciamos los valores iniciales si no se cumplen las condiciones
            cuantas_llevo=0;


        }

        return -1;
    }


    public int ai(){

        int casilla;

        casilla=dosenraya(2);  //cone ste codigo instruimos que cuando sea el turno de la maquina, si estoy apunto de hacer tryky esta lo sepa y ponga la X en el espacio d ela casilla


        if (casilla!=-1) return casilla; // si la casilla es =-1  del valor de la casilla faltante, reornar el valor de la casilla

        if (dificultad>0){

            casilla=dosenraya(1);  //aquí se refiere al jugador 1

            if (casilla!=-1)return casilla; //al retornar la casilla  se cierra el codigo y no continua buscando un numero alazar en el random
        }
        if (dificultad==2){  //si no hay manera de hacer triky  y se ha elegido la dificultad imposible=2


            if (casillla[0]==0)return 0; //ej si empezamos marcando el centro casilla4, la aplicacioon siemrpe marcara la casilla 0
            if (casillla[2]==0)return 2;
            if (casillla[6]==0)return 6;
            if (casillla[8]==0)return 8;
        }





       Random casilla_azar=new Random();   //creamos la instancia casilla azar

        casilla=casilla_azar.nextInt(9);   //la intancia viaha a el enetero casilla regresando un valor entero
        return casilla;

    }



    public final int dificultad;


    public int jugador;

    private int [] casillla;

    private final int[][] COMBINACIONES={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
}
