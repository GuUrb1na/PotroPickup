package mx.itson.potropickup.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import mx.itson.potropickup.MainActivity
import mx.itson.potropickup.R

class RegistroFragment : Fragment(R.layout.registro_fragment) {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val correo = view.findViewById(R.id.correo) as EditText
        val password = view.findViewById(R.id.password) as EditText
        val nombre = view.findViewById(R.id.nombre) as EditText
        val btnIngresar = view.findViewById(R.id.btn_registrarse) as Button
        val tvRegistrarse = view.findViewById(R.id.TV_ingresar) as TextView
        val newFragment = LoginFragment()
        tvRegistrarse.setOnClickListener { (activity as MainActivity).loadFragment(newFragment) }
        btnIngresar.setOnClickListener{(activity as MainActivity).crearCuenta(correo.text.toString(), password.text.toString(), nombre.text.toString())}
    }

    override fun onPause() {
        super.onPause()
    }


    override fun onResume() {
        super.onResume()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}