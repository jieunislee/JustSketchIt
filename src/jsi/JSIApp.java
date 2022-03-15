
package jsi;

import java.awt.Point;
import java.awt.geom.Point2D;
import javax.swing.JFrame;
import x.XApp;
import x.XLogMgr;
import x.XScenarioMgr;

public class JSIApp extends XApp {
    // member variables
    private JFrame mFrame = null;
    
    private JSICanvas2D mCanvas2D = null;
    public JSICanvas2D getCanvas2D() {
        return this.mCanvas2D;
    }
    
    private JSIXform mXform = null;
    public JSIXform getXform() {
        return this.mXform;
    }
    
    private JSIColorChooser mColorChooser = null;
    public JSIColorChooser getColorChooser() {
        return this.mColorChooser;
    }
    
    private JSIEventListener mJSIEventListener = null;
    public JSIEventListener getJSIEventListener(){
        return this.mJSIEventListener;
    }
    
    // JSI mgrs
    private JSIPenMarkMgr mPenMarkMgr = null;
    public JSIPenMarkMgr getPenMarkMgr() {
        return this.mPenMarkMgr;
    }
    
    private JSIPtCurveMgr mPtCurveMgr = null;
    public JSIPtCurveMgr getPtCurveMgr() {
        return this.mPtCurveMgr;
    }

    private XScenarioMgr mScenarioMgr = null;
    @Override
    public XScenarioMgr getScenarioMgr() {
        return this.mScenarioMgr;
    }

    private XLogMgr mLogMgr = null;
    @Override
    public XLogMgr getLogMgr() {
        return this.mLogMgr;
    }
    
    // constructor
    public JSIApp() {
        // step 1: create components.
        // 1) frame, 2)canvas, 3) other components,
        // 4) event listeners, 5) managers.
        this.mFrame = new JFrame("JustSketchIt");
        this.mCanvas2D = new JSICanvas2D(this);
        this.mXform = new JSIXform();
        this.mColorChooser = new JSIColorChooser();
        this.mJSIEventListener = new JSIEventListener(this);
        this.mPenMarkMgr = new JSIPenMarkMgr();
        this.mPtCurveMgr = new JSIPtCurveMgr();
        this.mScenarioMgr = new JSIScenarioMgr(this);
        this.mLogMgr = new XLogMgr();
        this.mLogMgr.setPrintOn(true);
        
        // step 2 : build and show visual components.
        this.mFrame.add(this.mCanvas2D);
        this.mFrame.setSize(800, 600);
        this.mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mFrame.setVisible(true);
        
        // step 3: conenct event listeners.
        this.mCanvas2D.addMouseListener(this.mJSIEventListener);
        this.mCanvas2D.addMouseMotionListener(this.mJSIEventListener);
        this.mCanvas2D.setFocusable(true);
        this.mCanvas2D.addKeyListener(this.mJSIEventListener);
    }

    public static void main(String[] args) {
        new JSIApp();
    }
}
