package com.github.jep.hugejson.test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import junit.framework.TestCase;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public final class ObjectTypeAdapterTest extends TestCase {

    private final Gson gson = new GsonBuilder().create();
    private final TypeAdapter<Object> adapter = gson.getAdapter(Object.class);

    private File file = new File("src/main/resources/hugeJson.json");
    private String content = FileUtils.readFileToString(file, "UTF-8");

    public ObjectTypeAdapterTest() throws IOException {
    }

    public void testDeserialize() throws Exception {
        Map<?, ?> map = (Map<?, ?>) adapter.fromJson("{\"a\":5,\"b\":[1,2,null],\"c\":{\"x\":\"y\"}}");
        assertEquals(5.0, map.get("a"));
        assertEquals(Arrays.asList(1.0, 2.0, null), map.get("b"));
        assertEquals(Collections.singletonMap("x", "y"), map.get("c"));
        assertEquals(3, map.size());
    }

    public void testSerialize() throws Exception {
        Object object = new RuntimeType();
        assertEquals("{'a':5,'b':[1,2,null]}", adapter.toJson(object).replace("\"", "'"));
    }

    public void testSerializeNullValue() throws Exception {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("a", null);
        assertEquals("{'a':null}", adapter.toJson(map).replace('"', '\''));
    }

    public void testDeserializeNullValue() throws Exception {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("a", null);
        assertEquals(map, adapter.fromJson("{\"a\":null}"));
    }

    public void testSerializeObject() throws Exception {
        assertEquals("{}", adapter.toJson(new Object()));
    }

    @SuppressWarnings("unused")
    private class RuntimeType {
        Object a = 5;
        Object b = Arrays.asList(1, 2, null);
    }
}