package mbinfo.com.br.mb_os;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class Tela_inicial extends AppCompatActivity implements View.OnClickListener {

    String url = "";
    String parametros ="";
    TextView txtos;
    Button ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        txtos = (TextView)findViewById(R.id.ost);
        ok = (Button)findViewById(R.id.ver);
        ok.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            String osc = txtos.getText().toString();
            if (osc.isEmpty()){
                Toast.makeText(getApplicationContext(), "Por Favor Digite uma OS", Toast.LENGTH_SHORT).show();
            }else{
                //ip para conexao com endereço remoto para webservice
                url = "http://192.168.1.210:8010/webservice/lista.php";
                parametros = "rand="+ osc ;
               // Toast.makeText(getApplicationContext(), url+ parametros, Toast.LENGTH_SHORT).show();
                new  solicitaDados().execute(url);
            }


        } else {
            Toast.makeText(getApplicationContext(), "Nenhuma Coneção com internet detectada", Toast.LENGTH_SHORT).show();
        }

    }

    private class  solicitaDados extends AsyncTask<String, Void ,String> {
        @Override
        protected String doInBackground(String...urls){
            //try{
            return Conecta.postDados(urls[0],parametros);
            //}catch (IOException e){
            //   return "Seila";
            // }
        }
        @Override
        protected void onPostExecute(String resultado){
            if (resultado.contains("ok")){
                String[] dados = resultado.split(",");
                Intent abreResultado = new Intent(Tela_inicial.this,Result.class);
                abreResultado.putExtra("nome_cliente",dados[1]);
                abreResultado.putExtra("servico_cliente",dados[2]);
                abreResultado.putExtra("situacao_servico",dados[3]);
                abreResultado.putExtra("valor_servico",dados[4]);
                startActivity(abreResultado);
            }else {
                Toast.makeText(getApplicationContext(), "Nenhuma OS Encontrada!", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
