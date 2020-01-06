package com.example.jesuspc.ejercicio_trees_rayas;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //iniciamos el array casillas que identifica cada casilla y la almacena en l array(ileras)

       CASILLAS=new int[9];

       CASILLAS[0]=R.id.a1;
        CASILLAS[1]=R.id.a2;
        CASILLAS[2]=R.id.a3;
        CASILLAS[3]=R.id.b1;
        CASILLAS[4]=R.id.b2;
        CASILLAS[5]=R.id.b3;
        CASILLAS[6]=R.id.c1;
        CASILLAS[7]=R.id.c2;
        CASILLAS[8]=R.id.c3;
    }



        public void ajugar(View vista){ //este methodo sera para que el tablero quede en blanco antes de iniciar el juego
                                            //vista sera el nombre del argumento a llamar


            ImageView imagen; // imagen en blanco para todas las casillas / nombre de la variable imagen cualquieras ponerle

            for (int Cadacasilla:CASILLAS){   //for para recorrer el array casillas con su id 0,1,2...8 cadacasilla esinventado

                imagen=(ImageView)findViewById((Cadacasilla)); //almacenamos el id de la casilla dento del nombre imagen

                imagen.setImageResource(R.drawable.casilla);//le asigne la casilla en blanco para limpiar el tablero

            }


            jugadores=1;            //podemos haber creado un valor para cada boton pero como usan el mismo para iniciaar

            if (vista.getId()==R.id.dos_jug){  //si vista que es la funcion VIEW se iguala al id jugadores 2 entonces jugadores es =2

                jugadores=2;
            }

            //reconoceremos el radio group para modificar los estatutos de dificultad
            RadioGroup configuraciondificultad=(RadioGroup)findViewById(R.id.config_dificultad);


            int id=configuraciondificultad.getCheckedRadioButtonId(); //almacenamos el id del elemento seleccionado y ponerlo en la variable item

            int dificultad=0;
            if (id == R.id.facil) {

                dificultad=1;

                 }
             else if (id==R.id.imposible){

                dificultad=2;
            }


            partida=new partida(dificultad);

            ((Button)findViewById(R.id.jugador1)).setEnabled(false); //asi hemos inabilitado el boton hasta que se sellecione

            ((RadioGroup)findViewById(R.id.config_dificultad)).setAlpha(0); //este no tiene seenable, pero tiene setalpha para la trasparencia


            ((Button)findViewById(R.id.dos_jug)).setEnabled(false);
        }


        public void toque(View vista_toque){  //pasaremos la vista casilla para que pueda leer la casilla al tocarlas
                                //debemos decirle que recibira por parametro una vista es decir el tipo para leer el click


            if (partida==null){   //si la partida no se ha seleccionado no hace dara y se retorna.

                return;
            }

            int casilla=0;

            for (int i=0;i<9;i++) {       //para i=0 siempre qeu sea menor de 9  aumentar 1



                if (CASILLAS[i]==vista_toque.getId()){   //con esto identificamos la casilla tome un valor i y se identifique

                    casilla=i;      //una vez identificado

                    break;      //salimos con un break para romper el ciclo
                }

            }



           /* Toast nombre_toast=Toast.makeText(this,"casilla "+CASILLAS,Toast.LENGTH_LONG);  //cuadro emergente pequeño
            nombre_toast.setGravity(Gravity.CENTER,0,0);

            nombre_toast.show();*/

           if (partida.comprueba_casilla(casilla)==false){

               return;

           }


           marca(casilla); //jugador 1 marca jugador 1

           int resultado= partida.turno();  //la casilla acciona al jugador 2 y marca una X

            if (resultado>0){ //si el toque es =0 la artida continua, si es mayor que 0 lalma al methodo termina que aun no hemos creado

                termina(resultado); //variable donde el jugador ha ganado

                return;
            }


           partida.ai();  //acciona el random


            while (partida.comprueba_casilla(casilla)!=true){

                casilla=partida.ai();
            }



           marca(casilla);  //retorna al jugdor 1 y volvemos a jugar

          resultado= partida.turno();   //ya se ha cumplido el siclo y regresa al jugador 2


            if (resultado>0){ //si el toque es =0 la artida continua, si es mayor que 0 lalma al methodo termina que aun no hemos creado

                termina(resultado); //variable donde el jugador ha ganado
            }

        }


        private void termina(int resultado2){

        String mensaje;
        if (resultado2==1) mensaje="gana cierculos";
        else if (resultado2==2) mensaje="gana aspas";
        else mensaje="empate";

        Toast nombre_toast=Toast.makeText(this,mensaje,Toast.LENGTH_LONG);  //cuadro emergente pequeño
            nombre_toast.setGravity(Gravity.CENTER,0,0);

            nombre_toast.show();

            partida=null;  //la partida termina y debemos habilitar los botones

            ((Button)findViewById(R.id.jugador1)).setEnabled(true); //set enable =habilitar

            ((Button)findViewById(R.id.dos_jug)).setEnabled(true);

            ((RadioGroup)findViewById(R.id.config_dificultad)).setAlpha(1); //set alpha el methodo ubicado


        }


        private void marca(int casilla){  //asigna la imagen a poner cuando el jugador=1 circulo o=2 aspas

        ImageView imagen_X_O;
        imagen_X_O=(ImageView)findViewById(CASILLAS[casilla]);

        if (partida.jugador==1){

            imagen_X_O.setImageResource(R.drawable.circulo);
        }else {
            imagen_X_O.setImageResource(R.drawable.aspa);
        }

        }

        private int jugadores;

        private int[] CASILLAS;

        private partida partida;


}
