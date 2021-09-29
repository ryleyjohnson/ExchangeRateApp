import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class MainWindow {

    DataStorage dataStorage =  new DataStorage();

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

        JFrame mainWindow = new JFrame("Currency Calculator");
        mainWindow.setContentPane(new MainWindow().panelMain);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.pack();
        mainWindow.setVisible(true);

    }


    public double calculateRate(double inputValue , Object inputKey , Object outputKey){

        //calculates the exchange rate and returns the final value.

        double inputRate = (double) dataStorage.getMap2().get(inputKey);
        double outputRate = (double) dataStorage.getMap2().get(outputKey);
        double exchangeRate = outputRate / inputRate;
        double outputValue =  inputValue * exchangeRate;
        return Math.round(outputValue*100.0)/100.0;
    }

    public MainWindow() {

        //Populates the combo boxes with key values.
        for(Map.Entry entry: dataStorage.getMap2().entrySet()){
            Object Items = entry.getKey();
            inputCurrencyTypeComboBox.addItem(Items);
            outputCurrencyTypeComboBox.addItem(Items);
        }



        okayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Attempts to calculate a response with the user's input, and will tell them to input a valid amount if it fails.

                try {
                    double outputPull = calculateRate(Double.parseDouble(inputText1.getText()), inputCurrencyTypeComboBox.getSelectedItem(), outputCurrencyTypeComboBox.getSelectedItem());
                    outputLabel.setText(String.valueOf(outputPull));
                }catch(Exception f) {
                    outputLabel.setText("Invalid input. Please enter a proper amount.");
                }
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Resets all fields.

                inputText1.setText(" ");
                outputLabel.setText(" ");
                inputCurrencyTypeComboBox.setSelectedIndex(0);
                outputCurrencyTypeComboBox.setSelectedIndex(0);
            }
        });

    }




}
