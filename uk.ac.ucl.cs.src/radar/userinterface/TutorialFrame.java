package radar.userinterface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;

public class TutorialFrame extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JEditorPane editorPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TutorialFrame frame = new TutorialFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TutorialFrame() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		editorPane = new JEditorPane();
		editorPane.setBounds(100, 100, 600, 500);
		scrollPane.setViewportView(editorPane);
	}
	public JEditorPane getEditorPane(){
		return editorPane;
	}
	public void  setEditorString( JEditorPane jEditorPane)
	{   
        // make it read-only
        jEditorPane.setEditable(false);

        // add an html editor kit
        HTMLEditorKit kit = new HTMLEditorKit();
        jEditorPane.setEditorKit(kit);
        
        // add some styles to the html
        StyleSheet styleSheet = kit.getStyleSheet();
        styleSheet.addRule("body {color:#000; font-family:times; margin: 4px; }");
        styleSheet.addRule("h1 {color: blue;}");
        styleSheet.addRule("h2 {color: #ff0000;}");
        styleSheet.addRule("pre {font : 10px monaco; color : black; background-color : #fafafa; }");

        // create some simple html as a string
        String htmlString = "<html>\n"
                          + "<body>\n"
                          + "<h1>RADAR: Requirements and Architecture Decision Analyser</h1>\n"
                          + "<h2><a href=\"http://www0.cs.ucl.ac.uk/staff/S.Busari/\">Saheed Busari</a> and <a href=\"http://letier.cs.ucl.ac.uk/\">Emmanuel Letier</a></h2>"
                          + "<p>Uncertainty and conflicting objectives make many software requirements and architecture decisions particularly hard."
                          + "Quantitative probabilistic models allow software architects to analyse such decisions using stochastic simulation and multi- objective optimisation, "
                          + "but the difficulty of elaborating the models is an obstacle to the wider adoption of such techniques. To reduce this obstacle, the paper presents a novel "
                          + "modelling language and analysis tool, called RADAR, intended to facilitate requirements and architecture decision analysis. The language has relations to "
                          + "quantitative AND/OR goal models used in requirements engineering and to feature models used in software product lines. It, however, simplifies such models to a "
                          + "minimum set of language constructs essential for decision analysis. The paper presents RADAR’ modelling language, automated support for decision analysis, and our"
                          + "experience applying the language and tool to four real-world examples.</p>\n"
                          + "<p> Before we decribe the tool...</p>\n"
                          + "<p></p>\n"
                          + "</body>\n";
        
        // create a document, set it on the jeditorpane, then add the html
        Document doc = kit.createDefaultDocument();
        jEditorPane.setDocument(doc);
        jEditorPane.setText(htmlString);
        jEditorPane.setBounds(100, 100, 30, 20);
	}

}
