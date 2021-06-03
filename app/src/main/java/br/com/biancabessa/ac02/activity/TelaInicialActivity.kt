package br.com.biancabessa.ac02.activity

import android.Manifest
import android.app.AlertDialog
import android.app.SearchManager
import android.content.Intent
import android.content.pm.PackageManager
import android.database.MatrixCursor
import android.net.Uri
import android.os.Bundle
import android.provider.BaseColumns
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.appcompat.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.app.ActivityCompat
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.biancabessa.ac02.R
import br.com.biancabessa.ac02.`object`.ProdutoPrefs
import br.com.biancabessa.ac02.`object`.ProdutoService
import br.com.biancabessa.ac02.adapter.ProdutosAdapterRV
import br.com.biancabessa.ac02.model.ProdutoClasse
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_tela_inicial.*
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.toolbar.*

class TelaInicialActivity : DebugActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var produtos = listOf<ProdutoClasse>()

    fun toast(message: String?) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)

        val nomeShared = ProdutoPrefs.getString("nomedoproduto")
        Toast.makeText(this, "valor em SharedPreferences: $nomeShared", Toast.LENGTH_LONG).show()

        setSupportActionBar(toolbar)
        supportActionBar?.title = "Inicio"

        configurarMenuLateral()

        recycle_prod?.layoutManager = LinearLayoutManager(this)
    }

    override fun onResume() {
        super.onResume()
        Thread {
            produtos = ProdutoService.getProdutosDB()

            runOnUiThread {
                recycle_prod?.adapter = ProdutosAdapterRV(produtos) {
                    onClickProduto(it)
                }
            }
        }.start()
    }

    fun onClickProduto(produto: ProdutoClasse){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Remover")
        builder.setMessage("Você deseja remover o produto?")
        builder.setPositiveButton("Remover"){dialog, which ->
            Thread {
                ProdutoService.deleteProdutoDB(produto)

                runOnUiThread {
                    val intent = Intent(this, TelaInicialActivity::class.java)
                    intent.putExtra("produto", produto)
                    startActivity(intent)
                }
            }.start()
        }
        builder.setNeutralButton("Cancelar"){_,_ ->
            Toast.makeText(this,"Você cancelou a ação.",Toast.LENGTH_SHORT).show()
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    //Função de busca na activity
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val searchView = menu?.findItem(R.id.action_buscar)?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                toast(query)
                if (query == null || query.equals("")){
                    recycle_prod?.adapter = ProdutosAdapterRV(produtos) {
                        onClickProduto(it)
                    }
                }

                var prodlist = produtos.filter {
                    x -> x.nome.equals(query)
                }

                recycle_prod?.adapter = ProdutosAdapterRV(prodlist) {
                    onClickProduto(it)
                }

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                if (newText == null || newText.equals("")){
                    recycle_prod?.adapter = ProdutosAdapterRV(produtos) {
                        onClickProduto(it)
                    }
                }
                else {
                    var prodlist = produtos.filter { x ->
                        x.nome.contains(newText!!)
                    }

                    recycle_prod?.adapter = ProdutosAdapterRV(prodlist) {
                        onClickProduto(it)
                    }
                }

                return true
            }

        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if(id == R.id.action_add){
            Toast.makeText(this, "Clicou adicionar", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, TelaAddProdActivity::class.java)
            startActivity(intent)
        }

        else if(id == R.id.action_buscar){
            Toast.makeText(this, "Clicou pesquisar", Toast.LENGTH_SHORT).show()

        }

        else if (id == android.R.id.home){
            finish()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun configurarMenuLateral(){
        var toogle = ActionBarDrawerToggle(
            this,
            layout_menu_lateral,
            toolbar,
            R.string.nav_open,
            R.string.nav_close
        )
        layout_menu_lateral.addDrawerListener(toogle)
        toogle.syncState()

        nav_menu_lateral.setNavigationItemSelectedListener(this)
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.item_mapa -> {
                val intent = Intent(this, MapasActivity::class.java)
                Toast.makeText(this, "Maps", Toast.LENGTH_SHORT).show()
                startActivity(intent)
            }

            R.id.nav_opcao02 -> {
                if (ActivityCompat.checkSelfPermission( this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "11988962650"))
                    startActivity(intent)
                }else{
                    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), 1)
                }
            }

            R.id.nav_opcao03 -> {
                val intent = Intent(this, MainActivity::class.java)
                Toast.makeText(this, "Login", Toast.LENGTH_SHORT).show()

                startActivity(intent)
            }
        }
        layout_menu_lateral.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            1 -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission( this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "11988962650"))
                    startActivity(intent)
                }
            }
        }
    }
}