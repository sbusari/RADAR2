package radar.plot.goal.graph;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.JFrame;

import prefuse.Constants;
import prefuse.Display;
import prefuse.Visualization;
import prefuse.action.ActionList;
import prefuse.action.RepaintAction;
import prefuse.action.assignment.ColorAction;
import prefuse.action.assignment.DataColorAction;
import prefuse.action.layout.graph.ForceDirectedLayout;
import prefuse.action.layout.graph.NodeLinkTreeLayout;
import prefuse.activity.Activity;
import prefuse.controls.DragControl;
import prefuse.controls.PanControl;
import prefuse.controls.ZoomControl;
import prefuse.data.Graph;
import prefuse.data.Node;
import prefuse.data.Schema;
import prefuse.data.io.GraphWriter;
import prefuse.data.tuple.TupleSet;
import prefuse.render.DefaultRendererFactory;
import prefuse.render.EdgeRenderer;
import prefuse.render.LabelRenderer;
import prefuse.util.ColorLib;
import prefuse.util.FontLib;
import prefuse.util.PrefuseLib;
import prefuse.visual.VisualItem;
import prefuse.visual.expression.InGroupPredicate;
import radar.enumeration.ExpressionType;
import radar.parser.generated.ModelParser;

public class DynamicGoalGraph 
{
	Map <String, Node> nodeList_;
	private  Graph graph;
	private  Visualization vis;
	private  Display d;	
	String graphType_ ="GoalGraph";
	public DynamicGoalGraph (Map <String, Node> nodeList, Graph varGraph){
		nodeList_ = nodeList;
		graph = varGraph;

	}

    //public static void main(String[] argv) 
    public void goalGraph(String graphType) 
	{
    	// string id
    	// string expression
    	// string expressiontype
    	
    	graphType_ = graphType;
		setUpData(graphType);
		setUpVisualization();
		setUpRenderers();
		setUpActions();
		setUpDisplay();
		
        // The following is standard java.awt.
        // A JFrame is the basic window element in awt. 
        // It has a menu (minimize, maximize, close) and can hold
        // other gui elements. 
        
        // Create a new window to hold the visualization.  
        // We pass the text value to be displayed in the menubar to the constructor.
        JFrame frame = new JFrame(graphType);
        
        // Ensure application exits when window is closed
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // The Display object (d) is a subclass of JComponent, which
        // can be added to JFrames with the add method.
        frame.add(d);
        
        // Prepares the window.
        frame.pack();           
        
        // Shows the window.
        frame.setVisible(true); 
        
        // We have to start the ActionLists that we added to the visualization
        vis.run("color");
        vis.run("layout");
	}
	
	private  void setUpData(String graphType)
	{
        // -- 1. load the data ------------------------------------------------
    	
    	// Here we are manually creating the data structures.  500 nodes are
    	// added to the Graph structure.  500 edges are made randomly 
    	// between the nodes.
       	
		graph = new Graph(true);
        
    	// For this example, we will add a little bit more
        // information to the graph.  
        // We can add data columns (recall that the graph
        // is backed by a table).
        
        // Add columns for gender, age, and job.
        
        graph.addColumn("id", String.class);
        graph.addColumn("nodeType", String.class);
        graph.addColumn("nodeValue", String.class);
       
        //graph.putClientProperty("edgedefault", "directed");
        //System.out.println (s);
        // The set of jobs that our population will randomly pull from.
        String[] nodeTypeList = {"Decision", "Objective", "DecisionSubset", "ModelParameter", "ParameterValue", "DecisionJoiner", "OptionJoiner"};
        
        // A random number generator.
        Random rand = new Random();
        
	}

	private  void setUpVisualization()
	{
        // -- 2. the visualization --------------------------------------------
        // We must first creat the Visualization object.
		vis = new Visualization();
        
        // Now we add our previously created Graph object to the visualization.
        // The graph gets a textual label so that we can refer to it later on.
        vis.add("graph", graph);
	}
	
	private  void setUpRenderers()
	{
        // -- 3. the renderers and renderer factory ---------------------------
		
        FinalRenderer r = new FinalRenderer();
        
        /*LabelRenderer r = new LabelRenderer("id");
        r.setRoundedCorner(8, 8); // round the corners
*/        
        EdgeRenderer e = new EdgeRenderer(Constants.EDGE_TYPE_LINE, Constants.EDGE_ARROW_REVERSE);
        e.setArrowType(Constants.EDGE_ARROW_REVERSE);
        //e.setArrowHeadSize(30, 30);
        
        
        DefaultRendererFactory drf = new DefaultRendererFactory(r,e);
        drf.setDefaultEdgeRenderer(e);
        
        // We now have to have a renderer for our decorators.
        drf.add(new InGroupPredicate("nodedec"), new LabelRenderer("id"));
        //drf.add(new InGroupPredicate("nodedec"), e);
       
        
        // create a new DefaultRendererFactory
        // This Factory will use the ShapeRenderer for all nodes.
        vis.setRendererFactory(drf);
	
     // -- Decorators
        final Schema DECORATOR_SCHEMA = PrefuseLib.getVisualItemSchema();
        DECORATOR_SCHEMA.setDefault(VisualItem.INTERACTIVE, false); 
    	DECORATOR_SCHEMA.setDefault(VisualItem.TEXTCOLOR, ColorLib.rgb(0, 0, 0)); 
    	DECORATOR_SCHEMA.setDefault(VisualItem.FONT, FontLib.getFont("Tahoma",25));
        
        vis.addDecorators("nodedec", "graph.nodes", DECORATOR_SCHEMA);
	}
	
	private  void setUpActions()
	{
        // -- 4. the processing actions ---------------------------------------
        
        // We must color the nodes of the graph.  
        // Notice that we refer to the nodes using the text label for the graph,
        // and then appending ".nodes".  The same will work for ".edges" when we
        // only want to access those items.
        // The ColorAction must know what to color, what aspect of those 
        // items to color, and the color that should be used.
        // First we have to create a color palette.  Colors are represented as ints (8 bit red, 8 bit green, 8 bit blue, 8 bit alpha).
        // The ColorLib class has convenience methods for constructing color ints.
        
        //int[] palette = {ColorLib.rgb(0, 255, 255), ColorLib.rgb(153, 153, 225),  ColorLib.rgb(255, 204, 153),  ColorLib.rgb(0, 128, 128), ColorLib.rgb(0, 255, 0)}; 
        int[] palette = {ColorLib.rgb(153, 153, 225), ColorLib.rgb(153, 153, 225),  ColorLib.rgb(153, 153, 225),  ColorLib.rgb(153, 153, 225), ColorLib.rgb(153, 153, 225),ColorLib.rgb(153, 153, 225),ColorLib.rgb(153, 153, 225)}; 
        //"Decision", "Objective", "DecisionSubset", "ModelParameter", "ParameterValue", "DecisionJoiner", "OptionJoiner";

        // Now we create the DataColorAction
        // We give it the nodes to color, the data column to color by, a constant (don't worry about it (or check the api)), 
        // the way to color, and the palette to use.
        
        DataColorAction fill = new DataColorAction("graph.nodes", "nodeType", Constants.NOMINAL, VisualItem.FILLCOLOR, palette);
        
        // Similarly to the node coloring, we use a ColorAction for the 
        // edges
        ColorAction edges = new ColorAction("graph.edges", VisualItem.STROKECOLOR, ColorLib.gray(200));
        
        ColorAction arrow = new ColorAction("graph.edges", 
        		VisualItem.FILLCOLOR, ColorLib.gray(200));
        // Create an action list containing all color assignments
        // ActionLists are used for actions that will be executed
        // at the same time.  
        ActionList color = new ActionList();
        color.add(fill);
        color.add(edges);
        color.add(arrow);
        
        // The layout ActionList is constantly run to recalculate 
        // the positions of the nodes.
        ActionList layout = new ActionList(Activity.INFINITY);
        
        // We add the layout to the layout ActionList, and tell it
        // to operate on the "graph".
        //layout.add(new NodeLinkTreeLayout("graph"));
        layout.add(new NodeLinkTreeLayout("graph", 2, 40,100, 100));
        //layout.add(new ForceDirectedLayout("graph", true));
        
        layout.add(new FinalDecoratorLayout("nodedec"));
        
        // We add a RepaintAction so that every time the layout is 
        // changed, the Visualization updates it's screen.
        layout.add(new RepaintAction());
        
        // add the actions to the visualization
        vis.putAction("color", color);
        vis.putAction("layout", layout);
	}
	
        
        // -- 5. the display and interactive controls -------------------------
    private  void setUpDisplay()
    {
        // Create the Display object, and pass it the visualization that it 
        // will hold.
		d = new Display(vis);
        
        // Set the size of the display.
        d.setSize(500, 400); 
        
        // We use the addControlListener method to set up interaction.
        
        // The DragControl is a built in class for manually moving
        // nodes with the mouse. 
        d.addControlListener(new DragControl(true));
        // Pan with left-click drag on background
        d.addControlListener(new PanControl(16, true)); 
        // Zoom with right-click drag
        d.addControlListener(new ZoomControl());
        
    }
    

    
}




