package bakss.computernetworks;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView mainNav;
    FrameLayout mainFrame;

    AboutFragment aboutFragment;
    LectionsFragment lectionsFragment;
    TestingFragment testingFragment;
    GlossaryFragment glossaryFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainFrame = (FrameLayout) findViewById(R.id.main_frame);
        mainNav = (BottomNavigationView) findViewById(R.id.main_nav);
        // создаем фрагменты
        aboutFragment = new AboutFragment(); // фрагмент о программе
        lectionsFragment = new LectionsFragment(); // фрагмент с лекциями
        testingFragment = new TestingFragment(); // фрагмент с тестированием
        glossaryFragment = new GlossaryFragment(); // фрагмент с глоссарием

        // задаем фрагмент если нету сохраненных данных после уничтожения activity
        if (savedInstanceState == null) {
            setFragment(aboutFragment);
        }
        // задаем фрагмент в зависимости от выбранного пункта меню
        mainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.about:
                        setFragment(aboutFragment);
                        return true;

                    case R.id.lections:
                        setFragment(lectionsFragment);
                        return true;

                    case R.id.dictionary:
                        setFragment(glossaryFragment);
                        return true;

                    case R.id.testing:
                        setFragment(testingFragment);
                        return true;

                    default:
                        return false;
                }
            }
        });

    }

    // функция задающая фрагмент
    public void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        // диалоговое окно при нажатии на кнопку "назад"
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(getString(R.string.exit))
                .setMessage(getString(R.string.exit_desc))
                .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton(getString(R.string.no), null)
                .show();
    }

}
