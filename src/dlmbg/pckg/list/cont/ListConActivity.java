package dlmbg.pckg.list.cont;

import java.util.Arrays;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListConActivity extends Activity {

	private String[] Distro = { "Ubuntu", "Arch Linux", "Mandriva",
			"Open Suse", "IGOS Nusantara", "Linux Mint", "Debian", "Fedora",
			"CrunchBang", "Backtrack", "Puppy Linux", "OpenBSD", "Slackware",
			"BlankOn", "CentOS" };
	private String[] pilihan_menu = { "Tambah Data", "Edit Data", "Hapus Data",
			"Kirim Data" };

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Arrays.sort(Distro);

		ListView list = (ListView) findViewById(R.id.list);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, Distro);
		list.setAdapter(adapter);
		registerForContextMenu(list);
	}

	public void onCreateContextMenu(ContextMenu menu, View tampil,
			ContextMenuInfo menuInfo) {
		if (tampil.getId() == R.id.list) {
			AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
			menu.setHeaderTitle(Distro[info.position]);
			for (int i = 0; i < pilihan_menu.length; i++) {
				menu.add(Menu.NONE, i, i, pilihan_menu[i]);
			}
		}
	}

	public boolean onContextItemSelected(MenuItem item) {
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
				.getMenuInfo();
		String aksi = pilihan_menu[item.getItemId()];
		String nama_pilihan = Distro[info.position];
		String isi = String.format("Anda melakukan operasi %s pada pilihan %s",
				aksi, nama_pilihan);

		Toast.makeText(this, isi, Toast.LENGTH_LONG).show();
		return true;
	}
}