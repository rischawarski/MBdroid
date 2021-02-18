package mbinfo.com.br.mb_os;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Result extends AppCompatActivity implements View.OnClickListener {

    String telefone = "43-3475-2059";
    TextView rcliente,rservico,rsituacao,rvalor;
    String cliente,servico,situacao,valor;



    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        rcliente = (TextView)findViewById(R.id.rcliente);
        rservico = (TextView)findViewById(R.id.rservico);
        rsituacao = (TextView)findViewById(R.id.rsituacao);
        rvalor = (TextView)findViewById(R.id.rvalor);

        cliente = getIntent().getExtras().getString("nome_cliente");
        servico = getIntent().getExtras().getString("servico_cliente");
        situacao = getIntent().getExtras().getString("situacao_servico");
        valor = getIntent().getExtras().getString("valor_servico");

        rcliente.setText(cliente);
        rservico.setText(servico);
        rsituacao.setText(situacao);
        rvalor.setText("R$ :"+valor);

        final ImageButton btLigar = (ImageButton) findViewById(R.id.btnligar);
        btLigar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        Uri uri = Uri.parse("tel:"+telefone);
        Intent intent = new Intent(Intent.ACTION_DIAL,uri);

        startActivity(intent);
    }
}
