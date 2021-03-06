package hgtest.storage.bje.BJEStorageImplementation;

import org.hypergraphdb.HGIndex;
import org.hypergraphdb.storage.ByteArrayConverter;
import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 */
public class BJEStorageImplementation_getIndexTest extends
		BJEStorageImplementationTestBasis
{
	@Test
	public void convertersAndComparatorsAreNull() throws Exception
	{
		startup(1);
		final String indexName = "sampleIndex";
		final ByteArrayConverter<Integer> keyConverter = null;
		final ByteArrayConverter<String> valueConverter = null;
		final Comparator<byte[]> comparator = null;
		final boolean bidirectional = true;
		final boolean createIfNecessary = true;

        final HGIndex<Integer, String> createdIndex = storage.getIndex(
				indexName, keyConverter, valueConverter, comparator,null,
				bidirectional, createIfNecessary);

        assertNotNull(createdIndex);
		shutdown();
	}

	@Test
	public void bidirectionalFlagIsSetToFalse() throws Exception
	{
		startup(1);
		final boolean bidirectional = false;

        final HGIndex<Integer, String> createdIndex = storage.getIndex(
				"sampleIndex", null, null, null, null, bidirectional, true);

        assertNotNull(createdIndex);
		shutdown();
	}

	@Test
	public void createIfNecessaryFlagIsSetToFalse() throws Exception
	{
		startup();
		final boolean createIfNecessary = false;

        final HGIndex<Object, Object> createdIndex = storage.getIndex(
				"sampleIndex", null, null, null, null, true, createIfNecessary);

        assertNull(createdIndex);
		shutdown();
	}

	@Test
	public void indexWithTheSameNameAlreadyExists() throws Exception
	{
		startup(1);
		final String indexName = "sample index";
		final HGIndex<Object, Object> firstIndex = storage.getIndex(indexName, null, null, null, null, true, true);

        final HGIndex<Object, Object> secondIndex = storage.getIndex(indexName,
				null, null, null, null, true, true);

        assertEquals(firstIndex, secondIndex);
		shutdown();
	}

    @Test
    public void indexNameIsNull() throws Exception {
        startup(1);
        final String indexName = null;

        final HGIndex<Object, Object> createdIndex = storage.getIndex(indexName, null, null, null, null, true, true);

        assertNotNull(createdIndex);
        shutdown();
    }
}
