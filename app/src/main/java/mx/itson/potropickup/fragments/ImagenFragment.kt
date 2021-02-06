package mx.itson.potropickup.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import mx.itson.potropickup.R

class ImagenFragment : Fragment(R.layout.imagen_fragment) {
    // [START declare_auth]
    var image: Int = R.drawable.logo
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageView = getView()?.findViewById<ImageView>(R.id.image)
        if (imageView != null) {
          Glide.with(this).load(image).into(imageView)
        }
    }

}