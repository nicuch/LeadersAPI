package ro.nicuch.leaders.api;

import org.jetbrains.annotations.NotNull;
import ro.nicuch.leaders.enums.RankDataType;

public abstract class RankData {
    private final RankDataType rankDataType;

    public RankData(@NotNull RankDataType rankDataType) {
        this.rankDataType = rankDataType;
    }

    @NotNull
    public final RankDataType getRankDataType() {
        return this.rankDataType;
    }

    @NotNull
    public abstract String getDisplayName();

    @NotNull
    public abstract String getDisplayValue();

    @NotNull
    public abstract String getValue();
}
