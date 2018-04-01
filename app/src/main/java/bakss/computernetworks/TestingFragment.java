package bakss.computernetworks;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class TestingFragment extends Fragment {

    Button startBtn;
    TestingActionFragment testingActionFragment;

    public TestingFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView =
                inflater.inflate(R.layout.fragment_testing, container, false);

        startBtn = (Button) rootView.findViewById(R.id.startBtn);
        // переход на фрагмент с тестом
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testingActionFragment = new TestingActionFragment();
                if (getActivity() != null) {
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    Fragment fragment = fm.findFragmentById(R.id.main_frame);
                    fm.beginTransaction().remove(fragment).commit();
                    MainActivity ma = (MainActivity) getActivity();
                    ma.setFragment(testingActionFragment);
                }
            }
        });

        return rootView;
    }

}