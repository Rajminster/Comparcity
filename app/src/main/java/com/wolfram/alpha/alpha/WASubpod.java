/*
 * Created on Dec 9, 2009
 *
 */
package com.wolfram.alpha.alpha;

import com.wolfram.alpha.alpha.visitor.Visitable;


public interface WASubpod extends Visitable {

    String getTitle();
    Visitable[] getContents();
    
    void acquireImage();
    
    void setUserData(Object obj);
    Object getUserData();

}
