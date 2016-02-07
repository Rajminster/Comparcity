/*
 * Created on Dec 8, 2009
 *
 */
package com.wolfram.alpha.alpha.visitor;

import com.wolfram.alpha.alpha.WAAssumption;
import com.wolfram.alpha.alpha.WAExamplePage;
import com.wolfram.alpha.alpha.WAFutureTopic;
import com.wolfram.alpha.alpha.WAImage;
import com.wolfram.alpha.alpha.WAInfo;
import com.wolfram.alpha.alpha.WALink;
import com.wolfram.alpha.alpha.WAPlainText;
import com.wolfram.alpha.alpha.WAPod;
import com.wolfram.alpha.alpha.WAPodState;
import com.wolfram.alpha.alpha.WAQueryResult;
import com.wolfram.alpha.alpha.WAReinterpretWarning;
import com.wolfram.alpha.alpha.WARelatedExample;
import com.wolfram.alpha.alpha.WARelatedLink;
import com.wolfram.alpha.alpha.WASound;
import com.wolfram.alpha.alpha.WASourceInfo;
import com.wolfram.alpha.alpha.WASubpod;
import com.wolfram.alpha.alpha.WAUnits;
import com.wolfram.alpha.alpha.WAWarning;


public interface Visitor {
    
    void visit(WAQueryResult obj); 
    void visit(WAPod obj);
    void visit(WASubpod obj); 
    void visit(WAAssumption obj); 
    void visit(WAWarning obj); 
    void visit(WAInfo obj);
    void visit(WAPodState obj);
    void visit(WARelatedLink obj); 
    void visit(WARelatedExample obj);
    void visit(WASourceInfo obj); 
    void visit(WAFutureTopic obj);
    void visit(WAExamplePage obj);
    void visit(WALink obj);
    void visit(WAReinterpretWarning obj);
    void visit(WAUnits obj);
    
    // Content types
    void visit(WAPlainText obj);
    void visit(WAImage obj); 
    void visit(WASound obj); 

}
