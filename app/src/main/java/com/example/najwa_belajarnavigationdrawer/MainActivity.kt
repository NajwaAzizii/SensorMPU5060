//package com.example.najwa_belajarnavigationdrawer
//
//import android.os.Bundle
//import android.view.MenuItem
//import android.widget.Toast
//import androidx.appcompat.app.ActionBarDrawerToggle
//import androidx.appcompat.app.AppCompatActivity
//import androidx.fragment.app.FragmentTransaction
//import com.example.najwa_belajarnavigationdrawer.R
//import com.example.najwa_belajarnavigationdrawer.databinding.ActivityMainBinding
//import com.google.android.material.navigation.NavigationView
//
//class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
//
//    private lateinit var binding: ActivityMainBinding
//    private lateinit var toggle: ActionBarDrawerToggle
//
//    private lateinit var inboxFragment: HalamanInbox
//    private lateinit var draftFragment: HalamanDraft
//    private lateinit var sendFragment: HalamanSend
//    private lateinit var settingFragment: HalamanSetting
//    private lateinit var helpFragment: HalamanHelp
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        toggle = ActionBarDrawerToggle(
//            this, binding.drawerlayout, R.string.open, R.string.close
//        )
//        binding.drawerlayout.addDrawerListener(toggle)
//        toggle.syncState()
//
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        binding.navView.setNavigationItemSelectedListener(this)
//    }
//
//    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.inbox -> {
//                inboxFragment = HalamanInbox()
//                supportFragmentManager.beginTransaction()
//                    .replace(R.id.frame_layout, inboxFragment)
//                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                    .commit()
//                Toast.makeText(applicationContext, "Ini Halaman Inbox", Toast.LENGTH_SHORT).show()
//            }
//
//            R.id.draft -> {
//                draftFragment = HalamanDraft()
//                supportFragmentManager.beginTransaction()
//                    .replace(R.id.frame_layout, draftFragment)
//                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                    .commit()
//                Toast.makeText(applicationContext, "Ini Halaman Draft", Toast.LENGTH_SHORT).show()
//            }
//
//            R.id.send -> {
//                sendFragment = HalamanSend()
//                supportFragmentManager.beginTransaction()
//                    .replace(R.id.frame_layout, sendFragment)
//                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                    .commit()
//                Toast.makeText(applicationContext, "Ini Halaman Send", Toast.LENGTH_SHORT).show()
//            }
//
//            R.id.setting -> {
//                settingFragment = HalamanSetting()
//                supportFragmentManager.beginTransaction()
//                    .replace(R.id.frame_layout, settingFragment)
//                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                    .commit()
//                Toast.makeText(applicationContext, "Ini Halaman Setting", Toast.LENGTH_SHORT).show()
//            }
//
//            R.id.help -> {
//                helpFragment = HalamanHelp()
//                supportFragmentManager.beginTransaction()
//                    .replace(R.id.frame_layout, helpFragment)
//                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                    .commit()
//                Toast.makeText(applicationContext, "Ini Halaman Help", Toast.LENGTH_SHORT).show()
//            }
//        }
//        binding.drawerlayout.closeDrawers()
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if (toggle.onOptionsItemSelected(item)) {
//            return true
//        }
//        return super.onOptionsItemSelected(item)
//    }
//}
