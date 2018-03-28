package bakss.computernetworks;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class TestingActionFragment extends Fragment {


    public TestingActionFragment() {
    }

    TestingFragment testingFragment;
    TextView question, questCount;
    Button answer1, answer2, answer3, answer4;

    private static final int VARIANTS = 4;
    private static final int QUESTIONS = 30;
    private static final char DELIMITER = '/';
    int current_right;
    int wrong = 0, right = 0, time = 0, total_time = 30, quest = 0;
    private String[][] AnsMatrix  = new String[VARIANTS][QUESTIONS];
    private int[] RightAnswers = new int[QUESTIONS];
    private String[] Ques = new String[QUESTIONS];
    private TypedArray Base;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView =
                inflater.inflate(R.layout.fragment_testing_action, container, false);

        question = (TextView) rootView.findViewById(R.id.question);
        questCount = (TextView) rootView.findViewById(R.id.questCount);

        answer1 = (Button) rootView.findViewById(R.id.answer1);
        answer2 = (Button) rootView.findViewById(R.id.answer2);
        answer3 = (Button) rootView.findViewById(R.id.answer3);
        answer4 = (Button) rootView.findViewById(R.id.answer4);

        LoadQuestions();
        LoadQuestion();

        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time++;
                quest++;
                wrong++;
                if (current_right == 0) {
                    wrong--;
                    right++;
                }
                if (time==total_time){
                    Stats();
                }
                LoadQuestion();
            }
        });

        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time++;
                quest++;
                wrong++;
                if (current_right == 1) {
                    wrong--;
                    right++;
                }
                if (time == total_time){
                    Stats();
                }
                LoadQuestion();
            }

        });

        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time++;
                quest++;
                wrong++;
                if (current_right == 2) {
                    wrong--;
                    right++;
                }
                if (time==total_time){
                    Stats();
                }
                LoadQuestion();

            }
        });

        answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ++time;
                ++quest;
                wrong++;
                if (current_right == 3) {
                    wrong--;
                    right++;
                }
                if (time==total_time){
                    Stats();
                }
                LoadQuestion();
            }
        });

        return rootView;
    }

    private void LoadQuestions(){
        Resources res = getResources();
        Base = res.obtainTypedArray(R.array.Questions);
        for (int i = 0; i < QUESTIONS; i++){
            Ques[i] = getSubstringBetweenDelimiters(0,1,Base.getString(i));
            for (int j = 0; j < VARIANTS; j++){
                AnsMatrix[j][i] = getSubstringBetweenDelimiters(j + 1,j + 2,Base.getString(i));
            }
            RightAnswers[i]=Integer.parseInt(getSubstringBetweenDelimiters(VARIANTS + 1,VARIANTS + 2,Base.getString(i)));
        }
    }

    private String getSubstringBetweenDelimiters(int k, int m, String str){
        int index1 = 0;
        int index2 = 0;
        int len = str.length();
        int dels = 0;
        for (int i = 0; i < len; i++){
            if (str.charAt(i) == DELIMITER){
                dels++;
            }
            if (dels == k){
                index1 = i;
            }
            if (dels == m){
                index2 = i;
            }
        }
        return str.substring(index1 + 2, index2 + 1);
    }

    private void LoadQuestion() {
        if (time != total_time) {
            questCount.setText("Вопрос " + (time + 1) + " из " + (total_time));
            int qs = quest;
            question.setText(Ques[qs]);
            answer1.setText(AnsMatrix[0][qs]);
            answer2.setText(AnsMatrix[1][qs]);
            answer3.setText(AnsMatrix[2][qs]);
            answer4.setText(AnsMatrix[3][qs]);
            current_right = RightAnswers[qs] - 1;
        }
    }

    private void Stats() {
        double rating=Math.round(((double)right/((double)right+(double)wrong))*100);
        String stat="";
        stat+=getString(R.string.note1);
        stat+=" "+right+" ";
        if (right > 1) {
            stat += getString(R.string.note2);
        } else {
            stat += getString(R.string.note4);
        }
        stat+=" "+total_time+". ";
        stat+=getString(R.string.note3);
        stat+=" "+(rating+"").substring(0,(rating+"").length()-2);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(getString(R.string.result))
                .setMessage(stat)
                .setCancelable(false)
                .setNegativeButton(getString(R.string.accept),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                testingFragment = new TestingFragment();
                                if (getActivity() != null) {
                                    FragmentManager fm = getActivity().getSupportFragmentManager();
                                    Fragment fragment = fm.findFragmentById(R.id.main_frame);
                                    fm.beginTransaction().remove(fragment).commit();
                                    MainActivity ma = (MainActivity) getActivity();
                                    ma.setFragment(testingFragment);
                                }
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

}


