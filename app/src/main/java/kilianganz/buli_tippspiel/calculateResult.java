package kilianganz.buli_tippspiel;

import android.util.Log;

import java.util.ArrayList;

public class calculateResult {
    private int drawPts = 5;
    private int drawTendPts = 2;
    private int defDiffPts = 3;
    private int defTendPts = 2;
    private int defPts = 5;
    ArrayList<Integer> tip = new ArrayList<Integer>();

    ArrayList<Integer> res = new ArrayList<Integer>();

    private void getLogVals() {
        tip.add(0);
        tip.add(1);
        res.add(1);
        res.add(0);
    }

    //database xy;
    /*
    private ArrayList<Integer> getTip(String game) {
        ArrayList<Integer> out = xy.getTip(game);
        return out;
    }

    private ArrayList<Integer> getRes(String game) {
        ArrayList<Integer> out = xy.getRes(game);
        return out;
    }
    */

    public int calculatePoints(ArrayList<String> matches) {
        int sumPts = 0;


        for (String match : matches) {
            getLogVals();

            boolean draw = false;           //Switch for matches with draw

            ArrayList<Integer> tempTip = tip;
            //ArrayList<Integer> tempTip = getTip(match);
            String tipp = "";
            ArrayList<Integer> tempRes = res;
            //ArrayList<Integer> tempRes = getRes(match);
            String result = "";

            int tipDif = tempTip.get(0) - tempTip.get(1);
            int resDif = tempRes.get(0) - tempRes.get(1);

            if (tipDif == 0 && resDif == 0) {
                draw = true;
            } else {
                draw = false;
            }

            for (int i = 0; i < tempTip.size(); i++) {
                tipp += tempTip.get(i).toString();
                if (i == 0) {
                    tipp += " - ";
                }
            }
            for (int i = 0; i < tempRes.size(); i++) {
                result += tempRes.get(i).toString();
                if (i == 0) {
                    result += " - ";
                }
            }
            Log.d("Tipp: ", tipp );
            Log.d("Ergebnis: ", result );

            if (draw) {
                if (tempTip.get(0) == tempTip.get(0)) {
                    sumPts += drawPts;
                } else {
                    sumPts += drawTendPts;
                }
            } else {
                if ((tipDif == resDif) && tempTip.get(0) == tempRes.get(0)) {
                    sumPts += defPts;
                } else if (tipDif == resDif && tempTip.get(0) != tempRes.get(0)) {
                    sumPts += defDiffPts;
                } else if (Integer.signum(tipDif) == Integer.signum(resDif)) {
                    sumPts += defTendPts;
                }
            }


        }
        Log.d("Summe: ","i = " + sumPts );
        return sumPts;
    }
}
