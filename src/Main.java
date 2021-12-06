import javax.swing.*;

public class Main {
    public static void main(String[] args) {//не передаются аргументы среды
        if (args.length == 0) {
            System.out.println("NO ARGUMENTS");
            System.exit(-1);
        }

        double[] coefficients = new double[args.length];//коэффициенты для горнера
        int i = 0;
        try {
            for (String arg: args) {
                coefficients[i++] = Double.parseDouble(arg);
            }
        } catch (NumberFormatException exception) {//если не число то ошибка
            System.out.println("WRONG ARGUMENTS");
            System.exit(-2);
        }

        GornerTableFrame frame = new GornerTableFrame(coefficients);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}














