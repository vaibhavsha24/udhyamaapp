package com.example.udhyamaapp.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import com.example.udhyamaapp.Adpaters.CategoryAdapter
import com.example.udhyamaapp.Adpaters.SubCategoryAdapter
import com.example.udhyamaapp.Category
import com.example.udhyamaapp.ItemDecoration
import com.example.udhyamaapp.R
import com.example.udhyamaapp.subCategory
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_sub_category.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class SubCategory : AppCompatActivity() {

private lateinit var database:FirebaseDatabase
   private  lateinit  var  myRef:DatabaseReference
        var  subCategoryAdapter:SubCategoryAdapter?=null
    var subcategorylayout: GridLayoutManager?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_category)
        setSupportActionBar(toolbarsub)
//        setTitle("Gro Bazar")

        var action = supportActionBar
        action!!.setDisplayShowTitleEnabled(false)
        action!!.setDisplayHomeAsUpEnabled(true)
//        action!!.setLogo(R.drawable.gro)
        var subcategorylist = ArrayList<subCategory>()

        database = FirebaseDatabase.getInstance()
        myRef = database.getReference("vaibhav")
        var key=""
        var catname:String?=null
        if (intent.extras != null)
        {
            key=intent.getStringExtra("key")
            catname=intent.getStringExtra("category")
            println("category name in subcategory is "+catname)
        }
        println(key)
        var ref=myRef.child("detail").child("Category").child(key).child("subCategory")

        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                println("erooro takes")
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {


                for (snapshot in dataSnapshot.children)
                {
                    var category=snapshot.getValue(subCategory::class.java)

                    var categoryobject=subCategory()

                    if(category!=null)
                    {
                        categoryobject.name=category.name
                        categoryobject.image=category.image
                        categoryobject.categoryname=catname
                        categoryobject.key=snapshot.key
                        subcategorylist!!.add(categoryobject)
                        subcategoryrecycler.adapter!!.notifyDataSetChanged()

                    }



                }
            }
        })
    subCategoryAdapter= SubCategoryAdapter(subcategorylist)
        subcategorylayout= GridLayoutManager(this,3)


        subcategoryrecycler.adapter=subCategoryAdapter
        subcategoryrecycler.layoutManager=subcategorylayout

        subcategoryrecycler.addItemDecoration(ItemDecoration(30))


    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()

        return true
    }
}
