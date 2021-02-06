package mx.itson.potropickup

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import mx.itson.potropickup.fragments.*


class MainActivity : AppCompatActivity() {
    // [START declare_auth]
    private lateinit var auth: FirebaseAuth
    private var mDatabase: DatabaseReference? = null


    // [END declare_auth]
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        mDatabase = FirebaseDatabase.getInstance().reference;
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<ImagenFragment>(R.id.fragmentImage)
                add<LoginFragment>(R.id.options)
            }
        }
        setContentView(R.layout.activity_main)
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser: FirebaseUser? = auth.currentUser

    }

    fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(R.anim.fragment_open_enter, R.anim.fragment_close_exit)
        transaction.replace(R.id.options, fragment)
        transaction.commit()

    }

    fun ingresar(email: String, password: String) {
        //Checa si estan vacios
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(baseContext, "Porfavor ingrese todo los datos", Toast.LENGTH_SHORT)
                .show()
        } else {
            // Empieza la auteticacion
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val user = auth.currentUser
                        val newFragment = OpcionesFragment()
                        Toast.makeText(baseContext, "Registro exitoso", Toast.LENGTH_SHORT).show()
                        loadFragment(newFragment)
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(
                            baseContext, "Datos incorrectos",
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                }
        }
    }

    fun crearCuenta(email: String, password: String, nombre: String) {
        if (email.isEmpty() || password.isEmpty() || nombre.isEmpty()) {
            //Checa si estan vacios
            Toast.makeText(baseContext, "Porfavor ingrese todo los datos", Toast.LENGTH_SHORT)
                .show()
        } else {
            // Empieza el registro
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val user = auth.currentUser
                        if (user != null) {
                            writeNewUser(user.uid, nombre, email)
                        }
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(
                            baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                }
        }


    }

    //Escribe los datos en la Realtime DB
    fun writeNewUser(userId: String, nombre: String, email: String) {
        mDatabase!!.child("Usuario").child(userId).child("nombre").setValue(nombre)
        mDatabase!!.child("Usuario").child(userId).child("correo").setValue(email)
    }
}