package pixelledcolorchooser;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import javax.swing.JTextArea;

public class ArduinoCLI {

    String arduinoCLIPath = "C:\\ArduinoCLI";
    String COM_PORT = "COM3";
    String sketchFolderName = "MyFirstSketch";
    JTextArea console;
    String ctext = "";

    String[] cmd = {"cmd.exe", "/c", "cd " + arduinoCLIPath + "&& arduino-cli core install arduino:avr && "
        + "arduino-cli compile --fqbn arduino:avr:uno MyFirstSketch  &&"
        + "arduino-cli upload -p " + COM_PORT + " --fqbn arduino:avr:uno " + sketchFolderName};

    ArduinoCLI(String com, String fname, String[] cmd) {
        COM_PORT = com;
        sketchFolderName = fname;
        this.cmd = cmd;
    }

    ArduinoCLI(String com, String fname, String[] cmd, JTextArea console) {
        COM_PORT = com;
        sketchFolderName = fname;
        this.cmd = cmd;
        PrintStream printStream = new PrintStream(new CustomOutputStream(console));
        System.setOut(printStream);
        System.setErr(printStream);
        this.console = console;

    }

    boolean runCommand() {
        try {
            ProcessBuilder builder = new ProcessBuilder(cmd);
            Process p = builder.start();
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            int exitCode = p.waitFor();
            System.out.println("\nExited with error code : " + exitCode);
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
}
