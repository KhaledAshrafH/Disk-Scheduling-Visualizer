import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import java.io.*;
import org.jfree.chart.ChartUtilities;

public class GUI extends JFrame{

    static class DataInformation {
        public DataInformation(Map<String,Integer> m) throws IOException {
                final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

                for (Map.Entry<String,Integer> newMap : m.entrySet()) {
                    dataset.addValue(newMap.getValue(), newMap.getKey() +"("+ Integer.toString(newMap.getValue())+")", "");
                }

                JFreeChart barChart = ChartFactory.createBarChart3D(
                        "Compare Total Movement Between Algorithms",
                        "Algorithms",
                        "Total Movement",
                        dataset,
                        PlotOrientation.VERTICAL,
                        true, true, false);

                int width = 640; /* Width of the image */
                int height = 480; /* Height of the image */
                File barChart3D = new File( "Algorithms.jpeg" );
                ChartUtilities.saveChartAsJPEG( barChart3D, barChart, width, height);
            }
    }

    static class DrawChart extends ApplicationFrame{
        public  Vector<Integer> vector;
        public  String Name;

        public DrawChart(String applicationTitle,Vector<Integer>v,String name) {
            super(applicationTitle);
            setVector(v,name);
            JFreeChart lineChart = ChartFactory.createLineChart(
                    "",
                    "cylinders", "",
                    createDataset(),
                    PlotOrientation.HORIZONTAL,
                    true,
                    true,
                    false);

            ChartPanel chartPanel = new ChartPanel(lineChart);
            chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
            setContentPane(chartPanel);
        }

        void setVector(Vector<Integer>v,String name){
            vector = v;
            this.Name = name;
        }

        public DefaultCategoryDataset createDataset() {
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            String begin = "10";
            for (Integer value : vector) {
                dataset.addValue(value, Name, begin);
                int tmp = Integer.parseInt(begin);
                tmp += 10;
                begin = Integer.toString(tmp);
            }
            return dataset;
        }

    }

    private JPanel MainPanel;
    public String Text;
    private JLabel FirstLabel;
    private JPanel SecondPanel;
    private JRadioButton C_LOOK;
    private JRadioButton LOOK;
    private JRadioButton Scan;
    private JRadioButton C_Scan;
    private JRadioButton FCFS;
    private JRadioButton SSTF;
    private JRadioButton OPTIMIZED;
    private JButton CalculateButton;
    private JComboBox CheckC_Scan;
    private JComboBox CheckC_Look;
    private JComboBox CheckLook;
    private JComboBox CheckScan;
    private JFormattedTextField FieldC_Look;
    private JFormattedTextField FieldScan;
    private JFormattedTextField FieldC_Scan;
    private JFormattedTextField FieldFCFS;
    private JFormattedTextField FieldSSTF;
    private JTextField FieldLook;
    private JFormattedTextField StartC;
    private JFormattedTextField EndC;
    private JTextArea Que;

    public GUI(String title) {
        super(title);
        this.setVisible(true);
        Que.setRows(10);

        ArrayList<JRadioButton> Algorithms = new ArrayList<>();
        Algorithms.add(C_LOOK);
        Algorithms.add(LOOK);
        Algorithms.add(Scan);
        Algorithms.add(C_Scan);
        Algorithms.add(FCFS);
        Algorithms.add(SSTF);
        Algorithms.add(OPTIMIZED);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setContentPane(MainPanel);
        this.pack();

        CalculateButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e)
            {
                Map<String,Integer> AllMovement = new HashMap<>();
                Text = Que.getText();
                String[] s = Text.split("\n");
                if (!StartC.getText().equals("") && !EndC.getText().equals("") && !Text.equals("")){
                    for (JRadioButton Radio : Algorithms) {
                        ArrayList<Integer> queue = new ArrayList<>();
                        for (String value : s)
                            queue.add(Integer.parseInt(value));

                        if (Radio.isSelected())
                        {
                            switch (Radio.getText())
                            {
                                case "C-LOOK":
                                {
                                    if (!FieldC_Look.getText().equals(""))
                                    {
                                        C_LOOK obj;
                                        if (CheckC_Look.getModel().getSelectedItem() == "Right")
                                        {
                                            obj = new C_LOOK(Integer.parseInt(StartC.getText()), Integer.parseInt(EndC.getText()), Integer.parseInt(FieldC_Look.getText()), true, queue);
                                        }
                                        else
                                        {
                                            obj = new C_LOOK(Integer.parseInt(StartC.getText()), Integer.parseInt(EndC.getText()), Integer.parseInt(FieldC_Look.getText()), false, queue);
                                        }
                                        obj.Calculate();
                                        System.out.println("C-LOOK Algorithm");
                                        obj.display();

                                        DrawChart chart = new DrawChart("Algorithms Chart", obj.sequences, "C-LOOK Algorithm" + " (" +obj.TotalMovement +")");
                                        chart.pack();
                                        RefineryUtilities.centerFrameOnScreen(chart);
                                        chart.setVisible(true);
                                        AllMovement.put("C-LOOK",obj.TotalMovement);
                                    }
                                    break;
                                }
                                case "LOOK":
                                {
                                    if (!FieldLook.getText().equals(""))
                                    {
                                        LOOK obj;
                                        if (CheckLook.getModel().getSelectedItem() == "Right")
                                        {
                                            obj = new LOOK(Integer.parseInt(StartC.getText()), Integer.parseInt(EndC.getText()), Integer.parseInt(FieldLook.getText()), true, queue);
                                        }
                                        else
                                        {
                                            obj = new LOOK(Integer.parseInt(StartC.getText()), Integer.parseInt(EndC.getText()), Integer.parseInt(FieldLook.getText()), false, queue);
                                        }
                                        obj.Calculate();
                                        System.out.println("LOOK Algorithm");
                                        obj.display();

                                        DrawChart chart = new DrawChart("Algorithms Chart", obj.sequences, "LOOK Algorithm"+ " (" +obj.TotalMovement +")");
                                        chart.pack();
                                        RefineryUtilities.centerFrameOnScreen(chart);
                                        chart.setVisible(true);

                                        AllMovement.put("LOOK",obj.TotalMovement);
                                    }
                                    break;
                                }
                                case "Scan":
                                {
                                    if (!FieldScan.getText().equals(""))
                                    {
                                        SCAN obj;
                                        if (CheckScan.getModel().getSelectedItem() == "Right")
                                        {
                                            obj = new SCAN(Integer.parseInt(StartC.getText()), Integer.parseInt(EndC.getText()), Integer.parseInt(FieldScan.getText()), true, queue);
                                        }
                                        else
                                        {
                                            obj = new SCAN(Integer.parseInt(StartC.getText()), Integer.parseInt(EndC.getText()), Integer.parseInt(FieldScan.getText()), false, queue);
                                        }
                                        obj.Calculate();
                                        System.out.println("Scan Algorithm");
                                        obj.display();

                                        DrawChart chart = new DrawChart("Algorithms Chart", obj.sequences, "Scan Algorithm"+ " (" +obj.TotalMovement +")");
                                        chart.pack();
                                        RefineryUtilities.centerFrameOnScreen(chart);
                                        chart.setVisible(true);
                                        AllMovement.put("Scan",obj.TotalMovement);
                                    }
                                    break;
                                }
                                case "C-Scan":
                                {
                                    if (!FieldC_Scan.getText().equals(""))
                                    {
                                        C_SCAN obj;
                                        if (CheckC_Scan.getModel().getSelectedItem() == "Right")
                                        {
                                            obj = new C_SCAN(Integer.parseInt(StartC.getText()), Integer.parseInt(EndC.getText()), Integer.parseInt(FieldC_Scan.getText()), true, queue);
                                        }
                                        else
                                        {
                                            obj = new C_SCAN(Integer.parseInt(StartC.getText()), Integer.parseInt(EndC.getText()), Integer.parseInt(FieldC_Scan.getText()), false, queue);
                                        }
                                        obj.Calculate();
                                        System.out.println("C-Scan Algorithm");
                                        obj.display();

                                        DrawChart chart = new DrawChart("Algorithms Chart", obj.sequences, "C-Scan Algorithm"+ " (" +obj.TotalMovement +")");
                                        chart.pack();
                                        RefineryUtilities.centerFrameOnScreen(chart);
                                        chart.setVisible(true);
                                        AllMovement.put("C-Scan",obj.TotalMovement);
                                    }
                                    break;
                                }
                                case "FCFS":
                                {
                                    if (!FieldFCFS.getText().equals("")){
                                        FCFS obj = new FCFS(Integer.parseInt(StartC.getText()),Integer.parseInt(EndC.getText()),Integer.parseInt(FieldFCFS.getText()),queue);
                                        obj.Calculate();
                                        System.out.println("FCFS Algorithm");
                                        obj.display();

                                        DrawChart chart = new DrawChart("Algorithms Chart", obj.sequences, "FCFS Algorithm"+ " (" +obj.TotalMovement +")");
                                        chart.pack();
                                        RefineryUtilities.centerFrameOnScreen(chart);
                                        chart.setVisible(true);
                                        AllMovement.put("FCFS",obj.TotalMovement);
                                    }
                                    break;
                                }
                                case "SSTF":
                                {
                                    if (!FieldSSTF.getText().equals("")){
                                        SSTF obj = new SSTF(Integer.parseInt(StartC.getText()),Integer.parseInt(EndC.getText()),Integer.parseInt(FieldSSTF.getText()),queue);
                                        obj.Calculate();
                                        System.out.println("SSTF Algorithm");
                                        obj.display();

                                        DrawChart chart = new DrawChart("Algorithms Chart", obj.sequences, "SSTF Algorithm"+ " (" +obj.TotalMovement +")");
                                        chart.pack();
                                        RefineryUtilities.centerFrameOnScreen(chart);
                                        chart.setVisible(true);
                                        AllMovement.put("SSTF",obj.TotalMovement);
                                    }
                                    break;
                                }
                                case "OPTIMIZED":
                                {
                                    OPTIMIZED obj = new OPTIMIZED(Integer.parseInt(StartC.getText()), Integer.parseInt(EndC.getText()), queue);
                                    obj.Calculate();
                                    System.out.println("OPTIMIZED Algorithm");
                                    obj.display();

                                    DrawChart chart = new DrawChart("Algorithms Chart", obj.sequences, "OPTIMIZED Algorithm"+ " (" +obj.TotalMovement +")");
                                    chart.pack();
                                    RefineryUtilities.centerFrameOnScreen(chart);
                                    chart.setVisible(true);
                                    AllMovement.put("OPTIMIZED",obj.TotalMovement);
                                    break;
                                }
                                default:
                                    break;
                            }
                        }
                    }
                }
                try {
                    new DataInformation(AllMovement);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
    public static void main(String[] args) {
       new GUI("Disk Scheduling Algorithms");
    }
}