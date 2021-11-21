package Main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


class Main extends JFrame {
    // Константы с исходным размером окна приложения
    private static final int WIDTH = 500;
    private static final int HEIGHT = 400;

    public Main(){
        super("Вычисление значения функции"); // вызываем конструктор предка
        // в конструктор предка мы передаем заглавие
        setSize(WIDTH, HEIGHT); // устанавливаем размеры окна

        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - WIDTH) / 2,  // Отцентрировать окно приложения на экране
                (kit.getScreenSize().height - HEIGHT) / 2);

        JTextField textPar = new JTextField("0", 5);
        // Установить максимальный размер равный предпочтительному, чтобы
        // предотвратить увеличение размера поля ввода
        textPar.setMaximumSize(
                new Dimension(2 * textPar.getPreferredSize().width,
                        textPar.getPreferredSize().height));

        Box hboxParValue = Box.createHorizontalBox(); // контейнер с горизонтальной укладкой
        hboxParValue.add(Box.createHorizontalGlue());
        hboxParValue.add(new JLabel("P:"));
        hboxParValue.add(Box.createHorizontalStrut(10));
        hboxParValue.add(textPar);
        hboxParValue.add(Box.createHorizontalStrut(10));

        JButton Plus = new JButton("+");
        Plus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {

                    Double P = Double.parseDouble(textPar.getText());
                    textPar.setText(Double.toString(P + 1));
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(),
                            "Ошибка!!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        hboxParValue.add(Plus);
        hboxParValue.add(Box.createHorizontalStrut(10));

        JButton Minus = new JButton("-");
        Minus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    Double P = Double.parseDouble(textPar.getText());
                    textPar.setText(Double.toString(P - 1));
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(),
                            "Ошибка!!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        hboxParValue.add(Minus);
        hboxParValue.add(Box.createHorizontalGlue());

        Box Func = Box.createHorizontalBox();
        Func.add(Box.createHorizontalGlue());
        Func.add(new JLabel("Формула f(x) = (P − 1) ∗ X"));
        Func.add(Box.createHorizontalGlue());

        JTextField textFieldX = new JTextField("0", 5);
        // Установить максимальный размер равный предпочтительному, чтобы
        // предотвратить увеличение размера поля ввода
        textFieldX.setMaximumSize(
                new Dimension(2 * textFieldX.getPreferredSize().width,
                        textFieldX.getPreferredSize().height));

        // Создать метку для вывода значения функции
        JLabel labelY = new JLabel("");
        labelY.setMinimumSize(textFieldX.getMaximumSize());  // т е минимальной строкой для у будет являться максимальная для х
        labelY.setPreferredSize(textFieldX.getPreferredSize()); // те предпочтительно для н является предпочтительное для х

        Box hboxXValue = Box.createHorizontalBox();

        hboxXValue.add(Box.createHorizontalGlue());
        hboxXValue.add(new JLabel("X:"));
        hboxXValue.add(Box.createHorizontalStrut(10));
        hboxXValue.add(textFieldX);
        hboxXValue.add(Box.createHorizontalGlue());
        // Установить максимальный размер равный предпочтительному, чтобы
        // предотвратить увеличение размера поля ввода
        hboxXValue.setMaximumSize(
                new Dimension(hboxXValue.getMaximumSize().width,
                        hboxXValue.getPreferredSize().height));


        Box hboxYValue = Box.createHorizontalBox();
        hboxYValue.add(Box.createHorizontalGlue());
        hboxYValue.add(new JLabel("Y:"));
        hboxYValue.add(Box.createHorizontalStrut(10));
        // Добавить метку для вывода значения Y
        hboxYValue.add(labelY);
        hboxYValue.add(Box.createHorizontalGlue());
        // Установить максимальный размер равный предпочтительному, чтобы
        // предотвратить увеличение размера поля ввода
        hboxYValue.setMaximumSize(
                new Dimension(hboxYValue.getMaximumSize().width,
                        hboxYValue.getPreferredSize().height));

        JButton buttonCalc = new JButton("Вычислить");
        buttonCalc.setAlignmentX(CENTER_ALIGNMENT);
        // Задать действие на нажатие "Вычислить" и привязать к кнопке
        buttonCalc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    //в parseDouble parseDouble содержится исключение NumberFormatException
                    Double X = Double.parseDouble(textFieldX.getText());
                    Double P = Double.parseDouble(textPar.getText());
                    labelY.setText(Double.toString((P - 1) * X));

                } catch (NumberFormatException e) {    //
                    JOptionPane.showMessageDialog(null, e.getMessage(),
                            "Ошибка!!", JOptionPane.ERROR_MESSAGE);

                }
            }
        });

        Box vboxParam = Box.createVerticalBox();
        vboxParam.setBorder(BorderFactory.createTitledBorder("Параметры функции:"));
        vboxParam.add(Box.createVerticalGlue());
        vboxParam.add(hboxParValue);
        vboxParam.add(Box.createVerticalStrut(30));
        vboxParam.add(Func);
        vboxParam.add(Box.createVerticalStrut(30));
        // добавляем вертикальный клей
        vboxParam.add(Box.createVerticalGlue());
        //чтобы при увеличении окна по вертикали окно не изменялось
        vboxParam.setMaximumSize(new Dimension(vboxParam.getMaximumSize().width, 100));
        getContentPane().add(vboxParam);
        Box Knop = Box.createHorizontalBox();
        Knop.add(Box.createHorizontalGlue());
        Knop.add(buttonCalc);

        Knop.add(Box.createHorizontalGlue());

        Box vboxCalculator = Box.createVerticalBox();
        // Задать для контейнера тип рамки c заголовком
        vboxCalculator.setBorder(BorderFactory.createTitledBorder("Расчет значения функции:"));
        vboxCalculator.add(Box.createVerticalGlue());
        vboxCalculator.add(hboxXValue);
        vboxCalculator.add(Box.createVerticalStrut(50));
        vboxCalculator.add(hboxYValue);
        vboxCalculator.add(Box.createVerticalStrut(10));
        vboxCalculator.add(Knop);
        vboxCalculator.add(Box.createVerticalGlue());
        getContentPane().add(vboxCalculator);

        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
    }


    public static void main(String[] args) throws Exception {

        // Создать экземпляр главного окна
        Main frame = new Main();
        // Задать действие, выполняемое при закрытии окна
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Показать главное окно приложения
        frame.setVisible(true);
    }
}

