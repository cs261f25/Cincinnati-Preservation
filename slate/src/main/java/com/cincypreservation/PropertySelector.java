import javax.swing.*;

public class PropertySelector {

    public static void main(String[] args) {
        // Create a window (JFrame)
        JFrame frame = new JFrame("Neighborhood and Property Selector");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Example neighborhoods
        String[] neighborhoods = {"Downtown", "Midtown", "Uptown"};
        JComboBox<String> neighborhoodBox = new JComboBox<>(neighborhoods);

        // Example properties (hardcoded for now)
        String[] properties = {"Property A", "Property B", "Property C"};
        JComboBox<String> propertyBox = new JComboBox<>(properties);

        // Add items to the window
        JPanel panel = new JPanel();
        panel.add(new JLabel("Select Neighborhood:"));
        panel.add(neighborhoodBox);

        panel.add(new JLabel("Select Property:"));
        panel.add(propertyBox);

        frame.add(panel);
        frame.setVisible(true);
    }
}
