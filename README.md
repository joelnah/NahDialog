# NahDialog

[![](https://jitpack.io/v/joelnah/NahDialog.svg)](https://jitpack.io/#joelnah/NahDialog)

dependencies {
	        implementation 'com.github.joelnah:NahDialog:Tag'
	}
  
  
  
  
class MainActivity : AppCompatActivity() {

    lateinit var dialog: NahDialog
    lateinit var listDialog: NahListDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSingle.setOnClickListener {
            dialog= NahDialog(this)
                .setDialog("single", "Test single dialog."
                    , View.OnClickListener {
                        toast("close")
                        dialog.dismiss()
                    }, View.OnClickListener {
                        toast("ok")
                        dialog.dismiss()
                    })
               .setBtnText(getString(R.string.cancel),getString(R.string.ok))
            dialog.show()
        }

        val list = ArrayList<String>()
        list.add("aaaa")
        list.add("bbbb")
        list.add("cccc")
        list.add("dddd")

        btnList.setOnClickListener {
            listDialog = NahListDialog(this, list)
                .setHeaderTitle("Title")
                .setOnSelectPosition {
                    toast(list[it])
                }
                .setBtnText(getString(R.string.cancel),getString(R.string.ok))
            listDialog.show()
        }

        btnMultiList.setOnClickListener {
            listDialog = NahListDialog(this, list)
                .setHeaderTitle("Title")
                .setMultiChoice(true)
                .setOnMultiSelectPosition {
                    var s = ""
                    for (i in it) {
                        s += "$i : ${list[i]} "
                    }
                    toast(s)
                }
                .setBtnText(getString(R.string.cancel),getString(R.string.ok))
            listDialog.show()
        }

    }
