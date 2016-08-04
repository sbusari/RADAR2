package radar.plot.goal.graph;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.swing.JPopupMenu;

import prefuse.render.AbstractShapeRenderer;
import prefuse.visual.NodeItem;
import prefuse.visual.VisualItem;



public class FinalRenderer extends AbstractShapeRenderer
{
	String [] nodeTypeList_;
	String graphType = "GoalGraph";
	protected RectangularShape m_rect = new Rectangle2D.Double();
	protected RectangularShape m_round_rect = new RoundRectangle2D.Double();
	protected Ellipse2D m_ecl = new Ellipse2D.Double();
	public FinalRenderer(){
	}
	
	@Override
	protected Shape getRawShape(VisualItem item) 
	{	
		m_ecl.setFrame(item.getX(), item.getY(), (Integer) 250, (Integer)30);
		Shape result = m_ecl;
		if(item instanceof NodeItem)
		{
			
			String expr = (String) item.get("nodeType");
			if (expr.trim().equals("Objective") ) {
				m_rect.setFrame(item.getX(), item.getY(), (Integer) 300, (Integer)50);
				result =m_rect;  
			}else if (expr.trim().equals("QualityVariable")  || expr.trim().equals("Identifier")  ) {
				RectangularShape m_rect = new RoundRectangle2D.Double(item.getX(), item.getY(), (Integer) 300, (Integer)50,25, 25);
				result =m_rect;
			}
			if (expr.trim().equals("Joiner") ) {
				RectangularShape m_rect = new RoundRectangle2D.Double(item.getX(), item.getY(), (Integer) 20, (Integer)20, 20, 20);
				result =m_rect;
			}
			else if (expr.trim().equals("Option")){
				RectangularShape m_rect = new RoundRectangle2D.Double(item.getX(), item.getY(), (Integer) 300, (Integer)50, 160, 160);
				result =m_rect;
			}else if (expr.trim().equals("ArithmeticJoiner")){
				m_ecl.setFrame(item.getX(), item.getY(), (Integer)1, (Integer)1);
				result =m_ecl;
			}
		}
		return result;
		
	}
}
