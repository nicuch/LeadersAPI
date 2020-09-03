package ro.nicuch.leaders.settings;

import ro.nicuch.leaders.LeadersPlugin;

public class TaskLoader {
    private static boolean loaded = false;

    public static void load(LeadersPlugin plugin) {
        if (loaded)
            return;
        // code goes here
        loaded = true;
    }

    public static void unload() {
        if (!loaded)
            return;
        // code goes here
        loaded = false;
    }
}
