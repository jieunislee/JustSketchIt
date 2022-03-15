
package jsi;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Stroke;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class JSIPtCurve {
    public static final double MIN_DISTANCE_BTWN_PTS = 5.0f;
    
    // member variables
    private ArrayList<Point2D.Double> mPts = null;
    public ArrayList<Point2D.Double> getPts() {
        return this.mPts;
    }

    private Rectangle2D.Double mBoundingBox;
    public Rectangle2D.Double getBoundingBox() {
        return this.mBoundingBox;
    }

    private Stroke mStroke = null;
    public Stroke getStroke() {
        return this.mStroke;
    }

    public void setStroke(BasicStroke bs) {
        this.mStroke = bs;
    }
    
    private Color mColor = null;
    public Color getColor() {
        return this.mColor;
    }
    
    public void setColor(Color c) {
        this.mColor = c;
    }
    
    // constructor
    public JSIPtCurve(Point2D.Double pt, Color c, Stroke s) {
        this.mPts = new ArrayList<Point2D.Double>();
        this.mPts.add(pt);
        this.mBoundingBox = new Rectangle2D.Double(pt.x, pt.y, 0.0, 0.0);
        BasicStroke bs = (BasicStroke)s;
        this.mColor = new Color(c.getRed(), 
                c.getGreen(), c.getBlue(), c.getAlpha());
        this.mStroke = new BasicStroke(bs.getLineWidth(),
                bs.getEndCap(), bs.getLineJoin());
    }
    
    public void addPoint(Point2D.Double pt) {
        this.mPts.add(pt);
        this.mBoundingBox.add(pt);
    }
    
    public void increaseStrokeWidth(float dw) {
        BasicStroke s = (BasicStroke)this.mStroke;
        if (s.getLineWidth() + dw < JSICanvas2D.MINUMUM_STROKE_WIDTH) {
            this.mStroke = new BasicStroke(JSICanvas2D.MINUMUM_STROKE_WIDTH,
                    s.getEndCap(), s.getLineJoin());
        } else {
            this.mStroke = new BasicStroke(s.getLineWidth() + dw,
                    s.getEndCap(), s.getLineJoin());
        }
    }
}
