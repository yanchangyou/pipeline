package org.software.matter.molecule.platform.pipeline.core.data.tree0D;

import org.software.matter.molecule.platform.pipeline.core.data.Tree;
import org.software.matter.molecule.platform.pipeline.exception.data.tree0D.Tree0DConvertException;

public abstract class Tree0D extends Tree {

	public abstract Date converToDate() throws Tree0DConvertException;

	public abstract Number converToNumber() throws Tree0DConvertException;
	
	public abstract String converToString() ;

//	public abstract int compareWithDate(Date date) throws Tree0DConvertException;
//
//	public abstract int compareWithNumber(Number number) throws Tree0DConvertException;
//	
//	public abstract int compareWithString(String string) ; 
}
