package com.ball_game.app.util;
import static java.util.Map.entry;
import java.util.*;

public final class BackgroundConverter {
    
    private static Map<String, RGBTuple> table = Map.ofEntries(
        entry("dark_blue", new RGBTuple(50,58,135)),
        entry("white", new RGBTuple(255,255,255))
    );

    public static RGBTuple getRGBTuple(String background){
        return table.get(background);
    }
}
