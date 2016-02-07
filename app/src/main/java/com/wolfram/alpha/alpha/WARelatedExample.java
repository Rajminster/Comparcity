/*
 * Created on Mar 1, 2010
 *
 */
package com.wolfram.alpha.alpha;

import com.wolfram.alpha.alpha.visitor.Visitable;


public interface WARelatedExample extends Visitable {

    String getInput();
    String getDescription();
    String getCategory();
    WAImage getCategoryThumb();
    String getCategoryPage();   
    
}
