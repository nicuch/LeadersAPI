package ro.nicuch.leaders.api;

import ro.nicuch.leaders.enums.RankDataType;

public abstract class RankData {
    private final RankDataType rankDataType;

    public RankData(RankDataType rankDataType) {
        this.rankDataType = rankDataType;
    }

    public final RankDataType getRankDataType() {
        return this.rankDataType;
    }

    public abstract String getDisplayName();

    public abstract String getDisplayValue();

    public abstract String getValue();
}
