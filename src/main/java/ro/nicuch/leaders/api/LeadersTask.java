package ro.nicuch.leaders.api;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

public interface LeadersTask extends Runnable {

    @NotNull TaskDescription getTaskDescription();

    @NotNull RankData getRankData(int rank);

    @NotNull Map<Integer, RankData> getRanksData();

}
