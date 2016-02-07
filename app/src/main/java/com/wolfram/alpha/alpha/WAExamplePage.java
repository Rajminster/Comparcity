/*
 * Created on Sep 19, 2010
 *
 */
package com.wolfram.alpha.alpha;

import com.wolfram.alpha.alpha.visitor.Visitable;


public interface WAExamplePage extends Visitable {

    String getCategory();
    String getURL();
}
