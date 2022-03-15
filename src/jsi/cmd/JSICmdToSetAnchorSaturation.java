
package jsi.cmd;

import jsi.JSIColorChooser;
import jsi.scenario.JSIColorScenario;
import x.XApp;
import x.XLoggableCmd;

/**
 *
 * @author oooo
 */
public class JSICmdToSetAnchorSaturation extends XLoggableCmd{
    // fields 
    private float mAnchorSaturation  = Float.NaN;
    
    // private constructor
    private JSICmdToSetAnchorSaturation(XApp app) {
        super(app);
    }
    
    public static boolean execute(XApp app) {
        JSICmdToSetAnchorSaturation cmd = new JSICmdToSetAnchorSaturation(app);
        return cmd.execute();
    }

    @Override
    protected boolean defineCmd() {
        JSIColorScenario scenario = JSIColorScenario.getSingleton();
        JSIColorChooser colorChooser = scenario.getColorChooser();
        colorChooser.setAnchorSaturation();
        this.mAnchorSaturation = colorChooser.getSaturation();
        return true;
    }

    @Override
    protected String createLog() {
        StringBuffer sb = new StringBuffer();
        sb.append(this.getClass().getSimpleName()).append("\t");      
        sb.append(this.mAnchorSaturation);         
        return sb.toString();
    }
}
