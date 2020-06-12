package com.example.parcialcorte3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import static android.widget.Toast.LENGTH_SHORT;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    EditText titulo,nombre,descripcion;
    Spinner marcadores;
    Button guardar, mostrar;
    LatLng punto;
    private GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_maps );
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById( R.id.map );
        mapFragment.getMapAsync( this );
        titulo = (EditText) findViewById( R.id.titulo );
        nombre = (EditText) findViewById( R.id.nombre );
        descripcion = (EditText) findViewById( R.id.descripcion );
        marcadores = (Spinner) findViewById( R.id.marcadores );
        guardar = (Button) findViewById( R.id.guardar );
        mostrar = (Button) findViewById( R.id.mostrar );


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng aux = new LatLng( 11.004327, -74.823624 );
        mMap.addMarker(new MarkerOptions().position( aux ).title("Autonoma del caribe"));
        mMap.moveCamera( CameraUpdateFactory.newLatLngZoom( aux, 16 ) );
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                mMap.clear();
                punto = latLng;
                mMap.addMarker( new MarkerOptions().position( punto ).title("Nuevo marcador") );
                mMap.animateCamera( CameraUpdateFactory.newLatLngZoom( punto,15 ) );
                titulo.setEnabled( true );
                nombre.setEnabled(true);
                descripcion.setEnabled(true);
                guardar.setEnabled( true );
            }
        });
        mostrar();

    }
    public void guardar(View view){
        Marcador marcador = new Marcador(titulo.getText().toString().trim(),nombre.getText().toString(),descripcion.getText().toString(), punto.latitude,punto.longitude);
        marcador.ingresar( this );
        titulo.setText( "" );
        titulo.setEnabled( false );
        nombre.setEnabled( false );
        descripcion.setEnabled( false );
        guardar.setEnabled( false );
        mostrar();
    }
    private void mostrar() {
        marcadores.setAdapter( new Marcador().obtenerMarcadores( this ) );
    }

    public void mostrarMarcador(View view){
        mMap.clear();
        Marcador m = (Marcador) marcadores.getSelectedItem();
        punto = new LatLng( m.getLatitud(),m.getLongitud() );
        Toast.makeText(MapsActivity.this, "Nro de pista: "+m.getTitulo()+"\nNombre: "+m.getNombre()+"\nDescripcion: "+m.getDescripcion()+"\nLatitud: "+m.getLatitud()+"\nLongitud: "+m.getLongitud()+"", LENGTH_SHORT).show();
        mMap.addMarker( new MarkerOptions().position( punto ).title( m.getNombre() ) );
        mMap.animateCamera( CameraUpdateFactory.newLatLngZoom( punto, 20 ) );
    }



    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


}