package spiel_wirtschaft.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlValue;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

@XmlAccessorType(XmlAccessType.FIELD)
public abstract class AbstractBE {

	@XmlValue
	private final long ID;

	private static long idCounter = 1;

	public AbstractBE() {
		super();
		ID = idCounter;
		idCounter++;
	}

	public long getID() {
		return ID;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
