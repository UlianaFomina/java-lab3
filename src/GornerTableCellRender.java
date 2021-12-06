import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.io.Console;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.stream.IntStream;

public class GornerTableCellRender implements TableCellRenderer {

    private String requiredValue;

    private boolean coolSearch;

    private JPanel panel = new JPanel();
    private JLabel label = new JLabel();

    private DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance();

    {
        formatter.setMaximumFractionDigits(6);//форматируем максимальное количество символов - 6
        formatter.setGroupingUsed(false);
        DecimalFormatSymbols curSymbol = formatter.getDecimalFormatSymbols();
        curSymbol.setDecimalSeparator('.');
        formatter.setDecimalFormatSymbols(curSymbol);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        String formattedValue = formatter.format(value);
        double a = Double.parseDouble(formattedValue);

        label.setText(formattedValue);
        panel.add(label);
        if(a > 0)  panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        else if(a < 0)  panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        else panel.setLayout(new FlowLayout(FlowLayout.CENTER));

        //поиск по значению(выделен красным)
        if (requiredValue != null && requiredValue.equals(formattedValue) && column == 1) panel.setBackground(Color.RED);
        else panel.setBackground(Color.WHITE);

        return panel;
    }

    public void setRequiredValue(String requiredValue) {
        this.requiredValue = requiredValue;
    }

    public void setCoolSearch(boolean coolSearch) {
        this.coolSearch = coolSearch;
    }

    public DecimalFormat getFormatter() {
        return formatter;
    }


}