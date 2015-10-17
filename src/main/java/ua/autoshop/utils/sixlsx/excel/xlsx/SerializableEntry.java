package ua.autoshop.utils.sixlsx.excel.xlsx;


import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * @author floyd
 * 
 */
public interface SerializableEntry {
	public void serialize(XMLStreamWriter writer) throws XMLStreamException;
}
