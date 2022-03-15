
package jsi.cmd;

import java.awt.geom.AffineTransform;
import jsi.JSIApp;
import x.XApp;
import x.XLoggableCmd;

/**
 *
 * @author oooo
 */
public class JSICmdToHome extends XLoggableCmd {
    private AffineTransform mXformBef = null;
    private AffineTransform mXformAft = null;
    
    private JSICmdToHome(XApp app) {
        super(app);
    }

    public static boolean execute(XApp app) {
        JSICmdToHome cmd = new JSICmdToHome(app);
        return cmd.execute();    
    }

    @Override
    protected boolean defineCmd() {
        JSIApp app = (JSIApp) this.mApp;
        
        this.mXformBef = new AffineTransform(app.getXform().
                getCurXformFromWorldToScreen());
        app.getXform().home();
        this.mXformAft = new AffineTransform(app.getXform().
                getCurXformFromWorldToScreen());
        return true;
    }
    
    @Override
    protected String createLog() {
        StringBuffer sb = new StringBuffer();
        sb.append(this.getClass().getSimpleName()).append("\t");
        sb.append(this.mXformBef).append("\t");
        sb.append(this.mXformAft);
        return sb.toString();
    }
}