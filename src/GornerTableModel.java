import javax.swing.table.AbstractTableModel;

public class GornerTableModel extends AbstractTableModel {

    private double[] coefficients;
    private double xBeg;
    private double xEnd;
    private double step;

    public GornerTableModel(double xBeg, double xEnd, double step, double[] coefficients) {
        this.xBeg = xBeg;
        this.xEnd = xEnd;
        this.step = step;
        this.coefficients = coefficients;
    }

    @Override
    public int getRowCount() {
        return (int) (Math.ceil((xEnd - xBeg) / step) + 1);
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    private double getResult(double result, double x, double[] coefficients){
        int i = coefficients.length - 1;
        result = coefficients[i--];
        while (i >= 0) {
            result = result * x + coefficients[i--];
        }
        return result;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        double x = xBeg + rowIndex * step;
        double result = 0;
        boolean alternativeResult = false;

        if (columnIndex == 1) {//считаем по горнеру 2 столбик
            result = getResult(result, x, coefficients);
        }

        if(columnIndex == 2){//проверка на целую часть чётную
            if((int)getResult(result, x, coefficients) % 2 == 0){
                alternativeResult = true;
            }else{
                alternativeResult = false;
            }
        }

        switch (columnIndex) {
            case 0: {
                return x;
            }
            case 1: {
                return result;
            }
            case 2: {
                return alternativeResult;
            }
            default: {
                return 0;
            }
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: {
                return "Значение X";
            }
            case 1: {
                return "Значение многочлена по схеме Горнера";
            }
            case 2: {
                return "Целая часть четная?";
            }
            default: {
                return "";
            }
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {//если это 2 колонка возвтращаем boolean
        return columnIndex == 2? Boolean.class:Double.class;
    }

    public double getXBeg() {
        return xBeg;
    }

    public double getXEnd() {
        return xEnd;
    }

    public double getStep() {
        return step;
    }
}
