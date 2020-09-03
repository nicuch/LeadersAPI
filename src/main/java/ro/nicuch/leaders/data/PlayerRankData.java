package ro.nicuch.leaders.data;

import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ro.nicuch.leaders.api.RankData;
import ro.nicuch.leaders.enums.RankDataType;

public class PlayerRankData extends RankData {
    private final String displayName;
    private final String displayValue;
    private final String value;
    private final OfflinePlayer player;

    public PlayerRankData(@NotNull String displayName, @NotNull String displayValue, @NotNull String value, @Nullable OfflinePlayer player) {
        super(RankDataType.PLAYER);
        this.displayName = displayName;
        this.displayValue = displayValue;
        this.value = value;
        this.player = player;
    }

    @Override
    public @NotNull String getDisplayName() {
        return this.displayName;
    }

    @Override
    public @NotNull String getDisplayValue() {
        return this.displayValue;
    }

    @Override
    public @NotNull String getValue() {
        return this.value;
    }

    @Nullable
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
