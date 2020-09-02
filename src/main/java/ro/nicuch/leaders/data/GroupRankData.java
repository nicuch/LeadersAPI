package ro.nicuch.leaders.data;

import org.bukkit.OfflinePlayer;
import ro.nicuch.leaders.api.RankData;
import ro.nicuch.leaders.enums.RankDataType;

public class GroupRankData extends RankData {
    private final String displayName;
    private final String displayValue;
    private final String value;
    private final OfflinePlayer player;

    public GroupRankData(String displayName, String displayValue, String value, OfflinePlayer player) {
        super(RankDataType.GROUP);
        this.displayName = displayName;
        this.displayValue = displayValue;
        this.value = value;
        this.player = player;
    }

    @Override
    public String getDisplayName() {
        return this.displayName;
    }

    @Override
    public String getDisplayValue() {
        return this.displayValue;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    public OfflinePlayer getPlayer() {
        return this.player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupRankData that = (GroupRankData) o;
        return this.value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }
}
