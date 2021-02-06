package mx.itson.potropickup.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import mx.itson.potropickup.MainActivity
import mx.itson.potropickup.R

class OpcionesFragment : Fragment(R.layout.opciones_fragment) {
    val user = Firebase.auth.currentUser
    val mDatabase = FirebaseDatabase.getInstance().reference.child("Usuario").child(user!!.uid)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val ordenarFragment = MenuFragment()
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val nombre = snapshot.child("nombre").getValue().toString()
                val usuario = view?.findViewById(R.id.TV_user) as TextView
                usuario.text = nombre
            }

            override fun onCancelled(error: DatabaseError) {

            }

        }
        mDatabase.addValueEventListener(listener)
        val btnOrdenar = view.findViewById(R.id.btn_menu) as Button
        val btnCarrito = view.findViewById(R.id.btn_comprar) as Button
        val btnCuenta = view.findViewById(R.id.btn_cuenta) as Button
        btnOrdenar.setOnClickListener { (activity as MainActivity).loadFragment(ordenarFragment) }
        //btnCarrito.setOnClickListener { (activity as MainActivity).loadFragment(newFragment) }
        //btnCuenta.setOnClickListener { (activity as MainActivity).loadFragment(newFragment) }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
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