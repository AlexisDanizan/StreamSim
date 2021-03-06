/**
 * 
 */
package test;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.mockito.Mockito;

import core.attribute.IAttribute;
import core.attribute.type.AttributeType;
import core.element.IElement;
import core.element.StreamElement;
import core.jdbc.JdbcStorageManager;
import junit.framework.TestCase;

/**
 * @author Roland
 *
 */
public class JdbcStorageManagerTestSuite extends TestCase {

	/**
	 * Test method for {@link core.jdbc.JdbcStorageManager#createStreamTable(java.lang.String, java.util.ArrayList)}.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void testCreateStreamTable() throws ClassNotFoundException, SQLException {
		IAttribute attr1 = Mockito.mock(IAttribute.class);
		IAttribute attr2 = Mockito.mock(IAttribute.class);
		IAttribute attr3 = Mockito.mock(IAttribute.class);
		
		Mockito.when(attr1.getName()).thenReturn("attribute1");
		Mockito.when(attr1.getType()).thenReturn(AttributeType.ENUM);
		Mockito.when(attr2.getName()).thenReturn("attribute2");
		Mockito.when(attr2.getType()).thenReturn(AttributeType.INT);
		Mockito.when(attr3.getName()).thenReturn("attribute3");
		Mockito.when(attr3.getType()).thenReturn(AttributeType.TEXT);
		
		ArrayList<IAttribute> attributes = new ArrayList<>();
		attributes.add(attr1);
		attributes.add(attr2);
		attributes.add(attr3);
		
		JdbcStorageManager manager = new JdbcStorageManager("localhost", "root", null);
		manager.createStreamTable("testStream1", attributes);
	}

	/**
	 * Test method for {@link core.jdbc.JdbcStorageManager#recordStream(java.lang.String, java.util.ArrayList, java.util.HashMap)}.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void testRecordStream() throws ClassNotFoundException, SQLException {
		IAttribute attr1 = Mockito.mock(IAttribute.class);
		IAttribute attr2 = Mockito.mock(IAttribute.class);
		IAttribute attr3 = Mockito.mock(IAttribute.class);
		
		Mockito.when(attr1.getName()).thenReturn("attribute1");
		Mockito.when(attr1.getType()).thenReturn(AttributeType.ENUM);
		Mockito.when(attr2.getName()).thenReturn("attribute2");
		Mockito.when(attr2.getType()).thenReturn(AttributeType.INT);
		Mockito.when(attr3.getName()).thenReturn("attribute3");
		Mockito.when(attr3.getType()).thenReturn(AttributeType.TEXT);
		
		ArrayList<IAttribute> attributes = new ArrayList<>();
		attributes.add(attr1);
		attributes.add(attr2);
		attributes.add(attr3);
		
		Object[] values1 = {"enum1", 1, "text1"};
		Object[] values2 = {"enum2", 2, "text2"};
		Object[] values3 = {"enum3", 3, "text3"};
		Object[] values4 = {"enum4", 4, "text4"};
		Object[] values5 = {"enum5", 5, "text5"};
		Object[] values6 = {"enum6", 6, "text6"};
		
		IElement elem1 = (IElement) new StreamElement(3, 0.0, values1);
		IElement elem2 = (IElement) new StreamElement(3, 0.0, values2);
		IElement elem3 = (IElement) new StreamElement(3, 0.0, values3);
		IElement elem4 = (IElement) new StreamElement(3, 0.0, values4);
		IElement elem5 = (IElement) new StreamElement(3, 0.0, values5);
		IElement elem6 = (IElement) new StreamElement(3, 0.0, values6);
		
		IElement[] chunk1 = new IElement[1];
		chunk1[0] = elem1;
		
		IElement[] chunk2 = new IElement[2];
		chunk2[0] = elem2;
		chunk2[1] = elem3;
		
		IElement[] chunk3 = new IElement[3];
		chunk3[0] = elem4;
		chunk3[1] = elem5;
		chunk3[2] = elem6;
		
		HashMap<String, IElement[]> chunks = new HashMap<>();
		chunks.put("P1It1", chunk1);
		chunks.put("T1It1", chunk2);
		chunks.put("P2It1", chunk3);
		
		JdbcStorageManager manager = new JdbcStorageManager("localhost", "root", null);
		manager.recordStream("testStream2", attributes, chunks);
	}

	/**
	 * Test method for {@link core.jdbc.JdbcStorageManager#recordParameters(java.lang.String, java.lang.Integer, java.lang.String, java.lang.Long)}.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void testRecordParameters() throws ClassNotFoundException, SQLException {
		JdbcStorageManager manager = new JdbcStorageManager("localhost", "root", null);
		manager.recordParameters("testStream3", 9000, "linear", 1L);
	}

	/**
	 * Test method for {@link core.jdbc.JdbcStorageManager#getElements(java.lang.String, java.util.ArrayList)}.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void testGetElements() throws ClassNotFoundException, SQLException {
		IAttribute attr1 = Mockito.mock(IAttribute.class);
		IAttribute attr2 = Mockito.mock(IAttribute.class);
		IAttribute attr3 = Mockito.mock(IAttribute.class);
		
		Mockito.when(attr1.getName()).thenReturn("attribute1");
		Mockito.when(attr1.getType()).thenReturn(AttributeType.ENUM);
		Mockito.when(attr2.getName()).thenReturn("attribute2");
		Mockito.when(attr2.getType()).thenReturn(AttributeType.INT);
		Mockito.when(attr3.getName()).thenReturn("attribute3");
		Mockito.when(attr3.getType()).thenReturn(AttributeType.TEXT);
		
		ArrayList<IAttribute> attributes = new ArrayList<>();
		attributes.add(attr1);
		attributes.add(attr2);
		attributes.add(attr3);
		
		Object[] values1 = {"enum1", 1, "text1"};
		Object[] values2 = {"enum2", 2, "text2"};
		Object[] values3 = {"enum3", 3, "text3"};
		Object[] values4 = {"enum4", 4, "text4"};
		Object[] values5 = {"enum5", 5, "text5"};
		Object[] values6 = {"enum6", 6, "text6"};
		
		IElement elem1 = (IElement) new StreamElement(3, 0.0, values1);
		IElement elem2 = (IElement) new StreamElement(3, 0.0, values2);
		IElement elem3 = (IElement) new StreamElement(3, 0.0, values3);
		IElement elem4 = (IElement) new StreamElement(3, 0.0, values4);
		IElement elem5 = (IElement) new StreamElement(3, 0.0, values5);
		IElement elem6 = (IElement) new StreamElement(3, 0.0, values6);
		
		IElement[] chunk1 = new IElement[1];
		chunk1[0] = elem1;
		
		IElement[] chunk2 = new IElement[2];
		chunk2[0] = elem2;
		chunk2[1] = elem3;
		
		IElement[] chunk3 = new IElement[3];
		chunk3[0] = elem4;
		chunk3[1] = elem5;
		chunk3[2] = elem6;
		
		HashMap<String, IElement[]> chunks = new HashMap<>();
		chunks.put("P1It1", chunk1);
		chunks.put("T1It1", chunk2);
		chunks.put("P2It1", chunk3);
		
		JdbcStorageManager manager = new JdbcStorageManager("localhost", "root", null);
		manager.recordStream("testStream4", attributes, chunks);
		
		ArrayList<String> attrNames = new ArrayList<>();
		for(int i = 0; i < attributes.size(); i++){
			attrNames.add(attributes.get(i).getName());
		}
		
		HashMap<String, IElement[]> actual = manager.getElements("testStream4", attributes);
		/*for(String chunkId : actual.keySet()){
			IElement[] chunk = actual.get(chunkId);
			System.out.print(chunkId + "= [");
			for(IElement element : chunk){
				System.out.println(element.toString(attrNames));
			}
			System.out.println("]");
		}*/
		assertEquals(chunks, actual);
	}

	/**
	 * Test method for {@link core.jdbc.JdbcStorageManager#getPort(java.lang.String)}.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void testGetPort() throws ClassNotFoundException, SQLException {
		JdbcStorageManager manager = new JdbcStorageManager("localhost", "root", null);
		manager.recordParameters("testStream5", 9005, "linearIncrease", 1L);
		manager.recordParameters("testStream6", 9006, "all", 1L);
		manager.recordParameters("testStream7", 9007, "exponentialDecrease", 1L);
		
		assertEquals(9005, manager.getPort("testStream5"), 0);
		assertEquals(9006, manager.getPort("testStream6"), 0);
		assertEquals(9007, manager.getPort("testStream7"), 0);
	}

	/**
	 * Test method for {@link core.jdbc.JdbcStorageManager#getVariation(java.lang.String)}.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void testGetVariation() throws ClassNotFoundException, SQLException {
		JdbcStorageManager manager = new JdbcStorageManager("localhost", "root", null);
		manager.recordParameters("testStream8", 9000, "linearIncrease", 1L);
		manager.recordParameters("testStream9", 9000, "all", 1L);
		manager.recordParameters("testStream10", 9000, "exponentialDecrease", 1L);
		
		assertEquals("linearIncrease", manager.getVariation("testStream8"));
		assertEquals("all", manager.getVariation("testStream9"));
		assertEquals("exponentialDecrease", manager.getVariation("testStream10"));
	}

	/**
	 * Test method for {@link core.jdbc.JdbcStorageManager#getTickDelay(java.lang.String)}.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void testGetTickDelay() throws ClassNotFoundException, SQLException {
		JdbcStorageManager manager = new JdbcStorageManager("localhost", "root", null);
		manager.recordParameters("testStream11", 9000, "linear", 1L);
		manager.recordParameters("testStream12", 9000, "linear", 2L);
		manager.recordParameters("testStream13", 9000, "linear", 3L);
		
		assertEquals(1L, manager.getTickDelay("testStream11"), 0);
		assertEquals(2L, manager.getTickDelay("testStream12"), 0);
		assertEquals(3L, manager.getTickDelay("testStream13"), 0);
	}

}
