package radar.plot.goal.graph;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import prefuse.Constants;
import prefuse.Display;
import prefuse.Visualization;
import prefuse.action.ActionList;
import prefuse.action.RepaintAction;
import prefuse.action.assignment.ColorAction;
import prefuse.action.assignment.DataColorAction;
import prefuse.action.layout.graph.BalloonTreeLayout;
import prefuse.action.layout.graph.ForceDirectedLayout;
import prefuse.action.layout.graph.FruchtermanReingoldLayout;
import prefuse.action.layout.graph.NodeLinkTreeLayout;
import prefuse.action.layout.graph.TreeLayout;
import prefuse.activity.Activity;
import prefuse.controls.DragControl;
import prefuse.controls.PanControl;
import prefuse.controls.ZoomControl;
import prefuse.data.Graph;
import prefuse.data.io.DataIOException;
import prefuse.data.io.GraphMLReader;
import prefuse.render.DefaultRendererFactory;
import prefuse.render.EdgeRenderer;
import prefuse.render.LabelRenderer;
import prefuse.util.ColorLib;
import prefuse.visual.VisualItem;
import prefuse.visual.expression.InGroupPredicate;

public class GoalGraph {

	public static void main(String[] argv) {
	    SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	             // -- 1. load the data ------------------------------------------------

	            // load the socialnet.xml file. it is assumed that the file can be
	            // found at the root of the java classpath
	            Graph graph = null;
	            try {
	                //graph = new GraphMLReader().readGraph("/Users/INTEGRALSABIOLA/Documents/JavaProject/Prefuse/tree/goalmodel");
	                graph = new GraphMLReader().readGraph("/Users/INTEGRALSABIOLA/Documents/JavaProject/Prefuse/tree/socialnet.xml");
	                //graph = new GraphMLReader().readGraph("/Users/INTEGRALSABIOLA/Documents/JavaProject/Prefuse/tree/graph-sample.xml");
	                //graph = new GraphMLReader().readGraph("/Users/INTEGRALSABIOLA/Documents/JavaProject/Prefuse/tree/terror.xml");
	                //graph = new GraphMLReader().readGraph("/Users/INTEGRALSABIOLA/Documents/JavaProject/Prefuse/tree/treeml-sample.xml");
	                
	            } catch ( DataIOException e ) {
	                e.printStackTrace();
	                System.err.println("Error loading graph. Exiting...");
	                System.exit(1);
	            }


	            // -- 2. the visualization --------------------------------------------

	            // add the graph to the visualization as the data group "graph"
	            // nodes and edges are accessible as "graph.nodes" and "graph.edges"
	            Visualization vis = new Visualization();
	            vis.add("graph", graph);
	            vis.setInteractive("graph.edges", null, false);

	            // -- 3. the renderers and renderer factory ---------------------------

	            // draw the "name" label for NodeItems
	            LabelRenderer r = new LabelRenderer("name");
	            r.setRoundedCorner(8, 8); // round the corners
	            
	            EdgeRenderer e = new EdgeRenderer(Constants.EDGE_TYPE_LINE, Constants.EDGE_ARROW_REVERSE);
	            e.setArrowType(Constants.EDGE_ARROW_REVERSE);
	            e.setArrowHeadSize(10, 10);
	            
	            DefaultRendererFactory factory =new DefaultRendererFactory(r,e);
	            factory.setDefaultEdgeRenderer(e);
	            //factory.add(new InGroupPredicate("edgeDeco"), new LabelRenderer(VisualItem.LABEL));
	            // create a new default renderer factory
	            // return our name label renderer as the default for all non-EdgeItems
	            // includes straight line edges for EdgeItems by default
	            vis.setRendererFactory(factory);
	

	            // -- 4. the processing actions ---------------------------------------

	            // create our nominal color palette
	            // pink for females, baby blue for males
	            int[] palette = new int[] {
	                ColorLib.rgb(255,180,180), ColorLib.rgb(190,190,255), ColorLib.rgb(180,160,255)
	            };
	            // map nominal data values to colors using our provided palette
	            DataColorAction fill = new DataColorAction("graph.nodes", "gender",
	                    Constants.NOMINAL, VisualItem.FILLCOLOR, palette);
	            
	            
	            // use black for node text
	            ColorAction text = new ColorAction("graph.nodes",
	                    VisualItem.TEXTCOLOR, ColorLib.gray(0));
	            // use light grey for edges
	            ColorAction edges = new ColorAction("graph.edges",
	                    VisualItem.STROKECOLOR, ColorLib.gray(200));
	            
	            ColorAction arrow = new ColorAction("graph.edges", 
	            		VisualItem.FILLCOLOR, ColorLib.gray(200));
	           /* ColorAction edges = new ColorAction("graph.edges",
	                    VisualItem.FILLCOLOR, ColorLib.gray(200));*/

	            // create an action list containing all color assignments
	            ActionList color = new ActionList();
	            color.add(fill);
	            color.add(text);
	            color.add(edges);
	            color.add(arrow);

	            // create an action list with an animated layout
	            ActionList layout = new ActionList(Activity.INFINITY);
	            //layout.add(new ForceDirectedLayout("graph"));
	            layout.add(new NodeLinkTreeLayout("graph"));
	            
	            layout.add(new RepaintAction());

	            // add the actions to the visualization
	            vis.putAction("color", color);
	            vis.putAction("layout", layout);


	            // -- 5. the display and interactive controls -------------------------

	            Display d = new Display(vis);
	            d.setSize(1000,800); // set display size
	            // drag individual items around
	            d.addControlListener(new DragControl());
	            // pan with left-click drag on background
	            d.addControlListener(new PanControl()); 
	            // zoom with right-click drag
	            d.addControlListener(new ZoomControl());

	            // -- 6. launch the visualization -------------------------------------

	            // create a new window to hold the visualization
	            JFrame frame = new JFrame("prefuse example");
	            // ensure application exits when window is closed
	            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            frame.add(d);
	            frame.pack();           // layout components in window
	            frame.setVisible(true); // show the window

	            // assign the colors
	            vis.run("color");
	            // start up the animated layout
	            vis.run("layout");
	        }
	    });
	}
}
