package mx.itson.potropickup.models

import android.widget.Toast
import com.google.firebase.database.*
import java.util.*

class Food(
    var Nombre: String = "",
    var fotoURL: String = "",
    var ID: Int = 0,
    var Precio: Int = 0,
    var Descripcion: String = ""
)
