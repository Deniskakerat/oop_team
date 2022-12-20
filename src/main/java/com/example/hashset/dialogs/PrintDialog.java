package com.example.hashset.dialogs;

import javafx.scene.control.Dialog;
import javafx.stage.DirectoryChooser;

import java.io.File;

public class PrintDialog extends Dialog {
    private File dir;
    private DirectoryChooser directoryChooser;
    public PrintDialog() {
        directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choose directory in which save the hashSet result file");
    }
    public File chooseDirectory(){
        dir  = directoryChooser.showDialog(this.getOwner());
        return dir;
    }

}
