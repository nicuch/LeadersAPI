package ro.nicuch.leaders.data;

import org.bukkit.OfflinePlayer;
import ro.nicuch.leaders.api.RankData;

public class PlayerRankData implements RankData {
    private final String displayName;
    private final String displayValue;
    private final String value;
    private final OfflinePlayer player;

    public PlayerRankData(String displayName, String displayValue, String value, OfflinePlayer player) {
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
        PlayerRankData that = (PlayerRankData) o;
        return this.player.equals(that.player);
    }

    @Override
    public int hashCode() {
        return this.player.hashCode();
    }
}
