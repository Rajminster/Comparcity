/*
 * Created on Dec 9, 2009
 *
 */
package com.wolfram.alpha.alpha;

import java.io.File;

import com.wolfram.alpha.alpha.visitor.Visitable;


public interface WASound extends Visitable {
    
    String getURL();
    
    String getFormat();
    
    File getFile();
    
    void acquireSound();

}
