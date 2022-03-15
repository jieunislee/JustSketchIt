package jsi;

import java.awt.Point;
import java.awt.Rectangle;

public class JSISelectionBox extends Rectangle {
    private Point mAnchorPt = null;
    
    public JSISelectionBox(Point pt) {
        super(pt);
        this.mAnchorPt = pt;
    }
    
    public void update(Point pt) {
        this.setRect(this.mAnchorPt.x, this.mAnchorPt.y, 0, 0);
        this.add(pt);
    }
}