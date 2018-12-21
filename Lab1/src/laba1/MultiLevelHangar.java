package laba1;

import java.util.ArrayList;

public class MultiLevelHangar {
    ArrayList<Hangar<IFighter>> hangarStages;

    private final int countPlaces = 20;

    public MultiLevelHangar(int countStages, int pictureWidth, int pictureHeight) {
        hangarStages = new ArrayList<Hangar<IFighter>>();
        for (int i = 0; i < countStages; ++i)
        {
            hangarStages.add(new Hangar<IFighter>(countPlaces, pictureWidth, pictureHeight));
        }
    }

    public Hangar<IFighter> getAt(int index) {
        if (index > -1 && index < hangarStages.size()) {
            return hangarStages.get(index);
        }

        return null;
    }
}
