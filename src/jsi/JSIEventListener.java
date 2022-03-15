
package jsi;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author oooo
 */
public class JSIEventListener implements MouseListener, 
        MouseMotionListener, KeyListener {
    // fields
    private JSIApp mApp = null;
    
    // constructor
    public JSIEventListener(JSIApp app) {
        this.mApp = app;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {}
    
    @Override
    public void mousePressed(MouseEvent e) {
        if (this.mApp.getPenMarkMgr().mousePressed(e)) {
            JSIScene curScene = 
                    (JSIScene) this.mApp.getScenarioMgr().getCurScene();
            curScene.handleMousePress(e);
            this.mApp.getCanvas2D().repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (this.mApp.getPenMarkMgr().mouseReleased(e)) {
            JSIScene curScene = 
                    (JSIScene) this.mApp.getScenarioMgr().getCurScene();
            curScene.handleMouseRelease(e);
            this.mApp.getCanvas2D().repaint();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
    
    @Override
    public void mouseDragged(MouseEvent e) {
        if (this.mApp.getPenMarkMgr().mouseDragged(e)) {
            JSIScene curScene =
                    (JSIScene) this.mApp.getScenarioMgr().getCurScene();
            curScene.handleMouseDrag(e);
            this.mApp.getCanvas2D().repaint();
        } 
    }

    @Override
    public void mouseMoved(MouseEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        JSIScene curScene = (JSIScene) this.mApp.getScenarioMgr().getCurScene();
        curScene.handleKeyDown(e);
        this.mApp.getCanvas2D().repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        JSIScene curScene = (JSIScene) this.mApp.getScenarioMgr().getCurScene();
        curScene.handleKeyUp(e);
        this.mApp.getCanvas2D().repaint();
    }
}
