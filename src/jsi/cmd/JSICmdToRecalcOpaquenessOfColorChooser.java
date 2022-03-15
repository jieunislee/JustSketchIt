
package jsi.cmd;

import jsi.JSIApp;
import jsi.JSIColorChooser;
import jsi.JSIPenMark;
import jsi.scenario.JSIColorScenario;
import x.XApp;
import x.XLoggableCmd;

/**
 *
 * @author oooo
 */
public class JSICmdToRecalcOpaquenessOfColorChooser extends XLoggableCmd{
    // fields 
    private float mOpaquenessBef  = Float.NaN;
    private float mOpaquenessAft  = Float.NaN;
    
    // private constructor
    private JSICmdToRecalcOpaquenessOfColorChooser(XApp app) {
        super(app);
    }
    
    public static boolean execute(XApp app) {
        JSICmdToRecalcOpaquenessOfColorChooser cmd = 
                new JSICmdToRecalcOpaquenessOfColorChooser(app);
        return cmd.execute();
    }

    @Override
    protected boolean defineCmd() {
        JSIApp app = (JSIApp) this.mApp;
        JSIColorScenario scenario = JSIColorScenario.getSingleton();
        JSIColorChooser colorChooser = scenario.getColorChooser();
        JSIPenMark penMark = app.getPenMarkMgr().getLastPenMark();
        
        this.mOpaquenessBef = colorChooser.getOpaqueness();
        colorChooser.recalcColors(penMark, app.getCanvas2D().getWidth(),
                app.getCanvas2D().getHeight());
        this.mOpaquenessAft = colorChooser.getOpaqueness();
        return true;
    }

    @Override
    protected String createLog() {
        StringBuffer sb = new StringBuffer();
        sb.append(this.getClass().getSimpleName()).append("\t");      
        sb.append(this.mOpaquenessBef).append("\t");    
        sb.append(this.mOpaquenessAft);    
        return sb.toString();
    }
}
