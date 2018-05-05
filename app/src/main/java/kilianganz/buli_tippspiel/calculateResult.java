package kilianganz.buli_tippspiel;

import android.util.Log;

import java.util.ArrayList;

public class calculateResult {
    private int drawPts = 50;
    private int drawTendPts = 25;
    private int defDiffPts = 30;
    private int defTendPts = 20;
    private int defPts = 70;

    //DatabaseHandler-Klasse muss implementiert werden!
    databaseHandler dataHandler;

    private ArrayList<Integer> getTip(String game) {
        ArrayList<Integer> out = dataHandler.getTip(game);
        return out;
    }

    private ArrayList<Integer> getRes(String game) {
        ArrayList<Integer> out = dataHandler.getRes(game);
        return out;
    }


    public int calculatePoints(ArrayList<String> matches) {


        int sumPts = 0;


        for (String match : matches) {

            boolean draw = false;           //Switch for matches with draw

            ArrayList<Integer> tempTip = getTip(match);
            String tipp = "";
            //Was wenn noch keine Ergebnisse vorhanden?
            ArrayList<Integer> tempRes = getRes(match);
            String result = "";
            if (tempTip != null && tempRes != null) {
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
                Log.d("Tipp: ", tipp);
                Log.d("Ergebnis: ", result);

                if (draw) {
                    if (tempTip.get(0) == tempRes.get(0)) {
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



            } else {
                Log.d("Achtung!", "Begegnung hat keinen Tipp oder noch kein Ergebnis!");
            }
        }
        Log.d("Summe: ", "i = " + sumPts);
        return sumPts;
    }
}
