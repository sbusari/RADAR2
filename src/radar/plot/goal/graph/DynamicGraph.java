package radar.plot.goal.graph;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import prefuse.Constants;
import prefuse.Display;
import prefuse.Visualization;
import prefuse.action.ActionList;
import prefuse.action.RepaintAction;
import prefuse.action.assignment.ColorAction;
import prefuse.action.assignment.DataColorAction;
import prefuse.action.layout.graph.BalloonTreeLayout;
import prefuse.action.layout.graph.ForceDirectedLayout;
import prefuse.action.layout.graph.NodeLinkTreeLayout;
import prefuse.action.layout.graph.SquarifiedTreeMapLayout;
import prefuse.activity.Activity;
import prefuse.controls.DragControl;
import prefuse.controls.PanControl;
import prefuse.controls.ZoomControl;
import prefuse.data.Graph;
import prefuse.data.Node;
import prefuse.data.Schema;
import prefuse.render.DefaultRendererFactory;
import prefuse.render.EdgeRenderer;
import prefuse.render.LabelRenderer;
import prefuse.util.ColorLib;
import prefuse.util.FontLib;
import prefuse.util.PrefuseLib;
import prefuse.util.display.ScaleSelector;
import prefuse.visual.VisualItem;
import prefuse.visual.expression.InGroupPredicate;
import radar.model.Alternative;
import radar.model.Model;
import radar.model.Objective;
import radar.model.QualityVariable;
import radar.model.SolutionValues;

public class DynamicGraph 
{
	private static final int W = 640;
	private static final int H = 480;
	Map<String, Node> nodeList_;
	private  Graph graph;
	private  Visualization vis;
	private  Display d;	
	String graphType_ ="GoalGraph";
	Model semanticModel_;
	String outputFolder_;
	public DynamicGraph ( Model semanticModel, String outputFolder){
		semanticModel_ = semanticModel;
		nodeList_ = new LinkedHashMap<String, Node>();
		outputFolder_ = outputFolder;
	}
	void createDependencyGraphPerObjective (Graph g, Objective obj){
		Node obj_node = g.addNode();
		obj_node.set("id",obj.getLabel());
		obj_node.set("nodeType", "Objective");
		obj_node.set("nodeValue", obj.getLabel());
		nodeList_.put(obj.getLabel(), obj_node);
		QualityVariable qvObjReferTo = obj.getQualityVariable();
		if (!nodeList_.containsKey(qvObjReferTo.getLabel())){
			List<Node> qv_nodes = qvObjReferTo.addNodeToGraph(g, semanticModel_, qvObjReferTo.getLabel(), nodeList_);
			for (int j=0; j < qv_nodes.size(); j ++){
				g.addEdge(obj_node, qv_nodes.get(j));
			}
		}else{
			
			Node qv_node = nodeList_.get(qvObjReferTo.getLabel());
			g.addEdge(obj_node, qv_node);
		}
		
	}
	void createDependencyGraphForAllObjectives (Graph g){
		List<Objective> objList = new ArrayList<Objective>(semanticModel_.getObjectives().values());
		
		for (int i =0; i < objList.size(); i ++){
			Objective obj = objList.get(i);
			Node obj_node = g.addNode();
			obj_node.set("id",obj.getLabel());
			obj_node.set("nodeType", "Objective");
			obj_node.set("nodeValue", obj.getLabel());
			nodeList_.put(obj.getLabel(), obj_node);
			QualityVariable qvObjReferTo = obj.getQualityVariable();
			if (!nodeList_.containsKey(qvObjReferTo.getLabel())){
				List<Node> qv_nodes = qvObjReferTo.addNodeToGraph(g, semanticModel_, qvObjReferTo.getLabel(), nodeList_);
				for (int j=0; j < qv_nodes.size(); j ++){
					g.addEdge(obj_node, qv_nodes.get(j));
				}
			}else{
				
				Node qv_node = nodeList_.get(qvObjReferTo.getLabel());
				g.addEdge(obj_node, qv_node);
			}
		}
		
	}
	private void createGraph(File f){
		ScaleSelector scaler  = new ScaleSelector();
		//Display display   = new Display(vis);
		scaler.setImage(d.getOffscreenBuffer());
		double scale = scaler.getScale();
		boolean success = false;
	    try {
	        OutputStream out = new BufferedOutputStream(
	                            new FileOutputStream(f));
	        System.out.print("Saving image "+f.getName()+", "+
	                         "png"+" format...");
	        
	        success = d.saveImage(out, "png", scale);
	        out.flush();
	        out.close();
	        System.out.println("\tDONE");
	    } catch ( Exception e ) {
	        success = false;
	    }
	}
	private void createImage(File f){

	        FileOutputStream out = null;
	        try {
	            Rectangle2D bounds = vis.getBounds("graph");

	                        // Adding some margins here
	            double  width  = 400; //bounds.getWidth() + 100;
	            double  height = 500; //bounds.getHeight() + 100;
	            Display display   =  d; //new Display(vis);          
	                        // I forget why this big of code is called.
	            display.pan(-bounds.getX() + 50, -bounds.getY() + 50);

	            BufferedImage img = new BufferedImage((int)width, 
	                    (int)height, BufferedImage.TYPE_INT_RGB);
	            
	            Graphics2D g = (Graphics2D)img.getGraphics();
	            
	            display.paintDisplay(g, new Dimension((int)width, (int)height));

	            out = new FileOutputStream(f);
	            ImageIO.write(img,"png",f);

	        } catch (Exception ex) {
	            try {
	                Logger.getLogger(DynamicGraph.class.getName()).log(Level.SEVERE, null, ex);
	                out.close();
	            } catch (IOException ex1) {
	                Logger.getLogger(DynamicGraph.class.getName()).log(Level.SEVERE, null, ex1);
	            }
	        } 
	    }

	public void plotDecisionGraph(String graphType, Objective obj ) {
	
	}
    public void plotVariableDependencyGraph(String graphType, Objective obj ) 
	{
    	// string id
    	// string expression
    	// string expressiontype
    	
    	graphType_ = graphType;
		setUpData(graphType,obj);
		setUpVisualization();
		setUpRenderers();
		setUpActions();
		setUpDisplay();
		File f = new File (outputFolder_ +"/graph.png");
		createImage(f);
		//createGraph(f);
		
        // The following is standard java.awt.
        // A JFrame is the basic window element in awt. 
        // It has a menu (minimize, maximize, close) and can hold
        // other gui elements. 
        
        // Create a new window to hold the visualization.  
        // We pass the text value to be displayed in the menubar to the constructor.
        JFrame frame = new JFrame(graphType);
        
        // Ensure application exits when window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
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
	
	private  void setUpData(String graphType, Objective obj)
	{
        // -- 1. load the data ------------------------------------------------
       	
		graph = new Graph(true);
        graph.addColumn("id", String.class);
        graph.addColumn("nodeType", String.class);
        graph.addColumn("nodeValue", String.class);
        String[] nodeTypeList = {"Objective", "QualityVariable", "Option"};
        
        createDependencyGraphPerObjective (graph, obj);
        System.out.println("Graph generated");
        
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
        EdgeRenderer e = new EdgeRenderer(Constants.EDGE_TYPE_CURVE, Constants.EDGE_ARROW_REVERSE);
        e.setArrowType(Constants.EDGE_ARROW_REVERSE);
        e.setArrowHeadSize(10, 10);
        
        
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
    	DECORATOR_SCHEMA.setDefault(VisualItem.FONT, FontLib.getFont("Tahoma",15));
        
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
        int[] palette = {ColorLib.rgb(153, 153, 225), ColorLib.rgb(153, 153, 225),  ColorLib.rgb(153, 153, 225)}; 
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
        layout.add(new NodeLinkTreeLayout("graph", 2, 20,50, 50));
        //layout.add(new ForceDirectedLayout("graph", true));
        //layout.add(new SquarifiedTreeMapLayout("graph"));
        
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
		//d = new Display(vis);
		
		d = new Display(vis) {

		    @Override
		    public Dimension getPreferredSize() {
		        return new Dimension(W, H);
		    }
		};
		d.pan(W / 2, H / 2);
		
        
        // Set the size of the display.
		
        //d.setSize(500, 400); 
        
        // We use the addControlListener method to set up interaction.
        
        // The DragControl is a built in class for manually moving
        // nodes with the mouse. 
        d.addControlListener(new DragControl());
        // Pan with left-click drag on background
        d.addControlListener(new PanControl(PanControl.LEFT_MOUSE_BUTTON, true)); 
        // Zoom with right-click drag
        d.addControlListener(new ZoomControl());
        
        
        
    }
    

    
}




