package mx.itson.potropickup.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.firebase.ktx.Firebase
import mx.itson.potropickup.MainActivity
import mx.itson.potropickup.R


class LoginFragment : Fragment(R.layout.login_fragment){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)

    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val correo = view.findViewById(R.id.correo) as EditText
        val password = view.findViewById(R.id.password) as EditText
        val btnIngresar = view.findViewById(R.id.btn_ingresar) as Button
        val tvRegistrarse = view.findViewById(R.id.TV_registrarse) as TextView
        val newFragment = RegistroFragment()
        tvRegistrarse.setOnClickListener { (activity as MainActivity).loadFragment(newFragment) }
        btnIngresar.setOnClickListener{(activity as MainActivity).ingresar(correo.text.toString(), password.text.toString())}

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