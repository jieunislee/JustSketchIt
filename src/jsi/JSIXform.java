
package jsi;

import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;

public class JSIXform {
    public static final Point PIVOT_PT = new Point(100, 100);
    public static final double MIN_STARTING_ARM_LEN_FOR_SCALING = 100.0;
    
    private Point mStartingScreenPt = null;
    public void setStartingScreenPt(Point pt) {
        this.mStartingScreenPt = pt;
        this.mStartXformFromWorldToScreen.
                setTransform(this.mCurXformFromWorldToScreen);
    }
    
    private AffineTransform mStartXformFromWorldToScreen = null;
    
    private AffineTransform mCurXformFromWorldToScreen = null;
    public AffineTransform getCurXformFromWorldToScreen() {
        return this.mCurXformFromWorldToScreen;
    }
    
    private AffineTransform mCurXformFromScreenToWorld = null;
    public AffineTransform getCurXformFromScreenToWorld() {
        return this.mCurXformFromScreenToWorld;
    }
    
    public JSIXform() {
        this.mCurXformFromWorldToScreen = new AffineTransform();
        this.mCurXformFromScreenToWorld = new AffineTransform();
        this.mStartXformFromWorldToScreen = new AffineTransform();
    }
    
    public void updateCurXformFromScreenToWorld() {
        try {
            this.mCurXformFromScreenToWorld =
                    this.mCurXformFromWorldToScreen.createInverse();
        } catch (NoninvertibleTransformException ex) {
            System.out.println("NoninvertibleTransformException");
        }
    }
    
    public Point calcPtFromWorldToScreen(Point2D.Double worldPt) {
        Point screenPt = new Point();
        this.mCurXformFromWorldToScreen.transform(worldPt, screenPt);
        return screenPt;
    }
    
    public Point2D.Double calcPtFromScreenToWorld(Point screenPt) {
        Point2D.Double worldPt = new Point2D.Double();
        this.mCurXformFromScreenToWorld.transform(screenPt, worldPt);
        return worldPt;
    }

    public boolean translateTo(Point pt) {
        if (this.mStartingScreenPt == null) {
            return false;
        }
        this.mCurXformFromWorldToScreen.
                setTransform(this.mStartXformFromWorldToScreen);
        this.updateCurXformFromScreenToWorld();
        
        Point2D.Double worldPt0 = 
                this.calcPtFromScreenToWorld(this.mStartingScreenPt);
        Point2D.Double worldPt1 = 
                this.calcPtFromScreenToWorld(pt);
        double dx = worldPt1.x - worldPt0.x;
        double dy = worldPt1.y - worldPt0.y;
        
        //translate the screen coordinate system by (-dx, -dy).
        this.mCurXformFromWorldToScreen.translate(dx, dy);
        this.updateCurXformFromScreenToWorld();
        return true;
    }
    
    public boolean rotateTo(Point pt) {
        if (this.mStartingScreenPt == null) {
            return false;
        }
        
        this.mCurXformFromWorldToScreen.
                setTransform(this.mStartXformFromWorldToScreen);
        this.updateCurXformFromScreenToWorld();
        
        double ang0 = Math.atan2(this.mStartingScreenPt.y - JSIXform.PIVOT_PT.y,
                this.mStartingScreenPt.x - JSIXform.PIVOT_PT.x);
        double ang1 = Math.atan2(pt.y - JSIXform.PIVOT_PT.y,
                pt.x - JSIXform.PIVOT_PT.x);
        double ang = ang1 - ang0;
        
        Point2D.Double worldPivotPt = 
                this.calcPtFromScreenToWorld(JSIXform.PIVOT_PT);
        
        this.mCurXformFromWorldToScreen.translate(worldPivotPt.x, 
                worldPivotPt.y);
        this.mCurXformFromWorldToScreen.rotate(ang);
        this.mCurXformFromWorldToScreen.translate(-worldPivotPt.x, 
                -worldPivotPt.y);
        
        this.updateCurXformFromScreenToWorld();
        return true;
    }

    public boolean zoomNRotateTo(Point pt) {
        if (this.mStartingScreenPt == null) {
            return false;
        }
        
        this.mCurXformFromWorldToScreen.
                setTransform(this.mStartXformFromWorldToScreen);
        this.updateCurXformFromScreenToWorld();
        
        // for scaling
        double d0 = JSIXform.PIVOT_PT.distance(this.mStartingScreenPt);
        if (d0 < JSIXform.MIN_STARTING_ARM_LEN_FOR_SCALING) {
            return false;
        }
        double d1 = JSIXform.PIVOT_PT.distance(pt);
        double s = d1 / d0;
        
        double ang0 = Math.atan2(this.mStartingScreenPt.y - JSIXform.PIVOT_PT.y,
                this.mStartingScreenPt.x - JSIXform.PIVOT_PT.x);
        double ang1 = Math.atan2(pt.y - JSIXform.PIVOT_PT.y,
                pt.x - JSIXform.PIVOT_PT.x);
        double ang = ang1 - ang0;
        
        Point2D.Double worldPivotPt = 
                this.calcPtFromScreenToWorld(JSIXform.PIVOT_PT);
        
        //translate the screen coordiante system by (-dx, -dy).
        this.mCurXformFromWorldToScreen.translate(worldPivotPt.x, worldPivotPt.y);
        //rotate the screen coordiante system by -angle
        this.mCurXformFromWorldToScreen.rotate(ang);
        //scale the screen coordinate system by (1/s, 1/s)
        this.mCurXformFromWorldToScreen.scale(s, s);
        //translate the coordinate system by (+dx,dy)
        this.mCurXformFromWorldToScreen.translate(-worldPivotPt.x, -worldPivotPt.y);
        
        this.updateCurXformFromScreenToWorld();
        return true;
    }

    public void home() {
        //identity = initial condition
        this.mCurXformFromWorldToScreen.setToIdentity();
        this.updateCurXformFromScreenToWorld();
    }
}