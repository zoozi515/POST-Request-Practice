package com.example.postrequestpractice

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class UpdateDeleteUser  : AppCompatActivity() {
    lateinit var id_et: EditText
    lateinit var name_et: EditText
    lateinit var loc_et: EditText
    lateinit var update_button: Button
    lateinit var delete_button: Button

    var userID: Int =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.update_delete_user)

        id_et = findViewById(R.id.id_editText)
        name_et = findViewById(R.id.name_editText)
        loc_et = findViewById(R.id.location_editText)

        userID = id_et.text.toString()?.toInt()!!

        update_button = findViewById(R.id.update_button)
        delete_button = findViewById(R.id.delete_button5)

        update_button.setOnClickListener{

            var f = Users.UserDetails(name_et.text.toString(), loc_et.text.toString(), userID)

            updateUser(f, onResult = {
                id_et.setText("")
                name_et.setText("")
                loc_et.setText("")
                Toast.makeText(applicationContext, "Save Success!", Toast.LENGTH_SHORT).show();
            })
        }

        delete_button.setOnClickListener {
            deleteUser(f, onResult = {
                id_et.setText("")
                name_et.setText("")
                loc_et.setText("")
                Toast.makeText(applicationContext, "Save Success!", Toast.LENGTH_SHORT).show();
            })
        }

    }

    fun deleteUser(f: Users.UserDetails, onResult: () -> Unit){
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        apiInterface?.deleteUser(userID)?.enqueue(object : Callback<Void>,
            retrofit2.Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                onResult()
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                onResult()
                Toast.makeText(applicationContext, "Data are not deleted!", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun updateUser(f: Users.UserDetails, onResult: () -> Unit){

        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        apiInterface?.updateUser(userID, Users.UserDetails(
            name_et.text.toString(),
            loc_et.text.toString(),
            userID
        ))?.enqueue(object : retrofit2.Callback<Users.UserDetails> {
            override fun onResponse(
                call: Call<Users.UserDetails>,
                response: Response<Users.UserDetails>
            ) {
                onResult()
            }

            override fun onFailure(call: Call<Users.UserDetails>, t: Throwable) {
                onResult()
                Toast.makeText(applicationContext, "Data are not updated!", Toast.LENGTH_SHORT).show()
            }
        })
    }
}