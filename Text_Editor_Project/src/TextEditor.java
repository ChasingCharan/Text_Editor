import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    //Declaring properties of TextEditor
    JFrame frame;
    JMenuBar menuBar;
    JMenu file,edit;
    //File Menu items
    JMenuItem newFile, openFile, saveFile;
    //File menu items
    JMenuItem cut, copy, past, selectAll, close;
    JTextArea textArea;
    TextEditor(){
        //Initialize a frame
        frame=new JFrame();
        //Initialize menubar
        menuBar=new JMenuBar();
        //Initialize text area
        textArea=new JTextArea();
        //Initialize menu
        file=new JMenu("File");
        edit=new JMenu("Edit");
        //Initialize file menu items
        newFile=new JMenuItem("New Window");
        openFile=new JMenuItem("Open File");
        saveFile=new JMenuItem("Save file");
        //Initialize file menu items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        //Add menu items to File menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);
        //Initialize edit menu items
        cut=new JMenuItem("Cut");
        copy=new JMenuItem("Copy");
        past=new JMenuItem("Past");
        selectAll=new JMenuItem("Select All");
        close=new JMenuItem("Close");
        //Adding action listeners to edit menu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        past.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);
        //Add menu Items to Edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(past);
        edit.add(selectAll);
        edit.add(close);
        // Add menu to menuBar
        menuBar.add(file);
        menuBar.add(edit);
        // Set menubar to frame
        frame.setJMenuBar(menuBar);
        //Create content Panel
        JPanel panel=new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        //add text area to panel
        panel.add(textArea, BorderLayout.CENTER);
        //Create Scroll to panel
        JScrollPane scrollPane=new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //Add scroll pane to panel
        panel.add(scrollPane);
        //Add panel to frame
        frame.add(panel);
        // Set Dimensions of frame
        frame.setBounds(0,0,400,400);
        frame.setTitle("Text Editor");
        frame.setVisible(true);
        frame.setLayout(null);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==cut){
            //Perform cut operation
            textArea.cut();
        }
        if(e.getSource()==copy){
            //Perform copy operation
            textArea.copy();
        }
        if(e.getSource()==past){
            //Perform past operation
            textArea.paste();
        }
        if(e.getSource()==selectAll){
            //Perform selectAll operation
            textArea.selectAll();
        }
        if(e.getSource()==close){
            //Perform close editor operation
            System.exit(0);
        }
        if(e.getSource()==openFile){
            //Open a file Chooser
            JFileChooser fileChooser=new JFileChooser("C:");
            int chooseOption =fileChooser.showOpenDialog(null);
            //If we have clicked on open button
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                //Getting selected file
                File file=fileChooser.getSelectedFile();
                //Get the path of selected file
                String filePath=file.getPath();
                try{
                    //Initialize file reader
                    FileReader fileReader=new FileReader(filePath);
                    //Initialize buffered Reader
                    BufferedReader bufferedReader=new BufferedReader(fileReader);
                    String intermediate="", output="";
                    //Read contents of file line by line
                    while((intermediate=bufferedReader.readLine())!=null){
                        output+=intermediate+"\n";
                    }
                    //Set the output string to text area
                    textArea.setText(output);
                } catch (IOException fileNotFoundException){
                    fileNotFoundException.printStackTrace();
                }
            }
        }
        if(e.getSource()==saveFile){
            //Initialize file picker
            JFileChooser fileChooser=new JFileChooser("C:");
            //Get choose option from file chooser
            int chooseOption=fileChooser.showOpenDialog(null);
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                //Create a new file with chosen directory path and file name
                File file=new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try{
                    //Initialize file writer
                    FileWriter fileWriter=new FileWriter(file);
                    //Initialize Buffered writer
                    BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
                    // write contents of text area to file
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                } catch (IOException ioException){
                    ioException.printStackTrace();
                }
            }
         }
        if (e.getSource()==newFile){
            TextEditor newTextEditor=new TextEditor();
        }
    }
    public static void main(String[] args) {
        TextEditor TextEditor=new TextEditor();
    }
}