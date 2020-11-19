package br.com.pedrohenriquemoura.conhecendoretrofit2;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import br.com.pedrohenriquemoura.conhecendoretrofit2.utils.Mask;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText edt_cep;
    private TextView txt_data;
    private Retrofit retrofit;
    final String URL_BASE = "http://api.postmon.com.br/v1/cep/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setUpRetrofit();
    }

    private void initViews() {
        edt_cep = (EditText) findViewById(R.id.edt_cep);
        txt_data = (TextView) findViewById(R.id.txt_data);

        edt_cep.addTextChangedListener(Mask.insert(Mask.CEP_MASK, edt_cep));
    }

    private void setUpRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public void returnData(View v) {
        String cep = edt_cep.getText().toString();
        Services service = retrofit.create(Services.class);
        Call<Cep> call = service.getAddress(cep);

        txt_data.setText("Buscando informações...");

        call.enqueue(new Callback<Cep>() {
            @Override
            public void onResponse(Call<Cep> call, Response<Cep> response) {
                if (response.isSuccessful()) {
                    Cep cep = response.body();

                    String strCep = "Cidade: " + cep.getCidade() + " " +
                            "\nEstado: " + cep.getEstado() + " " +
                            "\nBairro: " + cep.getBairro() + " " +
                            "\nLogradouro: " + cep.getLogradouro();
                    txt_data.setText(strCep);
                }
            }

            @Override
            public void onFailure(Call<Cep> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Ocorreu um erro na requisição!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}