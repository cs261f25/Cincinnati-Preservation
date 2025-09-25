package com.cincypreservation;

import java.io.IOException;

public class LaunchCamera {
    public static void main(String args[]) {
        try {
            // Use ProcessBuilder to execute the command to launch the Windows Camera app.
            ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "start microsoft.windows.camera:");
            pb.start();
            System.out.println("Windows Camera app has been launched.");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to launch the Windows Camera app.");
        }

    }

}
