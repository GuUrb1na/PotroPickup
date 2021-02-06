package mx.itson.potropickup.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import mx.itson.potropickup.MainActivity
import mx.itson.potropickup.R
import mx.itson.potropickup.models.Food
import mx.itson.potropickup.models.FoodAdapter
import java.util.ArrayList

class MenuFragment: Fragment(R.layout.menu_fragment), FoodAdapter.OnItemClickListener {
    //Lista que contendra a el menu de comida que sera pasada al Adapter para renderizar
    var  ComidaList : ArrayList<Food> = ArrayList<Food>()
    //Ruta de la cual sacaremos el Menu
    val rootRef = FirebaseDatabase.getInstance().reference.child("Comiditas")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Extracciones de datos de la ruta rootRef
        val poollistener = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                //Esta es la lista que fue extraida de la base de datos, contiene el menu
                val t: GenericTypeIndicator<List<Food?>?> = object : GenericTypeIndicator<List<Food?>?>() {}
                //Pasamos todos los datos de la lista original a la lista que fue declarada globalmente
                ComidaList = snapshot.getValue(t) as ArrayList<Food>
                //Ejecutamos la rendirizacion del reciclador
                llenarReciclador()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }
        //Asignamos todoo lo que acaba hacer el bloque anterior a la ruta que declaramos de la base de datos que contiene el menu
        rootRef.addValueEventListener(poollistener)


    }

    //Este metodo renderiza el reciclador
    fun llenarReciclador(){
        //Por alguna razon las listas de RealTimeDatabase regresan un item basura a el principio de la lista y se tiene que eliminar
        //para que no se junton con los items de Food
        ComidaList.removeAt(0)
        //Instancion del reciclador y cambios esteticos
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView?.addItemDecoration(DividerItemDecoration(context,DividerItemDecoration.VERTICAL))
        recyclerView?.hasFixedSize()
        //Asignacion de layout al reciclador
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView?.layoutManager = layoutManager
        //Asignacion de adapter al reciclador
        val adapter = FoodAdapter(ComidaList, this)
        recyclerView?.adapter = adapter
    }

    override fun onItemClick(position: Int) {
        val productoFragment = ProductoFragment()
        Toast.makeText(context, "item $position clikeado", Toast.LENGTH_SHORT).show()
        val bundle = Bundle()
        bundle.putInt("Orden",position)
        productoFragment.arguments = bundle
        (activity as MainActivity).loadFragment(productoFragment)
    }
}