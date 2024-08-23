package MiniJava.codeGenerator;
public class Indirect implements TypeAddress {

    @Override
    public String toString(int number) {
        return "@" + number;
    }
}