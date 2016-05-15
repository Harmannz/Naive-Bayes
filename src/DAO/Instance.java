package DAO;

import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.bcel.internal.classfile.Attribute;
/**
 * Represents an instance from the data set.
 * The classLabel is -1 if the data set is unlabelled (i.e. if data is for testing).
 * The attributes and classLabels are represented as binary values.
 * @author Harman Singh
 * @version 1.0
 */
public class Instance {
	private int classLabel;
	private List<Integer> attributes;
	
	public Instance(final int classLabel, final List<Integer> attributes) {
		this.classLabel = classLabel;
		this.attributes = new ArrayList<Integer>(attributes);
	}

	public int getClassLabel() {
		return classLabel;
	}

	public void setClassLabel(int classLabel) {
		this.classLabel = classLabel;
	}

	public List<Integer> getAttributes() {
		return new ArrayList<>(attributes);
	}

	public void setAttributes(List<Integer> attributes) {
		this.attributes = new ArrayList<>(attributes);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attributes == null) ? 0 : attributes.hashCode());
		result = prime * result + classLabel;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Instance other = (Instance) obj;
		if (attributes == null) {
			if (other.attributes != null)
				return false;
		} else if (!attributes.equals(other.attributes))
			return false;
		if (classLabel != other.classLabel)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Instance [classLabel=" + classLabel + ", attributes=" + attributes + "]";
	}
	

}
