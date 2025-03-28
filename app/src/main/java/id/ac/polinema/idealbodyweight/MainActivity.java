package id.ac.polinema.idealbodyweight;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import id.ac.polinema.idealbodyweight.fragment.AboutFragment;
import id.ac.polinema.idealbodyweight.fragment.BodyMassFragment;
import id.ac.polinema.idealbodyweight.fragment.BrocaIndexFragment;
import id.ac.polinema.idealbodyweight.fragment.MenuFragment;
import id.ac.polinema.idealbodyweight.fragment.ResultFragment;

import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements MenuFragment.OnFragmentInteractionListener,
		BrocaIndexFragment.OnFragmentInteractionListener, ResultFragment.OnFragmentInteractionListener,
		BodyMassFragment.OnFragmentInteractionListener{

	// Deklarasikan atribut Fragment di sini
	private AboutFragment aboutFragment;
	private MenuFragment menuFragment;
	private BrocaIndexFragment brocaIndexFragment;
	private BodyMassFragment bodyMassFragment;
	private ResultFragment resultFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		aboutFragment = AboutFragment.newInstance("Ahmad Musyadad Aminullah");
		menuFragment = new MenuFragment();
		brocaIndexFragment = new BrocaIndexFragment();
		resultFragment = new ResultFragment();
		bodyMassFragment = new BodyMassFragment();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, menuFragment).commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu, menu);
		return  true;
	}

	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item) {
		// TODO: Tambahkan penanganan menu di sini
		if (item.getItemId() == R.id.menu_about) {
			getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, aboutFragment).addToBackStack(null).commit();
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onBrocaIndexButtonClicked() {
		getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, brocaIndexFragment, "BrocaIndex").		addToBackStack(null).commit();
	}

	@Override
	public void onBodyMassIndexButtonClicked() {
		getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, bodyMassFragment, "BodyMassIndex").addToBackStack(null).commit();
	}

	@Override
	public void onCalculateBrocaIndexClicked(float index) {
		resultFragment.setInformation(String.format("Your ideal weight is %.2f kg", index));
		getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, resultFragment).commit();
	}

	@Override
	public void onTryAgainButtonClicked(String tag) {
		if (tag.equalsIgnoreCase("BrocaIndex")){
			getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, brocaIndexFragment).commit();
		} else {
			getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, bodyMassFragment).commit();
		}
	}

	@Override
	public void onCalculateBodyMassIndex(float index, String classific) {
		resultFragment.setInformation(String.format("Your BMI is %.2f kg \r\n" + classific, index));
		getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, resultFragment).commit();
	}
}
