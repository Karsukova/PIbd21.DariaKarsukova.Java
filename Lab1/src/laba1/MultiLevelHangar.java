package laba1;
import java.util.*;
import java.io.*;
import java.awt.*;
public class MultiLevelHangar {
        ArrayList<Hangar<IFighter>> hangarStages;
        private final int countPlaces = 15;
        private int pictureWidth;
        private int pictureHeight;
        public MultiLevelHangar(int countStages, int pictureWidth, int pictureHeight)
        {   this.pictureWidth = pictureWidth;
            this.pictureHeight = pictureHeight;
            hangarStages = new ArrayList<Hangar<IFighter>>();
            for (int i = 0; i < countStages; ++i)
            {
                hangarStages.add(new Hangar<IFighter>(countPlaces, pictureWidth, pictureHeight));
            }
        }
        public Hangar<IFighter> get(int index)
        {
            if (index > -1 && index < hangarStages.size()) {
                return hangarStages.get(index);
            }
            return null;
        }
    public boolean saveData(String filename) {
        File file = new File(filename);
        if (file.exists()) {
            file.delete();
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            writeToFile("CountLeveles:" + hangarStages.size() + System.lineSeparator(), bw);
            for (Hangar level : hangarStages) {
                writeToFile("Level" + System.lineSeparator(), bw);
                for (int i = 0; i < countPlaces; i++) {
                	IFighter plane = level.getTrasport(i);
                    if (plane != null) {
                        if (plane.getClass().getSimpleName().equals("Airplane")) {
                            writeToFile(i + ":Airplane:", bw);
                        }
                        if (plane.getClass().getSimpleName().equals("FighterPlane")) {
                            writeToFile(i + ":FighterPlane:", bw);
                        }
                        writeToFile(plane + System.lineSeparator(), bw);
                    }
                }
            }
            return true;
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
    }

    public boolean loadData(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            return false;
        }
        String bufferTextFromFile = "";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                bufferTextFromFile += line + "\n";
            }
            String[] strs = bufferTextFromFile.split("\n");
            if (strs[0].contains("CountLeveles")) {
                //считываем количество уровней
                int count = Integer.parseInt(strs[0].split(":")[1]);
                if (hangarStages != null) {
                    hangarStages.clear();
                }
                hangarStages = new ArrayList<Hangar<IFighter>>(count);
            } else {
                //если нет такой записи, то это не те данные
                return false;
            }
            int counter = -1;
            IFighter pl = null;
            for (int i = 1; i < strs.length - 1; ++i) {
                //идем по считанным записям
                if (strs[i].equals("Level")) {
                    //начинаем новый уровень
                    counter++;
                    hangarStages.add(new Hangar<IFighter>(countPlaces, pictureWidth, pictureHeight));
                    continue;
                }
                if (strs[i].isEmpty() || strs[i] == null) {
                    continue;
                }
                if (strs[i].split(":")[1].equals("Airplane")) {
                    pl = new Airplane(strs[i].split(":")[2]);
                } else if (strs[i].split(":")[1].equals("FighterPlane")) {
                    pl = new FighterPlane(strs[i].split(":")[2]);
                }
                hangarStages.get(counter).setTrasport(Integer.parseInt(strs[i].split(":")[0]), pl);
            }
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    private void writeToFile(String text, BufferedWriter writer) {
        try {
            char[] info = text.toCharArray();
            writer.write(info, 0, info.length);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    }