package laba1;

public class HangarNotFoundException extends Exception {
    public HangarNotFoundException(int index){
        super("�� ������ ������� �� �����: "+index);
    }
}