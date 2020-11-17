package cl.desafiolatam.multipleintent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private Boolean resultadoImagen = false;
    private ImageView imagenView;
    private Button cargarImagenBtn;
    private Button siguienteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imagenView = findViewById(R.id.imagen_view);
        cargarImagenBtn = findViewById(R.id.btnCargarImagen);
        siguienteBtn = findViewById(R.id.btnSiguiente);

        //Botón Cargar Imagen
        cargarImagenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tomarFoto();
            }
        });

        //Botón Siguiente
        siguienteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                siguienteConDatos();
            }
        });
    }

    private void tomarFoto() {
        Intent fotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (fotoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(fotoIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

  @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imagenBitmap = (Bitmap) extras.get("data");
            imagenView.setImageBitmap(imagenBitmap);
            resultadoImagen = true;
        } else {
            resultadoImagen = false;
        }
    }

    private void siguienteConDatos() {
        Intent sendIntent = new Intent(this, ResultActivity.class);
        sendIntent.putExtra("url_desafiolatam", "https://www.desafiolatam.com/");
        //sendIntent.putExtra("resultado_imagen", resultadoImagen);
        sendIntent.putExtra("resultado_imagen", resultadoImagen);
        startActivity(sendIntent);
    }
}