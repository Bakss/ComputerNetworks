package bakss.computernetworks;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.*;
import android.view.inputmethod.InputMethodManager;
import android.webkit.*;
import android.widget.*;

import java.lang.reflect.Method;

public class DisplayFragment extends Fragment {

    WebView webView;
    final static String resourceDir = "file:///android_asset/HTML/";
    String fName;
    Integer lectionID;
    TextView title, matchesCount;
    ImageButton btnClose, btnSearch, searchPrev, searchNext, searchClose;
    RelativeLayout searchBar, toolBar;
    EditText searchEdit;
    LectionsFragment lectionsFragment;

    public DisplayFragment() {
        this.setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = rootView = inflater.inflate(R.layout.fragment_display, container, false);

        toolBar = (RelativeLayout) rootView.findViewById(R.id.toolBar);
        searchBar = (RelativeLayout) rootView.findViewById(R.id.searchBar);
        searchBar.setVisibility(View.GONE);

        Bundle bundle = getArguments();
        if (bundle != null) {
            String fileName = bundle.getString("fileName");
            Integer id = bundle.getInt("lectionID");
            if (fileName != null){
                fName = fileName;
                lectionID = id;
            }
        }

        title = (TextView) rootView.findViewById(R.id.title);
        title.setText(getString(R.string.lection) + " " + (lectionID + 1));

        webView = (WebView) rootView.findViewById(R.id.webView);
        webView.loadUrl(resourceDir + fName);
        btnClose = (ImageButton) rootView.findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lectionsFragment = new LectionsFragment();
                if (getActivity() != null) {
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    Fragment fragment = fm.findFragmentById(R.id.main_frame);
                    fm.beginTransaction().remove(fragment).commit();
                    MainActivity ma = (MainActivity) getActivity();
                    ma.setFragment(lectionsFragment);
                }
            }
        });

        btnSearch = (ImageButton) rootView.findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchBar.setVisibility(View.VISIBLE);
                toolBar.setVisibility(View.GONE);
            }
        });

        searchEdit = (EditText) rootView.findViewById(R.id.searchEdit);
        searchEdit.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if((event.getAction() == KeyEvent.ACTION_DOWN) && ((keyCode == KeyEvent.KEYCODE_ENTER))){
                    HideKeyboard();
                    webView.findAll(searchEdit.getText().toString());
                    try{
                        Method m = WebView.class.getMethod("setFindIsUp", Boolean.TYPE);
                        m.invoke(webView, true);
                    }catch(Exception ignored){}

                }
                return false;
            }
        });
        searchNext = (ImageButton) rootView.findViewById(R.id.searchNext);
        searchNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.findNext(true);
            }
        });
        searchPrev = (ImageButton) rootView.findViewById(R.id.searchPrev);
        searchPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.findNext(false);
            }
        });
        matchesCount = (TextView) rootView.findViewById(R.id.matchesCount);
        webView.setFindListener(new WebView.FindListener() {
            @Override
            public void onFindResultReceived(int activeMatchOrdinal, int numberOfMatches, boolean isDoneCounting) {
                matchesCount.setText("");
                if (numberOfMatches != 0) {
                    matchesCount.setText((activeMatchOrdinal + 1) + " из " + numberOfMatches);
                } else {
                    searchEdit.setText("");
                    matchesCount.setText("");
                    Toast toast = Toast.makeText(getContext(),getString(R.string.search_nothing),Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        searchClose = (ImageButton) rootView.findViewById(R.id.searchClose);
        searchClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HideKeyboard();
                webView.clearMatches();
                searchEdit.setText("");
                matchesCount.setText("");
                searchBar.setVisibility(View.GONE);
                toolBar.setVisibility(View.VISIBLE);
            }
        });

        return rootView;
    }

    private void HideKeyboard(){
        InputMethodManager inputManager = (InputMethodManager)
                getActivity().getSystemService(getContext().INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(searchEdit.getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

}
