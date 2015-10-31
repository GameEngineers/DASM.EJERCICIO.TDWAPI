package com.example.gestionusuarios;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MostrarUsuarioActivity extends AppCompatActivity {

    TextView tvUserId, tvUserName, tvUserEmail, tvUserCTime, tvUserGroup;
    ImageView ivUserIsActive, ivUserIsAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_usuario);

        // Recuperar usuario
        final Usuario usuario = (Usuario) getIntent().getExtras().getSerializable("usuario");
        Log.i("Mostrar Usuario", usuario.toString());

        // Identificar recursos
        tvUserId       = (TextView)  findViewById(R.id.tvUserId);
        tvUserName     = (TextView)  findViewById(R.id.tvUserName);
        tvUserEmail    = (TextView)  findViewById(R.id.tvUserEmail);
        ivUserIsActive = (ImageView) findViewById(R.id.ivUserIsActive);
        ivUserIsAdmin  = (ImageView) findViewById(R.id.ivUserIsAdmin);
        tvUserCTime    = (TextView)  findViewById(R.id.tvUserCTime);
        tvUserGroup    = (TextView)  findViewById(R.id.tvUserGroup);

        // Asignar valores
        tvUserId.setText(String.valueOf(usuario.getId()));
        tvUserName.setText(usuario.getUsername());
        tvUserEmail.setText(usuario.getEmail());
        ivUserIsActive.setImageResource(usuario.isActive()
                ? android.R.drawable.button_onoff_indicator_on
                : android.R.drawable.button_onoff_indicator_off);
        ivUserIsAdmin.setImageResource(usuario.isAdmin()
                ? android.R.drawable.button_onoff_indicator_on
                : android.R.drawable.button_onoff_indicator_off);
        if (usuario.getCreateTime() != null) {
            tvUserCTime.setText(usuario.getCreateTime().getDate());
        }
        if (usuario.getGroupId() != null) {
            tvUserGroup.setText(usuario.getGroupId().getGroupname());
            tvUserGroup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    StringBuilder txtGrupo = new StringBuilder();
                    txtGrupo.append("Id: " + usuario.getGroupId().getId() + "\n");
                    txtGrupo.append("Nombre: " + usuario.getGroupId().getGroupname() + "\n");
                    txtGrupo.append("Descripción: " + usuario.getGroupId().getDescription());
                    Toast.makeText(getApplicationContext(), txtGrupo.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}
