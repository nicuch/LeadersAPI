package ro.nicuch.leaders.api;

import java.util.Map;

public interface LeadersTask extends Runnable {

    TaskDescription getTaskDescription();

    RankData getRankData(int rank);

    Map<Integer, RankData> getRanksData();
}
