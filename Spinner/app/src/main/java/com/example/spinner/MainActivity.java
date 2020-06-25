
package com.example.spinner;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity { /*AppCompatActivity representa una ventana de Android y tiene todos los métodos
                                                        necesarios para crear y mostrar los objetos que hemos dispuesto en el archivo xml.*/

    /*Definimos variables en java donde almacenamos las referencias a los objetos definidos en el archivo XML. */

    EditText edtAnt, edtActual, edtTotalPagar;   /*  - De tipo EditText (estas dos clases se declaran en el paquete android.widget,
                                                         es necesario importar dichas clases para poder definir las variables de dichas clases.
                                                     - El objeto de la clase Button no es necesario definir un atributo, sino que desde el archivo XML inicializaremos la propiedad onClick.*/
    Spinner sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Enlazar las variables con los objetos definidos en el archivo XML, esto se hace llamando al método findViewById:

        edtAnt = (EditText) /* <- Operador cast */ findViewById(R.id.edtConsumoAnterior); /*La clase findViewById retorna una clase de tipo View, por eso usamos el operador Cast para
                                                                                            convertirla a EditText*/
        edtActual = (EditText) findViewById(R.id.edtConsumoActual);
        edtTotalPagar = (EditText) findViewById(R.id.edtTotalAPagar);
        sp = (Spinner) findViewById(R.id.spinner);


        //Creando un ArrayAdapter, el arreglo de cadenas y el layout del Spinner
        ArrayAdapter<CharSequence> dataAdapter = ArrayAdapter.createFromResource(this, R.array.tarifa_prompts, android.R.layout.simple_spinner_item);

        //Especificando el comportamiento del Sppiner
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Aplicando el Adapter al spinner
        sp.setAdapter(dataAdapter);
    }

    /*planteamos el método que se ejecutará cuando se presione el botón (el método debe recibir como parámetro un objeto de la clase View)
     * Debemos ir al archivo XML e inicializar la propiedad onClick del objeto button con el nombre del método*/


    public void calcular2(View v){
        String tarifa = (String) sp.getSelectedItem();
        float total_a_pagar = 0.00f;
        //Validar campos vacios

        String constAnt = edtAnt.getText().toString();
        String constAct = edtActual.getText().toString();

        if(constAnt.equals(" ")){
            Toast.makeText(this, "Indica cual es tu consumo anterior", Toast.LENGTH_LONG).show();
            //Focus en jugar y abrir el teclado

        }else if(constAct.equals(" ")){
            Toast.makeText(this, "Indica cual es tu consumo actual", Toast.LENGTH_LONG).show();
            //Focus en jugar y abrir el teclado

        }else{
            Float consAnt = Float.parseFloat(edtAnt.getText().toString());
            Float consAct = Float.parseFloat(edtActual.getText().toString());

            float consumo_total = (consAct - consAnt) * 1;

            if (consumo_total < 0) {

                edtTotalPagar.setText("0.00");
            }
            else {
                if (tarifa.equals("1")) {
                    Toast.makeText(this, "Has seleccionado la Tarifa " + sp.getSelectedItem(), Toast.LENGTH_LONG).show();
                    if (consumo_total <= 75) {
                        total_a_pagar = (float) (consumo_total *.820);
                    } else if (consumo_total > 75 && consumo_total <=140) {
                        total_a_pagar = (float) (((consumo_total - 75)*.992) + (75 *.820));
                    } else {
                        total_a_pagar = (float) (((consumo_total - 140)*2.901) + (75 *.820) + (65 * .992));
                    }
                } else if (tarifa.equals("1A")) {
                    Toast.makeText(this, "Has seleccionado la Tarifa " + sp.getSelectedItem(), Toast.LENGTH_LONG).show();
                    if (consumo_total <= 75) {
                        total_a_pagar = (float) (consumo_total *.820);
                    } else if (consumo_total > 75 && consumo_total <=150) {
                        total_a_pagar = (float) (((consumo_total - 75)*.992) + (75 *.820));
                    } else {
                        total_a_pagar = (float) (((consumo_total - 150)*2.901) + (75 *.820) + (65 * .992));
                    }
                } else if (tarifa.equals("1B")) {
                    Toast.makeText(this, "Has seleccionado la Tarifa " + sp.getSelectedItem(), Toast.LENGTH_LONG).show();
                    if (consumo_total <= 75) {
                        total_a_pagar = (float) (consumo_total *.820);
                    } else if (consumo_total > 75 && consumo_total <=175) {
                        total_a_pagar = (float) (((consumo_total - 75)*.992) + (75 *.820));
                    } else {
                        total_a_pagar = (float) (((consumo_total - 175)*2.901) + (75 *.820) + (65 * .992));
                    }
                }
                edtTotalPagar.setText( Float.toString(total_a_pagar));
            }

        }
    }


}
