package util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections4.ComparatorUtils;
import org.apache.commons.collections4.comparators.ComparableComparator;
import org.apache.commons.collections4.comparators.ComparatorChain;



public class ObjectSort {

	@SuppressWarnings("unchecked")
	public static <T> void sort(List<T> object, String sortField){

		Comparator<Object> comparator=ComparableComparator.INSTANCE;
		comparator=ComparatorUtils.reversedComparator(comparator);		
		ArrayList<Object> sortFields=new ArrayList<Object>();
		sortFields.add(new BeanComparator(sortField, comparator));
		Collections.sort(object,new ComparatorChain(sortFields));
		
	}
}
