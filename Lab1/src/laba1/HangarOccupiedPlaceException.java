package laba1;

public class HangarOccupiedPlaceException extends Exception {
    public HangarOccupiedPlaceException(int index){
        super("�� ����� "+ index+ "��� ����� �������");
    }
}
