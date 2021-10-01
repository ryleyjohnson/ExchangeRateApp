import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class MainWindow {

    CurrencyExchangeDataStorage currencyExchangeDataStorage =  new CurrencyExchangeDataStorage();

    private JButton okayButton;
    private JButton clearButton;
    private JPanel panelMain;
    private JLabel outputLabel;
    private JTextField inputText1;
    private JComboBox inputCurrencyTypeComboBox;
    private JComboBox outputCurrencyTypeComboBox;
    private JLabel outputCurrencyTypeLabel;
    private JLabel resultLabel;
    private JLabel inputCurrencyLabel;
    private JLabel inputCurrencyTypeLabel;


    public static void createWindow(){

        JFrame mainWindow = new JFrame("Currency Exchange Calculator");
        mainWindow.setContentPane(new MainWindow().panelMain);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.pack();
        mainWindow.setVisible(true);
    }


    public void fillRateDataStorage(){

        //Populates the combo boxes with key values on launch.
        for(Map.Entry entry: currencyExchangeDataStorage.getInnerExchangeRateMap().entrySet()){
            Object Items = entry.getKey();
            inputCurrencyTypeComboBox.addItem(Items);
            outputCurrencyTypeComboBox.addItem(Items);
        }
    }


    public double changeInputToExchangeRate(){

        double inputValue = Double.parseDouble(inputText1.getText());
        Object inputKey = inputCurrencyTypeComboBox.getSelectedItem() ;
        Object outputKey = outputCurrencyTypeComboBox.getSelectedItem();
        double inputRate = (double) currencyExchangeDataStorage.getInnerExchangeRateMap().get(inputKey);
        double outputRate = (double) currencyExchangeDataStorage.getInnerExchangeRateMap().get(outputKey);

        double outputCalculation = ExchangeRateMath.calculateRate(inputValue,inputRate,outputRate);

        return outputCalculation;

    }


    public MainWindow() {

        fillRateDataStorage();


        okayButton.addActionListener(eventListenerOkayButton -> {

            //Attempts to calculate a response with the user's input, and will tell them to input a valid amount if it fails.
            try {
                outputLabel.setText(String.valueOf(changeInputToExchangeRate()));
            }catch(Exception exceptionNoInput) {
                outputLabel.setText("Invalid input. Please enter a proper amount.");
            }
        });


        clearButton.addActionListener(eventListenerClearButton -> {

            //Resets all fields.
            inputText1.setText("");
            outputLabel.setText("");
            inputCurrencyTypeComboBox.setSelectedIndex(0);
            outputCurrencyTypeComboBox.setSelectedIndex(0);
        });
    }
}
