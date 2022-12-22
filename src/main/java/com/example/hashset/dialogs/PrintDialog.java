package com.example.hashset.dialogs;

import javafx.scene.control.Dialog;
import javafx.stage.DirectoryChooser;
import java.io.File;

/** Class to choose the directory where to save file with values from HashSet **/
public class PrintDialog extends Dialog {
    private final DirectoryChooser directoryChooser;
    public PrintDialog() {
        // create directoryChooser that will open the window to choose directory
        directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choose directory in which save the hashSet result file");
    }
    /** Return File that contains chosen directory **/
    public File chooseDirectory(){
        // start the directoryChooser and save resulting directory into dir
        return directoryChooser.showDialog(this.getOwner());
    }

}
